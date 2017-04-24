package harrypotter.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import harrypotter.model.character.Wizard;

public class ChoosingSpells extends JPanel 
{   
	private JPanel champInfo;
	private JPanel spells;
	
    public ChoosingSpells(String school)
    {
    	setLayout(null);
        this.champInfo = new JPanel();
        //this.champInfo.setSize(150, 200);
        //this.champInfo.setLayout(new BorderLayout());
        this.spells = new JPanel();
        this.spells.setSize(450, 450);
        this.spells.setLayout(new GridLayout(7,3));
        this.spells.setBounds(100, 50, 450, 450);
        //add(this.champInfo , BorderLayout.NORTH);
        add(this.spells);
        addHouseImage(school);
    }
    
    public void addSpellButton(JButton btn)
    {
    	this.spells.add(btn);
    }
    
    public void addButtons(JButton btn){
    	this.add(btn);
    }
    
    public void addHouseImage(String s){
    	JButton btn = new JButton();
    	switch(s){
    	case "RavenClawButton":
			ImageIcon ravenclawimg = (new ImageIcon("r.png"));
            btn.setIcon(ravenclawimg);
            btn.setBounds(50,40, ravenclawimg.getIconWidth(), ravenclawimg.getIconHeight());
    		btn.setBorder(BorderFactory.createEmptyBorder());
    		btn.setContentAreaFilled(false);
    		break;
		case "HufflepuffButton":
			ImageIcon hufflepuffimg = (new ImageIcon("h.png"));
			btn.setIcon(hufflepuffimg);
			btn.setBounds(50,40, hufflepuffimg.getIconWidth(), hufflepuffimg.getIconHeight());
			btn.setBorder(BorderFactory.createEmptyBorder());
			btn.setContentAreaFilled(false);
			break;
		case "GryffindorButton":
			ImageIcon gryffindorimg = (new ImageIcon("g.png"));
            btn.setIcon(gryffindorimg);
			btn.setBounds(50,40, gryffindorimg.getIconWidth(), gryffindorimg.getIconHeight());
			btn.setBorder(BorderFactory.createEmptyBorder());
			btn.setContentAreaFilled(false);
			break;
		case "SlytherinButton":
			ImageIcon slytherinimg = (new ImageIcon("s.png"));
			btn.setIcon(slytherinimg);
			btn.setBounds(50,40, slytherinimg.getIconWidth(), slytherinimg.getIconHeight());
			btn.setBorder(BorderFactory.createEmptyBorder());
			btn.setContentAreaFilled(false);
			break;
    	
    	}
    	addButtons(btn);
    }
   
    public void paintComponent(Graphics g){
		super.paintComponent(g);
		Image img = new ImageIcon("HarryPotter.jpeg").getImage();
		g.drawImage(img, 0, 0, null);
	}

}
