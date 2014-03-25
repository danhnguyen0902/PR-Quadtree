// -------------------------------------------------------------------------
/**
 * Extracts the location of a city out of its data
 *
 * @author Danh
 * @version Mar 5, 2014
 */
public class CoordinatesExtractor2D
    implements Extractor2D<City>
{

    @Override
    public Point2D getPoint2D(City elem)
    {
        return (new Point2D(elem.getX(), elem.getY()));
    }

}
