/**
 *  Colors that can be used
 * @author rzhu61
 * @version 1
 */
public abstract class Piece {

    private Color color;
    /**
     * Creates a piece of specified color.
     * @param param the color of the piece
     */
    public Piece(Color param) {
        this.color = param;
    }
    /**
     * Returns the color of this piece.
     * @return the color of the piece
     */
    public Color getColor() {
        return color;
    }
    /**.
     * @return the algebraic name of the piece
     */
    public abstract String algebraicName();
    /**.
     * @return the fen name of the piece
     */
    public abstract String fenName();
    /**.
     * @param square the Square the piece is moving from
     * @return an array of all the possible Squares
     */
    public abstract Square[] movesFrom(Square square);
}
