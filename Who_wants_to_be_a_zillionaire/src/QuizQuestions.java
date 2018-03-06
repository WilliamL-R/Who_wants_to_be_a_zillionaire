
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;
import java.util.ArrayList;
import java.util.Scanner;

public class QuizQuestions {

    private ArrayList<Question> generalQuestions = new ArrayList<>();


    public void QuizQuestions(){
        try{
            FileReader file = new FileReader("generalQuestions.txt");
            BufferedReader reader = new BufferedReader(file);
            Scanner scanner = new Scanner(reader);

            String line;
            String question = "";
            String[] answers = null;

            int correctAnswer = 0;

            int numberOfAnswers = 0;
            int counter = 0;

            do{
                do{
                    line =scanner.nextLine();

                    if (line.contains("?")){ // stores the question
                        question = line;
                    } else if (counter == 0 && line.length() == 1){ // stores the numberOfAnswers
                        numberOfAnswers = Integer.valueOf(line);
                        answers = new String[numberOfAnswers];
                    } else if (line.contains(")")){ // stores the answers
                        answers[counter++] = line;
                    } else if (Character.isDigit(line.indexOf(0)) || counter == numberOfAnswers) { //Stores the correctAnswer
                        correctAnswer = Integer.valueOf(line);
                    }
                } while (correctAnswer == 0);

                generalQuestions.add(new Question(question, answers, correctAnswer));
                numberOfAnswers = 0;
                counter = 0;
                correctAnswer = 0;

            }while (scanner.hasNext());

            file.close();
            reader.close();
            scanner.close();
        }catch(IOException e){
            e.printStackTrace();
        }
    }



    public Question generateGeneralQuestion(){
        Question questionN;
        Random rand = new Random();
        int indexLocation = rand.nextInt((generalQuestions.size() - 0)+ 0);
        questionN = (Question) generalQuestions.get(indexLocation);
        while (questionN.isQuestionAnswered() == true){
            indexLocation = rand.nextInt((generalQuestions.size() - 0)+ 0);
            questionN = (Question) generalQuestions.get(indexLocation);
        }
        if (indexLocation == -1){
            return null;
        }else{
            return (Question)generalQuestions.get(indexLocation);
        }
    }


}

