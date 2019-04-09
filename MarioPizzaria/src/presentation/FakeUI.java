package presentation;

import businesslogic.Order;
import businesslogic.Pizza;
import presentation.UI;
import java.util.ArrayList;

/**
 *@author Michael N. Korsgaard
 * @author Nicolai Gregersen
 * @author Jens Brønd
 * @author Oscar Laurberg
 */
public class FakeUI implements UI {

    private int index = 0;
    private String[] input;
    public ArrayList<String> output = new ArrayList();

    public FakeUI(String[] input) {
        this.input = input;
    }

    @Override
    public int selectPizza() {
        output.add("Skriv hvilket pizzanummer kunden har bestilt: ");
        return Integer.parseInt(input[index++]);
    }

    @Override
    public void showPizzaSelection(String str) {
        output.add(str);
    }

    @Override
    public void displayOrderNumber(int orderNumber) {
        newLines();
        output.add("Ordrernummeret er: " + orderNumber);
        output.add("");
    }

    @Override
    public void displayMenu(ArrayList<Pizza> menukort) {

        for (Pizza pizza : menukort) {
            output.add(pizza.toString());
        }
        pressAnyKey();

    }

    @Override
    public void displayMainMenu() {
        newLines();
        output.add("Vælg en af følgende muligheder:");
        output.add("1. Vis menu");
        output.add("2. Opret bestilling");
        output.add("3. Se aktive ordrer");
        output.add("4. Afslut bestilling");
        output.add("5. Annullere en bestilling");
        output.add("6. Se Historik");
        output.add("7. Afslut program");
    }

    @Override
    public String mainMenuSelection() {
        return input[index++];
    }

    @Override
    public void displayHistory(ArrayList<String> readHistory) {
        output.add("Historik:");
        for (String arkiveretOrdre : readHistory) {
            output.add(arkiveretOrdre);
        }
    }

    @Override
    public int selectOrder() {
        newLines();
        output.add("Indtast ordrenummer på den ordre du vil afslutte: ");
        return Integer.parseInt(input[index++]);
    }

    @Override
    public void pressAnyKey() {
        output.add("Press any key to return to the main menu.");
        String temp = input[index++];
    }

    @Override
    public void displayOrders(Order order) {
        output.add(order.toString());
    }

    @Override
    public int selectPizzaAmount() {
        output.add("Skriv hvor mange af pizzaen kunde har bestilt: ");
        return Integer.parseInt(input[index++]);
    }

    @Override
    public Boolean selectMorePizza() {
        output.add("Har kunden bestilt flere pizzaer?: ");
        output.add("1. Ja");
        output.add("2. Nej");
        return Integer.parseInt(input[index++]) == 1;
    }

    @Override
    public void showPizzaListSelection(ArrayList<Pizza> pizzaList) {
        for (Pizza pizza : pizzaList) {
            output.add(pizza.toString());
        }
        pressAnyKey();
    }

    private void newLines() {
        output.add("");
        output.add("");
        output.add("");
        output.add("");
        output.add("");
    }

}
