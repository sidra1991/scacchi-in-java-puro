import java.util.ArrayList;
import java.util.List;

public class Pedestrian extends Piece{


    public Pedestrian(boolean color,String position,Chessboard ches,King king){
        super("pedestrian",color? "PW":"PB",position,color,ches,king);
    }


    @Override
    public List<String> movementPossibility() {
        List<String> possibility=new ArrayList<String>();

        String[] posistionArray = position.split(""); 
        int vertical = Integer.parseInt(posistionArray[0]);
        String orizzontal = posistionArray[1];
        int orizzontalInt = 0;
        String[] letter = chessboard.orizontalLocation;
        List<String> DirectionControl = new ArrayList<String>();
        

        for (int i = 0; i < orizontalLocation.length; i++) {
            if (orizontalLocation[i].equals(orizzontal)) {
                orizzontalInt = i;
                break;
            }
        }

        //normal
        if (color && vertical -1 > 0 && chessboard.SearcSquare((vertical-1)+letter[orizzontalInt]).getPiece()==null) {
            possibility.add((vertical - 1) + orizzontal);
        }else if(!color&&vertical+1 < 9 && chessboard.SearcSquare((vertical+1)+letter[orizzontalInt]).getPiece()==null){
            possibility.add((vertical + 1) + orizzontal);
        }  
        
        //doble to start
        if(position.equals(start)){
            //color true = white / false = black
            if (color && chessboard.SearcSquare((vertical-2)+letter[orizzontalInt]).getPiece()==null) {
                possibility.add((vertical - 2) + orizzontal);
            }else if(!color&&chessboard.SearcSquare((vertical+2)+letter[orizzontalInt]).getPiece()==null){
                possibility.add((vertical + 2) + orizzontal);
            }
        }


        //eat mode
        if (this.color) {

            if (vertical-1>0&&(orizzontalInt+1)<9 &&chessboard.SearcSquare((vertical-1)+letter[orizzontalInt+1]).pieceEsist() && chessboard.SearcSquare((vertical-1)+letter[orizzontalInt+1]).getPiece().getColor() != this.color) {
                if (chessboard.SearcSquare((vertical-1)+letter[orizzontalInt+1]).getPiece().isKing()) {
                    DirectionControl.add(position);
                    chessboard.SearcSquare((vertical-1)+letter[orizzontalInt+1]).getPiece().saveMov(DirectionControl);
                }
                possibility.add((vertical - 1) + orizontalLocation[orizzontalInt +1]);
            }
            
            if(vertical-1 >0&&(orizzontalInt-1)<0 &&chessboard.SearcSquare((vertical-1)+letter[orizzontalInt-1]).pieceEsist() && chessboard.SearcSquare((vertical-1)+letter[orizzontalInt-1]).getPiece().getColor() != this.color){
                if (chessboard.SearcSquare((vertical-1)+letter[orizzontalInt-1]).getPiece().isKing()) {
                    DirectionControl.add(position);
                    chessboard.SearcSquare((vertical-1)+letter[orizzontalInt-1]).getPiece().saveMov(DirectionControl);
                }
                possibility.add((vertical - 1) + orizontalLocation[orizzontalInt -1]);
            }
        }else{
            if(vertical+1 < 9&&orizzontalInt+1 < 9 && chessboard.SearcSquare((vertical+1)+letter[orizzontalInt+1]).pieceEsist() && chessboard.SearcSquare((vertical+1)+letter[orizzontalInt+1]).getPiece().getColor() != this.color){
                if (chessboard.SearcSquare((vertical+1)+letter[orizzontalInt+1]).getPiece().isKing()) {
                    DirectionControl.add(position);
                    chessboard.SearcSquare((vertical+1)+letter[orizzontalInt+1]).getPiece().saveMov(DirectionControl);
                }
                possibility.add((vertical + 1) + orizontalLocation[orizzontalInt +1]);
            }
            
            if(vertical+1 < 9&&(orizzontalInt-1)<0 && chessboard.SearcSquare((vertical+1)+letter[orizzontalInt-1]).pieceEsist() && chessboard.SearcSquare((vertical+1)+letter[orizzontalInt-1]).getPiece().getColor() != this.color){
                if (chessboard.SearcSquare((vertical+1)+letter[orizzontalInt-1]).getPiece().isKing()) {
                    DirectionControl.add(position);
                    chessboard.SearcSquare((vertical+1)+letter[orizzontalInt-1]).getPiece().saveMov(DirectionControl);
                }
                possibility.add((vertical + 1) + orizontalLocation[orizzontalInt -1]);
            }
        }


        //ghost possibility
        if (vertical == 4 || vertical == 5) {
            String var1 = "";
            if (orizzontalInt + 1 < 9) {
                var1 = vertical + letter[orizzontalInt + 1];
            }
            
            String var2 = "";
            if (orizzontalInt - 1 > 0) {
                var2 = vertical + letter[orizzontalInt - 1];
            }
            
            if( orizzontalInt + 1 < 9 && 
                chessboard.getlastMove().equals(var1)&&
                chessboard.SearcSquare(var1).getPiece().getTipe().equals("pedestrian")&&
                chessboard.SearcSquare(var1).getPiece().getMovement() == 1
            ){;
                possibility.add("GHOST-RIGHT");
            }

            if( orizzontalInt + 1 > 9 &&
                chessboard.getlastMove().equals(var2)&&
                chessboard.SearcSquare(var2).getPiece().getTipe().equals("pedestrian")&&
                chessboard.SearcSquare(var2).getPiece().getMovement() == 1
                ){
                possibility.add("GHOST-LEFT");
            }
        }

        return possibility;
    }

    @Override
    public List<String> saveKing() {
        List<String> possibility =new ArrayList<>();
        List<String> allPoss =  movementPossibility();
        
        allPoss.forEach(el-> {
            if( this.king.getKingPointer()!=null){
                this.king.getKingPointer().contains(el);
                possibility.add(el);  
            }
            }
        );

        return possibility;
    }

}
