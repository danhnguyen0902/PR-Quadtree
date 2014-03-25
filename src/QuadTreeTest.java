import java.util.ArrayList;
import java.util.List;
import student.TestCase;

// -------------------------------------------------------------------------
/**
 * Tests the QuadTree class
 *
 * @author Danh
 * @version Mar 6, 2014
 */
public class QuadTreeTest
    extends TestCase
{
    private QuadTree<City> quadTree;
    private City           city1;
    private City           city2;
    private City           city3;
    private City           city4;
    private City           city5;
    private City           city6;
    private City           city7;
    private City           city8;
    private City           city9;
    private City           city10;
    private City           city11;


    // ---------------------------------------------------------
    /**
     * Sets up a beginning for each test case.
     */
    public void setUp()
    {
        quadTree = new QuadTree<City>(new CoordinatesExtractor2D());

        city1 = new City("a", 1, 700, 100);
        city2 = new City("b", 5, 500, 500);
        city3 = new City("b", 0, 500, 600);
        city4 = new City("a", 4, 140, 104);
        city5 = new City("c", 4, 900, 900);
        city6 = new City("d", 8, 751, 765);
        city7 = new City("d", 8, 751, 765);
        city8 = new City("a", 1, 265, 653);
        city9 = new City("a", 1, 265, 653);
        city10 = new City("e", 3, -1, 653);
        city11 = new City("f", 1, 265, 2000);
    }


    // ---------------------------------------------------------
    /**
     * Tests the insert() method
     */
    public void testInsert()
    {
        quadTree.insert(city1);
        quadTree.insert(city2);
        quadTree.insert(city3);
        quadTree.insert(city4);
        quadTree.insert(city5);
        quadTree.insert(city6);
        quadTree.insert(city7);
        quadTree.insert(city8);
        quadTree.insert(city9);
        quadTree.insert(city10);
        quadTree.insert(city11);

        assertEquals(city1, quadTree.findLocation(700, 100));
    }


    // ---------------------------------------------------------
    /**
     * Tests the makeNull() method
     */
    public void testMakeNull()
    {
        quadTree.insert(city1);
        quadTree.insert(city2);
        quadTree.insert(city3);

        quadTree.makeNull();

        assertEquals(null, quadTree.findLocation(700, 100));
    }


    // ----------------------------------------------------------
    /**
     * Tests the tree() method
     */
    public void testTree()
    {
        // 1
        List<StringBuilder> list;

        list = quadTree.treeLocation();

        assertEquals("Empty", list.get(0).toString());

        // 2
        quadTree.insert(city1);
        quadTree.insert(city2);
        quadTree.insert(city3);
        quadTree.insert(city4);
        quadTree.insert(city5);
        quadTree.insert(city6);
        quadTree.insert(city7);
        quadTree.insert(city8);
        quadTree.insert(city9);
        quadTree.insert(city10);
        quadTree.insert(city11);

        list = quadTree.treeLocation();
        for (int i = 0; i < list.size(); ++i)
        {
            System.out.println(list.get(i).toString());
        }
    }


    // ---------------------------------------------------------
    /**
     * Tests the find() method
     */
    public void testFind()
    {
        quadTree.insert(city4);
        quadTree.findLocation(200, 200);
        quadTree.findLocation(140, 104);
        quadTree.findLocation(140, 200);
        quadTree.findLocation(200, 104);

        quadTree.insert(city7);
        quadTree.findLocation(200, 200);
        quadTree.findLocation(751, 765);

        quadTree.insert(city8);
        quadTree.findLocation(265, 653);
        quadTree.findLocation(265, 3);
        quadTree.findLocation(2, 653);
        quadTree.findLocation(2, 6);
        quadTree.insert(city5);
        quadTree.findLocation(900, 900);
        quadTree.findLocation(900, 800);
        quadTree.findLocation(9, 900);
        quadTree.findLocation(90, 9);

        quadTree.insert(city6);
        quadTree.findLocation(751, 765);
        quadTree.findLocation(751, 465);
        quadTree.findLocation(451, 765);
        quadTree.findLocation(451, 465);

        quadTree.insert(city4);
        quadTree.findLocation(51, 104);
        quadTree.findLocation(519, 104);
        quadTree.findLocation(140, 234);
        quadTree.findLocation(140, 104);
        quadTree.insert(city3);
        quadTree.findLocation(500, 600);
        quadTree.findLocation(500, 660);
        quadTree.findLocation(320, 600);
        quadTree.findLocation(52, 6320);

        quadTree.insert(city2);
        quadTree.findLocation(500, 500);
        quadTree.findLocation(500, 660);
        quadTree.findLocation(320, 600);
        quadTree.findLocation(52, 6320);

        quadTree.insert(city1);
        assertEquals(city1, quadTree.findLocation(700, 100));
        quadTree.insert(city2);
        quadTree.insert(city3);
        quadTree.insert(city5);
        assertEquals(city5, quadTree.findLocation(900, 900));
        quadTree.insert(city8);

        assertEquals(city1, quadTree.findLocation(700, 100));
        assertEquals(city5, quadTree.findLocation(900, 900));
        assertEquals(city8, quadTree.findLocation(265, 653));
        assertEquals(null, quadTree.findLocation(-1, 100));
    }


    // ---------------------------------------------------------
    /**
     * Tests the delete() method
     */
    public void testDelete()
    {
        quadTree.deleteLocation(1, 1);

        quadTree.insert(city1);
        quadTree.deleteLocation(1, 1);
        quadTree.deleteLocation(700, 100);
        quadTree.insert(city1);
        quadTree.deleteLocation(700, 1);
        quadTree.deleteLocation(1, 100);


        quadTree.insert(city4);
        quadTree.insert(city8);
        quadTree.deleteLocation(140, 104);
        quadTree.insert(city7);
        quadTree.deleteLocation(751, 765);

        quadTree.insert(city2);
        quadTree.insert(city3);
        quadTree.insert(city1);
        quadTree.insert(city2);
        quadTree.insert(city3);
        quadTree.insert(city4);
        quadTree.insert(city5);
        quadTree.insert(city6);
        quadTree.insert(city7);
        quadTree.insert(city8);
        quadTree.insert(city9);
        quadTree.insert(city10);
        quadTree.insert(city11);

        assertEquals(null, quadTree.deleteLocation(700, -1));
        assertEquals(city2, quadTree.deleteLocation(500, 500));
        assertEquals(city6, quadTree.deleteLocation(751, 765));
        assertEquals(city8, quadTree.deleteLocation(265, 653));
        assertEquals(city3, quadTree.deleteLocation(500, 600));
        assertEquals(city4, quadTree.deleteLocation(140, 104));
    }


    // ---------------------------------------------------------
    /**
     * Tests the rFind() method
     */
    public void testRFind()
    {
        quadTree.insert(city2);
        quadTree.insert(city3);
        quadTree.insert(city1);
        quadTree.insert(city2);
        quadTree.insert(city3);
        quadTree.insert(city4);
        quadTree.insert(city5);
        quadTree.insert(city6);
        quadTree.insert(city7);
        quadTree.insert(city8);
        quadTree.insert(city9);
        quadTree.insert(city10);
        quadTree.insert(city11);
        quadTree.insert(city1);

        List<StringBuilder> list = new ArrayList<StringBuilder>();

        assertEquals(list, quadTree.rFind(100, 200, 400, 500));
        assertEquals(list, quadTree.rFind(1034, 2000, 400, 500));
        assertEquals(list, quadTree.rFind(1004, -2000, 400, 500));
        assertEquals(list, quadTree.rFind(-2000, 1004, 400, 500));
        assertEquals(list, quadTree.rFind(-2000, 3004, 400, 500));
    }
}
