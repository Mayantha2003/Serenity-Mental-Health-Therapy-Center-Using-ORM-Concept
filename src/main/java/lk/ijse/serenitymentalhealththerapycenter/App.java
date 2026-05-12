package lk.ijse.serenitymentalhealththerapycenter;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import lk.ijse.serenitymentalhealththerapycenter.config.SessionFactoryConfig;
import lk.ijse.serenitymentalhealththerapycenter.entity.User;
import lk.ijse.serenitymentalhealththerapycenter.util.PasswordUtil;
import lombok.Getter;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.io.IOException;
import java.time.LocalDateTime;

public class App extends Application {

    @Getter
    private static Scene scene;

    @Getter
    private static Stage primaryStage;

    @Override
    public void start(Stage stage) throws IOException {

        // Create default admin only once
        createDefaultAdmin();

        primaryStage = stage;

        scene = new Scene(loadFXML("Login"), 900, 600);

        primaryStage.setMaximized(false);

        primaryStage.getIcons().add(
                new Image(App.class.getResourceAsStream("/image/img.png"))
        );

        primaryStage.setTitle("Serenity Mental Health Therapy Center - Login");

        primaryStage.setScene(scene);

        primaryStage.setMinWidth(750);
        primaryStage.setMinHeight(500);

        primaryStage.centerOnScreen();
        primaryStage.show();
    }

    private void createDefaultAdmin() {

        Session session = null;
        Transaction transaction = null;

        try {

            session = SessionFactoryConfig
                    .getInstance()
                    .getSession();

            transaction = session.beginTransaction();

            User existingUser = session.createQuery(
                            "FROM User u WHERE u.username = :username", User.class)
                    .setParameter("username", "admin")
                    .uniqueResult();

            if (existingUser == null) {

                User admin = new User();

                admin.setUsername("admin");

                admin.setPasswordHash(PasswordUtil.hashPassword("Admin@123"));

                admin.setRole("ADMIN");
                admin.setFullName("System Admin");
                admin.setEmail("admin@gmail.com");
                admin.setPhone("0770000000");
                admin.setStatus("ACTIVE");
                admin.setFailedLoginAttempts(0);
                admin.setCreatedAt(LocalDateTime.now());

                session.persist(admin);

                transaction.commit();

                System.out.println("Default admin created!");
                System.out.println("Username: admin");
                System.out.println("Password: Admin@123");

            } else {

                System.out.println("Admin already exists!");
                transaction.commit();
            }

        } catch (Exception e) {

            if (transaction != null) {
                transaction.rollback();
            }

            System.out.println("Error creating admin:");
            e.printStackTrace();

        } finally {

            if (session != null) {
                session.close();
            }
        }
    }


    public static void setRoot(String fxml) throws IOException {

        scene.setRoot(loadFXML(fxml));

        if ("Dashboard".equals(fxml)) {

            primaryStage.hide();
            primaryStage.setMaximized(true);
            primaryStage.centerOnScreen();
            primaryStage.show();

            primaryStage.setTitle("Serenity Mental Health Therapy Center - Dashboard");

        } else {

            primaryStage.setWidth(900);
            primaryStage.setHeight(600);
            primaryStage.setMaximized(false);
            primaryStage.centerOnScreen();
        }
    }

    public static Parent loadFXML(String fxml) throws IOException {

        return new FXMLLoader(
                App.class.getResource(
                        "/lk/ijse/serenitymentalhealththerapycenter/" + fxml + ".fxml"
                )
        ).load();
    }

    public static void main(String[] args) {
        launch();
    }
}