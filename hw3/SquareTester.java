public class SquareTester {
    public static void main(String[] args) throws InvalidSquareException {
        try {
            new Square("a1");
            System.out.println("square a1 passed the test!");
        } catch (InvalidSquareException e) {
            System.out.println(e.getMessage());
        }
        try {
            String invalidSquare = "a9";
            new Square(invalidSquare);
            System.out.println(invalidSquare);
        } catch (InvalidSquareException e) {
            System.out.println("a9 failed the test!");
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        try {
            String invalidSquare = "a8    ";
            new Square(invalidSquare);
            System.out.println(invalidSquare);
            System.out.println("square a8 passed the test!");
        } catch (InvalidSquareException e) {
            System.out.println("a8 failed the test!");
            System.out.println(e.getMessage());
        }
        Square s = new Square("f7");
        System.out.println('f' == s.getFile());
        System.out.println('7' == s.getRank());
        Square s2 = new Square('e', '4');
        System.out.println("e4".equals(s2.toString()));
        System.out.println(s2.toString());
    }
}
