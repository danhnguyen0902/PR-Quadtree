import java.util.List;
import student.TestCase;

// -------------------------------------------------------------------------
/**
 * Tests the DatabaseBST class
 *
 * @author Danh Nguyen (danh0902)
 * @version Feb 10, 2014
 */
public class DatabaseBSTTest
    extends TestCase
{
    private DatabaseBST<City> nameBST;
    private DatabaseBST<City> populationBST;
    private City              city1;
    private City              city2;
    private City              city3;
    private City              city4;
    private City              city5;
    private City              city6;
    private City              city7;
    private City              city8;
    private City              city9;
    private City              tmp;
    private List<City>        list;


    /**
     * Sets up a beginning for each test case.
     */
    public void setUp()
    {
        nameBST = new DatabaseBST<City>(new NameComp());
        populationBST = new DatabaseBST<City>(new PopulationComp());

        city1 = new City("a", 1, "stuff");
        city2 = new City("b", 5, "stuff");
        city3 = new City("b", 0, "stuff");
        city4 = new City("a", 4, "stuff");
        city5 = new City("c", 4, "stuff");
        city6 = new City("d", 3, "stuff");
        city7 = new City("d", 8, "stuff");
        city8 = new City("d", 6, "stuff");
        city9 = new City("a", 1, "stuff");
    }


    /**
     * Tests the insert() method
     */
    public void testInsert()
    {
        // 0 element
        assertNull(nameBST.find(city1));

        // 1 element
        nameBST.insert(city1);
        tmp = nameBST.find(city1).get(0);
        assertEquals(tmp, city1);

        // normal amount:
        populationBST.insert(city1);
        populationBST.insert(city2);
        populationBST.insert(city3);
        populationBST.insert(city4);
        populationBST.insert(city5);
        populationBST.insert(city6);
        populationBST.insert(city7);
        populationBST.insert(city8);
        populationBST.insert(city9);

        nameBST.insert(city1);
        nameBST.insert(city2);
        nameBST.insert(city3);
        nameBST.insert(city4);
        nameBST.insert(city5);
        nameBST.insert(city6);
        nameBST.insert(city7);
        nameBST.insert(city8);
        nameBST.insert(city9);
    }


    /**
     * Test the find() method
     */
    public void testFind()
    {
        // 0 element
        assertNull(nameBST.find(city1));

        // 1 element
        nameBST.insert(city1);
        tmp = nameBST.find(city1).get(0);
        assertEquals(tmp, city1);

        // normal amount:
        populationBST.insert(city1);
        populationBST.insert(city2);
        populationBST.insert(city3);
        populationBST.insert(city4);
        populationBST.insert(city5);
        populationBST.insert(city6);
        populationBST.insert(city7);
        populationBST.insert(city8);
        populationBST.insert(city9);

        nameBST.insert(city1);
        nameBST.insert(city2);
        nameBST.insert(city3);
        nameBST.insert(city4);
        nameBST.insert(city5);
        nameBST.insert(city6);
        nameBST.insert(city7);
        nameBST.insert(city8);
        nameBST.insert(city9);

        list = nameBST.find(city1);
        assertEquals(list.size(), 4);

        list = populationBST.find(city4);
        assertEquals(list.size(), 2);
    }


    // ----------------------------------------------------------
    /**
     * Tests the findKth() method
     */
    public void testFindKth()
    {
        // 0 element
        assertNull(nameBST.findKth(0));

        // 1 element
        nameBST.insert(city1);
        tmp = nameBST.findKth(0);
        assertEquals(tmp, city1);

        // normal amount:
        populationBST.insert(city1);
        populationBST.insert(city2);
        populationBST.insert(city3);
        populationBST.insert(city4);
        populationBST.insert(city5);
        populationBST.insert(city6);
        populationBST.insert(city7);
        populationBST.insert(city8);
        populationBST.insert(city9);

        nameBST.insert(city1);
        nameBST.insert(city2);
        nameBST.insert(city3);
        nameBST.insert(city4);
        nameBST.insert(city5);
        nameBST.insert(city6);
        nameBST.insert(city7);
        nameBST.insert(city8);
        nameBST.insert(city9);

        assertEquals(nameBST.findKth(2), city4);
        assertEquals(nameBST.findKth(1), city1);
        assertEquals(nameBST.findKth(0), city1);
        assertEquals(nameBST.findKth(5), city3);
        assertEquals(nameBST.findKth(4), city2);
        assertEquals(nameBST.findKth(6), city5);
        assertEquals(nameBST.findKth(3), city9);
        assertEquals(nameBST.findKth(7), city6);
        assertEquals(nameBST.findKth(8), city7);

        assertEquals(populationBST.findKth(8), city7);
        assertEquals(populationBST.findKth(6), city2);
    }


    // ----------------------------------------------------------
    /**
     * Tests the findRange() method
     */
    public void testFindRange()
    {
        // 0 element
        assertNull(nameBST.findRange(city1, city5));

        // 1 element
        nameBST.insert(city1);
        list = nameBST.findRange(city1, city1);
        assertEquals(1, list.size());

        // normal amount:
        populationBST.insert(city1);
        populationBST.insert(city2);
        populationBST.insert(city3);
        populationBST.insert(city4);
        populationBST.insert(city5);
        populationBST.insert(city6);
        populationBST.insert(city7);
        populationBST.insert(city8);
        populationBST.insert(city9);

        nameBST.insert(city1);
        nameBST.insert(city2);
        nameBST.insert(city3);
        nameBST.insert(city4);
        nameBST.insert(city5);
        nameBST.insert(city6);
        nameBST.insert(city7);
        nameBST.insert(city8);
        nameBST.insert(city9);

        list = nameBST.findRange(city1, city5);
        assertEquals(list.size(), 7);
        list = populationBST.findRange(city7, city6);
        assertNull(list);
    }


    /**
     * Tests the delete() method
     */
    public void testDelete()
    {
        // 0 element
        nameBST.delete(city1);

        // 1 element
        nameBST.insert(city1);
        nameBST.delete(city1);
        assertNull(nameBST.find(city1));
        nameBST.delete(city2);

        //
        nameBST.insert(city2);
        nameBST.insert(city1);
        nameBST.insert(city5);
        nameBST.delete(city9);
        nameBST.delete(city2);
        nameBST.delete(city1);
        nameBST.delete(city5);

        // normal amount:
        populationBST.insert(city1);
        populationBST.insert(city2);
        populationBST.insert(city3);
        populationBST.insert(city4);
        populationBST.insert(city5);
        populationBST.insert(city6);
        populationBST.insert(city7);
        populationBST.insert(city8);
        populationBST.insert(city9);

        nameBST.insert(city1);
        nameBST.insert(city2);
        nameBST.insert(city3);
        nameBST.insert(city4);
        nameBST.insert(city5);
        nameBST.insert(city6);
        nameBST.insert(city7);
        nameBST.insert(city8);
        nameBST.insert(city9);

        nameBST.delete(city4);
        populationBST.delete(city2);

        nameBST.delete(city9);
        list = nameBST.find(city9);
        assertEquals(list.size(), 1);
        populationBST.delete(city7);
        list = populationBST.find(city7);
        assertNull(list);
    }


    // ----------------------------------------------------------
    /**
     * Tests the sort() method
     */
    public void testSort()
    {
        nameBST.sort();

        nameBST.insert(new City("a", 1, "stuff"));
        nameBST.insert(new City("b", 5, "stuff"));
        nameBST.insert(new City("b", 0, "stuff"));
        nameBST.insert(new City("a", 4, "stuff"));
        nameBST.insert(new City("c", 4, "stuff"));
        nameBST.insert(new City("d", 3, "stuff"));
        nameBST.insert(new City("d", 8, "stuff"));
        nameBST.insert(new City("d", 6, "stuff"));
        nameBST.insert(new City("a", 1, "stuff"));

        nameBST.sort();
        list = nameBST.find(city1);
        assertEquals(list.size(), 3);

        populationBST.insert(new City("a", 1, "stuff"));
        populationBST.insert(new City("b", 5, "stuff"));
        populationBST.insert(new City("b", 0, "stuff"));
        populationBST.insert(new City("a", 4, "stuff"));
        populationBST.insert(new City("c", 4, "stuff"));
        populationBST.insert(new City("d", 3, "stuff"));
        populationBST.insert(new City("d", 8, "stuff"));
        populationBST.insert(new City("d", 6, "stuff"));
        populationBST.insert(new City("a", 1, "stuff"));

        populationBST.sort();
        list = populationBST.find(city4);
        assertEquals(list.size(), 2);
    }


    // ----------------------------------------------------------
    /**
     * Tests the tree() method
     */
    public void testTree()
    {
        nameBST.tree();

        nameBST.insert(new City("a", 1, "stuff"));
        nameBST.insert(new City("b", 5, "stuff"));
        nameBST.insert(new City("b", 0, "stuff"));
        nameBST.insert(new City("a", 4, "stuff"));
        nameBST.insert(new City("c", 4, "stuff"));
        nameBST.insert(new City("d", 3, "stuff"));
        nameBST.insert(new City("d", 8, "stuff"));
        nameBST.insert(new City("d", 6, "stuff"));
        nameBST.insert(new City("a", 1, "stuff"));

        nameBST.sort();
        list = nameBST.find(city1);
        assertEquals(list.size(), 3);

        populationBST.insert(new City("a", 1, "stuff"));
        populationBST.insert(new City("b", 5, "stuff"));
        populationBST.insert(new City("b", 0, "stuff"));
        populationBST.insert(new City("a", 4, "stuff"));
        populationBST.insert(new City("c", 4, "stuff"));
        populationBST.insert(new City("d", 3, "stuff"));
        populationBST.insert(new City("d", 8, "stuff"));
        populationBST.insert(new City("d", 6, "stuff"));
        populationBST.insert(new City("a", 1, "stuff"));

        populationBST.tree();
        list = populationBST.find(city1);
        assertEquals(list.size(), 2);
    }


    /**
     * Test the makeNull() method
     */
    public void testMakeNull()
    {
        nameBST.insert(new City("a", 1, "stuff"));
        nameBST.insert(new City("b", 5, "stuff"));
        nameBST.insert(new City("b", 0, "stuff"));
        nameBST.insert(new City("a", 4, "stuff"));
        nameBST.insert(new City("c", 4, "stuff"));
        nameBST.insert(new City("d", 3, "stuff"));
        nameBST.insert(new City("d", 8, "stuff"));
        nameBST.insert(new City("d", 6, "stuff"));
        nameBST.insert(new City("a", 1, "stuff"));

        nameBST.makeNull();

        populationBST.insert(new City("a", 1, "stuff"));
        populationBST.insert(new City("b", 5, "stuff"));
        populationBST.insert(new City("b", 0, "stuff"));
        populationBST.insert(new City("a", 4, "stuff"));
        populationBST.insert(new City("c", 4, "stuff"));
        populationBST.insert(new City("d", 3, "stuff"));
        populationBST.insert(new City("d", 8, "stuff"));
        populationBST.insert(new City("d", 6, "stuff"));
        populationBST.insert(new City("a", 1, "stuff"));

        populationBST.makeNull();

        assertTrue(nameBST.isEmpty());
    }


    // ----------------------------------------------------------
    /**
     * Tests other functions
     */
    public void testOthers()
    {
        City city = new City("a", 1, "stuff");
        BinaryNode<City> node = new BinaryNode<City>(city);
        node.setElement(city);

        city.setName("b");
        city.setPopulation(100);
        city.getPayload();
        city.setPayload("another");
        assertEquals(city.getName(), "b");
    }
}
