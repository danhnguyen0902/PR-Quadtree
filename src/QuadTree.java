import java.util.ArrayList;
import java.util.List;

// -------------------------------------------------------------------------
/**
 * The QuadTree that references to the database
 *
 * @param <T>
 *            The type of data element contained in the node.
 * @author Danh
 * @version Mar 5, 2014
 */
public class QuadTree<T>
{
    private static final int MIN_RANGE = 0;
    private static final int MAX_RANGE = 1023;

    private int              xMinimum  = MIN_RANGE;
    private int              yMinimum  = MIN_RANGE;
    private int              xMaximum  = (MAX_RANGE + 1);
    private int              yMaximum  = (MAX_RANGE + 1);

    private boolean          duplicate;
    private T                deletedElement;

    private QuadNode<T>      flyWeight;
    private QuadNode<T>      root;
    private Extractor2D<T>   extractor;


    // ----------------------------------------------------------
    /**
     * Create a new QuadTree object.
     *
     * @param extractor
     *            used to extract the location of the object
     */
    public QuadTree(Extractor2D<T> extractor)
    {
        // Use default constructor
        flyWeight = new EmptyNode<T>();

        // root is an empty node at first
        root = flyWeight;

        this.extractor = extractor;
    }


    // ----------------------------------------------------------
    /**
     * Insert an element into the tree
     *
     * @param elem
     *            the element that needs to be inserted
     * @return 1 Out of Bounds, 2 Duplicate, 0 Successfully Inserted
     */
    public int insert(T elem)
    {
        // Check if Out of Bounds
        Point2D point = extractor.getPoint2D(elem);
        if (point.getX() < MIN_RANGE || point.getX() > MAX_RANGE
            || point.getY() < MIN_RANGE || point.getY() > MAX_RANGE)
        {
            return 1;
        }

        // Insert
        QuadNode<T> tmp;

        duplicate = false;
        tmp =
            root.insert(
                point.getX(),
                point.getY(),
                elem,
                xMinimum,
                yMinimum,
                xMaximum,
                yMaximum);

        // Duplicate
        if (duplicate)
        {
            return 2;
        }
        // Successful
        else
        {
            root = tmp;
            return 0;
        }
    }


    // ----------------------------------------------------------
    /**
     * Remove all elements out of the tree
     */
    public void makeNull()
    {
        root = flyWeight;
    }


    // ----------------------------------------------------------
    /**
     * Return the elements on the tree in pre-order
     *
     * @return list of result elements
     */
    public List<StringBuilder> treeLocation()
    {
        List<StringBuilder> list = new ArrayList<StringBuilder>();

        root.tree(list, 0, xMinimum, yMinimum, xMaximum, yMaximum);

        return list;
    }


    // ----------------------------------------------------------
    /**
     * Find an element with the given location
     *
     * @param x
     *            x coordinate of the element
     * @param y
     *            y coordinate of the element
     * @return the element if found, null otherwise
     */
    public T findLocation(int x, int y)
    {
        // Check if Out of Bounds
        if (x < MIN_RANGE || x > MAX_RANGE || y < MIN_RANGE || y > MAX_RANGE)
        {
            return null;
        }

        return root.find(x, y, xMinimum, yMinimum, xMaximum, yMaximum);
    }


    // ----------------------------------------------------------
    /**
     * Remove the element with the given location out of the tree
     *
     * @param x
     *            x coordinate of the element
     * @param y
     *            y coordinate of the element
     * @return deleted element, null if there's no element to delete
     */
    public T deleteLocation(int x, int y)
    {
        // Check if Out of Bounds
        if (x < MIN_RANGE || x > MAX_RANGE || y < MIN_RANGE || y > MAX_RANGE)
        {
            return null;
        }

        deletedElement = null;

        root = root.delete(x, y, xMinimum, yMinimum, xMaximum, yMaximum);

        return deletedElement;
    }


    // ----------------------------------------------------------
    /**
     * Find all the objects located within a given bound
     *
     * @param xLow
     *            x top left of the bound
     * @param yLow
     *            y top left of the bound
     * @param xHigh
     *            x bottom right of the bound
     * @param yHigh
     *            y bottom right of the bound
     * @return list of result elements
     */
    public List<StringBuilder> rFind(int xLow, int yLow, int xHigh, int yHigh)
    {
        List<StringBuilder> list = new ArrayList<StringBuilder>();

        // Check if Out of Bounds
        if (xHigh < MIN_RANGE || xLow > MAX_RANGE || yHigh < MIN_RANGE
            || yLow > MAX_RANGE)
        {
            return list;
        }

        root.rFind(
            list,
            xLow,
            yLow,
            xHigh,
            yHigh,
            xMinimum,
            yMinimum,
            xMaximum,
            yMaximum);

        return list;
    }


    // -------------------------------------------------------------------------
    /**
     * Abstract node in the QuadTree
     *
     * @param <E>
     *            The type of data element contained in the node.
     * @author Danh
     * @version Mar 5, 2014
     */
    private interface QuadNode<E>
    {
        // ----------------------------------------------------------
        /**
         * Insert an element into the tree
         *
         * @param x
         *            x coordinate of the current node
         * @param y
         *            y coordinate of the current node
         * @param elem
         *            inserted element
         * @param xMin
         *            the current minimum x coordinate
         * @param yMin
         *            the current minimum y coordinate
         * @param xMax
         *            the current maximum x coordinate
         * @param yMax
         *            the current maximum y coordinate
         * @return the new tree with the element inserted
         */
        public QuadNode<E> insert(
            int x,
            int y,
            E elem,
            int xMin,
            int yMin,
            int xMax,
            int yMax);


        // ----------------------------------------------------------
        /**
         * Find an element in the tree
         *
         * @param x
         *            x coordinate
         * @param y
         *            y coordinate
         * @param xMin
         *            the current minimum x coordinate
         * @param yMin
         *            the current minimum y coordinate
         * @param xMax
         *            the current maximum x coordinate
         * @param yMax
         *            the current maximum y coordinate
         * @return the element found
         */
        public E find(int x, int y, int xMin, int yMin, int xMax, int yMax);


        // ----------------------------------------------------------
        /**
         * Remove an element out of the tree
         *
         * @param x
         *            x coordinate
         * @param y
         *            y coordinate
         * @param xMin
         *            the current minimum x coordinate
         * @param yMin
         *            the current minimum y coordinate
         * @param xMax
         *            the current maximum x coordinate
         * @param yMax
         *            the current maximum y coordinate
         * @return the tree after deletion
         */
        public QuadNode<E> delete(
            int x,
            int y,
            int xMin,
            int yMin,
            int xMax,
            int yMax);


        // ----------------------------------------------------------
        /**
         * List out the elements on the tree in pre-order
         *
         * @param list
         *            list of results
         * @param height
         *            the current height of the tree
         * @param xMin
         *            the current minimum x coordinate
         * @param yMin
         *            the current minimum y coordinate
         * @param xMax
         *            the current maximum x coordinate
         * @param yMax
         *            the current maximum y coordinate
         */
        public void tree(
            List<StringBuilder> list,
            int height,
            int xMin,
            int yMin,
            int xMax,
            int yMax);


        /**
         * List out the elements within a given bound
         *
         * @param list
         *            list of results
         * @param xLow
         *            x top left of the bound
         * @param yLow
         *            y top left of the bound
         * @param xHigh
         *            x bottom right of the bound
         * @param yHigh
         *            y bottom right of the bound
         * @param xMin
         *            the current minimum x coordinate
         * @param yMin
         *            the current minimum y coordinate
         * @param xMax
         *            the current maximum x coordinate
         * @param yMax
         *            the current maximum y coordinate
         */
        public void rFind(
            List<StringBuilder> list,
            int xLow,
            int yLow,
            int xHigh,
            int yHigh,
            int xMin,
            int yMin,
            int xMax,
            int yMax);
    }


    // -------------------------------------------------------------------------
    /**
     * Represents an empty node in the QuadTree
     *
     * @param <E>
     *            The type of data element contained in the node.
     * @author Danh
     * @version Mar 5, 2014
     */
    private class EmptyNode<E>
        implements QuadNode<E>
    {
        @Override
        public QuadNode<E> insert(
            int x,
            int y,
            E elem,
            int xMin,
            int yMin,
            int xMax,
            int yMax)
        {
            return new LeafNode<E>(elem);
        }


        @Override
        public E find(int x, int y, int xMin, int yMin, int xMax, int yMax)
        {
            return null;
        }


        @SuppressWarnings("unchecked")
        @Override
        public QuadNode<E> delete(
            int x,
            int y,
            int xMin,
            int yMin,
            int xMax,
            int yMax)
        {
            return (EmptyNode<E>)flyWeight;
        }


        @Override
        public void tree(
            List<StringBuilder> list,
            int height,
            int xMin,
            int yMin,
            int xMax,
            int yMax)
        {
            StringBuilder string = new StringBuilder("");

            for (int i = 1; i <= 4 * height; ++i)
            {
                string.append(".");
            }

            string.append("Empty");

            list.add(string);
        }


        @Override
        public void rFind(
            List<StringBuilder> list,
            int xLow,
            int yLow,
            int xHigh,
            int yHigh,
            int xMin,
            int yMin,
            int xMax,
            int yMax)
        {
            // do nothing
        }
    }


    // -------------------------------------------------------------------------
    /**
     * Represents a leaf node in the QuadTree
     *
     * @param <E>
     *            The type of data element contained in the node.
     * @author Danh
     * @version Mar 5, 2014
     */
    public class LeafNode<E>
        implements QuadNode<E>
    {
        private E       element;
        private Point2D point;


        // ----------------------------------------------------------
        /**
         * Create a new LeafNode object.
         *
         * @param elem
         *            the given element
         */
        @SuppressWarnings("unchecked")
        public LeafNode(E elem)
        {
            this.element = elem;

            point = extractor.getPoint2D((T)this.element);
        }


        // ----------------------------------------------------------
        /**
         * Return the element of the leaf node
         *
         * @return the element
         */
        public E getElement()
        {
            return this.element;
        }


        @Override
        public QuadNode<E> insert(
            int x,
            int y,
            E elem,
            int xMin,
            int yMin,
            int xMax,
            int yMax)
        {
            // Duplicate
            if (point.getX() == x && point.getY() == y)
            {
                duplicate = true;
                return null;
            }
            // Successful
            else
            {
                QuadNode<E> internal = new InternalNode<E>();
                QuadNode<E> tmp =
                    internal.insert(
                        point.getX(),
                        point.getY(),
                        this.element,
                        xMin,
                        yMin,
                        xMax,
                        yMax);

                return tmp.insert(x, y, elem, xMin, yMin, xMax, yMax);
            }
        }


        @Override
        public E find(int x, int y, int xMin, int yMin, int xMax, int yMax)
        {
            if (point.getX() == x && point.getY() == y)
            {
                return element;
            }
            else
            {
                return null;
            }
        }


        @SuppressWarnings("unchecked")
        @Override
        public QuadNode<E> delete(
            int x,
            int y,
            int xMin,
            int yMin,
            int xMax,
            int yMax)
        {
            if (point.getX() == x && point.getY() == y)
            {
                deletedElement = (T)this.getElement();

                return (EmptyNode<E>)flyWeight;
            }

            return this;
        }


        @Override
        public void tree(
            List<StringBuilder> list,
            int height,
            int xMin,
            int yMin,
            int xMax,
            int yMax)
        {
            StringBuilder string = new StringBuilder("");

            for (int i = 1; i <= 4 * height; ++i)
            {
                string.append(".");
            }

            string.append(this.element);

            list.add(string);
        }


        @Override
        public void rFind(
            List<StringBuilder> list,
            int xLow,
            int yLow,
            int xHigh,
            int yHigh,
            int xMin,
            int yMin,
            int xMax,
            int yMax)
        {
            if (xLow <= point.getX() && point.getX() <= xHigh
                && yLow <= point.getY() && point.getY() <= yHigh)
            {
                StringBuilder string = new StringBuilder("");
                string.append(this.getElement());

                list.add(string);
            }
        }
    }


    // -------------------------------------------------------------------------
    /**
     * Represents an internal node in the QuadTree
     *
     * @param <E>
     *            The type of data element contained in the node.
     * @author Danh
     * @version Mar 5, 2014
     */
    public class InternalNode<E>
        implements QuadNode<E>
    {
        private QuadNode<E> northWest;
        private QuadNode<E> northEast;
        private QuadNode<E> southWest;
        private QuadNode<E> southEast;


        // ----------------------------------------------------------
        /**
         * Create a new InternalNode object.
         */
        @SuppressWarnings("unchecked")
        public InternalNode()
        {
            northWest = (EmptyNode<E>)flyWeight;
            northEast = (EmptyNode<E>)flyWeight;
            southWest = (EmptyNode<E>)flyWeight;
            southEast = (EmptyNode<E>)flyWeight;
        }


        @Override
        public QuadNode<E> insert(
            int x,
            int y,
            E elem,
            int xMin,
            int yMin,
            int xMax,
            int yMax)
        {
            QuadNode<E> tmp;
            int xMid = (xMin + xMax) / 2;
            int yMid = (yMin + yMax) / 2;

            // ((InternalNode<E>)node).NW
            if (x < xMid && y < yMid) // NW
            {
                tmp = northWest.insert(x, y, elem, xMin, yMin, xMid, yMid);
                if (!duplicate)
                {
                    northWest = tmp;
                }
            }

            if (x >= xMid && y < yMid) // NE
            {
                tmp = northEast.insert(x, y, elem, xMid, yMin, xMax, yMid);
                if (!duplicate)
                {
                    northEast = tmp;
                }
            }

            if (x < xMid && y >= yMid) // SW
            {
                tmp = southWest.insert(x, y, elem, xMin, yMid, xMid, yMax);
                if (!duplicate)
                {
                    southWest = tmp;
                }
            }

            if (x >= xMid && y >= yMid) // SE
            {
                tmp = southEast.insert(x, y, elem, xMid, yMid, xMax, yMax);
                if (!duplicate)
                {
                    southEast = tmp;
                }
            }

            return this;
        }


        @Override
        public E find(int x, int y, int xMin, int yMin, int xMax, int yMax)
        {
            int xMid = (xMin + xMax) / 2;
            int yMid = (yMin + yMax) / 2;

            if (x < xMid && y < yMid) // NW
            {
                return northWest.find(x, y, xMin, yMin, xMid, yMid);
            }
            else if (x >= xMid && y < yMid) // NE
            {
                return northEast.find(x, y, xMid, yMin, xMax, yMid);
            }
            else if (x < xMid && y >= yMid) // SW
            {
                return southWest.find(x, y, xMin, yMid, xMid, yMax);
            }
            else
            // if (x >= xMid && y >= yMid) // SE
            {
                return southEast.find(x, y, xMid, yMid, xMax, yMax);
            }
        }


        @SuppressWarnings("unchecked")
        @Override
        public QuadNode<E> delete(
            int x,
            int y,
            int xMin,
            int yMin,
            int xMax,
            int yMax)
        {
            // delete
            int xMid = (xMin + xMax) / 2;
            int yMid = (yMin + yMax) / 2;

            if (x < xMid && y < yMid) // NW
            {
                northWest = northWest.delete(x, y, xMin, yMin, xMid, yMid);
            }

            if (x >= xMid && y < yMid) // NE
            {
                northEast = northEast.delete(x, y, xMid, yMin, xMax, yMid);
            }

            if (x < xMid && y >= yMid) // SW
            {
                southWest = southWest.delete(x, y, xMin, yMid, xMid, yMax);
            }

            if (x >= xMid && y >= yMid) // SE
            {
                southEast = southEast.delete(x, y, xMid, yMid, xMax, yMax);
            }

            // count the number of empty nodes after deletion (if any)
            int count = 0;
            QuadNode<E> tmp = null;

            if (northWest == flyWeight)
            {
                ++count;
            }
            else
            {
                tmp = northWest;
            }

            if (northEast == flyWeight)
            {
                ++count;
            }
            else
            {
                tmp = northEast;
            }

            if (southWest == flyWeight)
            {
                ++count;
            }
            else
            {
                tmp = southWest;
            }

            if (southEast == flyWeight)
            {
                ++count;
            }
            else
            {
                tmp = southEast;
            }

            // if the internal node has 3 empty nodes then reduce it to a leaf
            if (count == 3
                && tmp.getClass().toString().equals("class QuadTree$LeafNode"))
            {
                // System.out.println(tmp.getClass().toString());
                // This one doesn't work:
                // if (tmp.getClass().equals("class QuadTree$LeafNode"))

                return new LeafNode<E>(((LeafNode<E>)tmp).getElement());
            }

            return this;
        }


        @Override
        public void tree(
            List<StringBuilder> list,
            int height,
            int xMin,
            int yMin,
            int xMax,
            int yMax)
        {
            int xMid = (xMin + xMax) / 2;
            int yMid = (yMin + yMax) / 2;

            StringBuilder string = new StringBuilder("");

            for (int i = 1; i <= 4 * height; ++i)
            {
                string.append(".");
            }

            string.append("Internal ");
            string.append("(");
            string.append(xMid);
            string.append(", ");
            string.append(yMid);
            string.append(")");

            list.add(string);

            northWest.tree(list, height + 1, xMin, yMin, xMid, yMid);
            northEast.tree(list, height + 1, xMid, yMin, xMax, yMid);
            southWest.tree(list, height + 1, xMin, yMid, xMid, yMax);
            southEast.tree(list, height + 1, xMid, yMid, xMax, yMax);
        }


// -------------------------------rFind------------------------------------
        // ----------------------------------------------------------
        /**
         * Check if a value is in between to end points
         *
         * @param value
         *            the value that needs to be checked
         * @param min
         *            the first end point
         * @param max
         *            the second end point
         * @return true if it's in, false otherwise
         */
        public boolean valueInRange(int value, int min, int max)
        {
            return (value >= min) && (value <= max);
        }


        // ----------------------------------------------------------
        /**
         * Check if two rectangles overlaps each other
         *
         * @param xLow
         *            x top left of the 1st rectangle
         * @param yLow
         *            y top left of the 1st rectangle
         * @param xHigh
         *            x bottom right of the 1st rectangle
         * @param yHigh
         *            y bottom right of the 1st rectangle
         * @param xMin
         *            x top left of the 2nd rectangle
         * @param yMin
         *            y top left of the 2nd rectangle
         * @param xMax
         *            x bottom right of the 2nd rectangle
         * @param yMax
         *            y bottom right of the 2nd rectangle
         * @return true if they overlap, false otherwise
         */
        public boolean overlap(
            int xLow,
            int yLow,
            int xHigh,
            int yHigh,
            int xMin,
            int yMin,
            int xMax,
            int yMax)
        {
            boolean xOverlap =
                valueInRange(xLow, xMin, xMax)
                    || valueInRange(xMin, xLow, xHigh);

            boolean yOverlap =
                valueInRange(yLow, yMin, yMax)
                    || valueInRange(yMin, yLow, yHigh);

            return xOverlap && yOverlap;
        }


        @Override
        public void rFind(
            List<StringBuilder> list,
            int xLow,
            int yLow,
            int xHigh,
            int yHigh,
            int xMin,
            int yMin,
            int xMax,
            int yMax)
        {
            int xMid = (xMin + xMax) / 2;
            int yMid = (yMin + yMax) / 2;

            // if the two current rectangle don't overlap each other, go back
            if (!overlap(xLow, yLow, xHigh, yHigh, xMin, yMin, xMax, yMax))
            {
                return;
            }

            // NW
            northWest.rFind(
                list,
                xLow,
                yLow,
                xHigh,
                yHigh,
                xMin,
                yMin,
                xMid,
                yMid);

            // NE
            northEast.rFind(
                list,
                xLow,
                yLow,
                xHigh,
                yHigh,
                xMid,
                yMin,
                xMax,
                yMid);

            // SW
            southWest.rFind(
                list,
                xLow,
                yLow,
                xHigh,
                yHigh,
                xMin,
                yMid,
                xMid,
                yMax);

            // SE
            southEast.rFind(
                list,
                xLow,
                yLow,
                xHigh,
                yHigh,
                xMid,
                yMid,
                xMax,
                yMax);
        }
    }
}
