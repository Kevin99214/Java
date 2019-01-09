import javax.swing.JButton;
import java.awt.Color;
/**
 * A single entity
 * 
 * @author Kevin Qin 
 * @version 2015-04-22 #09
 */
public class Cell
{
    // instance variables
    private JButton cell;
    private int[][] coordinates;   
    private boolean currentState;
    private boolean nextState;   

    /**
     * Constructor for objects of class Cell
     */
    public Cell()
    {
        cell = new JButton();
        coordinates = new int[1][1];
        currentState = false;
        nextState = false;
    } // end of public Cell()

    /**
     * Constructor for objects of class Cell which takes the X coordinate and the Y coordinate
     */
    public Cell(int xPosition, int yPosition)
    {
        cell = new JButton();
        coordinates = new int[xPosition][yPosition];
        currentState = false;
        nextState = false;
    } // end of public Cell(int xPosition, int yPosition)

    /*
     * accessor methods
     */
    /**
     * returns the button of this cell
     * 
     * @return the button of this cell
     */
    public JButton getCellButton()
    {
        return cell;
    } // end of public JButton cellButton()

    /**
     * returns the current state of this cell
     * 
     * @return the current state of this cell. Either true or false
     */
    public boolean getCurrentState()
    {
        return currentState;
    } // end of public boolean getCurrState()

    /**
     * returns the next state of this cell
     * 
     * @return the next state of this cell
     */
    public boolean getNextState()
    {
        return nextState;
    } // end of public boolean getNextState()

    /**
     * returns the x coordinate of this cell
     * 
     * @return the x coordinate of this cell
     */
    public int getXCoordinate()
    {
        return coordinates.length;
    } // end of public int getXCoordinate()

    /**
     * returns the y coordinate of this cell
     * 
     * @return the y coordinate of this cell
     */
    public int getYCoordinate()
    {
        return coordinates[0].length;
    } // end of public int getYCoordinate()

    /**
     * Returns a string representation of this cell.
     *
     * @return a string representing this cell
     */
    public String toString()
    {
        return
        "["
        + "coordinates: (" + coordinates.length + "," + coordinates[0].length + ")"
        + ", current state: " + currentState
        + ", next State: " + nextState
        + ", JButton: " + cell
        + "]";
    } // end of public String toString()

    /*
     * mutator mehtods 
     */

    /**
     * sets the coordinate of this cell
     * 
     * @param x the new x coordinate of this cell. Must be positive
     * @param y the new y coordinate of this cell. Must be positive
     */
    public void setCoordinates(int x, int y)
    {
        if (x >= 0)
        {
            if (y >= 0)
            {
                coordinates = new int[x][y]; 
            }
        }
        else 
        {
            coordinates = new int[1][1];
        } // end of if (x >= 0)
    } //end of public void setCoordinates(int x, int y)

    /**
     * set the current state of this cell. 
     * 
     * @param newState the new current state of this cell. Must be either true or false
     */
    public void setCurrentState (boolean newState)
    {
        currentState = newState;       
    } //end of public void setCurrentState (boolean newState)

    /**
     * set the next state of this cell. 
     * 
     * @param newState the new next state of this cell. Must be either true or false
     */
    public void setNextState (boolean newState)
    {
        nextState = newState;
    } // end of public void setNextState (boolean newState)

    /**
     * sets the colour of this cell
     */
    public void setColour()
    {
        if(currentState)
        { 
            cell.setForeground(Color.BLACK);
            cell.setBackground(Color.GREEN);
        }
        else
        {
            cell.setForeground(Color.BLACK);
            cell.setBackground(Color.WHITE);
        } // end of if(currentState)
    } // end of public void setColour()
} // end of Class Cell
