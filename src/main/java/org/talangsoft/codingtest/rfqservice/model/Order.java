package org.talangsoft.codingtest.rfqservice.model;

public class Order {
    private Direction direction;
    private double price;
    private String currency;
    private int amountOfBitcoins;

    public Order(Direction direction, double price, String currency, int amountOfBitcoins) {
        this.direction = direction;
        this.price = price;
        this.currency = currency;
        this.amountOfBitcoins = amountOfBitcoins;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public int getAmountOfBitcoins() {
        return amountOfBitcoins;
    }

    public void setAmountOfBitcoins(int amountOfBitcoins) {
        this.amountOfBitcoins = amountOfBitcoins;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Order order = (Order) o;

        if (Double.compare(order.price, price) != 0) return false;
        if (amountOfBitcoins != order.amountOfBitcoins) return false;
        if (direction != order.direction) return false;
        return currency.equals(order.currency);

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = direction.hashCode();
        temp = Double.doubleToLongBits(price);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + currency.hashCode();
        result = 31 * result + amountOfBitcoins;
        return result;
    }

    @Override
    public String toString() {
        return "Order{" +
                "direction=" + direction +
                ", price=" + price +
                ", currency='" + currency + '\'' +
                ", amountOfBitcoins=" + amountOfBitcoins +
                '}';
    }
}
