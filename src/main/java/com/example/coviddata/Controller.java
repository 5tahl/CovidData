package com.example.coviddata;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class Controller {
    @FXML
    private TextField textfieldSearch, textfieldCountry, textfieldContinent, textfieldPopulation,
            textfieldCases, textfieldDeceased, textfieldPercentageInfected, textfieldPercentageDeceased;


    // Makes my search function. The function get the user input from "textfieldSearch" and saves it to a variable.
    // It then gets the data from the chosen URL, this case covid-api, then saves it to a json file named covidData.
    // Takes the json file and sends it to the readJson method, where it gets returned as a ArrayList with
    // the relevant data. It then takes that data and displays it in the correct TextFields.
    @FXML
    protected void search() {
        String search = textfieldSearch.getText();
        ArrayList<String> covidDataArray;


        covidDataArray = ReadJsonFile.readJson("covidData.json", search);
        if (!covidDataArray.isEmpty()) {
            textfieldCountry.setText(covidDataArray.get(0));
            textfieldContinent.setText(covidDataArray.get(1));
            textfieldPopulation.setText(covidDataArray.get(2));
            textfieldCases.setText(covidDataArray.get(3));
            textfieldDeceased.setText(covidDataArray.get(4));

            // Formats the percentage values to two decimals.
            DecimalFormat format = new DecimalFormat();
            format.setMaximumFractionDigits(2);
            // Makes the percentage calculations.
            String getPercentageInfected = format.format((Double.parseDouble(covidDataArray.get(3)) / Double.parseDouble(covidDataArray.get(2)) * 100));
            textfieldPercentageInfected.setText(getPercentageInfected + "%");
            String getPercentageDeceased = format.format((Double.parseDouble(covidDataArray.get(4)) / Double.parseDouble(covidDataArray.get(2)) * 100));
            textfieldPercentageDeceased.setText(getPercentageDeceased + "%");
        }

    }
}