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
    
    public DBFacade() throws SQLException{
        connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/Marios_Pizza", "root", "1234");
        statement = connect.createStatement();
    }
    
    public void insert(String values, String table, String columns) throws SQLException{
        statement.executeUpdate("INSERT INTO " + table + " " + columns + " VALUES " + values);
    }
    public void select(String table) throws SQLException{
        ResultSet result = statement.executeQuery("SELECT * FROM " + table);
        while (result.next()){
            int r = result.getInt(2);
            System.out.println(r);
        }
    }
}
