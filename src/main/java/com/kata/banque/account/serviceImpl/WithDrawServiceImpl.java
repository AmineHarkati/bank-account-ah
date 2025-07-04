package com.kata.banque.account.serviceImpl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kata.banque.account.model.BankAccount;
import com.kata.banque.account.model.Transaction;
import com.kata.banque.serviceInterface.WithDrawService;

@Service
public class WithDrawServiceImpl implements WithDrawService{

    @Override
    public void withdraw(double amount,BankAccount account,List<Transaction> transactions) {
        account.withdraw(amount);
        transactions.add(new Transaction(-amount, account.getBalance()));
    }
}
