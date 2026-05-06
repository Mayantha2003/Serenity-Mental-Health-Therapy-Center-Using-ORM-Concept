module lk.ijse.serenitymentalhealththerapycenter {
    requires javafx.controls;
    requires javafx.fxml;


    opens lk.ijse.serenitymentalhealththerapycenter to javafx.fxml;
    exports lk.ijse.serenitymentalhealththerapycenter;
    exports lk.ijse.serenitymentalhealththerapycenter.controller;
    opens lk.ijse.serenitymentalhealththerapycenter.controller to javafx.fxml;
}