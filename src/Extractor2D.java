// -------------------------------------------------------------------------
/**
 * Extract a particular part of data
 *
 * @param <T>
 *            The type of data element contained in the node.
 * @author Danh
 * @version Mar 5, 2014
 */
public interface Extractor2D<T>
{
    // ----------------------------------------------------------
    /**
     * Return the location of a given object
     *
     * @param element
     *            the object whose location needs to be extracted
     * @return the location of the object
     */
    public Point2D getPoint2D(T element);
}
