import java.util.ArrayList;
import java.util.List;

public class Bishop extends Piece{
    public Bishop(boolean color,String position,Chessboard ches){
        super("bishop",color? "BW":"BB",position,color,ches);

    }

    @Override
    List<String> movementPossibility() {
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

        /*
         * value for control the moove of tower
         * var[0] is number for the square in table
         * var[1] is param for true or false 1 is true and the if can run, 0 is false and stop relative if
         */
        int up =1;
        int below =1;
        int left = 1;
        int right =1;

        boolean upRight =false;
        boolean belowLeft =false;
        boolean upLeft = false;
        boolean belowRight =false;

        String[] upRightDirection = new String[8];
        String[] belowLeftDirection = new String[8];
        String[] upLeftDirection = new String[8];
        String[] belowRightDirection = new String[8];


        for (int i = 0; i < 9; i++) {

            int var1 = 0;
            int var2 = 0;
            Square squ = chessboard.SearcSquare((var1)+chessboard.getOrizontalLocationLetter(var2));
            if (upRight && belowLeft && upLeft && belowRight) {
                break;
            }

            if (up > 9 || up < 0) {
                upRight = true;
                upLeft = true;
            }

            if (below > 9 || below < 0) {
                belowLeft = true;
                belowRight = true;
            }

            if (left > 9 || left < 0) {
                belowLeft = true ;
                upLeft = true;
            }

            if (right > 9 || right < 0) {
                upRight=true;
                belowRight = true;
            }


            if (!upRight && vertical + up < 9 && orizzontalInt + right < 9) {
                var1 = vertical+up;
                var2 = orizzontalInt+right;
                if (!squ.pieceEsist()) {
                    upLeftDirection[i] = squ.getIdentify(); 
                    possibility.add((var1) + orizontalLocation[var2]);
                }else if(squ.getPiece().color != this.color){
                    if (squ.getPiece().isKing()) {
                       upLeftDirection[i] = squ.getIdentify(); 
                    }
                    possibility.add((var1) + orizontalLocation[var2]);
                    upRight = !upRight;
                }else{
                    upRight = !upRight;
                }
            } 


            if (!belowRight && vertical - below > 0 && orizzontalInt + right < 9) {
                var1 = vertical-below;
                var2 = orizzontalInt+right;
                if (!squ.pieceEsist()) {
                    upLeftDirection[i] = squ.getIdentify(); 
                    possibility.add((var1) + orizontalLocation[var2]);
                }else if(squ.getPiece().color != this.color){
                    if (squ.getPiece().isKing()) {
                       upLeftDirection[i] = squ.getIdentify(); 
                    }
                    possibility.add((var1) + orizontalLocation[var2]);
                    upRight = !upRight;
                }else{
                    upRight = !upRight;
                }
            } 

            if (!belowLeft && vertical - below > 0 && orizzontalInt + left < 9) {
                var1 = vertical-below;
                var2 = orizzontalInt+left;
                if (!squ.pieceEsist()) {
                    upLeftDirection[i] = squ.getIdentify(); 
                    possibility.add((var1) + orizontalLocation[var2]);
                }else if(squ.getPiece().color != this.color){
                    if (squ.getPiece().isKing()) {
                       upLeftDirection[i] = squ.getIdentify(); 
                    }
                    possibility.add((var1) + orizontalLocation[var2]);
                    upRight = !upRight;
                }else{
                    upRight = !upRight;
                }
            } 

            if (!upLeft && vertical + up <9 && orizzontalInt + left<9) {
                var1 = vertical+up;
                var2 = orizzontalInt+left;
                if (!squ.pieceEsist()) {
                    upLeftDirection[i] = squ.getIdentify(); 
                    possibility.add((var1) + orizontalLocation[var2]);
                }else if(squ.getPiece().color != this.color){
                    if (squ.getPiece().isKing()) {
                       upLeftDirection[i] = squ.getIdentify(); 
                    }
                    possibility.add((var1) + orizontalLocation[var2]);
                    upRight = !upRight;
                }else{
                    upRight = !upRight;
                }
            } 


            up++;
            below++;
            left++;
            right++;
        }



        return possibility;
    }
}
