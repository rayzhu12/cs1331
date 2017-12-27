import java.util.Iterator;
import java.util.Set;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Arrays;
import java.lang.reflect.Constructor;

public class SquareSetTester {
    public static void main(String[] args) throws InvalidSquareException {
        SquareSet set = new SquareSet();
        Square a4 = new Square("a4");
        set.add(a4);
        set.add(new Square("e3"));
        set.add(new Square("f5"));
        for (Square s : set) {
            System.out.println(s);
        }
        Iterator<Square> iterator = set.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
        SquareSet set = new SquareSet();
        set.add(new Square("a1"));
        set.add(new Square("f5"));
        set.add(new Square("g8"));

        SquareSet set2 = new SquareSet();
        set2.add(new Square("e3"));
        set2.add(new Square("f5"));
        set2.add(new Square("a4"));

        SquareSet sset2 = new SquareSet();
        sset2.add(new Square("g8"));
        sset2.add(new Square("a1"));
        sset2.add(new Square("f5"));

        SquareSet sset3 = new SquareSet();
        sset3.add(new Square("g8"));
        sset3.add(new Square("a2"));
        sset3.add(new Square("f5"));

        SquareSet set4 = new SquareSet();
        set4.add(new Square("g8"));
        set4.add(new Square("f5"));

        Set<Square> set5 = new HashSet<Square>();
        set5.add(new Square("a1"));
        set5.add(new Square("f5"));
        set5.add(new Square("g8"));

        SquareSet set6 = new SquareSet();
        set6.add(new Square("a1"));
        set6.add(new Square("f5"));
        set6.add(new Square("g8"));
        set6.add(new Square("g7"));

        // System.out.println(set.equals(set5));
    }
}
