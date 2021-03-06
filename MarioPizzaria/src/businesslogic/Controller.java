package businesslogic;

import datasource.Facade;
import datasource.FileFacade;
import java.sql.SQLException;
import presentation.UI;
import java.util.ArrayList;

/* @author Michael N. Korsgaard
 * @author Nicolai Gregersen
 * @author Jens Brønd
 * @author Oscar Laurblad*/

public class Controller {

    private UI ui;
    private String name;
    private ArrayList<Pizza> menu;
    private ArrayList<Order> activeOrders;
    private int currentOrderNr;
    private FileFacade file;
    private Facade facade;

    public Controller(UI ui, ArrayList<Pizza> menu, Facade facade) throws SQLException {
        this.menu = menu;
        this.ui = ui;
        this.facade = facade;
        activeOrders = new ArrayList<Order>();
        currentOrderNr = facade.readHighestOrderNo();
        file = new FileFacade();

    }

    public String getName() {
        return name;
    }

    public void newOrder() {

        //Opret pizza-ordre
        Order order = new Order(currentOrderNr);
        Boolean morePizza = true;
        while (morePizza) {
            try {
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
            } catch (Exception e) {
                System.out.println("Du indtastede ikke et gældende pizzanummer. Prøv igen.");
            }
        }
        activeOrders.add(order);
        ui.displayOrderNumber(currentOrderNr);
        ui.showPizzaListSelection((order.getPizzaList()));
        currentOrderNr++;

    }

    public ArrayList<Order> getActiveOrders() {
        return activeOrders;
    }

    public void displayMenu() {
        ui.displayMenu(menu);

    }

    public void start() throws SQLException {
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
                    displayStatistics();
                    break;
                case "8":
                    quit = true;
                    break;
                default:
                    throw new IllegalArgumentException();
            }
        } while (!quit);

    }

    public void finishOrder() throws SQLException {
        int index = 0;
        int ordreNummer = ui.selectOrder();
        boolean temp = true;

        while (temp) {
            if (activeOrders.get(index).getOrderNumber() == ordreNummer) {
                facade.archiveOrder(activeOrders.get(index));
                activeOrders.remove(index);
                temp = false;
            } else {
                index++;
            }
        }

    }

    public void displayHistory() throws SQLException {
        ui.displayHistory(facade.readHistory());
        ui.pressAnyKey();
    }

    public void cancelOrder() {
        int index = 0;
        int orderNumber = ui.selectOrder();

        boolean temp = true;
        try {
            while (temp) {
                if (activeOrders.get(index).getOrderNumber() == orderNumber) {
                    ui.cancelOrderMsg(activeOrders.get(index));
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

    private void displayStatistics() throws SQLException {
        ui.showStatistics(facade.readStatistics());
        ui.pressAnyKey();
    }

}
