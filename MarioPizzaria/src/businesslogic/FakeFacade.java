package businesslogic;

import java.sql.SQLException;
import java.util.ArrayList;

public class FakeFacade implements Facade {
    
    public ArrayList <String> testArray;
    
    public FakeFacade (){
        this.testArray= new ArrayList();
        
        
    }
    
    @Override
    public void archiveOrder(Order order) throws SQLException {
        testArray.add(order.toString());
    }

    @Override
    public ArrayList<String> readHistory() throws SQLException {
        return testArray;
    }

    @Override
    public int readHighestOrderNo() throws SQLException {
        return 1;
    }
    
    
}
