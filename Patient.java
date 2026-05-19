package com.mycompany.ehks;

import java.time.LocalDate;

public class Patient {
    private String name;
    private String vorname;
    private LocalDate geburtsdatum;
    private String versichertenId;
    private String strasse;
    private String hausnummer;
    private String plz;
    private String ort;
    private String versichertenArt;
    private String geschlecht;
    private String betriebsstaettenNr;
    private String arztnummer;
    private String arztname;

    public Patient() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVorname() {
        return vorname;
    }

    public void setVorname(String vorname) {
        this.vorname = vorname;
    }

    public LocalDate getGeburtsdatum() {
        return geburtsdatum;
    }

    public void setGeburtsdatum(LocalDate geburtsdatum) {
        this.geburtsdatum = geburtsdatum;
    }

    public String getVersichertenId() {
        return versichertenId;
    }

    public void setVersichertenId(String versichertenId) {
        this.versichertenId = versichertenId;
    }

    public String getStrasse() {
        return strasse;
    }

    public void setStrasse(String strasse) {
        this.strasse = strasse;
    }

    public String getHausnummer() {
        return hausnummer;
    }

    public void setHausnummer(String hausnummer) {
        this.hausnummer = hausnummer;
    }

    public String getPlz() {
        return plz;
    }

    public void setPlz(String plz) {
        this.plz = plz;
    }

    public String getOrt() {
        return ort;
    }

    public void setOrt(String ort) {
        this.ort = ort;
    }

    public String getVersichertenArt() {
        return versichertenArt;
    }

    public void setVersichertenArt(String versichertenArt) {
        this.versichertenArt = versichertenArt;
    }

    public String getGeschlecht() {
        return geschlecht;
    }

    public void setGeschlecht(String geschlecht) {
        this.geschlecht = geschlecht;
    }

    public String getBetriebsstaettenNr() {
        return betriebsstaettenNr;
    }

    public void setBetriebsstaettenNr(String betriebsstaettenNr) {
        this.betriebsstaettenNr = betriebsstaettenNr;
    }

    public String getArztnummer() {
        return arztnummer;
    }

    public void setArztnummer(String arztnummer) {
        this.arztnummer = arztnummer;
    }

    public String getArztname() {
        return arztname;
    }

    public void setArztname(String arztname) {
        this.arztname = arztname;
    }

    public String getPatientName() {
        return name != null ? name + (vorname != null ? ", " + vorname : "") : "Unbekannt";
    }

    public String getGeburtsdatumString() {
        return geburtsdatum != null ? geburtsdatum.toString() : "";
    }

    public boolean isValid() {
        return com.mycompany.ehks.Validator.isValidPatient(
                name, vorname, getGeburtsdatumString(),
                versichertenId, strasse, hausnummer, plz, ort,
                versichertenArt, geschlecht,
                betriebsstaettenNr, arztnummer, arztname
        );
    }

    @Override
    public String toString() {
        return String.join(";",
                name, vorname, getGeburtsdatumString(),
                versichertenId, strasse, hausnummer, plz, ort,
                versichertenArt, geschlecht,
                betriebsstaettenNr, arztnummer, arztname
        );
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Patient)) return false;
        Patient p = (Patient) o;
        return versichertenId != null && versichertenId.equals(p.versichertenId);
    }

    @Override
    public int hashCode() {
        return versichertenId != null ? versichertenId.hashCode() : 0;
    }
}