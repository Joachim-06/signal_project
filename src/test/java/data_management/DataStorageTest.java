package data_management;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import com.data_management.DataStorage;
import com.data_management.PatientRecord;
import com.data_management.DataReader;

import java.util.List;
import java.util.ArrayList;

class DataStorageTest {
    
    @Test
    void testAddAndGetRecords()  throws IOException {
        // TODO Perhaps you can implement a mock data reader to mock the test data?
        // DataReader reader
        DataReader reader = new DataReader() {
            public List<PatientRecord> mockRecords() {
                List<PatientRecord> mockRecords = new ArrayList<>();
                mockRecords.add(new PatientRecord(1, 100.0, "WhiteBloodCells", 1714376789050L));
                return mockRecords;
            }

            @Override
            public void readData(DataStorage dataStorage) throws IOException {
                try {
                    List<PatientRecord> records = mockRecords();
                    for (PatientRecord record : records) {
                        dataStorage.addPatientData(
                            record.getPatientId(),
                            record.getMeasurementValue(),
                            record.getRecordType(),
                            record.getTimestamp()
                        );
                    }
                } catch (Exception e) {
                    throw new IOException("Failed to read and store data", e);
                }
            }
        };
        DataStorage storage = new DataStorage();
        reader.readData(storage);
        
        storage.addPatientData(1, 200.0, "WhiteBloodCells", 1714376789051L);

        List<PatientRecord> records = storage.getRecords(1, 1714376789050L, 1714376789051L);
        assertEquals(2, records.size()); // Check if two records are retrieved
        assertEquals(100.0, records.get(0).getMeasurementValue()); // Validate first record
    }


}
