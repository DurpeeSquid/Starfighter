//Name -

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import javax.imageio.ImageIO;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class AlienHorde
{
	private List<Alien> aliens;
	private int score;
	private int lives = 3;
	

	public AlienHorde(int size, int rightBound, int bottomBound, int speed)
	{
		aliens = new ArrayList<Alien>();
		int x = 10;
		int y = 75;
		for (int i = 0; i < size; i++)
		{
			aliens.add(new Alien(x, y, 50, 50, speed));
			x += 70;
			if(x >= rightBound)
			{
				x = 10;
				y = bottomBound+8;
			}
		}
	}

	public void newWave(int size, int rightBound, int bottomBound)
	{
		int x = 10;
		int y = 10;
		for (int i = 0; i < size; i++)
		{
			aliens.add(new Alien(x, y, 50, 50, 2));
			x += 70;
			if(x >= rightBound)
			{
				x = 10;
				y = bottomBound+8;
			}
		}
	}

	public int getSize()
	{
		return aliens.size();
	}
	
	public void add(Alien al)
	{
		aliens.add(al);
	}

	public void drawEmAll( Graphics window )
	{
		for(Alien alien : aliens)
		{
			alien.draw(window);
		}
	}

	//moveEmAll handles random alien movement.
	public void moveEmAll(int topBound, int bottomBound, int leftBound, int rightBound)
	{
		for (Alien alien : aliens)
		{
			if (alien.getAllow())
			{
				alien.timed();
				alien.setAllow(false);
			}
			
			
			if (alien.getMovementExport() == 0 && alien.getY() > topBound)
			{
				alien.move("UP");
				
			}

			if ((alien.getMovementExport() == 1 || alien.getMovementExport() == 2) && alien.getY() < bottomBound)
			{
				alien.move("DOWN");
				
			}

			if ((alien.getMovementExport() == 3 || alien.getMovementExport() == 4) && alien.getX() < rightBound)
			{
				alien.move("RIGHT");
				
			}

			if ((alien.getMovementExport() == 5 || alien.getMovementExport() == 6) && alien.getX() > leftBound)
			{	
				alien.move("LEFT");
				
			}
			

			if (alien.getMovementExport() == 7 && alien.getY() > topBound && alien.getX() > leftBound)
			{
				alien.move("NORTHWEST");
			}

			if (alien.getMovementExport() == 8 && alien.getY() > topBound && alien.getX() < rightBound)
			{
				alien.move("NORTHEAST");
			}

			if ((alien.getMovementExport() == 9 || alien.getMovementExport() == 10) && alien.getY() < bottomBound && alien.getX() > leftBound)
			{
				alien.move("SOUTHWEST");
			}

			if ((alien.getMovementExport() == 11 || alien.getMovementExport() == 12) && alien.getY() < bottomBound && alien.getX() < rightBound)
			{
				alien.move("SOUTHEAST");
			}

		} 
	}

	//Comaprews bullet positions to alien positions to detect collisions
	public void removeDeadOnes(List<Ammo> shots)
	{
		for(int i = 0; i < aliens.size(); i++)
		{
			for (int j = 0; j < shots.size(); j++)
			{
				if(shots.get(j).getX() >= aliens.get(i).getX() && shots.get(j).getX() <= aliens.get(i).getX() + aliens.get(i).getWidth())
				{
					if(shots.get(j).getY() >= aliens.get(i).getY() && shots.get(j).getY() <= aliens.get(i).getY() + aliens.get(i).getHeight())
					{
						aliens.remove(i);
						shots.remove(j);
						score += 5;
					}
				}
			}
		}

		//Checks if aliiens reached bottom of screen
		for(int k = 0; k < aliens.size(); k++)
		{
			if (aliens.get(k).getY() > 700)
			{
				aliens.remove(k);
				score -= 2;
			}
		}


	}

	//Checks for ship colision with aliens
	public void checkCollision(Ship ship)
	{
		for (int i = 0; i < aliens.size(); i++)
			{
				if(ship.getX() >= aliens.get(i).getX() && ship.getX() <= aliens.get(i).getX() + aliens.get(i).getWidth())
				{
					if(ship.getY() >= aliens.get(i).getY() && ship.getY() <= aliens.get(i).getY() + aliens.get(i).getHeight())
					{
						aliens.remove(i);
						score -= 5;
						lives --;
					}
				}
			}
	}

	public void removeAll()
	{
		if (aliens.size() > 0)
		{
			for (int i = 0; i < aliens.size(); i++)
			{
				aliens.remove(i);
			}
		}
	}

	public int getScore()
	{
		return score;
	}

	public int getLives()
	{
		return lives;
	}


	public String toString()
	{
		return "";
	}
}
