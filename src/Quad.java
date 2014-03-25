import java.util.Scanner;
// On my honor:
//
// - I have not used source code obtained from another student,
// or any other unauthorized source, either modified or
// unmodified.
//
// - All source code and documentation used in my program is
// either my original work, or was derived by me from the
// source code published in the textbook for this course.
//
// - I have not discussed coding details about this project with
// anyone other than the instructor, ACM/UPE tutors or the TAs assigned
// to this course. I understand that I may discuss the concepts
// of this program with other students, and that another student
// may help me debug my program so long as neither of us writes
// anything during the discussion or modifies any computer file
// during the discussion. I have violated neither the spirit nor
// letter of this restriction.

// -------------------------------------------------------------------------
/**
 * The main program that receives commands from users
 *
 * @author Danh Nguyen (danh0902)
 * @version Feb 10, 2014
 */
public class Quad
{
    // ----------------------------------------------------------
    /**
     * Read commands from users and return results back to them. Compiler:
     * Eclipse - ADT Bundle, Operating System: Windows 7
     *
     * @version Feb 10, 2014
     * @param args
     *            program invocation and its parameters
     */
    public static void main(String[] args)
    {
        // BST Commands Processing
        BSTCommands bstCmd = new BSTCommands();

        // QuadTree Commands Processing
        QuadTreeCommands quadCmd = new QuadTreeCommands();

        // Reading inputs
        String command;
        String[] token;
        String delims = "\\s+";

        Scanner scan = new Scanner(System.in);

        while (scan.hasNextLine())
        {
            command = scan.nextLine();
            token = command.trim().split(delims);

            if (token[0].equals("insert"))
            {
                if (token.length == 4)
                {
                    bstCmd.insert(token);
                }
                else
                {
                    // Insert to both: BSTs and QuadTree
                    bstCmd.anotherInsert(token);
                    quadCmd.insert(token);
                }
            }

            if (token[0].equals("find"))
            {
                if (token[1].equals("location"))
                {
                    quadCmd.findLocation(token);
                }
                else
                {
                    bstCmd.find(token);
                }
            }

            if (token[0].equals("findKth"))
            {
                bstCmd.findKth(token);
            }

            if (token[0].equals("findRange"))
            {
                bstCmd.findRange(token);
            }

            if (token[0].equals("delete"))
            {
                if (token[1].equals("location"))
                {
                    // delete from both: BSTs and QuadTree
                    City tmp = quadCmd.deleteLocation(token);

                    bstCmd.anotherDelete(tmp);
                }
                else
                {
                    bstCmd.delete(token);
                }
            }

            if (token[0].equals("sort"))
            {
                bstCmd.sort(token);
            }

            if (token[0].equals("tree"))
            {
                if (token[1].equals("location"))
                {
                    quadCmd.treeLocation(token);
                }
                else
                {
                    bstCmd.tree(token);
                }
            }

            if (token[0].equals("makenull"))
            {
                // Make null both: BSTs and QuadTree
                bstCmd.makeNull(token);
                quadCmd.makeNull();
            }

            if (token[0].equals("rfind"))
            {
                quadCmd.rFind(token);
            }
        }

        scan.close();
    }
}
