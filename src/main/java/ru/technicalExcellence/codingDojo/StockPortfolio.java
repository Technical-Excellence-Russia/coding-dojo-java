package ru.technicalExcellence.codingDojo;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

class StockPortfolio {

    private final Map<String, AtomicInteger> stocks = new HashMap<>();
    private final Map<String, Double> market;
    private double balance = 0.0;

    public StockPortfolio(Map<String, Double> market) {
        this.market = market;
    }

    public boolean isEmpty() {
        return stocks.isEmpty() || stocks.values().stream().map(AtomicInteger::get).reduce(0, Integer::sum) == 0;
    }

    public int getStockCount(String stockName) {
        return getStockCountOrCreate(stockName).get();
    }

    public void purchase(String stockName, int count) throws StockIsNotExistException, NotEnoughMoneyException {
        checkIfStockExistAtMarketOrThrowException(stockName);

        final var currentCount = getStockCountOrCreate(stockName);

        withdrawCash(market.get(stockName) * count);
        currentCount.addAndGet(count);
    }

    public void sell(String stockName, int count) throws StockOverflowException, StockIsNotExistException {
        checkIfStockExistAtMarketOrThrowException(stockName);

        final var currentCount = getStockCountOrCreate(stockName);
        if (currentCount.get() < count) {
            throw new StockOverflowException();
        }

        addCash(market.get(stockName) * count);
        currentCount.addAndGet(-count);
    }


    public double getCashBalance() {
        return balance;
    }

    public void addCash(double money) {
        balance += money;
    }

    public void withdrawCash(double money) throws NotEnoughMoneyException {
        if (money > balance) {
            throw new NotEnoughMoneyException();
        }
        balance -= money;
    }

    protected AtomicInteger getStockCountOrCreate(String stockName) {
        stocks.putIfAbsent(stockName, new AtomicInteger());
        return stocks.get(stockName);
    }

    protected void checkIfStockExistAtMarketOrThrowException(String stockName) throws StockIsNotExistException {
        if (!market.containsKey(stockName)) {
            throw new StockIsNotExistException();
        }
    }
}
