package com.epam.courses.service.logic;

import com.epam.courses.service.Transaction;

abstract public class BaseService {
    private Transaction transaction;

    public Transaction getTransaction() {
        return transaction;
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }
}
