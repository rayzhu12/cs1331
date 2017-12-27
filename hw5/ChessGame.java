import java.util.function.Predicate;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Tracks a game of chess.
 * @author rzhu61
 * @version 1
 */
public class ChessGame {
    private List<Move> moves;
    /**
     * Creates a list of moves; i.e. a chess game.
     * @param list  the list of moves
     */
    public ChessGame(List<Move> list) {
        moves = list;
    }
    /**
     * Returns the move at the specified index.
     * @param  n the index of the requested move
     * @return the requested Move
     */
    public Move getMove(int n) {
        return moves.get(n);
    }
    /**
     * Returns a list filtered by the Predicate.
     * @param  filter the filter with which to filter the moves
     * @return a filtered list
     */
    public List<Move> filter(Predicate<Move> filter) {
        return moves.stream().filter(filter).collect(Collectors.toList());
    }
    /**
     * Gets all the moves with comments using a lambda expression.
     * @return the list filtered accordingly
     */
    public List<Move> getMovesWithComment() {
        Predicate<Move> predcom = (move) ->
            (move.getBlackPly().getComment().isPresent()
            || move.getWhitePly().getComment().isPresent());
        return filter(predcom);
    }
    /**
     * Gets all the moves w/o comments using an anonymous inner class.
     * @return the list filtered accordingly
     */
    public List<Move> getMovesWithoutComment() {
        Predicate<Move> pred = new Predicate<Move>() {
            public boolean test(Move move) {
                return !(move.getBlackPly().getComment().isPresent()
                    || move.getWhitePly().getComment().isPresent());
            }
        };
        return filter(pred);
    }
    private class PiecePredicate implements Predicate<Move> {
        private Piece piece;
        public PiecePredicate(Piece piece) {
            this.piece = piece;
        }
        @Override
        public boolean test(Move move) {
            return (move.getWhitePly().getPiece().algebraicName()
                == piece.algebraicName()
                || move.getBlackPly().getPiece().algebraicName()
                == piece.algebraicName());
        }
    }
    /**
     * Returns all the moves with the specified piece
     * @param p the piece to filter for
     * @return list of moves with specified piece
     */
    public List<Move> getMovesWithPiece(Piece p) {
        return filter(new PiecePredicate(p));
    }
    /**
     * @return the list of moves
     */
    public List<Move> getMoves() {
        return moves;
    }
}
