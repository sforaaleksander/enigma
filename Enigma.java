import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.List;
import java.util.Collections;


class Enigma {
    public static void main(String[] args) {
        try {
            if (args.length < 2) {
                throw new ArrayIndexOutOfBoundsException("Not enough arguments!");
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Please provide valid arguments: [-D/-E] [CIPHER NAME]");
            e.printStackTrace();
        }
        String userMode = args[0].toUpperCase();
        String userCipher = args[1].toUpperCase();
        if (args.length == 3) {
            String userKey = args[2].toUpperCase();
            cipherChoice(userCipher, userMode, userKey);
        }
        else{
            cipherChoice(userCipher, userMode, "0");

        }
    }

    // public static void cipherChoice(String userCipher, String userMode) {
    //     cipherChoice(userCipher, userMode, "0");
    // }

    public static void cipherChoice(String userCipher, String userMode, String userKey) {
        switch (userCipher) {
            case "ATBASH":
                atbash();
                break;
            case "CESAR":
                cesar(userKey);
                break;
        }
    }

    public static void atbash() {
        System.out.println("Atbash Cipher\n");
        Scanner scan = new Scanner(System.in);
        String userString = scan.next().toUpperCase();
        System.out.println("ciphering: " + userString);

        ArrayList<String> ciphered = new ArrayList<String>();
        
        List<String> alphabetList = new ArrayList<String>(); 
        alphabetList = Arrays.asList("A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M",
                                     "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z");
       
        String[] textList = userString.split("");
        

        for (String letter : textList) {
            int letterIndex = alphabetList.indexOf(letter);
            Collections.reverse(alphabetList);
            ciphered.add(alphabetList.get(letterIndex));
            Collections.reverse(alphabetList);


        }
        System.out.println(String.join("", ciphered));
    }


    public static void cesar(String userKey) {
        System.out.println("Caesar Cipher\n");
        Scanner scan = new Scanner(System.in);
        String userString = scan.next().toUpperCase();
        System.out.print("ciphering: " + userString + "\n");

        int userKeyInt = Integer.parseInt(userKey);

        ArrayList<String> ciphered = new ArrayList<String>();
        
        List<String> alphabetList = new ArrayList<String>(); 
        alphabetList = Arrays.asList("A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M",
                                     "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z");
       
        String[] textList = userString.split("");
        

        for (String letter : textList) {
            int letterIndex = alphabetList.indexOf(letter);
            ciphered.add(alphabetList.get((letterIndex + userKeyInt) % 26 -1));

        }
        System.out.println("Deciphering: " + String.join("", ciphered));
    }
}

// public static void modeChoice(String userMode){
// switch (userMode) {
// case "-D":
// System.out.println("Decipher");
// break;
// case "-E":
// System.out.println("Encipher");
// } }
