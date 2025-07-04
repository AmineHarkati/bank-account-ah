package com.kata.banque.account.controller;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.kata.banque.account.model.BankAccount;
import com.kata.banque.account.model.Transaction;
import com.kata.banque.serviceInterface.DepositService;
import com.kata.banque.serviceInterface.PrintStatementService;
import com.kata.banque.serviceInterface.WithDrawService;

@RestController
@RequestMapping("/account")
public class BankAccountController {
    
    @Autowired
    private DepositService depositService;
    @Autowired
    private PrintStatementService printStatementService;
    @Autowired
    private WithDrawService withDrawService;
    
    private final BankAccount account  = new BankAccount();;
    private final List<Transaction> transactions = new ArrayList<>();

    /*public BankAccountController() {
        this.account = new BankAccount();
        this.transactions = new ArrayList<>();
    }*/


    //private final BankAccountService service;

    /*public BankAccountController(BankAccountService service) {
        this.service = service;
    }*/

    @PostMapping("/deposit/{amount}")
    public ResponseEntity<String> deposit(@PathVariable double amount) {
        try{
            depositService.deposit(amount,account,transactions);
        return ResponseEntity.ok("Dépôt effectué : " + amount);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Erreur : montant négatif ou 0 non autorisé");
        }
    }

    @PostMapping("/withdraw/{amount}")
    public ResponseEntity<String> withdraw(@PathVariable double amount) {
        if(amount<=0){
            return ResponseEntity.badRequest().body("montant doit etre sup à 0");
        }
        try {
            withDrawService.withdraw(amount,account,transactions);
            return ResponseEntity.ok("Retrait effectué : " + amount);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Erreur : Solde insuffisant.");
        }
    }

    @GetMapping("/statement")
    public ResponseEntity<String> getStatement() {
        return ResponseEntity.ok(printStatementService.printStatement(transactions));
    }
}
