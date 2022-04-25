public class Game {

    private static Board board = new Board();
    public static boolean isDone = false;

    /*
    * Description: Draws background 
    * Inputs: None
    * Outputs: None
    */

    public static void drawBackground() {
        PennDraw.clear();
        PennDraw.setFontSize(20);
        PennDraw.setPenColor(PennDraw.BLACK);
        PennDraw.text(285, 630, "Welcome to 2048!");

        PennDraw.setPenColor(171, 155, 155);
        PennDraw.filledSquare(300, 300, 275);
        PennDraw.setPenColor(202, 181, 181);
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                PennDraw.filledSquare(Board.xCoord[i], Board.yCoord[j], 55);
            }
        }
    }

    /*
    * Description: Main function that runs 2048 Game
    * Inputs: String arguments (or n/a)
    * Outputs: None
    */

    public static void main(String[] args) {
        PennDraw.setCanvasSize(600, 780);
        PennDraw.setXscale(-75, 700);
        PennDraw.setYscale(-50, 650);
        PennDraw.enableAnimation(100);

    // Loops until user has won or lost
        while (isDone == false) {
            drawBackground();
            board.drawConsole();
            board.draw();

            if (board.hasLost()) {
                board.lossDrawing();
                isDone = true;
            }

            if (board.hasWon()) {
                board.winDrawing();
                isDone = true;
            }
    // Calls board movement when user presses appropriate key
            if (PennDraw.hasNextKeyTyped() && !isDone) {
                board.move(PennDraw.nextKeyTyped());
            }

            PennDraw.advance();
        
        }
        
    }
}