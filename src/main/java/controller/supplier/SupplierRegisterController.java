package controller.supplier;

import controller.basis.Controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Supplier;
import service.SupplierService;

import java.util.ArrayList;

public class SupplierRegisterController extends Controller {
    @FXML
    private TextField supplierName;
    @FXML
    private TextField supplierPhone;
    @FXML
    private TextField supplierEmail;
    @FXML
    private Label errorLabel;

    private Supplier currentSupplier;

    private SupplierService supplierService = new SupplierService();

    @FXML
    public void onCancel(ActionEvent event) {
        closeWindow(event);
    }

    @FXML
    public void onSubmit(ActionEvent event){
        ArrayList<String> errors =
            supplierService.saveOrUpdate(
                currentSupplier,
                supplierName.getText(),
                supplierPhone.getText(),
                supplierEmail.getText()
            );

        if(errors.isEmpty()) {
            this.closeWindow(event);
        } else {
            errorLabel.setText(String.join("\n", errors));
            errorLabel.setVisible(true);
        }
    }

    public void setSupplier(Supplier supplier) {
        this.currentSupplier = supplier;

        if (supplier != null) {
            this.supplierName.setText(supplier.getName());
            this.supplierPhone.setText(supplier.getContact());
            this.supplierEmail.setText(supplier.getEmail());
        }
    }

    @FXML
    public void initialize(SupplierService supplierService) {}
}
