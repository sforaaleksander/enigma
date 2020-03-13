import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.lang.NullPointerException;

class Enigma {
    // private static String[] args;
    private static List<String> CIPHERLIST = Arrays.asList("ATBASH", "CAESAR", "BACONIAN", "RAILFENCE", "POLYBIUS",
            "COLUMNARTRANSPOSITION", "SIMPLE", "AUTOKEY", "BEAUFORT");

    public static void main(String[] args) {
        checkArgs(args);
    }

    public static void cipherChoiceWithKey(String userCipher, String userMode, String userKey) {
        Map<String, Runnable> commands = new HashMap<>();

        //TODO is there a neater way of populating a hashmap?
        //map.of() did not work until i modified the cipher classes
        //to return a string instead of void
        commands.put("CAESAR", () -> CaesarCipher.caesar(userKey, userMode));
        commands.put("RAILFENCE", () -> RailfenceCipher.railfence(userMode, userKey));
        commands.put("POLYBIUS", () -> PolybiusCipher.polybius(userMode, userKey));
        commands.put("COLUMNARTRANSPOSITION",
                () -> ColumnarTranspositionCipher.columnarTransposition(userMode, userKey));
        commands.put("SIMPLE", () -> SimpleCipher.simple(userKey, userMode));
        commands.put("AUTOKEY", () -> AutokeyCipher.autokey(userKey, userMode));
        commands.put("BEAUFORT", () -> BeaufortCipher.beaufort(userKey, userMode));

        commands.get(userCipher).run();
    }

    private static void cipherChoiceWithoutKey(String userCipher, String userMode) {
        Map<String, Runnable> noKeyCommands = new HashMap<>();
        noKeyCommands.put("ATBASH", () -> AtbashCipher.atbash());
        noKeyCommands.put("BACONIAN", () -> BaconianCipher.baconian(userMode));
        noKeyCommands.get(userCipher).run();
    }

    private static void printCipherList() {
        System.out.println("Available ciphers:");
        for (int i = 0; i < CIPHERLIST.size(); i++) {
            System.out.println((i + 1) + ". " + CIPHERLIST.get(i));
        }
    }

    private static void basicInfo() {
        System.out.println("To execute program type: Enigma [-D/-E] [CIPHER NAME] optional[KEY].");
        System.out.println("To list all available ciphers type: Enigma -l");
        System.exit(0);
    }

    private static void oneArgs(String args[]) {
        String listCiphers = args[0].toUpperCase();
        if (listCiphers.equals("-L")) {
            printCipherList();
        } else {
            basicInfo();
        }
    }

    private static void twoArgs(String args[]) {
        String userMode = args[0];
        String userCipher = args[1].toUpperCase();
        cipherChoiceWithoutKey(userCipher, userMode);
    }

    private static void threeArgs(String args[]) {
        String userMode = args[0];
        String userCipher = args[1].toUpperCase();
        String userKey = args[2].toUpperCase();
        cipherChoiceWithKey(userCipher, userMode, userKey);
    }

    private static void tooManyArgs(String args[]) {
        System.out.println("Too many arguments passed.");
        basicInfo();
    }


    //TODO: why do i need to pass an argument here if args is
    // defined as a static field of the class
    private static void checkArgs(String args[]) {
        Integer lengthOfArgs = args.length;
        Map<Integer, Runnable> argsCount = new HashMap<>();
        argsCount.put(0, () -> basicInfo());
        argsCount.put(1, () -> oneArgs(args));
        argsCount.put(2, () -> twoArgs(args));
        argsCount.put(3, () -> threeArgs(args));
        try {
        argsCount.get(lengthOfArgs).run();
    }
        catch (NullPointerException e){
            tooManyArgs(args);
        }

    }
}
