package org.talangsoft.codingtest.rfqservice.service;

import org.talangsoft.codingtest.rfqservice.model.Order;

import java.util.List;

public interface LiveOrderBoard {
    List<Order> ordersFor(String currency);
}
