public class Chessboard {
    
    private Scacchiera scacchiera;
    private Square[][] table = new Square[9][9];
    public String[] orizontalLocation = {" ","A","B","C","D","E","F","G","H"};

    public Chessboard(Scacchiera scacchiera){
        this.scacchiera = scacchiera;
        generateTable();

    }

    /*
     * generate all square of the table
     */
    public void generateTable(){
        for (int i = 0; i < 9; i++) {
            int swiccer = i % 2;

            for (int j = 0; j < 9; j++) {
                boolean color;

                if (i == 0 || j == 0) {
                    color = true;
                    String name = i == 0? " "+orizontalLocation[j]: " " + i;
                    FakePiece f = new FakePiece(color, i+orizontalLocation[j],name);
                    Square squ = new Square(color,i+orizontalLocation[j]);
                    squ.insertElement(f);
                    table[i][j] = squ;
                } else {
                    color = j % 2 == 0? true : false;
                    color = swiccer == 1? !color: color;
                    Square squ = new Square(color,i+orizontalLocation[j]);
                    table[i][j] = squ;
                }
            }  
        }
    }


    /*
     * the method getTable get's all square and concatenates 
     * and divides them by line thus creating the game board
     */
    public void printTable(){
        String[][] line = new String[9][3];

        for (int i = 0; i < table.length; i++) {
            line[i][0] = "";
            line[i][1] = "";
            line[i][2] = "";
            for (int j = 0; j < table[i].length; j++) {
                String[] base = table[i][j].getColorSquare();
                line[i][0] += base[0];
                line[i][1] += base[1];
                line[i][2] += base[2];
            }
            
        }
        
        //stamp table
        for (int i = 0; i < line.length; i++) {
            System.out.println(line[i][0]);
            System.out.println(line[i][1]);
            System.out.println(line[i][2]);
        }


    }


    /*
     * search for a specific box
     */
    public Square SearcSquare(String identify){
        for (int i = 0; i < table.length; i++) {
            for (int j = 0; j < table[i].length; j++) {
                if (table[i][j].getIdentify().equals(identify)) {
                    return table[i][j];
                }
            }
        }
        return null;
    }


    //geter and setter
    public Square[][] getSquares(){
        return table;
    }

    public String[] getOrizontalLocation(){
        return orizontalLocation;
    }

    public String getOrizontalLocationLetter(int num){
        return orizontalLocation[num];
    }

    public String getlastMove(){
       return scacchiera.getLastMove();
    }


}
