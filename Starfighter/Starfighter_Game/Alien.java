
import java.io.File;
import java.net.URL;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import javax.imageio.ImageIO;
import java.util.Timer;
import java.util.TimerTask;


public class Alien extends MovingThing
{
	private int speed;
	private Image image;
	private int movement;
	private int movementExport;
	private boolean allow = true;

	public Alien()//default constructor
	{
		this(0,0,50,50,0);
	}

	public Alien(int x, int y)//overloaded constructor
	{
	   this(x, y, 50, 50 ,0);
	}

	public Alien(int x, int y, int s)
	{
	   this(x, y, 50, 50, s);
	   
	}

	public Alien(int x, int y, int w, int h, int s)
	{
		super(x, y, w, h);
		speed=s;
		try
		{
			
			image = ImageIO.read(new File("alien.JPG"));
		}
		catch(Exception e)
		{

		}
	}

	public void timed()
	{
		Timer timer = new Timer();
	
		TimerTask task = new TimerTask()
		{
			public void run()
			{
				movementExport = getMovement();
				allow = true;
			}

		};

		timer.schedule(task, 1000);
	}


	public void setSpeed(int s)
	{
	   speed = s;
	}

	public int getSpeed()
	{
	   return speed;
	}
	
	public int getMovement()
	{
		movement = (int)(Math.random()*13);
		return movement;
	}

	public int getMovementExport()
	{
		return movementExport;
	}

	public boolean getAllow()
	{
		return allow;
	}

	public void setAllow(boolean t)
	{
		if (t)
		{
			allow = true;
		}

		else
		{
			allow = false;
		}
	}

	public void move(String direction)
	{

    if(direction.equals("LEFT"))
    {
			setX(getX()-getSpeed());
    }
    else if(direction.equals("RIGHT"))
		{
			setX(getX() + getSpeed());
		}
		else if(direction.equals("UP"))
		{
			setY(getY() - getSpeed());
		}
		else if(direction.equals("DOWN"))
		{
			setY(getY() + getSpeed());
		}
		else if(direction.equals("NORTHWEST"))
		{
			setY(getY() - getSpeed());
			setX(getX() - getSpeed());
		}
		else if(direction.equals("NORTHEAST"))
		{
			setY(getY() - getSpeed());
			setX(getX() + getSpeed());
		}
		else if(direction.equals("SOUTHWEST"))
		{
			setY(getY() + getSpeed());
			setX(getX() - getSpeed());
		}
		else if(direction.equals("SOUTHEAST"))
		{
			setY(getY() + getSpeed());
			setX(getX() + getSpeed());
		}
	}

	public void draw( Graphics window ){
   	window.drawImage(image,getX(),getY(),getWidth(),getHeight(),null);
	}

	public String toString()
	{
		return super.toString() + getSpeed();
	}
}