package com.data_management;

import java.io.IOException;

public interface DataReader {
    public static final DataStorage dataStorage = DataStorage.getInstance();
    /**
     * Reads data from a specified source and stores it in the data storage.
     * 
     * @param dataStorage the storage where data will be stored
     * @throws IOException if there is an error reading the data
     */
    void readData() throws IOException;

    void stopReading();
}
