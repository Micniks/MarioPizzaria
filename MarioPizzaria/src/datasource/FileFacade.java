package datasource;

import businesslogic.Order;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;

/* @author Michael N. Korsgaard
 * @author Nicolai Gregersen
 * @author Jens Brønd
 * @author Oscar Laurblad*/

public class FileFacade implements Facade {

    File orderHistory = new File("Ordre Historik.txt");
    FileWriter fw;
    BufferedWriter bufWriter;
    FileReader fr;
    BufferedReader bufReader;

    public FileFacade() {

        try {
            fw = new FileWriter(orderHistory, true);
            bufWriter = new BufferedWriter(fw);
            fr = new FileReader(orderHistory);
            bufReader = new BufferedReader(fr);
        } catch (IOException ex) {
            Logger.getLogger(FileFacade.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void archiveOrder(Order order) {

        try {
            bufWriter.write(order.toString());
            bufWriter.newLine();
            bufWriter.flush();
        } catch (IOException ex) {
            Logger.getLogger(FileFacade.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public ArrayList<String> readHistory() {
        ArrayList<String> history = new ArrayList();
        try {
            while (bufReader.ready()) {
                history.add(bufReader.readLine());
            }
        } catch (IOException ex) {
            Logger.getLogger(FileFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
        return history;
    }

//Write a method to find the highest order number from the files
    @Override
    public int readHighestOrderNo() throws SQLException {

        ArrayList<String> history = readHistory();
        ArrayList<Integer> orderNumbers = new ArrayList();
        int orderNo;
        int orderNumber;
        for (String str : history) {
            if (str.contains("OrdreNummer: ")) {
                String[] orderString;
                orderString = str.split(" ");
                orderNumber = Integer.parseInt(orderString[1]);
                orderNumbers.add(orderNumber);
            }
        }
        if (!orderNumbers.isEmpty()) {
            orderNo = (int) Collections.max(orderNumbers);
            orderNo++;
        } else {
            orderNo = 1;
        }
        return orderNo;
    }

    @Override
    public String readStatistics() throws SQLException {
        return "Statistik ikke undersøttet i fil-modulet endnu";
    }

}
