package harrypotter.view;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainLauncher extends JPanel{

	//JPanel btnPanel ;
	
	public MainLauncher() throws IOException{
		setLayout(null); // To be able to change the locations of objects inside the panel
		//super();
	    //btnPanel = new JPanel(new BorderLayout());
	    //add(btnPanel);
	}
	
	public void addButtons(ArrayList <JButton> btns)
	{ // Play , Quit	
		//btnPanel.add(btns.get(0),BorderLayout.NORTH);
		//btnPanel.add(btns.get(1),BorderLayout.SOUTH);
		this.add(btns.get(0));
		this.add(btns.get(1));
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		Image img = new ImageIcon("HarryPotter.jpeg").getImage();
		g.drawImage(img, 0, 0, null);
	}

}
