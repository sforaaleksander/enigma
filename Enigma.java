import java.util.ArrayList;
import java.util.Scanner;

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
        
        ArrayList<String> alphabetList = new ArrayList<String>(); 
        alphabetList = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};

        String[] textList = userString.split("");

        for (String letter : textList) {
            int letterIndex = alphabetList.indexOf(letter);
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
