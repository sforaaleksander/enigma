import java.util.Scanner;
import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Collections;

class AtbashCipher {

    private static List<String> alphabetList = Arrays.asList("A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L",
            "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z");
    private static String userString = userMessage();
    private static String userStringStrip = userString.replaceAll("\\s", ""); 
    private static List<String> ciphered = new ArrayList<String>();
    private static List<String> textList = Arrays.asList(userStringStrip.split(""));


    public static void atbash() {
        for (String letter : textList) {
            int letterIndex = alphabetList.indexOf(letter);
            Collections.reverse(alphabetList);
            ciphered.add(alphabetList.get(letterIndex));
            Collections.reverse(alphabetList);
        }
        System.out.println("\nCiphered message: " + String.join("", ciphered));
    }

    public static String userMessage() {
        System.out.println("Atbash Cipher\n");
        System.out.print("Enter message: ");
        Scanner scan = new Scanner(System.in);
        String userString = scan.nextLine().toUpperCase();
        scan.close();
        return userString;
    }
}