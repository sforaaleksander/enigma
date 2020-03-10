import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.List;


public class Try{
    public static void main(String[] args){


        String userKey = "Ala ma kota";
        List<String> userKeyList = new ArrayList<>();
        userKeyList = Arrays.asList(userKey.split(""));
        System.out.println(userKeyList);
        for(String letter : userKeyList){
            if(letter.equalsIgnoreCase(" ")){
                userKeyList.remove(letter);
            }
        }

        
    }
}