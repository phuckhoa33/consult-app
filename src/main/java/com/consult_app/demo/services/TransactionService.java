package com.consult_app.demo.services;

import java.util.List;

public interface TransactionService {
    String createTransactionBySolana(Object inforTrans);

    String createTransactionByPaypal(Object inforTrans);

    List<Object> getTransactions();
}
