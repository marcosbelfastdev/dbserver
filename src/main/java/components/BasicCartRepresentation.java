package components;

public class BasicCartRepresentation {

    private int numberOfProducts;
    private float totalToPay;

    public void increaseProductCount() {
        numberOfProducts++;
    }

    public void addToTotal(float subtotal) {
        totalToPay += subtotal;
    }

    public int getNumberOfItemsInCart() {
        return numberOfProducts;
    }

    public float getTotalToPay() {
        return totalToPay;
    }
}
