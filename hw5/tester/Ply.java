import java.util.Optional;
/**
 * Tracks a single player's turn in a chess game.
 * @author rzhu61
 * @version 1
 */
public class Ply {
    private Piece piece;
    private Square from;
    private Square to;
    private Optional<String> comment;
    /**
     * Creates an instance of a Ply.
     * @param  p the piece
     * @param  f the Square from
     * @param  t the Square to
     * @param  c an optional comment
     */
    public Ply(Piece p, Square f, Square t, Optional<String> c) {
        piece = p;
        from = f;
        to = t;
        comment = c;
    }
    /**
     * @return the piece
     */
    public Piece getPiece() {
        return piece;
    }
    /**
     * @return the square from
     */
    public Square getFrom() {
        return from;
    }
    /**
     * @return the square to
     */
    public Square getTo() {
        return to;
    }
    /**
     * @return the optional comment
     */
    public Optional getComment() {
        return comment;
    }
}
