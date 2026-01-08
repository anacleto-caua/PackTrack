package controller.sale;

import controller.basis.Controller;
import jakarta.validation.ValidationException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.util.StringConverter;
import model.Client;
import model.Product;
import model.Sale;
import model.SaleItem;
import service.ClientService;
import service.ProductService;
import service.SaleService;
import util.SaleIten;
import util.table.TableFactory;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;

public class SaleRegisterController extends Controller {

    @FXML
    private TableView<SaleIten> itemsTable;
    @FXML
    private ComboBox<Client> saleClientName;
    @FXML
    private ComboBox<Product> products;
    @FXML
    private TextField txtQuantity;
    @FXML
    private TextField saleTotalValue;
    @FXML
    private TextField saleCurrency; // TODO: CHANGE FROM TEXTFIELD TO A MORE SUITABLE FIELD
    @FXML
    private TextField saleDate;
    @FXML
    private Label errorLabel;
    @FXML
    private DatePicker datePicker;

    private Sale currentSale;

    private SaleService saleService = new SaleService();
    private ClientService clientService = new ClientService();
    private ProductService productService = new ProductService();
    private ObservableList<SaleIten> itensDaVenda = FXCollections.observableArrayList();
    private ObservableList<Client> obsClients;
    private ObservableList<Product> obsProducts;


    @FXML
    public void initialize() {
        obsClients = clientService.getClientsList();
        obsProducts = productService.getProductsList();

        if (saleClientName != null) {
            saleClientName.getItems().addAll(obsClients);
        }
        if (products != null) {
            products.getItems().addAll(obsProducts);
        }

        this.configureComboboxConverter();

        UnaryOperator<TextFormatter.Change> filter = change -> {
            String text = change.getControlNewText();
            if (text.matches("\\d*")) { // RegEx: aceita apenas dígitos
                return change;
            }
            return null; // Rejeita a mudança se não for número
        };

        TextFormatter<String> textFormatter = new TextFormatter<>(filter);
        txtQuantity.setTextFormatter(textFormatter);

        this.initTable();
    }

    private void initTable() {
        var columns = List.of(
                TableFactory.Column.<SaleIten>of("Produto", s -> s.getProduct().getName()),
                TableFactory.Column.<SaleIten>of("Qtd", s -> s.getQtd().toString())
        );

        TableFactory<SaleIten> factory = new TableFactory<>(columns);
        factory.initializeTable(itemsTable, this::deleteItem, this::updateItem);

        refreshTableData();
    }

    private void deleteItem(SaleIten item) {}
    private void updateItem(SaleIten item) {}

    private void refreshTableData() {
        itemsTable.setItems(itensDaVenda);
    }

    private void configureComboboxConverter() {
        products.setConverter(new StringConverter<Product>() {
            @Override
            public String toString(Product product) {
                // O que aparecerá no Dropdown e no campo selecionado
                return (product != null) ? product.getName() : "";
            }

            @Override
            public Product fromString(String string) {
                // Geralmente não é necessário se o ComboBox não for editável
                return null;
            }
        });

        saleClientName.setConverter(new StringConverter<Client>() {
            @Override
            public String toString(Client client) {
                // O que aparecerá no Dropdown e no campo selecionado
                return (client != null) ? client.getName() : "";
            }

            @Override
            public Client fromString(String string) {
                // Geralmente não é necessário se o ComboBox não for editável
                return null;
            }
        });
    }

    @FXML
    public void onCancel(ActionEvent event) {
        this.closeWindow(event);
    }

    @FXML
    public void onSubmit(ActionEvent event){
        try {
            if(currentSale == null){
                currentSale = new Sale();
            }
            currentSale.setClient(saleClientName.getValue()); //client
//            currentSale.setItems(itensDaVenda.stream().map(SaleIten::getProduct).collect(Collectors.toList())); //items
            currentSale.setTotalValue(new BigDecimal(saleTotalValue.getText()));
            currentSale.setDate(new Date(saleDate.getText()));

            saleService.saveOrUpdate(currentSale);
            this.closeWindow(event);

        } catch (ValidationException e) {
            errorLabel.setText(e.getMessage());
            errorLabel.setVisible(true);
        }
    }

    public void setSale(Sale sale) {
        this.currentSale = sale;

        if (sale != null) {
            this.saleClientName.setValue(sale.getClient());
            this.saleTotalValue.setText(sale.getTotalValue().toString());
            this.saleDate.setText(sale.getDate().toString());
        }
    }

    public void handleDateChange() {

    }

    public void onAddItem() {
        Product p = products.getValue();
        int qtd = Integer.parseInt(txtQuantity.getText());

        if (p != null && qtd > 0) {
            itensDaVenda.add(new SaleIten(p, qtd, p.getValue()));
//            atualizarTotal();

            // Limpa os campos para o próximo item
            txtQuantity.clear();
            products.getSelectionModel().clearSelection();
        }
    }
}
