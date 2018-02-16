import javax.swing.*;

/**
 * Created by u1563819 on 16/02/2018.
 */
public class MainMenuGUI {
    private JPanel MenuWindow;
    private JButton playButton;
    private JButton settingsButton;
    private JButton rulesButton;
    private JButton exitButton;
    private JLabel Logo;


    public static void main(String[] args) {
        JFrame frame = new JFrame("MainMenu");
        frame.setContentPane(new MainMenuGUI().MenuWindow);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setResizable(false);
    }
}
