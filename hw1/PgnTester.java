import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class PgnTester {

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
                if (Character.isLowerCase(moves[i][j].charAt(0))) {
                    if (j%2 == 0) {
                        wPawn(convertsRow(moves[i][j]), convertsCol(moves[i][j]));
                    } else {
                        bPawn(convertsRow(moves[i][j]), convertsCol(moves[i][j]));
                    }
                    System.out.println(convertsRow(moves[i][j]));
                    System.out.println(convertsCol(moves[i][j]));
                }
                String move = moves[i][j].substring(1);
                if (moves.charAt(0) == 'N' || moves.charAt(0) == 'n') {
                    knight(convertsRow(move), convertsCol(move));
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
    public static int convertsRow(String move) {
        int row = 8 - Integer.parseInt(move.substring(1), 10);
        return row;
    }
/**
*Read in the move and convert the move to an
*array index for the column.
*
*@param move a String containing the individual row
*@return an integer array index of the column
*/
    public static int convertsCol(String move) {
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
    public static void bPawn(int row, int col) {
        board[row][col] = 'p';
        for (int i = 0; i < board.length; i++) {
            if (board[i][col] == 'p') {
                board[i][col] = ' ';
                i = board.length;
            }
        }
    }
    public static void knight(int row, int col) {

    }

    public static void wBishop(int row, int col) {

    }
    public static void bBishop(int row, int col) {

    }


/**
*Returns the FEN Notation of the board
*
*
* @param board 2D array of chars containing the final board
* @return a String of the final board position in FEN notation
*/
    public static String fenNotation(char[][] board) {
        StringBuilder builder = new StringBuilder();
        int count = 0;
        for (int i = 0; i < board.length; i++) {
            count = 0;
            for (int j = 0; j < board[i].length; j++) {
                if (j == 7) {
                    if (board[i][j] == ' ') {
                        count++;
                        builder.append(count + "/");
                    } else {
                        builder.append(Character.toString(board[i][j]) + "/");
                    }
                    count = 0;
                } else {
                    if (board[i][j] == ' ') {
                        count++;
                    } else {
                        if (count != 0) {
                            builder.append(count
                                + Character.toString(board[i][j]));
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
        return fenNotation(board);
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
        for (int i = 0; i < movesList.length; i++) {
            for (int j = 0; j < movesList[i].length; j++) {
                if (movesList[i][j] != null) {
                    System.out.println(movesList[i][j]);
                }
            }
        }

        moving(movesList);


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
