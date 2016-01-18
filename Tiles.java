import java.util.Random;

public class Tiles {
    private int[] bag;
    private int[] points; // point values of tiles
    private int tilesLeft;
    private Random random = new Random();

    public Tiles() {
        initializeTiles();
        tilesLeft = 100;
    }

    public void initializeTiles() {
        // wild,a,b,c,d,e,f,g,h,i,j,k,l,m,n,o,p,q,r,s,t,u,v,w,x,y,z
        // 27 total tiles -- 26 letters plus wild
        bag = new int[] {2,9,2,2,4,12,2,3,2,9,1,1,4,
                        2,6,8,2,1,6,4,6,4,2,2,1,2,1};

        points = new int[] {0,1,3,3,2,1,4,2,4,1,8,5,1,3,
                            1,1,3,10,1,1,1,1,4,4,8,4,10};
    }

    public int tilesLeft() {
        return tilesLeft;
    }

    public int drawTile() {
        if (tilesLeft <= 0) return -1;

        // TODO: improve drawing algorithm later.
        int draw = random.nextInt(26);
        while (bag[draw] <= 0) {
            draw = random.nextInt(26);
        }
        bag[draw]--;
        tilesLeft--;
        return draw;
    }

    // display the tiles that are remaining in the pool
    public void displayTiles() {
        for (int i = 0; i < 27; i++) {
            System.out.println(tileLetter(i) + ":" + bag[i] + "\t");
        }
    }

    // convert the numerical representation of the letter to the actual letter
    public char tileLetter(int tile) {
        char letter;
        if (tile == 0) letter = '?'; // wild
        else letter = (char)(tile + 'A' - 1);
        return letter;
    }
}
