package businesslogic;

/* @author Michael N. Korsgaard
 * @author Nicolai Gregersen
 * @author Jens Brønd
 * @author Oscar Laurblad*/

public class Pizza {

    private int pizzaNumber;
    private String pizzaName;
    private double price;
    private String ingredients;

    public Pizza(int pizzaNumber, String pizzaName, double price, String ingredients) {
        this.pizzaName = pizzaName;
        this.pizzaNumber = pizzaNumber;
        this.price = price;
        this.ingredients = ingredients;
    }

    public int getPizzaNumber() {
        return pizzaNumber;
    }

    public String getPizzaName() {
        return pizzaName;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return pizzaNumber + ". " + pizzaName + ", Pris DKK:" + price + "\n" + ingredients + " \n";
    }

}
