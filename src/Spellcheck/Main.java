package Spellcheck;

import java.io.*;
import java.util.Scanner;
import javax.swing.JOptionPane;

/**
 * Created by Patrick on 11/7/2015.
 */
public class Main {

    public static void main(String [] args)
    {
       /** ChainHashMap dictionary = new ChainHashMap();

        /**FileInputStream wordlist;
        Scanner input = new Scanner("wordlist.txt");
        while(input.hasNextLine()){
            String data = input.nextLine();
            System.out.println(data);
        }

        Scanner wordlist = null;
        try{
            //wordlist = new FileInputStream("wordlist.txt");
            //BufferedReader d = new BufferedReader(new InputStreamReader(wordlist));
            //System.out.println(d.readLine());
            wordlist = new Scanner(new BufferedReader(new FileReader("wordlist.txt")));
            while(wordlist.hasNext()){
                dictionary.put(wordlist.next().toLowerCase());

            }
            System.out.println(dictionary.get("Africa"));
            /**for(int i = 0; i < 3; i++){
                System.out.println(dictionary.put(wordlist.next()));
            }
        }
        catch(IOException e){
            System.err.println("Unable to read the file!");
            System.exit(-1);
        }

        char testChar = 'a';

        StringBuilder test = new StringBuilder("Test");
        for(int i = 0; i < 4; i++ ){
            test.insert(2*i, testChar++);
        }
        System.out.println(test.toString());**/


        Spellchecking checker = new Spellchecking();
        /**System.out.println("This is a test for swapped letters using the String wsash, therefore swash should be found in the outputs:");
        System.out.println(checker.spellChecking("wsash"));
        System.out.println("This is a test for missing letters using the String issing, therefore missing should be found in the outputs:");
        System.out.println(checker.spellChecking("issing"));
        System.out.println("This is a test for extra letters using the String extraa, therefore extra should be found in the outputs:");
        System.out.println(checker.spellChecking("extraa"));
        System.out.println("This is a test for wrong letters using the String extraa, therefore wrong should be found in the outputs:");
        System.out.println(checker.spellChecking("trong"));
        System.out.println("This is a test for phonetic substitution using the String fonetic, therefore phonetic should be found in the outputs:");
        System.out.println(checker.spellChecking("fonetic"));
        System.out.println("This is a test for a nonsense word using the String fdsgaage, the program should provide an error message:");
        System.out.println(checker.spellChecking("fdsgaage"));**/
        //Scanner input = new Scanner(System.in);
        //System.out.print("Please enter the word you want checked: ");
        //String inputString = input.nextLine();

        int reply = JOptionPane.showConfirmDialog(null, "Would you like to test the program? Press Yes to run a preset test, otherwise press No to skip straight to inputting your own words.", "Testing", JOptionPane.YES_NO_OPTION);
        if(reply ==JOptionPane.YES_OPTION){
            JOptionPane.showMessageDialog(null,"This is a test for missing letters using the String issing, therefore missing should be found in the outputs:" + "\n" + checker.spellChecking("issing"));
            JOptionPane.showMessageDialog(null,"This is a test for swapped letters using the String wsash, therefore swash should be found in the outputs:" + "\n" + checker.spellChecking("wsash") );
            JOptionPane.showMessageDialog(null,"This is a test for extra letters using the String extraa, therefore extra should be found in the outputs:" + "\n" + checker.spellChecking("extraa") );
            JOptionPane.showMessageDialog(null,"This is a test for wrong letters using the String trong, therefore wrong should be found in the outputs:" + "\n" + checker.spellChecking("trong") );
            JOptionPane.showMessageDialog(null,"This is a test for phonetic substitution using the String fonetic, therefore phonetic should be found in the outputs:" + "\n" + checker.spellChecking("fonetic") );
            JOptionPane.showMessageDialog(null,"This is a test for a nonsense word using the String fdsgaage, the program should provide an error message:" + "\n" + checker.spellChecking("fdsgaage") );

        }

        String input;
        do{
            input = JOptionPane.showInputDialog(null, "Please Enter the word you want checked or type 'quit' to exit: ");
            if(input.equals("quit")){
                JOptionPane.showMessageDialog(null, "Thank you for using this Spellchecking Program, Goodbye.");
                break;
            }
            JOptionPane.showMessageDialog(null, checker.spellChecking(input.toLowerCase()));

        }while(!(input.equals("quit")));
        //StringBuilder test = new StringBuilder("phat");
        //System.out.println(test.indexOf("ch"));
        //System.out.println(test.replace(test.indexOf("ch"),test.indexOf("ch")+2,"sh"));
    }


}
