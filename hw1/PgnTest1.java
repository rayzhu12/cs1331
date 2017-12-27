import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class PgnTest1 {

    /**
     * Find the tagName tag pair in a PGN game and return its value.
     *
     * @see http://www.saremba.de/chessgml/standards/pgn/pgn-complete.htm
     *
     * @param tagName the name of the tag whose value you want
     * @param game a `String` containing the PGN text of a chess game
     * @return the value in the named tag pair
     */
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
     * @see
     *
     * @param game a `String` containing a PGN-formatted chess game or opening
     * @return array of Strings with the separate turns
     */
    public static String[] moves(String game) {
        String[] moves = game.split("[0-9]+\\.");
        return moves;
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
        return fenNotation(newBoard());
    }
    /**
     * Creates a 2D character array called NewBoard
     *
     *
     * @return a 2D char array containing a new board
     */
    public static char[][] newBoard() {
        char[][] newBoard = new char[][] {
            {'r', 'n', ' ', 'q', 'k', 'b', 'n', 'r'},
            {'p', 'p', 'p', 'p', 'p', 'p', 'p', 'p'},
        };
        return newBoard;
    }

    public static String fenNotation(char[][] board) {
        StringBuilder builder = new StringBuilder();
        int count = 0;
        for (int i = 0; i < board.length; i++) {
            count=0;
            System.out.println("New row" + count);

            for (int j = 0; j < board[i].length; j++) {
                if (j==7) {
                    System.out.println("Reached end of line" + count);
                    if (board[i][j]==' ') {
                        count++;
                        System.out.println("empty space" + count);
                        builder.append(count);
                        System.out.println(builder.toString());

                    }
                        builder.append("/");
                    }
                else {
                    System.out.println("not empty" + count);
                    if (board[i][j]==' ') {
                        count++;
                        System.out.println("added to count" + count);
                    }
                    else {
                        if (count!=0) {
                            System.out.println("count not equal to zero" + count);
                            builder.append(count + Character.toString(board[i][j]));
                            count=0;
                            System.out.println(builder.toString());
                            System.out.println(board[i][j]);
                        }
                        else {
                            System.out.println(count);
                            builder.append(board[i][j]);
                            System.out.println(builder.toString());
                        }
                    }
                }
            }
        }
        return builder.toString();
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

        // System.out.println(board);
        for (String move: moves(game)) {
            System.out.println(move);
        }
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
