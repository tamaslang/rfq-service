package org.talangsoft.codingtest.rfqservice.service;

import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import org.talangsoft.codingtest.rfqservice.model.Direction;
import org.talangsoft.codingtest.rfqservice.model.Order;
import org.talangsoft.codingtest.rfqservice.model.Quote;

import java.math.BigDecimal;
import java.util.*;

public class RfqServiceImpl implements RfqService {

    private BigDecimal makeSomeMoney = BigDecimal.valueOf(0.02);

    private interface CalculatePriceStrategy {
        double calculatePrice(double price);
    }

    private CalculatePriceStrategy bidPriceStrategy = price -> BigDecimal.valueOf(price).subtract(makeSomeMoney).doubleValue();
    private CalculatePriceStrategy askPriceStrategy = price -> BigDecimal.valueOf(price).add(makeSomeMoney).doubleValue();

    private LiveOrderBoard liveOrderBoard;

    public RfqServiceImpl(LiveOrderBoard liveOrderBoard) {
        this.liveOrderBoard = liveOrderBoard;
    }

    @Override
    public Optional<Quote> quoteFor(String currency, int amount) {
        Preconditions.checkArgument(!"".equals(currency), "Currency should not be empty!");
        Preconditions.checkArgument(amount > 0, "Amount should be a number greater than 0!");

        Currency useCurrency = Currency.createFromString(currency);

        List<Order> orders = liveOrderBoard.ordersFor(useCurrency.getCurrencyCode());

        Optional<Order> bid = findHighestBuy(orders, amount);
        if (!bid.isPresent()) {
            return Optional.empty();
        }
        Optional<Order> ask = findLowestSell(orders, amount);
        if (!ask.isPresent()) {
            return Optional.empty();
        }

        return Optional.of(createQuote(bid.get(), ask.get()));
    }

    private Optional<Order> findHighestBuy(List<Order> orders, int amount) {
        return orders.stream().filter(o -> o.getDirection() == Direction.BUY && o.getAmountOfBitcoins() == amount)
                .max((o1, o2) -> Double.compare(o1.getPrice(), o2.getPrice()));
    }

    private Optional<Order> findLowestSell(List<Order> orders, int amount) {
        return orders.stream().filter(o -> o.getDirection() == Direction.SELL && o.getAmountOfBitcoins() == amount)
                .min((o1, o2) -> Double.compare(o1.getPrice(), o2.getPrice()));
    }

    private Quote createQuote(Order bid, Order ask) {
        return new Quote(bidPriceStrategy.calculatePrice(bid.getPrice()), askPriceStrategy.calculatePrice(ask.getPrice()));
    }

}

class Currency {

    private static Set<String> supportedCurrencies = new HashSet<String>(Arrays.asList(
            new String[]{"USD"}
    ));

    private final String currencyCode;

    private Currency(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    private static boolean isCurrencySupported(String currency) {
        return supportedCurrencies.contains(currency);
    }

    public static Currency createFromString(String currencyCode) {
        Preconditions.checkArgument(!Strings.isNullOrEmpty(currencyCode), "Currency can not be empty!");
        Preconditions.checkArgument(isCurrencySupported(currencyCode.toUpperCase()), "Currency '%s' is not supported!");
        return new Currency(currencyCode.toUpperCase());
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    @Override
    public String toString() {
        return currencyCode;
    }
}
