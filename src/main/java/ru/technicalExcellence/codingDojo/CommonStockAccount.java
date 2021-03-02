package ru.technicalExcellence.codingDojo;

import java.math.BigDecimal;
import java.util.Optional;

public class CommonStockAccount implements StockAccount {

    private BigDecimal balance = BigDecimal.ZERO;

    @Override
    public Double getBalance() {
        return balance.doubleValue();
    }

    @Override
    public void credit(Double amount) {
        balance = balance.add(replaceByZeroIfNull(amount));
    }

    @Override
    public void deposit(Double amount) throws NotEnoughMoneyException {
        final var subtractAmount = replaceByZeroIfNull(amount);
        if (subtractAmount.compareTo(balance) > 0) {
            throw new NotEnoughMoneyException();
        }
        balance = balance.subtract(subtractAmount);
    }

    private BigDecimal replaceByZeroIfNull(Double amount) {
        return BigDecimal.valueOf(Optional.ofNullable(amount).orElse(0.0));
    }
}
