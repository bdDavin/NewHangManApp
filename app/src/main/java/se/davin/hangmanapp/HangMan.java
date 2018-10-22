package se.davin.hangmanapp;

import android.content.res.Resources;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class HangMan {

    private ArrayList<String> words = new ArrayList<>();
    private String word;
    private boolean[] foundLetters;
    private List<Character> wrongLetters = new ArrayList<>();
    private List<Character> rightLetters = new ArrayList<>();
    private int triesLeft;

    public HangMan(Resources r) {
        String[] temp = r.getStringArray(R.array.words);
        words.addAll(Arrays.asList(temp));
    }

    public String getBadLetterUsed(){
        String wrongLettersString = "";
        for (int i = 0; i < wrongLetters.size(); i++) {
            wrongLettersString += wrongLetters.get(i);
            if (i != wrongLetters.size() - 1){
                wrongLettersString += ", ";
            }
        }
        return wrongLettersString;
    }

    public String getHiddenWord(){
        String hiddenWord = "";
        for (int i = 0; i < word.length(); i++) {
            if (foundLetters[i]) {
                hiddenWord += word.charAt(i);
            }else{
                hiddenWord += "-";
            }
        }
        return hiddenWord;
    }

    public String getRealWord(){
        return word;

    }

    public int getTriesLeft(){
        return triesLeft;
    }

    public void guess(char guess){
        boolean foundIt = false;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (guess == c) {
                foundLetters[i] = true;
                foundIt = true;
            }
        }
        if (foundIt){
            rightLetters.add(guess);
        }else{
            wrongLetters.add(guess);
            triesLeft--;
        }
    }

    public boolean hasLost(){
        if (triesLeft == 0){
            return true;
        }
        return false;
    }

    public boolean hasUsedLetter(char c){
        if (rightLetters.contains(c) || wrongLetters.contains(c)){
            return true;
        }
        return false;
    }

    public boolean hasWon(){
        int check = 0;
        for (int i = 0; i < word.length(); i++) {
            if (foundLetters[i]) {
                check++;
            }
        }
        return check == foundLetters.length;

    }

    public void newWord(){
        Random r = new Random();
        int randomInt = r.nextInt((words.size()));
        this.word = words.get(randomInt);

        rightLetters.clear();
        wrongLetters.clear();
        triesLeft = 10;

        this.foundLetters = new boolean[word.length()];
        for (int i = 0; i < word.length(); i++) {
            foundLetters[i] = false;
        }
    }
}
