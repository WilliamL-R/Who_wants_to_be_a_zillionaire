/**
 * Created by u1563819 on 05/03/2018.
 */
public class CurrentPlayer {

    private Player currentPlayer;
    private String currentPlayerName;
    private int currentPlayerMoney;
    private boolean currentPlayerTurn;
    /*
       TODO:Popup with question
       TODO:Question wrong, end game for player and search for new player
       TODO:Question right, go back to main screen and add money to player's Money Won
       TODO:End Screen with each player's money won and overall winner.
       //TODO: End Game using null pointer
     */

    public CurrentPlayer(PlayerList playerList){
        Player player = playerList.playerTurn();
        if (player == null){
            return;
        }
        this.currentPlayer = player;
        this.currentPlayerName = currentPlayer.getPlayerName();
        this.currentPlayerMoney = currentPlayer.getMoneyWon();
        this.currentPlayerTurn = currentPlayer.isTurnOver();
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

    public void setCurrentPlayerMoney() {

        if ( currentPlayerMoney == 0) {
            this.currentPlayerMoney = 100;
           currentPlayer.setMoneyWon(currentPlayerMoney);
        } else if (currentPlayerMoney == 1638400) {
            currentPlayer.setMoneyWon(currentPlayerMoney);
            return;
        } else if ( currentPlayerMoney >= 100) {
            int increasedMoney = currentPlayerMoney * 2;
            this.currentPlayerMoney = increasedMoney;
            currentPlayer.setMoneyWon(currentPlayerMoney);

        }
    }

    public boolean isCurrentPlayerTurn() {
        return currentPlayerTurn;
    }

    public void setCurrentPlayerTurnOver(boolean curturn) {
        this.currentPlayerTurn = false;
        currentPlayer.setTurnOver(curturn);
}


    @Override
    public String toString() {
        return "CurrentPlayer{" +
                "currentPlayer=" + currentPlayer +
                ", currentPlayerName='" + currentPlayerName + '\'' +
                ", currentPlayerMoney=" + currentPlayerMoney +
                ", currentPlayerTurn=" + currentPlayerTurn +
                '}';
    }
}
