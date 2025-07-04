package com.kata.banque.account.unitTests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.kata.banque.account.model.BankAccount;
import com.kata.banque.account.model.Transaction;
import com.kata.banque.serviceInterface.DepositService;
import com.kata.banque.serviceInterface.PrintStatementService;
import com.kata.banque.serviceInterface.WithDrawService;

@SpringBootTest
class KataApplicationTests {

    @Autowired
    private DepositService depositService;
    @Autowired
    private PrintStatementService printStatementService;
    @Autowired
    private WithDrawService withDrawService;

    private final BankAccount account  = new BankAccount();;
    private final List<Transaction> transactions = new ArrayList<>();

	@Test
    void depositAndWithdrawOperations() {
        //BankAccountService service = new BankAccountService();
        depositService.deposit(1000,account,transactions);
        withDrawService.withdraw(200,account,transactions);

        String statement = printStatementService.printStatement(transactions);
        assertTrue(statement.contains("1000.0"));
        assertTrue(statement.contains("-200.0"));
    }

    @Test
    void depositNegativeAmountShouldFail() {
		IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> depositService.deposit(-100,account,transactions));
        assertEquals("Le montant du dépôt doit être positif et sup à 0"
		,exception.getMessage());
    }

    @Test
    void withdrawMoreThanBalanceShouldFail() {
        depositService.deposit(500,account,transactions);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> withDrawService.withdraw(600,account,transactions));
        assertEquals("Fonds insuffisants.", exception.getMessage());
    }

    @Test
    void withdrawExactBalanceShouldWork() {
        depositService.deposit(500,account,transactions);
        withDrawService.withdraw(500,account,transactions);
    }

}
