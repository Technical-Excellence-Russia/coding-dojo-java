package ru.technicalExcellence.codingDojo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class StockPortfolioShould {

    private StockPortfolio stockPortfolio;

    private StockAccount stockAccount;

    @BeforeEach
    void setUp() {
        var market = Map.of(
                "IBM", 10.0,
                "APPL", 20.0,
                "INTL", 30.0
        );
        stockAccount = mock(StockAccount.class);
        stockPortfolio = new StockPortfolio(market, stockAccount);
    }

    @Test
    void beEmptyIfNothingHadBought() {
        stockPortfolio.getStockCount("IBM");
        assertTrue(stockPortfolio.isEmpty());
    }

    @Test
    void giveAbilityToPurchaseUniqueStocks() throws StockIsNotExistException, NotEnoughMoneyException {
        stockPortfolio.purchase("IBM", 1);
        assertFalse(stockPortfolio.isEmpty());

        assertEquals(1, stockPortfolio.getStockCount("IBM"));
        assertEquals(0, stockPortfolio.getStockCount("APPLE"));
        verify(stockAccount).deposit(10.0);
    }

    @Test
    void giveAbilityToSellUniqueStocks() throws StockOverflowException, StockIsNotExistException, NotEnoughMoneyException {
        stockPortfolio.purchase("IBM", 2);
        stockPortfolio.sell("IBM", 1);

        assertEquals(1, stockPortfolio.getStockCount("IBM"));
        verify(stockAccount).deposit(20.0);
        verify(stockAccount).credit(10.0);
    }


    @Test
    void notGiveAbilityToSellUniqueStockOverItCount() throws StockIsNotExistException, NotEnoughMoneyException {
        stockPortfolio.purchase("IBM", 1);
        assertThrows(StockOverflowException.class, () -> stockPortfolio.sell("IBM", 2));
        assertEquals(1, stockPortfolio.getStockCount("IBM"));
        verify(stockAccount).deposit(10.0);

    }


    @Test
    void notGiveAbilityToSellNonExistentStock() {
        assertThrows(StockIsNotExistException.class, () -> stockPortfolio.sell("TESLA", 1));
        assertEquals(0, stockPortfolio.getStockCount("TESLA"));

  }

    @Test
    void notGiveAbilityToPurchaseNonExistentStock() {
        assertThrows(StockIsNotExistException.class, () -> stockPortfolio.purchase("TESLA", 1));
        assertEquals(0, stockPortfolio.getStockCount("TESLA"));
    }

    @Test
    void notGiveAbilityToPurchaseIfNoMoney() throws NotEnoughMoneyException {
        doThrow(new NotEnoughMoneyException()).when(stockAccount).deposit(20.0);

        assertThrows(NotEnoughMoneyException.class, () -> stockPortfolio.purchase("APPL", 1));

        assertEquals(0, stockPortfolio.getStockCount("APPL"));
    }

}
