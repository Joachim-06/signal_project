package com.cardio_generator.outputs;

/**
* Gives the type of strategy used to generate the output (eg: console or file)
*
*/
public interface OutputStrategy {

    /**
    * Generates the output with the chosen output strategy
    *
    *@param patientId The Id of the patient
    *@param timestamp The timestamp
    *@param label The label chosen
    *@param data The data that is going to be generated in the output
    */
    void output(int patientId, long timestamp, String label, String data);
}
