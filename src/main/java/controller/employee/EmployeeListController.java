package controller.employee;

import controller.basis.Controller;
import dao.EmployeeDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import manager.ViewManager;
import model.Employee;
import util.table.TableFactory;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EmployeeListController extends Controller  {

    @FXML
    private TableView<Employee> employeeTable;

    private EmployeeDAO employeeDAO = new  EmployeeDAO();

    @FXML
    public void initialize() {

        var columns = List.of(
                TableFactory.Column.<Employee>of("ID", e -> String.valueOf(e.getId())),
                TableFactory.Column.<Employee>of("Username", Employee::getUsername),
                TableFactory.Column.<Employee>of("Email", Employee::getEmail),
                TableFactory.Column.<Employee>of("Phone", Employee::getPhone),
                TableFactory.Column.<Employee>of("CPF", Employee::getCpf),
                TableFactory.Column.<Employee>of("Roles", e -> e.getRoles() != null ? e.getRoles().toString() : "[]"),
                TableFactory.Column.<Employee>of("Salary", e -> e.getSalary() != null ? String.format("R$ %.2f", e.getSalary()) : "R$ 0.00"),
                TableFactory.Column.<Employee>of("Hire Date", e -> e.getHireDate() != null ? new java.text.SimpleDateFormat("dd/MM/yyyy").format(e.getHireDate()) : "")
        );

        TableFactory<Employee> factory = new TableFactory<>(columns);
        factory.initializeTable(employeeTable, this::handleDeleteEmployee, this::handleUpdateEmployee);

        refreshTableData();

        ViewManager.loadStyle("style.css");
    }

    private void refreshTableData() {
        List<Employee> dbList = employeeDAO.findAll();
        ObservableList<Employee> observableList = FXCollections.observableArrayList(dbList);

        employeeTable.setItems(observableList);
    }

    private void handleDeleteEmployee(Employee employee) {
        try {
            System.out.println("Delete funcionário: " + employee.getUsername());
            ViewManager.showModal("employee/EmployeeRegister.fxml", "Atualizar " + employee.getUsername());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void handleUpdateEmployee(Employee employee) {
        try {
            System.out.println("Atualizar funcionário: " + employee.getUsername());
            ViewManager.showModal("employee/EmployeeRegister.fxml", "Atualizar " + employee.getUsername());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}