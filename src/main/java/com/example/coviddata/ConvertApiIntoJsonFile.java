package com.example.coviddata;

import java.io.FileWriter;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class ConvertApiIntoJsonFile {

    // API for project: ConvertApiIntoJsonFile.makeJsonFile("https://covid-api.mmediagroup.fr/v1/cases");

    // Method that reads JSON from an API and creates a JSON file.
    public static void makeJsonFile(String urlPath) {
        try {
            URL url = new URL(urlPath);

            // Opens the connection to the api and sets the request method to "GET".
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();

            // Checks if the connection is 200 or not.
            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("HttpResponseCode: " + conn.getResponseCode());
            } else {
                String data = "";
                // Makes a scanner object that scans the input stream from the chosen URL.
                Scanner scanner = new Scanner(url.openStream());

                // Saves all the JSON data to a String.
                while (scanner.hasNext()) {
                    data += scanner.nextLine();
                }
                scanner.close();

                // Creates a new JSON file named "covidData" and saves the data to it.
                try (FileWriter file = new FileWriter("covidData.json")) {
                    file.write(data);
                    file.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}