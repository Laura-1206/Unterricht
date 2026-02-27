package zahlenumwandlung;
import java.util.Scanner;
public class Main {
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while(true){
        System.out.println("=== Zahlensystem Konverter ===");
        System.out.println("1 - Dual (Binär) zu Decimal");
        System.out.println("2 - Decimal zu Dual");
        System.out.println("3 - Programm beenden");
        System.out.print("Auswahl: ");

        int auswahl = scanner.nextInt();
        scanner.nextLine(); // Buffer leeren
        
        try {
            switch (auswahl) {

                case 1:
                    System.out.print("Dualzahl eingeben: ");
                    String dualInput = scanner.nextLine();

                    Dual dual = new Dual(dualInput);
                    Decimal dezimalErgebnis = dual.toDecimal();

                    System.out.println("Decimal: " + dezimalErgebnis.getWert());
                    break;

                case 2:
                    System.out.print("Decimalzahl eingeben: ");
                    String decimalInput = scanner.nextLine();

                    Decimal decimal = new Decimal(decimalInput);
                    Dual dualErgebnis = decimal.toDual();

                    System.out.println("Dual: " + dualErgebnis.getWert());
                    break;
                case 3:
                    System.out.println("Das Programm wird jetzt beendet");
                    return;

                default:
                    System.out.println("Ungültige Auswahl.");
            }

        } catch (IllegalArgumentException e) {
            System.out.println("Fehler: " + e.getMessage());
        }

    }
    }
}
