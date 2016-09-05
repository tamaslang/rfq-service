package org.talangsoft.codingtest.rfqservice;

import org.junit.Test;
import org.talangsoft.codingtest.rfqservice.model.Quote;
import org.talangsoft.codingtest.rfqservice.service.RfqService;
import org.talangsoft.codingtest.rfqservice.service.RfqServiceImpl;

import java.util.Optional;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class RfqServiceImplTest {

    private final RfqService underTest = new RfqServiceImpl(LiveOrderBoardTestImpl.createWithDescribedExampleData());

    @Test
    public void rfqForMatchingSellAndBuyShouldBeSuccessfulForAmount200() {
        assertThat(underTest.quoteFor("USD", 200), is(Optional.of(new Quote(232.69, 232.75))));
    }

    @Test
    public void rfqForMatchingSellAndBuyShouldBeSuccessfulForAmount100() {
        assertThat(underTest.quoteFor("USD", 100), is(Optional.of(new Quote(232.68, 232.76))));
    }

    @Test
    public void rfqForMatchingSellAndBuyShouldBeEmptyForAmount500() {
        assertThat(underTest.quoteFor("USD", 500), is(Optional.empty()));
    }

    @Test
    public void rfqForMatchingSellAndBuyShouldPickHighestBuyAndLowestSellPrice() {
        RfqService  rfqServiceForHighestAndLowest = new RfqServiceImpl(LiveOrderBoardTestImpl.createWithMinMaxTest());
        assertThat(rfqServiceForHighestAndLowest.quoteFor("USD", 100),is(Optional.of(new Quote(4.98, 1.02))));
    }


    @Test(expected = IllegalArgumentException.class)
    public void rfqServiceShouldFailOnEmptyCurrency() {
        underTest.quoteFor("", 100);
    }

    @Test(expected = IllegalArgumentException.class)
    public void rfqServiceShouldFailOnNotSupportedCurrency() {
        underTest.quoteFor("NOT_SUPPORTED", 100);
    }

    @Test(expected = IllegalArgumentException.class)
    public void rfqServiceShouldFailOnZeroAmount() {
        underTest.quoteFor("usd", 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void rfqServiceShouldFailOnNegativeAmount() {
        underTest.quoteFor("usd", -1);
    }

    @Test
    public void rfqServiceShouldRespondOnSupportedCurrency() {
        assertThat(underTest.quoteFor("usd", 100), is(Optional.of(new Quote(232.68, 232.76))));
    }

    @Test
    public void rfqServiceShouldRespondOnSupportedCurrencyWithMixedCase() {
        assertThat(underTest.quoteFor("UsD", 100), is(Optional.of(new Quote(232.68, 232.76))));
    }

    @Test
    public void rfqServiceShouldRespondOnSupportedCurrencyWithUpperCase() {
        assertThat(underTest.quoteFor("USD", 100), is(Optional.of(new Quote(232.68, 232.76))));
    }


}

