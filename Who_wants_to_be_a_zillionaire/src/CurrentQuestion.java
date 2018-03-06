public class CurrentQuestion {

    private Question currentQobject;
    private String currentQString;
    private String[] currentAnswers;
    private int currentCorrectAnswer;
    private boolean currentQuestionAnswered;

    // TODO:Get constructor to recognise the private variables of current question.

    public CurrentQuestion(QuizQuestions quizQuestions){
        this.currentQobject = quizQuestions.generateGeneralQuestion();
        this.currentQString = currentQobject.getQuestion();
        this.currentAnswers = currentQobject.getAnswers();
        this.currentCorrectAnswer = currentQobject.getCorrectAnswer();
        this.currentQuestionAnswered = currentQobject.isQuestionAnswered();
    }


}
