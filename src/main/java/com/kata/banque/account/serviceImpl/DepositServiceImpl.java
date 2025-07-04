package com.kata.banque.account.serviceImpl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kata.banque.account.model.BankAccount;
import com.kata.banque.account.model.Transaction;
import com.kata.banque.serviceInterface.DepositService;

@Service
public class DepositServiceImpl implements DepositService {


    public void deposit(double amount,BankAccount account,List<Transaction> transactions) {
        account.deposit(amount);
        transactions.add(new Transaction(amount, account.getBalance()));
    }
}
