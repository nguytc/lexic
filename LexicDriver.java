import java.util.Scanner;

public class LexicDriver {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nLEXIC\n\nPlayer 1 Starts\nReady?");
        scanner.nextLine(); // y or n

        Lexic game = new Lexic();
        int currentPlayer = 1;

        while (game.tilesLeft() > 0) {
            game.clearScreen();
            System.out.println(
                "Player " + currentPlayer + " Turn\n" +
                "Input letter and board location to place tile there.\n" +
                "Format: {letter} {row} {col}\n" +
                "Input 'submit' to submit letter placement.");

            game.displayBoard();
            game.displayHand(currentPlayer);
            String input = scanner.nextLine().toLowerCase();

            while (!input.equals("submit") && !input.equals("pass")) {
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
                    boolean isWild = false;
                    if (letter == '?') {
                        System.out.println("Wild tile: Select a letter\n" +
                        "A B C D E F G H I J K L M N O P Q R S T U V W X Y Z");
                        letter = Character.toUpperCase(scanner.nextLine().charAt(0));
                        isWild = true;
                    }
                    game.playLetter(currentPlayer, letter, row, col, isWild);
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
