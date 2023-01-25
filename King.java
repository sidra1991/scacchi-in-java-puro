import java.util.ArrayList;
import java.util.List;

public class King extends Piece{
    private Tower tower1;
    private Tower tower2;

    public King(boolean color,String position,Chessboard ches){
        super("king",color? "KW":"KB",position,color,ches,null);

        
    }
    
    @Override
    public List<String> movementPossibility() {


        List<String> possibility=new ArrayList<String>();

        String[] posistionArray = position.split(""); 
        int vertical = Integer.parseInt(posistionArray[0]);
        String orizzontal = posistionArray[1];
        int orizzontalInt = 0;

        for (int i = 0; i < orizontalLocation.length; i++) {
            if (orizontalLocation[i].equals(orizzontal)) {
                orizzontalInt = i;
                break;
            }
        }

        int[] positionDirection = {0,+1,-1};
        

        //regular move
        for (int i = 0; i < positionDirection.length; i++) {
            for (int j = 0; j < positionDirection.length; j++) {

                int var1 = vertical + positionDirection[i];
                int var2 = orizzontalInt + positionDirection[j];
                String letter = chessboard.getOrizontalLocationLetter(var2);

                if (var1 < 9 && 
                    var2 < 9 &&
                    var1 > 9 && 
                    var2 > 9 ) 
                    {
                        Square squ = chessboard.SearcSquare(var1+letter);
                        if (squ.getPointerEnemy(color) == 0 ) {
                            if (!squ.pieceEsist() || squ.getPiece().color != this.color) {
                            possibility.add(var1 + letter);
                        }
                    }
                } 
            }
        }


        if(this.movement == 0){
           String[] castling =  castlingPossibility();

           if (!castling[0].equals("")) {
                possibility.add(castling[0]);
           }

           if (!castling[1].equals("")) {
                possibility.add(castling[1]);
            }
        }

        return possibility; 
    }


    /*
     * function name castlingPossibility()
     * @param (Square[][]) 
     * @return (string[2])
     * 
     * inside the function
     * 1. check on allied towers if they have made moves
     * 2. add possibility or ad void string
     * 
     */

    public String[] castlingPossibility(){
        String[] possibility = {"",""};

        if (!tower1.getIposition().equals("") && tower1.getMovement() == 0 ) {
            
            if (color) {
                if (
                    chessboard.SearcSquare("8B").getPointerEnemy(color) == 0 &&
                    !chessboard.SearcSquare("8B").pieceEsist() &&
                    chessboard.SearcSquare("8C").getPointerEnemy(color) == 0 &&
                    !chessboard.SearcSquare("8C").pieceEsist()&&
                    chessboard.SearcSquare("8D").getPointerEnemy(color) == 0 &&
                    !chessboard.SearcSquare("8D").pieceEsist()) 
                {
                    possibility[0] = "LC"; 
                }

            } else {
                if (
                    chessboard.SearcSquare("1C").getPointerEnemy(color) == 0 &&
                    !chessboard.SearcSquare("1C").pieceEsist() &&
                    chessboard.SearcSquare("1D").getPointerEnemy(color) == 0 &&
                    !chessboard.SearcSquare("1D").pieceEsist()&&
                    chessboard.SearcSquare("1B").getPointerEnemy(color) == 0 &&
                    !chessboard.SearcSquare("1B").pieceEsist())
                {
                    possibility[0] = "LC"; 
                }

            }
            
        }

        if (!tower2.getIposition().equals("") && tower2.getMovement() == 0) {
            if (color) {
                if (
                    chessboard.SearcSquare("8F").getPointerEnemy(color) == 0 &&
                    !chessboard.SearcSquare("8F").pieceEsist()&&
                    chessboard.SearcSquare("8G").getPointerEnemy(color) == 0 &&
                    !chessboard.SearcSquare("8G").pieceEsist()) 
                {
                    possibility[1] = "RC"; 
                }

            } else {
                if (
                    chessboard.SearcSquare("1F").getPointerEnemy(color) == 0 &&
                    !chessboard.SearcSquare("1F").pieceEsist()&&
                    chessboard.SearcSquare("1G").getPointerEnemy(color) == 0 &&
                    !chessboard.SearcSquare("1G").pieceEsist()) 
                {
                    possibility[1] = "RC"; 
                }

            }
        }
        
        return possibility;
    }

    @Override
    public void move(Scacchiera scacchiera,List<String> list,String mov){

        if (mov.equals("LC")) {
            if (color) {
                tower1.move(scacchiera, list, "8D");
                chessboard.SearcSquare("8C").insertElement( chessboard.SearcSquare(position).getPiece());
                chessboard.SearcSquare(position).deletElement();
                this.position = "8C";

            } else {
                tower1.move(scacchiera, list, "1D");
                chessboard.SearcSquare("1C").insertElement( chessboard.SearcSquare(position).getPiece());
                chessboard.SearcSquare(position).deletElement();
                this.position = "1C";
            }
            
        } else if(mov.equals("RC")){

            if (color) {
                tower2.move(scacchiera, list, "8F");
                chessboard.SearcSquare("8G").insertElement( chessboard.SearcSquare(position).getPiece());
                chessboard.SearcSquare(position).deletElement();
                this.position = "8G";
            }else{
                tower2.move(scacchiera, list, "1F");
                chessboard.SearcSquare("1G").insertElement( chessboard.SearcSquare(position).getPiece());
                chessboard.SearcSquare(position).deletElement();
                this.position = "1G";
            }


        }else {
            if( list.contains(mov) ){
                Square movControl = chessboard.SearcSquare(mov);

            if (movControl.pieceEsist()) {
                movControl.getPiece().removePosition();
                scacchiera.deletePieceInList(movControl.getPiece());
            }

            chessboard.SearcSquare(mov).insertElement( chessboard.SearcSquare(position).getPiece());
            chessboard.SearcSquare(position).deletElement();
            this.position = mov;
            movementPossibility();
        }
        
        
        
        }

        
    }

    public List<String> CheckMate(Square[][] table){
        List<String> saveKing = new ArrayList<>();

        movementPossibility().forEach(el -> saveKing.add(el));

        return saveKing;
    }

    @Override
    public List<String> saveKing() {
        List<String> text = movementPossibility();
        return text;
    }

    public void setTover1(Tower tower){
        this.tower1 = tower;
    }

    public void setTover2(Tower tower){
        this.tower2 = tower;
    }

}
