import java.util.HashMap;
import java.util.Map;

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

        Map<String, Runnable> commands = new HashMap<>();
        commands.put("ATBASH", () -> AtbashCipher.atbash());
        commands.put("CESAR", () -> CesarCipher.cesar(userKey, userMode));
        commands.put("BACONIAN", () -> BaconianCipher.baconian(userMode));
        commands.put("RAILFENCE", () -> RailfenceCipher.railfence(userMode, userKey));
        commands.put("POLYBIUS", () -> PolybiusCipher.polybius(userMode, userKey));
        commands.put("COLUMNARTRANSPOSITION", () -> ColumnarTranspositionCipher.columnarTransposition(userMode, userKey));
        commands.put("SIMPLE", () -> SimpleCipher.simple(userKey, userMode));
        commands.put("AUTOKEY", () -> AutokeyCipher.autokey(userKey, userMode));
        commands.put("BEAUFORT", () -> BeaufortCipher.beaufort(userKey, userMode));

        commands.get(userCipher).run();

    }
}
