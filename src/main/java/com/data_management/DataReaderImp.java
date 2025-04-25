package com.data_management;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class DataReaderImp implements DataReader{

    private final String outputDir;

    public DataReaderImp(String outputDir) {
        this.outputDir = outputDir;
    }

    @Override
    public void readData(DataStorage dataStorage) throws IOException {
        Path dirPath = Paths.get(outputDir);
        if (!Files.exists(dirPath) || !Files.isDirectory(dirPath)) {
            throw new FileNotFoundException("Output directory does not exist: " + outputDir);
        }

        try (DirectoryStream<Path> stream = Files.newDirectoryStream(dirPath)) {
            for (Path filePath : stream) {
                if (Files.isRegularFile(filePath)) {
                    parseFile(filePath, dataStorage);
                }
            }
        }
    }

    private void parseFile(Path filePath, DataStorage dataStorage) throws IOException {
        try (BufferedReader reader = Files.newBufferedReader(filePath)) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] tokens = line.split(",");
                if (tokens.length != 4) continue; // skip malformed lines

                try {
                    int patientId = Integer.parseInt(tokens[0].trim());
                    double measurementValue = Double.parseDouble(tokens[1].trim());
                    String recordType = tokens[2].trim();
                    long timestamp = Long.parseLong(tokens[3].trim());

                    dataStorage.addPatientData(patientId, measurementValue, recordType, timestamp);
                } catch (NumberFormatException e) {
                    System.err.println("Invalid data format in line: " + line);
                }
            }
        }
    }
    
}
