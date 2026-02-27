
package zahlenumwandlung;

public class Dual {
    private String wert;

    // Konstruktor
    public Dual(String wert) {
        if (!istGueltigeDualZahl(wert)) {
            throw new IllegalArgumentException("Ungültige Dualzahl!");
        }
        this.wert = wert;
    }

    // Getter
    public String getWert() {
        return wert;
    }

    // Validierung
    private boolean istGueltigeDualZahl(String wert) {
        return wert != null && wert.matches("[01]+");
    }

    // Umwandlung in Decimal
    public Decimal toDecimal() {
        int dezimal = Integer.parseInt(wert, 2);
        return new Decimal(String.valueOf(dezimal));
    }
}
