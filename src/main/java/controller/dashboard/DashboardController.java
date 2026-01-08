package controller.dashboard;

import controller.basis.Controller;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import model.Product;
import service.ProductService;
import service.SaleService;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class DashboardController extends Controller {

    @FXML private Label lblTotalSales;
    @FXML private Label lblSalesComparison;
    @FXML private Label lblSystemStatus;
    @FXML private Label lblTicker;
    @FXML private BarChart<String, Number> chartSalesHistory;
    @FXML private VBox vboxLowStock;

    private SaleService saleService = new SaleService();

    private ProductService productService = new ProductService();

    private final int LOW_STOCK_THRESHOLD = 15;

    @FXML
    public void initialize() {
        chartSalesHistory.setStyle("default-color0: #28a745;");

        setTotalSales(saleService.getCurrentMonthTotal());
        setSalesGrowth(saleService.getSalesGrowth());
        setTransactionFeed(saleService.getDashboardTicker());
        setChartData(saleService.getSalesHistory(5));

        loadLowStockList(LOW_STOCK_THRESHOLD);

        // Static for now
        setSystemStatus("Operação Normal");
    }

    public void setTotalSales(double value) {
        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
        lblTotalSales.setText(currencyFormat.format(value));
    }

    public void setSalesGrowth(double percentage) {
        if (percentage >= 0) {
            lblSalesComparison.setText("+" + percentage + "% comparado ao mês anterior");
            lblSalesComparison.setStyle("-fx-text-fill: #28a745;");
        } else {
            lblSalesComparison.setText("-" + percentage + "% comparado ao mês anterior");
            lblSalesComparison.setStyle("-fx-text-fill: #dc3545;");
        }
    }

    public void setSystemStatus(String status) {
        lblSystemStatus.setText(status);
    }

    public void setTransactionFeed(String feedText) {
        lblTicker.setText(feedText);
    }

    public void setChartData(Map<String, Number> data) {
        chartSalesHistory.getData().clear();

        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("Vendas");

        data.forEach((month, amount) -> {
            series.getData().add(new XYChart.Data<>(month, amount));
        });

        chartSalesHistory.getData().add(series);
    }

    public void clearLowStockList() {
        if (vboxLowStock.getChildren().size() > 2) {
            vboxLowStock.getChildren().remove(2, vboxLowStock.getChildren().size());
        }
    }

    public void addLowStockItem(String name, int quantity, int threshold) {
        String colorHex;

        if (quantity <= threshold/2) colorHex = "#dc3545";
        else colorHex = "#e67e22";

        HBox item = createStockItemHBox(name, quantity + " un", colorHex);
        vboxLowStock.getChildren().addAll(item, new Separator());
    }

    private void loadLowStockList(int threshold) {
        clearLowStockList();

        List<Product> lowStockItems = productService.getLowStockProducts(threshold);

        if (lowStockItems.isEmpty()) {
            Label okLabel = new Label("Estoque Normal");
            okLabel.setStyle("-fx-text-fill: #275f45; -fx-font-style: italic;");
            vboxLowStock.getChildren().add(okLabel);
        } else {
            for (Product p : lowStockItems) {
                addLowStockItem(p.getName(), p.getQuantity(), threshold);
            }
        }
    }

    // PRIVATE HELPERS
    private HBox createStockItemHBox(String name, String quantityStr, String colorHex) {
        HBox hbox = new HBox(10);

        Label lblName = new Label(name);
        lblName.setMaxWidth(Double.MAX_VALUE);
        HBox.setHgrow(lblName, Priority.ALWAYS);

        Label lblQty = new Label(quantityStr);
        lblQty.setStyle("-fx-font-weight: bold; -fx-text-fill: " + colorHex + ";");

        hbox.getChildren().addAll(lblName, lblQty);
        return hbox;
    }
}