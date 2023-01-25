import java.util.ArrayList;
import java.util.List;

public class Tower extends Piece {

    public Tower(boolean color,String position,Chessboard ches,King king){
        super("tower",color? "TW":"TB",position,color,ches,king);

    }

    @Override
    public List<String> movementPossibility() {
        List<String> possibility=new ArrayList<String>();

        String[] posistionArray = position.split(""); 
        int vertical = Integer.parseInt(posistionArray[0]);
        String orizzontal = posistionArray[1];
        int orizzontalInt = 0;
        List<String> DirectionControl = new ArrayList<String>();

        for (int i = 0; i < orizontalLocation.length; i++) {
            if (orizontalLocation[i].equals(orizzontal)) {
                orizzontalInt = i;
                break;
            }
        }

        //movement support
        int[] moveSistem ={
            //up
            1,
            //right
            1,
            //below
            -1,
            //left
            -1
        };

        for (int i = 0; i < moveSistem.length;) {
            //vertical
            int var1 = i <= 1? moveSistem[i]:0;
            //orizontal
            int var2 = i > 1? moveSistem[i]:0;

            boolean canMove = true;

            if (vertical + var1 < 9 && vertical + var1 > 0 && orizzontalInt + var2 < 9 && orizzontalInt + var2 > 0) {
                String letter =  orizontalLocation[orizzontalInt+var2];
                int verti = vertical + var1;
                Square squ = chessboard.SearcSquare(verti+letter);

                if (!squ.pieceEsist()) {
                    possibility.add(verti+letter);
                    DirectionControl.add(squ.getIdentify());
                }else if(squ.getPiece().color != this.color){
                    if (squ.getPiece().isKing()) {
                        DirectionControl.add(position);
                        squ.getPiece().saveMov(DirectionControl);
                    }
                    
                    possibility.add(verti+letter);
                    canMove = !canMove;
                }else{
                    canMove = !canMove;
                }
            }else{
                canMove = !canMove;
            }


            if (!canMove) {
                i++;
            }else{

                if(i <= 1){
                    moveSistem[i] = var1 > 0? moveSistem[i] + 1:moveSistem[i]-1;
                }else{
                    moveSistem[i] = var2 > 0? moveSistem[i] + 1:moveSistem[i]-1;
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
