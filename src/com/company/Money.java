package com.company;

/**
 * @author Jaedoo Lee
 */
public class Money {
    private int value;

    public Money add(Money money) {
        return new Money(this.value + money.value);
    }

    public int getValue() {
        return value;
    }
}
