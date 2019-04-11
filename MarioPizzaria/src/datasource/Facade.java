package datasource;

import businesslogic.Order;
import java.sql.SQLException;
import java.util.ArrayList;

/* @author Michael N. Korsgaard
 * @author Nicolai Gregersen
 * @author Jens Brønd
 * @author Oscar Laurblad*/

public interface Facade {

    public void archiveOrder(Order order) throws SQLException;

    ArrayList<String> readHistory() throws SQLException;

    public int readHighestOrderNo() throws SQLException;

    public String readStatistics() throws SQLException;

}
