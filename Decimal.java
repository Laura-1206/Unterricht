package zahlenumwandlung;


public class Decimal {
    private String wert;

    // Konstruktor
    public Decimal(String wert) {
        if (!istGueltigeDecimalZahl(wert)) {
            throw new IllegalArgumentException("Ungültige Decimalzahl!");
        }
        this.wert = wert;
    }

    // Getter
    public String getWert() {
        return wert;
    }

    // Validierung
    private boolean istGueltigeDecimalZahl(String wert) {
        return wert != null && wert.matches("\\d+");
    }

    // Umwandlung in Dual
    public Dual toDual() {
        int dezimal = Integer.parseInt(wert);
        String dualString = Integer.toBinaryString(dezimal);
        return new Dual(dualString);
    }
}
