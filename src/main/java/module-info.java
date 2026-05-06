module lk.ijse.serenitymentalhealththerapycenter {
    requires javafx.controls;
    requires javafx.fxml;


    opens lk.ijse.serenitymentalhealththerapycenter to javafx.fxml;
    exports lk.ijse.serenitymentalhealththerapycenter;
}