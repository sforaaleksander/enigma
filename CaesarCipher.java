import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.List;
import java.util.Collections;

public class CaesarCipher {

    private static String userString = userMessage();
    private static List<String> ALPHABETLIST = Arrays.asList("A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L",
            "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z");
    private static List<String> ciphered = new ArrayList<String>();
    private static String userStringStrip = userString.replaceAll("\\s", "");
    private static List<String> textList;
    private static int userKeyInt;
    

    public static void caesar(String userKey, String userMode) {
        if (userKey.equals("0")){
            userKey = "3";
        }
        userKeyInt = checkUserKey(userKey);
        List<String> textListToCheck = Arrays.asList(userStringStrip.split(""));
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

    private static int checkUserKey(String userKey){
        try{
        userKeyInt = Integer.parseInt(userKey);
        }
        catch(NumberFormatException e){
            System.out.println("\nYou didn't pass an integer no. for key, so your key will be: 0.\n");
            userKeyInt = 0;
        }
        return userKeyInt;
    }

    private static void userChoice(String userMode) {
        if (userMode.equals("-E")) {
            caesarCipher();
        } else if (userMode.equals("-D")) {
            caesarDecipher();
        }
    }

    private static String userMessage() {
        System.out.println("Caesar Cipher\n");
        System.out.print("Enter message: ");
        Scanner scan = new Scanner(System.in);
        String userString = scan.nextLine().toUpperCase();
        scan.close();
        return userString;
    }

    private static void caesarCipher() {
        
        for (String letter : textList) {
            int letterIndex = ALPHABETLIST.indexOf(letter);
            ciphered.add(ALPHABETLIST.get((letterIndex + userKeyInt) % 26));
        }
        System.out.println("\nYour key : " + userKeyInt);
        System.out.println("Cipher message: " + String.join("", ciphered));
    }

    private static void caesarDecipher() {
        Collections.reverse(ALPHABETLIST);
        for (String letter : textList) {
            int letterIndex = ALPHABETLIST.indexOf(letter);
            ciphered.add(ALPHABETLIST.get((letterIndex + userKeyInt) % 26));
            
        }
        System.out.println("\nYour key : " + userKeyInt);
        System.out.println("Decipher message: " + String.join("", ciphered));
    }
}