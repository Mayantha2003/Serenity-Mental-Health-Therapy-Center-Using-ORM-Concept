module lk.ijse.serenitymentalhealththerapycenter {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;

    opens lk.ijse.serenitymentalhealththerapycenter to javafx.graphics;
    opens lk.ijse.serenitymentalhealththerapycenter.controller to javafx.fxml;

    exports lk.ijse.serenitymentalhealththerapycenter;
    exports lk.ijse.serenitymentalhealththerapycenter.controller;
}