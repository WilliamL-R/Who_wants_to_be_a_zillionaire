import javax.swing.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

public class CurrentQuestion {

    private QuizQuestions currentQuiz;
    private Question currentQobject;
    private String currentQString;
    private String[] currentAnswers;
    private int currentCorrectAnswer;
    private boolean currentQuestionAnswered;



    public CurrentQuestion(QuizQuestions quizQuestions, int categoryInt){
        this.currentQuiz  = quizQuestions;
        this.currentQobject = currentQuiz.generateQuestion(categoryInt);
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
        System.out.println("currentQuestionAnswered: " + currentQuestionAnswered);
        this.currentQuestionAnswered = currentQuestionAnswered;
        System.out.println("this: " + this.currentQuestionAnswered);
        currentQobject.setQuestionAnswered(currentQuestionAnswered);
        currentQuiz.setAnswered(currentQobject);
        System.out.println("currentQobject: " + currentQobject);
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
        int correctperc = -1;
        Integer percent1 = rand.nextInt(101);
        Integer percent2 = rand.nextInt(101 - percent1);
        Integer percent3 = rand.nextInt(101 - percent1 - percent2);
        Integer percent4 = 100 - percent1 - percent2 - percent3;
        Integer[] percentages = {percent1,percent2,percent3,percent4};
        String[] PERCENTAGETEXT = {"Answer 1: ", "Answer 2: ", "Answer 3: ", "Answer 4: "};


        ArrayList<Integer> possiblePerc = new ArrayList<>(Arrays.asList(percentages));
        Integer highestValue = Collections.max(new ArrayList<>(Arrays.asList(percentages)));



        for(int i=0; i<=percentages.length; i++){
            boolean rightAnswer = checkAnswer(i);
            if(rightAnswer == true){
                Collections.swap(possiblePerc,
                 i-1 ,
                possiblePerc.indexOf(highestValue));

                labels.get(i-1).setText(PERCENTAGETEXT[i-1]+ highestValue.toString() + "%");

                correctperc = i - 1;
                break;
            }

        }
        for(int i = 0; i<labels.size(); i++){
            if (i == correctperc){
                continue;
            }else{
                labels.get(i).setText(PERCENTAGETEXT[i] + possiblePerc.get(i).toString() + "%");
            }

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


    @Override
    public String toString() {
        return "CurrentQuestion{" +
                "currentQobject=" + currentQobject +
                ", currentQString='" + currentQString + '\'' +
                ", currentAnswers=" + Arrays.toString(currentAnswers) +
                ", currentCorrectAnswer=" + currentCorrectAnswer +
                ", currentQuestionAnswered=" + currentQuestionAnswered +
                '}';
    }
}
