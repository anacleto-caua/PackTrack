package controller.employee;

import controller.basis.Controller;
import controller.client.ClientRegisterController;
import dao.EmployeeDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;
import manager.ViewManager;
import model.Employee;
import service.EmployeeService;
import util.table.TableFactory;

import java.text.SimpleDateFormat;
import java.util.List;

public class EmployeeListController extends Controller  {

    @FXML
    private TableView<Employee> employeeTable;

    @FXML
    private VBox rootPane;

    private EmployeeService employeeService = new EmployeeService();

    @FXML
    public void initialize() {

        var columns = List.of(
                TableFactory.Column.<Employee>of("ID", e -> String.valueOf(e.getId())),
                TableFactory.Column.<Employee>of("Username", Employee::getUsername),
                TableFactory.Column.<Employee>of("Email", Employee::getEmail),
                TableFactory.Column.<Employee>of("Phone", Employee::getPhone),
                TableFactory.Column.<Employee>of("CPF", Employee::getCpf),
                TableFactory.Column.<Employee>of("Roles",  e -> String.valueOf(e.getRole())),
                TableFactory.Column.<Employee>of("Salary", e -> e.getSalary() != null ? String.format("R$ %.2f", e.getSalary()) : "R$ 0.00"),
                TableFactory.Column.<Employee>of("Hire Date", e -> e.getHireDate() != null ? new SimpleDateFormat("dd/MM/yyyy").format(e.getHireDate()) : "")
        );

        TableFactory<Employee> factory = new TableFactory<>(columns);
        factory.initializeTable(employeeTable, this::handleDeleteEmployee, this::handleUpdateEmployee);

        refreshTableData();

        ViewManager.loadStyle("style.css");
    }

    @FXML
    public void openCreationModal() {
        ViewManager.showModal("employee/EmployeeRegister.fxml", "Cadastrar Funcionario", rootPane);
        refreshTableData();
    }

    private void refreshTableData() {
        employeeTable.setItems(employeeService.getEmployeesList());
    }

    private void handleDeleteEmployee(Employee employee) {
        Runnable performDelete = () -> {
            employeeService.delete(employee);
            this.refreshTableData();
        };

        ViewManager.showConfirmDialog(
            "Confirmar Exclusão",
            "Tem certeza que deseja excluir este registro?",
            performDelete
        );
    }

    private void handleUpdateEmployee(Employee employee) {
        ViewManager.showModal("employee/EmployeeRegister.fxml", "Atualizar Funcionario", rootPane,
                (EmployeeRegisterController controller) -> {
                    controller.setEmployee(employee);
                });
        refreshTableData();
    }
}