
package businesslogic;

import java.util.ArrayList;

/**
 *@author Michael N. Korsgaard
 * @author Nicolai Gregersen
 * @author Jens Brønd
 * @author Oscar Laurberg
 */
public class Order {
    
    //  NEW CODE:   PizzaArray to make a order contain more pizzas
    //  private ArrayList<Pizza> pizzaList;
    
    private Pizza pizza;
    private int orderNumber;
    
    //bestillingsnummer mangler

    public Order(Pizza pizza, int orderNumber) {
        this.pizza = pizza;
        this.orderNumber = orderNumber;
    }

    public Pizza getPizza() {
        return pizza;
    }

    public int getOrderNumber() {
        return orderNumber;
    }
    
    @Override
    public String toString() {
        return "OrdreNummer: " + orderNumber + "\n" + pizza;
    }
    
    
    
    
    
}
