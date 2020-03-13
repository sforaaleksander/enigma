import java.util.Scanner;


class RailfenceCipher {

    public static void railfence(String userMode, String userKey) {
        if (userKey.equals("0")){
            userKey = "3";
        }
        Scanner scan = new Scanner(System.in);
        String userString = scan.next().toUpperCase();
        System.out.println("ciphering: " + userString);
        scan.close();
        if (userMode.equals("-E")) {
            railfenceEncrypt(userKey, userString);
        } else
            railfenceDecrypt(userKey, userString);

    }

    public static void railfenceEncrypt(String userKey, String userString) {
        boolean direction = false;
        String ciphered = "";
        int j = 0;
        int row = Integer.parseInt(userKey);
        int col = userString.length();
        char[][] table = new char[row][col];
        for (int i = 0; i < col; i++) {
            if (j == 0 || j == row - 1)
                direction = !direction;
            table[j][i] = userString.charAt(i);
            if (direction)
                j++;
            else
                j--;
        }
        for (int i = 0; i < row; i++) {
            for (int k = 0; k < col; k++) {
                if (table[i][k] != 0) {
                    ciphered += table[i][k];
                }
            }
        }
        System.out.println(ciphered);
    }

    public static void railfenceDecrypt(String userKey, String userString) {
        boolean direction = false;
        int j = 0;
        int row = Integer.parseInt(userKey);
        int col = userString.length();
        char[][] table = new char[row][col];
        String deciphered = "";
        for (int i = 0; i < col; i++) {
            if (j == 0 || j == row - 1)
                direction = !direction;

            table[j][i] = '#';

            if (direction)
                j++;
            else
                j--;
        }
        int index = 0;
        direction = false;
        for (int i = 0; i < row; i++) {
            for (int k = 0; k < col; k++) {
                if (table[i][k] == '#' && index < col) {
                    table[i][k] = userString.charAt(index++);
                }
            }
        }
        j = 0;
        for (int i = 0; i < col; i++) {
            if (j == 0 || j == row - 1)
                direction = !direction;

            deciphered += table[j][i];

            if (direction)
                j++;
            else
                j--;
        }
        System.out.println(deciphered);

    }
}