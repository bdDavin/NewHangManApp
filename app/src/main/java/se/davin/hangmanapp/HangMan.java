package se.davin.hangmanapp;

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

    public HangMan() {
        String[] array = {"bridge", "bone", "grapes", "bell", "jellyfish", "bunny", "truck", "grass", "door", "monkey", "spider", "bread", "ears", "bowl", "bracelet", "alligator", "bat", "clock", "lollipop", "moon", "doll", "orange", "ear", "basketball", "bike", "airplane", "pen", "inchworm", "seashell", "rocket", "cloud", "bear", "corn", "chicken", "purse", "glasses", "blocks", "carrot", "turtle", "pencil", "horse", "dinosaur", "head", "lamp", "snowman", "ant", "giraffe", "cupcake", "chair", "leaf", "bed", "snail", "baby", "balloon", "bus", "cherry", "crab", "football", "branch", "robot"};
        words.addAll(Arrays.asList(array));
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
