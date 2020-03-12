import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.List;
import java.util.Collections;

public class AutokeyCipher {
    private static List<String> alphabetList = Arrays.asList("A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L",
            "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z");
    private static String userString = userMessage();
    private static String userStringStrip = userString.replaceAll("\\s", "");
    private static List<String> textList;
    private static List<String> userKeyList = new ArrayList<>();
    private static List<String> cipher = new ArrayList<>();
    private static String[][] tabulaRecta = tabulaRectaMethod();

    public static void autokey(String userKey, String userMode) {
           
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


    public static String userMessage() {
        System.out.println("Autokey Cipher\n");
        System.out.print("Enter message: ");
        Scanner scan = new Scanner(System.in);
        String userString = scan.nextLine().toUpperCase();
        scan.close();
        return userString;
    }

    public static void userChoice(String userMode) {
        if (userMode.equals("-e")) {
            autokeyCipher();
        } else if (userMode.equals("-d")) {
            autokeyDecipher();
        }
    }

    public static void autokeyCipher() {
        try {
            List<String> mainKey = mainKeyMethod();
            int indexLettekKey = 0;
            int indexLetterText = 0;
            while (indexLettekKey < mainKey.size()) {
                int i = alphabetList.indexOf(mainKey.get(indexLettekKey));
                int j = alphabetList.indexOf(textList.get(indexLetterText));
                cipher.add(tabulaRecta[i][j]);
                indexLettekKey++;
                indexLetterText++;
            }
            System.out.println("\nCiphered message: " + String.join("", cipher));
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Message can contalin only english letters.");
            e.printStackTrace();
        }
    }

    public static List<String> mainKeyMethod() {
        List<String> mainKey = new ArrayList<>();
        mainKey.addAll(userKeyList);
        for (String letter : textList) {
            if (textList.size() > mainKey.size()) {
                mainKey.add(letter);
            } else if (textList.size() < mainKey.size()) {
                mainKey.remove(mainKey.size() - 1);
            }
        }
        return mainKey;
    }

    public static String[][] tabulaRectaMethod() {
        String[][] tabulaRecta = new String[26][26];
        for (int j = 0; j < alphabetList.size(); j++) {
            for (int i = 0; i < alphabetList.size(); i++) {
                tabulaRecta[j][i] = alphabetList.get(i);
            }
            Collections.rotate(alphabetList, alphabetList.size() - 1);
        }
        return tabulaRecta;
    }

    public static void autokeyDecipher() {
        List<String> mainKey = new ArrayList<>();
        mainKey.addAll(userKeyList);
        int indexLetterKey = 0;
        int indexLetterText = 0;
        while (indexLetterText < textList.size()) {
            int i = alphabetList.indexOf(mainKey.get(indexLetterKey));
            for (String[] row : tabulaRecta) {
                if (row[i].equalsIgnoreCase(textList.get(indexLetterText))) {
                    cipher.add(row[0]);
                    if (mainKey.size() <= textList.size()) {
                        mainKey.add(row[0]);
                    }
                }
            }
            indexLetterKey++;
            indexLetterText++;
        }
        System.out.println("\nDecipher message: " + String.join("", cipher));
    }

}