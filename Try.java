import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.List;


public class Try{
    public static void main(String[] args){

        List<String> alphabetList = Arrays.asList("A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", 
                                                             "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z");
        String userString = "Ala ma% kota".toUpperCase();
        String userStringStrip = userString.replaceAll("\\s", ""); 
        List<String> textList = Arrays.asList(userStringStrip.split(""));
        for(String letter : textList){
            if(!alphabetList.contains(letter)){
                System.out.println("Text messgage can contains only english letters.");
                break;
            }
            
        }

        System.out.println(textList);
        
    }
}