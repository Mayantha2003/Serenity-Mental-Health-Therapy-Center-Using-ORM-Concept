module lk.ijse.serenitymentalhealththerapycenter {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires javafx.base;

    requires org.hibernate.orm.core;
    requires jakarta.persistence;
    requires java.naming;
    requires bcrypt;
    requires java.sql;
    requires mysql.connector.j;
    requires static lombok;

    opens lk.ijse.serenitymentalhealththerapycenter to javafx.graphics;
    opens lk.ijse.serenitymentalhealththerapycenter.controller to javafx.fxml;
    opens lk.ijse.serenitymentalhealththerapycenter.entity to org.hibernate.orm.core;
    opens lk.ijse.serenitymentalhealththerapycenter.config to org.hibernate.orm.core;
    opens lk.ijse.serenitymentalhealththerapycenter.dto to javafx.base;
    opens lk.ijse.serenitymentalhealththerapycenter.dto.tm to javafx.base;

    exports lk.ijse.serenitymentalhealththerapycenter;
    exports lk.ijse.serenitymentalhealththerapycenter.controller;
}