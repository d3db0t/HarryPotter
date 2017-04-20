package harrypotter.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;

import harrypotter.model.character.GryffindorWizard;
import harrypotter.model.character.HufflepuffWizard;
import harrypotter.model.character.RavenclawWizard;
import harrypotter.model.character.SlytherinWizard;
import harrypotter.model.character.Wizard;
import harrypotter.view.Launcher;
import harrypotter.view.MainLauncher;
import harrypotter.view.PreGameLauncher;

public class LauncherController implements ActionListener {
	private Launcher launcher;
	private MainLauncher mainLauncher;
	private PreGameLauncher preGameLauncher;
	private ArrayList <Wizard> champions;
	
	public LauncherController() throws IOException{
		// Initialize MainLauncher
		mainLauncher = new MainLauncher();
		// DragonInto gif
		JButton dragonintrobtn = new JButton();
		dragonintrobtn.setName("DragonButton");
		mainLauncher.addButtons(dragonintrobtn);
		// HarryPotter Logo
		JButton harrypotterlogobtn = new JButton();
		harrypotterlogobtn.setName("HarryPotterLogo");
		mainLauncher.addButtons(harrypotterlogobtn);
		// NewGameButton
		JButton newgamebtn = new JButton();
		newgamebtn.setName("NewGameButton");
		newgamebtn.addActionListener(this);
		mainLauncher.addButtons(newgamebtn);
        //Set main launcher to be visible 		
		mainLauncher.setVisible(true);
		launcher = new Launcher();
		launcher.addPanel(mainLauncher);
		//Initialize the champions ArrayList 
		this.champions = new ArrayList <Wizard>();
	}
	
	@Override
	public void actionPerformed(ActionEvent e){
		JButton btn  = (JButton) e.getSource();
		String btnID = btn.getName();
		switch(btnID){
		case "NewGameButton" : startPreGameLauncher();
							   break;
		case "RavenClawButton":
			if (this.champions.size() == 4)
				 JOptionPane.showMessageDialog(launcher, "You reached the maximum number of champions");
			else
			{
			  String name = showNameWindow();
			  if(name != null)
				  this.champions.add(new RavenclawWizard(name));
			}
			 break;
		case "HufflepuffButton":
			 if (this.champions.size() == 4)
				 JOptionPane.showMessageDialog(launcher, "You reached the maximum number of champions");
			 else
			 {
				String name1 = showNameWindow();
				if(name1 != null)
				   this.champions.add(new HufflepuffWizard(name1));
			 }
			 break;
		case "GryffindorButton":
		     if (this.champions.size() == 4)
			     JOptionPane.showMessageDialog(launcher, "You reached the maximum number of champions");
		     else
		     {
		        String name2 = showNameWindow();
			    if(name2 != null)
				   this.champions.add(new GryffindorWizard(name2));
		     }
			 break;
		case "SlytherinButton":
			 if (this.champions.size() == 4)
				 JOptionPane.showMessageDialog(launcher, "You reached the maximum number of champions");
			 else
			 {
			     String name3 = showNameWindow();
			     if(name3 != null)
				    this.champions.add(new SlytherinWizard(name3));
			 }
			 break;
		case "CreateButton":
			if(this.champions.size() == 4)
				startTournament();
			else
				JOptionPane.showMessageDialog(launcher, "Please select " + (4 - this.champions.size())
						+ " more champions");
			break;
		}
	}
	private String showNameWindow()
	{
		String name = JOptionPane.showInputDialog(launcher,
                "Please enter your name", null);
		return name;
	}
	public void startPreGameLauncher(){
		mainLauncher.removeAll();
		mainLauncher.revalidate();
		preGameLauncher = new PreGameLauncher();
		// RavenClaw Image Button
		JButton ravenclawbtn = new JButton();
		ravenclawbtn.setName("RavenClawButton");
		ravenclawbtn.addActionListener(this);
		preGameLauncher.addButtons(ravenclawbtn);
		// Hufflepuff Image Button
		JButton hufflepuffbtn = new JButton();
		hufflepuffbtn.setName("HufflepuffButton");
		hufflepuffbtn.addActionListener(this);
		preGameLauncher.addButtons(hufflepuffbtn);
		// Gryffindor Image Button
		JButton gryffindorbtn = new JButton();
		gryffindorbtn.setName("GryffindorButton");
		gryffindorbtn.addActionListener(this);
		preGameLauncher.addButtons(gryffindorbtn);
		// Slytherin Image Button
		JButton slytherinbtn = new JButton();
		slytherinbtn.setName("SlytherinButton");
		slytherinbtn.addActionListener(this);
		preGameLauncher.addButtons(slytherinbtn);
		// CreateButtonGlow Image
		// CreateButton Button
		JButton createbtn = new JButton();
		createbtn.setName("CreateButton");
		createbtn.addActionListener(this);
		preGameLauncher.addButtons(createbtn);
		//Make pre game launcher visible and adding it to frame
		preGameLauncher.setVisible(true); 
		launcher.addPanel(preGameLauncher);
	}
	public void startTournament()
	{
		
	}
	public static void main(String [] args) throws IOException{
		new LauncherController();
	}

}
