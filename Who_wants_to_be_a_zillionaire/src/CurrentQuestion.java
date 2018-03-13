import java.util.Random;

public class CurrentQuestion {

    private Question currentQobject;
    private String currentQString;
    private String[] currentAnswers;
    private int currentCorrectAnswer;
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

    public int[] askThePublic(){
        Random rand = new Random();
        int percent1 = rand.nextInt(101);
        int percent2 = rand.nextInt(101 - percent1);
        int percent3 = rand.nextInt(101 - percent1 - percent2);
        int percent4 = 100 - percent1 - percent2 - percent3;
        int[] percentages = {percent1,percent2,percent3,percent4};
        return percentages;
    }

    public boolean checkAnswer(int ans){
        if (ans == currentCorrectAnswer){
           currentQobject.setQuestionAnswered(true);
           return true;


        }else{
            currentQobject.setQuestionAnswered(false);
            return false;
        }
    }


}
