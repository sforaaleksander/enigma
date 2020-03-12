import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.List;

public class SimpleCipher {

    private static String userString = userMessage();
    private static List<String> alphabetList = Arrays.asList("A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L",
            "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z");
    private static List<String> textList;
    private static List<String> ciphered = new ArrayList<String>();
    private static List<String> newAlphabetList = new ArrayList<>();
    private static List<String> alphabetChangeList = new ArrayList<String>();
    private static String userStringStrip = userString.replaceAll("\\s", "");
    private static List<String> userKeyList = new ArrayList<>();

    public static void simple(String userKey, String userMode) {

        userKeyList = new ArrayList<>();

        List<String> textListToCheck = Arrays.asList(userStringStrip.split(""));
        userKeyList = Arrays.asList(userKey.split(""));
        textList = new ArrayList<>();
        for(String letter : textListToCheck){
            if(alphabetList.contains(letter)){
                textList.add(letter);
            }   
        }
        if(textList.size() == textListToCheck.size()){
            userChoice(userMode);
        }
        else{
            System.out.println("Message can contains anly letters from english alphabet.");
        } 
    }

    public static void userChoice(String userMode) {
        if (userMode.equals("-e")) {
            simpleCipher();
        } else if (userMode.equals("-d")) {
            simpleDecipher();
        }
    }

    public static void simpleCipher() {

        newAlphabetList = newAlphabetListMethod();
        for (String letter : textList) {
            int letterIndex = alphabetList.indexOf(letter);
            if (letter.equals(" ")) {
                ciphered.add("");
            } else {
                ciphered.add(newAlphabetList.get(letterIndex));
            }
        }
        System.out.println("\nCiphered message: " + String.join("", ciphered));
    }

    public static void simpleDecipher() {

        newAlphabetList = newAlphabetListMethod();
        for (String letter : textList) {
            int letterIndex = newAlphabetList.indexOf(letter);
            if (letter.equals(" ")) {
                ciphered.add("");
            } else {
                ciphered.add(alphabetList.get(letterIndex));
            }
        }
        System.out.println("\nDeciphered message: " + String.join("", ciphered));
    }

    public static String userMessage() {
        System.out.println("Simple Cipher\n");
        System.out.print("Enter message: ");
        Scanner scan = new Scanner(System.in);
        String userString = scan.nextLine().toUpperCase();
        scan.close();
        return userString;
    }

    public static List<String> newAlphabetListMethod() {
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
        return newAlphabetList;

    }
}
