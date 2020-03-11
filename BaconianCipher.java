import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.List;


public class BaconianCipher {

    private static String userString = userMessage();
    private static List<String> alphabetList = Arrays.asList("A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L",
            "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z");
    private static List<String> numbersList = Arrays.asList("00000", "00001", "00010", "00011", "00100", "00101",
            "00110", "00111", "01000", "01001", "01010", "01011", "01100", "01101", "01110", "01111", "10000", "10001",
            "10010", "10011", "10100", "10101", "10110", "10111", "11000", "11001");
    private static String[] textList = userString.split("");
    private static List<String> ciphered = new ArrayList<String>();


    public static void baconian(String userMode) {
        if (userMode.equals("-e")) {
            baconianCipher();
        } else if (userMode.equals("-d")) {
            baconianDecipher();
        }
    }

    public static String userMessage() {
        System.out.println("Baconian Cipher\n");
        System.out.print("Enter message: ");
        Scanner scan = new Scanner(System.in);
        String userString = scan.nextLine().toUpperCase();
        scan.close();
        return userString;
    }

    private static void baconianCipher() {
        for (String letter : textList) {
            int letterIndex = alphabetList.indexOf(letter);
            if (letter.equals(" ")) {
                ciphered.add("");
            } else {
                ciphered.add(numbersList.get(letterIndex));
            }
        }
        System.out.println("Cipher message: " + String.join("", ciphered));
    }

    private static void baconianDecipher() {
        List<String> strings = new ArrayList<String>();
        int index = 0;
        while (index < userString.length()) {
            strings.add(userString.substring(index, Math.min(index + 5, userString.length())));
            index += 5;
        }
        for (String number : strings) {
            int numberIndex = numbersList.indexOf(number);
            ciphered.add(alphabetList.get(numberIndex));
        }
        System.out.println("Decipher message: " + String.join("", ciphered));

    }
}