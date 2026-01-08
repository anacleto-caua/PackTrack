package service;

import dao.SaleDAO;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.ValidationException;
import jakarta.validation.Validator;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Sale;
import org.hibernate.validator.messageinterpolation.ParameterMessageInterpolator;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;
import java.util.stream.Collectors;

public class SaleService {
    private SaleDAO saleDAO = new SaleDAO();

    private final Validator validator = Validation.byDefaultProvider()
            .configure()
            .messageInterpolator(new ParameterMessageInterpolator())
            .buildValidatorFactory()
            .getValidator();

    public void save(Sale sale) {
        saleDAO.save(sale);
    }

    public void update(Sale sale) {
        saleDAO.update(sale);
    }

    public void delete(Sale sale) {
        saleDAO.delete(sale.getId());
    }

    public ObservableList<Sale> getSalesList() {
        List<Sale> dbList = saleDAO.findAll();
        return FXCollections.observableArrayList(dbList);
    }

    public void saveOrUpdate(Sale sale) {
        validate(sale);

        if (sale.getId() == null) {
            saleDAO.save(sale);
        } else {
            saleDAO.update(sale);
        }
    }

    private void validate(Sale sale) {
        Set<ConstraintViolation<Sale>> violations = validator.validate(sale);
        if (!violations.isEmpty()) {
            String errors = violations.stream()
                    .map(ConstraintViolation::getMessage)
                    .collect(Collectors.joining("\n"));
            throw new ValidationException(errors);
        }
    }

    // --- DASHBOARD METHODS ---

    public double getCurrentMonthTotal() {
        Date start = toDate(LocalDate.now().withDayOfMonth(1));
        Date end = toDate(LocalDate.now().plusMonths(1).withDayOfMonth(1).minusDays(1));

        return saleDAO.sumTotalValueByDateRange(start, end).doubleValue();
    }

    public double getSalesGrowth() {
        double current = getCurrentMonthTotal();

        Date prevStart = toDate(LocalDate.now().minusMonths(1).withDayOfMonth(1));
        Date prevEnd = toDate(LocalDate.now().withDayOfMonth(1).minusDays(1));
        double previous = saleDAO.sumTotalValueByDateRange(prevStart, prevEnd).doubleValue();

        if (previous == 0) return 100.0; // Avoid division by zero

        double growth = ((current - previous) / previous) * 100;

        BigDecimal bd = new BigDecimal(Double.toString(growth));
        return bd.setScale(1, RoundingMode.HALF_UP).doubleValue();
    }

    public String getDashboardTicker() {
        List<Sale> recentSales = saleDAO.findRecent(5); // Get last 5
        StringBuilder ticker = new StringBuilder();

        for (Sale sale : recentSales) {
            if (!ticker.isEmpty()) {
                ticker.append("  /  ");
            }
            ticker.append("VENDA #").append(sale.getId()).append(" { ");

            String itemsStr = sale.getItems().stream()
                    .map(item -> item.getQuantity() + "x " + item.getProduct().getName())
                    .collect(Collectors.joining(", "));

            ticker.append(itemsStr).append(" }");
        }

        return ticker.toString().isEmpty() ? "Nenhuma venda registrada." : ticker.toString();
    }

    public Map<String, Number> getSalesHistory(int monthsBack) {
        Map<String, Number> history = new LinkedHashMap<>();
        SimpleDateFormat sdf = new SimpleDateFormat("MMM/yy", new Locale("pt", "BR"));

        for (int i = monthsBack - 1; i >= 0; i--) {
            LocalDate dateRef = LocalDate.now().minusMonths(i);

            Date start = toDate(dateRef.withDayOfMonth(1));
            Date end = toDate(dateRef.plusMonths(1).withDayOfMonth(1).minusDays(1));

            BigDecimal total = saleDAO.sumTotalValueByDateRange(start, end);

            String label = sdf.format(start);
            label = label.substring(0, 1).toUpperCase() + label.substring(1);

            history.put(label, total);
        }
        return history;
    }

    private Date toDate(LocalDate localDate) {
        return Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }
}
