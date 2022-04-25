public class Tile {

    // x-coord of Tile
    private int x;
    // y-coord of Tile
    private int y;
    // value of Tile
    private int value;

    /*
    * Description: Constructor of tile object with value of 2 or 4
    * Inputs: None
    * Outputs: None
    */

    public Tile (int x, int y) {
        this.x = x;
        this.y = y;

        if (Math.random() < 0.7) {
            value = 2;
        }   
        else {
            value = 4;
        }   

    }

    /*
    * Description: Sets tile to certain color based on value
    * Inputs: None
    * Outputs: None
    */

    private void setTileColor() {
        if (value == 2) {
            PennDraw.setPenColor(252, 246, 246);
        } else if (value == 4) {
            PennDraw.setPenColor(239, 236, 214);
        } else if (value == 8) {
            PennDraw.setPenColor(252, 199, 119);
        } else if (value == 16) {
            PennDraw.setPenColor(249, 158, 119);
        } else if (value == 32) {
            PennDraw.setPenColor(253, 100, 62);
        } else if (value == 64) {
            PennDraw.setPenColor(250, 70, 25);
        } else if (value == 128) {
            PennDraw.setPenColor(253, 245, 135);
        } else if (value == 256) {
            PennDraw.setPenColor(250, 237, 44);
        } else if (value == 512) {
            PennDraw.setPenColor(242, 230, 40);
        } else if (value == 1024) {
            PennDraw.setPenColor(242, 227, 43);
        } else if (value == 2048) {
            PennDraw.setPenColor(202, 189, 10);
        }
        else {
            PennDraw.setPenColor(PennDraw.LIGHT_GRAY);
        }
    }

    /*
    * Description: Draws tile at location with its value
    * Inputs: None
    * Outputs: None
    */

    public void draw() {
        PennDraw.setPenRadius(0.005);
        setTileColor();
        PennDraw.filledSquare(x, y, 55);
        PennDraw.text(x, y, "" + value);
        PennDraw.setPenColor(PennDraw.BLACK);
        PennDraw.text(x, y, "" + value);
    }

    /* Description: Setter function to change tile's xCoord
    * Inputs: new x int of Tile
    * Outputs: None
    */
    
    public void setX(int newX) {
        x = newX;
    }

    /*
    * Description: Setter function to change tile's yCoord
    * Inputs: new y int of Tile
    * Outputs: none
    */

    public void setY(int newY) {
        y = newY;
    }
    
    /* 
    * Description: Getter function to get tile's value
    * Inputs: None
    * Outputs: integer value
    */

    public int getValue() {
        return value;
    }

    /* Description: Getter function to get x-coord of tile
    * Inputs: None;
    * Outputs: Integer x-coordinate of tile
    */

    public int getX() {
        return x;
    }

    /* Description: Getter function to get y-coord of tile
    * Inputs: None
    * Outputs: Integer y-coordinate of tile
    */

    public int getY() {
        return y;
    }

    /* 
    * Description: Returns true tile should merge with another tile based
    *               based on its value
    * Inputs: Tile t
    * Outputs: true or false value
    */


    public boolean shouldMergeWith(Tile t) {
        return this.value == t.value;
    }

    /*
    * Description: Adds the values of like tiles when merging
    * Inputs: Tile t
    * Outputs: None
    */

    public void mergeWith(Tile t) {
        this.value += t.value;
    }

    /* 
    * Description: Adds incremX to current xCoord
    * Inputs: int incremX
    * Outputs: None
    */

    public void addToX(int incremX) {
        x += incremX;
    }

    /*
    * Description: Adds incremY to current yCoord
    * Inputs: int incremY
    * Outputs: None
    */ 
    
    public void addToY (int incremY) {
        y += incremY;
    }

    public static void main(String[] args) {
        PennDraw.setCanvasSize(550, 650);
        PennDraw.setXscale(-25, 525);
        PennDraw.setYscale(-25, 625);
        Tile t = new Tile(50, 50);
        t.draw();
    }
    
}