package com.mycompany.ehks;

import java.util.ArrayList;
import java.util.List;

public class Validator {

    private static boolean isBlank(String s) {
        return s == null || s.trim().isEmpty();
    }

    public static boolean isValidGeschlecht(String geschlecht) {
        return geschlecht != null
                && (geschlecht.equals("M") || geschlecht.equals("F") || geschlecht.equals("UN"));
    }

    public static boolean isValidVersichertenArt(String art) {
        return art != null
                && (art.equals("1") || art.equals("3") || art.equals("5"));
    }

    public static boolean isValidPatient(
            String name,
            String vorname,
            String geburtsdatum,
            String versichertenId,
            String strasse,
            String hausnummer,
            String plz,
            String ort,
            String versichertenArt,
            String geschlecht,
            String betriebsstaettenNr,
            String arztnummer,
            String arztname
    ) {
        if (name == null || name.trim().isEmpty()) {
            return false;
        }
        if (vorname == null || vorname.trim().isEmpty()) {
            return false;
        }
        if (geburtsdatum == null || !geburtsdatum.matches("\\d{4}-\\d{2}-\\d{2}")) {
            return false;
        }
        try {
            java.time.LocalDate.parse(geburtsdatum);
        } catch (Exception e) {
            return false;
        }
        if (versichertenId == null || versichertenId.trim().isEmpty()) {
            return false;
        }
        if (strasse == null || strasse.trim().isEmpty()) {
            return false;
        }
        if (hausnummer == null || hausnummer.trim().isEmpty()) {
            return false;
        }
        if (plz == null || plz.trim().isEmpty()) {
            return false;
        }
        if (ort == null || ort.trim().isEmpty()) {
            return false;
        }
        if (versichertenArt == null || !isValidVersichertenArt(versichertenArt)) {
            return false;
        }
        if (geschlecht == null || !isValidGeschlecht(geschlecht)) {
            return false;
        }
        if (betriebsstaettenNr == null || betriebsstaettenNr.trim().isEmpty()) {
            return false;
        }
        if (arztnummer == null || arztnummer.trim().isEmpty()) {
            return false;
        }
        if (arztname == null || arztname.trim().isEmpty()) {
            return false;
        }

        return true;
    }

    public static boolean isValidScreening(ScreeningData screening) {
        List<String> errors = getValidationErrors(screening);
        return errors.isEmpty();
    }

    public static List<String> getValidationErrors(ScreeningData screening) {
        List<String> errors = new ArrayList<>();

        if (screening == null) {
            errors.add("Screening ist null.");
            return errors;
        }

        if (!screening.isVerdachtsdiagnoseND()) {
            errors.add("VerdachtsdiagnoseND muss 'Ja' sein.");
        } else {
            int anzahlDiagnosen = 0;
            if (screening.isMalignesMelanom()) {
                anzahlDiagnosen++;
            }
            if (screening.isBasalzellkarzinom()) {
                anzahlDiagnosen++;
            }
            if (screening.isSpinozellulaeresKarzinom()) {
                anzahlDiagnosen++;
            }
            if (screening.isAndererHautkrebs()) {
                anzahlDiagnosen++;
            }
            if (screening.isSonstigerDermatologischerBefund()) {
                anzahlDiagnosen++;
            }
            if (screening.isUeberweisungAnDermatologen()) {
                anzahlDiagnosen++;
            }

            if (anzahlDiagnosen == 0) {
                errors.add("Bei 'VerdachtsdiagnoseND = Ja' muss mindestens eine Angabe erfolgen.");
            }

            if (anzahlDiagnosen != 1) {
                errors.add("Es darf **nur eine** Angabe in 1.1.2–1.1.7 gegeben sein (momentan: " + anzahlDiagnosen + ").");
            }

            if (screening.isUeberweisungAnDermatologen()
                    && (screening.isMalignesMelanom()
                    || screening.isBasalzellkarzinom()
                    || screening.isSpinozellulaeresKarzinom()
                    || screening.isAndererHautkrebs()
                    || screening.isSonstigerDermatologischerBefund())) {
                errors.add("Bei 'Überweisung an Dermatologen = Ja' dürfen keine anderen Diagnosen angegeben werden.");
            }
        }

        if (!screening.isGesundheitsuntersuchung()) {
            errors.add("Gesundheitsuntersuchung muss 'Ja' sein.");
        }

        if (!screening.isUeberweisungImRahmenHKS()) {
            errors.add("'Patient kommt auf Überweisung im Rahmen HKS' muss 'Ja' sein.");
        }
        if (!screening.isUeberweisenderArztHatHKS()) {
            errors.add("'Überweisender Arzt hat HKS durchgeführt' muss 'Ja' sein.");
        }

        if (screening.isAngabeUeberweisenderArzt()) {
            int anzahlUeberweisend = 0;
            if (screening.isUeberweisenderArztMalignesMelanom()) {
                anzahlUeberweisend++;
            }
            if (screening.isUeberweisenderArztBasalzellkarzinom()) {
                anzahlUeberweisend++;
            }
            if (screening.isUeberweisenderArztSpinozellulaeresKarzinom()) {
                anzahlUeberweisend++;
            }
            if (screening.isUeberweisenderArztAndererHautkrebs()) {
                anzahlUeberweisend++;
            }

            if (anzahlUeberweisend == 0) {
                errors.add("Bei 'Angabe liegt vor' muss mindestens eine Verdachtsdiagnose angegeben werden.");
            } else if (anzahlUeberweisend > 1) {
                errors.add("Es darf **nur eine** Verdachtsdiagnose des überweisenden Arztes angegeben werden.");
            }
        } else {
            if (screening.isUeberweisenderArztMalignesMelanom()
                    || screening.isUeberweisenderArztBasalzellkarzinom()
                    || screening.isUeberweisenderArztSpinozellulaeresKarzinom()
                    || screening.isUeberweisenderArztAndererHautkrebs()) {
                errors.add("Wenn 'Angabe liegt vor' = Nein, dürfen keine Diagnosen angegeben werden.");
            }
        }

        if (!screening.isDermatologeVerdachtsdiagnose()) {
            errors.add("Verdachtsdiagnose des Dermatologen muss 'Ja' sein.");
        } else {
            int dermatologeAnzahl = 0;
            if (screening.isDermatologeMalignesMelanom()) {
                dermatologeAnzahl++;
            }
            if (screening.isDermatologeBasalzellkarzinom()) {
                dermatologeAnzahl++;
            }
            if (screening.isDermatologeSpinozellulaeresKarzinom()) {
                dermatologeAnzahl++;
            }
            if (screening.isDermatologeAndererHautkrebs()) {
                dermatologeAnzahl++;
            }
            if (screening.isDermatologeSonstigerBiopsieBefund()) {
                dermatologeAnzahl++;
            }

            if (dermatologeAnzahl == 0) {
                errors.add("Bei 'Verdachtsdiagnose' = Ja muss mindestens eine Verdachtsdiagnose angegeben werden.");
            } else if (dermatologeAnzahl > 1) {
                errors.add("Es darf **nur eine** Verdachtsdiagnose des Dermatologen angegeben werden.");
            }
        }

        if (!screening.isBiopsieOderExzision()) {
            errors.add("'Biopsie/Exzision' muss 'Ja' sein.");
        } else {
            int anzahlBiopsien = screening.getAnzahlBiopsien();
            if (anzahlBiopsien < 0 || anzahlBiopsien > 99) {
                errors.add("'Anzahl der entnommenen Biopsien' muss zwischen 0 und 99 liegen.");
            }

            if (screening.isAnderweitigeTherapie() && screening.isKeineWeitereTherapie()) {
                errors.add("'anderweitige Therapie' und 'keine weitere Therapie' können nicht beide 'Ja' sein.");
            }
        }

        if (!screening.isBiopsieOderExzision()) {
            if (screening.isHistopathologieMalignesMelanom()
                    || screening.isHistopathologieBasalzellkarzinom()
                    || screening.isHistopathologieSpinozellulaeresKarzinom()
                    || screening.isHistopathologieAndererHautkrebs()
                    || screening.isHistopathologieAtypischerNaevus()
                    || screening.isHistopathologieJunktionalerNaevus()
                    || screening.isHistopathologieAktinischeKeratose()
                    || screening.isHistopathologieAndereVeränderung()) {
                errors.add("Keine Histopathologie-Felder erlaubt, wenn 'Biopsie/Exzision' = Nein.");
            }
        } else {
            int histopathKrebsAnzahl = 0;
            if (screening.isHistopathologieMalignesMelanom()) {
                histopathKrebsAnzahl++;
            }
            if (screening.isHistopathologieBasalzellkarzinom()) {
                histopathKrebsAnzahl++;
            }
            if (screening.isHistopathologieSpinozellulaeresKarzinom()) {
                histopathKrebsAnzahl++;
            }
            if (screening.isHistopathologieAndererHautkrebs()) {
                histopathKrebsAnzahl++;
            }

            if (histopathKrebsAnzahl == 0) {
                errors.add("Bei 'Biopsie/Exzision' muss mindestens eine Hautkrebs-Diagnose angegeben werden.");
            } else if (histopathKrebsAnzahl > 1) {
                errors.add("Es darf **nur eine** Hautkrebs-Diagnose angegeben werden.");
            }

            if (screening.isHistopathologieMalignesMelanom()) {
                if (isBlank(screening.getMelanomKlassifikation())) {
                    errors.add("'Klassifikation (Melanom)' ist Pflicht bei Melanom.");
                }
            }

            if (screening.isHistopathologieBasalzellkarzinom()) {
                Double h = screening.getBasalzellHorizontal();
                if (h == null || h < 0.1 || h > 999.9) {
                    errors.add("'Horizontaler Tumordurchmesser (Basalzell)' muss 0.1–999.9 mm sein.");
                }
            }

            if (screening.isHistopathologieSpinozellulaeresKarzinom()) {
                if (isBlank(screening.getSpinozellulaeresKlassifikation())) {
                    errors.add("'Klassifikation (Spinozell)' ist Pflicht bei Spinozell.");
                }
            }

            boolean hatNaevus = screening.isHistopathologieAtypischerNaevus();
            boolean hatJunktional = screening.isHistopathologieJunktionalerNaevus();
            boolean hatKeratose = screening.isHistopathologieAktinischeKeratose();
            boolean hatAndere = screening.isHistopathologieAndereVeränderung();

            if (!hatNaevus && !hatJunktional && !hatKeratose && !hatAndere) {
                errors.add("Bei 'Biopsie/Exzision' muss mindestens eine der Nävi/Keratosen angegeben werden.");
            }
        }

        if (screening.getPatient() == null) {
            errors.add("Patient ist nicht gesetzt.");
        } else if (!screening.getPatient().isValid()) {
            errors.add("Patient ist nicht gültig (KBV-Prävention-Plausi fehlgeschlagen).");
        }

        if (screening.getUntersuchungsdatum() == null) {
            errors.add("Untersuchungsdatum ist nicht gesetzt.");
        }

        return errors;
    }

    public static boolean isValidAnzahlBiopsien(int anzahl) {
        return anzahl >= 0 && anzahl <= 99;
    }

    public static boolean isValidTumordurchmesser(Double durchmesser) {
        return durchmesser != null && durchmesser >= 0.1 && durchmesser <= 999.9;
    }

    public static boolean isValidMelanomKlassifikation(String klassifikation) {
        return klassifikation != null
                && (klassifikation.equals("in situ") || klassifikation.equals("invasiv"));
    }

    public static boolean isValidSpinozellulaeresKlassifikation(String klassifikation) {
        return klassifikation != null
                && (klassifikation.equals("in situ") || klassifikation.equals("invasiv"));
    }

    public static boolean isValidGrading(String grading) {
        return grading != null
                && (grading.equals("Gx") || grading.equals("G1")
                || grading.equals("G2") || grading.equals("G3") || grading.equals("G4"));
    }

    public static boolean isValidBreslow(String breslow) {
        return breslow != null
                && (breslow.equals("≤ 1 mm") || breslow.equals("1,01-2 mm")
                || breslow.equals("2,01-4 mm") || breslow.equals("> 4 mm"));
    }
}