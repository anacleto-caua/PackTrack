package util;

import javafx.beans.property.SimpleStringProperty;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.util.Callback;
import java.lang.reflect.RecordComponent;
import java.util.function.Consumer;

public class TableInitializer {

    public static <T extends Record> void initializeTable(
            TableView<T> table,
            Class<T> dtoClass,
            Consumer<T> deleteHandler,
            Consumer<T> updateHandler
    ) {
        table.getColumns().clear();

        // Fallback message for empty tables
        Label emptyLabel = new Label("Nenhum registro encontrado.");
        emptyLabel.setStyle("-fx-font-size: 16px; -fx-text-fill: #999;");
        table.setPlaceholder(emptyLabel);

        double columnWidth = 150.0;

        for (RecordComponent component : dtoClass.getRecordComponents()) {
            TableColumn<T, String> column = new TableColumn<>(formatHeader(component.getName()));
            column.setCellValueFactory(data -> {
                try {
                    Object value = component.getAccessor().invoke(data.getValue());
                    return new SimpleStringProperty(value != null ? value.toString() : "");
                } catch (Exception e) {
                    throw new RuntimeException("Falha ao acessar o componente do record: " + component.getName(), e);
                }
            });
            column.setPrefWidth(columnWidth);
            table.getColumns().add(column);
        }

        TableColumn<T, Void> actionColumn = createActionColumn(deleteHandler, updateHandler);
        actionColumn.setPrefWidth(250);
        table.getColumns().add(actionColumn);
    }

    private static String formatHeader(String s) {
        if (s == null || s.isEmpty()) return "";
        String formatted = s.substring(0, 1).toUpperCase() + s.substring(1);
        return formatted.replace('_', ' ');
    }

    private static <T> TableColumn<T, Void> createActionColumn(
            Consumer<T> deleteHandler,
            Consumer<T> updateHandler
    ) {
        TableColumn<T, Void> actionColumn = new TableColumn<>("Ações");

        Callback<TableColumn<T, Void>, TableCell<T, Void>> cellFactory = param -> new ActionCell<>(
                deleteHandler,
                updateHandler
        );

        actionColumn.setCellFactory(cellFactory);
        return actionColumn;
    }

    private static class ActionCell<T> extends TableCell<T, Void> {
        private final Button deleteBtn = new Button("Apagar");
        private final Button updateBtn = new Button("Atualizar");
        private final HBox buttonBox = new HBox(5);

        public ActionCell(
                Consumer<T> deleteHandler,
                Consumer<T> updateHandler
        ) {
            deleteBtn.getStyleClass().addAll("action-button", "delete-button");
            updateBtn.getStyleClass().addAll("action-button", "update-button");

            buttonBox.getChildren().addAll(deleteBtn, updateBtn);
            buttonBox.setAlignment(Pos.CENTER);

            deleteBtn.setOnAction(event -> deleteHandler.accept(getItemData()));
            updateBtn.setOnAction(event -> updateHandler.accept(getItemData()));
        }

        @SuppressWarnings("unchecked")
        private T getItemData() {
            return (T) getTableView().getItems().get(getIndex());
        }

        @Override
        public void updateItem(Void item, boolean empty) {
            super.updateItem(item, empty);
            setGraphic(empty ? null : buttonBox);
        }
    }
}
