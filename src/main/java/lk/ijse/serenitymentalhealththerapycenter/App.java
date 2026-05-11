package lk.ijse.serenitymentalhealththerapycenter;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class App extends Application {

    private static Scene scene;
    private static Stage primaryStage;

    @Override
    public void start(Stage stage) throws IOException {
        primaryStage = stage;
        scene = new Scene(loadFXML("Login"), 900, 600);
        primaryStage.setMaximized(false);
        primaryStage.getIcons().add(new Image(App.class.getResourceAsStream("/image/img.png")));
        primaryStage.setTitle("Serenity Mental Health Therapy Center - Login");
        primaryStage.setScene(scene);
        primaryStage.setMinWidth(750);
        primaryStage.setMinHeight(500);
        primaryStage.centerOnScreen();
        primaryStage.show();
    }

    public static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));

        if ("Dashboard".equals(fxml)) {
            primaryStage.hide();
            primaryStage.setMaximized(true);
            primaryStage.centerOnScreen();
            primaryStage.show();
            primaryStage.getIcons().add(new Image(App.class.getResourceAsStream("/image/img.png")));
            primaryStage.setTitle("Serenity Mental Health Therapy Center - Dashboard");
            primaryStage.toFront();
        } else {
            primaryStage.setWidth(900);
            primaryStage.setHeight(600);
            primaryStage.setMaximized(false);
            primaryStage.centerOnScreen();
        }
    }

    public static Parent loadFXML(String fxml) throws IOException {
        // REMOVED "view/" from the path
        return new FXMLLoader(App.class.getResource("/lk/ijse/serenitymentalhealththerapycenter/" + fxml + ".fxml")).load();
    }

    public static Stage getPrimaryStage() {
        return primaryStage;
    }

    public static Scene getScene() {
        return scene;
    }

    public static void main(String[] args) {
        launch();
    }
}