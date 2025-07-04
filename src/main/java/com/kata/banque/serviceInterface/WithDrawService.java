package com.kata.banque.serviceInterface;

import java.util.List;

import com.kata.banque.account.model.BankAccount;
import com.kata.banque.account.model.Transaction;

public interface WithDrawService {

    public void withdraw(double amount,BankAccount account,List<Transaction> transactions);

}
