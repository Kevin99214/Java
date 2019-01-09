/**
 * Creates an object of the clockDisplay class and outputs the clock to the terminal window.
 * 
 * @author Kevin Qin
 * @version 1.0 2015-02-24
 */
public class Clock
{
   //class fields
   private final long DELAY = 1000L;// the delay between each second
   private final int MAXIMUM_TICKS = 60;// the number of seconds in a minute
   private final int ORIGINAL_VALUE = 0;// the base or starting value for this clock
   
   //instance fields
   private ClockDisplay clock;
   private int ticks;
        
   /**
    * Construtor for clock class that constructs a stopwatch. 
    * Once created, this stopwatch will start counting
    * 
    * @param minuteLimit the limit of this clock's number of minutes. Must be a positive integer
    * @param hourLimit the limit of this clock's number of hours. Must be a positive integer
    */
   public Clock(int minuteLimit, int hourLimit)
   {
       clock = new ClockDisplay(minuteLimit, hourLimit, ORIGINAL_VALUE, ORIGINAL_VALUE);
       countSeconds();
   } // end of construtor Clock(int minuteLimit, int hourLimit)
   
   /**
    * Construtor for clock class that constructs a clock that shows the actual time.
    * Once created, clock will start counting
    * 
    * @param minuteLimit the limit of this clock's number of minutes. Must be a positive integer
    * @param hourLimit the limit of this clock's number of hours. Must be a positive integer
    * @param minuteValue this clock's current minute value. Must be a positive number and be less than the limit
    * @param minuteValue this clock's current hour value. Must be a positive number and be less than the limit
    */
   public Clock(int minuteLimit, int hourLimit, int minuteValue, int hourValue)
   {
       clock = new ClockDisplay(minuteLimit, hourLimit, minuteValue, hourValue);
       countSeconds();
   } // end of construtor Clock(int minuteLimit, int hourLimit, int minuteValue, int hourValue)
      
   private void countSeconds()
   {
       try
       {
            do
            {
                Thread.sleep(DELAY);
                System.out.print("\f");
                if (ticks < 10)
                {
                    System.out.print(clock.getClockDisplay() + ":0" + ticks++);
                }
                else
                {
                    System.out.print(clock.getClockDisplay() + ":" + ticks++);
                } // end of if(ticks < 10)
           
                if (ticks == MAXIMUM_TICKS)
                {
                    clock.increment();
                    ticks = 0; // reset the seconds to zero
                }
            } while(ticks < MAXIMUM_TICKS);
       } // end of try
       
       catch (InterruptedException e) 
       { 
            // Restore the interrupted status
            Thread.currentThread().interrupt();
       } // end of catch (InterruptedException e) 
   }
   
   /**
    * creates an object of the clock class
    * 
    * @param argument <ol> Element<li>the type of clock wanted. Regular clock = 1. Stopwatch = 2.</li>
    *                             <li>the limit this clock's minutes. Must be a positive integer</li>
    *                             <li>the limit this clock's hours. Must be a positive integer </li>
    *                             <li>the value of this clock's minutes. Must be a positive number and be less than the limit</li>
    *                             <li>the value of this clock's hours. Must be a positive number and be less than the limit</li>
    *                         </ol>
    */
   public static void main(int[] argument)
   {
       final int MINUTE_LIMIT = argument[0];
       final int HOUR_LIMIT = argument[1];
       final int MINUTE_VALUE = argument[2];
       final int HOUR_VALUE = argument[3];
       
       Clock clock = new Clock(MINUTE_LIMIT, HOUR_LIMIT, MINUTE_VALUE, HOUR_VALUE);
   }
} // end of class Clock