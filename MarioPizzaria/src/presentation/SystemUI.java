package presentation;

import businesslogic.Order;
import businesslogic.Pizza;
import java.util.ArrayList;
import presentation.UI;
import java.util.Scanner;

/**
 *
 * @author Michael N. Korsgaard
 * @author Nicolai Gregersen
 * @author Jens Brønd
 * @author Oscar Laurberg
 */
public class SystemUI implements UI {

    Scanner scan = new Scanner(System.in);

    public SystemUI() {

    }

    @Override
    public int selectPizza() {
        System.out.println("Skriv hvilket pizzanummer kunden har bestilt: ");
        int pizzaValg = scan.nextInt();
        scan.nextLine();
        return pizzaValg;
    }

    @Override
    public void showPizzaSelection(String pizzaSelection) {
        System.out.println(pizzaSelection);
    }

    @Override
    public void displayOrderNumber(int orderNumber) {
        System.out.println("Ordrernummeret er: " + orderNumber +"\n");
        pressAnyKey();
    }

    @Override
    public void displayMenu(ArrayList<Pizza> menu) {
        for (Pizza pizza : menu) {
            System.out.println(pizza.toString());
        }
        pressAnyKey();

    }

    @Override
    public void displayMainMenu() {
        newLines();
        System.out.println("Vælg en af følgende muligheder:");
        System.out.println("1. Vis menu");
        System.out.println("2. Opret bestilling");
        System.out.println("3. Se aktive ordrer");
        System.out.println("4. Afslut bestilling");
        System.out.println("5. Annullere en bestilling");
        System.out.println("6. Se historik");
        System.out.println("7. Afslut program");
    }

    @Override
    public String mainMenuSelection() {
        String menuValg = scan.nextLine();
        return menuValg;
    }

    @Override
    public void displayHistory(ArrayList<String> readHistory) {
        System.out.println("Historik:");
        for (String arkiveretOrdre : readHistory) {
            System.out.println(arkiveretOrdre);
        }
    }

    @Override
    public int selectOrder() {
        newLines();
        System.out.println("Indtast ordrenummer på den ordre du vil afslutte: ");
        int valg = scan.nextInt();
        scan.nextLine();
        return valg;
    }

    @Override
    public void pressAnyKey() {
        System.out.println("Press any key to return to the main menu.");
        String temp = scan.nextLine();

    }

    @Override
    public void displayOrders(Order order) {
        System.out.println(order);

    }

    private void newLines() {
        System.out.println("");
        System.out.println("");
        System.out.println("");
        System.out.println("");
        System.out.println("");

    }
}