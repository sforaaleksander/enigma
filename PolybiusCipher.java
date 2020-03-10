import java.util.Scanner;

class PolybiusCipher {
    public static void polybius(String userMode, String userKey) {
        Scanner scan = new Scanner(System.in);
        String userString = scan.next().toUpperCase();
        char[] toEncrypt = userString.toCharArray();
        String ciphered = "";
        char[] userKeyList = userKey.toCharArray();
        char[][] table = new char[6][6];
        table[0][0] = '#';
        char[] alfa = { '0', 'A', 'B', 'C', 'D', 'E' };
        scan.close();
        for (int i = 1; i < 6; i++) {
            table[0][i] = alfa[i];
            table[i][0] = alfa[i];
        }
        int index = 0;
        for (int i = 1; i < 6; i++) {
            for (int j = 1; j < 6; j++) {
                table[i][j] = userKeyList[index];
                index += 1;
            }
        }

        for (char letter : toEncrypt) {
            for (int i = 1; i < 6; i++) {
                for (int j = 1; j < 6; j++) {
                    if (table[i][j] == letter) {
                        ciphered += table[i][0];
                        ciphered += table[0][j];
                    }
                }
            }
        }

        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                System.out.print(table[i][j]);
            }
            System.out.println("");
        }
        System.out.println(ciphered);
    }
}