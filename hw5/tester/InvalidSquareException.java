/**
* Exception thrown when invalid square is entered
* @author rzhu61
* @version 1
*/
public class InvalidSquareException extends RuntimeException {
    /**
     * @param name of the invalid Square
     */
    public InvalidSquareException(String name) {
        super(name);
    }
}
