 public class Board {
    private Tile[][] tileList;
    private int numOfMoves;

    public static final int[] xCoord = {120, 240, 360, 480};
    public static final int[] yCoord = {120, 240, 360, 480};

    private int numberOfTiles;
    
    /*
    * Description: Constructor for board object (4x4)
    * Inputs: None
    * Outputs: None
    */

    public Board() {
        tileList = new Tile[4][4];
        numOfMoves = 0;
        addTile();
        addTile();
    }

    /* 
    * Description: Adds a tile to random space on board when called
    * Inputs: None
    * Outputs: None
    */

    public void addTile() {

        while(true) {
            int i = (int) (Math.random() * 4);
            int j = (int) (Math.random() * 4);

            
            if (tileList[i][j] == null) {
                tileList[i][j] = new Tile(xCoord[j], yCoord[i]);
                tileList[i][j].draw();
                numberOfTiles++;
                return;
            }
        } 
        
    }

    /*
    * Description: Draws board
    * Inputs: None
    * Outputs: None
    */

    public void draw() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (tileList[i][j] != null) {
                    tileList[i][j].draw();
                }
            }
        }
    }
    
    /*
    * Description: Sets coordinates of each tile in tileList to appropriate
    *              coordinates on the board
    * Inputs: None
    * Outputs: None
    */

    private void updateCoordinates() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (tileList[i][j] != null) {
                    tileList[i][j].setX(xCoord[j]);
                    tileList[i][j].setY(yCoord[i]);
                }
            }
        }
    }

    
    /*
    * Description: Returns tile immediately after current tile moves
    * Inputs: int i, int j, char p
    * Outputs: Next Tile
    */

    private Tile nextTile(int i, int j, char p) {
        if (p == 's') {
            if (i - 1 >= 0) {
                return tileList[i - 1][j];
            }
        }
        if (p == 'w') {
            if (i + 1 < 4) {
                return tileList[i + 1][j];
            }
        }
        if (p == 'a') {
            if (j - 1 >= 0) {
                return tileList[i][j-1];
            }
        }
        if (p == 'd') {
            if (j + 1 < 4) {
                return tileList[i][j + 1];
            }
        }
        return null;

    }  

    /* 
    * Description: Returns true if tile has reached an edge based on movement
    *              from char position
    * Inputs: int i, int j, char p
    * Outputs: returns boolean (true or false)
    */

    private boolean reachEdge(int i, int j, char p) {
        if (p == 'a') {
            if (j <= 0) {
                return true;
            }
        }
        if (p == 'd') {
            if (j >= 3) {
                return true;
            }
        }
        if (p == 'w') {
            if (i >= 3) {
                return true;
            }
        }
        if (p == 's') {
            if (i <= 0) {
                return true;
            }
        }
        return false;
    } 

    /*
    * Description: returns true if tile is blocked by another tile that 
    *              that does not share the same value
    * Inputs: int i, int j, char p
    * Outputs: boolean (true or false)
    */

    private boolean blockedByTile(int i, int j, char p) {
        if (nextTile(i, j, p) == null) {
            return false;
        }
        if (nextTile(i, j, p).shouldMergeWith(tileList[i][j])) {
            return false;
        }
        return true;
    }

    /*
    * Description: returns true if tile encounters an edge or another tile
    * Inputs: int i, int j, char p
    * Outputs: boolean (true or false)
    */

    private boolean encountersSomething(int i, int j, char p) {
        return reachEdge(i, j, p) || nextTile(i, j, p) != null;
    }

    /*
    * Description: returns true if tile can move
    * Inputs: int i, int j, char p
    * Outputs: boolean (true or false)
    */

    private boolean canMove(int i, int j, char p) {
        Tile curr = tileList[i][j];

        if (curr == null) {
            return false;
        }
        if (blockedByTile(i, j, p)) {
            return false;
        }
        if (reachEdge(i, j, p)) {
            return false;
        }
        return true;
    }

    /*
    * Description: Returns true is movement of tile, in response to character,
    *              is possible
    * Inputs: char p
    * Outputs: boolean (true or false)
    */

    private boolean isMoveViable(char p) {
        if (p == 'a' || p == 'd' || p == 's' || p == 'w') {
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    if (canMove(i, j, p)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /*
    * Description: After key pressed, all tiles that can move will move
    *              in this direction until encounters something  
    * Inputs: char p
    * Outputs: None
    */
    private void updateBoard(char p) {
        // If user wants to move up
        if (p == 'w') {
            for (int i = 3; i >= 0; i--) {
                for (int j = 0; j < 4; j++) {
                    int k = i;
                    while (!encountersSomething(k, j, p)) {
                        // change position in array
                        tileList[k + 1][j] = tileList[k][j];
                        tileList[k][j] = null;
                        k++;
                    }
                }
            }
        } 
        // If user wants to move down
        else if (p == 's') {
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    int k = i;
                    while (!encountersSomething(k, j, p)) {
                        tileList[k - 1][j] = tileList[k][j];
                        tileList[k][j] = null;
                        k--;
                    }
                }
            }
        }
        // If user wants to move left
         else if (p == 'a') {
            for (int j = 0; j < 4; j++) {
                for (int i = 0; i < 4; i++) {
                    int k = j;
                    while (!encountersSomething(i, k, p)) {
                        tileList[i][k - 1] = tileList[i][k];
                        tileList[i][k] = null;
                        k--;
                    }
                }
            }
        } 
        // If user wants to move right
        else if (p == 'd') {
            for (int j = 3; j >= 0; j--) {
                for (int i = 0; i < 4; i++) {
                    int k = j;
                    while (!encountersSomething(i, k, p)) {
                        tileList[i][k + 1] = tileList[i][k];
                        tileList[i][k] = null;
                        k++;
                    }
                }
            }
        }
    }

    /*
    * Description: Returns true if tile can merge with tile next based off
    *              direction inputed by key
    * Inputs: char p 
    * Outputs: boolean (true or false)
    */

    private boolean canMerge(int i, int j, char p) {
        // if no tile, no merge
        if (tileList[i][j] == null) {
            return false;
        }
        // if no previous tile, no merge
        if (nextTile(i, j, p) == null) {
            return false;
        }
        return nextTile(i, j, p).shouldMergeWith(tileList[i][j]);
    }

    /*
    * Description: Merges all possible tiles based off direction of key
    * Inputs: char p  
    * Outputs: None
    */ 

    private void mergeAllPossible(char p) {
        // If user wants to move up
        if (p == 'w') {
            for (int i = 3; i >= 0; i--) {
                for (int j = 0; j < 4; j++) {
                    if (canMerge(i, j, p)) {
                        nextTile(i, j, p).mergeWith(tileList[i][j]);
                        tileList[i][j] = null;
                        numberOfTiles--;
                    }
                }
            }
        } 
        // If user wants to move down
        else if (p == 's') {
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    if (canMerge(i, j, p)) {
                        nextTile(i, j, p).mergeWith(tileList[i][j]);
                        tileList[i][j] = null;
                        numberOfTiles--;
                    }
                }
            }
        } 
        // If user wants to move left
        else if (p == 'a') {
            for (int j = 0; j < 4; j++) {
                for (int i = 0; i < 4; i++) {
                    if (canMerge(i, j, p)) {
                        nextTile(i, j, p).mergeWith(tileList[i][j]);
                        tileList[i][j] = null;
                        numberOfTiles--;
                    }
                }
            }
        } 
        // If user wants to move right
        else if (p == 'd') {
            for (int j = 3; j >= 0; j--) {
                for (int i = 0; i < 4; i++) {
                    if (canMerge(i, j, p)) {
                        nextTile(i, j, p).mergeWith(tileList[i][j]);
                        tileList[i][j] = null;
                        numberOfTiles--;
                    }
                }
            }
        }
    }

    /*
    * Description: Draws console that keeps track of moves and number of tiles
    * Inputs: None
    * Outputs: None
    */

    public void drawConsole() {
        PennDraw.setFontSize(25);
        PennDraw.setPenColor(PennDraw.BLACK);
        PennDraw.text(285, 610, "Moves:" + numOfMoves);
        PennDraw.text(285, 590, "Number Of Tiles:" + numberOfTiles);
    }

    /*
    * Description: Slides tile to position on tileList
    * Inputs: None
    * Outputs: None
    */

    private void slideTiles() {
        PennDraw.enableAnimation(100);
        int[][] x = new int[4][4];
        int[][] y = new int[4][4];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (tileList[i][j] != null) {
                    x[i][j] = xCoord[j] - tileList[i][j].getX();
                    y[i][j] = yCoord[i] - tileList[i][j].getY();
                }
            }
        }
        for (int k = 0; k < 20; k++) {
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    if (tileList[i][j] != null) {
                        tileList[i][j].addToX(x[i][j] / 20);
                        tileList[i][j].addToY(y[i][j] / 20);
                    }
                }
            }
            if (PennDraw.hasNextKeyTyped()) {
                return;
            }
            Game.drawBackground();
            draw();
            drawConsole();
            PennDraw.advance();
        }
    }

    /*
    * Description: Moves tiles on board smoothly to match movement
    *              based off key 
    * Inputs: char p  
    * Outputs: None
    */

    public void move(char p) {
        if (isMoveViable(p)) {
            updateBoard(p);
            slideTiles();
            mergeAllPossible(p);
            slideTiles();
            updateBoard(p);
            slideTiles();
            updateCoordinates();
            addTile();
            numOfMoves++;
        }
    }

    /*
    * Description: Whether or not user has won
    * Inputs: None
    * Outputs: Boolean (true or false)
    */


    public boolean hasWon() {
        for (int i = 0; i < tileList.length; i++) {
            for (int j = 0; j < tileList.length; j++) {
                if (tileList[i][j] != null && tileList[i][j].getValue() >= 2048) {
                    return true;
                }
            }
        }
        return false;
    }

    /*
    * Description: Draws winning drawing
    * Inputs: None
    * Outputs: None
    */

     public void winDrawing() {
        PennDraw.setPenColor(PennDraw.GREEN);
        PennDraw.filledRectangle(300, 350, 260, 20);
        PennDraw.setFontSize(30);
        PennDraw.setPenColor(PennDraw.BLACK);
        PennDraw.text(320, 350, "You won in " + numOfMoves + " moves!");
    }

    /*
    * Description: Whether or not user has lost
    * Inputs: None
    * Outputs: Boolean (true or false)
    */

    public boolean hasLost() {
        // Not lose if number of Tiles is not at max 
        if (numberOfTiles <= 15) {
            return false;
        }
        // Loops through tileList
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                // Checks if adjacent (horizontal) tiles can merge
                if (i + 1 < 4 && tileList[i][j].shouldMergeWith(tileList[i + 1][j])) {
                    return false;
                }
                // Checks if adjacent (vertical) tiles can merge
                else if (j + 1 < 4 && tileList[i][j].shouldMergeWith(tileList[i][j + 1])) {
                    return false;
                }
            }

        }

        return true;
    }

    /* 
    * Description: Loss drawing
    * Inputs: None
    * Outputs: None
    */
    public void lossDrawing() {
        PennDraw.setPenColor(PennDraw.RED);
        PennDraw.filledRectangle(300, 350, 260, 20);
        PennDraw.setFontSize(30);
        PennDraw.setPenColor(PennDraw.BLACK);
        PennDraw.text(320, 350, "You lost in " + numOfMoves + " moves....");
    }

}