package model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ExpenseTrackerModel {

  public List<Transaction> transactions;
  public List<Transaction> filteredTransactions;

  public ExpenseTrackerModel() {
    transactions = new ArrayList<>(); 
    filteredTransactions = new ArrayList<>();
  }

  public void addTransaction(Transaction t) {
    transactions.add(t);
  }

  public void removeTransaction(Transaction t) {
    transactions.remove(t);
  }

  public List<Transaction> getTransactions() {
    return filteredTransactions.isEmpty() ? transactions : filteredTransactions;
  }

  public List<Transaction> getFiltedTransactions(double amount, String category) {
    return transactions.stream()
            .filter(e -> amount == 0 || e.getAmount() == amount) 
            .filter(e -> category.isEmpty() || e.getCategory().toLowerCase().contains(category.toLowerCase())) 
            .collect(Collectors.toList());
 }

  public List<Transaction> getAllTransactions() {
  return transactions;
  }

}