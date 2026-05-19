package com.mycompany.ehks;

import java.time.LocalDate;
import java.util.UUID;

public class ScreeningData {

    private Patient patient;
    private LocalDate untersuchungsdatum;

    private boolean verdachtsdiagnoseND;
    private boolean malignesMelanom;
    private boolean basalzellkarzinom;
    private boolean spinozellulaeresKarzinom;
    private boolean andererHautkrebs;
    private boolean sonstigerDermatologischerBefund;
    private boolean ueberweisungAnDermatologen;

    private boolean gesundheitsuntersuchung;

    private boolean ueberweisungImRahmenHKS;
    private boolean ueberweisenderArztHatHKS;

    private boolean angabeUeberweisenderArzt;
    private boolean ueberweisenderArztMalignesMelanom;
    private boolean ueberweisenderArztBasalzellkarzinom;
    private boolean ueberweisenderArztSpinozellulaeresKarzinom;
    private boolean ueberweisenderArztAndererHautkrebs;

    private boolean dermatologeVerdachtsdiagnose;
    private boolean dermatologeMalignesMelanom;
    private boolean dermatologeBasalzellkarzinom;
    private boolean dermatologeSpinozellulaeresKarzinom;
    private boolean dermatologeAndererHautkrebs;
    private boolean dermatologeSonstigerBiopsieBefund;

    private boolean biopsieOderExzision;
    private int anzahlBiopsien;
    private boolean anderweitigeTherapie;
    private boolean keineWeitereTherapie;

    private boolean histopathologieMalignesMelanom;
    private String melanomKlassifikation;
    private String melanomBreslow;

    private boolean histopathologieBasalzellkarzinom;
    private Double basalzellHorizontal;
    private Double basalzellVertikal;

    private boolean histopathologieSpinozellulaeresKarzinom;
    private String spinozellulaeresKlassifikation;
    private String spinozellulaeresGrading;

    private boolean histopathologieAndererHautkrebs;
    private boolean histopathologieAtypischerNaevus;
    private boolean histopathologieJunktionalerNaevus;
    private boolean histopathologieAktinischeKeratose;
    private boolean histopathologieAndereVeränderung;

    public ScreeningData() {
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public LocalDate getUntersuchungsdatum() {
        return untersuchungsdatum;
    }

    public void setUntersuchungsdatum(LocalDate datum) {
        this.untersuchungsdatum = datum;
    }

    public boolean isVerdachtsdiagnoseND() {
        return verdachtsdiagnoseND;
    }

    public void setVerdachtsdiagnoseND(boolean verdachtsdiagnoseND) {
        this.verdachtsdiagnoseND = verdachtsdiagnoseND;
    }

    public boolean isMalignesMelanom() {
        return malignesMelanom;
    }

    public void setMalignesMelanom(boolean malignesMelanom) {
        this.malignesMelanom = malignesMelanom;
    }

    public boolean isBasalzellkarzinom() {
        return basalzellkarzinom;
    }

    public void setBasalzellkarzinom(boolean basalzellkarzinom) {
        this.basalzellkarzinom = basalzellkarzinom;
    }

    public boolean isSpinozellulaeresKarzinom() {
        return spinozellulaeresKarzinom;
    }

    public void setSpinozellulaeresKarzinom(boolean spinozellulaeresKarzinom) {
        this.spinozellulaeresKarzinom = spinozellulaeresKarzinom;
    }

    public boolean isAndererHautkrebs() {
        return andererHautkrebs;
    }

    public void setAndererHautkrebs(boolean andererHautkrebs) {
        this.andererHautkrebs = andererHautkrebs;
    }

    public boolean isSonstigerDermatologischerBefund() {
        return sonstigerDermatologischerBefund;
    }

    public void setSonstigerDermatologischerBefund(boolean sonstigerDermatologischerBefund) {
        this.sonstigerDermatologischerBefund = sonstigerDermatologischerBefund;
    }

    public boolean isUeberweisungAnDermatologen() {
        return ueberweisungAnDermatologen;
    }

    public void setUeberweisungAnDermatologen(boolean ueberweisungAnDermatologen) {
        this.ueberweisungAnDermatologen = ueberweisungAnDermatologen;
    }

    public boolean isGesundheitsuntersuchung() {
        return gesundheitsuntersuchung;
    }

    public void setGesundheitsuntersuchung(boolean gesundheitsuntersuchung) {
        this.gesundheitsuntersuchung = gesundheitsuntersuchung;
    }

    public boolean isUeberweisungImRahmenHKS() {
        return ueberweisungImRahmenHKS;
    }

    public void setUeberweisungImRahmenHKS(boolean ueberweisungImRahmenHKS) {
        this.ueberweisungImRahmenHKS = ueberweisungImRahmenHKS;
    }

    public boolean isUeberweisenderArztHatHKS() {
        return ueberweisenderArztHatHKS;
    }

    public void setUeberweisenderArztHatHKS(boolean ueberweisenderArztHatHKS) {
        this.ueberweisenderArztHatHKS = ueberweisenderArztHatHKS;
    }

    public boolean isAngabeUeberweisenderArzt() {
        return angabeUeberweisenderArzt;
    }

    public void setAngabeUeberweisenderArzt(boolean angabeUeberweisenderArzt) {
        this.angabeUeberweisenderArzt = angabeUeberweisenderArzt;
    }

    public boolean isUeberweisenderArztMalignesMelanom() {
        return ueberweisenderArztMalignesMelanom;
    }

    public void setUeberweisenderArztMalignesMelanom(boolean ueberweisenderArztMalignesMelanom) {
        this.ueberweisenderArztMalignesMelanom = ueberweisenderArztMalignesMelanom;
    }

    public boolean isUeberweisenderArztBasalzellkarzinom() {
        return ueberweisenderArztBasalzellkarzinom;
    }

    public void setUeberweisenderArztBasalzellkarzinom(boolean ueberweisenderArztBasalzellkarzinom) {
        this.ueberweisenderArztBasalzellkarzinom = ueberweisenderArztBasalzellkarzinom;
    }

    public boolean isUeberweisenderArztSpinozellulaeresKarzinom() {
        return ueberweisenderArztSpinozellulaeresKarzinom;
    }

    public void setUeberweisenderArztSpinozellulaeresKarzinom(boolean ueberweisenderArztSpinozellulaeresKarzinom) {
        this.ueberweisenderArztSpinozellulaeresKarzinom = ueberweisenderArztSpinozellulaeresKarzinom;
    }

    public boolean isUeberweisenderArztAndererHautkrebs() {
        return ueberweisenderArztAndererHautkrebs;
    }

    public void setUeberweisenderArztAndererHautkrebs(boolean ueberweisenderArztAndererHautkrebs) {
        this.ueberweisenderArztAndererHautkrebs = ueberweisenderArztAndererHautkrebs;
    }

    public boolean isDermatologeVerdachtsdiagnose() {
        return dermatologeVerdachtsdiagnose;
    }

    public void setDermatologeVerdachtsdiagnose(boolean dermatologeVerdachtsdiagnose) {
        this.dermatologeVerdachtsdiagnose = dermatologeVerdachtsdiagnose;
    }

    public boolean isDermatologeMalignesMelanom() {
        return dermatologeMalignesMelanom;
    }

    public void setDermatologeMalignesMelanom(boolean dermatologeMalignesMelanom) {
        this.dermatologeMalignesMelanom = dermatologeMalignesMelanom;
    }

    public boolean isDermatologeBasalzellkarzinom() {
        return dermatologeBasalzellkarzinom;
    }

    public void setDermatologeBasalzellkarzinom(boolean dermatologeBasalzellkarzinom) {
        this.dermatologeBasalzellkarzinom = dermatologeBasalzellkarzinom;
    }

    public boolean isDermatologeSpinozellulaeresKarzinom() {
        return dermatologeSpinozellulaeresKarzinom;
    }

    public void setDermatologeSpinozellulaeresKarzinom(boolean dermatologeSpinozellulaeresKarzinom) {
        this.dermatologeSpinozellulaeresKarzinom = dermatologeSpinozellulaeresKarzinom;
    }

    public boolean isDermatologeAndererHautkrebs() {
        return dermatologeAndererHautkrebs;
    }

    public void setDermatologeAndererHautkrebs(boolean dermatologeAndererHautkrebs) {
        this.dermatologeAndererHautkrebs = dermatologeAndererHautkrebs;
    }

    public boolean isDermatologeSonstigerBiopsieBefund() {
        return dermatologeSonstigerBiopsieBefund;
    }

    public void setDermatologeSonstigerBiopsieBefund(boolean dermatologeSonstigerBiopsieBefund) {
        this.dermatologeSonstigerBiopsieBefund = dermatologeSonstigerBiopsieBefund;
    }

    public boolean isBiopsieOderExzision() {
        return biopsieOderExzision;
    }

    public void setBiopsieOderExzision(boolean biopsieOderExzision) {
        this.biopsieOderExzision = biopsieOderExzision;
    }

    public int getAnzahlBiopsien() {
        return anzahlBiopsien;
    }

    public void setAnzahlBiopsien(int anzahlBiopsien) {
        this.anzahlBiopsien = anzahlBiopsien;
    }

    public boolean isAnderweitigeTherapie() {
        return anderweitigeTherapie;
    }

    public void setAnderweitigeTherapie(boolean anderweitigeTherapie) {
        this.anderweitigeTherapie = anderweitigeTherapie;
    }

    public boolean isKeineWeitereTherapie() {
        return keineWeitereTherapie;
    }

    public void setKeineWeitereTherapie(boolean keineWeitereTherapie) {
        this.keineWeitereTherapie = keineWeitereTherapie;
    }

    public boolean isHistopathologieMalignesMelanom() {
        return histopathologieMalignesMelanom;
    }

    public void setHistopathologieMalignesMelanom(boolean histopathologieMalignesMelanom) {
        this.histopathologieMalignesMelanom = histopathologieMalignesMelanom;
    }

    public String getMelanomKlassifikation() {
        return melanomKlassifikation;
    }

    public void setMelanomKlassifikation(String melanomKlassifikation) {
        this.melanomKlassifikation = melanomKlassifikation;
    }

    public String getMelanomBreslow() {
        return melanomBreslow;
    }

    public void setMelanomBreslow(String melanomBreslow) {
        this.melanomBreslow = melanomBreslow;
    }

    public boolean isHistopathologieBasalzellkarzinom() {
        return histopathologieBasalzellkarzinom;
    }

    public void setHistopathologieBasalzellkarzinom(boolean histopathologieBasalzellkarzinom) {
        this.histopathologieBasalzellkarzinom = histopathologieBasalzellkarzinom;
    }

    public Double getBasalzellHorizontal() {
        return basalzellHorizontal;
    }

    public void setBasalzellHorizontal(Double basalzellHorizontal) {
        this.basalzellHorizontal = basalzellHorizontal;
    }

    public Double getBasalzellVertikal() {
        return basalzellVertikal;
    }

    public void setBasalzellVertikal(Double basalzellVertikal) {
        this.basalzellVertikal = basalzellVertikal;
    }

    public boolean isHistopathologieSpinozellulaeresKarzinom() {
        return histopathologieSpinozellulaeresKarzinom;
    }

    public void setHistopathologieSpinozellulaeresKarzinom(boolean histopathologieSpinozellulaeresKarzinom) {
        this.histopathologieSpinozellulaeresKarzinom = histopathologieSpinozellulaeresKarzinom;
    }

    public String getSpinozellulaeresKlassifikation() {
        return spinozellulaeresKlassifikation;
    }

    public void setSpinozellulaeresKlassifikation(String spinozellulaeresKlassifikation) {
        this.spinozellulaeresKlassifikation = spinozellulaeresKlassifikation;
    }

    public String getSpinozellulaeresGrading() {
        return spinozellulaeresGrading;
    }

    public void setSpinozellulaeresGrading(String spinozellulaeresGrading) {
        this.spinozellulaeresGrading = spinozellulaeresGrading;
    }

    public boolean isHistopathologieAndererHautkrebs() {
        return histopathologieAndererHautkrebs;
    }

    public void setHistopathologieAndererHautkrebs(boolean histopathologieAndererHautkrebs) {
        this.histopathologieAndererHautkrebs = histopathologieAndererHautkrebs;
    }

    public boolean isHistopathologieAtypischerNaevus() {
        return histopathologieAtypischerNaevus;
    }

    public void setHistopathologieAtypischerNaevus(boolean histopathologieAtypischerNaevus) {
        this.histopathologieAtypischerNaevus = histopathologieAtypischerNaevus;
    }

    public boolean isHistopathologieJunktionalerNaevus() {
        return histopathologieJunktionalerNaevus;
    }

    public void setHistopathologieJunktionalerNaevus(boolean histopathologieJunktionalerNaevus) {
        this.histopathologieJunktionalerNaevus = histopathologieJunktionalerNaevus;
    }

    public boolean isHistopathologieAktinischeKeratose() {
        return histopathologieAktinischeKeratose;
    }

    public void setHistopathologieAktinischeKeratose(boolean histopathologieAktinischeKeratose) {
        this.histopathologieAktinischeKeratose = histopathologieAktinischeKeratose;
    }

    public boolean isHistopathologieAndereVeränderung() {
        return histopathologieAndereVeränderung;
    }

    public void setHistopathologieAndereVeränderung(boolean histopathologieAndereVeränderung) {
        this.histopathologieAndereVeränderung = histopathologieAndereVeränderung;
    }

    public String getPatientName() {
        return patient != null ? patient.getPatientName() : "Unbekannt";
    }

    public String getUntersuchungsdatumString() {
        return untersuchungsdatum != null ? untersuchungsdatum.toString() : "";
    }

    public String getStatusColor() {
        if (biopsieOderExzision || histopathologieMalignesMelanom || histopathologieBasalzellkarzinom
                || histopathologieSpinozellulaeresKarzinom || histopathologieAndererHautkrebs) {
            return "#ffaaaa";
        }
        if (malignesMelanom || basalzellkarzinom || spinozellulaeresKarzinom || andererHautkrebs
                || dermatologeMalignesMelanom || dermatologeBasalzellkarzinom || dermatologeSpinozellulaeresKarzinom
                || dermatologeAndererHautkrebs) {
            return "#ffffaa";
        }
        if (gesundheitsuntersuchung) {
            return "#aaffaa";
        }
        return "#ffffff";
    }

    public boolean isValid() {
        if (!verdachtsdiagnoseND) {
            return false;
        }

        if (verdachtsdiagnoseND && malignesMelanom) {
            if (!dermatologeVerdachtsdiagnose || !dermatologeMalignesMelanom) {
                return false;
            }
        }

        if (!gesundheitsuntersuchung) {
            return false;
        }

        if (!ueberweisungImRahmenHKS) {
            return false;
        }

        if (!ueberweisenderArztHatHKS) {
            return false;
        }

        int ueberweisenderArztCount = 0;
        if (angabeUeberweisenderArzt) {
            ueberweisenderArztCount++;
        }
        if (ueberweisenderArztMalignesMelanom) {
            ueberweisenderArztCount++;
        }
        if (ueberweisenderArztBasalzellkarzinom) {
            ueberweisenderArztCount++;
        }
        if (ueberweisenderArztSpinozellulaeresKarzinom) {
            ueberweisenderArztCount++;
        }
        if (ueberweisenderArztAndererHautkrebs) {
            ueberweisenderArztCount++;
        }
        if (ueberweisenderArztCount > 1) {
            return false;
        }

        if (!dermatologeVerdachtsdiagnose) {
            return false;
        }

        if (!biopsieOderExzision) {
            return false;
        }

        if (biopsieOderExzision && (anzahlBiopsien < 0 || anzahlBiopsien > 99)) {
            return false;
        }

        if (anderweitigeTherapie && keineWeitereTherapie) {
            return false;
        }

        if (!biopsieOderExzision && (histopathologieMalignesMelanom || histopathologieBasalzellkarzinom
                || histopathologieSpinozellulaeresKarzinom || histopathologieAndererHautkrebs)) {
            return false;
        }

        if (histopathologieMalignesMelanom && (melanomKlassifikation == null || melanomKlassifikation.isEmpty())) {
            return false;
        }

        if (histopathologieBasalzellkarzinom && (basalzellHorizontal == null || basalzellHorizontal < 0.1 || basalzellHorizontal > 999.9)) {
            return false;
        }

        if (histopathologieSpinozellulaeresKarzinom && (spinozellulaeresKlassifikation == null || spinozellulaeresKlassifikation.isEmpty())) {
            return false;
        }

        return patient != null && patient.isValid();
    }

    @Override
    public String toString() {
        return String.join(";",
                patient != null ? patient.getVersichertenId() : "",
                getUntersuchungsdatumString(),
                Boolean.toString(verdachtsdiagnoseND),
                Boolean.toString(malignesMelanom),
                Boolean.toString(basalzellkarzinom),
                Boolean.toString(spinozellulaeresKarzinom),
                Boolean.toString(andererHautkrebs),
                Boolean.toString(sonstigerDermatologischerBefund),
                Boolean.toString(ueberweisungAnDermatologen),
                Boolean.toString(gesundheitsuntersuchung),
                Boolean.toString(ueberweisungImRahmenHKS),
                Boolean.toString(ueberweisenderArztHatHKS),
                Boolean.toString(angabeUeberweisenderArzt),
                Boolean.toString(ueberweisenderArztMalignesMelanom),
                Boolean.toString(ueberweisenderArztBasalzellkarzinom),
                Boolean.toString(ueberweisenderArztSpinozellulaeresKarzinom),
                Boolean.toString(ueberweisenderArztAndererHautkrebs),
                Boolean.toString(dermatologeVerdachtsdiagnose),
                Boolean.toString(dermatologeMalignesMelanom),
                Boolean.toString(dermatologeBasalzellkarzinom),
                Boolean.toString(dermatologeSpinozellulaeresKarzinom),
                Boolean.toString(dermatologeAndererHautkrebs),
                Boolean.toString(dermatologeSonstigerBiopsieBefund),
                Boolean.toString(biopsieOderExzision),
                Integer.toString(anzahlBiopsien),
                Boolean.toString(anderweitigeTherapie),
                Boolean.toString(keineWeitereTherapie),
                Boolean.toString(histopathologieMalignesMelanom),
                melanomKlassifikation != null ? melanomKlassifikation : "",
                melanomBreslow != null ? melanomBreslow : "",
                Boolean.toString(histopathologieBasalzellkarzinom),
                basalzellHorizontal != null ? basalzellHorizontal.toString() : "",
                basalzellVertikal != null ? basalzellVertikal.toString() : "",
                Boolean.toString(histopathologieSpinozellulaeresKarzinom),
                spinozellulaeresKlassifikation != null ? spinozellulaeresKlassifikation : "",
                spinozellulaeresGrading != null ? spinozellulaeresGrading : "",
                Boolean.toString(histopathologieAndererHautkrebs),
                Boolean.toString(histopathologieAtypischerNaevus),
                Boolean.toString(histopathologieJunktionalerNaevus),
                Boolean.toString(histopathologieAktinischeKeratose),
                Boolean.toString(histopathologieAndereVeränderung)
        );
    }

    private final UUID id = UUID.randomUUID();

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ScreeningData)) {
            return false;
        }
        ScreeningData that = (ScreeningData) o;
        return patient != null && that.patient != null
                && patient.getVersichertenId() != null && that.patient.getVersichertenId() != null
                && patient.getVersichertenId().equals(that.patient.getVersichertenId())
                && java.util.Objects.equals(untersuchungsdatum, that.untersuchungsdatum);
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(
                patient != null ? patient.getVersichertenId() : null,
                untersuchungsdatum
        );
    }
}