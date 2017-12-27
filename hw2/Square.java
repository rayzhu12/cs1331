/**
* Represents a square on the chessboard
* -
* @author rzhu61
* @version 1
*/

public class Square {
    private char file;
    private char rank;

    /**
     * Constructor
     * Creates a square with the file and rank by calling the other constructor
     *
     * @param file the char representation of the file
     * @param rank the char representation of the rank
     */
    public Square(char file, char rank) throws InvalidSquareException {
        this(Character.toString(file) + Character.toString(rank));
    }

    /**
     * Constructor:
     * Creates a Square with all required parameters.
     *
     * @param name a String containing the file and rank
     */
    public Square(String name) throws InvalidSquareException {
        if (!validFile(name.charAt(0)) || !validRank(name.charAt(1))) {
            throw new InvalidSquareException("Invalid file!");
        } else {
            this.file = name.charAt(0);
            this.rank = name.charAt(1);
        }
    }
    /**
     * Given a file, returns true if the file is valid and false if it is not.
     * @param thisfile the char representation of the file
     * @return true if the file is a valid file
     */
    public boolean validFile(char thisfile) {
        if (thisfile >= 'a' && thisfile <= 'h') {
            return true;
        }
        return false;
    }

    /**
     * Given a rank, returns true if the rank is valid and false if it is not.
     * @param thisrank the char representation of the rank
     * @return true if the rank is a valid
     */
    public boolean validRank(char thisrank) {
        if (thisrank >= '1' && thisrank <= '8') {
            return true;
        }
        return false;
    }

    /**
     * @return the Square's file as a char
     */
    public char getFile() {
        return file;
    }

    /**
     * @return the Square's rank as a char
     */
    public char getRank() {
        return rank;
    }

    /**
     * @return the String reppresentation of the Square
     *    by concatenating the file and rank
     */
    public String toString() {
        return Character.toString(file) + Character.toString(rank);
    }

    /**
     * @param that an object to be compared to this for functional equality
     * @return true if and only if the
     */
    @Override
    public boolean equals(Object that) {
        if (null == that) {
            return false;
        }
        if (this == that) {
            return true;
        }
        if (!(that instanceof Square)) {
            return false;
        }
        Square those = (Square) that;
        return (this.file == those.getFile()) && (this.rank == those.getRank());
    }
}
