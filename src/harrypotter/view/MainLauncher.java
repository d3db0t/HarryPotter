package harrypotter.view;

import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

public class MainLauncher extends JPanel{
	
	public MainLauncher() throws IOException{
		setLayout(null); // To be able to change the locations of objects inside the panel
	}
	
	public void addButtons(ArrayList <JButton> btns)
	{	
		this.add(btns.get(0));
		this.add(btns.get(1));
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		Image img = new ImageIcon("HarryPotter.jpeg").getImage();
		g.drawImage(img, 0, 0, null);
	}

}
