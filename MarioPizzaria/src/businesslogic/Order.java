package businesslogic;

import java.util.ArrayList;

/* @author Michael N. Korsgaard
 * @author Nicolai Gregersen
 * @author Jens Brønd
 * @author Oscar Laurblad*/

public class Order {

    private ArrayList<Pizza> pizzaList;
    private Pizza pizza;
    private int orderNumber;

    //bestillingsnummer mangler
    public Order(Pizza pizza, int orderNumber) {
        this.pizza = pizza;
        this.orderNumber = orderNumber;
    }

    public Order(int orderNumber) {
        this.orderNumber = orderNumber;
        this.pizzaList = new ArrayList();
    }

    public void addPizza(Pizza pizza) {
        pizzaList.add(pizza);
    }

    public Pizza getPizza() {
        return pizza;
    }

    public ArrayList<Pizza> getPizzaList() {
        return pizzaList;
    }

    public int getOrderNumber() {
        return orderNumber;
    }

    @Override
    public String toString() {
        StringBuilder SB = new StringBuilder();
        SB.append("OrdreNummer: " + orderNumber + "\n");
        for (Pizza pizza : pizzaList) {
            SB.append(pizza);

        }
        return SB.toString();
    }

}
