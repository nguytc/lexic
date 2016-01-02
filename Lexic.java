/*
Tim Nguyen
2016
Lexic: A Word Game
*/

public class Lexic {
    public int[][] board;
    // player hands
    // remaining tiles
    // player points

    public Lexic() {
        board = new int[15][15];
        initializeBoard();
    }

    public void initializeBoard() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (is3W(i, j)) board[i][j] = 113;
                else if (is2W(i, j)) board[i][j] = 112;
                else if (is3L(i, j)) board[i][j] = 103;
                else if (is2L(i, j)) board[i][j] = 102;
                else board[i][j] = 0;
            }
        }
        board[7][7] = 100;
    }

    public boolean is3W(int x, int y) {
        if (((x == 0 || x == 14) && (y == 3 || y == 11)) ||
            ((x == 3 || x == 11) && (y == 0 || y == 14))) return true;
        else return false;
    }

    public boolean is2W(int x, int y) {
        if (((x == 1 || x == 13) && (y == 5 || y == 9)) ||
            ((x == 5 || x == 9) && (y == 1 || y == 13)) ||
            (x == 7 && (y == 3 || y == 11)) ||
            ((x == 3 || x == 11) && y == 7)) return true;
        else return false;
    }

    public boolean is3L(int x, int y) {
        if (((x == 0 || x == 14) && (y == 6 || y == 8)) ||
            ((x == 3 || x == 11) && (y == 3 || y == 11)) ||
            ((x == 5 || x == 9) && (y == 5 || y == 9)) ||
            ((x == 6 || x == 8) && (y == 0 || y == 14))) return true;
        else return false;
    }

    public boolean is2L(int x, int y) {
        if (((x == 1 || x == 4 || x == 10 || x == 13) && (y == 2 || y == 12)) ||
            ((x == 2 || x == 12) && (y == 1 || y == 4 || y == 10 || y == 13)) ||
            ((x == 4 || x == 10) && (y == 6 || y == 8)) ||
            ((x == 6 || x == 8) && (y == 4 || y == 10))) return true;
        else return false;
    }

    public void displayBoard() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == 0) System.out.print(" -");
                else if (board[i][j] == 113) System.out.print(" $");
                else if (board[i][j] == 112) System.out.print(" #");
                else if (board[i][j] == 103) System.out.print(" @");
                else if (board[i][j] == 102) System.out.print(" !");
                else if (board[i][j] == 100) System.out.print(" *");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Lexic game = new Lexic();
        game.displayBoard();
    }
}
