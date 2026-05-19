package com.mycompany.ehks;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class DataStorage {

    private static final ObservableList<Patient> patients = FXCollections.observableArrayList();
    private static final ObservableList<ScreeningData> screenings = FXCollections.observableArrayList();

    private DataStorage() {
    }

    public static ObservableList<Patient> getPatients() {
        return patients;
    }

    public static void addScreening(ScreeningData s) {
        if (s != null && !screenings.contains(s)) {
            screenings.add(s);
        }
    }

    public static ObservableList<ScreeningData> getScreenings() {
        return screenings;
    }

    public static void clearAll() {
        patients.clear();
        screenings.clear();
    }

    public static void loadAllData(String patientsPath, String screeningsPath) throws Exception {
        FileHandler.loadAllData(patientsPath, screeningsPath);
    }

    public static void addPatient(Patient p) {
        if (p != null && !patients.contains(p)) {
            patients.add(p);
        }
    }

    public static void addPatientForce(Patient p) {
        if (p != null) {
            if (!patients.contains(p)) {
                patients.add(p);
            }
        }
    }
}