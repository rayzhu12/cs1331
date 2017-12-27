/**
 * A move in a chess game.
 * @author rzhu61
 * @version 1
 */
public class Move {
    private Ply whitePly;
    private Ply blackPly;
    /**
     * creates a single move.
     * @param  w the white ply
     * @param  b the black ply
     */
    public Move(Ply w, Ply b) {
        whitePly = w;
        blackPly = b;
    }
    /**
     * @return the white ply
     */
    public Ply getWhitePly() {
        return whitePly;
    }
    /**
     * @return the black ply
     */
    public Ply getBlackPly() {
        return blackPly;
    }
}
