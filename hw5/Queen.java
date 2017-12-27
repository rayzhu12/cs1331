/**
* Represents a Queen on the chessboard
* -
* @author rzhu61
* @version 1
*/

public class Queen extends Piece {

    /**
    * Constructor:
    * Creates a Queen of color param
    * @param param the Color of the Queen to be created
    */

    public Queen(Color param) {
        super(param);
    }

    /**
    * Gives the algebraic name of the Queen piece.
    * @return a String containing the algebraic name
    */
    @Override
    public String algebraicName() {
        return "Q";
    }

    /**
    * Returns the FEN name of the Queen based on the color
    * @return a String containing the FEN name
    */
    @Override
    public String fenName() {
        if (getColor() == Color.BLACK) {
            return "q";
        } else {
            return "Q";
        }
    }

    /**
     * Given current position, returns all the possible square to move to.
     * @param square the position of this Queen on the board
     * @return an array of all the possible Squares the Queen can move to
     */
    @Override
    public Square[] movesFrom(Square square) {
        char file = square.getFile();
        int f = (int) file;
        char rank = square.getRank();
        int r = (int) rank;
        Square[] moves = new Square[28];
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
