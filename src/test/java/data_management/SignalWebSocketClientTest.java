package data_management;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.net.URI;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.data_management.DataStorage;
import com.data_management.PatientRecord;
import com.data_management.SignalWebSocketClient;

public class SignalWebSocketClientTest  {
    
    @Test
    public void testOnMessageWithValidData() throws Exception {
        DataStorage storage = DataStorage.getInstance();
        SignalWebSocketClient client = new SignalWebSocketClient(new URI("ws://localhost:8080"), storage);

        client.onMessage("41, 165, blood_pressure, 120.0");

        List<PatientRecord> records = storage.getRecords(41, 164, 166);
        assertEquals(1, records.size());
        assertEquals("blood_pressure", records.get(0).getRecordType());
        assertEquals(120.0, records.get(0).getMeasurementValue());
    }

    @Test
    public void testOnMessageWithMalformedData() throws Exception {
        DataStorage storage = DataStorage.getInstance();
        SignalWebSocketClient client = new SignalWebSocketClient(new URI("ws://localhost:8080"), storage);

        client.onMessage("111, 165, heart_rate");

        List<PatientRecord> records = storage.getRecords(111, 164, 166); // no patient ID used
        assertTrue(records.isEmpty());
    }
}
