import java.util.Collection;
import java.util.Set;
import java.util.Iterator;
import java.lang.reflect.Array;
import java.util.NoSuchElementException;

/**
 * SquareSet set for homework 4
 * CS1331
 * @author rzhu61
 * @version 1.0
 */

public class SquareSet1 implements Set<Square> {
    private Square[] master;

    /**
     * No args constructor.
     * Instantiates the backing array of size 10.
     */
    public SquareSet1() {
        master = new Square[10];
    }
    /**
     * Args constructor
     * @param c  the collection of elements to be added
     */
    public SquareSet1(Collection<? extends Square> c) {
        master = new Square[10];
        addAll(c);
    }
    /**
     * Adds a new valid Square to the Square
     * @param  Square s : square to be added
     * @return true if the Square was added successfully
     * @throws NullPointerException if the Square is null
     * @throws InvalidSquareException if the Square is invalid
     */
    @Override
    public boolean add(Square s) {
        if (s == null) {
            throw new NullPointerException();
        }
        if (!isValid(s)) {
            throw new InvalidSquareException("" + s.getFile() + s.getRank());
        }
        if (size() > 0) {
            for (Square here : master) {
                if (s.equals(here)) {
                    return false;
                }
            }
        }
        if (size() == master.length) {
            Square[] temp = new Square[master.length * 2];
            int count = 0;
            for (Square c : master) {
                if (master[count] != null) {
                    temp[count] = master[count];
                    count++;
                }
            }
            temp[size()] = s;
            master = temp;
            return true;
        } else {
            master[size()] = s;
            return true;
        }

    }
    /**
     * Checks if a given Square is a valid one.
     * @param s the square to be tested
     * @return true if the Square is valid, false if not
     */
    public boolean isValid(Square s) {
        return (s.getFile() >= 'a' && s.getFile() <= 'h'
            && s.getRank() >= '1' && s.getRank() <= '8');
    }
    /**
     * Adds all the elements of the Collection c
     * @param  Collection<?> c the elements to be added
     * @return true if elements were added
     */
    @Override
    public boolean addAll(Collection<? extends Square> c) {
        for (Square s : c) {
            if (s == null) {
                throw new NullPointerException();
            }
            if (!isValid(s)) {
                throw new InvalidSquareException(""
                    + s.getFile() + s.getRank());
            }
        }
        int fail = 0;
        for (Square s : c) {
            if (!contains(s)) {
                add(s);
            } else {
                fail++;
            }
        }
        return (fail < c.size());
    }
    /**
     * @throws UnsupportedOperationException
     */
    @Override
    public void clear() {
        throw new UnsupportedOperationException();
    }
    /**
     * Checks if the SquareSet contains given object
     * @param  Object o : the object to search for
     * @return boolean true if the specified object was found
     */
    @Override
    public boolean contains(Object o) {
        for (Square elem : master) {
            if (o != null && o.equals(elem)) {
                return true;
            }
        }
        return false;
    }
    /**
     * Checks if SquareSet contains all of the elements in c
     * @param  Collection<?> c: the list of items to check for
     * @return true if the SquareSet contains all of the items in c
     */
    @Override
    public boolean containsAll(Collection<?> c) {
        for (Object o : c) {
            if (!contains(o)) {
                return false;
            }
        }
        return true;
    }
    /**
     * Evaluates if objects compared have the same elements.
     * @param  Object o: object to be evaluated for equality
     * @return true if this is equal to Object o
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null) {
            return false;
        }
        if (!(o instanceof Set)) {
            return false;
        }
        Set other = (Set) o;
        if (other.size() != this.size()) {
            return false;
        }
        for (Object s : other) {
            if (!this.contains(s)) {
                return false;
            }
        }
        return (containsAll(other));
    }
    /**
     * Returns the hashcode of the SquareSet
     * @return the int of the HashCode
     */
    @Override
    public int hashCode() {
        int hashcode = 0;
        for (int i = 0; i < master.length; i++) {
            if (master[i] != null) {
                hashcode += master[i].hashCode();
            }
        }
        return hashcode;
    }
    /**
     * Checks if this SquareSet is empty or not
     * @return true if the SquareSet is empty
     */
    @Override
    public boolean isEmpty() {
        for (Square s : master) {
            if (s != null) {
                return false;
            }
        }
        return true;
    }
    /**
     * [iterator description]
     * @return [description]
     */
    @Override
    public Iterator<Square> iterator() {
        return new MyIterator();
    }
    /**
     *
     */
    private class MyIterator implements Iterator<Square> {
        private int count = 0;
        /**
         * [hasNext description]
         * @return [description]
         */
        @Override
        public boolean hasNext() {
            return (master[count] != null);
        }
        /**
         * [next description]
         * @return [description]
         * @throws NoSuchElementException [description]
         */
        @Override
        public Square next() throws NoSuchElementException {
            if (!hasNext()) {
                throw new NoSuchElementException();
            } else {
                count++;
            }
            return master[count - 1];
        }
    }
    /**
     * Removes specified Object o from the set.
     * @param  Object o: the object to be removed
     * @return true if the Object was successfully removed
     */
    @Override
    public boolean remove(Object o) {
        int count = 0;
        boolean removed = false;
        if (!(o instanceof Square)) {
            throw new ClassCastException();
        }
        for (Square s : master) {
            if (s != null && s.equals(o)) {
                master[count] = null;
                removed = true;
            }
            count++;
        }
        return removed;
    }
    /**
     * @throws UnsupportedOperationException
     */
    @Override
    public boolean removeAll(Collection<?> c) {
        throw new UnsupportedOperationException();
    }
    /**
     * @throws UnsupportedOperationException
     */
    @Override
    public boolean retainAll(Collection<?> c) {
        throw new UnsupportedOperationException();
    }
    /**
     * Returns the size of the SquareSet
     * @return the integer size of the backing array
     */
    @Override
    public int size() {
        int count = 0;
        for (Square s : master) {
            if (s != null) {
                count++;
            }
        }
        return count;
    }
    /**
     *@return an Object array containing the elements of SquareSet
     */
    @Override
    public Object[] toArray() {
        int count = 0;
        for (Square s : master) {
            if (s != null) {
                count++;
            }
        }
        Object[] array = new Object[count];
        count = 0;
        for (Square s : master) {
            if (s != null) {
                array[count] = s;
                count++;
            }
        }
        return array;
    }
    /**
     * [toArray description]
     * @param  T[] a: an array of generic type
     * @return an Array of type T
     */
    @Override
    public <T> T[] toArray(T[] a) {
        if (a == null) {
            throw new NullPointerException();
        }
        if (a.length < size()) {
            a = (T[]) Array.newInstance(a.getClass().getComponentType(),
             size());
        } else if (a.length > size()) {
            a[size()] = null;
        }
        System.arraycopy(master, 0, a, 0, size());
        return a;
    }
}
