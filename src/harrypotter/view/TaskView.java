package harrypotter.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class TaskView extends JPanel
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
        addChampInfo();
        addInventory();
        map[4][4].setIcon(new ImageIcon("/home/omar_emad/Desktop/HarryPotter_pics/mage03.gif"));
        add(center,BorderLayout.CENTER);
        add(north,BorderLayout.NORTH);
        add(south,BorderLayout.SOUTH);
        add(east,BorderLayout.EAST);
        add(west,BorderLayout.WEST);
       
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
}
