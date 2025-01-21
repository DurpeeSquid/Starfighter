
//Name -

import javax.swing.JFrame;
import java.awt.Component;
import java.awt.Graphics;

public class StarFighter extends JFrame
{
	public static int WIDTH = 1540;
	public static int HEIGHT = 800;

	public StarFighter()
	{
		super("STARFIGHTER");

		setSize(WIDTH, HEIGHT);

		OuterSpace theGame = new OuterSpace(this);
		((Component)theGame).setFocusable(true);

    getContentPane().add( theGame );
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

}