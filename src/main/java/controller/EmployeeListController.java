package controller;

import controller.basis.Controller;
import interfaces.EmployeeDTO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import manager.ViewManager;
import utils.TableInitializer;

public class EmployeeListController extends Controller  {

    @FXML
    private TableView<EmployeeDTO> employeeTable;

    @FXML
    public void initialize() {
        TableInitializer.initializeTable(
                employeeTable,
                EmployeeDTO.class,
                this::handleDeleteEmployee,
                this::handleUpdateEmployee
        );

        employeeTable.setItems(loadMockData());

        ViewManager.loadStyle("style.css");
    }

    private ObservableList<EmployeeDTO> loadMockData() {
        return FXCollections.observableArrayList(
                new EmployeeDTO("João Silva", "joao.silva@email.com", "senha123", "Vendedor", "2500.00", "5.0"),
                new EmployeeDTO("Maria Oliveira", "maria.o@email.com", "senha456", "Gerente", "5000.00", "10.0"),
                new EmployeeDTO("Pedro Costa", "pedro.costa@email.com", "senha789", "Vendedor", "2600.00", "5.5"),
                new EmployeeDTO("Sofia Pereira", "sofia.p@email.com", "senha101", "Caixa", "1800.00", "2.0")
        );
    }

    private void handleDeleteEmployee(EmployeeDTO employee) {
        System.out.println("Apagar funcionário: " + employee.name());
        ViewManager.showModal("EmployeeRegister.fxml", "Apagar " + employee.name());
    }

    private void handleUpdateEmployee(EmployeeDTO employee) {
        System.out.println("Atualizar funcionário: " + employee.name());
        ViewManager.showModal("EmployeeRegister.fxml", "Atualizar " + employee.name());
    }
}