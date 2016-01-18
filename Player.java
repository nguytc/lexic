public class Player {
    private int[] hand;
    private int points;

    public Player() {
        hand = new int[7];
        for (int i = 0; i < hand.length; i++) {
            hand[i] = -1;
        }

        points = 0;
    }

    public int[] getHand() {
        return hand;
    }

    public void setHand(int[] newHand) {
        hand = newHand;
    }

    // display the current hand of the given player
    public void displayHand() {
        System.out.print("Hand: ");
        for (int i = 0; i < hand.length; i++) {
            if (hand[i] != -1) System.out.print(tileLetter(hand[i]) + " ");
        }
        System.out.println("\n");
    }

    // refill the hand so that the size of the hand is 7 again
    public void refillHand(Tiles tiles) {
        for (int i = 0; i < hand.length; i++) {
            if (hand[i] == -1) hand[i] = tiles.drawTile();
        }
    }

    public char tileLetter(int tile) {
        char letter;
        if (tile == 0) letter = '?'; // wild
        else letter = (char)(tile + 'A' - 1);
        return letter;
    }

    public void removeLetter(int tile) {
        // remove letter from hand
        for (int i = 0; i < hand.length; i++) {
            if (hand[i] == tile) {
                hand[i] = -1;
                break;
            }
        }
    }

    public boolean handHasLetter(int tile) {
        boolean present = false;
        for (int i = 0; i < hand.length; i++) {
            if (hand[i] == tile) {
                present = true;
                break;
            }
        }

        return present;
    }
}
