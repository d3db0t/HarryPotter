package harrypotter.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

public class PreGameLauncher extends JPanel{

	public PreGameLauncher(){
		setLayout(null); // To be able to change the locations of objects inside the panel
	}
	
	public void addButtons(JButton btn){
		this.add(btn);
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		Image img = new ImageIcon("HarryPotter.jpeg").getImage();
		g.drawImage(img, 0, 0, null);
	}

}
