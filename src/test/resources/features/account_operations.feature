Feature: Compte bancaire

  Scenario: Dépôt d'argent
    Given un nouveau compte bancaire
    When je dépose 1000 euros
    Then le solde doit être 1000

  Scenario: Retrait d'argent
    Given un nouveau compte bancaire avec 500 euros
    When je retire 200 euros
    Then le solde doit être 300

  Scenario: Affichage du relevé
    Given un nouveau compte bancaire
    When je dépose 100 euros
    And je retire 50 euros
    Then je demande le relevé
    And le relevé contient "Montant: -50.0"   
