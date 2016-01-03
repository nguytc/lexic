/*
Tim Nguyen
2016
Lexic: A Word Game
*/

import java.util.Scanner;
import java.util.Random;

public class Lexic {
    private int[][] board;
    private int[] tiles;
    private int tilesLeft;
    private int[] hand1;
    private int[] hand2;
    private Random random = new Random();

    public Lexic() {
        board = new int[15][15];
        initializeBoard();

        initializeTiles();
        tilesLeft = 100;

        hand1 = new int[7];
        hand2 = new int[7];

        startingHands();
    }

    public void startingHands() {
        for (int i = 0; i < hand1.length; i++) {
            hand1[i] = drawTile();
            hand2[i] = drawTile();
        }
    }

    public void initializeTiles() {
        // wild,a,b,c,d,e,f,g,h,i,j,k,l,m,n,o,p,q,r,s,t,u,v,w,x,y,z
        // 27 total tiles -- 26 letters plus wild
        tiles = new int[]  {2,9,2,2,4,12,2,3,2,9,1,1,4,
                            2,6,8,2,1,6,4,6,4,2,2,1,2,1};
    }

    public int tilesLeft() {
        return tilesLeft;
    }

    public int drawTile() {
        if (tilesLeft <= 0) return -1;

        // TODO: improve drawing algorithm later.
        int draw = random.nextInt(26);
        while (tiles[draw] <= 0) {
            draw = random.nextInt(26);
        }
        tiles[draw]--;
        tilesLeft--;
        return draw;
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

    // display the current hand of the given player
    public void displayHand(int player) {
        System.out.print("Hand: ");
        int[] hand;
        if (player == 1) hand = hand1;
        else hand = hand2;

        for (int i = 0; i < hand1.length; i++) {
            if (hand[i] != -1) System.out.print(tileLetter(hand[i]) + " ");
        }
        System.out.println("\n");
    }

    // convert the numerical representation of the letter to the actual letter
    public char tileLetter(int tile) {
        char letter;
        if (tile == 0) letter = '?'; // wild
        else letter = (char)(tile + 'A' - 1);
        return letter;
    }

    // display the tiles that are remaining in the pool
    public void displayTiles() {
        for (int i = 0; i < 27; i++) {
            System.out.println(tileLetter(i) + ":" + tiles[i] + "\t");
        }
    }

    // move one of the tiles onto the board
    public void playLetter(int player, char letter, char row, char col) {
        int[] hand;
        if (player == 1) hand = hand1;
        else hand = hand2;

        int tile = letter - 'A' + 1;

        // place the letter on the spot
        int x = row - 'a';
        int y = col - 'a';
        board[x][y] = tile;

        // remove letter from hand
        for (int i = 0; i < hand.length; i++) {
            if (hand[i] == tile) {
                hand[i] = -1;
                break;
            }
        }
    }

    // make sure that the current move is valid
    public boolean validMove(int player, char letter, char row, char col) {
        boolean valid = true;
        int tile = letter - 'A' + 1;
        boolean present = false;

        int[] hand;
        if (player == 1) hand = hand1;
        else hand = hand2;

        for (int i = 0; i < hand.length; i++) {
            if (hand[i] == tile) {
                present = true;
                break;
            }
        }

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
        } else if (x < 0 || x > 14 || y < 0 || y > 14) {
            System.out.println("Invalid board spot: Enter a-o for row and col");
            valid = false;
        } else if (board[x][y] < 100) {
            System.out.println("Invalid board spot: Already taken");
            valid = false;
        }

        return valid;
    }

    public void refillHand(int player) {
        int[] hand;
        if (player == 1) hand = hand1;
        else hand = hand2;

        for (int i = 0; i < hand.length; i++) {
            if (hand[i] == -1) {
                hand[i] = drawTile();
            }
        }
    }

    public void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
        System.out.println("LEXIC\n");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nLEXIC\n\nPlayer 1 Starts\nReady?");
        scanner.nextLine(); // y or n

        Lexic game = new Lexic();
        int currentPlayer = 1;

        while (game.tilesLeft() > 0) {
            game.clearScreen();
            System.out.println("Player " + currentPlayer + " Turn");
            System.out.println(
            "Input letter and board location to place tile there.\n" +
            "Format: {letter} {row} {col}\n" +
            "Input 'submit' to submit letter placement.");

            game.displayBoard();
            game.displayHand(currentPlayer);
            String input = scanner.nextLine().toLowerCase();

            while (!input.equals("submit")) {
                String[] parts = input.split(" ");
                if (parts.length != 3) {
                    System.out.println("Play a tile with {letter} {row} {col} or submit");
                    input = scanner.nextLine().toLowerCase();
                    continue;
                }

                char letter = Character.toUpperCase(parts[0].charAt(0));
                char row = parts[1].charAt(0);
                char col = parts[2].charAt(0);
                if (game.validMove(currentPlayer, letter, row, col)) {
                    game.playLetter(currentPlayer, letter, row, col);
                } else {
                    input = scanner.nextLine().toLowerCase();
                    continue;
                }

                game.displayBoard();
                game.displayHand(currentPlayer);

                input = scanner.nextLine().toLowerCase();
            }

            game.refillHand(currentPlayer);

            if (currentPlayer == 1) currentPlayer = 2;
            else currentPlayer = 1;

            game.clearScreen();

            System.out.println("OK\nX points awarded\n\n" +
                        "Now Player " + currentPlayer + "'s Turn\nReady?");
            scanner.nextLine();
        }
    }
}
