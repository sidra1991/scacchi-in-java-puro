import java.util.ArrayList;
import java.util.List;

public class Horse extends Piece{
    public Horse(boolean color,String position,Chessboard ches,King king){
        super("horse",color? "HW":"HB",position,color,ches,king);

    }


    @Override
    public List<String> movementPossibility() {
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
        
        List<String> DirectionControl = new ArrayList<String>();

        //all direction move vertical and orizontal
        int[] arraySupportVertical = {2,1,-1,-2,-2,-1,+1,+2};
        int[] arraySupportOrizontal = {1,+2,+2,1,-1,-2,-2,-1};
        
        
        for (int i = 0; i < arraySupportOrizontal.length; i++) {
            int var1 =vertical + arraySupportVertical[i];
            int var2 =orInt + arraySupportOrizontal[i];
            

        if ( var1 >= 1&& var1 <= 8&& var2 >= 1&& var2 <= 8 ){
                String letter = chessboard.getOrizontalLocationLetter(var2);
                Square squ = chessboard.SearcSquare(var1+letter);
                if (squ.pieceEsist() && !(squ.getPiece().getColor() == this.color)) {
                    if (squ.getPiece().isKing()) {
                        DirectionControl.add(position);
                        squ.getPiece().saveMov(DirectionControl);
                    }
                    possibility.add(var1 + letter);

                }else if(!(squ.pieceEsist())){
                    possibility.add(var1 + letter);
                }
                
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
