import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.List;
import java.util.Collections;

public class AutokeyCipher {
    private static List<String> ALPHABETLIST = Arrays.asList("A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L",
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


    private static String userMessage() {
        System.out.println("Autokey Cipher\n");
        System.out.print("Enter message: ");
        Scanner scan = new Scanner(System.in);
        String userString = scan.nextLine().toUpperCase();
        scan.close();
        return userString;
    }

    private static void userChoice(String userMode) {
        if (userMode.equals("-E")) {
            autokeyCipher();
        } else if (userMode.equals("-D")) {
            autokeyDecipher();
        }
    }

    private static void autokeyCipher() {
        try {
            List<String> mainKey = mainKeyMethod();
            int indexLettekKey = 0;
            int indexLetterText = 0;
            while (indexLettekKey < mainKey.size()) {
                int i = ALPHABETLIST.indexOf(mainKey.get(indexLettekKey));
                int j = ALPHABETLIST.indexOf(textList.get(indexLetterText));
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

    private static List<String> mainKeyMethod() {
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

    private static String[][] tabulaRectaMethod() {
        String[][] tabulaRecta = new String[26][26];
        for (int j = 0; j < ALPHABETLIST.size(); j++) {
            for (int i = 0; i < ALPHABETLIST.size(); i++) {
                tabulaRecta[j][i] = ALPHABETLIST.get(i);
            }
            Collections.rotate(ALPHABETLIST, ALPHABETLIST.size() - 1);
        }
        return tabulaRecta;
    }

    private static void autokeyDecipher() {
        List<String> mainKey = new ArrayList<>();
        mainKey.addAll(userKeyList);
        int indexLetterKey = 0;
        int indexLetterText = 0;
        while (indexLetterText < textList.size()) {
            int i = ALPHABETLIST.indexOf(mainKey.get(indexLetterKey));
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