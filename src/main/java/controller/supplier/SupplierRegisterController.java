package controller.supplier;

import controller.basis.Controller;
import jakarta.validation.ValidationException;
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
        try {
            Supplier supplier = new Supplier();
            if (currentSupplier != null) {
                supplier.setId(currentSupplier.getId());
            }

            supplier.setName(supplierName.getText());
            supplier.setContact(supplierPhone.getText());
            supplier.setEmail(supplierEmail.getText());

            supplierService.saveOrUpdate(supplier);

            this.closeWindow(event);

        } catch (ValidationException e) {
            errorLabel.setText(e.getMessage());
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
