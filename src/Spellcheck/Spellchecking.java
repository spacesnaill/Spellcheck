package Spellcheck;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by Patrick on 11/9/2015.
 */
public class Spellchecking {
    ArrayList<String> wordbank = new ArrayList(); //where we store the possible candidates for the right word
    ChainHashMap dictionary = new ChainHashMap(); //where our word list itself goes


    public Spellchecking(){
        //sets up the dictionary for the rest of the program to use
        Scanner wordlist = null;
        try{
            //wordlist = new FileInputStream("wordlist.txt");
            //BufferedReader d = new BufferedReader(new InputStreamReader(wordlist));
            //System.out.println(d.readLine());
            System.out.println("Loading Dictionary. Please wait.");
            wordlist = new Scanner(new BufferedReader(new FileReader("wordlist.txt")));
            while(wordlist.hasNext()){
                dictionary.put(wordlist.next().toLowerCase());
            }
            //System.out.println(dictionary.get("Africa"));
            /**for(int i = 0; i < 3; i++){
             System.out.println(dictionary.put(wordlist.next()));
             }**/
        }
        catch(IOException e){
            System.err.println("Unable to read the file!");
            JOptionPane.showMessageDialog(null, "Unable to find a word list! You need a text file named wordlist in the same folder as the jar!!!");
            System.exit(-1);
        }
        System.out.println("Dictionary loaded.");
    }

    public String spellChecking(String word){
        if(dictionary.get(word)){
            //System.out.println(word + " is already correctly spelt! Good job!");
            return word + " is already spelled correctly! Good Job!"; //jump out of the spellchecking process, this word is fine
        }
        wordbank.clear();
        swappedLetters(word);
        extraLetters(word);
        missingCharacters(word);
        wrongCharacter(word);
        //System.out.println("Done");
        phoneticSub(word);
        //System.out.println("Done");
        //output
        return "Were you trying to spell this? " + toString();
    }

    public void swappedLetters(String S){
        StringBuilder wordHolder = new StringBuilder(S); //holds the original word
        StringBuilder wordManipulator = new StringBuilder(S); //manipulates the word
        char ch1;
        char ch2;

        for(int i = 0; i < wordHolder.length() - 1; i++){
            wordManipulator.delete(0,wordManipulator.length()); //maintains the integrity of the word
            wordManipulator.append(wordHolder.toString());

            ch1 = wordManipulator.charAt(i); //stores the first character
            ch2 = wordManipulator.charAt(i+1); //stores the second character
            wordManipulator.setCharAt(i, ch2); //sets the first index to the character of the index after it
            wordManipulator.setCharAt(i+1, ch1); //sets the next index to the character of the index before it
            //System.out.println(wordManipulator.toString());

            if(dictionary.get(wordManipulator.toString())){
                wordbank.add(wordManipulator.toString()); //if the word is in the dictionary then we'll add it to the wordbank arraylist
                //System.out.println(wordManipulator.toString() + " was added!");
            }
        }
    }

    public void extraLetters(String S){
        StringBuilder wordHolder = new StringBuilder(S); //holds the original word
        StringBuilder wordManipulator = new StringBuilder(S); //manipulates the word

        for(int i = 0; i < wordHolder.length(); i++){
            wordManipulator.delete(0,wordManipulator.length()); //maintains the integrity of the word
            wordManipulator.append(wordHolder.toString());

            wordManipulator.deleteCharAt(i);

            if(dictionary.get(wordManipulator.toString())){
                wordbank.add(wordManipulator.toString()); //if the word is in the dictionary, add it to our wordbank arraylist
                //System.out.println(wordManipulator.toString() + " was added!");
            }
        }
    }

    public void missingCharacters(String S){
        StringBuilder wordHolder = new StringBuilder(S); //holds the original word
        StringBuilder wordManipulator = new StringBuilder(S); //manipulates the word
        char letter;

        for(int i = 0; i <= wordHolder.length(); i++){
            letter = 'a';
            for(int j = 0; j < 26; j++){
                wordManipulator.delete(0,wordManipulator.length()); //maintains the integrity of the word
                wordManipulator.append(wordHolder.toString());

                wordManipulator.insert(i,letter);
                //System.out.println(wordManipulator.toString());
                if(dictionary.get(wordManipulator.toString())){
                    wordbank.add(wordManipulator.toString()); //if the word is in the dictionary, add it to our wordbank arraylist
                    //System.out.println(wordManipulator.toString() + " was added!");
                }
                letter++; //increment the character through the alphabet
            }//end of nested for loop
        }//end of outer for loop
    }

    public void wrongCharacter(String S){
        StringBuilder wordHolder = new StringBuilder(S); //holds the original word
        StringBuilder wordManipulator = new StringBuilder(S); //manipulates the word
        char letter;

        for(int i = 0; i < wordHolder.length(); i++){
            letter = 'a';
            for(int j = 0; j < 26; j++){
                wordManipulator.delete(0,wordManipulator.length()); //maintains the integrity of the word
                wordManipulator.append(wordHolder.toString());

                wordManipulator.setCharAt(i, letter);
                //System.out.println(wordManipulator);
                if(dictionary.get(wordManipulator.toString())){
                    wordbank.add(wordManipulator.toString()); //if the word is in the dictionary, add it to our wordbank arraylist
                    //System.out.println(wordManipulator.toString() + " was added!");
                }
                letter++;
            }
        }
    }

    public void phoneticSub(String S) {
        StringBuilder wordHolder = new StringBuilder(S); //holds the original word
        StringBuilder wordManipulator = new StringBuilder(S); //manipulates the word
        String[] phonArray = {
                "ph", "gh", "th",
                "au", "ch", "ch", "tion"
        };
        String[] letterArray = {
                "f", "f", "z",
                "o", "k", "sh", "shun"
        };

        //phonetics to letters
        for (int i = 0; i < phonArray.length; i++) {
            wordManipulator.delete(0, wordManipulator.length()); //maintains the integrity of the word
            wordManipulator.append(wordHolder.toString());
            int j;
            j = wordManipulator.indexOf(phonArray[i]);
            if(j != -1) {
                wordManipulator.delete(j, j + phonArray[i].length());
                wordManipulator.insert(j,letterArray[i]);
                //System.out.println(wordManipulator.toString());
                if (dictionary.get(wordManipulator.toString())) {
                    wordbank.add(wordManipulator.toString()); //if the word is in the dictionary, add it to our wordbank arraylist
                    //System.out.println(wordManipulator.toString() + " was added!");
                }
            }
             /**catch (StringIndexOutOfBoundsException e) {
                wordManipulator.delete(wordManipulator.indexOf(phonArray[i], j), wordManipulator.indexOf(phonArray[i], j));
                wordManipulator.append(letterArray[i]);
                if (dictionary.get(wordManipulator.toString())) {
                    wordbank.add(wordManipulator.toString()); //if the word is in the dictionary, add it to our wordbank arraylist
                    System.out.println(wordManipulator.toString() + " was added!");
                }
                break;
            }**/
        }//end of the first for loop

        //letters to phonetics
        for (int i = 0; i < phonArray.length; i++) {
            wordManipulator.delete(0, wordManipulator.length()); //maintains the integrity of the word
            wordManipulator.append(wordHolder.toString());
            int j;

            j = wordManipulator.indexOf(letterArray[i]);
            if(j != -1) {
                wordManipulator.delete(j, j + letterArray[i].length());
                wordManipulator.insert(j,phonArray[i]);
                //System.out.println(wordManipulator.toString());
                if (dictionary.get(wordManipulator.toString())) {
                    wordbank.add(wordManipulator.toString()); //if the word is in the dictionary, add it to our wordbank arraylist
                    //System.out.println(wordManipulator.toString() + " was added!");
                }
            }
            //j = wordManipulator.indexOf(letterArray[i], j);

        }//end of the second for loop
    }//end of the method

    public void phoneticSubTest(String S){
        StringBuilder wordHolder = new StringBuilder(S); //holds the original word
        StringBuilder wordManipulator = new StringBuilder(S); //manipulates the word
        String[] phonArray = {
                "ph", "gh", "th",
                "au", "ch", "ch"
        };
        String[] letterArray = {
                "f", "f", "z",
                "o", "k", "sh"
        };

        //phonetics to letters

    }

    public String toString(){
        if(wordbank.size() == 0){
            return "Sorry, our dictionary doesn't know what you're talking about.";
        }
        String s = "";
        for(int i = 0; i < wordbank.size(); i++){
            s = s + "\n" + wordbank.get(i);
        }
        return s;
    }

}
