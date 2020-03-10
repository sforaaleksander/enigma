import java.util.Scanner;

class ColumnarTranspositionCipher {
    public static void columnarTransposition(String userMode, String userKey) {
        Scanner scan = new Scanner(System.in);
        String userString = scan.next().toUpperCase();

        if (userString.length() % userKey.length() != 0) {
            int missing = userKey.length() - (userString.length() % userKey.length());
            for (int i = 0; i < missing; i++) {
                userString += "X";
            }
        }
        char[] toEncrypt = userString.toCharArray();
        String ciphered = "";
        char[] userKeyList = userKey.toCharArray();
        int rowsNum = (int) Math.ceil(toEncrypt.length / userKey.length()) + 1;

        char[][] table = new char[rowsNum][userKey.length()];

        for (int j = 0; j < userKey.length(); j++) {
            table[0][j] = userKeyList[j];
        }

        int index = 0;
        for (int i = 1; i < rowsNum; i++) {
            for (int j = 0; j < userKey.length(); j++) {
                table[i][j] = toEncrypt[index];
                index += 1;
            }
        }

        char[][] transpositionedColumns = new char[userKey.length()][rowsNum];

        for (int j = 0; j < userKey.length(); j++) {
            for (int i = 0; i < rowsNum; i++) {
                transpositionedColumns[j][i] += table[i][j];
            }
        }

        System.out.println(" ");
        System.out.println("REGULAR TABLE");
        for (int i = 0; i < rowsNum; i++) {
            for (int j = 0; j < userKey.length(); j++) {
                System.out.print(table[i][j]);
            }
            System.out.println("");
        }

        System.out.println(" ");
        System.out.println("TRANSPOSITIONED COLUMNS");
        for (int i = 0; i < userKey.length(); i++) {
            for (int j = 0; j < rowsNum; j++) {
                System.out.print(transpositionedColumns[i][j]);
            }
            System.out.println("");
        }

        scan.close();

        char[][] sortedColumns = sortColumns(transpositionedColumns, userKey, rowsNum, toEncrypt);

        System.out.println(" ");
        System.out.println("SORTED COLUMNS");
        for (int i = 0; i < userKey.length(); i++) {
            for (int j = 0; j < rowsNum; j++) {
                System.out.print(sortedColumns[i][j]);
            }
            System.out.println("");
        }

        ciphered = collectLetters(sortedColumns, userKey, rowsNum);
        System.out.println(" ");
        System.out.println("FINAL RESULT");
        System.out.println(ciphered);
    }

    private static char[][] sortColumns(char[][] transpositionedColumns, String userKey, int rowsNum,
            char[] toEncrypt) {

        char[][] sortedColumns = new char[userKey.length()][rowsNum];
        char[] alphabet = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R',
                'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };
        int index = 0;
        for (char letter : alphabet) {
            for (int i = 0; i < userKey.length(); i++) {
                if (transpositionedColumns[i][0] == letter) {
                    sortedColumns[index] = transpositionedColumns[i];
                    index += 1;
                }
            }
        }
        return sortedColumns;
    }

    private static String collectLetters(char[][] sortedColumns, String userKey, int rowsNum) {
        String finalCipher = "";
        for (int i = 0; i < userKey.length(); i++) {
            for (int j = 1; j < rowsNum; j++) {
                finalCipher += sortedColumns[i][j];
            }
        }
        return finalCipher;
    }
}
