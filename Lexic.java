/*
Tim Nguyen
2016
Lexic: A Word Game
*/

public class Lexic {
    private Board board;
    private Tiles tiles;
    private Player player1;
    private Player player2;

    public Lexic() {
        board = new Board();
        tiles = new Tiles();

        player1 = new Player();
        player2 = new Player();
        player1.refillHand(tiles);
        player2.refillHand(tiles);
    }

    // move one of the tiles onto the board
    public void playLetter(int player, char letter, char row, char col, boolean wild) {
        int tile = letter - 'A' + 1;

        // place the letter on the spot
        int x = row - 'a';
        int y = col - 'a';
        board.placeTile(tile, x, y);

        if (wild) tile = 0; // remove wild tile rather than letter of wild

        if (player == 1) player1.removeLetter(tile);
        else player2.removeLetter(tile);

    }

    // make sure that the current move is valid
    public boolean validMove(int player, char letter, char row, char col) {
        boolean valid = true;
        int tile = letter - 'A' + 1;
        boolean present;

        if (player == 1) present = player1.handHasLetter(tile);
        else present = player2.handHasLetter(tile);

        int x = row - 'a';
        int y = col - 'a';

        if (player != 1 && player != 2) {
            System.out.println("Invalid player");
            valid = false;
        } else if ((tile < 0 || tile > 26) && letter != '?') {
            // check that the player has the letter
            System.out.println("Invalid letter input: " + letter);
            valid = false;
        } else if (present == false) {
            System.out.println("You don't have an " + letter);
            valid = false;
        } else if (!board.validSpot(x, y)) {
            valid = false;
        }

        return valid;
    }

    public void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
        System.out.println("LEXIC\n");
    }

    public int tilesLeft() {
        return tiles.tilesLeft();
    }

    public void refillHand(int player) {
        if (player == 1) player1.refillHand(tiles);
        else player2.refillHand(tiles);
    }

    public void displayHand(int player) {
        if (player == 1) player1.displayHand();
        else player2.displayHand();
    }

    public void displayBoard() {
        board.displayBoard();
    }
}
