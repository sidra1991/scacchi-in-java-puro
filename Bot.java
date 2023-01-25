import java.util.List;
import java.util.Random;

public class Bot extends Player{
    
    Random randPiece ;

    public Bot(boolean col, Scacchiera sca ,Chessboard ches,King king){
        super(col, sca, null,ches,king);
        randPiece = new Random();
    }


    @Override
    public void move() {
        int turn = scacchiera.getTurn();
        List<String> movePossibility = scacchiera.getListPicesAvaible(this.color);

        //the bot select a random on list piece can move
        String playerbot = movePossibility.get(randPiece.nextInt(movePossibility.size()));
        Piece piece = chessboard.SearcSquare(playerbot).getPiece();
        

        //the bot select a random on list possibility move
        List<String> pieceMuve = piece.movementPossibility();
        String playerbotMove = pieceMuve.get(randPiece.nextInt(pieceMuve.size()));
        piece.move(scacchiera,pieceMuve,playerbotMove);
        scacchiera.lastMove = playerbotMove;
        /*
         if (turn % 5 == 0 && !color) {
            chessboard.printTable();
        }*/
    }


    @Override
    public void moveInCheck() {
        List<String> movePossibility = scacchiera.getchekSistem(this.color);

        //the bot select a random on list piece can move
        String playerbot = movePossibility.get(randPiece.nextInt(movePossibility.size()));
        Piece piece = chessboard.SearcSquare(playerbot).getPiece();
        

        //the bot select a random on list possibility move
        List<String> pieceMuve = piece.saveKing();
        System.out.println();
        pieceMuve.forEach(el->System.out.println(el + "è di pieceMuve") );

        String playerbotMove = pieceMuve.get(randPiece.nextInt(pieceMuve.size()));
        System.out.println(playerbotMove + " è playerbotMove");
        piece.move(scacchiera,pieceMuve,playerbotMove);
        scacchiera.lastMove = playerbotMove;

 
        // chessboard.printTable();

    }


    /*
     * 
     * function only for humans, bot not used, extends only for silence him.
     * 
     */
    @Override
    public String controlInList() {
        
        return "";
    }

    @Override
    public String controlmove(List<String> muvement) {
        
        return "";
    }

}
