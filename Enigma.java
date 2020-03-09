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
        
        }
    cipherChoice(userCipher, userMode);
    }

    public static void cipherChoice(String userCipher, String userMode) {
        switch (userCipher) {
            case "ATBASH":
                atbash(userMode);
                break;
        }
    }

    public static void atbash(String mode) {
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
            

            
        }

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
