public class FlugSimulator {
    public static void main(String[] args) {
        int anzahlFluege = Integer.parseInt(args[0]);
        
        int plaetze = 75;
        int tickets = 78;
        double erscheinenWahrscheinlichkeit = 0.92;
        
        int ueberbuchungen = 0;
        int gesamtPassagiere = 0;
        
        for (int i = 0; i < anzahlFluege; i++) {
            int erschienen = 0;
            
            for (int j = 0; j < tickets; j++) {
                if (Math.random() < erscheinenWahrscheinlichkeit) {
                    erschienen++;
                }
            }
            
            if (erschienen > plaetze) {
                ueberbuchungen++;
            }
            
            gesamtPassagiere += erschienen;
        }
        
        double ueberbuchungsProzent = ((double) ueberbuchungen / anzahlFluege) * 100;
        double durchschnitt = (double) gesamtPassagiere / anzahlFluege;
        
        System.out.printf("Überbuchungen: %d (%.2f%%)\n", ueberbuchungen, ueberbuchungsProzent);
        System.out.printf("Mittelwert: %.1f\n", durchschnitt);
    }
}

