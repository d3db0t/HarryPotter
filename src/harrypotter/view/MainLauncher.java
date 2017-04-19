package harrypotter.view;

import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

public class MainLauncher extends JPanel{
	
	public MainLauncher() throws IOException{
		setLayout(null); // To be able to change the locations of objects inside the panel
	}
	
	public void addButtons(JButton btn)
	{	
		String name = btn.getName();
		switch(name)
		{
			case "DragonButton":
				ImageIcon dragonintroimg = (new ImageIcon("DragonIntro.gif"));
				btn.setIcon(dragonintroimg);
				btn.setBounds(300,10, dragonintroimg.getIconWidth(), dragonintroimg.getIconHeight());
				btn.setBorder(BorderFactory.createEmptyBorder());
				btn.setContentAreaFilled(false);
				break;
			case "HarryPotterLogo":
				ImageIcon harrypotterlogoimg = (new ImageIcon("HPlogo.png"));
				btn.setIcon(harrypotterlogoimg);
				btn.setBounds(20,40, harrypotterlogoimg.getIconWidth(), harrypotterlogoimg.getIconHeight());
				btn.setBorder(BorderFactory.createEmptyBorder());
				btn.setContentAreaFilled(false);
				break;
			case "NewGameButton":
				ImageIcon newgameimg = (new ImageIcon("NewGameButton.png"));
				btn.setIcon(newgameimg);
				btn.setBounds(900,450, newgameimg.getIconWidth(), newgameimg.getIconHeight());
			    btn.setBorder(BorderFactory.createEmptyBorder());
				btn.setContentAreaFilled(false);
				break;
		}
		this.add(btn);
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		Image img = new ImageIcon("HarryPotter.jpeg").getImage();
		g.drawImage(img, 0, 0, null);
	}

}
