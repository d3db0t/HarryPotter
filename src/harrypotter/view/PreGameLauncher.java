package harrypotter.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

public class PreGameLauncher extends JPanel{

	public PreGameLauncher(){
		setLayout(null); // To be able to change the locations of objects inside the panel
	}
	
	public void addButtons(JButton btn){
		String name = btn.getName();
		switch(name)
		{
		case "RavenClawButton":
			ImageIcon ravenclawimg = (new ImageIcon("r.png"));
            btn.setIcon(ravenclawimg);
            btn.setBounds(100,40, ravenclawimg.getIconWidth(), ravenclawimg.getIconHeight());
    		btn.setBorder(BorderFactory.createEmptyBorder());
    		btn.setContentAreaFilled(false);
    		break;
		case "HufflepuffButton":
			ImageIcon hufflepuffimg = (new ImageIcon("h.png"));
			btn.setIcon(hufflepuffimg);
			btn.setBounds(400,40, hufflepuffimg.getIconWidth(), hufflepuffimg.getIconHeight());
			btn.setBorder(BorderFactory.createEmptyBorder());
			btn.setContentAreaFilled(false);
			break;
		case "GryffindorButton":
			ImageIcon gryffindorimg = (new ImageIcon("g.png"));
            btn.setIcon(gryffindorimg);
			btn.setBounds(700,40, gryffindorimg.getIconWidth(), gryffindorimg.getIconHeight());
			btn.setBorder(BorderFactory.createEmptyBorder());
			btn.setContentAreaFilled(false);
			break;
		case "SlytherinButton":
			ImageIcon slytherinimg = (new ImageIcon("s.png"));
			btn.setIcon(slytherinimg);
			btn.setBounds(1000,40, slytherinimg.getIconWidth(), slytherinimg.getIconHeight());
			btn.setBorder(BorderFactory.createEmptyBorder());
			btn.setContentAreaFilled(false);
			break;
		case "CreateButton":
			// CreateButtonGlow Image
			ImageIcon createbuttonglowimg = (new ImageIcon("CreateButtonGlow.png"));
			// CreateButton Button
			ImageIcon createbuttonimg = (new ImageIcon("CreateButton.png"));
			btn.setIcon(createbuttonimg);
			btn.setBounds(990,550, createbuttonimg.getIconWidth(), createbuttonimg.getIconHeight());
			btn.setBorder(BorderFactory.createEmptyBorder());
			btn.setContentAreaFilled(false);
			btn.setRolloverIcon(createbuttonglowimg);
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
