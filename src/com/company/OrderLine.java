package com.company;

/**
 * @author Jaedoo Lee
 */
public class OrderLine {
    private Product product;
    private Money price;
    private int quantity;
    private int amounts;

    public OrderLine(Product product, Money price, int quantity) {
        this.product = product;
        this.price = new Money(price.getValue());
        this.quantity = quantity;
        this.amounts = calculateAmounts();
    }

    private int calculateAmounts() {
        return price * quantity;
    }

    public int getAmounts() {
        return amounts;
    }
}
