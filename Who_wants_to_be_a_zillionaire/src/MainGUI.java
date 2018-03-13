import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.util.Arrays;

public class MainGUI {

    private JPanel MainPanel;
    private JPanel MenuPanel;
    private JButton gameButton;
    private JButton rulesButton;
    private JPanel RulesPanel;
    private JButton menuButton;
    private JTextField theRulesWillGoTextField;
    private JPanel PlayerPanel;
    private JButton addPlayer;
    private JTextField playerNameEntered;
    private JButton removePlayer;


    private JList startPlayersList;
    private JPanel CategoryPanel;
    private JLabel categoryLogo;
    private JLabel playerActiveLabel;
    private JButton generalKnowledgeSelect;
    private JButton category2Select;
    private JButton category3Select;
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
    private CurrentPlayer curplay;
    private PlayerList playerList;
    private QuizQuestions quiz;
    private CurrentQuestion currq;

    private int[] todisable;
    private int[] perctodisplay;


    public MainGUI() {

        gameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MenuPanel.setVisible(false);
                PlayerPanel.setVisible(true);
                createPlayerList();
            }
        });
        rulesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MenuPanel.setVisible(false);
                RulesPanel.setVisible(true);
            }
        });
        menuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RulesPanel.setVisible(false);
                MenuPanel.setVisible(true);
            }
        });
        addPlayer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String newPlayerName = playerNameEntered.getText();
                if (newPlayerName.equals("")) {
                    JOptionPane.showMessageDialog(MainPanel, "Please enter player name.");
                } else {
                    playerList.addPlayer(newPlayerName);
                    playerNameEntered.setText("");
                }
            }
        });

        removePlayer.addComponentListener(new ComponentAdapter() {
        });
        removePlayer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
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
                PlayerPanel.setVisible(false);
                CategoryPanel.setVisible(true);
                setCurrentPlayer();
                playerActiveLabel.setText("Active Player: " + curplay.getCurrentPlayerName());
                currentMoney.setText("Current Money: £" + curplay.getCurrentPlayerMoney());
            }
        });

        generalKnowledgeSelect.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CategoryPanel.setVisible(false);
                QuestionPanel.setVisible(true);
                genQuestion();
                questionLabel.setText(currq.getCurrentQString());
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
                    JOptionPane.showMessageDialog(MainPanel, "The question is correct!");
                    curplay.setCurrentPlayerMoney();
                    QuestionPanel.setVisible(false);
                    CategoryPanel.setVisible(true);
                    currentMoney.setText("Current Money: £" + curplay.getCurrentPlayerMoney());
                } else {
                    JOptionPane.showMessageDialog(MainPanel, "You have lost the game.");
                    curplay.setCurrentPlayerTurnOver(true);
                    QuestionPanel.setVisible(false);
                    CategoryPanel.setVisible(true);
                    setCurrentPlayer();
                    System.out.println(curplay);
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
                    JOptionPane.showMessageDialog(MainPanel, "The question is correct!");
                    curplay.setCurrentPlayerMoney();
                    QuestionPanel.setVisible(false);
                    CategoryPanel.setVisible(true);
                    currentMoney.setText("Current Money: £" + curplay.getCurrentPlayerMoney());
                } else {
                    JOptionPane.showMessageDialog(MainPanel, "You have lost the game.");
                    System.out.println(curplay.isCurrentPlayerTurn());
                    QuestionPanel.setVisible(false);
                    CategoryPanel.setVisible(true);
                    setCurrentPlayer();
                    System.out.println(curplay);
                    playerActiveLabel.setText("Active Player: " + curplay.getCurrentPlayerName());
                    currentMoney.setText("Current Money: £" + curplay.getCurrentPlayerMoney());
                }
            }
        });
        answer3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int answer = 3;
                if(todisable[0] == answer || todisable[1] == answer){
                    answer3.setEnabled(false);
                }
                boolean rightAnswer = currq.checkAnswer(answer);
                if (rightAnswer == true) {
                    JOptionPane.showMessageDialog(MainPanel, "The question is correct!");
                    curplay.setCurrentPlayerMoney();
                    QuestionPanel.setVisible(false);
                    CategoryPanel.setVisible(true);
                    currentMoney.setText("Current Money: £" + curplay.getCurrentPlayerMoney());
                } else {
                    JOptionPane.showMessageDialog(MainPanel, "You have lost the game.");
                    curplay.setCurrentPlayerTurnOver(true);
                    QuestionPanel.setVisible(false);
                    CategoryPanel.setVisible(true);
                    setCurrentPlayer();
                    System.out.println(curplay);
                    playerActiveLabel.setText("Active Player: " + curplay.getCurrentPlayerName());
                    currentMoney.setText("Current Money: £" + curplay.getCurrentPlayerMoney());
                }
            }
        });
        answer4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int answer = 4;
                if(todisable[0] == answer || todisable[1] == answer){
                    answer4.setEnabled(false);
                }
                boolean rightAnswer = currq.checkAnswer(answer);
                if (rightAnswer == true) {
                    JOptionPane.showMessageDialog(MainPanel, "The question is correct!");
                    curplay.setCurrentPlayerMoney();
                    QuestionPanel.setVisible(false);
                    CategoryPanel.setVisible(true);
                    currentMoney.setText("Current Money: £" + curplay.getCurrentPlayerMoney());
                } else {
                    JOptionPane.showMessageDialog(MainPanel, "You have lost the game.");
                    curplay.setCurrentPlayerTurnOver(true);
                    QuestionPanel.setVisible(false);
                    CategoryPanel.setVisible(true);
                    setCurrentPlayer();
                    System.out.println(curplay);
                    playerActiveLabel.setText("Active Player: " + curplay.getCurrentPlayerName());
                    currentMoney.setText("Current Money: £" + curplay.getCurrentPlayerMoney());
                }
            }
        });
        halfandhalf.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
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
            @Override
            public void actionPerformed(ActionEvent e) {
                perctodisplay = currq.askThePublic();
                System.out.println(Arrays.toString(perctodisplay));
                // TODO: Sort the Array so the right answer gets the most percentage
                // TODO: Look into generating a chart.
            }
        });
    }

    public void createPlayerList() {
        playerList = new PlayerList();

        startPlayersList.setModel(playerList);
    }

    public void setCurrentPlayer() {
        //TODO: End Game using null pointer
        curplay = new CurrentPlayer(playerList);
        if (curplay.getCurrentPlayer() == null) {
            gameOverScreen();
        }
    }

    public void genQuestion() {
        quiz = new QuizQuestions();
        quiz.QuizQuestions();
        currq = new CurrentQuestion(quiz);
    }

    public void gameOverScreen() {

        System.out.println("You have lost the game!");
        System.exit(0);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("MainGUI");
        frame.setContentPane(new MainGUI().MainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}