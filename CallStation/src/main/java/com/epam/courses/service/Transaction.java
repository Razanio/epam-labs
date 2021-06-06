package com.epam.courses.service;

import com.epam.courses.service.exception.TransactionException;

public interface Transaction {
    void start() throws TransactionException;

    void commit() throws TransactionException;

    void rollback() throws TransactionException;
}
