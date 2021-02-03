package ru.technicalExcellence.codingDojo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class StockPortfolioShould {

    private StockPortfolio stockPortfolio;

    @BeforeEach
    void setUp() {
        var market = Map.of(
                "IBM", 10.0,
                "APPL   ", 20.0,
                "INTL", 30.0
        );
        stockPortfolio = new StockPortfolio(market);
    }

    @Test
    void beEmptyIfNothingHadBought() {
        stockPortfolio.getStockCount("IBM");
        assertTrue(stockPortfolio.isEmpty());
    }

    @Test
    void giveAbilityToPurchaseUniqueStocks() throws StockIsNotExistException, NotEnoughMoneyException {
        stockPortfolio.addCash(10.00);
        stockPortfolio.purchase("IBM", 1);
        assertFalse(stockPortfolio.isEmpty());

        assertEquals(1, stockPortfolio.getStockCount("IBM"));
        assertEquals(0, stockPortfolio.getStockCount("APPLE"));
        assertEquals(0.00, stockPortfolio.getCashBalance());
    }

    @Test
    void giveAbilityToSellUniqueStocks() throws StockOverflowException, StockIsNotExistException, NotEnoughMoneyException {
        stockPortfolio.addCash(20.00);
        stockPortfolio.purchase("IBM", 2);
        stockPortfolio.sell("IBM", 1);

        assertEquals(1, stockPortfolio.getStockCount("IBM"));
        assertEquals(10.00, stockPortfolio.getCashBalance());
    }

    @Test
    void notGiveAbilityToSellUniqueStockOverItCount() throws StockIsNotExistException, NotEnoughMoneyException {
        stockPortfolio.addCash(10.00);
        stockPortfolio.purchase("IBM", 1);
        assertThrows(StockOverflowException.class, () -> stockPortfolio.sell("IBM", 2));
        assertEquals(1, stockPortfolio.getStockCount("IBM"));
        assertEquals(0.00, stockPortfolio.getCashBalance());

    }

    @Test
    void notGiveAbilityToSellNonExistentStock() throws StockIsNotExistException, NotEnoughMoneyException {
        assertThrows(StockIsNotExistException.class, () -> stockPortfolio.sell("TESLA", 1));
        assertEquals(0, stockPortfolio.getStockCount("TESLA"));

    }

    @Test
    void notGiveAbilityToPurchaseNonExistentStock() {
        assertThrows(StockIsNotExistException.class, () -> stockPortfolio.purchase("TESLA", 1));
        assertEquals(0, stockPortfolio.getStockCount("TESLA"));
    }

    @Test
    void giveAbilityToAddMoneyToWallet() {
        stockPortfolio.addCash(50.00);
        assertEquals(50.00, stockPortfolio.getCashBalance());
    }

    @Test
    void notGiveAbilityToPurchaseIfNoMoney() throws StockIsNotExistException {
        stockPortfolio.addCash(10.00);

        assertThrows(NotEnoughMoneyException.class, () -> stockPortfolio.purchase("APPL", 1));

        assertEquals(10.00, stockPortfolio.getCashBalance());
        assertEquals(0, stockPortfolio.getStockCount("APPL"));
    }
}
