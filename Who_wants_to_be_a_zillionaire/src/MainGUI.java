import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;

public class MainGUI {

    private JPanel MainPanel;
    private JPanel MenuPanel;
    private JButton gameButton;
    private JButton rulesButton;
    private JPanel RulesPanel;
    private JButton menuButton;
    private JPanel PlayerPanel;
    private JButton addPlayer;
    private JTextField playerNameEntered;
    private JButton removePlayer;


    private JList startPlayersList;
    private JPanel CategoryPanel;
    private JLabel categoryLogo;
    private JLabel playerActiveLabel;
    private JButton generalKnowledgeSelect;
    private JButton videoGameSelect;
    private JButton musicSelect;
    private JLabel currentMoney;
    private JButton startGame;
    private JPanel QuestionPanel;
    private JButton answer1;
    private JButton answer2;
    private JButton answer3;
    private JButton answer4;
    private JButton halfandhalf;
    private JButton askthepublic;
    private JLabel questionLabel;
    private JLabel answer2percent;
    private JLabel answer1percent;
    private JLabel answer3percent;
    private JLabel answer4percent;
    private JPanel GameOverPanel;
    private JLabel gameoverlogo;
    private JButton backtomenu;
    private JList endPlayerList;
    private JButton exitgameendscreen;
    private JButton exitgamemenuscreen;
    private JTextArea questionArea;
    private JLabel logo;
    private JTextArea Rules;

    private CurrentPlayer curplay;
    private PlayerList playerList;
    private QuizQuestions quiz;
    private CurrentQuestion currq;
    private Sound buttonClickSound = new Sound();

    private int[] todisable;
    ArrayList<JLabel> percentLabel = new ArrayList<>(Arrays.asList(answer1percent,answer2percent,answer3percent,answer4percent));


    public MainGUI() {

        gameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MenuPanel.setVisible(false);
                PlayerPanel.setVisible(true);
                buttonClickSound.playSound(0);
                createPlayerList();
            }
        });
        rulesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MenuPanel.setVisible(false);
                RulesPanel.setVisible(true);
                buttonClickSound.playSound(0);
            }
        });
        menuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RulesPanel.setVisible(false);
                MenuPanel.setVisible(true);
                buttonClickSound.playSound(0);
            }
        });
        addPlayer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buttonClickSound.playSound(0);
                String newPlayerName = playerNameEntered.getText();
                newPlayerName.toLowerCase();
                if (newPlayerName.equals("")) {
                    JOptionPane.showMessageDialog(MainPanel, "Please enter player name.");
                } else if(!newPlayerName.matches("[a-zA-Z]+")){
                    JOptionPane.showMessageDialog(MainPanel, "No special characters or numbers allowed.");
                } else {
                    playerList.addPlayer(newPlayerName);
                    playerNameEntered.setText("");
                }
            }
        });

        removePlayer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buttonClickSound.playSound(0);
                int selectedIndex = startPlayersList.getSelectedIndex();
                if (selectedIndex == -1) {
                    JOptionPane.showMessageDialog(MainPanel, "Please select a player to remove.");
                } else {
                    Player playN = playerList.getElementAt(selectedIndex);
                    if (JOptionPane.showConfirmDialog(MainPanel, "Delete player? " + playN,
                            "Delete confirmation", JOptionPane.YES_NO_OPTION)
                            == JOptionPane.YES_OPTION) ;
                    {
                        playerList.removePlayer(playN.getPlayerName());
                        playerNameEntered.setText("");
                        startPlayersList.clearSelection();

                    }
                }
            }
        });
        startGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buttonClickSound.playSound(0);
                if(playerList.size() == 0){
                    JOptionPane.showMessageDialog(MainPanel, "Enter a player.");
                    return;
                }
                PlayerPanel.setVisible(false);
                CategoryPanel.setVisible(true);
                genQuestionList();
                genQuestions(0);
                genQuestions(1);
                genQuestions(2);
                setCurrentPlayer();
                playerActiveLabel.setText("Active Player: " + curplay.getCurrentPlayerName());
                currentMoney.setText("Current Money: £" + curplay.getCurrentPlayerMoney());
            }
        });

        generalKnowledgeSelect.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buttonClickSound.playSound(0);
                CategoryPanel.setVisible(false);
                QuestionPanel.setVisible(true);
                getQuestion(0);
                questionArea.setText(currq.getCurrentQString());
                answer1.setText(currq.getCurrentAnswerLocation(0));
                answer2.setText(currq.getCurrentAnswerLocation(1));
                answer3.setText(currq.getCurrentAnswerLocation(2));
                answer4.setText(currq.getCurrentAnswerLocation(3));
                answer1.setEnabled(true);
                answer2.setEnabled(true);
                answer3.setEnabled(true);
                answer4.setEnabled(true);
            }
        });

        videoGameSelect.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buttonClickSound.playSound(0);
                CategoryPanel.setVisible(false);
                QuestionPanel.setVisible(true);
                getQuestion(1);
                questionArea.setText(currq.getCurrentQString());
                answer1.setText(currq.getCurrentAnswerLocation(0));
                answer2.setText(currq.getCurrentAnswerLocation(1));
                answer3.setText(currq.getCurrentAnswerLocation(2));
                answer4.setText(currq.getCurrentAnswerLocation(3));
                answer1.setEnabled(true);
                answer2.setEnabled(true);
                answer3.setEnabled(true);
                answer4.setEnabled(true);
            }
        });

        musicSelect.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buttonClickSound.playSound(0);
                CategoryPanel.setVisible(false);
                QuestionPanel.setVisible(true);
                getQuestion(2);
                questionArea.setText(currq.getCurrentQString());
                answer1.setText(currq.getCurrentAnswerLocation(0));
                answer2.setText(currq.getCurrentAnswerLocation(1));
                answer3.setText(currq.getCurrentAnswerLocation(2));
                answer4.setText(currq.getCurrentAnswerLocation(3));
                answer1.setEnabled(true);
                answer2.setEnabled(true);
                answer3.setEnabled(true);
                answer4.setEnabled(true);
            }
        });

        answer1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int answer = 1;
                boolean rightAnswer = currq.checkAnswer(answer);
                if (rightAnswer == true) {
                    buttonClickSound.playSound(2);
                    JOptionPane.showMessageDialog(MainPanel, "The question is correct!");
                    curplay.setCurrentPlayerMoney();
                    clearLabels();
                    currq.setCurrentQuestionAnswered(true);
                    QuestionPanel.setVisible(false);
                    CategoryPanel.setVisible(true);
                    if(curplay.getCurrentPlayerMoney() >= 1638400){
                        JOptionPane.showMessageDialog(MainPanel, "You have won the game!");
                        curplay.setCurrentPlayerTurnOver(true);
                        setCurrentPlayer();
                        playerActiveLabel.setText("Active Player: " + curplay.getCurrentPlayerName());
                        currentMoney.setText("Current Money: £" + curplay.getCurrentPlayerMoney());
                    }else {
                        currentMoney.setText("Current Money: £" + curplay.getCurrentPlayerMoney());
                    }
                } else {
                    buttonClickSound.playSound(1);
                    JOptionPane.showMessageDialog(MainPanel, "You have lost the game.");
                    curplay.setCurrentPlayerTurnOver(true);
                    clearLabels();
                    askthepublic.setEnabled(true);
                    halfandhalf.setEnabled(true);
                    QuestionPanel.setVisible(false);
                    CategoryPanel.setVisible(true);
                    setCurrentPlayer();
                    System.out.println(curplay.isCurrentPlayerTurn());
                    playerActiveLabel.setText("Active Player: " + curplay.getCurrentPlayerName());
                    currentMoney.setText("Current Money: £" + curplay.getCurrentPlayerMoney());
                }
            }
        });
        answer2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int answer = 2;
                boolean rightAnswer = currq.checkAnswer(answer);
                if (rightAnswer == true) {
                    buttonClickSound.playSound(2);
                    JOptionPane.showMessageDialog(MainPanel, "The question is correct!");
                    curplay.setCurrentPlayerMoney();
                    clearLabels();
                    currq.setCurrentQuestionAnswered(true);
                    QuestionPanel.setVisible(false);
                    CategoryPanel.setVisible(true);
                    if(curplay.getCurrentPlayerMoney() >= 1638400){
                        JOptionPane.showMessageDialog(MainPanel, "You have won the game!");
                        curplay.setCurrentPlayerTurnOver(true);
                        setCurrentPlayer();
                        playerActiveLabel.setText("Active Player: " + curplay.getCurrentPlayerName());
                        currentMoney.setText("Current Money: £" + curplay.getCurrentPlayerMoney());
                    }else {
                        currentMoney.setText("Current Money: £" + curplay.getCurrentPlayerMoney());
                    }
                } else {
                    buttonClickSound.playSound(1);
                    JOptionPane.showMessageDialog(MainPanel, "You have lost the game.");
                    clearLabels();
                    askthepublic.setEnabled(true);
                    halfandhalf.setEnabled(true);
                    curplay.setCurrentPlayerTurnOver(true);
                    QuestionPanel.setVisible(false);
                    CategoryPanel.setVisible(true);
                    setCurrentPlayer();
                    System.out.println(curplay.isCurrentPlayerTurn());
                    playerActiveLabel.setText("Active Player: " + curplay.getCurrentPlayerName());
                    currentMoney.setText("Current Money: £" + curplay.getCurrentPlayerMoney());
                }
            }
        });
        answer3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int answer = 3;
                boolean rightAnswer = currq.checkAnswer(answer);
                if (rightAnswer == true) {
                    buttonClickSound.playSound(2);
                    JOptionPane.showMessageDialog(MainPanel, "The question is correct!");
                    curplay.setCurrentPlayerMoney();
                    clearLabels();
                    currq.setCurrentQuestionAnswered(true);
                    QuestionPanel.setVisible(false);
                    CategoryPanel.setVisible(true);
                    if(curplay.getCurrentPlayerMoney() >= 1638400){
                        JOptionPane.showMessageDialog(MainPanel, "You have won the game!");
                        curplay.setCurrentPlayerTurnOver(true);
                        setCurrentPlayer();
                        playerActiveLabel.setText("Active Player: " + curplay.getCurrentPlayerName());
                        currentMoney.setText("Current Money: £" + curplay.getCurrentPlayerMoney());
                    }else {
                        currentMoney.setText("Current Money: £" + curplay.getCurrentPlayerMoney());
                    }
                } else {
                    buttonClickSound.playSound(1);
                    JOptionPane.showMessageDialog(MainPanel, "You have lost the game.");
                    clearLabels();
                    askthepublic.setEnabled(true);
                    halfandhalf.setEnabled(true);
                    curplay.setCurrentPlayerTurnOver(true);
                    QuestionPanel.setVisible(false);
                    CategoryPanel.setVisible(true);
                    setCurrentPlayer();
                    System.out.println(curplay.isCurrentPlayerTurn());
                    playerActiveLabel.setText("Active Player: " + curplay.getCurrentPlayerName());
                    currentMoney.setText("Current Money: £" + curplay.getCurrentPlayerMoney());
                }
            }
        });
        answer4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int answer = 4;
                boolean rightAnswer = currq.checkAnswer(answer);
                if (rightAnswer == true) {
                    buttonClickSound.playSound(2);
                    JOptionPane.showMessageDialog(MainPanel, "The question is correct!");
                    curplay.setCurrentPlayerMoney();
                    clearLabels();
                    currq.setCurrentQuestionAnswered(true);

                    QuestionPanel.setVisible(false);
                    CategoryPanel.setVisible(true);
                    if(curplay.getCurrentPlayerMoney() >= 1638400){
                        JOptionPane.showMessageDialog(MainPanel, "You have won the game!");
                        curplay.setCurrentPlayerTurnOver(true);
                        setCurrentPlayer();
                        playerActiveLabel.setText("Active Player: " + curplay.getCurrentPlayerName());
                        currentMoney.setText("Current Money: £" + curplay.getCurrentPlayerMoney());
                    }else {
                        currentMoney.setText("Current Money: £" + curplay.getCurrentPlayerMoney());
                    }
                } else {
                    buttonClickSound.playSound(1);
                    JOptionPane.showMessageDialog(MainPanel, "You have lost the game.");
                    clearLabels();
                    askthepublic.setEnabled(true);
                    halfandhalf.setEnabled(true);
                    curplay.setCurrentPlayerTurnOver(true);
                    QuestionPanel.setVisible(false);
                    CategoryPanel.setVisible(true);
                    setCurrentPlayer();
                    System.out.println(curplay.isCurrentPlayerTurn());
                    playerActiveLabel.setText("Active Player: " + curplay.getCurrentPlayerName());
                    currentMoney.setText("Current Money: £" + curplay.getCurrentPlayerMoney());
                }
            }
        });
        halfandhalf.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buttonClickSound.playSound(0);
                halfandhalf.setEnabled(false);
                todisable = currq.halfAndHalf(currq.getCurrentCorrectAnswer());
                String disable1="answer"+Integer.toString(todisable[0]);
                String disable2="answer"+Integer.toString(todisable[1]);
                answer1.setName("answer1");
                answer2.setName("answer2");
                answer3.setName("answer3");
                answer4.setName("answer4");
                String[] ans ={answer1.getName(),answer2.getName(),answer3.getName(),answer4.getName()};

                for(int i=0; i < 3; i++) {
                    if (ans[0].equals(disable1) || ans[0].equals(disable2)) {
                        if(answer1.isEnabled()){
                            answer1.setEnabled(false);
                        }
                    }
                    if (ans[1].equals(disable1) || ans[1].equals(disable2)) {
                        if(answer2.isEnabled()) {
                            answer2.setEnabled(false);
                        }
                    }
                    if (ans[2].equals(disable1) || ans[2].equals(disable2)) {
                        if(answer3.isEnabled()) {
                            answer3.setEnabled(false);
                        }
                    }
                    if (ans[3].equals(disable1) || ans[3].equals(disable2)) {
                        if(answer4.isEnabled()) {
                            answer4.setEnabled(false);
                        }
                    }

                }
            }
        });
        askthepublic.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                buttonClickSound.playSound(0);
                askthepublic.setEnabled(false);
                for(JLabel percentLabel : percentLabel){
                    percentLabel.setVisible(true);
                }
                currq.askThePublic(percentLabel);


            }
        });
        backtomenu.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                buttonClickSound.playSound(0);
                GameOverPanel.setVisible(false);
                MenuPanel.setVisible(true);
            }
        });
        exitgameendscreen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buttonClickSound.playSound(0);
                exitBox();
            }
        });
        exitgamemenuscreen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buttonClickSound.playSound(0);
                exitBox();
            }
        });
    }

    public void clearLabels(){
        for(JLabel percentLabel : percentLabel){
            percentLabel.setVisible(false);
        }
    }

    public void createPlayerList() {
        playerList = new PlayerList();

        startPlayersList.setModel(playerList);
    }

    public void updatePlayerList(){
        endPlayerList.setModel(playerList);
    }


    public void setCurrentPlayer() {
        curplay = new CurrentPlayer(playerList);
        if (curplay.getCurrentPlayer() == null) {
            updatePlayerList();
            gameOverScreen();
        }
    }
    public void setQuestionAnswered(){
        currq.setCurrentQuestionAnswered(true);
    }

    public void genQuestionList(){
        quiz = new QuizQuestions();
    }

    public void genQuestions(int categoryInt) {
        quiz.QuizQuestions(categoryInt);
    }

    public void getQuestion(int categoryInt) {
        currq = new CurrentQuestion(quiz,categoryInt);
    }


    public void gameOverScreen() {
        CategoryPanel.setVisible(false);
        GameOverPanel.setVisible(true);
    }

    public void exitBox(){
        int reply = JOptionPane.showConfirmDialog(null, "Are you sure you want to exit?",
                "Exit", JOptionPane.YES_NO_OPTION);
        if(reply == JOptionPane.YES_OPTION){
            System.exit(0);
        }else{
            return;
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Who Wants to be a Zillionaire?");
        frame.setContentPane(new MainGUI().MainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

    }
}