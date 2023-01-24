import java.util.ArrayList;
import java.util.List;

public class Horse extends Piece{
    public Horse(boolean color,String position,Chessboard ches){
        super("horse",color? "HW":"HB",position,color,ches);

    }


    @Override
    List<String> movementPossibility() {
        List<String> possibility=new ArrayList<String>();

        String[] posistionArray = position.split(""); 
        int vertical = Integer.parseInt(posistionArray[0]);
        String orizzontal = posistionArray[1];
        int orInt = 0;

        for (int i = 0; i < orizontalLocation.length; i++) {
            if (orizontalLocation[i].equals(orizzontal)) {
                orInt = i;
                break;
            }
        }
        
        
        int[] arraySupportVertical = {2,1,-1,-2,-2,-1,+1,+2};
        int[] arraySupportOrizontal = {1,+2,+2,1,-1,-2,-2,-1};
        
        
        for (int i = 0; i < arraySupportOrizontal.length; i++) {
            int var1 =vertical + arraySupportVertical[i];
            int var2 =orInt + arraySupportOrizontal[i];
            String letter = chessboard.getOrizontalLocationLetter(var2);

        if ( var1>0&& var1<9&& var2>0&& var2<9 ){

                Square squ = chessboard.SearcSquare(var1+letter);
                if (squ.pieceEsist() && !(squ.getPiece().getColor() == this.color)) {
                    if (squ.getPiece().isKing()) {
                    //    squ.getPiece().check();
                    }
                    possibility.add(var1 + letter);

                }else if(!(squ.pieceEsist())){
                    possibility.add(var1 + letter);
                }
                
            }

        }

        return possibility;
    }
}
