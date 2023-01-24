
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Scacchiera {
   
    private List<Piece> piecesWhite;
    private List<Piece> piecesBlack;
    private King kingWhite;
    private King kingBlack;
    private boolean winner;
    private Player player1;
    private Player player2;
    private boolean colorTurn; 
    private Scacchiera scacchiera;
    public String lastMove;
    private Chessboard chessboard;

    

    public Scacchiera(){
    }

    /*
    * function name -> controlPointer
    * @param ()
    * 
    * inside the function 
    * 1. call listPiece white and black and use for add pointer to Square
    */
    public void controlPointer(){
        piecesWhite.forEach(el -> {
           List<String> pM = el.movementPossibility(chessboard.getSquares());
           pM.forEach(mov -> chessboard.SearcSquare(mov).addPointer(true));
        });
        piecesBlack.forEach(el -> {
            List<String> pM = el.movementPossibility(chessboard.getSquares());
            pM.forEach(mov -> chessboard.SearcSquare(mov).addPointer(false));
         });
    }



    /*
     * start game: generate al piece and start
     */
    public void newGame(){
        this.winner = false;
        chessboard = new Chessboard(scacchiera);
        this.piecesWhite = new ArrayList<Piece>();
        this.piecesBlack = new ArrayList<Piece>();
        //pedestrian
        for (int i = 0; i <= 15; i++) {

            //true = white, false = black
            boolean color = i <= 7 ? true:false;
            String position = i <= 7? 7 + chessboard.getOrizontalLocation()[i+1]: 2 + chessboard.getOrizontalLocation()[i-7];
            Pedestrian p = new Pedestrian(color,position,chessboard);

            //add to array for more controll piece
            if(i <= 7){
                piecesWhite.add(p);
            }else{
                piecesBlack.add(p);
            }
            chessboard.SearcSquare(position).insertElement(p);
        }

        //tower
        Tower tW = new Tower(true, 8+"A",chessboard);
        chessboard.SearcSquare(tW.getIposition()).insertElement(tW);
        piecesWhite.add(tW);
        Tower tW2 = new Tower(true, 8+"H",chessboard);
        chessboard.SearcSquare(tW2.getIposition()).insertElement(tW2);
        piecesWhite.add(tW2);
        Tower tB = new Tower(false, 1+"A",chessboard);
        chessboard.SearcSquare(tB.getIposition()).insertElement(tB);
        piecesBlack.add(tB);
        Tower tB2 = new Tower(false, 1+"H",chessboard);
        chessboard.SearcSquare(tB2.getIposition()).insertElement(tB2);
        piecesBlack.add(tB2);

        //horse
        Horse hW = new Horse(true, 8+"B",chessboard);
        chessboard.SearcSquare(hW.getIposition()).insertElement(hW);
        piecesWhite.add(hW);
        Horse hW2 = new Horse(true, 8+"G",chessboard);
        chessboard.SearcSquare(hW2.getIposition()).insertElement(hW2);
        piecesWhite.add(hW2);
        Horse hB = new Horse(false, 1+"B",chessboard);
        chessboard.SearcSquare(hB.getIposition()).insertElement(hB);
        piecesBlack.add(hB);
        Horse hB2 = new Horse(false, 1+"G",chessboard);
        chessboard.SearcSquare(hB2.getIposition()).insertElement(hB2);
        piecesBlack.add(hB2);

        //Bishop
        Bishop BW = new Bishop(true, 8+"C",chessboard);
        chessboard.SearcSquare(BW.getIposition()).insertElement(BW);
        piecesWhite.add(BW);
        Bishop BW2 = new Bishop(true, 8+"F",chessboard);
        chessboard.SearcSquare(BW2.getIposition()).insertElement(BW2);
        piecesWhite.add(BW2);
        Bishop BB = new Bishop(false, 1+"C",chessboard);
        chessboard.SearcSquare(BB.getIposition()).insertElement(BB);
        piecesBlack.add(BB);
        Bishop BB2 = new Bishop(false, 1+"F",chessboard);
        chessboard.SearcSquare(BB2.getIposition()).insertElement(BB2);
        piecesBlack.add(BB2);

        //queen
        Queen qW = new Queen(true, 8+"D",chessboard);
        chessboard.SearcSquare(qW.getIposition()).insertElement(qW);
        piecesWhite.add(qW);
        Queen qB = new Queen(false, 1+"D",chessboard);
        chessboard.SearcSquare(qB.getIposition()).insertElement(qB);
        piecesBlack.add(qB);

        //king
        King kW = new King(true, 8+"E",tW,tW2,chessboard);
        chessboard.SearcSquare(kW.getIposition()).insertElement(kW);
        this.kingWhite = kW;
        piecesWhite.add(kW);
        King kB = new King(false, 1+"E",tB,tB2,chessboard);
        chessboard.SearcSquare(kB.getIposition()).insertElement(kB);
        this.kingBlack = kB;
        piecesBlack.add(kB);

        colorTurn = true;
        controlPointer();

        chessboard.printTable();

        game();
    }
    

    //list of piece can move  :TODO----implement controll
    public List<String> getListPicesAvaible(Boolean playerColor){
        List<String> text = new ArrayList<String>();

        if (playerColor) {
            for (int i = 0; i < piecesWhite.size(); i++) {
                if(piecesWhite.get(i).movementPossibility(chessboard.getSquares())!=null&&piecesWhite.get(i).movementPossibility(chessboard.getSquares()).size() > 0){
                    text.add(piecesWhite.get(i).getIposition());
                }  
            }
        } else {
            for (int i = 0; i < piecesBlack.size(); i++) {
                if(piecesBlack.get(i).movementPossibility(chessboard.getSquares())!=null&&piecesBlack.get(i).movementPossibility(chessboard.getSquares()).size() > 0){
                    text.add(piecesBlack.get(i).getIposition());
                }
            }
        }
        return text; 
  
    }

    public boolean finish(){
        if (this.kingBlack.getCheck()) {
            //if (kingBlack.checkPosibily(getSquares()).size() == 0 ) {
              //  this.winner = true;
            //}
        }

        if (this.kingWhite.getCheck()) {
            //if (kingWhite.checkPosibily(getSquares()).size() == 0 ) {
              //  this.winner = true;
            //}
        }
        
        
        if(this.winner){
            System.out.println("scacco matto");
        }
        
        return this.winner;
    }

    /*
     * allows you to select the type of players
     */
    public void generateGamer(Scanner scan){
        System.out.println("menù \n1. P VS P \n2. P VS B \n3. B VS B ");
        int key = 0;
        boolean play1;
        boolean play2;
        while(true){
           
           if (scan.hasNextInt()) {
                key= scan.nextInt();
                if (key >= 1 && key <= 3) {
                    scan.nextLine();
                    break;                
                }else{
                    System.out.print("incorrect value try again");
                }

            }else {
                System.out.print("incorrect value try again");
            }
            
        }
        
        
        switch (key) {
            case 1:
            play1 = true;
            play2 = true;
                break;
            case 2:
            play1 = true;
            play2 = false;
                break;
            case 3:
            play1 = false;
            play2 = false;
                break;
        
            default:
            play1 = false;
            play2 = false;
                break;
        }
           

           
        if (play1) {
            this.player1 = new Player(true, this.scacchiera ,scan,chessboard);
            if (play2) {
                this.player2 = new Player(false, this.scacchiera ,scan,chessboard);
            } else{
                this.player2 = new Bot(false, scacchiera,chessboard );
            } 
        } else {
            this.player1 = new Bot(true, scacchiera,chessboard );
            this.player2 = new Bot(false, scacchiera,chessboard );
        }
    }


    /*
     * the game controll
     */
    public void game(){
       Scanner scan = new Scanner(System.in);
       
       if (player1 == null) {
            generateGamer(scan);
        }
        
        if (colorTurn) {
            if(kingWhite.getCheck()){
               // player1.moveCheck(kingWhite.checkPosibily(table),kingWhite.position); 
                kingWhite.setCheck(false);
            }else{
               player1.move(); 
            }
            
        }else{
            if(kingBlack.getCheck()){
                //,chessboardplayer1.moveCheck(kingBlack.checkPosibily(table),kingBlack.position); 
                kingWhite.setCheck(false);
            }else{
               player2.move(); 
            }
        }

        if (finish()) {
            scan.close();
            System.out.println();
        }else{
            colorTurn = !colorTurn;
            game();
        }
    }


    //getet and setter

    public void setScacchiera(Scacchiera scacchiera){
        this.scacchiera = scacchiera;
    }

    public void deletePieceInList(Piece p){
        System.out.println(p.getIdentify() + " it was eaten");
        if (p.getColor()) {
            this.piecesWhite.remove(p);
        } else {
            this.piecesBlack.remove(p);
        }
    }
}
