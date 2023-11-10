import java.util.Scanner;
import java.util.regex.Pattern;

public class ParcialLabIgnacioAriza {

    // Función para verificar si la fila del ADN es válida
    public static boolean dnaCheck(String dna) {
        String notAtcg = "bdefhijklmniopqrsuvwxyz";

        if (Pattern.matches("[a-zA-Z]{6}", dna)) {
            for (int i = 0; i < notAtcg.length(); i++) {
                for (int j = 0; j < dna.length(); j++) {
                    if (Character.toLowerCase(notAtcg.charAt(i)) == Character.toLowerCase(dna.charAt(j))) {
                        System.out.println(Character.toUpperCase(dna.charAt(j)) + " no es una letra válida.");
                        return false;
                    }
                }
            }
            return true;
        } else {
            return false;
        }
    }
    // Función para verificar si el ADN es mutante
    public static boolean isMutant(String[] dna) {
        int winnerCount = 0;

        // Checkeo horizontal
        for (int i = 0; i < 6; i++) {
            char lastLetter = ' ';
            int chain = 1;
            boolean winner = false;
            for (int j = 0; j < 6; j++) {
                if (dna[i].charAt(j) == lastLetter) {
                    chain++;
                    if (chain >= 4) {
                        winner = true;
                    }
                } else {
                    chain = 1;
                }
                lastLetter = dna[i].charAt(j);
            }
            if (winner) {
                winnerCount++;
            }
        }

        // Checkeo vertical
        for (int i = 0; i < 6; i++) {
            char lastLetter = ' ';
            int chain = 1;
            boolean winner = false;
            for (int j = 0; j < 6; j++) {
                if (dna[j].charAt(i) == lastLetter) {
                    chain++;
                    if (chain >= 4) {
                        winner = true;
                    }
                } else {
                    chain = 1;
                }
                lastLetter = dna[j].charAt(i);
            }
            if (winner) {
                winnerCount++;
            }
        }

        // Checkeo diagonal
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 6 - i; j++) {
                char lastLetter = ' ';
                int chain = 1;
                boolean winner = false;
                for (int k = 0; k < 6 - i; k++) {
                    if (dna[k].charAt(k + i) == lastLetter) {
                        chain++;
                        if (chain >= 4) {
                            winner = true;
                        }
                    } else {
                        chain = 1;
                    }
                    lastLetter = dna[k].charAt(k + i);
                }
                if (winner) {
                    winnerCount++;
                }
            }
        }

        // Checkeo diagonal inverso
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 6 - i; j++) {
                char lastLetter = ' ';
                int chain = 1;
                boolean winner = false;
                for (int k = 0; k < 6 - i; k++) {
                    if (dna[5 - k].charAt(k + i) == lastLetter) {
                        chain++;
                        if (chain >= 4) {
                            winner = true;
                        }
                    } else {
                        chain = 1;
                    }
                    lastLetter = dna[5 - k].charAt(k + i);
                }
                if (winner) {
                    winnerCount++;
                }
            }
        }

        return winnerCount > 1;
    }

    public static void main(String[] args) {
        String[] dna = new String[6];
        Scanner scanner = new Scanner(System.in);
        boolean done = false;

        while (!done) {
            // Ingreso de datos
            for (int i = 0; i < 6; i++) {
                boolean dnaValid = false;
                while (!dnaValid) {
                    System.out.println("Ingrese la siguiente fila del ADN:");
                    String dnaInput = scanner.nextLine();
                    dnaValid = dnaCheck(dnaInput);
                    if (!dnaValid) {
                        System.out.println("Por favor ingrese una fila de ADN válida.");
                        System.out.println("Una fila válida tiene 6 caracteres y solo contiene A, C, G y T.");
                    } else {
                        dna[i] = dnaInput.toUpperCase();
                    }

                }
            }
            // Resultado
            if (isMutant(dna)) {
                System.out.println("El ADN ingresado es mutante.");
            } else {
                System.out.println("El ADN ingresado no es mutante.");
            }

            // Menu para repetir o salir
            boolean validMenu = false;
            while (!validMenu) {
                System.out.println("Ingrese 1 si quiere revisar otro ADN o 2 si quiere terminar.");
                String menu = scanner.nextLine();
                if (menu.matches("\\d+")) {
                    int choice = Integer.parseInt(menu);
                    if (choice == 1 || choice == 2) {
                        validMenu = true;
                        done = choice == 2;
                    } else {
                        System.out.println("Ingrese 1 o 2.");
                    }
                } else {
                    System.out.println("Ingrese 1 o 2.");
                }
            }
        }
    }
}

// Casos de prueba
// adn = ["GCATCG","ATCAAA","CGTAGA","GTCAGA","TAGCAA","GCTAGT"] Este adn no es mutante
// adn = ["ATGCTA","CAGTTC","TTATTT","AGGATC","GGGGAC","TGAACG"] Este adn es mutante

