/**
* Exception thrown when invalid square is entered
* This is a checked exception (and thus extends Exception
* rather than RuntimeException) because it occurs upon a usage
* error rather than a programming error (i.e. the user entered
* an invalid square).
* @author rzhu61
* @version 1
*/
public class InvalidSquareException extends RuntimeException {
    /**
     * [Creates a checked Exception]
     * @param name of the invalid Square
     */
    public InvalidSquareException(String name) {
        super(name);
    }
}
