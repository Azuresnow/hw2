package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class ExpenseTrackerModel {

  private List<Transaction> transactions;
  private final List<Transaction> filteredTransactions;

  public ExpenseTrackerModel() {
    transactions = new ArrayList<>(); 
    filteredTransactions = new ArrayList<>();
  }

  public void addTransaction(Transaction t) {
    transactions.add(t);
  }

  public List<Transaction> removeTransaction(boolean getSelection) {
    transactions = transactions.stream().filter(e -> !e.getSelection()).collect(Collectors.toList());
    return transactions;
  }

  public List<Transaction> getTransactions() {
    return Collections.unmodifiableList(filteredTransactions.isEmpty() ? transactions : filteredTransactions);
  }

  public List<Transaction> getFiltedTransactions(double amount, String category) {
    return Collections.unmodifiableList(transactions.stream()
            .filter(e -> amount == 0 || e.getAmount() == amount) 
            .filter(e -> category.isEmpty() || e.getCategory().toLowerCase().contains(category.toLowerCase())) 
            .collect(Collectors.toList()));
 }


  public List<Transaction> getAllTransactions() {
    return Collections.unmodifiableList(transactions);
  }

 

}