package lk.ijse.serenitymentalhealththerapycenter.controller;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import lk.ijse.serenitymentalhealththerapycenter.App;
import lk.ijse.serenitymentalhealththerapycenter.bo.BOFactory;
import lk.ijse.serenitymentalhealththerapycenter.bo.BOTypes;
import lk.ijse.serenitymentalhealththerapycenter.bo.custom.UserBO;
import lk.ijse.serenitymentalhealththerapycenter.util.CurrentUser;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    @FXML private TextField txtUsername;
    @FXML private PasswordField txtPassword;
    @FXML private TextField txtPasswordVisible;
    @FXML private CheckBox showPasswordCheckBox;
    @FXML private ComboBox<String> cmbRole;
    @FXML private Label lblError;
    @FXML private Button btnLogin;

    private final UserBO userBO = BOFactory.getInstance().getBO(BOTypes.USER);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setupEnterKeyNavigation();
        setupPasswordToggle();
        loadRoleCombo();
        Platform.runLater(() -> txtUsername.requestFocus());
    }

    private void setupEnterKeyNavigation() {
        txtUsername.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                txtPassword.requestFocus();
            }
        });

        txtPassword.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                btnLogin.fire();
            }
        });

        txtPasswordVisible.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                btnLogin.fire();
            }
        });
    }

    private void setupPasswordToggle() {
        showPasswordCheckBox.selectedProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal) {
                txtPasswordVisible.setText(txtPassword.getText());
                txtPasswordVisible.setVisible(true);
                txtPasswordVisible.setManaged(true);
                txtPassword.setVisible(false);
                txtPassword.setManaged(false);
            } else {
                txtPassword.setText(txtPasswordVisible.getText());
                txtPassword.setVisible(true);
                txtPassword.setManaged(true);
                txtPasswordVisible.setVisible(false);
                txtPasswordVisible.setManaged(false);
            }
        });
    }

    private void loadRoleCombo() {
        cmbRole.setItems(javafx.collections.FXCollections.observableArrayList(
                "ADMIN", "THERAPIST", "RECEPTIONIST"
        ));
    }

    @FXML
    private void btnLoginOnAction() {
        lblError.setVisible(false);

        String username = txtUsername.getText().trim();
        String password = showPasswordCheckBox.isSelected()
                ? txtPasswordVisible.getText()
                : txtPassword.getText();
        String role = cmbRole.getValue();

        if (username.isEmpty()) {
            showError("Please enter your username");
            txtUsername.requestFocus();
            return;
        }

        if (password.isEmpty()) {
            showError("Please enter your password");
            txtPassword.requestFocus();
            return;
        }

        if (role == null || role.isEmpty()) {
            showError("Please select a role");
            cmbRole.requestFocus();
            return;
        }

        btnLogin.setDisable(true);
        btnLogin.setText("Signing in...");

        new Thread(() -> {
            try {
                boolean success = userBO.authenticate(username, password);

                Platform.runLater(() -> {
                    if (success) {
                        // Verify role matches
                        String actualRole = CurrentUser.getInstance().getRole();
                        if (!role.equalsIgnoreCase(actualRole)) {
                            loginFailed("Role mismatch. Please select correct role.");
                            return;
                        }

                        // Load Dashboard using App.setRoot()
                        try {
                            App.setRoot("Dashboard");
                        } catch (IOException e) {
                            showError("Failed to load dashboard: " + e.getMessage());
                            e.printStackTrace();
                        }
                    } else {
                        loginFailed("Invalid username or password");
                    }
                });

            } catch (SecurityException e) {
                Platform.runLater(() -> loginFailed(e.getMessage()));
            } catch (Exception e) {
                Platform.runLater(() -> loginFailed("System error: " + e.getMessage()));
                e.printStackTrace();
            }
        }).start();
    }

    private void loginFailed(String message) {
        btnLogin.setDisable(false);
        btnLogin.setText("LOGIN");
        showError(message);
        txtPassword.clear();
        txtPasswordVisible.clear();
        txtPassword.requestFocus();
    }

    private void showError(String message) {
        lblError.setText(message);
        lblError.setVisible(true);
    }
}