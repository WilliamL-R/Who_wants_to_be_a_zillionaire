import java.io.Serializable;

public class Player implements Serializable {
    private String playerName;
    private int moneyWon;
    private boolean turnOver;

    public Player(){
    }

    public Player(String pn){
        this.playerName = pn;
        this.moneyWon = 0;
        this.turnOver = false;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public int getMoneyWon() {
        return moneyWon;
    }

    public void setMoneyWon(int moneyWon) {
        this.moneyWon = moneyWon;
    }

    public boolean isTurnOver() {
        return turnOver;
    }

    public void setTurnOver(boolean turnOver) {
        this.turnOver = turnOver;
    }

    @Override
    public String toString() {
        return playerName;
    }

}
