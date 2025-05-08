package com.data_management;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import java.io.IOException;
import java.net.URI;


public class SignalWebSocketClient extends WebSocketClient {

    private DataStorage dataStorage;
    private DataReader dataReader;

    // Constructor takes a URI (e.g. ws://localhost:8887)
    public SignalWebSocketClient(URI serverUri, DataStorage dataStorage) {
        super(serverUri);
        this.dataStorage = dataStorage;
        this.dataReader = new MessageDataReader(this.dataStorage);
    }

    // Called when the connection is opened
    @Override
    public void onOpen(ServerHandshake handshake) {
        System.out.println("Connected to server");
    }

    // Called when a message is received
    @Override
    public void onMessage(String message){
        try {
            dataReader.readData(message);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    // Called when connection is closed
    @Override
    public void onClose(int code, String reason, boolean remote) {
        System.out.println("Disconnected");
    }

    // Called on error
    @Override
    public void onError(Exception ex) {
        ex.printStackTrace();
    }

    public static void main(String[] args) throws Exception {
        URI serverUri = new URI("ws://localhost:8090");
        DataStorage storage = DataStorage.getInstance();
        SignalWebSocketClient client = new SignalWebSocketClient(serverUri, storage);
        client.connectBlocking(); // Wait until connected
        System.out.println("ok ok");
        while (true) Thread.sleep(1000);
    }
}
