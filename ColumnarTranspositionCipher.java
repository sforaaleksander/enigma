import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;

class ColumnarTranspositionCipher {
    private static String enciphered = "";
    private static String deciphered = "";
    private static char[] ALPHABET = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P',
            'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };
    private static int rowsNum;
    private static char[] theMessage;
    private static char[] theKey;
    private static char[] sortedKey;
    private static char[][] table;
    private static char[][] decipherTable;
    private static char[][] sortedColumns;
    private static char[][] transpositionedColumns;
    private static int[] order;

    public static void columnarTransposition(String userMode, String userKey) {
        initialize(userKey);
        if (userMode.equals("-E")) {
            createTable();
            encipher();
        } else if (userMode.equals("-D")) {
            decipher();
        }

    }

    private static void initialize(String userKey) {
        theKey = userKey.toCharArray();
        gatherInput();
        rowsNum = (int) Math.ceil(theMessage.length / theKey.length) + 1;
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

    private static void printRegularTable(char[][] tableToPrint) {
        System.out.println(" ");
        for (int i = 0; i < rowsNum; i++) {
            for (int j = 0; j < theKey.length; j++) {
                System.out.print(tableToPrint[i][j]);
            }
            System.out.println("");
        }

    }

    private static void printModifiedTable(char[][] tableToPrint) {
        System.out.println(" ");
        System.out.println("TRANSPOSITIONED COLUMNS");
        for (int i = 0; i < theKey.length; i++) {
            for (int j = 0; j < rowsNum; j++) {
                System.out.print(tableToPrint[i][j]);
            }
            System.out.println("");
        }
    }

    private static char[][] transpositionColumns(char[][] tableToTranspose) {
        transpositionedColumns = new char[theKey.length][rowsNum];
        for (int j = 0; j < theKey.length; j++) {
            for (int i = 0; i < rowsNum; i++) {
                transpositionedColumns[j][i] += tableToTranspose[i][j];
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

    private static void encipher() {
        transpositionedColumns = transpositionColumns(table);
        sortedColumns = sortColumns(transpositionedColumns);
        printRegularTable(table);
        printModifiedTable(table);
        enciphered = collectLetters();
        System.out.println(enciphered);
    }

    private static void decipher() {
        createSortedKey();
        System.out.println(sortedKey);
        System.out.println(findOrder());
        createTableDecipher();
        transpositionedColumns = transpositionColumns(decipherTable);
        sortedColumns = sortColumnsDecipher(transpositionedColumns);
        printRegularTable(decipherTable);
        printModifiedTable(transpositionedColumns);
        printModifiedTable(sortedColumns);
        deciphered = readDecipher();
        System.out.println(deciphered);

    }

    private static void createSortedKey() {
        String keyString = "";
        for (char alpha : ALPHABET) {
            for (char letter : theKey) {
                if (alpha == letter) {
                    keyString += letter;
                }
            }
        }
        sortedKey = keyString.toCharArray();
    }

    private static void createTableDecipher() {
        decipherTable = new char[rowsNum][theKey.length];
        for (int i = 0; i < sortedKey.length; i++) {
            decipherTable[0][i] = sortedKey[i];
        }
        int index = 0;
        for (int i = 0; i < theKey.length; i++) {
            for (int j = 1; j < rowsNum; j++) {
                decipherTable[j][i] = theMessage[index];
                index += 1;
            }
        }
    }

    private static int[] findOrder() {
        List<Integer> orderList = new ArrayList<Integer>();
        for (char letter : theKey) {
            for (int i = 0; i < theKey.length; i++) {
                if (sortedKey[i] == letter) {
                    orderList.add(i);
                }
            }
        }
        order = new int[orderList.size()];
        for (int i = 0; i < order.length; i++) {
            order[i] = orderList.get(i).intValue();
        }
        return order;
    }

    private static char[][] sortColumnsDecipher(char[][] decipherTable) {
        sortedColumns = new char[theKey.length][rowsNum];
        for (int i = 0; i < order.length; i++) {
            sortedColumns[i] = decipherTable[order[i]];
        }
        return sortedColumns;
    }

    private static String readDecipher() {
        for (int i = 1; i < rowsNum; i++) {
            for (int j = 0; j < theKey.length; j++) {
                deciphered += sortedColumns[j][i];
            }
        }
        return deciphered;
    }
}
