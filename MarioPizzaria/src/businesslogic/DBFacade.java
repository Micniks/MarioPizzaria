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
/**
 *
 * @author Jens
 */
public class DBFacade{
    private final Connection connect;
    private Statement statement;
    private StringBuilder sb;
    
    public DBFacade() throws SQLException{
        connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/Marios_Pizza", "root", "1234");
        statement = connect.createStatement();
        sb = new StringBuilder();
    }
    
    public void insertOrders(String values, String table, String columns) throws SQLException{
        
//        System.out.println("INSERT INTO " + table + " " + columns + " VALUES " + values);
//        statement.executeUpdate("INSERT INTO " + table + " " + columns + " VALUES (" + values + ")");
        sb.append("INSERT INTO ");
        sb.append(table);
        sb.append("(");
        sb.append(columns);
        sb.append(") VALUES");
        sb.append("(");
        sb.append(values);
        sb.append(")");
        statement.executeUpdate(sb.toString());
        
    }
    public void select(String table) throws SQLException{
        ResultSet result = statement.executeQuery("SELECT * FROM " + table);
        while (result.next()){
            String s = result.getString(1);
            System.out.println(s);
        }
    }

    void insertPizzaOrders(String table, String values, String columns) throws SQLException {
        sb.append("INSERT INTO ");
        sb.append(table);
        sb.append("(");
        sb.append(columns);
        sb.append(") VALUES");
        sb.append("('");
        sb.append(values);
        sb.append("')");
        statement.executeUpdate(sb.toString());
    }
}
