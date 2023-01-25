import java.util.List;

public class FakePiece extends Piece{

    /*
     * this class is only for board table cant move this piece and cant eat this piece
     */



    public FakePiece(boolean color,String position,String name ){
        super(name,position);

    }

    @Override
    public List<String> movementPossibility() {
        
        return null;
    }

    @Override
    public List<String> saveKing(Piece king) {
        
        return null;
    }
    
}
