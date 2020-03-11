import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.List;
import java.util.Collections;

public class CesarCipher {

    private static String userString = userMessage();
    private static List<String> alphabetList = Arrays.asList("A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L",
            "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z");
    private static List<String> ciphered = new ArrayList<String>();
    private static String[] textList = userString.split("");

    public static void cesar(String userKey, String userMode) {

        if (userMode.equals("-e")) {
            cesarCipher(userKey);
        } else if (userMode.equals("-d")) {
            cesarDecipher(userKey);
        }
    }

    public static String userMessage() {
        System.out.println("Baconian Cipher\n");
        System.out.print("Enter message: ");
        Scanner scan = new Scanner(System.in);
        String userString = scan.nextLine().toUpperCase();
        scan.close();
        return userString;
    }

    private static void cesarCipher(String userKey) {
        int userKeyInt = Integer.parseInt(userKey);
        for (String letter : textList) {
            int letterIndex = alphabetList.indexOf(letter);
            if (letter.equals(" ")) {
                ciphered.add(" ");
            } else {
                ciphered.add(alphabetList.get((letterIndex + userKeyInt) % 26));
            }
        }
        System.out.println("Cipher message: " + String.join("", ciphered));
    }

    private static void cesarDecipher(String userKey) {
        int userKeyInt = Integer.parseInt(userKey);
        Collections.reverse(alphabetList);
        for (String letter : textList) {
            int letterIndex = alphabetList.indexOf(letter);
            if (letter.equals(" ")) {
                ciphered.add("");
            } else {
                ciphered.add(alphabetList.get((letterIndex + userKeyInt) % 26));
            }
        }
        System.out.println("Decipher message: " + String.join("", ciphered));
    }
}