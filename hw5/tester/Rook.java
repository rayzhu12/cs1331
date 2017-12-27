/**
* Represents a Rook on the chessboard
* -
* @author rzhu61
* @version 1
*/

public class Rook extends Piece {

    /**
    * Constructor:
    * Creates a Rook of color param
    * @param param the Color of the Rook to be created
    */
    public Rook(Color param) {
        super(param);
    }

    /**
    * Gives the algebraic name of the Rook piece.
    * @return a String containing the algebraic name
    */
    @Override
    public String algebraicName() {
        return "R";
    }

    /**
    * Returns the FEN name of the Rook based on the color
    * @return a String containing the FEN name
    */
    @Override
    public String fenName() {
        if (getColor() == Color.BLACK) {
            return "r";
        } else {
            return "R";
        }
    }

    /**
     * Given current position, returns all the possible square to move to.
     * @param square the position of this Rook on the board
     * @return an array of all the possible Squares the Rook can move to
     */
    @Override
    public Square[] movesFrom(Square square) {
        char file = square.getFile();
        char rank = square.getRank();
        int count = 0;
        Square[] moves = new Square[14];
        for (int i = 'a'; i <= 'h'; i++) {
            if (i != (int) file) {
                moves[count] = new Square((char) i, rank);
                count++;
            }
        }
        for (int j = '1'; j <= '8'; j++) {
            if (j != (int) rank) {
                moves[count] = new Square(file, (char) j);
                count++;
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
