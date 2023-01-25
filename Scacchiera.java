
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
    public int turn;

    

    public Scacchiera(){
    }


    /*
     * start game: generate al piece and start
     */
    public void newGame(){
        this.winner = false;
        this.lastMove = "";
        this.turn = 0;
        chessboard = new Chessboard(scacchiera);
        this.piecesWhite = new ArrayList<Piece>();
        this.piecesBlack = new ArrayList<Piece>();
        
        //king
        King kW = new King(true, 8+"E",chessboard);
        chessboard.SearcSquare(kW.getIposition()).insertElement(kW);
        this.kingWhite = kW;
        piecesWhite.add(kW);
        King kB = new King(false, 1+"E",chessboard);
        chessboard.SearcSquare(kB.getIposition()).insertElement(kB);
        this.kingBlack = kB;
        piecesBlack.add(kB);
        
        
        //pedestrian

        for (int i = 0; i <= 15; i++) {

            //true = white, false = black
            boolean color = i <= 7 ? true:false;
            String position = i <= 7? 7 + chessboard.getOrizontalLocation()[i+1]: 2 + chessboard.getOrizontalLocation()[i-7];
            Pedestrian p = new Pedestrian(color,position,chessboard, color?kW:kB);

            //add to array for more controll piece
            if(i <= 7){
                piecesWhite.add(p);
            }else{
                piecesBlack.add(p);
            }
            chessboard.SearcSquare(position).insertElement(p);
        }

        //tower
        Tower tW = new Tower(true, 8+"A",chessboard,kW);
        chessboard.SearcSquare(tW.getIposition()).insertElement(tW);
        piecesWhite.add(tW);
        kW.setTover1(tW);
        Tower tW2 = new Tower(true, 8+"H",chessboard,kW);
        chessboard.SearcSquare(tW2.getIposition()).insertElement(tW2);
        piecesWhite.add(tW2);
        kW.setTover2(tW2);
        Tower tB = new Tower(false, 1+"A",chessboard,kB);
        chessboard.SearcSquare(tB.getIposition()).insertElement(tB);
        piecesBlack.add(tB);
        kB.setTover1(tB);
        Tower tB2 = new Tower(false, 1+"H",chessboard,kB);
        chessboard.SearcSquare(tB2.getIposition()).insertElement(tB2);
        piecesBlack.add(tB2);
        kB.setTover2(tB2);

        //horse
        Horse hW = new Horse(true, 8+"B",chessboard,kW);
        chessboard.SearcSquare(hW.getIposition()).insertElement(hW);
        piecesWhite.add(hW);
        Horse hW2 = new Horse(true, 8+"G",chessboard,kW);
        chessboard.SearcSquare(hW2.getIposition()).insertElement(hW2);
        piecesWhite.add(hW2);
        Horse hB = new Horse(false, 1+"B",chessboard,kB);
        chessboard.SearcSquare(hB.getIposition()).insertElement(hB);
        piecesBlack.add(hB);
        Horse hB2 = new Horse(false, 1+"G",chessboard,kB);
        chessboard.SearcSquare(hB2.getIposition()).insertElement(hB2);
        piecesBlack.add(hB2);

        //Bishop
        Bishop BW = new Bishop(true, 8+"C",chessboard,kW);
        chessboard.SearcSquare(BW.getIposition()).insertElement(BW);
        piecesWhite.add(BW);
        Bishop BW2 = new Bishop(true, 8+"F",chessboard,kW);
        chessboard.SearcSquare(BW2.getIposition()).insertElement(BW2);
        piecesWhite.add(BW2);
        Bishop BB = new Bishop(false, 1+"C",chessboard,kB);
        chessboard.SearcSquare(BB.getIposition()).insertElement(BB);
        piecesBlack.add(BB);
        Bishop BB2 = new Bishop(false, 1+"F",chessboard,kB);
        chessboard.SearcSquare(BB2.getIposition()).insertElement(BB2);
        piecesBlack.add(BB2);

        //queen
        Queen qW = new Queen(true, 8+"D",chessboard,kW);
        chessboard.SearcSquare(qW.getIposition()).insertElement(qW);
        piecesWhite.add(qW);
        Queen qB = new Queen(false, 1+"D",chessboard,kB);
        chessboard.SearcSquare(qB.getIposition()).insertElement(qB);
        piecesBlack.add(qB);



        piecesWhite.forEach(el -> {
            List<String> pM = el.movementPossibility();
            pM.forEach(mov -> chessboard.SearcSquare(mov).addPointer(true));
        });
        piecesBlack.forEach(el -> {
            List<String> pM = el.movementPossibility();
            pM.forEach(mov -> chessboard.SearcSquare(mov).addPointer(false));
        });

        colorTurn = true;

        chessboard.printTable();
        Scanner scan = new Scanner(System.in);
        game(scan);
    }
    

    /*
     *list of piece can move 
     */
    public List<String> getListPicesAvaible(Boolean playerColor){
        List<String> text = new ArrayList<String>();

        if (playerColor) {
            for (int i = 0; i < piecesWhite.size(); i++) {
                if(piecesWhite.get(i).movementPossibility()!=null&&piecesWhite.get(i).movementPossibility().size() > 0){
                    text.add(piecesWhite.get(i).getIposition());
                }  
            }
        } else {
            for (int i = 0; i < piecesBlack.size(); i++) {
                if(piecesBlack.get(i).movementPossibility()!=null&&piecesBlack.get(i).movementPossibility().size() > 0){
                    text.add(piecesBlack.get(i).getIposition());
                }
            }
        }
        return text; 
  
    }

    /*
     * can save king
     */
    public List<String> getchekSistem(Boolean playerColor){
        List<String> text = new ArrayList<String>();

        if (playerColor) {
            for (int i = 0; i < piecesWhite.size(); i++) {
                if(piecesWhite.get(i).saveKing()!=null&&piecesWhite.get(i).saveKing().size() > 0){
                    piecesWhite.get(i).saveKing().forEach(el->System.out.println("puo essere salvato da " +el));
                    text.add(piecesWhite.get(i).getIposition());
                }  
            }
        } else {
            for (int i = 0; i < piecesBlack.size(); i++) {
                if(piecesBlack.get(i).saveKing()!=null&&piecesWhite.get(i).saveKing().size() > 0){
                    text.add(piecesBlack.get(i).getIposition());
                }
            }
        }
        return text; 
  
    }


    /*
     * control if chessMate
     */
    public void finish(){
        if (this.kingBlack.getCheck()) {
            if (getchekSistem(false).size() == 0 ) {
                this.winner = true;
                System.out.println("scacco matto");
            }
        }

        if (this.kingWhite.getCheck()) {
            if (getchekSistem(true).size() == 0 ) {
                this.winner = true;
                System.out.println("scacco matto");
            }
        }
    
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
            this.player1 = new Player(true, this.scacchiera ,scan,chessboard,kingWhite);
            if (play2) {
                this.player2 = new Player(false, this.scacchiera ,scan,chessboard, kingBlack);
            } else{
                this.player2 = new Bot(false, scacchiera,chessboard, kingBlack );
            } 
        } else {
            this.player1 = new Bot(true, scacchiera,chessboard,kingWhite );
            this.player2 = new Bot(false, scacchiera,chessboard, kingBlack );
        }
    }


    /*
     * the game controll
     */
    public void game(Scanner scan){
       System.out.println("turn -------" + turn);
       if (player1 == null) {
            generateGamer(scan);
        }
        
        if (colorTurn) {
            if(!kingWhite.getCheck()){
                player1.move();
                this.turn++;
            }else{
                finish();
                player1.moveInCheck(); 
                kingWhite.setCheck(false);
            }
            
        }else{
            if(!kingBlack.getCheck()){
                player2.move(); 
            }else{
                finish();
                player2.moveInCheck(); 
                kingWhite.setCheck(false);
            }
        }

        if (this.winner) {
            scan.close();
        }else{
            colorTurn = !colorTurn;
            game(scan);
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

    public String getLastMove(){
        return this.lastMove;
    }

    public int getTurn(){
        return turn;
    }
}
