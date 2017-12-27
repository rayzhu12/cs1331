import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class PgnReader {

    /**
     * Find the tagName tag pair in a PGN game and return its value.
     *
     * @see http://www.saremba.de/chessgml/standards/pgn/pgn-complete.htm
     *
     * @param tagName the name of the tag whose value you want
     * @param game a `String` containing the PGN text of a chess game
     * @return the value in the named tag pair
     */

    private static char[][] board = new char[][] {
        {'r', 'n', 'b', 'q', 'k', 'b', 'n', 'r'},
        {'p', 'p', 'p', 'p', 'p', 'p', 'p', 'p'},
        {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
        {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
        {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
        {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
        {'P', 'P', 'P', 'P', 'P', 'P', 'P', 'P'},
        {'R', 'N', 'B', 'Q', 'K', 'B', 'N', 'R'}
    };

    public static String tagValue(String tagName, String game) {
        int position = game.indexOf(tagName);
        int posOfQuote1;
        int posOfQuote2;
        if (position != -1) {
            posOfQuote1 = game.indexOf("\"", position);
            posOfQuote2 = game.indexOf("\"", posOfQuote1 + 1);
        } else {
            return "NOT GIVEN";
        }
        return game.substring(posOfQuote1 + 1, posOfQuote2);
    }

    /**
    * Creates and returns a String array with the separate turns.     *
    *
    * @param game a `String` containing a PGN-formatted chess game or opening
    * @return 2D array of Strings with the separate turns
    */
    public static String[][] listOfMoves(String game) {
        String[] moves = game.split("[0-9]+\\. ");
        String[][] finalMoves = new String[moves.length - 1][2];
        for (int i = 1; i < moves.length; i++) {
            String[] temp = moves[i].split(" ");
            if (temp.length == 2) {
                finalMoves[i - 1][0] = temp[0];
                finalMoves[i - 1][1] = temp[1];
            } else {
                finalMoves[i - 1][0] = temp[0];
            }
        }
        return finalMoves;
    }
    /**
    *Read in the moves from an array and alter
    *the pieces on the chess board accordingly
    *
    *@param moves a String array containing the separate moves
    *@return nothing
    */
    public static void moving(String[][] moves) {
        for (int i = 0; i < moves.length; i++) {
            for (int j = 0; j < moves[i].length; j++) {
                if (moves[i][j] != null && moves[i][j].length() >= 2) {
                    if (moves[i][j].charAt(0) == 'O') {
                        if (moves[i][j].length() >= 5) {
                            qCast(j == 0 ? true : false);
                        } else {
                            kCast(j == 0 ? true : false);
                        }
                    } else if (Character.isLowerCase(moves[i][j].charAt(0))) {
                        String movep;
                        char a = moves[i][j].charAt(0);
                        movep = moves[i][j].substring(2);
                        if (moves[i][j].charAt(1) == 'x') {
                            if (j == 0) {
                                wxPawn(a, convertRow(movep), convertCol(movep));
                            } else {
                                bxPawn(a, convertRow(movep), convertCol(movep));
                            }
                            if (moves[i][j].contains("=")) {
                                int o = movep.indexOf("=");
                                char pro = movep.charAt(o + 1);
                                promote(j == 0, pro,
                                    convertRow(movep), convertCol(movep));
                            }
                        } else {
                            if (j % 2 == 0) {
                                wPawn(convertRow(moves[i][j]),
                                    convertCol(moves[i][j]));
                            } else {
                                bPawn(convertRow(moves[i][j]),
                                    convertCol(moves[i][j]));
                            }
                            if (moves[i][j].contains("=")) {
                                int m = movep.indexOf("=");
                                char p = movep.charAt(m + 1);
                                promote(j == 0, p,
                                    convertRow(movep), convertCol(movep));
                            }
                        }
                    } else {
                        String move;
                        if (moves[i][j].charAt(1) == 'x') {
                            move = moves[i][j].substring(2);
                        } else {
                            move = moves[i][j].substring(1, 3);
                        }
                        if (moves[i][j].charAt(0) == 'N') {
                            if (j == 0) {
                                knight('N', convertRow(move), convertCol(move));
                            } else {
                                knight('n', convertRow(move), convertCol(move));
                            }
                        }
                        if (moves[i][j].charAt(0) == 'B') {
                            if (j == 0) {
                                bishop('B', convertRow(move), convertCol(move));
                            } else {
                                bishop('b', convertRow(move), convertCol(move));
                            }
                        }
                        if (moves[i][j].charAt(0) == 'R') {
                            if (j == 0) {
                                rook('R', convertRow(move), convertCol(move));
                            } else {
                                rook('r', convertRow(move), convertCol(move));
                            }
                        }
                        if (moves[i][j].charAt(0) == 'Q') {
                            if (j == 0) {
                                queen('Q', convertRow(move), convertCol(move));
                            } else {
                                queen('q', convertRow(move), convertCol(move));
                            }
                        }
                        if (moves[i][j].charAt(0) == 'K') {
                            if (j == 0) {
                                king('K', convertRow(move), convertCol(move));
                            } else {
                                king('k', convertRow(move), convertCol(move));
                            }
                        }
                    }
                }
            }
        }
    }
/**
*Read in the move and convert the move to an array
*index for the row.
*
*@param move a String containing the individual move
*@return an integer array index of the row
*/
    public static int convertRow(String move) {
        int row = 8 - Integer.parseInt(move.substring(1, 2));
        return row;
    }
/**
*Read in the move and convert the move to an
*array index for the column.
*
*@param move a String containing the individual row
*@return an integer array index of the column
*/
    public static int convertCol(String move) {
        int col = move.charAt(0) - 'a';
        return col;
    }

/**
*Reads in the row and column index and changes the
*location of the pawn in the array
*
*@param row an integer index of the row
*@param col an integer index of the column
*@return void
*/
    public static void wPawn(int row, int col) {
        board[row][col] = 'P';
        for (int i = board.length - 1; i >= 0; i--) {
            if (board[i][col] == 'P') {
                board[i][col] = ' ';
                i = -1;
            }
        }
    }
    public static void wxPawn(char a, int row, int col) {
        int c = a - 'a';
        if (board[row][col] == ' ') {
            board[row][col] = 'P';
            board[row + 1][c] = ' ';
            board[row + 1][col] = ' ';
        }
        if (board[row + 1][col - 1] == 'P') {
            board[row + 1][col - 1] = ' ';
            board[row][col] = 'P';
        } else if (board[row + 1][col + 1] == 'P') {
            board[row + 1][col + 1] = ' ';
            board[row][col] = 'P';
        }
    }
    public static void bPawn(int row, int col) {
        board[row][col] = 'p';
        for (int i = 0; i < board.length; i++) {
            if (board[i][col] == 'p') {
                board[i][col] = ' ';
                i = board.length;
            }
        }
    }
    public static void bxPawn(char a, int row, int col) {
        int c = a - 'a';
        if (board[row][col] == ' ') {
            board[row][col] = 'p';
            board[row - 1][c] = ' ';
            board[row - 1][col] = ' ';
        }
        if (board[row - 1][col - 1] == 'p') {
            board[row - 1][col - 1] = ' ';
            board[row][col] = 'p';
        } else if (board[row - 1][col + 1] == 'p') {
            board[row - 1][col + 1] = ' ';
            board[row][col] = 'p';
        }
    }
    public static void knight(char n, int r, int c) {
        if (r + 1 < 8 && c + 2 < 8 && board[r + 1][c + 2] == n) {
            board[r + 1][c + 2] = ' ';
        } else if (r + 1 < 8 && c - 2 >= 0 && board[r + 1][c - 2] == n) {
            board[r + 1][c - 2] = ' ';
        } else if (r - 1 >= 0 && c + 2 < 8 && board[r - 1][c + 2] == n) {
            board[r - 1][c + 2] = ' ';
        } else if (r - 1 >= 0 && c - 2 >= 0 && board[r - 1][c - 2] == n) {
            board[r - 1][c - 2] = ' ';
        } else if (r + 2 < 8 && c + 1 < 8 && board[r + 2][c + 1] == n) {
            board[r + 2][c + 1] = ' ';
        } else if (r + 2 < 8 && c - 1 >= 0 && board[r + 2][c - 1] == n) {
            board[r + 2][c - 1] = ' ';
        } else if (r - 2 >= 0 && c + 1 < 8 && board[r - 2][c + 1] == n) {
            board[r - 2][c + 1] = ' ';
        } else if (r - 2 >= 0 && c - 1 >= 0 && board[r - 2][c - 1] == n) {
            board[r - 2][c - 1] = ' ';
        }
        board[r][c] = n;
    }
    public static void bishop(char b, int row, int col) {
        for (int i = row, j = col; i < 8 && j < 8; i++, j++) {
            if (board[i][j] == b) {
                board[i][j] = ' ';
            }
        }
        for (int i = row, j = col; i < 8 && j >= 0; i++, j--) {
            if (board[i][j] == b) {
                board[i][j] = ' ';
            }
        }
        for (int i = row, j = col; i >= 0 && j >= 0; i--, j--) {
            if (board[i][j] == b) {
                board[i][j] = ' ';
            }
        }
        for (int i = row, j = col; i >= 0 && j < 8; i--, j++) {
            if (board[i][j] == b) {
                board[i][j] = ' ';
            }
        }
        board[row][col] = b;
    }
    public static void rook(char r, int row, int col) {
        int i = row;
        int j = col;
        while (i >= 0 && j >= 0) {
            if (board[i][j] == r) {
                board[i][j] = ' ';
            } else if (board[i][j] != ' ') {
                i = -1;
            }
            j--;
        }
        while (i >= 0 && j < 8) {
            if (board[i][j] == r) {
                board[i][j] = ' ';
            } else if (board[i][j] != ' ') {
                i = -1;
            }
            j++;
        }
        while (i < 8 && j < 8) {
            if (board[i][j] == r) {
                board[i][j] = ' ';
            } else if (board[i][j] != ' ') {
                i = 9;
            }
            i++;
        }
        while (i < 8 && j >= 0) {
            if (board[i][j] == r) {
                board[i][j] = ' ';
            } else if (board[i][j] != ' ') {
                i = 9;
            }
            i--;
        }
        board[row][col] = r;
    }
    public static void queen(char q, int r, int c) {
        int i = r;
        int j = c;
        while (j >= 0) {
            if (board[i][j] == q) {
                board[i][j] = ' ';
            }
            j--;
        }
        i = r;
        j = c;
        while (j < 8) {
            if (board[i][j] == q) {
                board[i][j] = ' ';
            }
            j++;
        }
        i = r;
        j = c;
        while (i < 8) {
            if (board[i][j] == q) {
                board[i][j] = ' ';
            }
            i++;
        }
        i = r;
        j = c;
        while (i >= 0) {
            if (board[i][j] == q) {
                board[i][j] = ' ';
            }
            i--;
        }
        for (int a = r, b = c; a < 8 && b < 8; a++, b++) {
            if (board[a][b] == q) {
                board[a][b] = ' ';
                board[r][c] = q;
            }
        }
        for (int a = r, b = c; a < 8 && b >= 0; a++, b--) {
            if (board[a][b] == q) {
                board[a][b] = ' ';
                board[r][c] = q;
            }
        }
        for (int a = r, b = c; a >= 0 && b >= 0; a--, b--) {
            if (board[a][b] == q) {
                board[a][b] = ' ';
                board[r][c] = q;
            }
        }
        for (int a = r, b = c; a >= 0 && b < 8; a--, b++) {
            if (board[a][b] == q) {
                board[a][b] = ' ';
                board[r][c] = q;
            }
        }
        board[r][c] = q;
    }
    public static void king(char k, int row, int col) {
        if (board[row + 1][col] == k) {
            board[row + 1][col] = ' ';
        } else if (board[row - 1][col] == k) {
            board[row - 1][col] = ' ';
        } else if (board[row][col + 1] == k) {
            board[row][col + 1] = ' ';
        } else if (board[row][col - 1] == k) {
            board[row][col - 1] = ' ';
        } else if (board[row + 1][col + 1] == k) {
            board[row + 1][col + 1] = ' ';
        } else if (board[row + 1][col - 1] == k) {
            board[row + 1][col - 1] = ' ';
        } else if (board[row - 1][col + 1] == k) {
            board[row - 1][col + 1] = ' ';
        } else if (board[row - 1][col - 1] == k) {
            board[row - 1][col - 1] = ' ';
        }
        board[row][col] = k;
    }
    public static void qCast(boolean isWhite) {
        if (isWhite) {
            board[7][4] = ' ';
            board[7][0] = ' ';
            board[7][2] = 'K';
            board[7][3] = 'R';
        } else {
            board[0][0] = ' ';
            board[0][4] = ' ';
            board[0][2] = 'k';
            board[0][3] = 'r';
        }
    }
    public static void kCast(boolean isWhite) {
        if (isWhite) {
            board[7][7] = ' ';
            board[7][4] = ' ';
            board[7][6] = 'K';
            board[7][5] = 'R';
        } else {
            board[0][7] = ' ';
            board[0][4] = ' ';
            board[0][6] = 'k';
            board[0][5] = 'r';
        }
    }
    public static void promote(boolean isWhite, char pro, int row, int col) {
        board[row][col] = isWhite ? pro : Character.toLowerCase(pro);
    }

/**
*Returns the FEN Notation of the board
*
*
* @param board 2D array of chars containing the final board
* @return a String of the final board position in FEN notation
*/
    public static String fenNotation() {
        StringBuilder builder = new StringBuilder();
        int count = 0;
        for (int i = 0; i < 8; i++) {
            count = 0;
            for (int j = 0; j < 8; j++) {
                if (j == 7) {
                    if (board[i][j] == ' ') {
                        count++;
                        builder.append(count + "/");
                    } else {
                        if (count != 0) {
                            builder.append(count);
                        }
                        builder.append(Character.toString(board[i][j]) + "/");
                    }
                    count = 0;
                } else {
                    if (board[i][j] == ' ') {
                        count++;
                    } else {
                        if (count != 0) {
                            builder.append(count);
                            builder.append(Character.toString(board[i][j]));
                            count = 0;
                        } else {
                            builder.append(Character.toString(board[i][j]));
                        }
                    }
                }
            }
        }
        return builder.toString().substring(0, builder.toString().length() - 1);
    }


/**
 * Play out the moves in game and return a String with the game's
 * final position in Forsyth-Edwards Notation (FEN).
 *
 * @see http://www.saremba.de/chessgml/standards/pgn/pgn-complete.htm#c16.1
 *
 * @param game a `String` containing a PGN-formatted chess game or opening
 * @return the game's final position in FEN.
 */
    public static String finalPosition(String game) {
        return fenNotation();
    }

/**
 * Reads the file named by path and returns its content as a String.
 *
 * @param path the relative or abolute path of the file to read
 * @return a String containing the content of the file
 */

    public static String fileContent(String path) {
        Path file = Paths.get(path);
        StringBuilder sb = new StringBuilder();
        try (BufferedReader reader = Files.newBufferedReader(file)) {
            String line = null;
            while ((line = reader.readLine()) != null) {
                // Add the \n that's removed by readline()
                sb.append(line + "\n");
            }
        } catch (IOException e) {
            System.err.format("IOException: %s%n", e);
            System.exit(1);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        String game = fileContent(args[0]);
        String[][] movesList = listOfMoves(game);

        int count = 0;
        // for (int i = 0; i < movesList.length; i++) {
        //     for (int j = 0; j < movesList[i].length; j++) {
        //         count++;
        //         if (movesList[i][j] != null) {
        //             System.out.println(movesList[i][j]);
        //         }
        //     }
        // }

        moving(movesList);

        // for (int i = 0; i < 8; i++) {
        //     for (int j = 0; j < 8; j++) {
        //         System.out.print(board[i][j]);
        //     }
        //     System.out.println();
        // }

        System.out.format("Event: %s%n", tagValue("Event", game));
        System.out.format("Site: %s%n", tagValue("Site", game));
        System.out.format("Date: %s%n", tagValue("Date", game));
        System.out.format("Round: %s%n", tagValue("Round", game));
        System.out.format("White: %s%n", tagValue("White", game));
        System.out.format("Black: %s%n", tagValue("Black", game));
        System.out.format("Result: %s%n", tagValue("Result", game));
        System.out.println("Final Position:");
        System.out.println(finalPosition(game));
    }
}
