/*
 * Bot.java
 *
 * Created on December 12, 2009, 11:18 PM
 *
 */

package fvb;

import java.awt.AWTException;
import java.awt.Point;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.geom.AffineTransform;

/**
 * Set all the parameters and take this Class for a ride
 *
 *Takes coordinates and angle of rotation, screen size and grid size... to be the bot.
 *Also takes the delay
 * @author Bageshwar
 * @version 1.0
 */
public class Bot {
    
    
    private AffineTransform at;
    public static boolean run = true;
    int clickDelay;
    /** Initialize Bot
     * @param delay Delay in Seconds
     * @param angle Angle of Rotation
     * startx, starty the starting points of the bot to work
     * gridx and gridy are the grid size
     * grid constant is the side length of the grid.
     *
     *
     */
    public Bot(final String _delay,final String  _angle,final String  _startX,final String _startY,
            final String _gridX,final String  _gridY,final String _gridConstant,final String _clickDelay)throws Exception {
        new Thread(new Runnable(){
            public void run() {
                System.out.println("Thread has started");
                int delay = Integer.parseInt(_delay);
                int startX = Integer.parseInt(_startX);
                int startY= Integer.parseInt(_startY);
                int gridX= Integer.parseInt(_gridX);
                int gridY= Integer.parseInt(_gridY);
                int gridConstant = Integer.parseInt(_gridConstant);
                float angle = Float.parseFloat(_angle);
                clickDelay = Integer.parseInt(_clickDelay);
                at = AffineTransform.getRotateInstance(Math.PI*angle/180);
                //System.out.println("angle=="+Math.PI*angle/180);
                //System.out.println(at.transform(b,a));
                Point a=null;
                try {
                    Thread.sleep(delay*1000);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
                Robot rb = null;
                try {
                    rb = new Robot();
                } catch (AWTException ex) {
                    ex.printStackTrace();
                }
                rb.setAutoDelay(clickDelay);
                
                
                
                for (int i = 0; i < gridX ; i++) {
                    for (int j = 0; j < gridY; j++) {
                        System.out.print(run+"\t");
                        if(!run)
                            return ;
                        a = new Point(
                                (i*gridConstant) ,
                                ((i+j)*gridConstant) );
                        //change log... the point was relocated and then transformed.. now
                        //i m first transforming it and then relocating it.
                        
                        //System.out.print(a);
                        //if(i!=0 && j !=0)
                        at.transform(a,a);
                        
                        //System.out.println("-->"+a+"-->Point[x="+(a.y+startY)+",y="+(a.x+startX)+"]");
                        rb.mouseMove(a.y+startY,a.x+startX);
                        rb.mousePress(InputEvent.BUTTON1_MASK);
                        rb.mouseRelease(InputEvent.BUTTON1_MASK);
                        try {
                            Thread.sleep(clickDelay);
                        } catch (InterruptedException ex) {
                            ex.printStackTrace();
                        }
                    }
                }
                
                
                System.out.println("The thread has ended");
            }//run
        }).start();
        
        
    }
    
    public static void main(String a[]){
        // String sangle = (Math.atan(0.475941422594)*180/Math.PI)+"";
        
        // System.out.println(sangle);
        for (int i = 0; i < BotAction.values().length ;i++) {
            System.out.println(BotAction.values()[i].getLoopSize());
        }
    }
    
    
    /**
     * Deleting the farm
     * */
    public Bot(final String _delay,final String  _angle,final String  _startX,final String _startY,
            final String _gridX,final String  _gridY,final String _gridConstant,final String _clickDelay,
            final Point deleteTool,final Point deleteAccept
            )throws Exception {
        new Thread(new Runnable(){
            public void run() {
                int delay = Integer.parseInt(_delay);
                int startX = Integer.parseInt(_startX);
                int startY= Integer.parseInt(_startY);
                int gridX= Integer.parseInt(_gridX);
                int gridY= Integer.parseInt(_gridY);
                int gridConstant = Integer.parseInt(_gridConstant);
                float angle = Float.parseFloat(_angle);
                int clickDelay = Integer.parseInt(_clickDelay);
                at = AffineTransform.getRotateInstance(Math.PI*angle/180);
                //System.out.println("angle=="+Math.PI*angle/180);
                //System.out.println(at.transform(b,a));
                Point a = null;
                try {
                    Thread.sleep(delay*1000);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
                Robot rb = null;
                try {
                    rb = new Robot();
                } catch (AWTException ex) {
                    ex.printStackTrace();
                }
                rb.setAutoDelay(clickDelay);
                try {
                    
        /*
         *If the field is left plowed already,
         *one iteration will plant the mentioned plant, and the second
         *iteration will delete them, need to rebuild the fields for which
         *the bot is not accurate enough
         */
                    //select the crop from the market
                    
                    
                    //select the delete tool
                    click(rb,deleteTool);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                try {
                    Thread.sleep(clickDelay);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
                
                //run=true;
                
                for (int i = 0; i < gridX ; i++) {
                    for (int j = 0; j < gridY ; j++) {
                        
                        if(!run)
                            return ;
                /*
                 *I need to travel to the delete tool,
                 *select the delete tool, click on the plot,
                 *click on accept,
                 *and loop.
                 */
                        
                        
                        //delete the crop
                        a = new Point(
                                (i*gridConstant) ,
                                ((i+j)*gridConstant) );
                        at.transform(a,a);
                        rb.mouseMove(a.y+startY,a.x+startX);
                        rb.mousePress(InputEvent.BUTTON1_MASK);
                        rb.mouseRelease(InputEvent.BUTTON1_MASK);
                        try {
                            Thread.sleep(clickDelay*20);
                        } catch (InterruptedException ex) {
                            ex.printStackTrace();
                        }
                        try {
                            
                            //accept the delete confirmation dialog
                            click(rb,deleteAccept);
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                        try {
                            Thread.sleep(clickDelay);
                        } catch (InterruptedException ex) {
                            ex.printStackTrace();
                        }
                    }
                }
                
                System.out.println("The thread has ended");
            }//run
        }).start();
        
    }
    
    
    
    
    /*
     * This is soyabean hack
     */
    public Bot(final String _delay,final String  _angle,final String  _startX,final String _startY,
            final String _gridX,final String  _gridY,final String _gridConstant,final String _clickDelay,final String _beans,
            final Point deleteTool,final Point deleteAccept,final Point cropSelect,
            final Point plowSelect,final Point plowPointSelect,final Point marketSelect
            )throws Exception {
        new Thread(new Runnable(){
            public void run() {
            
            int delay = Integer.parseInt(_delay);
            int startX = Integer.parseInt(_startX);
            int startY= Integer.parseInt(_startY);
            int gridX= Integer.parseInt(_gridX);
            int gridY= Integer.parseInt(_gridY);
            int gridConstant = Integer.parseInt(_gridConstant);
            float angle = Float.parseFloat(_angle);
            int clickDelay = Integer.parseInt(_clickDelay);
            int beans = Integer.parseInt(_beans);
            
            Point a = null;
                try {
                    Thread.sleep(delay*1000);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
                Robot rb = null;
                try {
                    rb = new Robot();
                } catch (AWTException ex) {
                    ex.printStackTrace();
                }
            rb.setAutoDelay(clickDelay);
            int CONSTANT_DELAY = 5; // no of times more than click delay, to wait while selecting tools.
            a = new Point(startX ,startY );
        /*
         *The algo is like this
         *1. You give me a barren land
         *2. I select plow tool
         *3. I click startx-starty
         *4. I click on market
         *5. I click on the crop
         *6. I click startx-starty
         *7. I click on the deletetool
         *8. I click startx-starty
         *9. I click deleteaccept
         * and i repeat this helova times
         */
            run=true;
            for (int i = 0; i < beans; i++) {
                if(!run)
                    return ;
                //select plow tool
                try{
                click(rb,plowSelect);
                //sleep for some time
                Thread.sleep(clickDelay*CONSTANT_DELAY);
                
                
                //click on the field, now the farm is ready
                click(rb,plowPointSelect);
                //sleep for some more time
                Thread.sleep(clickDelay*CONSTANT_DELAY);
                //select market
                click(rb,marketSelect);
                //sleep for some time
                Thread.sleep(clickDelay*50);
                //now select the crop
                click(rb,cropSelect);
                //sleep some more.
                Thread.sleep(clickDelay*CONSTANT_DELAY);
                //click on the field, now the crop will be sowed
                click(rb,a);
                //sleep some more.
                Thread.sleep(clickDelay*CONSTANT_DELAY);
                
                //now the seeds are sowed, select the delete tool
                click(rb,deleteTool);
                //sleep some more.
                Thread.sleep(clickDelay*CONSTANT_DELAY);
                //click xy
                click(rb,a);
                //sleep some more.
                Thread.sleep(clickDelay*50);
                //click deleteAccept
                click(rb,deleteAccept);
                Thread.sleep(clickDelay*CONSTANT_DELAY);
                //now the crop is deleted.
                }catch(Exception e){e.printStackTrace();}
            }
            System.out.println("The thread has ended");
        }//run
    }).start();
    
}


private void click(Robot rb,Point a)throws Exception{
    rb.mouseMove(a.y,a.x);
    rb.mousePress(InputEvent.BUTTON1_MASK);
    rb.mouseRelease(InputEvent.BUTTON1_MASK);
    Thread.sleep(clickDelay);
}


private void timer(final int delay){
    try {
        final Runnable clickTimer= new Runnable() {
            
            public void run() {
                while(run){
                    try {
                        Thread.sleep(delay);
                        //System.out.println("Timer ran...");
                        //MouseInfo.getPointerInfo().getDevice().
                        //Keyb
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    
                }
            }
        };
        //currently this feature is disabled.
        //new Thread(clickTimer).start();
        
    } catch (Exception e) {
        e.printStackTrace();
    }
    
    
    
}
}

enum BotAction {
    SOYABEAN_HACK(161),
    SOW(4),
    PLOW(4),
    HARVEST(4),
    TRAVERSE(4),
    DELETE_FIELD(28);
    
    BotAction(int loop){
        this.loopSize = loop;
    }
    int loopSize;
    public int getLoopSize(){
        return loopSize;
    }
}

