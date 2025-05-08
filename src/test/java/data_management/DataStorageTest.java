package data_management;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import com.data_management.DataStorage;
import com.data_management.MessageDataReader;
import com.data_management.PatientRecord;
import com.data_management.DataReader;

import java.util.List;
import java.util.ArrayList;

class DataStorageTest {
    
    @Test
    public void testSingletonDataStorage() {
        DataStorage dataStorage1 = DataStorage.getInstance();
        DataStorage dataStorage2 = DataStorage.getInstance();

        assertSame(dataStorage1, dataStorage2);
    }
    
     
    @Test
    void testAddAndGetRecords()  throws IOException {
        // TODO Perhaps you can implement a mock data reader to mock the test data?
        // DataReader reader
        
        DataStorage storage = DataStorage.getInstance();
        DataReader reader = new MessageDataReader(storage);
        reader.readData("1, 1714376789050, WhiteBloodCells, 100.0");
        
        storage.addPatientData(1, 200.0, "WhiteBloodCells", 1714376789051L);

        List<PatientRecord> records = storage.getRecords(1, 1714376789050L, 1714376789051L);
        assertEquals(2, records.size()); // Check if two records are retrieved
        assertEquals(100.0, records.get(0).getMeasurementValue()); // Validate first record
    }


}
