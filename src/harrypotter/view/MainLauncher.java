package harrypotter.view;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainLauncher extends Launcher{

	JPanel btnPanel ;
	
	public MainLauncher() throws IOException{
		super();
        getContentPane().setLayout(new BorderLayout());
	    setContentPane(new JPanel() {
	        BufferedImage image = ImageIO.read(new File("HarryPotter.jpeg"));
	        public void paintComponent(Graphics g) {
	            super.paintComponent(g);
	            g.drawImage(image, 0, 0,null);
	        }
	    });
	    this.btnPanel = new JPanel(new BorderLayout());
	    this.add(btnPanel);
	}
	
	public void addButtons(ArrayList <JButton> btns)
	{ // Play , Quit	
		btnPanel.add(btns.get(0),BorderLayout.WEST);
		btnPanel.add(btns.get(1),BorderLayout.EAST);		
	}

}
