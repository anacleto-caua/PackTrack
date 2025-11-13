package controller;

import controller.basis.Controller;
import interfaces.SupplierDTO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import service.SupplierService;

public class SupplierRegisterController extends Controller {
    @FXML
    private TextField supplierName;
    @FXML
    private TextField supplierAddress;
    @FXML
    private TextField supplierPhone;
    @FXML
    private TextField supplierEmail;

    @FXML
    public void onCancel(ActionEvent event) {
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void onSubmit(ActionEvent event){
        SupplierService supplierService = new SupplierService();
        
        SupplierDTO supplierDTO = new SupplierDTO(
                supplierName.getText(),
                supplierEmail.getText(),
                supplierPhone.getText()
        );

        supplierService.save(supplierDTO);

        this.closeWindow(event);
    }

    @FXML
    public void initialize(SupplierService supplierService) {}

    public TextField getSupplierName() {
        return supplierName;
    }

    public TextField getSupplierAddress() {
        return supplierAddress;
    }

    public TextField getSupplierPhone() {
        return supplierPhone;
    }

    public TextField getSupplierEmail() {
        return supplierEmail;
    }

    public void setSupplierName(TextField supplierName) {
        this.supplierName = supplierName;
    }

    public void setSupplierAddress(TextField supplierAddress) {
        this.supplierAddress = supplierAddress;
    }

    public void setSupplierPhone(TextField supplierPhone) {
        this.supplierPhone = supplierPhone;
    }

    public void setSupplierEmail(TextField supplierEmail) {
        this.supplierEmail = supplierEmail;
    }
}
