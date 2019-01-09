/**
 * An graphical implementation of the board for Conway's Game of Life. 
 * 
 * @author Kevin Qin
 * @version 2015-05-03 #09
 */
public class Game
{
    //class fields 
    private static final int MAXIMUM_X = 40;
    private static final int MAXIMUM_Y = 40;

    /**
     * generates a board object and starts the checking and updating of cells
     * 
     * @param argument not used
     */
    public static void main(String[] argument) throws InterruptedException
    {
        boolean activeProgram = true;
        Thread.sleep(5000);

        Board board = new Board(MAXIMUM_X,MAXIMUM_Y);         
        board.makeFrame();
        do
        {  

        }while(board.getIfFinishedChecking());

        board.disableButtons();       
        do
        {  
            if(board.getIfAllowedChecking())
            {
                board.applyRules();
            }// end of 
        }while(activeProgram);
    }// end of public static void main(String[] argument)
}
