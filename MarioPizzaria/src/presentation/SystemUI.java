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
        int pizzaValg = 0;
        boolean pizzaChoice = false;
        while (!pizzaChoice) {
            try {
                System.out.println("Skriv hvilket pizzanummer kunden har bestilt: ");
                pizzaValg = Integer.parseInt(scan.nextLine());
                pizzaChoice = true;
            }
            catch (Exception E) {
                System.out.println("Du har vidst tastet forkert.. Prøve igen.");
            }
        }
        return pizzaValg;
    }

    @Override
    public void showPizzaSelection(String pizzaSelection) {
        System.out.println(pizzaSelection);
    }

    @Override
    public void displayOrderNumber(int orderNumber) {
        newLines();
        System.out.println("Ordrernummeret er: " + orderNumber + "\n");
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
        System.out.println("7. Vis statistik");
        System.out.println("8. Afslut program");
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

    @Override
    public int selectPizzaAmount() {
        System.out.println("Skriv hvor mange af pizzaen kunde har bestilt: ");
        int pizzaAmount = scan.nextInt();
        scan.nextLine();
        return pizzaAmount;
    }

    @Override
    public Boolean selectMorePizza() {
        System.out.println("Har kunden bestilt flere pizzaer?: ");
        System.out.println("1. Ja");
        System.out.println("2. Nej");
        int morePizza = scan.nextInt();
        scan.nextLine();

        return morePizza == 1;
    }

    @Override
    public void showPizzaListSelection(ArrayList<Pizza> pizzaList) {
        for (Pizza pizza : pizzaList) {
            System.out.println(pizza);
        }
        pressAnyKey();
    }

    @Override
    public String getPassword() {
        System.out.println("Skriv venligst kodeordet til databasen.");
        String password = scan.nextLine();
        return password;
    }

    @Override
    public void cancelOrderMsg(Order order) {
        System.out.println("Du har annulleret " + order);
    }

    @Override
    public int askFacade() {
        int answer = 0;
        System.out.println("Skal data gemmes i:");
        System.out.println("1. Database");
        System.out.println("2. Fil");
        answer = scan.nextInt();
        scan.nextLine();
        return answer;
    }

    @Override
    public void showStatistics(String readStatistics) {
        System.out.println(readStatistics);
    }

}
