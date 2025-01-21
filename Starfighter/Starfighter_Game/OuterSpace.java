
//Name -

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Canvas;
import java.awt.event.ActionEvent;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import static java.lang.Character.*;
import java.awt.image.BufferedImage;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.TimerTask;
import java.util.Timer;
import java.awt.Font;

import javax.swing.JFrame;

public class OuterSpace extends Canvas implements KeyListener, Runnable
{
	private Ship ship;
	private Alien alienOne;
	private Alien alienTwo;
	private int topBound = 75;
	private int bottomBound = 710;
	private int rightBound = 1480;
	private int leftBound = 10;	
	private boolean cooldown = true;
	private boolean rapidfire = true;
	private boolean rapidfireGame = false;
	private boolean[] allow = {true, false, false, false, false, false, false, false, false, false};
	private boolean[] level = {false, false, false, false, false, false, false, false, false, false};
	
	

	public void timedShot(int m)
	{
		Timer timer1 = new Timer();
	
		TimerTask task1 = new TimerTask()
		{
			public void run()
			{
				cooldown = true;
			}
		};

		timer1.schedule(task1, m);
	}


	public void timed2()
	{
		Timer timer3 = new Timer();

		TimerTask task3 = new TimerTask() 
		{
			public void run()
			{
				rapidfireGame = false;
			}
		};
		timer3.schedule(task3, 2000);
	}

	
    private AlienHorde horde1;
	private AlienHorde horde2;
	private AlienHorde horde3;
	private AlienHorde horde4;
	private AlienHorde horde5;
	private AlienHorde horde6;
	private AlienHorde horde7;
	private AlienHorde horde8;
	private AlienHorde horde9;
	private AlienHorde horde10;

	private Bullets shots;
	

	private boolean[] keys;
	private BufferedImage back;

	public OuterSpace(JFrame par)
	{
		setBackground(Color.black);
		
		keys = new boolean[7];

		
		
		ship = new Ship(rightBound/2, 400, 2);
		shots = new Bullets();
		
		horde1 = new AlienHorde(15, rightBound, bottomBound/3, 1);
		horde2 = new AlienHorde(20, rightBound, bottomBound/3, 1);
		horde3 = new AlienHorde(25, rightBound, bottomBound/3, 1);
		horde4 = new AlienHorde(30, rightBound, bottomBound/3, 1);
		horde5 = new AlienHorde(35, rightBound, bottomBound/3, 1);
		horde6 = new AlienHorde(40, rightBound, bottomBound/3, 1);
		horde7 = new AlienHorde(45, rightBound, bottomBound/3, 1);
		horde8 = new AlienHorde(50, rightBound, bottomBound/3, 1);
		horde9 = new AlienHorde(30, rightBound, bottomBound/3, 2);
		horde10 = new AlienHorde(40, rightBound, bottomBound/3, 2);
		
		this.addKeyListener(this);
		new Thread(this).start();

		setVisible(true);
	}

   public void update(Graphics window)
   {
	   paint(window);
   }

   public void levelPlay(AlienHorde horde, boolean[] a, boolean[] l, int s, int i, Graphics g)
   {
	if (!l[i])
	{
		if (a[i])
		{
			g.setColor(Color.WHITE);
			g.setFont(new Font("", Font.PLAIN, 100));
			g.drawString("Press ENTER to Start Level " + (i+1), 125, 400 );

			if (keys[5] == true)
			{
				l[i] = true;
				a[i] = false;
			}
		}
	}

	if (l[i])
	{
		g.setColor(Color.BLUE);
		g.setFont(new Font("", Font.PLAIN, 40));
		g.drawString("Score: " + horde.getScore() + "/" + s, 25, 50 );

		g.setColor(Color.YELLOW);
		g.setFont(new Font("", Font.PLAIN, 40));
		g.drawString("Lives: " + horde.getLives(), 725, 50 );

		if (rapidfire)
		{
		g.setColor(Color.GREEN);
		g.setFont(new Font("", Font.PLAIN, 40));
		g.drawString("Rapid-Fire: Available" , 1150, 50 );
		}
		else
		{
			g.setColor(Color.RED);
			g.setFont(new Font("", Font.PLAIN, 40));
			g.drawString("Rapid-Fire: Not Available" , 1090, 50 );
		}

		
		horde.drawEmAll(g);
		horde.moveEmAll(topBound, bottomBound, leftBound, rightBound-20);
		horde.removeDeadOnes(shots.getList());
		horde.checkCollision(ship);
		if (rapidfire)
		{
			if (keys[6] == true)
			{
				rapidfire = false;
				rapidfireGame = true;
				timed2();
			}
		}

		if (horde.getLives() <= 0)
		{
			horde.removeAll();
			g.setColor(Color.RED);
			g.setFont(new Font("", Font.PLAIN, 100));
			g.drawString("GAME OVER", 500, 400 );
			g.setColor(Color.BLACK);
			g.fillRect(0,0,1540,150);
			
			
		}
		else
		{
			if (horde.getSize() == 0 && horde.getScore() >= s && l[i])
			{	
				l[i] = false;
				rapidfire = true;
				a[i+1] = true;
				g.setColor(Color.BLACK);
				g.fillRect(0,0,1540,700);
				if (i == 9)
				{
					g.setColor(Color.GREEN);
					g.setFont(new Font("", Font.PLAIN, 100));
					g.drawString("YOU WIN", 500, 400 );
				}
			}
			else if (horde.getSize() == 0 && horde.getScore() < s)
			{
				g.setColor(Color.RED);
				g.setFont(new Font("", Font.PLAIN, 100));
				g.drawString("GAME OVER", 500, 400 );
			}
		}
	}
   }


   public void paint( Graphics window )
	{
		//sets up the double buffering to make the game animation nice and smooth
		Graphics2D twoDGraph = (Graphics2D)window;

		//take a snap shop of the current screen and same it as an image
		//that is the exact same width and height as the current screen
		if(back==null)
		   back = (BufferedImage)(createImage(getWidth(),getHeight()));

		//create a graphics reference to the back ground image
		//we will draw all changes on the background image
		Graphics graphToBack = back.createGraphics();

		graphToBack.setColor(Color.BLACK);
		graphToBack.fillRect(0,0,rightBound+50,bottomBound+75);
		
		if(keys[0] == true)
		{
			if (ship.getX() > leftBound)
				ship.move("LEFT");
		}

//Code to move the ship & Fire bullets on a key press
		if(keys[1] == true)
		{
			if (ship.getX() < rightBound)
				ship.move("RIGHT");
		}

		if(keys[2] == true)
		{
			if (ship.getY() > topBound)
				ship.move("UP");
		}

		if(keys[3] == true)
		{
			if (ship.getY() < bottomBound)
				ship.move("DOWN");
		}

		if(keys[4] == true)
		{
			if (cooldown) 
			{
				shots.add(new Ammo(ship.getX() + ship.getWidth()/2, ship.getY(), 3));
				cooldown = false;
				
				if (rapidfireGame)
				{
					timedShot(75);
				}
				else
				{
					timedShot(200);
				}
			}
			
		}


	levelPlay(horde1,  allow,  level,  55,   0,  graphToBack);
	levelPlay(horde2,  allow,  level,  70,   1,  graphToBack);
	levelPlay(horde3,  allow,  level,  85,   2,  graphToBack);
	levelPlay(horde4,  allow,  level,  100,   3,  graphToBack);
	levelPlay(horde5,  allow,  level,  110,  4,  graphToBack);
	levelPlay(horde6,  allow,  level,  125,  5,  graphToBack);
	levelPlay(horde7,  allow,  level,  140,  6,  graphToBack);
	levelPlay(horde8,  allow,  level,  155,  7,  graphToBack);
	levelPlay(horde9,  allow,  level,  100,   8,  graphToBack);
	levelPlay(horde10, allow,  level,  110,  9,  graphToBack);

	
		
	ship.draw(graphToBack);
	shots.drawEmAll(graphToBack);
	shots.moveEmAll();
		twoDGraph.drawImage(back, null, 0, 0);
	}


	public void keyPressed(KeyEvent e)
	{
		if (e.getKeyCode() == KeyEvent.VK_LEFT)
		{
			keys[0] = true;
		}
		if (e.getKeyCode() == KeyEvent.VK_RIGHT)
		{
			keys[1] = true;
		}
		if (e.getKeyCode() == KeyEvent.VK_UP)
		{
			keys[2] = true;
		}
		if (e.getKeyCode() == KeyEvent.VK_DOWN)
		{
			keys[3] = true;
		}
		if (e.getKeyCode() == KeyEvent.VK_SPACE)
		{
			keys[4] = true;
		}
		if (e.getKeyCode() == KeyEvent.VK_ENTER)
		{
			keys[5] = true;
		}
		if (e.getKeyCode() == KeyEvent.VK_SHIFT)
		{
			keys[6] = true;
		}
		repaint();
	}

	public void keyReleased(KeyEvent e)
	{
		if (e.getKeyCode() == KeyEvent.VK_LEFT)
		{
			keys[0] = false;
		}
		if (e.getKeyCode() == KeyEvent.VK_RIGHT)
		{
			keys[1] = false;
		}
		if (e.getKeyCode() == KeyEvent.VK_UP)
		{
			keys[2] = false;
		}
		if (e.getKeyCode() == KeyEvent.VK_DOWN)
		{
			keys[3] = false;
		}
		if (e.getKeyCode() == KeyEvent.VK_SPACE)
		{
			keys[4] = false;
		}
		if (e.getKeyCode() == KeyEvent.VK_ENTER)
		{
			keys[5] = false;
		}
		if (e.getKeyCode() == KeyEvent.VK_SHIFT)
		{
			keys[6] = false;
		}
		repaint();
	}

	public void keyTyped(KeyEvent e)
	{

	}

   public void run()
   {
   	try
   	{
   		while(true)
   		{
   		   Thread.currentThread().sleep(5);
            repaint();
         }
      }catch(Exception e)
      {
      }
  	}
}

