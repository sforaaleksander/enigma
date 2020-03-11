import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.List;
import java.util.Collections;

public class AutokeyCipher{
    private static List<String> alphabetList = Arrays.asList("A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", 
                                                             "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z");
    private static String userString = userMessage();
    private static String userStringStrip = userString.replaceAll("\\s", ""); 
    private static List<String> textList = Arrays.asList(userStringStrip.split(""));
    
    public static void autokey(String userKey, String userMode) {
           
        List<String> userKeyList = new ArrayList<>();
        userKeyList = Arrays.asList(userKey.split(""));
        userChoice(userMode, userKeyList);
        
    }

    public static String userMessage(){
        System.out.println("Autokey Cipher\n");
        System.out.print("Enter message: ");
        Scanner scan = new Scanner(System.in);
        String userString = scan.nextLine().toUpperCase();
        scan.close();
        return userString;
    }

    public static void userChoice(String userMode, List<String> userKeyList){
        if (userMode.equals("-e")) {
            autokeyCipher(userKeyList);
        }
        else if (userMode.equals("-d")) {
            autokeyDecipher(userKeyList);
        }
    }

    public static void autokeyCipher(List<String> userKeyList){
        
        List<String> mainKey = mainKeyMethod(userKeyList);
        String[][] tabulaRecta = tabulaRectaMethod();    
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

    public static List<String> mainKeyMethod(List<String> userKeyList){
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

    public static String[][] tabulaRectaMethod(){
        String[][] tabulaRecta = new String[26][26];
        for (int j = 0; j < alphabetList.size(); j++){
            for(int i = 0; i < alphabetList.size(); i++){
                tabulaRecta[j][i] = alphabetList.get(i);
            }
            Collections.rotate(alphabetList, alphabetList.size() - 1);
        }
        return tabulaRecta;
    }

    public static void autokeyDecipher(List<String> userKeyList){
        List<String> mainKey = new ArrayList<>();
        mainKey.addAll(userKeyList);
        String[][] tabulaRecta = tabulaRectaMethod();    
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