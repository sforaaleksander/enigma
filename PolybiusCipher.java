import java.util.Scanner;

class PolybiusCipher {
    private static char[] theMessage;
    private static String enciphered = "";
    private static String deciphered = "";
    private static int TABLESIZE = 6;
    private static char[][] table = new char[TABLESIZE][TABLESIZE];
    private static char[] ALPHA = { '0', 'A', 'B', 'C', 'D', 'E' };
    private static char[] theKey;

    public static void polybius(String userMode, String userKey) {
        initialize(userKey);
        printTable();
        if (userMode.equals("-E")) {
            enciphered = encipher();
            System.out.println(enciphered);
        } else if (userMode.equals("-D")) {

        }
    }

    private static String gatherInput() {
        Scanner scan = new Scanner(System.in);
        String userString = scan.next().toUpperCase();
        scan.close();
        return userString;
    }

    private static void initialize(String userKey) {
        String userString = gatherInput();
        theMessage = userString.toCharArray();
        theKey = userKey.toCharArray();
        table = createTable();

    }

    private static char[][] createTable() {
        table[0][0] = '#';
        for (int i = 1; i < TABLESIZE; i++) {
            table[0][i] = ALPHA[i];
            table[i][0] = ALPHA[i];
        }
        int index = 0;
        for (int i = 1; i < TABLESIZE; i++) {
            for (int j = 1; j < TABLESIZE; j++) {
                table[i][j] = theKey[index];
                index += 1;
            }
        }
        return table;
    }

    private static void printTable() {
        for (int i = 0; i < TABLESIZE; i++) {
            for (int j = 0; j < TABLESIZE; j++) {
                System.out.print(table[i][j]);
            }
            System.out.println("");
        }
    }

    private static String encipher() {
        for (char letter : theMessage) {
            for (int i = 1; i < TABLESIZE; i++) {
                for (int j = 1; j < TABLESIZE; j++) {
                    if (table[i][j] == letter) {
                        enciphered += table[i][0];
                        enciphered += table[0][j];
                    }
                }
            }
        }
        return enciphered;
    }

    private static String decipher() {
        int column;
        int row;
        int count = 0;
        // for (int m = 0; m < theMessage.length; m++)
        //     for (int i = 0; i < TABLESIZE; i++) {
        //         if (table[i][0] == theMessage[m] && count == 0) {
        //             row = i;
        //         } else if (table[0][i] == theMessage[m] && count == 1) {
        //             column = i;
        //             deciphered += table[row][column];
        //         }
        //     }

        for (int m = 0; m < theMessage.length / 2; m++){
            for (int i = 0; i < TABLESIZE; i++) {
                if (table[i][0] == theMessage[m])
        }
    }

        return deciphered;
    }
}
