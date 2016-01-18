public class Board {
    private int[][] board;

    public Board() {
        board = new int[15][15];
        initializeBoard();
    }

    public void initializeBoard() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                board[i][j] = handleSpot(i, j);
            }
        }
    }

    // handle what the spot is. Could be neutral or bonus tile
    public int handleSpot(int x, int y) {
        if (x == 7 && y == 7) return 101;
        else if
            (((x == 0 || x == 14) && (y == 3 || y == 11)) ||
            ((x == 3 || x == 11) && (y == 0 || y == 14))) return 113;
        else if
            (((x == 1 || x == 13) && (y == 5 || y == 9)) ||
            ((x == 5 || x == 9) && (y == 1 || y == 13)) ||
            (x == 7 && (y == 3 || y == 11)) ||
            ((x == 3 || x == 11) && y == 7)) return 112;
        else if
            (((x == 0 || x == 14) && (y == 6 || y == 8)) ||
            ((x == 3 || x == 11) && (y == 3 || y == 11)) ||
            ((x == 5 || x == 9) && (y == 5 || y == 9)) ||
            ((x == 6 || x == 8) && (y == 0 || y == 14))) return 103;
        else if
            (((x == 1 || x == 4 || x == 10 || x == 13) && (y == 2 || y == 12)) ||
            ((x == 2 || x == 12) && (y == 1 || y == 4 || y == 10 || y == 13)) ||
            ((x == 4 || x == 10) && (y == 6 || y == 8)) ||
            ((x == 6 || x == 8) && (y == 4 || y == 10))) return 102;
        else return 100;
    }

    // display the current game board
    public void displayBoard() {
        System.out.println("\n    a b c d e f g h i j k l m n o\n");
        for (int i = 0; i < board.length; i++) {
            System.out.print((char)(i + 'a') + "  ");
            for (int j = 0; j < board[0].length; j++) {
                System.out.print(" ");
                if (board[i][j] == 100) System.out.print("-");
                else if (board[i][j] == 113) System.out.print("$");
                else if (board[i][j] == 112) System.out.print("#");
                else if (board[i][j] == 103) System.out.print("@");
                else if (board[i][j] == 102) System.out.print("!");
                else if (board[i][j] == 101) System.out.print("*");
                else System.out.print(tileLetter(board[i][j]));
            }
            System.out.println();
        }
        System.out.println();
    }

    public void placeTile(int tile, int x, int y) {
        if (validSpot(x, y)) board[x][y] = tile;
    }

    public boolean validSpot(int x, int y) {
        boolean valid = true;

        if (x < 0 || x > 14 || y < 0 || y > 14) {
            System.out.println("Invalid board spot: Enter a-o for row and col");
            valid = false;
        } else if (board[x][y] < 100) {
            System.out.println("Invalid board spot: Already taken");
            valid = false;
        }

        return valid;
    }

    // convert the numerical representation of the letter to the actual letter
    public char tileLetter(int tile) {
        char letter;
        if (tile == 0) letter = '?'; // wild
        else letter = (char)(tile + 'A' - 1);
        return letter;
    }
}
