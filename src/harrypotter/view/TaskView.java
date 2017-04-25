package harrypotter.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.util.ArrayList;

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
    private JTextArea traitInfo;
    private JTextArea spellsInfo;
    private JTextArea potionInfo;
    private JButton [][] map;
    private ArrayList <ImageIcon> gifs;
    
    public TaskView()
    {
    	setLayout(new BorderLayout());
    	this.center = new JPanel();
    	this.north =  new JPanel();
    	this.south = new JPanel();
    	this.east = new JPanel();
    	this.west = new JPanel();
    	this.map = new JButton[10][10];
    	this.center.setLayout(new GridLayout(10,10));
        this.champInfo = new JTextArea();
        this.traitInfo = new JTextArea();
        this.spellsInfo = new JTextArea();
        this.potionInfo = new JTextArea();
        this.champInfo.setLayout(new BorderLayout());
        north.add(champInfo);
        south.add(spellsInfo);
        east.add(traitInfo);
        west.add(potionInfo);
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
    }
    public void generateEmptyMap()
    {
    	for(int i = 0 ; i<10 ; i++)
    	{
    		for(int j = 0 ; j < 10 ; j++)
    		{
    			JButton a = new JButton();
    			a.setBackground(Color.BLACK);
    			this.map[i][j] = a;
    			center.add(a);
    		}
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

	public JTextArea getTraitInfo() {
		return traitInfo;
	}

	public void setTraitInfo(JTextArea traitInfo) {
		this.traitInfo = traitInfo;
	}

	public JTextArea getSpellsInfo() {
		return spellsInfo;
	}

	public void setSpellsInfo(JTextArea spellsInfo) {
		this.spellsInfo = spellsInfo;
	}

	public JTextArea getPotionInfo() {
		return potionInfo;
	}

	public void setPotionInfo(JTextArea potionInfo) {
		this.potionInfo = potionInfo;
	}

	public JButton[][] getButtonsMap() {
		return map;
	}

	public void setMap(JButton[][] map) {
		this.map = map;
	}
    
    /*
    public void addChampInfo()
    {
    	//JTextArea a = new JTextArea();
    	champInfo.setText("Name : Omar  House : Gryffindor Hp : 1000 IP :500 Trait : false");
    	//champInfo.add(a);
    	//champInfo.add(a,BorderLayout.SOUTH);
    		
    }
    
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
