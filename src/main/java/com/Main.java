package com;

import java.io.IOException;
import java.net.URI;

import com.cardio_generator.HealthDataSimulator;
import com.data_management.DataReader;
import com.data_management.DataStorage;

public class Main {
    public static void main(String[] args) throws Exception {
        
        //URI uri = new URI("ws://localhost:8080");
        //DataStorage storage = DataStorage.getInstance();
        //DataReader reader = new WebSocketClientSignal(uri, storage);
        //reader.readData();

        //System.out.println("blabla");
        
        //Runtime.getRuntime().addShutdownHook(new Thread(reader::stopReading));

        HealthDataSimulator.main(args);
        //if (args.length > 0 && args[0].equals("DataStorage")) {
        //    DataStorage.main(new String[]{});
        //} else {
        //    HealthDataSimulator.main(new String[]{});
        //}
    }

}
