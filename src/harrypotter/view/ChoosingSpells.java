package harrypotter.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import harrypotter.model.character.Wizard;

public class ChoosingSpells extends JPanel 
{   
	private JPanel champInfo;
	private JPanel spells;
	
    public ChoosingSpells()
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
    }
    
    public void addSpellButton(JButton btn)
    {
    	this.spells.add(btn);
    }
   
    public void paintComponent(Graphics g){
		super.paintComponent(g);
		Image img = new ImageIcon("HarryPotter.jpeg").getImage();
		g.drawImage(img, 0, 0, null);
	}

}
