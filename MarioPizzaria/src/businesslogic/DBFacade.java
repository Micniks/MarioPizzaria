/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package businesslogic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author Jens, Michael, Nicolai, Oscar
 */
public class DBFacade implements Facade {

    private final Connection connect;
    private Statement statement;
    private String serverTime = "serverTimezone=UTC";

    public DBFacade(String password) throws SQLException {
        connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/Marios_Pizza+?" + serverTime, "root", password);
        statement = connect.createStatement();

    }

    public DBFacade() throws SQLException {
        connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/Marios_Pizza+?" + serverTime, "root", "1234");
        statement = connect.createStatement();
    }

    public void insertOrders(Order order, double orderPrice) throws SQLException {
        StringBuilder sb = new StringBuilder();

        sb.append("INSERT INTO Orders (OrderNo, OrderPrice) VALUES (");
        sb.append(order.getOrderNumber());
        sb.append(",");
        sb.append(orderPrice);
        sb.append(")");

        statement.executeUpdate(sb.toString());
    }

    @Override
    public void archiveOrder(Order order) throws SQLException {
        StringBuilder sb = new StringBuilder();
        ArrayList<Pizza> tempList = order.getPizzaList();

        int[] qtyList = new int[30];

        double orderPrice = 0;
        int qty = 0;
        for (Pizza pizza : tempList) {
            orderPrice += pizza.getPrice();
            qtyList[pizza.getPizzaNumber() - 1]++;
        }

        sb.append("INSERT INTO Orders (OrderNo, OrderPrice) VALUES (");
        sb.append(order.getOrderNumber());
        sb.append(",");
        sb.append(orderPrice);
        sb.append(")");

        statement.executeUpdate(sb.toString());

        for (Pizza pizza : tempList) {
            if (qtyList[pizza.getPizzaNumber() - 1] > 0) {
                insertPizzaOrders(order, pizza, qtyList[pizza.getPizzaNumber() - 1]);
                qtyList[pizza.getPizzaNumber() - 1] = 0;
                //db.insert("('PizzaName', 123)", "Pizza", "(PizzaName, PizzaPrice)");
            }
        }
    }

    public void insertPizzaOrders(Order order, Pizza pizza, int qty) throws SQLException {
        StringBuilder sb = new StringBuilder();
        sb.append("INSERT INTO pizza_orders (OrderNo, PizzaNo, Quantity) VALUES (");
        sb.append(order.getOrderNumber());
        sb.append(",");
        sb.append(pizza.getPizzaNumber());
        sb.append(",");
        sb.append(qty);
        sb.append(")");
        statement.executeUpdate(sb.toString());
    }

    public int readHighestOrderNo() throws SQLException {
        ArrayList temp = new ArrayList();
        int highestOrderNo = 0;
        ResultSet result = statement.executeQuery("SELECT * from Orders");
        while (result.next()) {
            temp.add(result.getInt("OrderNo"));
        }
        if (temp.size() != 0) {
            highestOrderNo = (int) Collections.max(temp);
            highestOrderNo++;
        } else {
            highestOrderNo = 1;
        }
        return highestOrderNo;

    }

    @Override
    public ArrayList<String> readHistory() throws SQLException {
        int currentOrderNo;
        ArrayList<String> history = new ArrayList();
        StringBuilder sb = new StringBuilder();
        ResultSet resultPizzaOrders = statement.executeQuery("SELECT * from pizza_orders");

        ArrayList orderNumbers = new ArrayList();
        ArrayList pizzaNr = new ArrayList();
        ArrayList pizzaQty = new ArrayList();

        while (resultPizzaOrders.next()) {
            orderNumbers.add(resultPizzaOrders.getInt("OrderNo"));
            pizzaNr.add(resultPizzaOrders.getInt("PizzaNo"));
            pizzaQty.add(resultPizzaOrders.getInt("Quantity"));

        }

        ResultSet resultPizza = statement.executeQuery("SELECT * FROM pizza");
        ArrayList pizzanavn = new ArrayList();
        while (resultPizza.next()) {
            pizzanavn.add(resultPizza.getString("PizzaName"));

        }

        ResultSet resultOrders = statement.executeQuery("SELECT * FROM orders");
        while (resultOrders.next()) {
            sb.append("ordrenummer: ");
            currentOrderNo = resultOrders.getInt("OrderNo");
            sb.append(currentOrderNo);
            sb.append("\n");
            for (int i = 0; i < orderNumbers.size(); i++) {

                if (orderNumbers.get(i).equals(currentOrderNo)) {
                    sb.append(pizzaQty.get(i));
                    sb.append(" Stk. ");
                    sb.append(pizzanavn.get((int) pizzaNr.get(i) - 1));
                    sb.append("\n");

                }

            }
            history.add(sb.toString());
            sb.delete(0, sb.length());
        }
        return history;
    }

    @Override
    public String readStatistics() throws SQLException {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT PizzaName, pizza_orders.PizzaNo, sum(Quantity) as Sold_Pizzas from pizza_orders ");
        sb.append("join Orders ");
        sb.append("on pizza_orders.OrderNo = orders.OrderNo ");
        sb.append("join pizza ");
        sb.append("on pizza.PizzaNo = pizza_orders.PizzaNo ");
        sb.append("group by PizzaNo ");
        sb.append("order by Sold_Pizzas desc");
        ResultSet pizzaPopularity = statement.executeQuery(sb.toString());
        sb.delete(0,sb.length());
        
        ArrayList <String> pizzaName = new ArrayList();
        //ArrayList <Integer> pizzaNo = new ArrayList();
        ArrayList <Integer> soldPizzas = new ArrayList();
        
        while (pizzaPopularity.next()){
            pizzaName.add(pizzaPopularity.getString("PizzaName"));
           // pizzaNo.add(pizzaPopularity.getInt("PizzaNo"));
            soldPizzas.add(pizzaPopularity.getInt("Sold_Pizzas"));
        }
        sb.append("Pizza listet efter popularitet: ");
        sb.append("\n");
        int count = 1;
        for (int i = 0; pizzaName.size() > i; i++){
          sb.append(count++);  
          sb.append(". ");
          sb.append(pizzaName.get(i));
          sb.append(", ");
          sb.append(soldPizzas.get(i));
          sb.append(" solgte.");
          sb.append("\n");
          
        }
        
        return sb.toString();
    }
}
