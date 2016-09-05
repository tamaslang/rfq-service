package org.talangsoft.codingtest.rfqservice.model;


public class Quote {
    public final double bid;
    public final double ask;

    public Quote(double bid, double ask) {
        this.bid = bid;
        this.ask = ask;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Quote quote = (Quote) o;

        if (Double.compare(quote.bid, bid) != 0) return false;
        return Double.compare(quote.ask, ask) == 0;

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        temp = Double.doubleToLongBits(bid);
        result = (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(ask);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "Quote{" +
                "bid=" + bid +
                ", ask=" + ask +
                '}';
    }
}
