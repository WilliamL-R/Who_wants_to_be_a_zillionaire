import javax.swing.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

public class CurrentQuestion {

    private Question currentQobject;
    private String currentQString;
    private String[] currentAnswers;
    private int currentCorrectAnswer;
    private int currentQuestionPercentage;
    private boolean currentQuestionAnswered;



    public CurrentQuestion(QuizQuestions quizQuestions){
        this.currentQobject = quizQuestions.generateGeneralQuestion();
        this.currentQString = currentQobject.getQuestion();
        this.currentAnswers = currentQobject.getAnswers();
        this.currentCorrectAnswer = currentQobject.getCorrectAnswer();
        this.currentQuestionAnswered = currentQobject.isQuestionAnswered();
    }

    public String getCurrentQString() {
        return currentQString;
    }

    public void setCurrentQString(String currentQString) {
        this.currentQString = currentQString;
    }

    public String getCurrentAnswerLocation(int indexLocation) {
       String answer = currentAnswers[indexLocation];
        return answer;
    }

    public String[] getCurrentAnswers(){
        return currentAnswers;
    }

    public int getCurrentCorrectAnswer() {
        return currentCorrectAnswer;
    }

    public void setCurrentCorrectAnswer(int currentCorrectAnswer) {
        this.currentCorrectAnswer = currentCorrectAnswer;
    }

    public boolean isCurrentQuestionAnswered() {
        return currentQuestionAnswered;
    }

    public void setCurrentQuestionAnswered(boolean currentQuestionAnswered) {
        this.currentQuestionAnswered = currentQuestionAnswered;
    }

    public int[] halfAndHalf( int currentcorans){
        Random rand = new Random();
        int random = rand.nextInt(4);
        int[] anstodisable = new int[2];

        for(int i=0; i < 2; i++){
           while(random == currentcorans || random == anstodisable[0]){
             random = rand.nextInt(4) + 1;
           }
           anstodisable[i] = random;
        }
        return anstodisable;
    }

    public void askThePublic(ArrayList<JLabel> labels){
        Random rand = new Random();
        Integer percent1 = rand.nextInt(101);
        Integer percent2 = rand.nextInt(101 - percent1);
        Integer percent3 = rand.nextInt(101 - percent1 - percent2);
        Integer percent4 = 100 - percent1 - percent2 - percent3;
        Integer[] percentages = {percent1,percent2,percent3,percent4};

        ArrayList<Integer> possiblePerc = new ArrayList<>(Arrays.asList(percentages));
        ArrayList<String> potAnswers = new ArrayList<>(Arrays.asList(currentAnswers));
        Integer highestValue = Collections.max(new ArrayList<>(Arrays.asList(percentages)));


        for(int i=0; i<percentages.length; i++){
            boolean rightAnswer = checkAnswer(i+1);
            if(rightAnswer == true){
                Collections.swap(possiblePerc,
                potAnswers.indexOf(getCurrentAnswerLocation(currentCorrectAnswer)),
                possiblePerc.indexOf(highestValue));
                System.out.println(potAnswers.indexOf(getCurrentAnswerLocation(currentCorrectAnswer)));
                System.out.println(possiblePerc.indexOf(highestValue));

            }
            //i = highestValue;
            //System.out.println(possiblePerc.indexOf(highestValue));
        }
        for(int i = 0; i<labels.size(); i++){
            labels.get(i).setText(possiblePerc.get(i).toString());

        }
    }


    public boolean checkAnswer(int ans){
        if (ans == currentCorrectAnswer){
           currentQobject.setQuestionAnswered(true);
           return true;

        }else{
            currentQobject.setQuestionAnswered(true);
            return false;
        }
    }


}
