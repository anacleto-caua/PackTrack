package util.table;

import javafx.beans.property.SimpleStringProperty;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;

public class TableFactory<T> {

    public static class Column<T> {
        private final String header;
        private final Function<T, String> extractor;

        public Column(String header, Function<T, String> extractor) {
            this.header = header;
            this.extractor = extractor;
        }

        // Helper for cleaner code
        public static <T> Column<T> of(String header, Function<T, String> extractor) {
            return new Column<>(header, extractor);
        }
    }

    private final List<Column<T>> columns;

    public TableFactory(List<Column<T>> columns) {
        this.columns = columns;
    }

    public String createCsvString(List<T> dataList) {
        StringBuilder csv = new StringBuilder();

        // Header
        csv.append(columns.stream()
                        .map(col -> col.header)
                        .collect(Collectors.joining(";")))
                .append("\n");

        // Data
        for (T item : dataList) {
            csv.append(columns.stream()
                            .map(col -> {
                                String val = col.extractor.apply(item);
                                return val != null ? val : "";
                            })
                            .collect(Collectors.joining(",")))
                    .append("\n");
        }
        return csv.toString();
    }

    public void initializeTable(TableView<T> table, Consumer<T> deleteHandler, Consumer<T> updateHandler) {
        table.getColumns().clear();
        double defaultWidth = 150.0;

        for (Column<T> col : columns) {
            TableColumn<T, String> uiColumn = new TableColumn<>(col.header);
            uiColumn.setCellValueFactory(data ->
                    new SimpleStringProperty(col.extractor.apply(data.getValue()))
            );
            uiColumn.setPrefWidth(defaultWidth);
            table.getColumns().add(uiColumn);
        }

        if (deleteHandler != null || updateHandler != null) {
            TableColumn<T, Void> actionColumn = createActionColumn(deleteHandler, updateHandler);
            actionColumn.setPrefWidth(250);
            table.getColumns().add(actionColumn);
        }
    }

    private TableColumn<T, Void> createActionColumn(Consumer<T> deleteHandler, Consumer<T> updateHandler) {
        TableColumn<T, Void> actionColumn = new TableColumn<>("Ações");
        actionColumn.setCellFactory(param -> new ActionCell<>(deleteHandler, updateHandler));
        return actionColumn;
    }

    private static class ActionCell<T> extends TableCell<T, Void> {
        private final Button deleteBtn = new Button("Apagar");
        private final Button updateBtn = new Button("Atualizar");
        private final HBox buttonBox = new HBox(5);

        public ActionCell(Consumer<T> deleteHandler, Consumer<T> updateHandler) {
            deleteBtn.getStyleClass().addAll("action-button", "delete-button");
            updateBtn.getStyleClass().addAll("action-button", "update-button");
            buttonBox.getChildren().addAll(deleteBtn, updateBtn);
            buttonBox.setAlignment(Pos.CENTER);

            deleteBtn.setOnAction(e -> {
                T item = getItemData();
                if (item != null && deleteHandler != null) deleteHandler.accept(item);
            });
            updateBtn.setOnAction(e -> {
                T item = getItemData();
                if (item != null && updateHandler != null) updateHandler.accept(item);
            });
        }

        private T getItemData() { return getTableView().getItems().get(getIndex()); }

        @Override
        protected void updateItem(Void item, boolean empty) {
            super.updateItem(item, empty);
            setGraphic(empty ? null : buttonBox);
        }
    }
}