import java.util.Scanner;


class RailfenceCipher {
    private static String theMessage;
    private static String userKey;
    private static String enciphered = "";
    private static String deciphered = "";

    private static int row;
    private static int col;
    private static char[][] table; 


    public static void railfence(String userMode, String userKey) {
        if (userKey.equals("0")){
            userKey = "3";
        }

        theMessage = gatherInput();
        row = Integer.parseInt(userKey);
        col = theMessage.length();
        table = new char[row][col];


        if (userMode.equals("-E")) {
            enciphered = railfenceEncrypt(theMessage);
            System.out.println(" ");
            System.out.println("ENCIPHERED: " + enciphered);
        } else
            deciphered = railfenceDecrypt(theMessage);
            System.out.println(" ");
            railfenceDecrypt("DECIPHERED: " + theMessage);

    }

    private static String railfenceEncrypt(String theMessage) {
        boolean direction = false;
        int j = 0;
        for (int i = 0; i < col; i++) {
            if (j == 0 || j == row - 1)
                direction = !direction;
            table[j][i] = theMessage.charAt(i);
            if (direction)
                j++;
            else
                j--;
        }
        for (int i = 0; i < row; i++) {
            for (int k = 0; k < col; k++) {
                if (table[i][k] != 0) {
                    enciphered += table[i][k];
                }
            }
        }
        return enciphered;
    }

    private static String railfenceDecrypt(String theMessage) {
        boolean direction = false;
        int j = 0;
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
                    table[i][k] = theMessage.charAt(index++);
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
        return deciphered;

    }

    private static String gatherInput(){
        Scanner scan = new Scanner(System.in);
        String userString = scan.next().toUpperCase();
        System.out.println("ciphering: " + userString);
        scan.close();
        return userString;
        }
}