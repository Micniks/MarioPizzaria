/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package businesslogic;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Michael N. Korsgaard
 */
public interface Facade {
    public void archiveOrder(Order order) throws SQLException;
    ArrayList<String> readHistory() throws SQLException;
    public int readHighestOrderNo() throws SQLException;
}
