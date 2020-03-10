import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.List;
import java.util.Collections;

public class CesarCipher{
    
    public static void cesar(String userKey, String userMode) {
        System.out.println("Caesar Cipher\n");
        System.out.print("Enter message: ");
        Scanner scan = new Scanner(System.in);
        String userString = scan.nextLine().toUpperCase();

        int userKeyInt = Integer.parseInt(userKey);

        List<String> ciphered = new ArrayList<String>();

        List<String> alphabetList = new ArrayList<String>();
        alphabetList = Arrays.asList("A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P",
                "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z");

        String[] textList = userString.split("");

        if (userMode.equals("-e")) {
            for (String letter : textList) {
                int letterIndex = alphabetList.indexOf(letter);
                if (letter.equals(" ")) {
                    ciphered.add(" ");
                } else {
                    ciphered.add(alphabetList.get((letterIndex + userKeyInt) % 26));
                }

            }
            System.out.println("Cipher message: " + String.join("", ciphered));
        } else if (userMode.equals("-d")) {
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
}