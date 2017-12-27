/**
* Represents a Knight on the chessboard
* -
* @author rzhu61
* @version 1
*/

public class Knight extends Piece {

    /**
    * Constructor:
    * Creates a Knight of color param
    * @param param the Color of the Knight to be created
    */
    public Knight(Color param) {
        super(param);
    }

    /**
    * Gives the algebraic name of the Knight piece.
    * @return a String containing the algebraic name
    */
    @Override
    public String algebraicName() {
        return "N";
    }

    /**
    * Returns the FEN name of the Knight based on the color
    * @return a String containing the FEN name
    */
    @Override
    public String fenName() {
        if (getColor() == Color.BLACK) {
            return "n";
        } else {
            return "N";
        }
    }

    /**
     * Given current position, returns all the possible square to move to.
     * @param square the position of this Knight on the board
     * @return an array of all the possible Squares the Knight can move to
     */
    @Override
    public Square[] movesFrom(Square square) {
        char file = square.getFile();
        int f = (int) file;
        char rank = square.getRank();
        int r = (int) rank;
        Square[] moves = new Square[8];
        int count = 0;
        if (f - 1 >= 'a' && r - 2 >= '1') {
            moves[count] = new Square((char) (f - 1), (char) (r - 2));
            count++;
        }
        if (f + 1 <= 'h' && r - 2 >= '1') {
            moves[count] = new Square((char) (f + 1), (char) (r - 2));
            count++;
        }
        if (f - 2 >= 'a' && r + 1 <= '8') {
            moves[count] = new Square((char) (f - 2), (char) (r + 1));
            count++;
        }
        if (f - 2 >= 'a' && r - 1 >= '1') {
            moves[count] = new Square((char) (f - 2), (char) (r - 1));
            count++;
        }
        if (f + 2 <= 'h' && r + 1 <= '8') {
            moves[count] = new Square((char) (f + 2), (char) (r + 1));
            count++;
        }
        if (f + 2 <= 'h' && r - 1 >= '1') {
            moves[count] = new Square((char) (f + 2), (char) (r - 1));
            count++;
        }
        if (f - 1 >= 'a' && r + 2 <= '8') {
            moves[count] = new Square((char) (f - 1), (char) (r + 2));
            count++;
        }
        if (f + 1 <= 'h' && r + 2 <= '8') {
            moves[count] = new Square((char) (f + 1), (char) (r + 2));
            count++;
        }
        Square[] legalMoves = new Square[count];
        count = 0;
        for (int m = 0; m < moves.length; m++) {
            if (moves[m] != null) {
                legalMoves[count] = moves[m];
                count++;
            }
        }
        return legalMoves;
    }
}
