package com.example.coviddata;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class ReadJsonFile {

    // Method that reads a JSON file and searches in that file for the "search" value.
    // If it finds that value it then returns a ArrayList of different data.
    public static ArrayList<String> readJson(String path, String search) {
        ArrayList<String> dataArray = new ArrayList<>();
        String formattedSearch = "";

        // First checks if search is empty or not, if it is, return dataArray as is.
        // If the search was not empty, format the search, so it does not care about case.
        // The reason it checks specifically for "us" is because US is spelled with all capital letters.
         if (search.equalsIgnoreCase("us")) {
            formattedSearch = search.toUpperCase();
        } else if (search.contains(" ")) {
            formattedSearch += search.substring(0, 1).toUpperCase();
            for (int i = 1; i < search.length(); i++) {
                if (search.charAt(i) == ' ' ) {
                    formattedSearch += search.substring(i, i + 2).toUpperCase();
                    i++;
                } else {
                    formattedSearch += search.charAt(i);
                }
            }
        } else if (!search.contains(" ") && !search.equals("")) {
            formattedSearch = search.substring(0, 1).toUpperCase() + search.toLowerCase().substring(1);
        } else {
            return dataArray;
        }

        // Creates a JSONParser object that will be used to parse the covidData file.
        JSONParser parser = new JSONParser();
        try {
            // Parses the JSON file and saves it as an Object object.
            Object obj = parser.parse(new FileReader(path));
            // Makes it into a JSONObject.
            JSONObject getJsonObject = (JSONObject) obj;

            // Gets the correct country data based on the search.
            JSONObject getCountry = (JSONObject) getJsonObject.get(formattedSearch);

            // Checks if the JSONObject has any data or if the search did not return anything.
            if (!(getCountry == null)) {
                // Gets all the data from the correct country and saves it to a JSONObject.
                JSONObject getAll = (JSONObject) getCountry.get("All");

                // Adds all the relevant values from the country to the ArrayList that will get returned.
                dataArray.add(getAll.get("country").toString());
                dataArray.add(getAll.get("continent").toString());
                dataArray.add(getAll.get("population").toString());
                dataArray.add(getAll.get("confirmed").toString());
                dataArray.add(getAll.get("deaths").toString());

                return dataArray;
            }

        }
        catch(FileNotFoundException e) {e.printStackTrace();}
        catch(IOException e) {e.printStackTrace();}
        catch(ParseException e) {e.printStackTrace();}
        catch(Exception e) {e.printStackTrace();}
        return dataArray;
    }
}
