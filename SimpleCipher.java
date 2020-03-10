import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.List;
import java.util.Collections;

public class SimpleCipher{

    public static void simple(String userKey, String userMode) {
        System.out.println("Simple Cipher\n");
        System.out.print("Enter message: ");
        Scanner scan = new Scanner(System.in);
        String userString = scan.nextLine().toUpperCase();
        List<String> alphabetList = new ArrayList<String>();
        alphabetList = Arrays.asList("A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P",
                "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z");

        String[] textList = userString.split("");
        // String[] userKeyArr = userKey.split("");
        List<String> userKeyList = new ArrayList<>();
        userKeyList = Arrays.asList(userKey.split(""));

        if (userMode.equals("-e")) {
            simpleCipher(userKeyList, textList, alphabetList);
        }
        else if (userMode.equals("-d")) {
        simpleDecipher(userKeyList, textList, alphabetList);
        }

    }

    public static void simpleCipher(List<String> userKeyList, String[] textList, List<String> alphabetList) {
        List<String> ciphered = new ArrayList<String>();
        List<String> newAlphabetList = new ArrayList<>();
        List<String> alphabetChangeList = new ArrayList<String>();
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
        System.out.println(newAlphabetList);

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

    public static void simpleDecipher(List<String> userKeyList, String[] textList, List<String> alphabetList) {
        List<String> ciphered = new ArrayList<String>();
        List<String> newAlphabetList = new ArrayList<>();
        List<String> alphabetChangeList = new ArrayList<String>();
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
}