package harrypotter.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public abstract class TaskView extends JPanel
{
    private JPanel center;
    private JPanel north;
    private JPanel south;
    private JPanel east;
    private JPanel west;
    private JTextArea champInfo;    
    private JTextArea info;
    private JPanel traitInfo;
    private JPanel spellsInfo;
    private JPanel potionInfo;
    private JButton [][] map;
    private ArrayList <ImageIcon> gifs;
    
    public TaskView()
    {
    	setLayout(new BorderLayout());
    	this.center = new JPanel();
    	this.north =  new JPanel();
    	this.south = new JPanel();
    	this.east = new JPanel();
    	this.west = new JPanel(new GridLayout(3,0));
    	this.map = new JButton[10][10];
    	this.center.setLayout(new GridLayout(10,10));
        this.champInfo = new JTextArea();
        this.info = new JTextArea();
        this.traitInfo = new JPanel(new GridLayout(2,0));
        this.spellsInfo = new JPanel(new GridLayout(4,0));
        this.potionInfo = new JPanel(new GridLayout(10,0));
        this.west.add(spellsInfo);
        this.west.add(traitInfo);
        this.west.add(potionInfo);
        this.champInfo.setLayout(new BorderLayout());
        this.info.setLayout(new BorderLayout());
        north.add(champInfo);
        east.add(info);
        //south.add(spellsInfo);
        //south.setSize(100, 100);
        generateEmptyMap();
        //addChampInfo();
        //addInventory();
        add(center,BorderLayout.CENTER);
        add(north,BorderLayout.NORTH);
        add(south,BorderLayout.SOUTH);
        add(east,BorderLayout.EAST);
        add(west,BorderLayout.WEST);
        //Adding ArrayList of gifs and images
        this.gifs = new ArrayList<ImageIcon>();
        addGifs();
    }
    
    public void addGifs()
    {
    	this.gifs.add(new ImageIcon("Wizard1.gif"));
    	this.gifs.add(new ImageIcon("Wizard2.gif"));
    	this.gifs.add(new ImageIcon("Wizard3.gif"));
    	this.gifs.add(new ImageIcon("Wizard4.gif"));
    	this.gifs.add(new ImageIcon("egg-icon.png"));
    	this.gifs.add(new ImageIcon("wall.png"));
    }
    public void generateEmptyMap()
    {
    	for(int i = 0 ; i<10 ; i++)
    	{
    		for(int j = 0 ; j < 10 ; j++)
    		{
    			JButton a = new JButton();
    			//a.setName(""+i+j);
    			a.setBackground(Color.BLACK);
    			this.map[i][j] = a;
    			center.add(a);
    		}
    	}
    }
    public void generateSouthPanel(ArrayList<JButton> btns){
    	for (int i = 0; i < btns.size();i++){
    		JButton btn = btns.get(i);
    		String name = btn.getName();
    		switch(name){
    		case "Up":  btn.setBounds(500, 800, 50, 50);
    					//btn.setBorder(BorderFactory.createEmptyBorder());
    					//btn.setContentAreaFilled(false);
    					break;
    		case "Down": btn.setBounds(500, 900, 50, 50);
						 //btn.setBorder(BorderFactory.createEmptyBorder());
						 //btn.setContentAreaFilled(false);
						 break;
    		case "Right": btn.setBounds(550, 900, 50, 50);
			 			  //btn.setBorder(BorderFactory.createEmptyBorder());
			 			  //btn.setContentAreaFilled(false);
			 			  break;
    		case "Left": btn.setBounds(450, 900, 50, 50);
			 			 //btn.setBorder(BorderFactory.createEmptyBorder());
			 			 //btn.setContentAreaFilled(false);
			 			 break;
    		}
    		south.add(btn);
    	}
    }
    
    public ArrayList<ImageIcon> getGifs()
    {
    	return this.gifs;
    }
	public JPanel getCenter() {
		return center;
	}

	public void setCenter(JPanel center) {
		this.center = center;
	}

	public JPanel getNorth() {
		return north;
	}

	public void setNorth(JPanel north) {
		this.north = north;
	}

	public JPanel getSouth() {
		return south;
	}

	public void setSouth(JPanel south) {
		this.south = south;
	}

	public JPanel getEast() {
		return east;
	}

	public void setEast(JPanel east) {
		this.east = east;
	}

	public JPanel getWest() {
		return west;
	}

	public void setWest(JPanel west) {
		this.west = west;
	}

	public JTextArea getChampInfo() {
		return champInfo;
	}

	public void setChampInfo(JTextArea champInfo) {
		this.champInfo = champInfo;
	}

	public JPanel getTraitInfo() {
		return traitInfo;
	}

	public void setTraitInfo(JPanel traitInfo) {
		this.traitInfo =  traitInfo;
	}

	public JPanel getSpellsInfo() {
		return this.spellsInfo;
	}

	public void setSpellsInfo(JPanel spellsInfo) {
		this.spellsInfo = spellsInfo;
	}

	public JPanel getPotionInfo() {
		return this.potionInfo;
	}

	public void setPotionInfo(JPanel potionInfo) {
		this.potionInfo = potionInfo;
	}

	public JButton[][] getButtonsMap() {
		return map;
	}

	public void setMap(JButton[][] map) {
		this.map = map;
	}
    
	public JTextArea getInfo()
	{
		return this.info;
	}
    
    public void addChampInfo(String house, String name, int hp, int ip)
    {
    	//JTextArea a = new JTextArea();
    	champInfo.setText("Name: " + name + " House: " + house + " Hp: " + hp + " IP: " + ip);
    	//champInfo.add(a);
    	//champInfo.add(a,BorderLayout.SOUTH);
    	north.add(champInfo);
    	
    }
    
    public void addSpells(ArrayList <JButton> spells)
    {
    	this.spellsInfo.add(spells.get(0));
    	this.spellsInfo.add(spells.get(1));
    	this.spellsInfo.add(spells.get(2));
    	
    }
    
    public void showEast(String i)
    {
    	this.info.setText(i);
    	this.info.setVisible(true);
    }
    
    public void addTrait(JButton trait)
    {
    	this.traitInfo.add(trait);
    }
    
    public void addPotions(JButton btn)
    {
    	this.potionInfo.add(btn);
    }
    /*
    public void addInventory()
    {
    	traitInfo.setText("TraitInfo : \n Enables the champion to \n make two moves \n Cooldown:2");
    	potionInfo.setText("Potions Avaliable : 4");
    }
    
    public static void main(String [] args)
    {
    	JFrame a = new JFrame("TaskView");
    	
        TaskView v = new TaskView();
        a.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		a.setExtendedState(JFrame.MAXIMIZED_BOTH);
		a.setVisible(true);
		a.add(v); 
    }
    */
}
