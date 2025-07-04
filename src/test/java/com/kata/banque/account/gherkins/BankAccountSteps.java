package com.kata.banque.account.gherkins;

import io.cucumber.java.fr.*;

import java.util.ArrayList;
import java.util.List;

import com.kata.banque.account.model.BankAccount;
import com.kata.banque.account.model.Transaction;
import com.kata.banque.account.serviceImpl.DepositServiceImpl;
import com.kata.banque.account.serviceImpl.PrintStatementServiceImpl;
import com.kata.banque.account.serviceImpl.WithDrawServiceImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BankAccountSteps {

    private BankAccount account;
    private List<Transaction> transactions;
    private String statementResult;


    private final DepositServiceImpl depositService = new DepositServiceImpl();
    private final WithDrawServiceImpl withDrawService = new WithDrawServiceImpl();
    private final PrintStatementServiceImpl printService = new PrintStatementServiceImpl();

    @Étantdonné("un nouveau compte bancaire")
    public void un_nouveau_compte_bancaire() {
        account = new BankAccount();
        transactions = new ArrayList<>();
    }

    @Étantdonné("un nouveau compte bancaire avec {int} euros")
    public void un_nouveau_compte_bancaire_avec(int montant) {
        account = new BankAccount();
        transactions = new ArrayList<>();
        depositService.deposit(montant, account, transactions);
    }

    @Quand("je dépose {int} euros")
    public void je_depose(int montant) {
        depositService.deposit(montant, account, transactions);
    }

    @Quand("je retire {int} euros")
    public void je_retire(int montant) {
        withDrawService.withdraw(montant, account, transactions);
    }

    @Et("le relevé contient {string}")
    public void le_releve_contient(String attendu) {
        assert statementResult.contains(attendu);
    }

    @Alors("le solde doit être {int}")
    public void le_solde_doit_etre(int soldeAttendu) {
        assertEquals(soldeAttendu, account.getBalance());
    }

    @Alors("je demande le relevé")
    public void je_demande_le_releve() {
        statementResult = printService.printStatement(transactions);
    }



}
