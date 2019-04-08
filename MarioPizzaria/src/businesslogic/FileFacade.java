package businesslogic;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *@author Michael N. Korsgaard
 * @author Nicolai Gregersen
 * @author Jens Brønd
 * @author Oscar Laurberg
 */
public class FileFacade {

    File orderHistory = new File("Ordre Historik.txt");
    FileWriter fw;  //false lader den overwrite filen hver gang programmet kører
    BufferedWriter bufWriter;
    FileReader fr;
    BufferedReader bufReader;

    public FileFacade() {

        try {
            fw = new FileWriter(orderHistory, false);
            bufWriter = new BufferedWriter(fw);
            fr = new FileReader(orderHistory);
            bufReader = new BufferedReader(fr);
        } catch (IOException ex) {
            Logger.getLogger(FileFacade.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void archiveOrder(Order order) {

        try {
            bufWriter.write(order.toString());
            bufWriter.newLine();
            bufWriter.flush();
        } catch (IOException ex) {
            Logger.getLogger(FileFacade.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

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

}
