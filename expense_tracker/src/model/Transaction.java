package model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Transaction {

  private double amount;
  private String category;
  private String timestamp;
  private boolean isSelected;

  public Transaction(double amount, String category, boolean isSelected) {
    this.amount = amount;
    this.category = category;
    this.isSelected = isSelected;
    this.timestamp = generateTimestamp();
  }

  public double getAmount() {
    return amount;
  }

  public String getCategory() {
    return category;
  }
  
  public String getTimestamp() {
    return timestamp;
  }

  public boolean getSelection() {
    return isSelected;
  }

  private String generateTimestamp() {
    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm");  
    return sdf.format(new Date());
  }

}