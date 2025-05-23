package com.cardio_generator.outputs;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.concurrent.ConcurrentHashMap;

/**
* Handles the output of data on a file
*
*/
// Changed class name to UpperCamelCase
public class FileOutputStrategy implements OutputStrategy {

    // Changed variable name to lowerCamelCase
    private String baseDirectory;

    // Changed variable name to UPPER_SNAKE_CASE
    public final ConcurrentHashMap<String, String> FILE_MAP = new ConcurrentHashMap<>();

    // Changed class constructor name to UpperCamelCase
    public FileOutputStrategy(String baseDirectory) {

        this.baseDirectory = baseDirectory;
    }

    /**
    * Outputs patient's data to a file
    *
    *@param patientId The Id of the patient
    *@param timestamp The time at which the data was generated
    *@param label The label chosen
    *@param data The data that is going to be generated in the output
    */
    @Override
    public void output(int patientId, long timestamp, String label, String data) {
        try {
            // Create the directory
            Files.createDirectories(Paths.get(baseDirectory));
        } catch (IOException e) {
            System.err.println("Error creating base directory: " + e.getMessage());
            return;
        }
        // Set the FilePath variable
        // Changed variable to lowerCamelCase
        String filePath = FILE_MAP.computeIfAbsent(label, k -> Paths.get(baseDirectory, label + ".txt").toString());

        // Write the data to the file
        try (PrintWriter out = new PrintWriter(
                Files.newBufferedWriter(Paths.get(filePath), StandardOpenOption.CREATE, StandardOpenOption.APPEND))) {
            out.printf("Patient ID: %d, Timestamp: %d, Label: %s, Data: %s%n", patientId, timestamp, label, data);
        } catch (Exception e) {
            System.err.println("Error writing to file " + filePath + ": " + e.getMessage());
        }
    }
}
