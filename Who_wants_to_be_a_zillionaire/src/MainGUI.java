import sun.applet.Main;

import javax.swing.*;
import javax.swing.DefaultListModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;

public class MainGUI{

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
    private PlayerList playerList;



    public MainGUI() {

        RulesPanel.setVisible(false);
        PlayerPanel.setVisible(false);
        CategoryPanel.setVisible(false);
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
                String newPlayerName =  playerNameEntered.getText();
                if (newPlayerName.equals("")){
                    JOptionPane.showMessageDialog(MainPanel, "Please enter player name.");
                }else{
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
                if(selectedIndex == -1){
                    JOptionPane.showMessageDialog(MainPanel, "Please select a player to remove.");
                }else{
                    Player playN = playerList.getElementAt(selectedIndex);
                    if(JOptionPane.showConfirmDialog(MainPanel,"Delete player? " + playN,
                            "Delete confirmation",JOptionPane.YES_NO_OPTION)
                            == JOptionPane.YES_OPTION);{
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
                Game game = new Game(playerList);
                playerActiveLabel.setText("Active Player: " + game.getCurrentPlayerName());
                currentMoney.setText("Current Money: £" + game.getCurrentPlayerMoney());
            }
        });
    }

    public void createPlayerList(){
        playerList = new PlayerList();

        startPlayersList.setModel(playerList);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("MainGUI");
        frame.setContentPane(new MainGUI().MainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

    }
}