package com.data_management;

import java.io.IOException;
import java.net.URI;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

public class WebSocketClientSignal extends WebSocketClient implements DataReader{
    
    private DataStorage dataStorage;

    public WebSocketClientSignal(URI serverUri) {
        super(serverUri);
        this.dataStorage = DataStorage.getInstance();
    }
    
    @Override
    public void onOpen(ServerHandshake handshakedata) {
        System.out.println("WebSocket connection opened.");
    }

    @Override
    public void onMessage(String message) {
        String[] tokens = message.split(",");
        if (tokens.length != 4) {
            System.err.println("Malformed data: " + message);
            return;
        }

        try {
            int patientId = Integer.parseInt(tokens[0].trim());
            long timestamp = Long.parseLong(tokens[1].trim());
            String recordType = tokens[2].trim();
            double measurementValue = Double.parseDouble(tokens[3].trim());

            dataStorage.addPatientData(patientId, measurementValue, recordType, timestamp);
        } catch (NumberFormatException e) {
            System.err.println("Invalid number format in message: " + message);
        }
    }

    @Override
    public void onClose(int code, String reason, boolean remote) {
        System.out.println("WebSocket closed: " + reason);
    }

    @Override
    public void onError(Exception ex) {
        System.err.println("WebSocket error: " + ex.getMessage());
    }

    @Override
    public void readData() throws IOException {
        try {
            this.connectBlocking(); // blocking connect
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    @Override
    public void stopReading() {
        this.close();
    }
}
