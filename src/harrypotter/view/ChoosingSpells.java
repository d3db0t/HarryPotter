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
import javax.swing.JTextArea;
import javax.swing.JTextField;

import harrypotter.model.character.Wizard;

public class ChoosingSpells extends JPanel 
{   
	private JPanel champInfo;
	private JPanel spells;
	private JPanel spellsInfo;
	
    public ChoosingSpells(String school)
    {
    	setLayout(null);
        this.champInfo = new JPanel();
        //this.champInfo.setSize(150, 200);
        //this.champInfo.setLayout(new BorderLayout());
        this.spells = new JPanel();
        this.spells.setSize(450, 450);
        this.spells.setLayout(new GridLayout(7,3));
        this.spells.setBounds(850, 100, 450, 450);
        this.spells.setBackground(Color.BLACK);
        // Adding spells label
        JLabel sl = new JLabel("Choose 3 Spells");
        sl.setBounds(950, -200, 500, 500);
		sl.setFont(new java.awt.Font(null, 1, 30));
		sl.setForeground(Color.white);
		sl.setVisible(true);
		add(sl);
        //add(this.champInfo , BorderLayout.NORTH);
        add(this.spells);
        addHouseImage(school);
        this.spellsInfo = new JPanel();
    }
    
    public JPanel getSpellsInfo()
    {
    	return spellsInfo ;
    }
    public void addSpellButton(JButton btn)
    {
    	this.spells.add(btn);
    }
    
    public void addButtons(JButton btn){
    	
    	String name = btn.getName();
    	switch(name){
    	case "CreateCharacterButton": 
    		// CreateCharacterButtonGlow Image
    		ImageIcon createcharacterbuttonglowimg = (new ImageIcon("CreateButtonGlow.png"));
    		// CreateCharacterButton Button
    		ImageIcon createcharacterbuttonimg = (new ImageIcon("CreateButton.png"));
    		btn.setIcon(createcharacterbuttonimg);
    		btn.setBounds(990,550, createcharacterbuttonimg.getIconWidth(), createcharacterbuttonimg.getIconHeight());
    		btn.setBorder(BorderFactory.createEmptyBorder());
    		btn.setContentAreaFilled(false);
    		btn.setRolloverIcon(createcharacterbuttonglowimg);
    		break;
    	}
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
    	//addButtons(btn);
    	add(btn);
    }
    
    public void addNameTextField(JTextField tf){
    	JLabel l = new JLabel("Name: ");
    	tf.setBounds(200, 350, 150, 35);
    	tf.setBackground(Color.BLACK);
    	tf.setFont(new java.awt.Font(null, 1, 15));
    	tf.setForeground(Color.WHITE);
		tf.setVisible(true);
		l.setBounds(90, 115, 500, 500);
		l.setFont(new java.awt.Font(null, 1, 30));
		l.setForeground(Color.white);
		l.setVisible(true);
		this.add(tf);
		this.add(l);
    }
    
    public void showSpellInfo(String name,String type,int amount,int cost)
    {
    	JTextArea txt = new JTextArea();
    	//txt.setSize(600, 500);
    	//txt.setBounds(500, 100, 200, 300);
    	txt.setText("Spell name :"+ name+ "\n" +"SpellType:" + type +"\n" + amount+ "\n" + cost);
    	this.spellsInfo.add(txt);
    	this.spellsInfo.setVisible(true);
    	this.spellsInfo.setSize(200, 75);
    	this.spellsInfo.setBounds(400, 100, 200, 75);
    	add(spellsInfo);
    }
    public void paintComponent(Graphics g){
		super.paintComponent(g);
		Image img = new ImageIcon("HarryPotter.jpeg").getImage();
		g.drawImage(img, 0, 0, null);
	}

}
