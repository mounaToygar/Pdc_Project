package jeuOie;

import java.awt.*;
import java.awt.Image;
import java.awt.event.*;
import java.io.*;
import javax.imageio.ImageIO;
import javax.swing.*;

@SuppressWarnings("serial")
public class Bouton extends JButton implements MouseListener
{
	private String nom;
	private Image img;
	
	public Bouton(String arg)
	{
		super(arg);
		setPreferredSize(new Dimension(149,34));
		this.setBorderPainted(false);
		nom=arg;
		try 
		{
			img = ImageIO.read(new File("Media\\Bouton.png"));
		} catch (IOException e) 
		{
			e.printStackTrace();
		} 
		this.addMouseListener(this);
	}
	
	public void paintComponent(Graphics g)
	{
		try 
		{
	        g.drawImage(img, 0, 0, this);
		} catch (Exception e) 
		{
	        e.printStackTrace();
		} 
		g.setColor(Color.white);
		g.drawString(nom, (this.getWidth() - nom.length())/16 , (this.getHeight() / 2) +3 );
	}
	
	public void mouseClicked(MouseEvent event) {	}
	
	public void mouseEntered(MouseEvent event) 
	{
		try 
		{
			img = ImageIO.read(new File("Media\\BoutonHover.png"));
		} catch (IOException e) 
		{
			e.printStackTrace();
		}
		this.repaint();
		if(LeMain.getPartie()!=null)
			if(LeMain.getPartie().getInfo()!=null)
				LeMain.getPartie().getInfo().repaint();
	}
	
	public void mouseExited(MouseEvent event) 
	{
		try 
		{
			img = ImageIO.read(new File("Media\\bouton.png"));
	    } catch (IOException e) 
	    {
	    	e.printStackTrace();
	    } 
		this.repaint();
		if(LeMain.getPartie()!=null)
			if(LeMain.getPartie().getInfo()!=null)
				LeMain.getPartie().getInfo().repaint();
	}
	
	public void mousePressed(MouseEvent event) 
	{
		try 
		{
			img = ImageIO.read(new File("Media\\BoutonPressed.png"));
		} catch (IOException e) 
		{
			e.printStackTrace();
		}
		this.repaint();
		if(LeMain.getPartie()!=null)
			if(LeMain.getPartie().getInfo()!=null)
				LeMain.getPartie().getInfo().repaint();
	}
	
	public void mouseReleased(MouseEvent event) 
	{
		try 
		{
			img = ImageIO.read(new File("Media\\boutonHover.png"));
	    } catch (IOException e) 
	    {
	    	e.printStackTrace();
	    } 
		repaint();
		if(LeMain.getPartie()!=null)
			if(LeMain.getPartie().getInfo()!=null)
				LeMain.getPartie().getInfo().repaint();
	}
}