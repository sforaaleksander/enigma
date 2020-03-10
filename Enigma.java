import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.List;
import java.util.Collections;
import java.util.LinkedList;

class Enigma {
    public static void main(String[] args) {
        try {
            if (args.length < 2) {
                throw new ArrayIndexOutOfBoundsException("Not enough arguments!");
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Please provide valid arguments: [-D/-E] [CIPHER NAME]");
            e.printStackTrace();
        }
        String userMode = args[0].toUpperCase();
        String userCipher = args[1].toUpperCase();
        if (args.length == 3) {
            String userKey = args[2].toUpperCase();
            cipherChoice(userCipher, userMode, userKey);

        } else {
            cipherChoice(userCipher, userMode, "0");
        }
    }

    public static void cipherChoice(String userCipher, String userMode, String userKey) {
        switch (userCipher) {
            case "ATBASH":
                atbash();
                break;

            case "RAILFENCE":
                railfence(userMode, userKey);
                break;
            case "POLYBIUS":
                polybius(userMode, userKey);
                break;
        }
    }

    public static void atbash() {
        Scanner scan = new Scanner(System.in);
        String userString = scan.next().toUpperCase();
        System.out.println("ciphering: " + userString);
        List<String> ciphered = new ArrayList<String>();
        List<String> alphabetList = new ArrayList<String>();
        alphabetList = Arrays.asList("A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P",
                "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z");
        String[] textList = userString.split("");
        for (String letter : textList) {
            int letterIndex = alphabetList.indexOf(letter);
            Collections.reverse(alphabetList);
            ciphered.add(alphabetList.get(letterIndex));
            Collections.reverse(alphabetList);
        }
        System.out.println(String.join("", ciphered));
        scan.close();
    }

    public static void railfence(String userMode, String userKey) {
        Scanner scan = new Scanner(System.in);
        int keyInt = Integer.parseInt(userKey);
        String userString = scan.next().toUpperCase();
        System.out.println("ciphering: " + userString);
        String[] userLettersList = userString.split("");
        List<String> lettersArray = Arrays.asList(userLettersList);
        List<String> lettersLinked = new LinkedList<>(lettersArray);
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

    public static void polybius(String userMode, String userKey) {
        Scanner scan = new Scanner(System.in);
        String userString = scan.next().toUpperCase();
        char[] toEncrypt = userString.toCharArray();
        String ciphered = "";
        char[] userKeyList = userKey.toCharArray();
        char[][] table = new char[6][6];
        table[0][0] = '#';
        char[] alfa = { '0', 'A', 'B', 'C', 'D', 'E' };
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

// while(lettersLinked.size()!=0)

// {

// while (i < lettersLinked.size()) {
// ciphered.add(lettersLinked.get(i - x));
// lettersLinked.remove(i - x);
// i += magicNumber;
// if
// x += 1;
// }
// counter += 1;
// }

// public static void modeChoice(String userMode){
// switch (userMode) {
// case "-D":
// System.out.println("Decipher");
// break;
// case "-E":
// System.out.println("Encipher");
// } }
