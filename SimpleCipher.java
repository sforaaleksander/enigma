import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.List;

public class SimpleCipher {

    private static String userString = userMessage();
    private static List<String> ALPHABETLIST = Arrays.asList("A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L",
            "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z");
    private static List<String> textList;
    private static List<String> ciphered = new ArrayList<String>();
    private static List<String> newALPHABETLIST = new ArrayList<>();
    private static List<String> alphabetChangeList = new ArrayList<String>();
    private static String userStringStrip = userString.replaceAll("\\s", "");
    private static List<String> userKeyList = new ArrayList<>();

    public static void simple(String userKey, String userMode) {

        userKeyList = new ArrayList<>();
        List<String> textListToCheck = Arrays.asList(userStringStrip.split(""));
        userKeyList = checkUserKey(userKey);
        textList = new ArrayList<>();
        for(String letter : textListToCheck){
            if(ALPHABETLIST.contains(letter)){
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

    private static List<String> checkUserKey(String userKey) {

        String userKeyStrip = userKey.replaceAll("\\s", "");
        List<String> keyListToCheck = Arrays.asList(userKeyStrip.split(""));
        for (String letter : keyListToCheck) {
            if (ALPHABETLIST.contains(letter)) {
                userKeyList.add(letter);
            }
        }
        if (userKeyList.size() > 0) {
            return userKeyList;
        } else {
            userKeyList.add("A");
            return userKeyList;
        }
    }

    private static void userChoice(String userMode) {
        if (userMode.equals("-E")) {
            simpleCipher();
        } else if (userMode.equals("-D")) {
            simpleDecipher();
        }
    }

    private static void simpleCipher() {

        newALPHABETLIST = newALPHABETLISTMethod();
        for (String letter : textList) {
            int letterIndex = ALPHABETLIST.indexOf(letter);
            if (letter.equals(" ")) {
                ciphered.add("");
            } else {
                ciphered.add(newALPHABETLIST.get(letterIndex));
            }
        }
        System.out.println("\nYour key : " + String.join("", userKeyList));
        System.out.println("Ciphered message: " + String.join("", ciphered));
    }

    private static void simpleDecipher() {

        newALPHABETLIST = newALPHABETLISTMethod();
        for (String letter : textList) {
            int letterIndex = newALPHABETLIST.indexOf(letter);
            if (letter.equals(" ")) {
                ciphered.add("");
            } else {
                ciphered.add(ALPHABETLIST.get(letterIndex));
            }
        }
        System.out.println("\nYour key : " + String.join("", userKeyList));
        System.out.println("Deciphered message: " + String.join("", ciphered));
    }

    private static String userMessage() {
        System.out.println("Simple Cipher\n");
        System.out.print("Enter message: ");
        Scanner scan = new Scanner(System.in);
        String userString = scan.nextLine().toUpperCase();
        scan.close();
        return userString;
    }

    private static List<String> newALPHABETLISTMethod() {
        alphabetChangeList.addAll(ALPHABETLIST);
        for (String letter : userKeyList) {
            for (int i = 0; i < alphabetChangeList.size(); i++) {
                if (letter.equalsIgnoreCase(alphabetChangeList.get(i))) {
                    alphabetChangeList.remove(i);
                }
            }
        }
        newALPHABETLIST.addAll(userKeyList);
        newALPHABETLIST.addAll(alphabetChangeList);
        return newALPHABETLIST;

    }
}
