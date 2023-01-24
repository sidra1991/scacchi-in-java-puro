import java.util.ArrayList;
import java.util.List;

public class Pedestrian extends Piece{


    public Pedestrian(boolean color,String position,Chessboard ches){
        super("pedestrian",color? "PW":"PB",position,color,ches);
    }


    @Override
    List<String> movementPossibility() {
        List<String> possibility=new ArrayList<String>();

        String[] posistionArray = position.split(""); 
        int vertical = Integer.parseInt(posistionArray[0]);
        String orizzontal = posistionArray[1];
        int orizzontalInt = 0;
        String[] letter = chessboard.orizontalLocation;
        

        for (int i = 0; i < orizontalLocation.length; i++) {
            if (orizontalLocation[i].equals(orizzontal)) {
                orizzontalInt = i;
                break;
            }
        }

        if (vertical -1 > 0 && color && chessboard.SearcSquare((vertical-1)+letter[orizzontalInt]).getPiece()==null) {
            possibility.add((vertical - 1) + orizzontal);
        }else if(vertical+1 < 9 && chessboard.SearcSquare((vertical+1)+letter[orizzontalInt]).getPiece()==null){
            possibility.add((vertical + 1) + orizzontal);
        }  
        
        if(position.equals(start)){
            //color true = white / false = black
            if (color && chessboard.SearcSquare((vertical-2)+letter[orizzontalInt]).getPiece()==null) {
                possibility.add((vertical - 2) + orizzontal);
            }else if(color == false && chessboard.SearcSquare((vertical+2)+letter[orizzontalInt]).getPiece()==null){
                possibility.add((vertical + 2) + orizzontal);
            }
        }


        //eat mode
        if (this.color) {

            if (vertical-1>0&&(orizzontalInt+1)<9 &&chessboard.SearcSquare((vertical-1)+letter[orizzontalInt+1]).pieceEsist() && chessboard.SearcSquare((vertical-1)+letter[orizzontalInt+1]).getPiece().getColor() != this.color) {
                if (chessboard.SearcSquare((vertical-1)+letter[orizzontalInt+1]).getPiece().isKing()) {
                //    table[vertical - 1][orizzontalInt+1].getPiece().check();
                }
                possibility.add((vertical - 1) + orizontalLocation[orizzontalInt +1]);
            }
            
            if(vertical-1 >0&&(orizzontalInt-1)<0 &&chessboard.SearcSquare((vertical-1)+letter[orizzontalInt-1]).pieceEsist() && chessboard.SearcSquare((vertical-1)+letter[orizzontalInt-1]).getPiece().getColor() != this.color){
                if (chessboard.SearcSquare((vertical-1)+letter[orizzontalInt-1]).getPiece().isKing()) {
                //    table[vertical - 1][orizzontalInt-1].getPiece().check();
                }
                possibility.add((vertical - 1) + orizontalLocation[orizzontalInt -1]);
            }
        }else{
            if(vertical+1 < 9&&orizzontalInt+1 < 9 && chessboard.SearcSquare((vertical+1)+letter[orizzontalInt+1]).pieceEsist() && chessboard.SearcSquare((vertical+1)+letter[orizzontalInt+1]).getPiece().getColor() != this.color){
                if (chessboard.SearcSquare((vertical+1)+letter[orizzontalInt+1]).getPiece().isKing()) {
                //    table[vertical + 1][orizzontalInt+1].getPiece().check();
                }
                possibility.add((vertical + 1) + orizontalLocation[orizzontalInt +1]);
            }
            
            if(vertical+1 < 9&&(orizzontalInt-1)<0 && chessboard.SearcSquare((vertical+1)+letter[orizzontalInt-1]).pieceEsist() && chessboard.SearcSquare((vertical+1)+letter[orizzontalInt-1]).getPiece().getColor() != this.color){
                if (chessboard.SearcSquare((vertical+1)+letter[orizzontalInt-1]).getPiece().isKing()) {
                //    table[vertical + 1][orizzontalInt-1].getPiece().check();
                }
                possibility.add((vertical + 1) + orizontalLocation[orizzontalInt -1]);
            }
        }

        if (vertical == 4 || vertical == 5) {
            String var1 = vertical + letter[orizzontalInt + 1];
            if( chessboard.SearcSquare(chessboard.getlastMove()).equals(chessboard.SearcSquare(var1))&&chessboard.SearcSquare(chessboard.getlastMove()).getPiece().getTipe().equals("pedestrian")&& chessboard.SearcSquare(chessboard.getlastMove()).getPiece().getMovement() == 1){
                possibility.add("GHOST-RIGHT");
            }
            String var2 = vertical + letter[orizzontalInt - 1];
            if( chessboard.SearcSquare(chessboard.getlastMove()).equals(chessboard.SearcSquare(var2))&&chessboard.SearcSquare(chessboard.getlastMove()).getPiece().getTipe().equals("pedestrian")&& chessboard.SearcSquare(chessboard.getlastMove()).getPiece().getMovement() == 1){
                possibility.add("GHOST-LEFT");
            }
        }

        return possibility;
    }

}
