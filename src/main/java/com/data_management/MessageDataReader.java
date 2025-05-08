package com.data_management;

import java.io.IOException;

public class MessageDataReader implements DataReader{

    private DataStorage dataStorage;

    public MessageDataReader(DataStorage dataStorage) {
        this.dataStorage = dataStorage;
    }

    @Override
    public void readData(String message) throws IOException {
        String[] tokens = message.split(",");
        if (tokens.length != 4) {
            System.err.println("Malformed data: " + message);
            return;
        }

        try {
            System.out.println("ok");
            int patientId = Integer.parseInt(tokens[0].trim());
            long timestamp = Long.parseLong(tokens[1].trim());
            String recordType = tokens[2].trim();
            double measurementValue = Double.parseDouble(tokens[3].trim());

            dataStorage.addPatientData(patientId, measurementValue, recordType, timestamp);
        } catch (NumberFormatException e) {
            System.err.println("Invalid number format in message: " + message);
        }
    }
    
}
