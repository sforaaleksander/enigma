import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.List;
import java.util.Collections;

public class CaesarCipher {

    private static String userString = userMessage();
    private static List<String> alphabetList = Arrays.asList("A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L",
            "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z");
    private static List<String> ciphered = new ArrayList<String>();
    private static String userStringStrip = userString.replaceAll("\\s", "");
    private static List<String> textList;
    private static int userKeyInt;
    

    public static void caesar(String userKey, String userMode) {
        userKeyInt = Integer.parseInt(userKey);
        List<String> textListToCheck = Arrays.asList(userStringStrip.split(""));
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
            caesarCipher();
        } else if (userMode.equals("-d")) {
            caesarDecipher();
        }
    }

    public static String userMessage() {
        System.out.println("Caesar Cipher\n");
        System.out.print("Enter message: ");
        Scanner scan = new Scanner(System.in);
        String userString = scan.nextLine().toUpperCase();
        scan.close();
        return userString;
    }

    private static void caesarCipher() {
        
        for (String letter : textList) {
            int letterIndex = alphabetList.indexOf(letter);
            ciphered.add(alphabetList.get((letterIndex + userKeyInt) % 26));
        }
        System.out.println("Cipher message: " + String.join("", ciphered));
    }

    private static void caesarDecipher() {
        Collections.reverse(alphabetList);
        for (String letter : textList) {
            int letterIndex = alphabetList.indexOf(letter);
            ciphered.add(alphabetList.get((letterIndex + userKeyInt) % 26));
            
        }
        System.out.println("Decipher message: " + String.join("", ciphered));
    }
}