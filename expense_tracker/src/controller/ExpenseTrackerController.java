package controller;

import java.util.List;
import model.ExpenseTrackerModel;
import model.Transaction;
import view.ExpenseTrackerView;
public class ExpenseTrackerController {
  
  private ExpenseTrackerModel model;
  private ExpenseTrackerView view;

  public ExpenseTrackerController(ExpenseTrackerModel model, ExpenseTrackerView view) {
    this.model = model;
    this.view = view;

    // Set up view event handlers
  }

  public void refresh() {

    // Get transactions from model
    List<Transaction> transactions = model.getTransactions();

    // Pass to view
    view.refreshTable(transactions);

  }

  public void reset() {

    // Get transactions from model
    List<Transaction> transactions = model.getAllTransactions();

    // Pass to view
    view.refreshTable(transactions);

  }

  public boolean filter() {


    double amount = view.getAmountField();
    String category = view.getCategoryField();

    if (!InputValidation.isValidAmount(amount)) {
      return false;
    }
    if (!InputValidation.isValidCategory(category)) {
      return false;
    }

    List<Transaction> filteredTransactions = model.getFiltedTransactions(amount, category);

    view.refreshTable(filteredTransactions);

    return true;
}

public void remove(){
  boolean getSelection = view.getSelection();
  List<Transaction> removeTransactions = model.removeTransaction(getSelection);
  view.refreshTable(removeTransactions);

}


public boolean addTransaction(double amount, String category, boolean isSelected) {
    if (!InputValidation.isValidAmount(amount)) {
      return false;
    }
    if (!InputValidation.isValidCategory(category)) {
      return false;
    }
    
    Transaction t = new Transaction(amount, category, isSelected);
    model.addTransaction(t);
    view.getTableModel().addRow(new Object[]{t.getAmount(), t.getCategory(), t.getTimestamp(), t.getSelection()});
    refresh();
    return true;

  }
  
  // Other controller methods
}