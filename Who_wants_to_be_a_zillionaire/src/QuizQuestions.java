
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;
import java.util.ArrayList;
import java.util.Scanner;

public class QuizQuestions {

    private ArrayList<ArrayList<Question>> questionTopics = new ArrayList<ArrayList<Question>>();

    private ArrayList<Question> generalQuestions = new ArrayList<>();
    private ArrayList<Question> gameQuestions = new ArrayList<>();
    private ArrayList<Question> musicQuestions = new ArrayList<>();


    private String[] fileNames = {"src/generalQuestions.txt", "src/gameQuestions.txt", "src/musicQuestions.txt"};



    public void QuizQuestions(int categoryInt){
        try{
            initialiseLists();
            String fileName = fileNames[categoryInt];
            FileReader file = new FileReader(fileName);
            BufferedReader reader = new BufferedReader(file);
            Scanner scanner = new Scanner(reader);

            String line;
            String question = "";
            String[] answers = null;

            int correctAnswer = 0;

            int numberOfAnswers = 0;
            int counter = 0;
            boolean questionanswered = false;

            do{
                do{
                    line =scanner.nextLine();

                    if (line.contains("?")){ // stores the question
                        question = line;
                    } else if (counter == 0 && line.length() == 1){ //   the numberOfAnswers
                        numberOfAnswers = Integer.valueOf(line);
                        answers = new String[numberOfAnswers];
                    } else if (line.contains(")")){ // stores the answers
                        answers[counter++] = line;
                    } else if (Character.isDigit(line.indexOf(0)) || counter == numberOfAnswers) { //Stores the correctAnswer
                        correctAnswer = Integer.valueOf(line);
                    }
                } while (correctAnswer == 0);

                questionTopics.get(categoryInt).add(new Question(question, answers, correctAnswer, questionanswered));
                numberOfAnswers = 0;
                counter = 0;
                correctAnswer = 0;
                questionanswered = false;



            }while (scanner.hasNext());

            file.close();
            reader.close();
            scanner.close();
        }catch(IOException e){
            e.printStackTrace();
        }
    }



    public Question generateQuestion(int categoryInt){
        Question questionN;
        Random rand = new Random();

        int indexLocation = rand.nextInt((questionTopics.get(categoryInt).size() - 0)+ 0);
        questionN = (Question) questionTopics.get(categoryInt).get(indexLocation);
        while (questionN.isQuestionAnswered() == true){
            indexLocation = rand.nextInt((questionTopics.get(categoryInt).size() - 0)+ 0);
            questionN = (Question) questionTopics.get(categoryInt).get(indexLocation);
        }
        if (indexLocation == -1){
            return null;
        }else{
            return questionN;
        }
    }

    public void setAnswered(Question questionObj){
        boolean loopDone = false;
        for(int count1=0; count1<=questionTopics.size(); count1++){
           ArrayList<Question> arrayName = questionTopics.get(count1);
           for(int count2=0; count2<arrayName.size(); count2++){
               Question questcheck = arrayName.get(count2);
               if(questcheck.getQuestion().equals(questionObj.getQuestion())){
                   questcheck.setQuestionAnswered(true);
                   arrayName.set(count2, questionObj);
                   loopDone = true;
                   if (loopDone == true) {
                       break;
                   }
               }
           }
            if (loopDone == false){
                continue;
            }else{
                break;
            }
        }
    }

    public void initialiseLists(){
        int i = 0;
        if (i == 1){
            return;
        }else{
            questionTopics.add(generalQuestions);
            questionTopics.add(gameQuestions);
            questionTopics.add(musicQuestions);
            i++;
        }
    }
}

