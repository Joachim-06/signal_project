package com.cardio_generator.generators;

import com.cardio_generator.outputs.OutputStrategy;

/**
* Generates patient data
*
*/
public interface PatientDataGenerator {
    /**
    * Generates patient data
    *
    *@param patientId The id of the patient
    *@param outputStrategy The strategy used to output the generated data
    */
    void generate(int patientId, OutputStrategy outputStrategy);
}
