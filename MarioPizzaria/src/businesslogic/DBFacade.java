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
public class DBFacade {

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

//        System.out.println("INSERT INTO " + table + " " + columns + " VALUES " + values);
//        statement.executeUpdate("INSERT INTO " + table + " " + columns + " VALUES (" + values + ")");
        sb.append("INSERT INTO Orders (OrderNo, OrderPrice) VALUES (");
        sb.append(order.getOrderNumber());
        sb.append(",");
        sb.append(orderPrice);
        sb.append(")");

//        sb.append("INSERT INTO ");
//        sb.append(table);
//        sb.append("(");
//        sb.append(columns);
//        sb.append(") VALUES");
//        sb.append("(");
//        sb.append(values);
//        sb.append(")");
//String values, String table, String columns
        statement.executeUpdate(sb.toString());

    }

    public void select(String table) throws SQLException {
        ResultSet result = statement.executeQuery("SELECT * FROM " + table);
        while (result.next()) {
            String s = result.getString(1);
            System.out.println(s);
        }
    }

    void insertPizzaOrders(Order order, Pizza pizza, int qty) throws SQLException {
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

    public ArrayList readOrderPriceFromDatabase() throws SQLException {
        ArrayList al = new ArrayList();
        ResultSet result = statement.executeQuery("SELECT * FROM Orders");
        while (result.next()) {
            al.add(result.getDouble("OrderPrice"));
        }
        return al;
    }

    public ArrayList readPizzaQtyFromDatabase() throws SQLException {
        ArrayList al = new ArrayList();
        ResultSet result = statement.executeQuery("SELECT * FROM pizza_orders");
        while (result.next()) {
            al.add(result.getInt("PizzaNo"));
            al.add(result.getInt("Quantity"));
        }
        return al;
    }

    public int readHighestOrderNo() throws SQLException {
        ArrayList temp = new ArrayList();
        ResultSet result = statement.executeQuery("SELECT * from Orders");
        while (result.next()) {
            temp.add(result.getInt("OrderNo"));
        }
        int highestOrderNo = (int) Collections.max(temp);
        highestOrderNo++;
        return highestOrderNo;

    }

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
}
