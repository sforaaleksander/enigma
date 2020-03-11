import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.List;
import java.util.Collections;

public class SimpleCipher{

    private static String userString = userMessage();
    private static List<String> alphabetList = Arrays.asList("A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L",
            "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z");
    private static String[] textList = userString.split(""); 
    private static List<String> ciphered = new ArrayList<String>();
    private static List<String> newAlphabetList = new ArrayList<>();
    private static List<String> alphabetChangeList = new ArrayList<String>();

    public static void simple(String userKey, String userMode) {
        
        List<String> userKeyList = new ArrayList<>();
        userKeyList = Arrays.asList(userKey.split(""));

        if (userMode.equals("-e")) {
            simpleCipher(userKeyList);
        }
        else if (userMode.equals("-d")) {
        simpleDecipher(userKeyList);
        }

    }

    public static void simpleCipher(List<String> userKeyList) {
        
        alphabetChangeList.addAll(alphabetList);
        for (String letter : userKeyList) {
            for (int i = 0; i < alphabetChangeList.size(); i++) {
                if (letter.equalsIgnoreCase(alphabetChangeList.get(i))) {
                    alphabetChangeList.remove(i);
                }
            }
        }
        newAlphabetList.addAll(userKeyList);
        newAlphabetList.addAll(alphabetChangeList);

        for (String letter : textList) {
            int letterIndex = alphabetList.indexOf(letter);
            if (letter.equals(" ")) {
                ciphered.add("");
            } else {
                ciphered.add(newAlphabetList.get(letterIndex));
            }
        }
        System.out.println("Cipher message: " + String.join("", ciphered));
    }

    public static void simpleDecipher(List<String> userKeyList) {
        alphabetChangeList.addAll(alphabetList);
        for (String letter : userKeyList) {
            for (int i = 0; i < alphabetChangeList.size(); i++) {
                if (letter.equalsIgnoreCase(alphabetChangeList.get(i))) {
                    alphabetChangeList.remove(i);
                }
            }
        }
        newAlphabetList.addAll(userKeyList);
        newAlphabetList.addAll(alphabetChangeList);

        for (String letter : textList) {
            int letterIndex = newAlphabetList.indexOf(letter);
            if (letter.equals(" ")) {
                ciphered.add("");
            } else {
                ciphered.add(alphabetList.get(letterIndex));
            }
        }
        System.out.println("Decipher message: " + String.join("", ciphered));
    }

    public static String userMessage() {
        System.out.println("Baconian Cipher\n");
        System.out.print("Enter message: ");
        Scanner scan = new Scanner(System.in);
        String userString = scan.nextLine().toUpperCase();
        scan.close();
        return userString;
    }
}