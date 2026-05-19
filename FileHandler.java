package com.mycompany.ehks;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;
import java.util.Optional;
import javafx.scene.control.Alert;

public class FileHandler {

    public static void saveAllData(String patientsPath, String screeningsPath) throws IOException {
        savePatientsCSV(patientsPath);
        saveScreeningsCSV(screeningsPath);
    }

    private static void savePatientsCSV(String filename) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            writer.write("Name;Vorname;Geburtsdatum;VersichertenID;Strasse;Hausnummer;PLZ;Ort;VersichertenArt;Geschlecht;BetriebsstaettenNr;Arztnummer;Arztname");
            writer.newLine();

            for (Patient p : DataStorage.getPatients()) {
                writer.write(p.toString());
                writer.newLine();
            }
        }
    }

    private static void saveScreeningsCSV(String filename) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            writer.write("VersichertenID;Untersuchungsdatum;VerdachtsdiagnoseND;MalignesMelanom;Basalzellkarzinom;SpinozellulaeresKarzinom;AndererHautkrebs;SonstigerDermatologischerBefund;UeberweisungAnDermatologen;Gesundheitsuntersuchung;UeberweisungImRahmenHKS;UeberweisenderArztHatHKS;AngabeUeberweisenderArzt;UeberweisenderArztMalignesMelanom;UeberweisenderArztBasalzellkarzinom;UeberweisenderArztSpinozellulaeresKarzinom;UeberweisenderArztAndererHautkrebs;DermatologeVerdachtsdiagnose;DermatologeMalignesMelanom;DermatologeBasalzellkarzinom;DermatologeSpinozellulaeresKarzinom;DermatologeAndererHautkrebs;DermatologeSonstigerBiopsieBefund;BiopsieOderExzision;AnzahlBiopsien;AnderweitigeTherapie;KeineWeitereTherapie;HistopathologieMalignesMelanom;MelanomKlassifikation;MelanomBreslow;HistopathologieBasalzellkarzinom;BasalzellHorizontal;BasalzellVertikal;HistopathologieSpinozellulaeresKarzinom;SpinozellulaeresKlassifikation;SpinozellulaeresGrading;HistopathologieAndererHautkrebs;HistopathologieAtypischerNaevus;HistopathologieJunktionalerNaevus;HistopathologieAktinischeKeratose;HistopathologieAndereVeränderung");
            writer.newLine();

            for (ScreeningData s : DataStorage.getScreenings()) {
                writer.write(s.toString());
                writer.newLine();
            }
        }
    }

    public static void loadAllData(String patientsPath, String screeningsPath) throws Exception {
        DataStorage.clearAll();

        loadPatientsCSV(patientsPath);
        loadScreeningsCSV(screeningsPath);
    }

    private static void loadPatientsCSV(String filename) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get(filename));
        boolean isFirstLine = true;

        for (String line : lines) {
            if (isFirstLine) {
                isFirstLine = false;
                continue;
            }

            String[] parts = line.split(";", -1);
            if (parts.length < 13) {
                throw new IOException("Ungültige Patienten-CSV-Zeile (zu wenig Felder): " + line);
            }

            Patient p = new Patient();
            p.setName(parts[0]);
            p.setVorname(parts[1]);
            if (!parts[2].isEmpty()) {
                try {
                    p.setGeburtsdatum(LocalDate.parse(parts[2]));
                } catch (Exception e) {
                }
            }
            p.setVersichertenId(parts[3]);
            p.setStrasse(parts[4]);
            p.setHausnummer(parts[5]);
            p.setPlz(parts[6]);
            p.setOrt(parts[7]);
            p.setVersichertenArt(parts[8]);
            p.setGeschlecht(parts[9]);
            p.setBetriebsstaettenNr(parts[10]);
            p.setArztnummer(parts[11]);
            p.setArztname(parts[12]);

            DataStorage.addPatient(p);
        }
    }

    private static void loadScreeningsCSV(String filename) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get(filename));
        boolean isFirstLine = true;
        List<String> warnings = new ArrayList<>();

        for (String line : lines) {
            if (isFirstLine) {
                isFirstLine = false;
                continue;
            }

            String[] parts = line.split(";", -1);
            if (parts.length < 41) {
                throw new IOException("Ungültige Screening-CSV-Zeile (zu wenig Felder): " + line);
            }

            ScreeningData s = new ScreeningData();

            String pid = parts[0];
            Optional<Patient> foundOpt = DataStorage.getPatients().stream()
                    .filter(p -> pid != null && pid.equals(p.getVersichertenId()))
                    .findFirst();

            Patient patient;
            if (foundOpt.isPresent()) {
                patient = foundOpt.get();
            } else {
                patient = new Patient();
                patient.setVersichertenId(pid);
                patient.setName("Unbekannt");
                patient.setVorname("Unbekannt");

                DataStorage.addPatientForce(patient);
                warnings.add("WICHTIG: Patient mit ID '" + pid + "' nicht gefunden → automatisch erstellt und hinzugefügt.");
            }
            s.setPatient(patient);

            if (!parts[1].isEmpty()) {
                try {
                    s.setUntersuchungsdatum(LocalDate.parse(parts[1]));
                } catch (Exception e) {
                    warnings.add("WARNUNG: Ungültiges Untersuchungsdatum bei Patient " + pid + " → übersprungen.");
                }
            }

            s.setVerdachtsdiagnoseND(Boolean.parseBoolean(parts[2]));
            s.setMalignesMelanom(Boolean.parseBoolean(parts[3]));
            s.setBasalzellkarzinom(Boolean.parseBoolean(parts[4]));
            s.setSpinozellulaeresKarzinom(Boolean.parseBoolean(parts[5]));
            s.setAndererHautkrebs(Boolean.parseBoolean(parts[6]));
            s.setSonstigerDermatologischerBefund(Boolean.parseBoolean(parts[7]));
            s.setUeberweisungAnDermatologen(Boolean.parseBoolean(parts[8]));
            s.setGesundheitsuntersuchung(Boolean.parseBoolean(parts[9]));
            s.setUeberweisungImRahmenHKS(Boolean.parseBoolean(parts[10]));
            s.setUeberweisenderArztHatHKS(Boolean.parseBoolean(parts[11]));
            s.setAngabeUeberweisenderArzt(Boolean.parseBoolean(parts[12]));
            s.setUeberweisenderArztMalignesMelanom(Boolean.parseBoolean(parts[13]));
            s.setUeberweisenderArztBasalzellkarzinom(Boolean.parseBoolean(parts[14]));
            s.setUeberweisenderArztSpinozellulaeresKarzinom(Boolean.parseBoolean(parts[15]));
            s.setUeberweisenderArztAndererHautkrebs(Boolean.parseBoolean(parts[16]));
            s.setDermatologeVerdachtsdiagnose(Boolean.parseBoolean(parts[17]));
            s.setDermatologeMalignesMelanom(Boolean.parseBoolean(parts[18]));
            s.setDermatologeBasalzellkarzinom(Boolean.parseBoolean(parts[19]));
            s.setDermatologeSpinozellulaeresKarzinom(Boolean.parseBoolean(parts[20]));
            s.setDermatologeAndererHautkrebs(Boolean.parseBoolean(parts[21]));
            s.setDermatologeSonstigerBiopsieBefund(Boolean.parseBoolean(parts[22]));
            s.setBiopsieOderExzision(Boolean.parseBoolean(parts[23]));

            try {
                s.setAnzahlBiopsien(parts[24].isEmpty() ? 0 : Integer.parseInt(parts[24]));
            } catch (NumberFormatException e) {
                s.setAnzahlBiopsien(0);
                warnings.add("WARNUNG: Ungültige Anzahl Biopsien bei " + pid + " → auf 0 gesetzt.");
            }

            s.setAnderweitigeTherapie(Boolean.parseBoolean(parts[25]));
            s.setKeineWeitereTherapie(Boolean.parseBoolean(parts[26]));

            s.setHistopathologieMalignesMelanom(Boolean.parseBoolean(parts[27]));
            s.setMelanomKlassifikation(parts[28].isEmpty() ? null : parts[28]);
            s.setMelanomBreslow(parts[29].isEmpty() ? null : parts[29]);

            s.setHistopathologieBasalzellkarzinom(Boolean.parseBoolean(parts[30]));
            s.setBasalzellHorizontal(parseDoubleSafely(parts[31], "horizontal", pid, warnings));
            s.setBasalzellVertikal(parseDoubleSafely(parts[32], "vertikal", pid, warnings));

            s.setHistopathologieSpinozellulaeresKarzinom(Boolean.parseBoolean(parts[33]));
            s.setSpinozellulaeresKlassifikation(parts[34].isEmpty() ? null : parts[34]);
            s.setSpinozellulaeresGrading(parts[35].isEmpty() ? null : parts[35]);

            s.setHistopathologieAndererHautkrebs(Boolean.parseBoolean(parts[36]));
            s.setHistopathologieAtypischerNaevus(Boolean.parseBoolean(parts[37]));
            s.setHistopathologieJunktionalerNaevus(Boolean.parseBoolean(parts[38]));
            s.setHistopathologieAktinischeKeratose(Boolean.parseBoolean(parts[39]));
            s.setHistopathologieAndereVeränderung(Boolean.parseBoolean(parts[40]));

            DataStorage.addScreening(s);
        }

        if (!warnings.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Import-Warnungen");
            alert.setHeaderText("Einige Daten konnten nicht korrekt importiert werden.");
            alert.setContentText(String.join("\n", warnings));
            alert.showAndWait();
        }
    }

    private static Double parseDoubleSafely(String value, String field, String pid, List<String> warnings) {
        if (value.isEmpty()) {
            return null;
        }
        try {
            return Double.parseDouble(value);
        } catch (NumberFormatException e) {
            warnings.add("WARNUNG: Ungültiger Wert für '" + field + "' bei Patient " + pid + " → auf null gesetzt.");
            return null;
        }
    }
}