

/**
 * main
 */
public class Main {
    public static void main(String[] args) {

        System.out.println("-------------------------------------------------------\n------------------------ chess-------------------------\n.......................................................");         

        Scacchiera scacchiera = new Scacchiera();
        scacchiera.setScacchiera(scacchiera);

        scacchiera.newGame();

    }
}