/**
* Represents a King on the chessboard
* -
* @author rzhu61
* @version 1
*/

public class King extends Piece {

    /**
    * Constructor:
    * Creates a King of color param
    * @param param the Color of the King to be created
    */
    public King(Color param) {
        super(param);
    }

    /**
    * Gives the algebraic name of the King piece.
    * @return a String containing the algebraic name
    */
    @Override
    public String algebraicName() {
        return "K";
    }

    /**
    * Returns the FEN name of the King based on the color
    * @return a String containing the FEN name
    */
    @Override
    public String fenName() {
        if (getColor() == Color.BLACK) {
            return "k";
        } else {
            return "K";
        }
    }

    /**
     * Given current position, returns all the possible square to move to.
     * @param square the position of this King on the board
     * @return an array of all the possible Squares the King can move to
     */
    @Override
    public Square[] movesFrom(Square square) {
        char file = square.getFile();
        char rank = square.getRank();
        int count = 0;
        Square[] moves = new Square[8];
        for (int i = file - 1; i <= file + 1; i++) {
            for (int j = rank - 1; j <= rank + 1; j++) {
                if (!(i == file && j == rank)) {
                    if (i <= 'h' && i >= 'a' && j >= '1' && j <= '8') {
                        moves[count] = new Square((char) i, (char) j);
                        count++;
                    }
                }
            }
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
