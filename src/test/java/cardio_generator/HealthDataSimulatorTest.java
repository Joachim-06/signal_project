package cardio_generator;

import static org.junit.jupiter.api.Assertions.assertSame;

import org.junit.jupiter.api.Test;

import com.cardio_generator.HealthDataSimulator;


public class HealthDataSimulatorTest {
    @Test
    public void testSingletonDataStorage() {
        HealthDataSimulator dataStorage1 = HealthDataSimulator.getInstance();
        HealthDataSimulator dataStorage2 = HealthDataSimulator.getInstance();

        assertSame(dataStorage1, dataStorage2);
    }
}
