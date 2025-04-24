package data_management;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.Test;

import com.alerts.AlertGenerator;
import com.data_management.DataStorage;
import com.data_management.Patient;

public class AlertGeneratorTest {
    @Test
    public void testEvaluateData() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outputStream));


        DataStorage storage = new DataStorage();
        Patient patient = new Patient(1);
        patient.addRecord(108, "HeartRate", 20000);

        AlertGenerator generator = new AlertGenerator(storage);
        generator.evaluateData(patient);


        System.setOut(originalOut);
        String printed = outputStream.toString().trim();
        assertEquals("abnormal heart rate", printed);


    }
}
