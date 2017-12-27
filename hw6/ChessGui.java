import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;
import javafx.scene.layout.GridPane;
import javafx.scene.control.Button;
import javafx.scene.text.Font;

import javafx.stage.Stage;
public class ChessGui extends Application {
    private TableView table = new TableView();
    public static void main(String args[]) {
        launch(args);
    }
    @Override
    public void start(Stage stage) {
        Scene scene = new Scene(new Group());
        stage.setTitle("Chess DB GUI");
        stage.setWidth(1000);
        stage.setHeight(500);

        table.setEditable(true);

        TableColumn event = new TableColumn("Event");
        TableColumn site = new TableColumn("Site");
        TableColumn date = new TableColumn("Date");
        TableColumn white = new TableColumn("White");
        TableColumn black = new TableColumn("Black");
        TableColumn result = new TableColumn("Result");

        Button viewGame = new Button();
        viewGame.setText("View Game");
        Button dismiss = new Button();
        dismiss.setText("Dismiss");


        table.getColumns().addAll(event, site, date, white, black, result);
        GridPane grid = new GridPane();
        grid.add(viewGame, 0, 0);
        grid.add(dismiss, 1, 0);

        final VBox vbox = new VBox();
        vbox.setSpacing(5);
        vbox.setPadding(new Insets(10, 0, 0, 10));
        vbox.getChildren().addAll(table, grid);

        ((Group) scene.getRoot()).getChildren().addAll(vbox);

        stage.setScene(scene);
        stage.show();
    }
}
