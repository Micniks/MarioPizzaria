package datasource;

import businesslogic.Order;
import businesslogic.Pizza;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;

/* @author Michael N. Korsgaard
 * @author Nicolai Gregersen
 * @author Jens Brønd
 * @author Oscar Laurberg*/
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
        StringBuilder sbStat = new StringBuilder();
        StringBuilder sbQuery1 = new StringBuilder();
        sbQuery1.append("SELECT PizzaName, pizza_orders.PizzaNo, sum(Quantity) as Sold_Pizzas from pizza_orders ");
        sbQuery1.append("join Orders ");
        sbQuery1.append("on pizza_orders.OrderNo = orders.OrderNo ");
        sbQuery1.append("join pizza ");
        sbQuery1.append("on pizza.PizzaNo = pizza_orders.PizzaNo ");
        sbQuery1.append("group by PizzaNo ");
        sbQuery1.append("order by Sold_Pizzas desc");
        ResultSet pizzaPopularity = statement.executeQuery(sbQuery1.toString());
        
        ArrayList <String> pizzaName = new ArrayList();
        ArrayList <Integer> soldPizzas = new ArrayList();
        
        while (pizzaPopularity.next()){
            pizzaName.add(pizzaPopularity.getString("PizzaName"));
            soldPizzas.add(pizzaPopularity.getInt("Sold_Pizzas"));
        }
        sbStat.append("Pizza listet efter popularitet: ");
        sbStat.append("\n");
        int count = 1;
        for (int i = 0; pizzaName.size() > i; i++){
          sbStat.append(count++);  
          sbStat.append(". ");
          sbStat.append(pizzaName.get(i));
          sbStat.append(", ");
          sbStat.append(soldPizzas.get(i));
          sbStat.append(" solgte.");
          sbStat.append("\n");
          
        }
        
        sbStat.append("\n \n \n");
        sbStat.append("Timer listet efter travlhed:");
        sbStat.append("\n");
        
        StringBuilder sbQuery2 = new StringBuilder();
        sbQuery2.append("SELECT date_format( pickup_time, '%H' ) as Hour, ");
        sbQuery2.append("count(*) as Order_Qty ");
        sbQuery2.append("from orders ");
        sbQuery2.append("group by Hour ");
        sbQuery2.append("order by Order_Qty desc");
        ResultSet busyHours = statement.executeQuery(sbQuery2.toString());
        ArrayList hour = new ArrayList();
        ArrayList orderQty = new ArrayList();
        
        while (busyHours.next()){
            hour.add(busyHours.getInt("Hour"));
            orderQty.add(busyHours.getInt("Order_Qty"));
        }
        for (int i = 0; i < hour.size(); i++){
            sbStat.append("kl. ");
            sbStat.append(hour.get(i));
            sbStat.append(", antal ordrer: ");
            sbStat.append(orderQty.get(i));
            sbStat.append("\n");
        }
        return sbStat.toString();
    }
}
