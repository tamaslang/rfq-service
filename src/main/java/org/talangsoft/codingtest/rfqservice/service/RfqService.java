package org.talangsoft.codingtest.rfqservice.service;

import org.talangsoft.codingtest.rfqservice.model.Quote;

import java.util.Optional;

public interface RfqService {
    Optional<Quote> quoteFor(String currency, int amount);
}
