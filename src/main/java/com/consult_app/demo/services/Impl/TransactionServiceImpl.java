package com.consult_app.demo.services.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.consult_app.demo.services.SolanaService;
import com.consult_app.demo.services.TransactionService;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    SolanaService solanaService;

    @Override
    public String createTransactionBySolana(Object inforTrans) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createTransactionBySolana'");
    }

    @Override
    public String createTransactionByPaypal(Object inforTrans) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createTransactionByPaypal'");
    }

    @Override
    public List<Object> getTransactions() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getTransactions'");
    }

}
