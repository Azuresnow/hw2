import controller.ExpenseTrackerController;
import javax.swing.JOptionPane;
import model.ExpenseTrackerModel;
import view.ExpenseTrackerView;

public class ExpenseTrackerApp{

  public static void main(String[] args) {
    
    // Create MVC components
    ExpenseTrackerModel model = new ExpenseTrackerModel();
    ExpenseTrackerView view = new ExpenseTrackerView();
    ExpenseTrackerController controller = new ExpenseTrackerController(model, view);

    // Initialize view
    view.setVisible(true);

    view.getFilterBtn().addActionListener(e -> {

      controller.filter();
      
    });

    
    view.getResetBtn().addActionListener(e -> {

      controller.reset();
      
    });

    view.getRemoveBtn().addActionListener(e -> {

      controller.remove();

    });

    // Handle add transaction button clicks
    view.getAddTransactionBtn().addActionListener(e -> {
      // Get transaction data from view
      double amount = view.getAmountField();
      String category = view.getCategoryField();
      boolean isSelected  = view.getSelection();

      
      // Call controller to add transaction
      boolean added = controller.addTransaction(amount, category, isSelected);
      
      if (!added) {
        JOptionPane.showMessageDialog(view, "Invalid amount or category entered");
        view.toFront();
      }
    });

  }

}