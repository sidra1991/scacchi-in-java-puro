
import java.util.ArrayList;
import java.util.List;

public abstract class Piece {
    private String tipe;
    private String identify;
    protected String position;
    protected boolean color;
    protected String start;
    static final String[] orizontalLocation = {" ","A","B","C","D","E","F","G","H"};
    protected int movement;
    private boolean check;
    protected Chessboard chessboard;
    protected List<String> kingPointer;
    protected King king;

    public Piece(String identify,String position){
        this.identify = identify;
        this.position = position;
        
    }

    public Piece(String tipe,String identify,String position,boolean color, Chessboard ches,King king){
        this.tipe = tipe;
        this.identify = identify;
        this.position = position;
        this.color = color;
        this.start = position;
        this.check = false;
        this.movement = 0;
        this.chessboard = ches;
        this.king = king;
    }

    //metods abstract
    /*return a list of possibility for save the king */
    public abstract List<String> saveKing();
    public abstract List<String> movementPossibility();


    /*
     * name function move
     * @param (Scacchiera, List<String>, String)
     * 
     * inside function
     * 1. if the movement in string form is among the permitted ones, it proceeds with the movement. else is a error
     * 2. control square if this have a piece, remove piece and add new piece
     */
    public void move(Scacchiera scacchiera,List<String> list,String mov){
        if (mov.equals("GHOST-RIGHT")|| mov.equals("GHOST-LEFT")) {
            ghost(mov);
        }

        if( list.contains(mov) ){
            Square movControl = chessboard.SearcSquare(mov);
            if (movControl.pieceEsist()) {
                movControl.getPiece().removePosition();
                scacchiera.deletePieceInList(movControl.getPiece());
                this.movement++;
            }

            System.out.println(this.position + " in " + mov );

            chessboard.SearcSquare(mov).insertElement( chessboard.SearcSquare(position).getPiece());
            chessboard.SearcSquare(position).deletElement();
            this.position = mov;
            movementPossibility();
            scacchiera.lastMove = mov;
            this.movement++;
        }else{
            System.out.println("fail... ");
            System.out.println("valore inserito " + mov);
            System.out.println("valori possibili " );
            list.forEach(el ->System.out.println(el));
            System.out.println("il bot è rincoglionito ");
        }

        
    }

    /* deprecato
    public List<String> checkPosibily(List<String> heros ){
       
        List<String> movKingSave = List.copyOf(movementPossibility());
         if(heros != null){
           movKingSave.addAll(heros); 
        }

        return movKingSave;
    }
    */

    /*
     * control of the check king
     */
    public boolean isKing(){
        boolean result = this.tipe.equals("king")? true:false;

            if (result) {
                System.out.println("this king is in check " + this.position + " save him" ); 
                check();
            }

        return result;
    }


    //only pedestrian use this method
    public void ghost(String direction){
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

        if (color) {
          if (direction.equals("GHOST-RIGHT")) {
                chessboard.SearcSquare(chessboard.getlastMove()).deletElement();
                chessboard.SearcSquare(vertical+letter[orizzontalInt+1]).insertElement(chessboard.SearcSquare(position).getPiece());
                chessboard.SearcSquare(position).deletElement();
                setPosition((vertical+1)+letter[orizzontalInt+1]); 

            } else if (direction.equals("GHOST-LEFT")){
                chessboard.SearcSquare(chessboard.getlastMove()).deletElement();
                chessboard.SearcSquare(vertical+letter[orizzontalInt-1]).insertElement(chessboard.SearcSquare(position).getPiece());
                chessboard.SearcSquare(position).deletElement();
                setPosition((vertical+1)+letter[orizzontalInt-1]); 
            } 
        }else{
            if (direction.equals("GHOST-RIGHT")) {
                chessboard.SearcSquare(chessboard.getlastMove()).deletElement();
                chessboard.SearcSquare(vertical+letter[orizzontalInt+1]).insertElement(chessboard.SearcSquare(position).getPiece());
                chessboard.SearcSquare(position).deletElement();
                setPosition((vertical-1)+letter[orizzontalInt+1]); 

            } else if (direction.equals("GHOST-LEFT")){
                chessboard.SearcSquare(chessboard.getlastMove()).deletElement();
                chessboard.SearcSquare(vertical+letter[orizzontalInt-1]).insertElement(chessboard.SearcSquare(position).getPiece());
                chessboard.SearcSquare(position).deletElement();
                setPosition((vertical-1)+letter[orizzontalInt-1]); 
            } 
        }


        
    }
    

    // getter and setter

    public void saveMov(List<String> p){
        this.kingPointer = new ArrayList<>();
        kingPointer.addAll(p);
    }

    public List<String> getKingPointer(){
        return this.kingPointer;
    }

    public String getIdentify(){
        return identify;
    }

    public String getIposition(){
        return position;
    }

    public boolean getColor(){
        return color;
    }

    public boolean getCheck(){
        return this.check;
    }

    private void check(){
        this.check = !this.check;
    }

    public void removePosition(){
        this.position = "";
    }

    public String getTipe() {
        return this.tipe;
    }

    public int getMovement() {
        return movement;
    }

    public void setCheck(boolean check) {
        this.check = check;
    }

    public void setPosition(String position){
        this.position = position;
    }
}
