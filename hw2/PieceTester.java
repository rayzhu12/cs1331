public class PieceTester {
    public static void main(String[] args) {
        // Piece knight = new Knight(Color.BLACK);
        // assert knight.algebraicName().equals("N");
        // assert knight.fenName().equals("n");
        // Square[] attackedSquares = knight.movesFrom(new Square("f6"));
        // // test that attackedSquares contains e8, g8, etc.
        // Square a1 = new Square("a1");
        // Square otherA1 = new Square('a', '1');
        // Square h8 = new Square("h8");
        // assert a1.equals(otherA1);
        // assert !a1.equals(h8);

        // Square a1 = new Square("a1");
        // Square otherA1 = new Square('a', '1');
        // Square h8 = new Square("h8");
        // assert a1.equals(otherA1);
        // assert !a1.equals(h8);

        // Piece pawn = new Pawn(Color.BLACK);
        // assert pawn.algebraicName().equals("p");
        // assert pawn.fenName().equals("P");
        // Square[] attackedSquares = pawn.movesFrom(new Square("d3"));
        // // test that attackedSquares contains e8, g8, etc.
        // for (int i = 0; i < attackedSquares.length; i++) {
        //     System.out.println(attackedSquares[i]);
        // }


        // Piece king = new King(Color.WHITE);
        // assert king.algebraicName().equals("K");
        // assert king.fenName().equals("P");
        // Square[] attackedSquares = king.movesFrom(new Square("a1"));
        // // test that attackedSquares contains e8, g8, etc.
        // for (int i = 0; i < attackedSquares.length; i++) {
        //     System.out.println(attackedSquares[i]);
        // }

        // Piece rook = new Rook(Color.WHITE);
        // assert rook.algebraicName().equals("9");
        // System.out.println(rook.algebraicName());
        // assert rook.fenName().equals("r");
        // System.out.println(rook.fenName());
        // Square[] attackedSquares = rook.movesFrom(new Square("f4"));
        // // test that attackedSquares contains e8, g8, etc.
        // for (int i = 0; i < attackedSquares.length; i++) {
        //     System.out.println(attackedSquares[i]);
        // }

        // Piece bishop = new Bishop(Color.BLACK);
        // assert bishop.algebraicName().equals("B");
        // System.out.println(bishop.algebraicName());
        // assert bishop.fenName().equals("B");
        // System.out.println(bishop.fenName());
        // Square[] attackedSquares =
        // bishop.movesFrom(new Square("a1"));
        //
        // for (int i = 0; i < attackedSquares.length; i++) {
        //     System.out.println(attackedSquares[i]);
        // }

        // Piece queen = new Queen(Color.BLACK);
        // assert queen.algebraicName().equals("Q");
        // System.out.println(queen.algebraicName());
        // assert queen.fenName().equals("B");
        // System.out.println(queen.fenName());
        // Square[] attackedSquares =
        // queen.movesFrom(new Square("f4"));
        //
        // for (int i = 0; i < attackedSquares.length; i++) {
        //     System.out.println(attackedSquares[i]);
        // }

        // Piece queen = new Queen(Color.BLACK);
        // assert queen.algebraicName().equals("Q");
        // System.out.println(queen.algebraicName());
        // assert queen.fenName().equals("B");
        // System.out.println(queen.fenName());
        // Square[] attackedSquares =
        // queen.movesFrom(new Square("a1"));
        //
        // for (int i = 0; i < attackedSquares.length; i++) {
        //     System.out.println(attackedSquares[i]);
        // }

        Piece knight = new Knight(Color.BLACK);
        assert knight.algebraicName().equals("N");
        assert knight.fenName().equals("n");
        Square[] attackedSquares =
        knight.movesFrom(new Square("a1"));

        for (int i = 0; i < attackedSquares.length; i++) {
            System.out.println(i);
            System.out.println(attackedSquares[i]);
        }


    }
}
