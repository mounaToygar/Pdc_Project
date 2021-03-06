package jeuOie;

import java.io.*;
import java.util.Random;
import java.awt.*;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Plateau extends JPanel implements ActionListener
{
	private final int decXPion = 19-7;
	private final int decYPion = 509 - 475;

	
	public  final static int [][] coord =
	{
		  //10 cases /////
			  { 19 ,509},  { 55 ,530},  { 91, 510},  {126, 489},  {163, 510},  {199, 531},  {235, 511},  {271, 532},  {307, 512},  {343, 533},
		  //20 cases /////	
			  {379 ,513},  {415 ,534},  {451 ,514},  {487, 494},  {523, 474},  {559, 495},  {595, 475},  {595, 434},  {631, 414},  {667, 435},
		 //30 cases /////	
			  {703, 415},  {702, 374},  {667, 353},  {667, 311},  {703, 290},  {703, 248},  {667, 227},  {631, 206},  {631, 164},  {667, 143},
		 //40 cases /////	
			  {702, 122},  {702, 80 },  {666, 59 },  {630, 38 },  {594, 17 },  {558, 37 },  {522, 57 },  {486, 36 },  {450, 15 },  {414, 35 },
		 //50 cases 40 -> 49 /////	
			  {378, 14 },  {342, 35 },  {306, 55 },  {270, 34 },  {234, 55 },  {198, 34 },  {162, 13 },  {126, 33 },  { 90, 54 },  { 54, 74 },
		 //60 cases /////	
			  { 54, 116},  {18 , 137},  {18 , 179},  {53 , 200},  {89 , 221},  {89 , 263},  {53 , 284},  {53 , 326},  { 53, 368},  { 88, 389},
		 //70 cases /////	
			  {124, 369},  {160, 390},  {196, 411},  {232, 390},  {268, 370},  {304, 391},  {340, 371},  {376, 392},  {376, 434},  {412, 455},
		 //80 cases /////	
			  {448, 435},  {484, 415},  {484, 374},  {520, 353},  {556, 375},  {592, 355},  {591, 314},  {556, 293},  {556, 251},  {555, 210},
		 //90 cases /////	
			  {520, 189},  {520, 147},  {484, 126},  {448, 146},  {412, 167},  {376, 187},  {340, 166},  {304, 145},  {268, 165},  {232, 144},
		 //100 cases /////	
			  {196, 123},  {160, 144},  {160, 186},  {160, 228},  {196, 249},  {232, 270},  {268, 291},  {304, 271},  {340, 292},  {376, 272},
	};
	  
	private Case[] tab = new Case[100];
	private static Joueur j;
	private Boolean actif=false;	 
	public static Joueur getJoueur(){ return j;}
	public Plateau(int x, int y, Joueur j)
	{
		super();
		this.j = j;
		int nombreAleatoire;
		Random rand = new Random();
		int i;
		
		tab[0] = new Depart();tab[99] = new Fin();	
		tab[0].setPosition(0);tab[99].setPosition(99);	
		for( i = 1 ; i < 99 ; i++)
		{
			tab[i]= new Parcours();
			tab[i].setPosition(i);
		}		
		i=0;
		while (i<10)
		{
			nombreAleatoire = rand.nextInt(98 - 1 + 1 ) + 1;
			if ((tab[nombreAleatoire]instanceof Parcours)&&(nombreAleatoire!=98))
			{
				tab[nombreAleatoire]= new Bonus();
				i++;
			}
		}
		i=0;
		while (i<10)
		{
			nombreAleatoire = rand.nextInt(98 - 1 + 1 ) + 1;
			if((tab[nombreAleatoire]instanceof Parcours)&& (nombreAleatoire!=1))
			{
				if(!(tab[nombreAleatoire-2]instanceof Bonus))
				{
					tab[nombreAleatoire]= new Malus();
					i++;
				}
			}
		}
		i=0;
		while (i<10)
		{
			nombreAleatoire = rand.nextInt(98 - 1 + 1 ) + 1;
			if (tab[nombreAleatoire]instanceof Parcours )
			{
				tab[nombreAleatoire]= new Saut();
				i++;
			}
		}
		i=0;
		while (i<10)
		{
			nombreAleatoire = rand.nextInt(98 - 1 + 1 ) + 1;
			if((tab[nombreAleatoire]instanceof Parcours )&& (nombreAleatoire!=97))
			{
				if(nombreAleatoire<95)
				{
					if((!(tab[nombreAleatoire+2]instanceof Malus ))||(!(tab[nombreAleatoire+4]instanceof Malus )))
					{
						tab[nombreAleatoire]= new Definition();
						i++;
					}
				}
				else
				{
					tab[nombreAleatoire]= new Definition();
					i++;
				}
			}
		}
		i=0;
		while (i<10)
		{
			nombreAleatoire = rand.nextInt(98 - 1 + 1 ) + 1;
			if((tab[nombreAleatoire]instanceof Parcours )&& (nombreAleatoire!=98))
			{
				if(nombreAleatoire<97)
				{
					if(!(tab[nombreAleatoire+2]instanceof Malus ))
					{
						tab[nombreAleatoire]= new jeuOie.Image();
						i++;
					}
				}
				else
				{
					tab[nombreAleatoire]= new jeuOie.Image();
					i++;
				}
			}
		}
		setPreferredSize(new Dimension(x,y));
	}	  
  
	public Plateau(){  super(); }  

	public int getPos()
	{
		if(j !=null)
		{
			return j.getPosition();
		}
		else return -1;
	}
	  
	public void setPos( int pos)
	{
		if(j !=null)
		{
			j.setPosition(pos);
		}
	}
	  
	public void activer()
	{
		actif = true;
	}
	
	public void desactiver()
	{
		actif = false;
	}
	
	public Boolean isActif()
	{
		return actif;
	}
	  
	public void paintComponent(Graphics g)
	{
		String tmp;
		int i;
	    try 
	    {//dessiner le fond
	    	Image img = ImageIO.read(new File("Media\\fondCases.png"));
	        g.drawImage(img, 0,0, this);
	        //dessiner les cases
	        img = ImageIO.read(new File("Media\\caseDepart.png"));
	        g.drawImage(img, coord[0][0],coord[0][1] , this);
	        for(i=1;i<coord.length - 1;i++)	
	        {
	        	if(tab[i] instanceof Bonus ) img = ImageIO.read(new File("Media\\caseBonus.png"));
	        	else if(tab[i] instanceof Definition) img = ImageIO.read(new File("Media\\caseDef.png"));
	            else if(tab[i] instanceof Malus) img = ImageIO.read(new File("Media\\caseMalus.png"));
	            else if(tab[i] instanceof Parcours) img = ImageIO.read(new File("Media\\caseParcour.png"));
	            else if(tab[i] instanceof Saut) img = ImageIO.read(new File("Media\\caseSaut.png"));
	        	else if(tab[i] instanceof jeuOie.Image) img = ImageIO.read(new File("Media\\caseImg.png"));
	        	else if(tab[i] instanceof Fin) img = ImageIO.read(new File("Media\\caseFin.png"));	
	        	else  img = ImageIO.read(new File("Media\\caseImg.png"));
	 		    g.drawImage(img, coord[i][0],coord[i][1] , this);
	        }
            img = ImageIO.read(new File("Media\\caseFin.png"));
	        g.drawImage(img, coord[99][0],coord[99][1] , this);
	        //numeroter les cases
	        img = ImageIO.read(new File("Media\\numCases.png"));
		    g.drawImage(img, 0,0 , this);
		    //insertion Pion
	    	tmp = "Media\\P"+j.getPion()+".png";
        	img = ImageIO.read(new File(tmp));
        	g.drawImage(img, coord[getPos()][0] - decXPion ,coord[getPos()][1] - decYPion, this);
	 		img=null;
	    } catch (IOException e) 
	    {
	    	e.printStackTrace();
	    }
	}

	public void actionPerformed(ActionEvent arg0) 
	{
		actif = true;
		//setMsg(true);
		//repaint(); 
		((Bouton)arg0.getSource()).setEnabled(false);
	}

	
	public void jouer(PanneauInfo l)
	{	
		tab[j.getPosition()].jouerCase(j);	
		if(!(tab[j.getPosition()]instanceof Definition)&&!(tab[j.getPosition()]instanceof jeuOie.Image))
		{
			if(tab[j.getPosition()].isDeplacing())
			{   
				l.setSomme(tab[j.getPosition()].getDeplacement());
				l.getLancerDes().setEnabled(false);
				actif = true;
			}
		}
	}
		
	public Case[] getTab() 
	{
		return tab;
	}

	public void setTab(Case[] tab) 
	{
		this.tab = tab;
	}
}