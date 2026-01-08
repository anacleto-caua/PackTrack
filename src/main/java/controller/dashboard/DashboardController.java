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

import java.text.NumberFormat;
import java.util.Locale;
import java.util.Map;

public class DashboardController extends Controller {

    // --- FXML Injections ---
    @FXML private Label lblTotalSales;
    @FXML private Label lblSalesComparison;
    @FXML private Label lblSystemStatus;
    @FXML private Label lblTicker;
    @FXML private BarChart<String, Number> chartSalesHistory;
    @FXML private VBox vboxLowStock;

    /**
     * INITIALIZE: Sample calls showing how to use this controller.
     */
    @FXML
    public void initialize() {

        // Sets the main KPI total sales value, automatically formatting to BRL currency
        setTotalSales(45230.50);

        // Sets the percentage comparison; turns green if positive, red if negative
        setSalesGrowth(12.5); // Try -5.0 to see red

        // Sets the text for the system status box
        setSystemStatus("Operação Normal");

        // Sets the text for the rolling transaction feed
        setTransactionFeed("VENDA #101 { 2x PROD1, 3x PROD2, 4x PROD3, 5x PROD4 }");

        // Clears the chart and adds data (Month Name -> Value)
        setChartData(Map.of("Out/25", 12000, "Nov/25", 15000, "Dez/25", 22000, "Jan/26", 18500));

        // Clears previous list and adds specific products with quantity (Logic determines color)
        clearLowStockList();
        addLowStockItem("Produto A", 2);  // Critical (Red)
        addLowStockItem("Produto B", 12); // Warning (Orange)
        addLowStockItem("Produto C", 30); // Safe (Green)
    }

    // =================================================================================
    // PUBLIC API (HOOK YOUR SERVICES HERE)
    // =================================================================================

    /**
     * Updates the main Sales KPI.
     * @param value The total sales value (e.g., 45000.50).
     */
    public void setTotalSales(double value) {
        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
        lblTotalSales.setText(currencyFormat.format(value));
    }

    /**
     * Updates the comparison text and color.
     * @param percentage The growth percentage (e.g., 10.0 or -5.0).
     */
    public void setSalesGrowth(double percentage) {
        if (percentage >= 0) {
            lblSalesComparison.setText("+" + percentage + "% comparado ao mês anterior");
            lblSalesComparison.setStyle("-fx-text-fill: #28a745;"); // Green
        } else {
            lblSalesComparison.setText(percentage + "% comparado ao mês anterior");
            lblSalesComparison.setStyle("-fx-text-fill: #dc3545;"); // Red
        }
    }

    /**
     * Updates the System Status text.
     * @param status The status message.
     */
    public void setSystemStatus(String status) {
        lblSystemStatus.setText(status);
    }

    /**
     * Updates the string displayed in the transaction ticker.
     * @param feedText The formatted string of recent transactions.
     */
    public void setTransactionFeed(String feedText) {
        lblTicker.setText(feedText);
    }

    /**
     * Populates the Bar Chart.
     * @param data A Map where Key = Month/Year (String) and Value = Sales Amount (Number).
     */
    public void setChartData(Map<String, Number> data) {
        chartSalesHistory.getData().clear();

        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("Vendas"); // Legend title (hidden in CSS but good practice)

        data.forEach((month, amount) -> {
            series.getData().add(new XYChart.Data<>(month, amount));
        });

        chartSalesHistory.getData().add(series);
    }

    /**
     * Clears the "Low Stock" sidebar list (keeps the header).
     */
    public void clearLowStockList() {
        // Keep the first 2 elements (Header Label and Separator), remove the rest
        if (vboxLowStock.getChildren().size() > 2) {
            vboxLowStock.getChildren().remove(2, vboxLowStock.getChildren().size());
        }
    }

    /**
     * Adds a single product to the Low Stock list.
     * Automatically decides color based on quantity.
     * @param name Product Name.
     * @param quantity Current Stock.
     */
    public void addLowStockItem(String name, int quantity) {
        String colorHex;

        // Simple Logic for colors
        if (quantity <= 5) colorHex = "#dc3545";      // Red (Critical)
        else if (quantity <= 15) colorHex = "#e67e22"; // Orange (Warning)
        else colorHex = "#275f45";                     // Green (Ok)

        HBox item = createStockItemHBox(name, quantity + " un", colorHex);
        vboxLowStock.getChildren().addAll(item, new Separator());
    }

    // =================================================================================
    // PRIVATE HELPERS
    // =================================================================================

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