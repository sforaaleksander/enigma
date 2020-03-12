import java.util.Scanner;
import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Collections;

class AtbashCipher {

    private static List<String> ALPHABETLIST = Arrays.asList("A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L",
            "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z");
    private static String userString = userMessage();
    private static String userStringStrip = userString.replaceAll("\\s", ""); 
    private static List<String> ciphered = new ArrayList<String>();
    private static List<String> textList = Arrays.asList(userStringStrip.split(""));


    public static void atbash() {
        for (String letter : textList) {
            int letterIndex = ALPHABETLIST.indexOf(letter);
            Collections.reverse(ALPHABETLIST);
            ciphered.add(ALPHABETLIST.get(letterIndex));
            Collections.reverse(ALPHABETLIST);
        }
        System.out.println("\nCiphered message: " + String.join("", ciphered));

    }

    private static String userMessage() {
        System.out.println("Atbash Cipher\n");
        System.out.print("Enter message: ");
        Scanner scan = new Scanner(System.in);
        String userString = scan.nextLine().toUpperCase();
        scan.close();
        return userString;
    }
}