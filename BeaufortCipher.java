import java.util.Scanner;

class BeaufortCipher {
    private static String enciphered = "";
    private static String deciphered = "";
    private static int TABLESIZE = 27;
    private static char[] theMessage;
    private static char[] ALPHABET = { '0', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O',
            'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };
    private static char[][] table;
    private static char[] theKey;
    private static char[] keyBase;

    public static void beaufort(String userKey, String userMode) {
        if (userKey.equals("0")){
            userKey = "FORTIFICATION";
        }
        initialize(userKey);
        printTabulaRecta();
        if (userMode.equals("-E")) {
            System.out.println(" ");
            enciphered = encipher();
            System.out.println("ENCIPHERED: " + enciphered);
        } else if (userMode.equals("-D")) {
            System.out.println(" ");
            deciphered = decipher();
            System.out.println("DECIPHERED: " + deciphered);
        }
    }

    private static void initialize(String userKey){
        keyBase = userKey.toCharArray();
        String userMessage = gatherInput();
        theMessage = userMessage.toCharArray();
        String keyString = createKeyString();
        theKey = keyString.toCharArray();
        table = createTable();
        }

    private static String createKeyString() {
        int letterShift = 0;
        String keyString = "";
        for (int i = 0; i < theMessage.length; i++) {
            if (letterShift == keyBase.length) {
                letterShift = 0;
            }
            keyString += keyBase[letterShift];
            letterShift += 1;
        }
        return keyString;
    }

    private static void printTabulaRecta() {
        System.out.println("");
        for (int i = 0; i < TABLESIZE; i++) {
            for (int j = 0; j < TABLESIZE; j++) {
                System.out.print(table[i][j]);
            }
            System.out.println("");
        }
        System.out.println("");
    }

    private static String gatherInput() {
        System.out.println("ENTER YOUR MESSAGE: ");
        Scanner scan = new Scanner(System.in);
        String userMessage = scan.next().toUpperCase();
        scan.close();
        return userMessage;
    }

    private static char[][] createTable() {
        table = new char[TABLESIZE][TABLESIZE];
        table[0][0] = '#';
        for (int i = 1; i < TABLESIZE; i++) {
            table[0][i] = ALPHABET[i];
            table[i][0] = ALPHABET[i];
        }
        int index = 0;
        for (int i = 1; i < TABLESIZE; i++) {
            for (int j = 1; j < TABLESIZE; j++) {
                int shift = index + j;
                if (shift > 26) {
                    shift -= 26;
                }
                table[i][j] = ALPHABET[shift];
            }
            index += 1;
        }
        return table;
    }

    private static String encipher() {
        for (int m = 0; m < theMessage.length; m++) {
            for (int i = 1; i < TABLESIZE; i++) {
                if (table[0][i] == theMessage[m]) {
                    for (int j = 1; j < TABLESIZE; j++) {
                        if (table[j][i] == theKey[m]) {
                            enciphered += table[j][0];
                        }
                    }
                }
            }
        }
        return enciphered;
    }

    private static String decipher() {
        for (int m = 0; m < theMessage.length; m++) {
            for (int i = 1; i < TABLESIZE; i++) {
                if (table[i][0] == theMessage[m]) {
                    for (int j = 1; j < TABLESIZE; j++) {
                        if (table[i][j] == theKey[m]) {
                            deciphered += table[0][j];
                        }
                    }
                }
            }
        }
        return deciphered;
    }
}