public class Vollmond {
    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        int gefunden = 0;
        int jahr = 2025;
        int tag = 1;
        int tageBisVollmond = 29;

        String[] monate = {
            "Januar", "Februar", "Maerz", "April", "Mai", "Juni",
            "Juli", "August", "September", "Oktober", "November", "Dezember"
        };

        int[] tageImMonat = {31,28,31,30,31,30,31,31,30,31,30,31};
        int[] vollmonde = new int[12];

        while (gefunden < n) {
            // Schaltjahr prüfen
            if ((jahr % 4 == 0 && jahr % 100 != 0) || (jahr % 400 == 0)) {
                tageImMonat[1] = 29;
            } else {
                tageImMonat[1] = 28;
            }

            for (int i = 0; i < 12; i++) {
                while (tag <= tageImMonat[i]) {
                    vollmonde[i]++;
                    tag += tageBisVollmond;
                }
                if (vollmonde[i] >= 2 && gefunden < n) {
                    System.out.println(jahr + ", " + monate[i]);
                    gefunden++;
                }
                tag = tag - tageImMonat[i];
                if (tag <= 0) {
                    tag = 1;
                }
            }
            vollmonde = new int[12];
            jahr++;
        }
    }
}


