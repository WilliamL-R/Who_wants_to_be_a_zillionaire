import javax.swing.DefaultListModel;

public class PlayerList extends DefaultListModel<Player> {

    public PlayerList(){
        super();
    }

    public void addPlayer(String name){
        super.addElement(new Player(name));
    }

    public Player findPlayer(String playerName){
        Player playN;
        int indexLocation = -1;
        for (int i = 0;i<super.size();i++){
            playN = (Player)super.elementAt(i);
            if(playN.getPlayerName().equals(playerName)){
                indexLocation = i;
                break;
            }
        }

        if (indexLocation == -1){
            return null;
        }else{
            return (Player)super.elementAt(indexLocation);
        }
    }

    public void removePlayer(String playerName){
        Player playN = this.findPlayer(playerName);
        super.removeElement(playN);
    }

    public Player playerTurn(){
        Player playN;
        int indexLocation = -1;
        for (int i = 0;i<super.size();i++){
            playN = (Player)super.elementAt(i);
            if(playN.isTurnOver() == false){
                indexLocation = i;
                break;
            }
        }
        if (indexLocation == -1){
            return null;
        }else{

            return (Player)super.elementAt(indexLocation);
        }
    }
}
