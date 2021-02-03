package ru.technicalExcellence.codingDojo;

public interface StockAccount {
    Double getBalance();

    void credit(Double amount);

    void deposit(Double amount) throws NotEnoughMoneyException;
}
