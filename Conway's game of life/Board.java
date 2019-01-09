import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * A collection of squares put beside each other  
 * 
 * @author Kevin Qin
 * @version 2015-04-30 #09
 */
public class Board
{
    //class fields
    private static final Random generator = new Random();
    private static final int BUTTON_SIZE = 15;

    // instance fields
    private boolean activeChecking;
    private boolean allowChecking;
    private Cell[] board;
    private int generationCounter;
    private int length;
    private int width;
    private JButton doneButton;
    private JButton randomizeButton;
    private JFrame frame;
    private JLabel generationCount;

    /**
     * Constructs a board with specified length and width
     * 
     * @param length the number of columns this board will have
     * @param width the number of rows this board will have
     */
    public Board(int length, int width)
    {
        activeChecking = true;
        allowChecking = true;       
        board = new Cell[(length * width)];
        int counter = 0;
        this.length = length;
        generationCounter = 0;
        this.width = width;

        for(int x = 1; x < length + 1; x++)
        {
            for(int y = 1; y < width + 1; y++)
            {
                board[counter] = new Cell(x,y);
                counter++;               
            } // end of for(int y = 0; y < width; y++)
        } // end of for(int x = 0; x < length; x++)
    } // end of public Board(int length, int width)

    /*
     * accessor methods
     */
    /**
     * returns if the program allow this board to check and update
     * 
     * @return if the program allow this board to check and update
     */
    public boolean getIfAllowedChecking()
    {
        return allowChecking;
    }

    /**
     * returns if the user has finished selecting the cells on this board
     * 
     * @retrun if the user has finished selecting the cells on this board
     */
    public boolean getIfFinishedChecking()
    {
        return activeChecking;
    } // end of public boolean getIfFinishedChecking()

    /**
     * checks if the x coordinate of the cell is beside the other cell on this board
     * 
     * @param testCell the cell that is being checked on this board
     * @param adjacentCell the cell that is being checked to see if it is adjacent to the testCell
     */ 
    public boolean checkXCoordinates(int testCell, int adjacentCell)
    {
        if (board[adjacentCell].getXCoordinate() == board[testCell].getXCoordinate() || board[adjacentCell].getXCoordinate() == (board[testCell].getXCoordinate() - 1) || board[adjacentCell].getXCoordinate() == (board[testCell].getXCoordinate() + 1))
        {
            return true;
        }
        else
        {
            return false;
        } // end of if (board[adjacentCell].getXCoordinate() == board[testCell].getXCoordinate() || board[adjacentCell].getXCoordinate() == (board[testCell].getXCoordinate() - 1) || board[adjacentCell].getXCoordinate() == (board[testCell].getXCoordinate() + 1))
    } // end of public boolean checkXCoordinates(int testCell, int adjacentCell)

    /**
     * checks if the y coordinate of the cell is beside it
     * 
     * @param testCell the cell that is being checked on this board
     * @param adjacentCell the cell that is being checked to see if it is adjacent to the testCell
     */ 
    public boolean checkYCoordinates(int testCell, int adjacentCell)
    {
        if (board[adjacentCell].getYCoordinate() == board[testCell].getYCoordinate() || board[adjacentCell].getYCoordinate() == (board[testCell].getYCoordinate() - 1) || board[adjacentCell].getYCoordinate() == (board[testCell].getYCoordinate() + 1))
        {
            return true;
        }
        else
        {
            return false;
        } // end of if (board[adjacentCell].getXCoordinate() == board[testCell].getXCoordinate() || board[adjacentCell].getXCoordinate() == (board[testCell].getXCoordinate() - 1) || board[adjacentCell].getXCoordinate() == (board[testCell].getXCoordinate() + 1))
    } // end of public boolean checkYCoordinates(int testCell, int adjacentCell)  

    /**
     * Returns a string representation of this board.
     *
     * @return a string representing this board
     */
    public String toString()
    {
        return
        "["
        + "length: " + length
        + ", width: " + width
        + ", Cell Array: " + board
        + "]";
    } // end of public String toString()

    /*
     * mutator methods 
     */
    /**
     * applies the rules of the game of life on this board
     */
    public void applyRules() throws InterruptedException
    {        
        final long DELAY_TIME = 500L;

        checkSquare();  
        Thread.sleep(DELAY_TIME);
        updateCells(); 
        generationCount.setText("Generation: " + ++generationCounter);
    }// end of public void applyRules()

    /** 
     * checks if the cells that surround one cell are alive or dead
     */
    public void checkSquare()
    {
        for(int a = 0; a < board.length; a++)
        {
            int counter = 0;
            Cell testCell = board[a]; 
            for(int b = 0; b < board.length; b++)
            {
                Cell adjacentCell = board[b];
                if(checkXCoordinates(a,b))
                {
                    if(checkYCoordinates(a,b))
                    {
                        if(adjacentCell.getCurrentState())
                        {
                            counter++;
                        } // end of if(adjacentCell.getState())
                    } // end of if(checkYCoordinates(a,b))
                } // end of if(checkXCoordinates(a,b))
            } // end of for(int b = 0; b < testBoard.length; b++)

            if(testCell.getCurrentState())
            {
                if(counter - 1 < 2 || counter - 1 > 3)
                {
                    board[a].setNextState(false);
                } // end of if(counter - 1 < 2 || counter - 1 > 3)         
            }
            else 
            {
                if(counter == 3)
                {
                    board[a].setNextState(true);
                } // end of if(counter == 3)
            } // end of if(testCell.getState())
        } //end of for(int a = 0; a < testBoard.length; a++)
    } // end of public void checkSquare()

    /**
     * initializes the counter for the number of generations this board has gone through
     */
    public void initializeGenerationCounter()
    {
        generationCount = new JLabel("Generation: " + generationCounter);
        frame.add(generationCount, BorderLayout.NORTH);
    } // end of public void countGeneration()

    /**
     * inputs from a text file a specified pattern for this board
     */
    public void importFromFile()
    {
        // Location of file to read
        File file = new File("Image.txt");

        try 
        {
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) 
            {
                String line = scanner.nextLine();
                for(int x = 1; x < length + 1; x++)
                {                         
                    String lineCharacter = line.substring(x - 1, x);
                    System.out.println(line);
                    if(lineCharacter.equals("-"))
                    {
                        board[x].setNextState(false);
                    }
                    else if (lineCharacter.equals("*"))
                    {
                        board[x].setNextState(true);
                    }
                } // end of for(int x = 0; x < length; x++)
            }
            scanner.close();
            updateCells();
        } 
        catch (FileNotFoundException fileNotFoundException) 
        {
            fileNotFoundException.printStackTrace();
        } // end of catch (FileNotFoundException fileNotFoundException) 
    } // end of public void inputFromFile()

    /**
     * disables all the currents cells on this board
     */
    public void disableButtons()
    {
        randomizeButton.setEnabled(false);
        for(int x = 0; x < board.length; x++)
        {
            board[x].getCellButton().setEnabled(false);
        } // end of for(int x = 0; x < board.length; x++)       
    }

    /**
     * makes the frame of this board and creates the buttons needed for the board
     */ 
    public void makeFrame()
    {
        // creates the frame and all the necessary components
        doneButton = new JButton("Done");
        frame = new JFrame("Conway's Game of Life"); 
        Container contentPane = frame.getContentPane();
        contentPane.setLayout(new BorderLayout());
        JPanel buttonPanel = new JPanel();
        JPanel controlPanel = new JPanel();
        JPanel determinationPanel = new JPanel();
        JButton importButton = new JButton("Import predetermined pattern");
        buttonPanel.setLayout(new GridLayout(length, width));  

        // puts the button panel in the frame
        contentPane.add(buttonPanel, BorderLayout.CENTER);

        //assigns the cell buttons to the contentPane
        for(int x = 0; x < board.length; x++)
        {
            board[x].getCellButton().setPreferredSize(new Dimension(BUTTON_SIZE, BUTTON_SIZE));
            buttonPanel.add(board[x].getCellButton());
            board[x].getCellButton().addActionListener(new CellListener());
            board[x].setColour();
        } // end of for(int x = 0; x < board.length; x++)

        //adds the done button to the panel
        contentPane.add(doneButton, BorderLayout.SOUTH);
        doneButton.addActionListener(new DoneListener());

        //creates the randomize button 
        randomizeButton = new JButton("Randomize!");
        //adds the randomize button to the contentPane
        determinationPanel.setLayout(new FlowLayout());
        determinationPanel.add(randomizeButton);
        randomizeButton.addActionListener(new RandomizeListener());
        //determinationPanel.add(importButton);
        importButton.addActionListener(new ImportListener());
        contentPane.add( randomizeButton, BorderLayout.NORTH);

        // packs the frame
        frame.pack();
        frame.setVisible(true);
    } // end of public void makeFrame()

    /**
     * randomizes the starting state of the cells on this board
     */
    public void randomizeStartingState()
    {
        for(int x = 0; x < board.length; x++)
        {
            board[x].setCurrentState(generator.nextBoolean());
            board[x].setColour();
        } // end of for(int x = 0; x < board.length; x++)
    } // end of public void randomStartingState()

    /**
     * updates the state of the cells on this board
     */
    public void updateCells()
    {
        for(int x = 0; x < board.length; x++)
        {
            board[x].setCurrentState(board[x].getNextState());
            board[x].setColour();
        } // end of for(int x = 0; x < board.length; x++)
    } // end of public void updateCells() 

    class CellListener implements ActionListener
    {
        /**
         * Listens for an action and acts appropriately
         * 
         * @param event the event which will be heard
         */
        public void actionPerformed(ActionEvent event)
        {
            // Establish the source of the event which triggered this handler
            Object source = event.getSource();
            for(int x = 0; x < board.length; x++)
            {
                if(source == board[x].getCellButton())
                {
                    board[x].setCurrentState(!board[x].getCurrentState());
                    board[x].setNextState(!board[x].getNextState());
                    board[x].setColour();
                }
            }
        } // end of public void actionPerformed(ActionEvent event)
    } // end of class CellListener implements ActionListener

    class DoneListener implements ActionListener
    {
        /**
         * Listens for an action and acts appropriately
         * 
         * @param event the event which will be heard
         */
        public void actionPerformed(ActionEvent event)
        {          
            Container contentPane = frame.getContentPane();
            JButton exitButton = new JButton("Exit");
            JButton playPauseButton = new JButton("Play/Pause");
            JPanel controlPanel = new JPanel();
            controlPanel.setLayout(new FlowLayout());

            controlPanel.add(playPauseButton);
            playPauseButton.addActionListener(new PausePlayListener());
            controlPanel.add(exitButton);
            exitButton.addActionListener(new ExitListener());
            contentPane.add(controlPanel, BorderLayout.SOUTH);
            initializeGenerationCounter();
            activeChecking = false;
            frame.remove(doneButton);
            frame.remove(randomizeButton);
            frame.pack();
        } // end of public void actionPerformed(ActionEvent event)
    } // end of class DoneListener implements ActionListener

    class ExitListener implements ActionListener
    {
        /**
         * Listens for an action and acts appropriately
         * 
         * @param event the event which will be heard
         */
        public void actionPerformed(ActionEvent event)
        {     
            System.exit(0);
        } // end of public void actionPerformed(ActionEvent event)
    } // end of class ExitListener implements ActionListener
    
    class ImportListener implements ActionListener
    {
        /**
         * Listens for an action and acts appropriately
         * 
         * @param event the event which will be heard
         */
        public void actionPerformed(ActionEvent event)
        {     
            importFromFile();
        } // end of public void actionPerformed(ActionEvent event)
    } // end of class ImportListener implements ActionListener

    class PausePlayListener implements ActionListener
    {
        /**
         * Listens for an action and acts appropriately
         * 
         * @param event the event which will be heard
         */
        public void actionPerformed(ActionEvent event)
        {     
            allowChecking = !allowChecking;
        } // end of public void actionPerformed(ActionEvent event)
    } // end of class PausePlayListener implements ActionListener

    class RandomizeListener implements ActionListener
    {
        /**
         * Listens for an action and acts appropriately
         * 
         * @param event the event which will be heard
         */
        public void actionPerformed(ActionEvent event)
        {     
            randomizeStartingState();
        } // end of public void actionPerformed(ActionEvent event)
    } // end of class RandomizeListener implements ActionListener
} // end of Class Board
