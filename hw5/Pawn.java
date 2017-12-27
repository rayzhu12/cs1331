/**
* Represents a Pawn on the chessboard
* -
* @author rzhu61
* @version 1
*/

public class Pawn extends Piece {

    /**
    * Constructor:
    * Creates a Pawn of color param
    * @param param the Color of the Pawn to be created
    */
    public Pawn(Color param) {
        super(param);
    }

    /**
    * Gives the algebraic name of the Pawn piece.
    * @return a String containing the algebraic name
    */
    @Override
    public String algebraicName() {
        return "";
    }

    /**
    * Returns the FEN name of the Pawn based on the color
    * @return a String containing the FEN name
    */
    @Override
    public String fenName() {
        if (getColor() == Color.BLACK) {
            return "p";
        } else {
            return "P";
        }
    }

    /**
     * Given current position, returns all the possible square to move to.
     * @param square the position of this Pawn on the board
     * @return an array of all the possible Squares the Pawn can move to
     */
    @Override
    public Square[] movesFrom(Square square) {
        char file = square.getFile();
        char rank = square.getRank();
        Square[] moves;
        if ((rank == '7' && getColor() == Color.BLACK)
            || (rank == '2' && getColor() == Color.WHITE)) {
            moves = new Square[2];
        } else {
            moves = new Square[1];
        }
        switch (getColor()) {
        case BLACK:
            if (rank > '1') {
                moves[0] = new Square(file, (char) (rank - 1));
                if (rank == '7') {
                    moves[1] = new Square(file, (char) (rank - 2));
                }
            }
            break;
        case WHITE:
            if (rank < '8') {
                moves[0] = new Square(file, (char) (rank + 1));
                if (rank == '2') {
                    moves[1] = new Square(file, (char) (rank + 2));
                }
            }
            break;
        default:
            break;
        }
        int count = 0;
        for (int i = 0; i < moves.length; i++) {
            if (moves[i] != null) {
                count++;
            }
        }
        int arraycount = 0;
        Square[] finalMoves = new Square[count];
        for (int i = 0; i < moves.length; i++) {
            if (moves[i] != null) {
                finalMoves[arraycount] = moves[i];
                arraycount++;
            }
        }
        return finalMoves;
    }
}
