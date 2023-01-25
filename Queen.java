import java.util.ArrayList;
import java.util.List;

public class Queen extends Piece{
    public Queen(boolean color,String position,Chessboard ches,King king){
        super("queen",color? "QW":"QB",position,color,ches,king);

    }

    @Override
    public List<String> movementPossibility() {
        List<String> possibility=new ArrayList<String>();
        List<String> DirectionControl = new ArrayList<String>();
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

            if (vertical + var1 <= 8 && vertical + var1 >= 1 && orizzontalInt + var2 <= 8 && orizzontalInt + var2 >= 1) {
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

        
        boolean[] diagonals = {
            //up - right
            true,
            // below - right
            true,
            // below -left
            true,
            // up - left
            true
        };

        //for start all session to loop
        int[][] direction ={
            {
                //up -rigt
                1,1
            },
            {
                //below - right
                -1,1
            },
            {
                // below -left
                -1,-1
            },
            {
                // up - left
                1,-1
            }
        };

        for (int i = 0; i < diagonals.length;) {
            //vertical
            int var1 = direction[i][0] + vertical;
            //orizontal
            int var2 = direction[i][1] +  orizzontalInt ;

            //check if it does not leave the board
            if (var1 <= 8 &&  var1 >= 1 && var2 >= 1 &&  var2 <= 8) {
                
                

                //get the orizonltal position
                String letter = chessboard.getOrizontalLocationLetter(var2);
                //get the square
                Square squ = chessboard.SearcSquare(var1+letter);
                
                
                if (!squ.pieceEsist()) {
                    /*
                     * if the box is empty the move is legal and is added to the list, it is also added 
                     * to the direction checklist which will be returned in case it meets the king.
                     */
                    DirectionControl.add(squ.getIdentify()); 
                    possibility.add((var1) + orizontalLocation[var2]);
                }else if(squ.getPiece().color != this.color){
                    /*
                     * if it encounters any piece, it interrupts its cycle and checks if the piece 
                     * is an enemy and if so, adds it to the list of legal moves.
                     * if it encounters the enemy king, it calls its check method and passes it the direction control array
                     */
                    if (squ.getPiece().isKing()) {
                        DirectionControl.add(position);
                        squ.getPiece().saveMov(DirectionControl);
                    }
                    possibility.add((var1) + orizontalLocation[var2]);
                    diagonals[i]=false;
                }else{
                    /*
                     * if the previous cases are not respected, it stops its cycle
                     */
                    diagonals[i]=false;
                }
            }else{
                diagonals[i]=false;
            }
            
            if (!diagonals[i]) {
                i++;
                DirectionControl.clear();


            }else{
                if(var1 > 0){
                    direction[i][0]+=+1; 
                }else{
                    direction[i][0]+=-1;
                }

                if(var1 > 0){
                    direction[i][1]+=+1; 
                }else{
                    direction[i][1]+=-1;
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