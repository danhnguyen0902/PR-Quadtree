import java.util.ArrayList;
import java.util.List;
import java.util.Comparator;

// -------------------------------------------------------------------------
/**
 * The Binary Search Tree that references to the database
 *
 * @param <T>
 *            The type of data element contained in the node.
 * @author Danh Nguyen (danh0902)
 * @version Feb 10, 2014
 */
public class DatabaseBST<T>
{
    private BinaryNode<T> root;      // root of BST
    private Comparator<T> comparator; // comparator for direction decisions
    private boolean       found;     // a helper variable for find and delete
                                    // methods


    // ~ Constructor ...........................................................

    // ----------------------------------------------------------
    /**
     * Create a new DatabaseBST object.
     *
     * @param comparator
     *            an object for later on comparisons
     */
    public DatabaseBST(Comparator<T> comparator)
    {
        root = null;
        this.comparator = comparator;
    }


    // ~ Public methods ........................................................

    // ----------------------------------------------------------
    /**
     * Test if the tree is logically empty.
     *
     * @return true if empty, false otherwise.
     */
    public boolean isEmpty()
    {
        return (root == null);
    }


    // ----------------------------------------------------------
    /**
     * Insert an item into the tree.
     *
     * @param x
     *            the item to insert.
     */
    public void insert(T x)
    {
        root = insert(x, root);
    }


    // ----------------------------------------------------------
    /**
     * Find items in the tree.
     *
     * @param x
     *            the item to search for.
     * @return null if nothing was found, otherwise list of found items
     */
    public List<T> find(T x)
    {
        List<T> list = new ArrayList<T>();

        find(x, root, list);

        if (list.isEmpty())
        {
            return null;
        }

        return list;
    }


    // ----------------------------------------------------------
    /**
     * Find the k-th item in the sorted tree
     *
     * @param k
     *            the position of the desired item
     * @return null if nothing was found, otherwise the desired item
     */
    public T findKth(int k)
    {
        return findKth(k, root);
    }


    // ----------------------------------------------------------
    /**
     * Find all the items whose data are within the given range
     *
     * @param min
     *            the minimum data
     * @param max
     *            the maximum data
     * @return null if nothing was found, otherwise list of found items
     */
    public List<T> findRange(T min, T max)
    {
        List<T> list = new ArrayList<T>();

        findRange(min, max, root, list);

        if (list.isEmpty())
        {
            return null;
        }

        return list;
    }


    // ----------------------------------------------------------
    /**
     * Remove the specified value from the tree.
     *
     * @param x
     *            the item to delete.
     */
    public void delete(T x)
    {
        if (this.isEmpty())
        {
            return;
        }

        found = false;

        root = delete(x, root);
    }


    // ----------------------------------------------------------
    /**
     * Print out the records in ascending order
     *
     * @return null if the database is empty, otherwise list of items in
     *         ascending order
     */
    public List<T> sort()
    {
        if (this.isEmpty()) // if the database is empty
        {
            return null;
        }

        List<T> list = new ArrayList<T>();

        sort(root, list);

        return list;
    }


    // ----------------------------------------------------------
    /**
     * Print out the records in ascending order with additional spaces
     *
     * @return null if the database is empty, otherwise list of number of spaces
     *         for every item
     */
    public List<StringBuilder> tree()
    {
        if (this.isEmpty())
        {
            return null;
        }

        List<StringBuilder> list = new ArrayList<StringBuilder>();

        tree(root, 0, list);

        return list;
    }


    // ----------------------------------------------------------
    /**
     * Make the tree logically empty.
     */
    public void makeNull()
    {
        root = null;
    }


    // ----------------------------------------------------------
    /**
     * Internal method to insert a value into a subtree.
     *
     * @param x
     *            the item to insert.
     * @param node
     *            the node that roots the subtree.
     * @return the new root of the subtree.
     */
    private BinaryNode<T> insert(T x, BinaryNode<T> node)
    {
        if (node == null)
        {
            return new BinaryNode<T>(x);
        }
        else if (this.comparator.compare(x, node.getElement()) < 0)
        {
            node.setLeft(insert(x, node.getLeft()));

            node.incLeftSize();
        }
        else
        {
            node.setRight(insert(x, node.getRight()));
        }

        return node;
    }


    // ----------------------------------------------------------
    /**
     * Internal method to find an item in a subtree.
     *
     * @param x
     *            is item to search for.
     * @param node
     *            the node that roots the tree.
     */
    private void find(T x, BinaryNode<T> node, List<T> list)
    {
        if (node == null)
        {
            return; // not found or the tree is empty
        }
        else if (this.comparator.compare(x, node.getElement()) < 0)
        {
            // Search in the left subtree
            find(x, node.getLeft(), list);
        }
        else
        {
            // Search in the right subtree
            if (this.comparator.compare(x, node.getElement()) == 0)
            {
                list.add(node.getElement());
            }

            find(x, node.getRight(), list);
        }
    }


    // ----------------------------------------------------------
    /**
     * Internal method to find the k-th item in the sorted tree
     *
     * @param k
     *            the position of the desired item
     * @param node
     *            the current node
     */
    private T findKth(int k, BinaryNode<T> node)
    {
        if (node == null)
        {
            return null; // not found or the tree is empty
        }

        T result = null;

        int leftSize = node.getLeftSize();

        if (leftSize == k)
        {
            result = node.getElement();
        }
        else if (leftSize < k)
        {
            result = findKth(k - leftSize - 1, node.getRight());
        }
        else
        {
            result = findKth(k, node.getLeft());
        }

        return result;
    }


    // ----------------------------------------------------------
    /**
     * Internal method to find all the items whose data are within the given
     * range
     *
     * @param min
     *            the minimum data
     * @param max
     *            the maximum data
     * @param node
     *            the current node
     */
    private void findRange(T min, T max, BinaryNode<T> node, List<T> list)
    {
        if (node == null)
        {
            return; // not found or the tree is empty
        }

        if (this.comparator.compare(min, node.getElement()) <= 0)
        {
            findRange(min, max, node.getLeft(), list);
        }

        if (this.comparator.compare(min, node.getElement()) <= 0
            && this.comparator.compare(max, node.getElement()) >= 0)
        {
            list.add(node.getElement());
        }

        if (this.comparator.compare(max, node.getElement()) >= 0)
        {
            findRange(min, max, node.getRight(), list);
        }
    }


    // ----------------------------------------------------------
    /**
     * Internal method to delete a specified item from a subtree.
     *
     * @param x
     *            the item to delete.
     * @param node
     *            the node that roots the subtree.
     * @return the new root of the subtree.
     */
    private BinaryNode<T> delete(T x, BinaryNode<T> node)
    {
        if (node == null)
        {
            return null; // not found
        }

        // if value should be to the left of the root
        if (this.comparator.compare(x, node.getElement()) < 0)
        {
            node.setLeft(delete(x, node.getLeft()));

            if (found)
            {
                node.decLeftSize();
            }
        }
        // if value should be to the right of the root, we need to make sure the
        // address is the same too
        else if (this.comparator.compare(x, node.getElement()) > 0
            || x != node.getElement())
        {
            node.setRight(delete(x, node.getRight()));
        }
        // If value is on the current node and its address matches up too
        else
        {
            found = true;

            // If there are two children
            if (node.getLeft() != null && node.getRight() != null)
            {
                BinaryNode<T> nodeSucc = node.getRight();

                while (nodeSucc.getLeft() != null)
                {
                    nodeSucc = nodeSucc.getLeft();
                }

                node.setElement(nodeSucc.getElement());

                node.setRight(delete(nodeSucc.getElement(), node.getRight()));
            }
            // If there is only one child on the left
            else if (node.getLeft() != null)
            {
                return node.getLeft();
            }
            // If there is only one child on the right (this one also handles
            // the case that there's no child)
            else
            {
                return node.getRight();
            }
        }

        return node;
    }


    // ----------------------------------------------------------
    /**
     * Internal method to get the records in ascending order
     */
    private void sort(BinaryNode<T> node, List<T> list)
    {
        if (node.getLeft() != null)
        {
            sort(node.getLeft(), list);
        }

        list.add(node.getElement());

        if (node.getRight() != null)
        {
            sort(node.getRight(), list);
        }
    }


    // ----------------------------------------------------------
    /**
     * Internal method to get the records in ascending order with additional
     * spaces
     */
    private void tree(BinaryNode<T> node, int depth, List<StringBuilder> list)
    {
        if (node.getLeft() != null)
        {
            tree(node.getLeft(), depth + 1, list);
        }

        StringBuilder string = new StringBuilder("");
        for (int i = 0; i < 4 * depth; ++i)
        {
            string.append(" ");
        }
        list.add(string);

        if (node.getRight() != null)
        {
            tree(node.getRight(), depth + 1, list);
        }
    }
}
