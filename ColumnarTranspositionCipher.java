import java.util.Scanner;

class ColumnarTranspositionCipher {
    private static String enciphered = "";
    private static String deciphered = "";
    private static char[] ALPHABET = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M',
                                        'N', 'O', 'P', 'Q', 'R','S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };
    private static int rowsNum;
    private static char[] theMessage;
    private static char[] theKey;
    private static char[][] table;
    private static char[][] sortedColumns;
    private static char[][] transpositionedColumns;

    public static void columnarTransposition(String userMode, String userKey) {
        initialize(userKey);

        transpositionedColumns = transpositionColumns();
        sortedColumns = sortColumns(transpositionedColumns);
        printTables();
        enciphered = collectLetters();
        System.out.println(" ");
        System.out.println("FINAL RESULT");
        System.out.println(enciphered);
    }

    private static void initialize(String userKey) {
        theKey = userKey.toCharArray();
        gatherInput();
        rowsNum = (int) Math.ceil(theMessage.length / theKey.length) + 1;
        createTable();
    }

    private static void gatherInput() {
        Scanner scan = new Scanner(System.in);
        String userString = scan.next().toUpperCase();
        if (userString.length() % theKey.length != 0) {
            int missing = theKey.length - (userString.length() % theKey.length);
            for (int i = 0; i < missing; i++) {
                userString += "X";
            }
        }
        theMessage = userString.toCharArray();
        scan.close();
    }

    private static void createTable() {
        table = new char[rowsNum][theKey.length];
        for (int j = 0; j < theKey.length; j++) {
            table[0][j] = theKey[j];
        }
        int index = 0;
        for (int i = 1; i < rowsNum; i++) {
            for (int j = 0; j < theKey.length; j++) {
                table[i][j] = theMessage[index];
                index += 1;
            }
        }
    }

    private static void printTables() {
        System.out.println(" ");
        System.out.println("REGULAR TABLE");
        for (int i = 0; i < rowsNum; i++) {
            for (int j = 0; j < theKey.length; j++) {
                System.out.print(table[i][j]);
            }
            System.out.println("");
        }
        System.out.println(" ");
        System.out.println("TRANSPOSITIONED COLUMNS");
        for (int i = 0; i < theKey.length; i++) {
            for (int j = 0; j < rowsNum; j++) {
                System.out.print(transpositionedColumns[i][j]);
            }
            System.out.println("");
        }
        System.out.println(" ");
        System.out.println("SORTED COLUMNS");
        for (int i = 0; i < theKey.length; i++) {
            for (int j = 0; j < rowsNum; j++) {
                System.out.print(sortedColumns[i][j]);
            }
            System.out.println("");
        }
    }

    private static char[][] transpositionColumns() {
        char[][] transpositionedColumns = new char[theKey.length][rowsNum];
        for (int j = 0; j < theKey.length; j++) {
            for (int i = 0; i < rowsNum; i++) {
                transpositionedColumns[j][i] += table[i][j];
            }
        }
        return transpositionedColumns;
    }

    private static char[][] sortColumns(char[][] transpositionedColumns) {
        sortedColumns = new char[theKey.length][rowsNum];
        int index = 0;
        for (char letter : ALPHABET) {
            for (int i = 0; i < theKey.length; i++) {
                if (transpositionedColumns[i][0] == letter) {
                    sortedColumns[index] = transpositionedColumns[i];
                    index += 1;
                }
            }
        }
        return sortedColumns;
    }

    private static String collectLetters() {
        String finalCipher = "";
        for (int i = 0; i < theKey.length; i++) {
            for (int j = 1; j < rowsNum; j++) {
                finalCipher += sortedColumns[i][j];
            }
        }
        return finalCipher;
    }
}
