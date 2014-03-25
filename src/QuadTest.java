import student.TestCase;

// -------------------------------------------------------------------------
/**
 * Tests the Quad class
 *
 * @author Danh Nguyen (danh0902)
 * @version Feb 10, 2014
 */
public class QuadTest
    extends TestCase
{
    // ----------------------------------------------------------
    /**
     * First test
     */
    public void testMain1()
    {
        // 1
        Quad obj = new Quad();
        assertNotNull(obj);

        // 2

        setSystemIn("insert b 5 stuff\n" + "insert b 0 stuff\n"
            + "insert a 4 stuff\n" + "insert c 4 stuff\n"
            + "insert d 3 stuff\n" + "insert d 8 stuff\n"
            + "insert d 6 stuff\n" + "insert a 1 stuff\n"
            + "find name a\n" + "find name e\n" + "find population 4\n"
            + "find population       2\n" + "sort name\n"
            + "sort population\n" + "tree name\n" + "tree population\n"
            + "findRange population 0 8\n" + "findRange name d a\n"
            + "findKth name 7\n" + "findKth population 5\n\n"
            + "delete name a\n" + "sort population\n"
            + "delete population 4\n" + "sort name\n\n" + "makenull\n");
        Quad.main(null);
        assertTrue(systemOut().getHistory().contains(
            "insert b 5 stuff\n" + "insert b 0 stuff\n"
                + "insert a 4 stuff\n" + "insert c 4 stuff\n"
                + "insert d 3 stuff\n" + "insert d 8 stuff\n"
                + "insert d 6 stuff\n" + "insert a 1 stuff\n"
                + "find name a\n" + "a 4 stuff\n" + "a 1 stuff\n"
                + "find name e\n" + "Not found\n" + "find population 4\n"
                + "a 4 stuff\n" + "c 4 stuff\n" + "find population 2\n"
                + "Not found\n" + "sort name\n" + "a 4 stuff\n"
                + "a 1 stuff\n" + "b 5 stuff\n" + "b 0 stuff\n"
                + "c 4 stuff\n" + "d 3 stuff\n" + "d 8 stuff\n"
                + "d 6 stuff\n" + "sort population\n" + "b 0 stuff\n"
                + "a 1 stuff\n" + "d 3 stuff\n" + "a 4 stuff\n"
                + "c 4 stuff\n" + "b 5 stuff\n" + "d 6 stuff\n"
                + "d 8 stuff\n" + "tree name\n" + "    a 4 stuff\n"
                + "        a 1 stuff\n" + "b 5 stuff\n"
                + "    b 0 stuff\n" + "        c 4 stuff\n"
                + "            d 3 stuff\n" + "                d 8 stuff\n"
                + "                    d 6 stuff\n" + "tree population\n"
                + "    b 0 stuff\n" + "                a 1 stuff\n"
                + "            d 3 stuff\n" + "        a 4 stuff\n"
                + "            c 4 stuff\n" + "b 5 stuff\n"
                + "        d 6 stuff\n" + "    d 8 stuff\n"
                + "findRange population 0 8\n" + "b 0 stuff\n"
                + "a 1 stuff\n" + "d 3 stuff\n" + "a 4 stuff\n"
                + "c 4 stuff\n" + "b 5 stuff\n" + "d 6 stuff\n"
                + "d 8 stuff\n" + "findRange name d a\n" + "Not found\n"
                + "findKth name 7\n" + "d 6 stuff\n"
                + "findKth population 5\n" + "b 5 stuff\n"
                + "delete name a\n" + "sort population\n" + "b 0 stuff\n"
                + "d 3 stuff\n" + "c 4 stuff\n" + "b 5 stuff\n"
                + "d 6 stuff\n" + "d 8 stuff\n" + "delete population 4\n"
                + "sort name\n" + "b 5 stuff\n" + "b 0 stuff\n"
                + "d 3 stuff\n" + "d 8 stuff\n" + "d 6 stuff\n"
                + "makenull\n"));
    }


    // ----------------------------------------------------------
    /**
     * Second test
     */
    public void testMain2()
    {
        // 3
        setSystemIn("findKth name 100\n" + "delete name z\n"
            + "sort name\n" + "tree name\n");

        Quad.main(null);

        assertTrue(systemOut().getHistory().contains(
            "findKth name 100\n" + "Not found\n" + "delete name z\n"
                + "Not found\n" + "sort name\n" + "Database empty\n"
                + "tree name\n" + "Database empty\n"));

        // 4
        setSystemIn("insert A 1 700 100\n" + "insert B 2 900 300\n"
            + "insert C 3 700 100\n" + "insert D 4 700 200\n"
            + "insert E 5 1024 900\n" + "insert F 6 1023 -1\n"
            + "makenull\n");

        Quad.main(null);

        assertTrue(systemOut().getHistory().contains(
            "insert A 1 700 100\n" + "insert B 2 900 300\n"
                + "insert C 3 700 100\n" + "Duplicate\n"
                + "insert D 4 700 200\n" + "insert E 5 1024 900\n"
                + "Out of bounds\n" + "insert F 6 1023 -1\n"
                + "Out of bounds\n" + "makenull\n"));

        // 5
        setSystemIn("insert Home 5 0 0\n" + "insert Away 10 64 64\n"
            + "tree location\n");
        Quad.main(null);

        assertTrue(systemOut().getHistory().contains(
            "insert Home 5 0 0\n" + "insert Away 10 64 64\n"
                + "tree location\n" + "Internal (512, 512)\n"
                + "....Internal (256, 256)\n"
                + "........Internal (128, 128)\n"
                + "............Internal (64, 64)\n"
                + "................Home 5 (0, 0)\n"
                + "................Empty\n" + "................Empty\n"
                + "................Away 10 (64, 64)\n"
                + "............Empty\n" + "............Empty\n"
                + "............Empty\n" + "........Empty\n"
                + "........Empty\n" + "........Empty\n" + "....Empty\n"
                + "....Empty\n" + "....Empty\n"));
    }


    // ----------------------------------------------------------
    /**
     * Third test
     */
    public void testMain3()
    {
        // 6
        setSystemIn("tree name\n");
        Quad.main(null);
        assertTrue(systemOut().getHistory().contains(
            "tree name\n" + "Database empty\n"));

        // 7
        setSystemIn("find name a\n");
        Quad.main(null);
        assertTrue(systemOut().getHistory().contains(
            "find name a\n" + "Not found\n"));

        // 8
        setSystemIn("insert Home 5 0 0\n" + "insert Away 10 64 64\n"
            + "find location 64 64\n" + "find location 5 64\n"
            + "find location 1034 64\n" + "find location 64 1044\n"
            + "find location -2 64\n" + "find location 64 -4\n");
        Quad.main(null);
        assertTrue(systemOut().getHistory().contains(
            "insert Home 5 0 0\n" + "insert Away 10 64 64\n"
                + "find location 64 64\n" + "Away 10 (64, 64)\n"
                + "find location 5 64\n" + "Not found\n"
                + "find location 1034 64\n" + "Not found\n"
                + "find location 64 1044\n" + "Not found\n"
                + "find location -2 64\n" + "Not found\n"
                + "find location 64 -4\n" + "Not found\n"));

        // 9
        setSystemIn("insert Home 5 0 0\n" + "insert Away 10 64 64\n"
            + "delete location 64 64\n" + "tree location\n"
            + "delete location 3232 3\n" + "delete location 3 32323\n"
            + "delete location -1 3\n" + "delete location 32 -3\n"
            + "delete location 32 23\n");
        Quad.main(null);
        assertTrue(systemOut().getHistory().contains(
            "insert Home 5 0 0\n" + "insert Away 10 64 64\n"
                + "delete location 64 64\n" + "tree location\n"
                + "Home 5 (0, 0)\n" + "delete location 3232 3\n"
                + "Not found\n" + "delete location 3 32323\n"
                + "Not found\n" + "delete location -1 3\n"
                + "Not found\n" + "delete location 32 -3\n"
                + "Not found\n" + "delete location 32 23\n"
                + "Not found\n"));
    }


    // ----------------------------------------------------------
    /**
     * Fourth test
     */
    public void testMain4()
    {
        // 10
        setSystemIn("insert Home 5   0 0\n" + "insert hadf 4   900 770\n"
            + "insert ad 3     2 850\n" + "insert ad 4     550 400\n"
            + "insert aag 5    800 500\n" + "insert ad 5     500 500\n"
            + "insert ad 5     1000 1000\n" + "insert adga 1   300 900\n"
            + "insert aga 1    800 800\n" + "insert adga 32  200 500\n"
            + "insert here 32   1023  1023\n"
            + "insert test 31 1022 1022\n" + "insert test 3213 1022 1022\n"
            + "rfind 2 51 52 513\n" + "rfind 400 0 6426 2466\n"
            + "rfind 12 34 51 353\n");

        Quad.main(null);
        assertTrue(systemOut().getHistory().contains(
            "insert Home 5 0 0\n" + "insert hadf 4 900 770\n"
                + "insert ad 3 2 850\n" + "insert ad 4 550 400\n"
                + "insert aag 5 800 500\n" + "insert ad 5 500 500\n"
                + "insert ad 5 1000 1000\n" + "insert adga 1 300 900\n"
                + "insert aga 1 800 800\n" + "insert adga 32 200 500\n"
                + "insert here 32 1023 1023\n"
                + "insert test 31 1022 1022\n"
                + "insert test 3213 1022 1022\n" + "Duplicate\n"
                + "rfind 2 51 52 513\n" + "Not found\n"
                + "rfind 400 0 6426 2466\n" + "ad 5 (500, 500)\n"
                + "ad 4 (550, 400)\n" + "aag 5 (800, 500)\n"
                + "aga 1 (800, 800)\n" + "hadf 4 (900, 770)\n"
                + "ad 5 (1000, 1000)\n" + "test 31 (1022, 1022)\n"
                + "here 32 (1023, 1023)\n" + "rfind 12 34 51 353\n"
                + "Not found\n"));
    }
}
