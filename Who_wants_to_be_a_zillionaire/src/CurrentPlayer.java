/**
 * Created by u1563819 on 05/03/2018.
 */
public class CurrentPlayer {

    private Player currentPlayer;
    private String currentPlayerName;
    private int currentPlayerMoney;
    /*
       TODO:Popup with question
       TODO:Question wrong, end game for player and search for new player
       TODO:Question right, go back to main screen and add money to player's Money Won
       TODO:End Screen with each player's money won and overall winner.
       https://teamtreehouse.com/community/make-a-text-file-of-questions-into-a-quiz-using-java-filereader
       https://stackoverflow.com/questions/4716503/reading-a-plain-text-file-in-java
     */

    public CurrentPlayer(PlayerList playerList){
        this.currentPlayer = playerList.playerTurn();
        this.currentPlayerName = currentPlayer.getPlayerName();
        this.currentPlayerMoney = currentPlayer.getMoneyWon();
    }


    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public String getCurrentPlayerName() {
        return currentPlayerName;
    }

    public int getCurrentPlayerMoney() {
        return currentPlayerMoney;
    }

    public void setCurrentPlayerMoney(int currentPlayerMoney) {
        this.currentPlayerMoney = currentPlayerMoney;
    }

}
