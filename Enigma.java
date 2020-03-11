
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
        String userMode = args[0];
        String userCipher = args[1].toUpperCase();
        if (args.length == 3) {
            String userKey = args[2].toUpperCase();
            cipherChoice(userCipher, userMode, userKey);
        } else {
            cipherChoice(userCipher, userMode, "0");

        } 
    }

    public static void cipherChoice(String userCipher, String userMode, String userKey) {
        switch (userCipher) {
            case "ATBASH":
                AtbashCipher.atbash();
                break;
            case "CESAR":
                CesarCipher.cesar(userKey, userMode);
                break;
            case "BACONIAN":
                BaconianCipher.baconian(userMode);
            case "RAILFENCE":
                RailfenceCipher.railfence(userMode, userKey);
                break;
            case "POLYBIUS":
                PolybiusCipher.polybius(userMode, userKey);
                break;
            case "COLUMNARTRANSPOSITION":
                ColumnarTranspositionCipher.columnarTransposition(userMode, userKey);
                break;
            case "SIMPLE":
                SimpleCipher.simple(userKey, userMode);
                break;
<<<<<<< HEAD
            case "AUTOKEY":
                AutokeyCipher.autokey(userKey, userMode);
=======
            // case "AUTOKEY":
            //     autokey(userKey, userMode);
            //     break;
            case "BEAUFORT":
                BeaufortCipher.beaufort(userKey, userMode);
>>>>>>> 27b564ec1688877480b9aa48f4ab96af6c6f46e1
                break;
        }
    }
}
