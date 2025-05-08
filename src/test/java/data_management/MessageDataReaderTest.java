package data_management;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.data_management.DataStorage;
import com.data_management.MessageDataReader;
import com.data_management.PatientRecord;

public class MessageDataReaderTest {
    
    @Test
    public void testValidMessage() throws IOException {
        DataStorage storage = DataStorage.getInstance();
        String message = "101, 165, heart_rate, 72.5";
        MessageDataReader reader = new MessageDataReader(storage);
        reader.readData(message);

        List<PatientRecord> records = storage.getRecords(101, 164, 166);
        assertEquals(1, records.size());
        assertEquals("heart_rate", records.get(0).getRecordType());
        assertEquals(72.5, records.get(0).getMeasurementValue());
        assertEquals(165, records.get(0).getTimestamp());
    }

    @Test
    public void testMalformedMessage() throws IOException {
        DataStorage storage = DataStorage.getInstance();
        String message = "102, 165, heart_rate";  // missing one value

        MessageDataReader reader = new MessageDataReader(storage);
        reader.readData(message);

        List<PatientRecord> records = storage.getRecords(102, 164, 166);
        assertTrue(records.isEmpty());
    }


    @Test
    public void testInvalidNumberFormat() throws IOException {
        DataStorage storage = DataStorage.getInstance();
        String message = "abc, 1650000000000, heart_rate, 72.5";  // invalid patient ID

        MessageDataReader reader = new MessageDataReader(storage);
        reader.readData(message);

        List<PatientRecord> records = storage.getRecords(101, 164, 166);
        assertTrue(records.isEmpty());
    }
}
