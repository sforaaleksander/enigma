import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.List;
import java.util.Collections;

public class AutokeyCipher{
    private static List<String> alphabetList = Arrays.asList("A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", 
                                                             "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z");

    public static void autokey(String userKey, String userMode) {
        
        String userString = userMessage();
        String userStringStrip = userString.replaceAll("\\s", ""); 
        List<String> textList = new ArrayList<>();
        textList = Arrays.asList(userStringStrip.split(""));
        List<String> userKeyList = new ArrayList<>();
        userKeyList = Arrays.asList(userKey.split(""));

        userChoice(userMode, userKeyList, textList);
        
    }

    public static String userMessage(){
        System.out.println("Autokey Cipher\n");
        System.out.print("Enter message: ");
        Scanner scan = new Scanner(System.in);
        String userString = scan.nextLine().toUpperCase();
        scan.close();
        return userString;
    }

    public static void userChoice(String userMode, List<String> userKeyList, List<String> textList){
        if (userMode.equals("-e")) {
            autokeyCipher(userKeyList, textList);
        }
        else if (userMode.equals("-d")) {
            autokeyDecipher(userKeyList, textList);
        }
    }

    public static void autokeyCipher(List<String> userKeyList, List<String> textList){
        
        List<String> mainKey = mainKeyMethod(userKeyList, textList);
        String[][] tabulaRecta = tabulaRectaMethod(alphabetList);    
        List<String> cipher = new ArrayList<>();
        int indexLettekKey = 0;
        int indexLetterText = 0;
        while(indexLettekKey < mainKey.size()){
            int i = alphabetList.indexOf(mainKey.get(indexLettekKey));
            int j = alphabetList.indexOf(textList.get(indexLetterText));
            cipher.add(tabulaRecta[i][j]);
            indexLettekKey++;
            indexLetterText++;
        }
        System.out.println("\nDecipher message: " + String.join("", cipher));
    }

    public static List<String> mainKeyMethod(List<String> userKeyList, List<String> textList){
        List<String> mainKey = new ArrayList<>();
        mainKey.addAll(userKeyList);
        for(String letter : textList){
            if(textList.size() > mainKey.size()){
                mainKey.add(letter);
            }
            else if (textList.size() < mainKey.size()){
                mainKey.remove(mainKey.size()-1);
            }
        }
        return mainKey;
    }

    public static String[][] tabulaRectaMethod(List<String> alphabetList){
        String[][] tabulaRecta = new String[26][26];
        for (int j = 0; j < alphabetList.size(); j++){
            for(int i = 0; i < alphabetList.size(); i++){
                tabulaRecta[j][i] = alphabetList.get(i);
            }
            Collections.rotate(alphabetList, alphabetList.size() - 1);
        }
        return tabulaRecta;
    }

    public static void autokeyDecipher(List<String> userKeyList, List<String> textList){
        List<String> mainKey = new ArrayList<>();
        mainKey.addAll(userKeyList);
        String[][] tabulaRecta = tabulaRectaMethod(alphabetList);    
        List<String> cipher = new ArrayList<>();
        int indexLetterKey = 0;
        int indexLetterText = 0;
        while(indexLetterText < textList.size()){
            int i = alphabetList.indexOf(mainKey.get(indexLetterKey));
            for(String[] row : tabulaRecta){
                if(row[i].equalsIgnoreCase(textList.get(indexLetterText))){
                    cipher.add(row[0]);
                    if(mainKey.size() <= textList.size()){
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