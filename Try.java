import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.List;


public class Try{
    public static void main(String[] args){

        Scanner scan = new Scanner(System.in);
        String userString = scan.next().toUpperCase();
        System.out.println("ciphering: " + userString);

        ArrayList<String> ciphered = new ArrayList<String>();
        
        List<String> alphabetList = new ArrayList<String>(); 
        alphabetList = Arrays.asList("A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z");

        String[] textList = userString.split("");

        for (String letter : textList) {
            int letterIndex = alphabetList.indexOf(letter);
        }
    }
}