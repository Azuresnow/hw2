package view;

import java.awt.*;
import java.text.NumberFormat;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import model.Transaction;

public class ExpenseTrackerView extends JFrame {

  private JTable transactionsTable;
  private JButton addTransactionBtn;
  private JButton addFilterBtn;
  private JButton addRestBtn;
  private JButton addRemoveBtn;
  private JFormattedTextField amountField;
  private JTextField categoryField;
  private DefaultTableModel model;
  private JCheckBox selectField;
 

  public ExpenseTrackerView() {
    setTitle("Expense Tracker"); // Set title
    setSize(600, 400); // Make GUI larger

    String[] columnNames = {"serial", "Amount", "Category", "Date", ""};
    this.model = new DefaultTableModel(columnNames, 0) {
      public Class<?> getColumnClass(int column) {
        if (column == 4 && model.getRowCount() >= 2 && model.getDataVector().elementAt(0).elementAt(3) != null) {
        return Boolean.class;
      }
      return super.getColumnClass(column);
      }
  };
    
 

    addTransactionBtn = new JButton("Add Transaction");
    addFilterBtn = new JButton("Filter");
    addRestBtn = new JButton("Reset filter");
    addRemoveBtn = new JButton("Remove selected");

    // Create UI components
    JLabel amountLabel = new JLabel("Amount:");
    NumberFormat format = NumberFormat.getNumberInstance();

    amountField = new JFormattedTextField(format);
    amountField.setColumns(10);

    
    JLabel categoryLabel = new JLabel("Category:");
    categoryField = new JTextField(10);

    // Create table
    transactionsTable = new JTable(model);
  
    // Layout components
    JPanel inputPanel = new JPanel();
    inputPanel.add(amountLabel);
    inputPanel.add(amountField);
    inputPanel.add(categoryLabel); 
    inputPanel.add(categoryField);
    inputPanel.add(addTransactionBtn);
    inputPanel.add(addFilterBtn);
    inputPanel.add(addRestBtn);
    inputPanel.add(addRemoveBtn);

  
    JPanel buttonPanel = new JPanel();
    buttonPanel.add(addTransactionBtn);
    buttonPanel.add(addFilterBtn);
    buttonPanel.add(addRestBtn);
    buttonPanel.add(addRemoveBtn);
  
    // Add panels to frame
    add(inputPanel, BorderLayout.NORTH);
    add(new JScrollPane(transactionsTable), BorderLayout.CENTER); 
    add(buttonPanel, BorderLayout.SOUTH);
  
    // Set frame properties
    setSize(600, 400);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setVisible(true);
  
  }

  public void refreshTable(List<Transaction> transactions) {
      // Clear existing rows
      model.setRowCount(0);
      // Get row count
      int rowNum = model.getRowCount();
      double totalCost=0;
      // Calculate total cost
      for(Transaction t : transactions) {
        totalCost+=t.getAmount();
      }
      // Add rows from transactions list
      for(Transaction t : transactions) {
        model.addRow(new Object[]{rowNum+=1,t.getAmount(), t.getCategory(), t.getTimestamp(), t.getSelection()}); 
      }
        // Add total row
        Object[] totalRow = {"Total", null, null, totalCost, null};
        model.addRow(totalRow);
      
      // Fire table update
      transactionsTable.updateUI();
  
    }  
  

  
  
  public JButton getAddTransactionBtn() {
    return addTransactionBtn;
  }
  public JButton getFilterBtn() {
    return addFilterBtn;
  }
  public JButton getResetBtn() {
    return addRestBtn;
  }
  public JButton getRemoveBtn() {
    return addRemoveBtn;
  }
  public DefaultTableModel getTableModel() {
    return model;
  }
  // Other view methods
    public JTable getTransactionsTable() {
    return transactionsTable;
  }

  public double getAmountField() {
    if(amountField.getText().isEmpty()) {
      return 0;
    }else {
    double amount = Double.parseDouble(amountField.getText());
    return amount;
    }
  }

  public void setAmountField(JFormattedTextField amountField) {
    this.amountField = amountField;
  }

  
  public String getCategoryField() {
    return categoryField.getText();
  }

  public boolean getSelection() {
    if (selectField != null) {
        return selectField.isSelected();
    }
    return false; 
}

  public void setSelection(JCheckBox selectField) {
    this.selectField = selectField;
}

  public void setCategoryField(JTextField categoryField) {
    this.categoryField = categoryField;
  }
}
