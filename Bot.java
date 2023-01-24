import java.util.List;
import java.util.Random;

public class Bot extends Player{
    
    Random randPiece ;

    public Bot(boolean col, Scacchiera sca ,Chessboard ches){
        super(col, sca, null,ches);
        randPiece = new Random();
    }


    @Override
    public void move() {

        List<String> movePossibility = scacchiera.getListPicesAvaible(this.color);

        //the bot select a random on list piece can move
        String playerbot = movePossibility.get(randPiece.nextInt(movePossibility.size()));
        Piece piece = chessboard.SearcSquare(playerbot).getPiece();
        

        //the bot select a random on list possibility move
        List<String> pieceMuve = piece.movementPossibility(chessboard.getSquares());
        String playerbotMove = pieceMuve.get(randPiece.nextInt(pieceMuve.size()));
        piece.move(scacchiera,pieceMuve,playerbotMove);
        turn++;
        scacchiera.lastMove = playerbotMove;

         //if (turn % 5 == 0 && !color) {
            chessboard.printTable();
        //}
    }

    @Override
    public void moveCheck(List<String> posibility, String position) {
        chessboard.printTable();

        Piece piece = chessboard.SearcSquare(position).getPiece();
        System.out.println(posibility.toString());

        System.out.println("select now ");
        String playerbotMove = posibility.get(randPiece.nextInt(posibility.size()));
        piece.move(scacchiera,posibility,playerbotMove);
        turn++;
        scacchiera.lastMove = playerbotMove;
    }

    @Override
    public String controlInList() {
        // TODO Auto-generated method stub
        return "";
    }

    @Override
    public String controlmove(List<String> muvement) {
        // TODO Auto-generated method stub
        return "";
    }

}
