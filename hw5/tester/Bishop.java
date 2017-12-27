/**
* Represents a Bishop on the chessboard
* -
* @author rzhu61
* @version 1
*/
public class Bishop extends Piece {

    /**
    * Constructor:
    * Creates a Bishop of color param
    * @param param the Color of the Bishop to be created
    */
    public Bishop(Color param) {
        super(param);
    }

    /**
    * Gives the algebraic name of the Bishop piece.
    * @return a String containing the algebraic name
    */
    @Override
    public String algebraicName() {
        return "B";
    }

    /**
    * Returns the FEN name of the Bishop based on the color
    * @return a String containing the FEN name
    */
    @Override
    public String fenName() {
        if (getColor() == Color.BLACK) {
            return "b";
        } else {
            return "B";
        }
    }

    /**
     * Given current position, returns all the possible square to move to.
     * @param square the position of this Bishop on the board
     * @return an array of all the possible Squares the Bishop can move to
     */
    @Override
    public Square[] movesFrom(Square square) {
        char file = square.getFile();
        int f = (int) file;
        char rank = square.getRank();
        int r = (int) rank;
        Square[] moves = new Square[13];
        int count = 0;

        while (f <= 'h' && r <= '8') {
            if (f != file && r != rank) {
                moves[count] = new Square((char) f, (char) r);
                count++;
            }
            f++;
            r++;
        }
        f = (int) file;
        r = (int) rank;
        while (f <= 'h' && r >= '1') {
            if (f != file && r != rank) {
                moves[count] = new Square((char) f, (char) r);
                count++;
            }
            f++;
            r--;
        }
        f = (int) file;
        r = (int) rank;
        while (f >= 'a' && r <= '8') {
            if (f != file && r != rank) {
                moves[count] = new Square((char) f, (char) r);
                count++;
            }
            f--;
            r++;
        }
        f = (int) file;
        r = (int) rank;
        while (f >= 'a' && r >= '1') {
            if (f != file && r != rank) {
                moves[count] = new Square((char) f, (char) r);
                count++;
            }
            f--;
            r--;
        }
        f = (int) file;
        r = (int) rank;

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
