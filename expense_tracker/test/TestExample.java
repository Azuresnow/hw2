// package test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import controller.ExpenseTrackerController;
import controller.InputValidation;
import model.ExpenseTrackerModel;
import model.Transaction;
import view.ExpenseTrackerView;


public class TestExample {
  
  private ExpenseTrackerModel model;
  private ExpenseTrackerView view;
  private ExpenseTrackerController controller;

  @Before
  public void setup() {
    model = new ExpenseTrackerModel();
    view = new ExpenseTrackerView();
    controller = new ExpenseTrackerController(model, view);
  }

    public double getTotalCost() {
        double totalCost = 0.0;
        List<Transaction> allTransactions = model.getTransactions(); // Using the model's getTransactions method
        for (Transaction transaction : allTransactions) {
            totalCost += transaction.getAmount();
        }
        return totalCost;
    }
    


    @Test
    public void testAddTransaction() {
        // Pre-condition: List of transactions is empty
        assertEquals(0, model.getTransactions().size());
    
        // Perform the action: Add a transaction
        assertTrue(controller.addTransaction(50.00, "food", false));
    
        // Post-condition: List of transactions contains one transaction
        assertEquals(1, model.getTransactions().size());
    
        // Check the contents of the list
        assertEquals(50.00, getTotalCost(), 0.01);
    }

    @Test
    public void testAddTransactionErrorCategory() {
        // Pre-condition: List of transactions is empty
        assertEquals(0, model.getTransactions().size());
    
        // Perform the action: Add a transaction
        assertFalse(controller.addTransaction(50.00, " not food", false));
    
        // Post-condition: List of transactions contains one transaction
        assertEquals(0, model.getTransactions().size());
    }

    @Test
    public void testAddTransactionNegative() {
        // Pre-condition: List of transactions is empty
        assertEquals(0, model.getTransactions().size());
    
        // Perform the action: Add a transaction
        assertFalse(controller.addTransaction(-5, "food", false));
    
        // Post-condition: List of transactions contains one transaction
        assertEquals(0, model.getTransactions().size());
    }

    @Test
    public void testFilterTransactionsAmount() {
        // Pre-condition: List of transactions is empty
        assertEquals(0, model.getTransactions().size());
    
        // Perform the action: Add a transaction
        assertTrue(controller.addTransaction(3.00, "Bills", false));
        assertTrue(controller.addTransaction(5.00, "Bills", false));
        assertTrue(controller.addTransaction(3.00, "Other", false));
        assertTrue(controller.filter(3.0,"Bills"));
    
        // Post-condition: List of transactions contains one transaction
        assertEquals(2, model.getFiltedTransactions(3.00, "").size());
    
        // Check the contents of the list
    }

    @Test
    public void testFilterTransactionsCategory() {
        // Pre-condition: List of transactions is empty
        assertEquals(0, model.getTransactions().size());
    
        // Perform the action: Add a transaction
        assertTrue(controller.addTransaction(3.00, "Bills", false));
        assertTrue(controller.addTransaction(5.00, "Bills", false));
        assertTrue(controller.addTransaction(3.00, "Other", false));
        assertTrue(controller.filter(3.0,"other"));
    
        // Post-condition: List of transactions contains one transaction
        assertEquals(2, model.getFiltedTransactions(0.0, "Bills").size());
    
        // Check the contents of the list
    }




    
}