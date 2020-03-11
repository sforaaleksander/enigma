import java.util.Scanner;

class BeaufortCipher {
    private static String ciphered = "";
    private static int tableSize = 27;
    private static char[] theMessage;
    private static char[] alphabet = { '0', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M',
                                       'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };
    private static char[][] table = new char[tableSize][tableSize];
    private static char[] theKey;
    private static char[] keyBase;


    public static void beaufort(String userKey, String userMode) {
        keyBase = userKey.toCharArray();
        String userMessage = gatherInput();
        theMessage = userMessage.toCharArray();
        String keyString = createKeyString();
        theKey = keyString.toCharArray();
        table = createTable();
        printTabulaRecta();
        ciphered = encipher();
        System.out.println("\nFINAL");
        System.out.println(ciphered);
    }


    private static String createKeyString(){
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

    private static void printTabulaRecta(){
        for (int i = 0; i < tableSize; i++) {
            for (int j = 0; j < tableSize; j++) {
                System.out.print(table[i][j]);
            }
            System.out.println("");
        }
    }

    private static String gatherInput(){
        Scanner scan = new Scanner(System.in);
        String userMessage = scan.next().toUpperCase();
        scan.close();
        return userMessage;
    }

    private static char[][] createTable(){
        table[0][0] = '#';
        for (int i = 1; i < tableSize; i++) {
            table[0][i] = alphabet[i];
            table[i][0] = alphabet[i];
        }
        int index = 0;
        for (int i = 1; i < tableSize; i++) {
            for (int j = 1; j < tableSize; j++) {
                int shift = index + j;
                if (shift > 26) {
                    shift -= 26;
                }
                table[i][j] = alphabet[shift];
            }
            index += 1;
        }
        return table;
    }

    private static String encipher(){
        for (int m = 0; m < theMessage.length; m++) {
            for (int i = 0; i < tableSize; i++) {
                if (table[0][i] == theMessage[m]) {
                    for (int j = 0; j < tableSize; j++) {
                        if (table[j][i] == theKey[m]) {
                            ciphered += table[j][0];
                        }
                    }
                }
            }
        }
    return ciphered;
    }
}