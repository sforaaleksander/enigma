import java.util.Scanner;
import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Collections;

class AtbashCipher {
    public static void atbash() {
        Scanner scan = new Scanner(System.in);
        String userString = scan.next().toUpperCase();
        System.out.println("ciphering: " + userString);
        List<String> ciphered = new ArrayList<String>();
        List<String> alphabetList = new ArrayList<String>();
        alphabetList = Arrays.asList("A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P",
                "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z");
        String[] textList = userString.split("");
        for (String letter : textList) {
            int letterIndex = alphabetList.indexOf(letter);
            Collections.reverse(alphabetList);
            ciphered.add(alphabetList.get(letterIndex));
            Collections.reverse(alphabetList);
        }
        System.out.println(String.join("", ciphered));
        scan.close();
    }
}