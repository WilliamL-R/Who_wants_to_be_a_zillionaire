import java.io.Serializable;
import java.util.Arrays;


public class Question implements Serializable {
    private String question;
    private String[] answers;
    private int correctAnswer;
    private boolean questionAnswered;

    public Question(String question, String[] answers, int correctAnswer, boolean questionAnswered){
        this.question = question;
        this.answers = answers;
        this.correctAnswer = correctAnswer;
        this.questionAnswered = questionAnswered;
    }

    public String getQuestion() {
        return question;
    }

    public String[] getAnswers() {
        return answers;
    }

    public int getCorrectAnswer() {
        return correctAnswer;
    }

    public boolean isQuestionAnswered() {
        return questionAnswered;
    }

    public void setQuestionAnswered(boolean questionAnswered) {
        this.questionAnswered = questionAnswered;
    }

    @Override
    public String toString() {
        return "Question{" +
                "question='" + question + '\'' +
                ", answers=" + Arrays.toString(answers) +
                ", correctAnswer=" + correctAnswer +
                ", questionAnswered=" + questionAnswered +
                '}';
    }
}
