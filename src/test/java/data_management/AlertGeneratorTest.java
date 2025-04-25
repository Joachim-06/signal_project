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
    public void testEvaluateDataBP1() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outputStream));


        DataStorage storage = new DataStorage();
        AlertGenerator generator = new AlertGenerator(storage);
        Patient patient = new Patient(1);

        patient.addRecord(70, "BloodPressure", 20000);
        patient.addRecord(80, "BloodPressure", 20000);
        patient.addRecord(91, "BloodPressure", 20000);
        generator.evaluateData(patient);

        System.setOut(originalOut);

        String printed = outputStream.toString().trim();
        assertEquals("Blood Pressure Alert", printed);


    }

    @Test
    public void testEvaluateDataBP2() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outputStream));

        DataStorage storage = new DataStorage();
        AlertGenerator generator = new AlertGenerator(storage);
        Patient patient = new Patient(1);

        patient.addRecord(141, "BloodPressure", 20000);
        generator.evaluateData(patient);

        System.setOut(originalOut);

        String printed = outputStream.toString().trim();
        assertEquals("Blood Pressure Alert", printed);

    }

    @Test
    public void testEvaluateDataBS1() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outputStream));

        DataStorage storage = new DataStorage();
        AlertGenerator generator = new AlertGenerator(storage);
        Patient patient = new Patient(1);

        patient.addRecord(0.91, "BloodSaturation", 20000);
        generator.evaluateData(patient);

        System.setOut(originalOut);

        String printed = outputStream.toString().trim();
        assertEquals("Blood Saturation Alert", printed);

    }

    @Test
    public void testEvaluateDataC() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outputStream));

        DataStorage storage = new DataStorage();
        AlertGenerator generator = new AlertGenerator(storage);
        Patient patient = new Patient(1);

        patient.addRecord(0.87, "BloodSaturation", 20000);
        patient.addRecord(89, "BloodPressure", 20000);
        generator.evaluateData(patient);

        System.setOut(originalOut);

        String printed = outputStream.toString().trim();
        assertEquals("Hypotensive Hypoxemia Alert", printed);

    }

    @Test
    public void testEvaluateDataECG() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outputStream));

        DataStorage storage = new DataStorage();
        AlertGenerator generator = new AlertGenerator(storage);
        Patient patient = new Patient(1);

        patient.addRecord(80, "ECG", 20000);
        patient.addRecord(75, "ECG", 20000);
        patient.addRecord(78, "ECG", 20000);
        patient.addRecord(82, "ECG", 20000);
        patient.addRecord(119, "ECG", 20000);
        generator.evaluateData(patient);

        System.setOut(originalOut);

        String printed = outputStream.toString().trim();
        assertEquals("ECG Alert", printed);

    }


}
