import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;

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
    private JButton help1;
    private JButton help2;
    private JLabel questionLabel;
    private CurrentPlayer curplay;
    private PlayerList playerList;
    private QuizQuestions quiz;
    private CurrentQuestion currq;

    private int endInt;


    public MainGUI() {

        RulesPanel.setVisible(false);
        PlayerPanel.setVisible(false);
        CategoryPanel.setVisible(false);
        QuestionPanel.setVisible(false);
        MenuPanel.setVisible(true);


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
                answer1.setText(currq.getCurrentAnswers(0));
                answer2.setText(currq.getCurrentAnswers(1));
                answer3.setText(currq.getCurrentAnswers(2));
                answer4.setText(currq.getCurrentAnswers(3));


            }
        });
        answer1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int answer = 1;
                boolean rightAnswer = currq.checkAnswer(answer);
                if (rightAnswer == true) {
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
                boolean rightAnswer = currq.checkAnswer(answer);
                if (rightAnswer == true) {
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
                boolean rightAnswer = currq.checkAnswer(answer);
                if (rightAnswer == true) {
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
    }

    public void createPlayerList() {
        playerList = new PlayerList();

        startPlayersList.setModel(playerList);
    }

    public void setCurrentPlayer() {
        //TODO: End Game using null pointer
        curplay = new CurrentPlayer(playerList);
        if (curplay.getCurrentPlayer() == null) {
            endReturn();
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


    public void endReturn(){
        gameOverScreen();
    }


    public static void main(String[] args) {
        JFrame frame = new JFrame("MainGUI");
        frame.setContentPane(new MainGUI().MainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}