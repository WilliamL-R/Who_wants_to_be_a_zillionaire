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

    public String getCurrentAnswers(int indexLocation) {
       String answer = currentAnswers[indexLocation];
        return answer;
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

    public boolean checkAnswer(int ans){
//        System.out.println(ans);
//        System.out.println(currentCorrectAnswer);
        if (ans == currentCorrectAnswer){
           currentQobject.setQuestionAnswered(true);
           return true;


        }else{
            currentQobject.setQuestionAnswered(false);
            return false;
        }
        //if ans == currentCorrectAnswer
        //add to player money
        //question answered to true
        //if ans != currentCorrectAnswer
        //end turn
    }


}
