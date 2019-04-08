package businesslogic;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import presentation.UI;
import java.util.ArrayList;

/**
 *@author Michael N. Korsgaard
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

    public Controller(UI ui, ArrayList<Pizza> menu) {
        this.menu = menu;
        this.ui = ui;
        activeOrders = new ArrayList<Order>();
        currentOrderNr = 1;
        file = new FileFacade();
    }

    public String getName() {
        return name;
    }

    public void newOrder() {
        //indlæs pizzanr
        int pizzaNumber = ui.selectPizza();

        //opret bestilling
        Order order = new Order(menu.get(pizzaNumber - 1), currentOrderNr);

        //tilføj bestilling til ordreliste
        activeOrders.add(order);

        //vis ordrenummer på skærm
        ui.displayOrderNumber(currentOrderNr);

        //vis pizzavalg
        ui.showPizzaSelection((order.getPizza().toString()));

        //tæl orderNummer op
        currentOrderNr++;

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
        //Fix me: der mangler en måde at catche et forkert input
        while (temp) {
            if (activeOrders.get(index).getOrderNumber() == ordreNummer) {
                //læg i historik
                file.archiveOrder(activeOrders.get(index));
                activeOrders.remove(index);
                temp = false;

            } else {
                index++;
            }
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
        while (temp) {
            if (activeOrders.get(index).getOrderNumber() == orderNumber) {
                activeOrders.remove(index);
                temp = false;
            } else {
                index++;
            }
        }
    }

    public void displayActiveOrders() {
        for (Order order : activeOrders) {

            ui.displayOrders(order);
        }
        ui.pressAnyKey();
    }

}