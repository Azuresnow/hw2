package model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Transaction {

  public double amount;
  public String category;
  public String timestamp;
  public boolean isSelected;

  public Transaction(double amount, String category, boolean isSelected) {
    this.amount = amount;
    this.category = category;
    this.isSelected = isSelected;
    this.timestamp = generateTimestamp();
  }

  public double getAmount() {
    return amount;
  }

  public void setAmount(double amount) {
    this.amount = amount;
  }

  public String getCategory() {
    return category;
  }

  public void setCategory(String category) {
    this.category = category; 
  }
  
  public String getTimestamp() {
    return timestamp;
  }

  public boolean getSelection() {
    return isSelected;
  }

  public void setSelection(boolean isSelected) {
    this.isSelected = isSelected;
  }


  private String generateTimestamp() {
    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm");  
    return sdf.format(new Date());
  }

}