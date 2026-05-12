package lk.ijse.serenitymentalhealththerapycenter.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.serenitymentalhealththerapycenter.bo.BOFactory;
import lk.ijse.serenitymentalhealththerapycenter.bo.BOTypes;
import lk.ijse.serenitymentalhealththerapycenter.bo.custom.UserBO;
import lk.ijse.serenitymentalhealththerapycenter.dto.UserDTO;
import lk.ijse.serenitymentalhealththerapycenter.dto.tm.UserTM;
import lk.ijse.serenitymentalhealththerapycenter.util.CurrentUser;
import lk.ijse.serenitymentalhealththerapycenter.util.PasswordUtil;

import java.net.URL;
import java.util.ResourceBundle;

public class UserController implements Initializable {

    @FXML private TextField txtUserId;
    @FXML private TextField txtUsername;
    @FXML private ComboBox<String> cmbRole;
    @FXML private TextField txtEmail;
    @FXML private TextField txtPhone;
    @FXML private ComboBox<String> cmbStatus;
    @FXML private PasswordField txtPassword;
    @FXML private PasswordField txtConfirmPassword;

    @FXML private Button btnSave;
    @FXML private Button btnUpdate;
    @FXML private Button btnDelete;
    @FXML private Button btnClear;
    @FXML private Button btnAddNew;
    @FXML private TextField txtSearch;

    @FXML private TableView<UserTM> tblUsers;
    @FXML private TableColumn<UserTM, String> colUserId;
    @FXML private TableColumn<UserTM, String> colUsername;
    @FXML private TableColumn<UserTM, String> colRole;
    @FXML private TableColumn<UserTM, String> colEmail;
    @FXML private TableColumn<UserTM, String> colPhone;
    @FXML private TableColumn<UserTM, String> colStatus;

    @FXML private TextField txtCurrentUsername;
    @FXML private PasswordField txtCurrentPassword;
    @FXML private TextField txtCurrentPasswordVisible;
    @FXML private PasswordField txtNewPassword;
    @FXML private TextField txtNewPasswordVisible;
    @FXML private PasswordField txtConfirmNewPassword;
    @FXML private TextField txtConfirmNewPasswordVisible;
    @FXML private CheckBox showPasswordCheckBox;
    @FXML private Label lblChangePwError;
    @FXML private Button btnChangePassword;

    private final UserBO userBO = BOFactory.getInstance().getBO(BOTypes.USER);
    private ObservableList<UserTM> userList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        configureTable();
        loadCombos();
        loadAllUsers();
        setupTableSelection();
        setupPasswordVisibilityToggle();

        txtCurrentUsername.setText(CurrentUser.getInstance().getUsername());
        txtCurrentUsername.setEditable(false);
    }

    private void configureTable() {
        colUserId.setCellValueFactory(new PropertyValueFactory<>("userId"));
        colUsername.setCellValueFactory(new PropertyValueFactory<>("username"));
        colRole.setCellValueFactory(new PropertyValueFactory<>("role"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colPhone.setCellValueFactory(new PropertyValueFactory<>("phone"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        tblUsers.setItems(userList);
    }

    private void loadCombos() {
        cmbRole.setItems(FXCollections.observableArrayList("ADMIN", "THERAPIST", "RECEPTIONIST"));
        cmbStatus.setItems(FXCollections.observableArrayList("ACTIVE", "INACTIVE"));
        cmbStatus.setValue("ACTIVE");
    }

    private void loadAllUsers() {
        try {
            userList.clear();
            userList.addAll(userBO.getAllUsers());
        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, "Error", "Failed to load users: " + e.getMessage());
        }
    }

    private void setupTableSelection() {
        tblUsers.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal != null) populateForm(newVal);
        });
    }

    private void setupPasswordVisibilityToggle() {
        showPasswordCheckBox.selectedProperty().addListener((obs, oldVal, newVal) -> {
            togglePasswordField(txtCurrentPassword, txtCurrentPasswordVisible, newVal);
            togglePasswordField(txtNewPassword, txtNewPasswordVisible, newVal);
            togglePasswordField(txtConfirmNewPassword, txtConfirmNewPasswordVisible, newVal);
        });
    }

    private void togglePasswordField(PasswordField pwdField, TextField txtField, boolean show) {
        if (show) {
            txtField.setText(pwdField.getText());
            txtField.setVisible(true);
            txtField.setManaged(true);
            pwdField.setVisible(false);
            pwdField.setManaged(false);
        } else {
            pwdField.setText(txtField.getText());
            pwdField.setVisible(true);
            pwdField.setManaged(true);
            txtField.setVisible(false);
            txtField.setManaged(false);
        }
    }

    @FXML
    private void btnSaveOnAction() {
        try {
            if (!validateInput()) return;

            UserDTO dto = new UserDTO();
            dto.setUsername(txtUsername.getText().trim());
            dto.setPassword(txtPassword.getText());
            dto.setRole(cmbRole.getValue());
            dto.setEmail(txtEmail.getText().trim());
            dto.setPhone(txtPhone.getText().trim());
            dto.setStatus(cmbStatus.getValue());

            if (userBO.saveUser(dto)) {
                showAlert(Alert.AlertType.INFORMATION, "Success", "User saved successfully");
                clearForm();
                loadAllUsers();
            }
        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, "Error", e.getMessage());
        }
    }

    @FXML
    private void btnUpdateOnAction() {
        try {
            String userId = txtUserId.getText();
            if (userId == null || userId.isEmpty()) {
                showAlert(Alert.AlertType.WARNING, "Warning", "Please select a user to update");
                return;
            }

            UserDTO dto = new UserDTO();
            dto.setUserId(userId);
            dto.setUsername(txtUsername.getText().trim());
            dto.setRole(cmbRole.getValue());
            dto.setEmail(txtEmail.getText().trim());
            dto.setPhone(txtPhone.getText().trim());
            dto.setStatus(cmbStatus.getValue());

            if (userBO.updateUser(dto)) {
                showAlert(Alert.AlertType.INFORMATION, "Success", "User updated successfully");
                clearForm();
                loadAllUsers();
            }
        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, "Error", e.getMessage());
        }
    }

    @FXML
    private void btnDeleteOnAction() {
        String userId = txtUserId.getText();
        if (userId == null || userId.isEmpty()) {
            showAlert(Alert.AlertType.WARNING, "Warning", "Please select a user to delete");
            return;
        }

        Alert confirm = new Alert(Alert.AlertType.CONFIRMATION,
                "Are you sure you want to delete user " + userId + "?",
                ButtonType.YES, ButtonType.NO);

        confirm.showAndWait().ifPresent(response -> {
            if (response == ButtonType.YES) {
                try {
                    if (userBO.deleteUser(userId)) {
                        showAlert(Alert.AlertType.INFORMATION, "Success", "User deleted successfully");
                        clearForm();
                        loadAllUsers();
                    }
                } catch (Exception e) {
                    showAlert(Alert.AlertType.ERROR, "Error", e.getMessage());
                }
            }
        });
    }

    @FXML
    private void btnClearOnAction() {
        clearForm();
    }

    @FXML
    private void btnAddNewOnAction() {
        clearForm();
        txtUserId.setText("Auto");
        txtUsername.requestFocus();
    }

    @FXML
    private void txtSearchOnAction() {
        try {
            String search = txtSearch.getText().trim();
            if (search.isEmpty()) {
                loadAllUsers();
                return;
            }

            UserDTO user = userBO.findByUsername(search);
            if (user != null) {
                userList.clear();
                userList.add(new UserTM(
                        user.getUserId(),
                        user.getUsername(),
                        user.getRole(),
                        user.getEmail(),
                        user.getPhone(),
                        user.getStatus()
                ));
            } else {
                showAlert(Alert.AlertType.INFORMATION, "Search", "No user found with username: " + search);
            }
        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, "Error", e.getMessage());
        }
    }


    @FXML
    private void btnChangePasswordOnAction() {
        try {
            String currentPw = getPasswordFromField(txtCurrentPassword, txtCurrentPasswordVisible);
            String newPw = getPasswordFromField(txtNewPassword, txtNewPasswordVisible);
            String confirmPw = getPasswordFromField(txtConfirmNewPassword, txtConfirmNewPasswordVisible);

            lblChangePwError.setVisible(true);
            lblChangePwError.setTextFill(javafx.scene.paint.Color.web("#dc2626"));

            if (currentPw.isEmpty() || newPw.isEmpty() || confirmPw.isEmpty()) {
                lblChangePwError.setText("All password fields are required");
                return;
            }

            if (!newPw.equals(confirmPw)) {
                lblChangePwError.setText("New passwords do not match");
                return;
            }

            if (!PasswordUtil.isPasswordStrong(newPw)) {
                lblChangePwError.setText("Password must be 6+ chars with uppercase, lowercase, digit");
                return;
            }

            String userId = CurrentUser.getInstance().getUserId();
            boolean success = userBO.changePassword(userId, currentPw, newPw);

            if (success) {
                lblChangePwError.setTextFill(javafx.scene.paint.Color.web("#059669"));
                lblChangePwError.setText("Password changed successfully!");
                clearPasswordFields();
            } else {
                lblChangePwError.setText("Current password is incorrect");
            }
        } catch (Exception e) {
            lblChangePwError.setText("Error: " + e.getMessage());
        }
    }

    private String getPasswordFromField(PasswordField pwdField, TextField txtField) {
        return pwdField.isVisible() ? pwdField.getText() : txtField.getText();
    }

    private void populateForm(UserTM tm) {
        txtUserId.setText(tm.getUserId());
        txtUsername.setText(tm.getUsername());
        cmbRole.setValue(tm.getRole());
        txtEmail.setText(tm.getEmail());
        txtPhone.setText(tm.getPhone());
        cmbStatus.setValue(tm.getStatus());
    }

    private boolean validateInput() {
        if (txtUsername.getText() == null || txtUsername.getText().trim().isEmpty()) {
            showAlert(Alert.AlertType.WARNING, "Validation", "Username is required");
            return false;
        }
        if (txtPassword.getText() == null || txtPassword.getText().isEmpty()) {
            showAlert(Alert.AlertType.WARNING, "Validation", "Password is required");
            return false;
        }
        if (!txtPassword.getText().equals(txtConfirmPassword.getText())) {
            showAlert(Alert.AlertType.WARNING, "Validation", "Passwords do not match");
            return false;
        }
        if (!PasswordUtil.isPasswordStrong(txtPassword.getText())) {
            showAlert(Alert.AlertType.WARNING, "Validation",
                    "Password must be 6+ characters with uppercase, lowercase, and digit");
            return false;
        }
        if (cmbRole.getValue() == null) {
            showAlert(Alert.AlertType.WARNING, "Validation", "Role is required");
            return false;
        }
        return true;
    }

    private void clearForm() {
        txtUserId.clear();
        txtUsername.clear();
        txtPassword.clear();
        txtConfirmPassword.clear();
        cmbRole.setValue(null);
        txtEmail.clear();
        txtPhone.clear();
        cmbStatus.setValue("ACTIVE");
        tblUsers.getSelectionModel().clearSelection();
    }

    private void clearPasswordFields() {
        txtCurrentPassword.clear();
        txtCurrentPasswordVisible.clear();
        txtNewPassword.clear();
        txtNewPasswordVisible.clear();
        txtConfirmNewPassword.clear();
        txtConfirmNewPasswordVisible.clear();
    }

    private void showAlert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}