package ru.technicalExcellence.codingDojo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class StockAccountShould {

    private StockAccount stockAccount;

    @BeforeEach
    void setUp() {
        stockAccount = new CommonStockAccount();
    }

    @Test
    void haveZeroBalanceAtStart() {
        assertEquals(0.0, stockAccount.getBalance());
    }

    @Test
    void increaseBalanceWhenCredit() {
        final var arrange = 1.0;
        stockAccount.credit(arrange);
        assertEquals(arrange, stockAccount.getBalance());
    }

    @Test
    void notIncreaseBalanceWhenCreditWithNull() {
        stockAccount.credit(null);
        assertEquals(0.0, stockAccount.getBalance());
    }

    @Test
    void decreaseBalanceWhenDeposit() throws NotEnoughMoneyException {
        final Double arrange = 0.0;
        stockAccount.credit(arrange);
        stockAccount.deposit(arrange);
        assertEquals(0.0, stockAccount.getBalance());
    }

    @Test
    void notDecreaseBalanceWhenDebitWithNull() throws NotEnoughMoneyException {
        stockAccount.deposit(null);
        assertEquals(0.0, stockAccount.getBalance());
    }

    @Test
    void throwNotEnoughMoneyIfDepositAmountMoreThenBalance() throws NotEnoughMoneyException {
        final Double arrange = 1.0;
        stockAccount.credit(arrange);
        stockAccount.deposit(arrange);
        assertThrows(NotEnoughMoneyException.class, () -> stockAccount.deposit(arrange));
        assertEquals(0.0, stockAccount.getBalance());
    }

}
