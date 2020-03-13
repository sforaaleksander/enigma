import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

class Enigma {
    private static String[] args;
    private static List<String> CIPHERLIST = Arrays.asList("ATBASH", "CAESAR", "BACONIAN", "RAILFENCE", "POLYBIUS",
                                                "COLUMNARTRANSPOSITION", "SIMPLE", "AUTOKEY", "BEAUFORT");
    public static void main(String[] args) {

        String userListMode = args[0];

        // try {
        // if (args.length < 2) {
        // throw new ArrayIndexOutOfBoundsException("Not enough arguments!");
        // }
        // } catch (ArrayIndexOutOfBoundsException e) {
        // System.out.println("Please provide valid arguments: [-D/-E] [CIPHER NAME]");
        // e.printStackTrace();
        // }
        // String userMode = args[0].toUpperCase();
        // String userCipher = args[1].toUpperCase();
        // if (args.length == 3) {
        // String userKey = args[2].toUpperCase();
        // cipherChoice(userCipher, userMode, userKey);
        // } else {
        // cipherChoice(userCipher, userMode, "0");
        // }
    }

    public static void cipherChoice(String userCipher, String userMode, String userKey) {

        Map<String, Runnable> commands = new HashMap<>();
        
        commands.put("ATBASH", () -> AtbashCipher.atbash());
        commands.put("CESAR", () -> CaesarCipher.caesar(userKey, userMode));
        commands.put("BACONIAN", () -> BaconianCipher.baconian(userMode));
        commands.put("RAILFENCE", () -> RailfenceCipher.railfence(userMode, userKey));
        commands.put("POLYBIUS", () -> PolybiusCipher.polybius(userMode, userKey));
        commands.put("COLUMNARTRANSPOSITION",
                () -> ColumnarTranspositionCipher.columnarTransposition(userMode, userKey));
        commands.put("SIMPLE", () -> SimpleCipher.simple(userKey, userMode));
        commands.put("AUTOKEY", () -> AutokeyCipher.autokey(userKey, userMode));
        commands.put("BEAUFORT", () -> BeaufortCipher.beaufort(userKey, userMode));

        commands.get(userCipher).run();

    }

    private static void oneArgs() {
        if (args[0].toUpperCase() != "-L") {
            basicInfo();
        }
    }

    private static void basicInfo() {
        System.out.println("To execute program type: [-D/-E] [CIPHER NAME] optional[KEY].");
        System.out.println("To list all available ciphers type: Enigma -l");
        System.exit(0);
    }

    private static void printCipherList() {

    }



    private static void checkArgs() {

    Map<Integer, Runnable> argsCount = new HashMap<>();
        argsCount.put(0, () -> basicInfo());
        argsCount.put(1, () -> oneArgs());
        argsCount.put(2, () -> basicInfo());


}
}
