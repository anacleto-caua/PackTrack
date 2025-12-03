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

    private ObservableList<Employee> loadMockData() {
        return FXCollections.observableArrayList(
                new Employee(
                        1L,                                  // ID
                        "joao.silva",                        // Username
                        "senha123",                          // Password
                        "joao.silva@email.com",              // Email
                        "(11) 99999-0001",                   // Phone
                        "111.111.111-11",                    // CPF
                        new ArrayList<>(),                   // Roles (Empty List)
                        new BigDecimal("2500.00"),           // Salary
                        new Date()                           // HireDate
                ),
                new Employee(
                        2L,
                        "maria.oliveira",
                        "senha456",
                        "maria.o@email.com",
                        "(11) 99999-0002",
                        "222.222.222-22",
                        new ArrayList<>(),                   // Roles (Empty List)
                        new BigDecimal("5000.00"),
                        new Date()
                ),
                new Employee(
                        3L,
                        "pedro.costa",
                        "senha789",
                        "pedro.costa@email.com",
                        "(11) 99999-0003",
                        "333.333.333-33",
                        new ArrayList<>(),                   // Roles (Empty List)
                        new BigDecimal("2600.00"),
                        new Date()
                ),
                new Employee(
                        4L,
                        "sofia.pereira",
                        "senha101",
                        "sofia.p@email.com",
                        "(11) 99999-0004",
                        "444.444.444-44",
                        new ArrayList<>(),                   // Roles (Empty List)
                        new BigDecimal("1800.00"),
                        new Date()
                )
        );
    }

    private void handleDeleteEmployee(Employee employee) {
        try {
            System.out.println("Delete funcionário: " + employee.getUsername());
            ViewManager.showModal("EmployeeRegister.fxml", "Atualizar " + employee.getUsername());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void handleUpdateEmployee(Employee employee) {
        try {
            System.out.println("Atualizar funcionário: " + employee.getUsername());
            ViewManager.showModal("EmployeeRegister.fxml", "Atualizar " + employee.getUsername());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}