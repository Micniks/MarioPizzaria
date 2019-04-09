package businesslogic;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import presentation.UI;
import java.util.ArrayList;

/**
 * @author Michael N. Korsgaard
 * @author Nicolai Gregersen
 * @author Jens Brønd
 * @author Oscar Laurberg
 */
public class Controller {

    private UI ui;
    private String name;
    private ArrayList<Pizza> menu;
    private ArrayList<Order> activeOrders;
    private int currentOrderNr;
    private FileFacade file;
    private DBFacade db;

    public Controller(UI ui, ArrayList<Pizza> menu) throws SQLException {
        this.menu = menu;
        this.ui = ui;
        activeOrders = new ArrayList<Order>();
        currentOrderNr = 1;
        file = new FileFacade();
        db = new DBFacade();
    }
    
    public String getName() {
        return name;
    }

    public void newOrder() {
        /*  OLD CODE
        int pizzaNumber = ui.selectPizza();
        Order order = new Order(menu.get(pizzaNumber - 1), currentOrderNr);
        activeOrders.add(order);
        ui.displayOrderNumber(currentOrderNr);
        ui.showPizzaSelection((order.getPizza().toString()));
        currentOrderNr++;
        
         */
        //Opret pizza-ordre
        Order order = new Order(currentOrderNr);
        Boolean morePizza = true;
        try {
            do {
                //vælg pizza at bestille
                int pizzaNumber = ui.selectPizza();
                Pizza pizza = menu.get(pizzaNumber - 1);

                //vælg hvor mange af pizzaen der skal tilføjes
                int pizzaAmount = ui.selectPizzaAmount();
                for (int i = 0; i < pizzaAmount; i++) {
                    order.addPizza(pizza);
                }

                //vælg om der skal bestilles andre pizzaer
                morePizza = ui.selectMorePizza();
            } while (morePizza);
            activeOrders.add(order);
            ui.displayOrderNumber(currentOrderNr);
            ui.showPizzaListSelection((order.getPizzaList()));
            currentOrderNr++;

        } catch (Exception e) {
            System.out.println("Du indtastede ikke et gældende pizzanummer. Du returneres nu til hovedmenuen.");
        }
    }

    public ArrayList<Order> getActiveOrders() {
        return activeOrders;
    }

    public void displayMenu() {
        ui.displayMenu(menu);

    }

    public void start() {
        boolean quit = false;
        do {
            ui.displayMainMenu();
            String userInput = ui.mainMenuSelection();
            switch (userInput) {
                case "1":
                    displayMenu();
                    break;
                case "2":
                    newOrder();
                    break;
                case "3":
                    displayActiveOrders();
                    break;
                case "4":
                    finishOrder();
                    break;
                case "5":
                    cancelOrder();
                    break;
                case "6":
                    displayHistory();
                    break;
                case "7":
                    quit = true;
                    break;
                default:
                    throw new IllegalArgumentException();
            }
        } while (!quit);

    }

    public void finishOrder() {
        Order currentOrdre = null;
        int index = 0;
        int ordreNummer = ui.selectOrder();
        boolean temp = true;
        try {
            while (temp) {
                if (activeOrders.get(index).getOrderNumber() == ordreNummer) {
                    //læg i historik
                    //file.archiveOrder(activeOrders.get(index));
                    db.insert(, name, name);
                    activeOrders.remove(index);
                    temp = false;

                } else {
                    index++;
                }
            }

        } catch (Exception e) {
            System.out.println("Du indstastede ikke et aktivt ordrenummer.\n Du returneres nu til hovedmenuen.");
        }
    }

    public void displayHistory() {
        ui.displayHistory(file.readHistory());
        ui.pressAnyKey();
    }

    public void cancelOrder() {
        int index = 0;
        int orderNumber = ui.selectOrder();

        boolean temp = true;
        try {
            while (temp) {
                if (activeOrders.get(index).getOrderNumber() == orderNumber) {
                    activeOrders.remove(index);
                    temp = false;
                } else {
                    index++;
                }
            }
        } catch (Exception e) {
            System.out.println("Du indtastede ikke et aktivt ordrenummer.\nDu returneres til hovedmenuen.");
        }
    }

    public void displayActiveOrders() {
        for (Order order : activeOrders) {

            ui.displayOrders(order);
        }
        ui.pressAnyKey();
    }

}
