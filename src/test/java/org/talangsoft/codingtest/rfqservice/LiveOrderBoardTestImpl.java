package org.talangsoft.codingtest.rfqservice;

import org.talangsoft.codingtest.rfqservice.model.Direction;
import org.talangsoft.codingtest.rfqservice.model.Order;
import org.talangsoft.codingtest.rfqservice.service.LiveOrderBoard;

import java.util.Arrays;
import java.util.List;

public class LiveOrderBoardTestImpl implements LiveOrderBoard {

    private List<Order> staticOrderData;

    public LiveOrderBoardTestImpl(List<Order> staticOrderData) {
        this.staticOrderData = staticOrderData;
    }

    public static LiveOrderBoard createWithDescribedExampleData() {
        return new LiveOrderBoardTestImpl(Arrays.asList(
                new Order(Direction.BUY, 232.71, "USD", 200),
                new Order(Direction.SELL, 232.74, "USD", 100),
                new Order(Direction.SELL, 232.73, "USD", 200),
                new Order(Direction.BUY, 232.71, "USD", 500),
                new Order(Direction.BUY, 232.70, "USD", 100),
                new Order(Direction.SELL, 232.75, "USD", 200),
                new Order(Direction.BUY, 232.69, "USD", 500),
                new Order(Direction.SELL, 232.76, "USD", 300),
                new Order(Direction.BUY, 232.70, "USD", 200)
        ));
    }

    public static LiveOrderBoard createWithMinMaxTest() {
        return new LiveOrderBoardTestImpl(Arrays.asList(
                new Order(Direction.BUY, 3, "USD", 100),
                new Order(Direction.BUY, 2, "USD", 100),
                new Order(Direction.BUY, 5, "USD", 100),
                new Order(Direction.BUY, 4, "USD", 100),
                new Order(Direction.BUY, 1, "USD", 100),
                new Order(Direction.SELL, 3, "USD", 100),
                new Order(Direction.SELL, 2, "USD", 100),
                new Order(Direction.SELL, 1, "USD", 100),
                new Order(Direction.SELL, 4, "USD", 100),
                new Order(Direction.SELL, 5, "USD", 100)
        ));
    }


    @Override
    public List<Order> ordersFor(String currency) {
        return staticOrderData;
    }
}
