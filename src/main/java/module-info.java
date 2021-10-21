module com.example.coviddata {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;
    requires json.simple;

    opens com.example.coviddata to javafx.fxml;
    exports com.example.coviddata;
}