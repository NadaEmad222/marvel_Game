package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import engine.Game;
import engine.ModelListener;
import engine.Player;
import engine.PriorityQueue;
import exceptions.AbilityUseException;
import exceptions.ChampionDisarmedException;
import exceptions.InvalidTargetException;
import exceptions.LeaderAbilityAlreadyUsedException;
import exceptions.LeaderNotCurrentException;
import exceptions.NotEnoughResourcesException;
import exceptions.UnallowedMovementException;
import model.abilities.Ability;
import model.abilities.AreaOfEffect;
import model.abilities.CrowdControlAbility;
import model.abilities.DamagingAbility;
import model.abilities.HealingAbility;
import model.world.AntiHero;
import model.world.Champion;
import model.world.Cover;
import model.world.Direction;
import model.world.Hero;

public class GameGUI implements ActionListener , ModelListener{  //controller 

	
	private Gameview view ;
	private Player p1=new Player("timo");
	private Player p2= new Player("Pumba");
	private Object[][] components =new Object[5][5];
	private Game game =new Game(p1,p2);
	private ArrayList<String> player1Champions ;
	private ArrayList<String> player2Champions ;
	//private ArrayList<JButton> 
	private boolean AttackIsPressed = false ;
	private Champion c ;
	private boolean directionalCastAbility=false;
	private boolean singletargetCastAbility= false;
	private ArrayList<JButton> abilitiesBtn = new ArrayList<JButton>() ;
	private Ability pressedAbilty;
	private JPanel abilitiespnl;
	
	public GameGUI() throws IOException {
		
		//game = new Game(p1,p2);
		view = new Gameview();
		view.getNamefill().addActionListener(this);
		view.getNametext().addActionListener(this);
		view.getNametext2().addActionListener(this);
		player1Champions = new ArrayList<String>() ;
		player2Champions = new ArrayList<String>() ;
		displayAllChamp(this.game.getAvailableChampions(),0);
		game.setMyListener(this);
		
	}
public void displayAllChamp(ArrayList<Champion> champions, int a) {
		

		String champ = "";
		String p;
		champ += "Available Champions: \n";
		champ += "'''''''''''\n";
		for (Champion addedChamp : champions) {
			champ += "The Champion's Name is: " + addedChamp.getName() 
					+ " Current HP = " + addedChamp.getCurrentHP() 
					+ " Mana = " + addedChamp.getMana() 
					+ " Speed = " + addedChamp.getSpeed() 
					+ " Max Actions Per Turn = " + addedChamp.getMaxActionPointsPerTurn()	
					+ " Attack Damage = " + addedChamp.getAttackDamage() 
					+ " Attack Range = " + addedChamp.getAttackRange() ;
			if(addedChamp instanceof Hero)
			{
				champ += " Type: Hero" ;
			}
			else if(addedChamp instanceof AntiHero)
			{
				champ += " Type: AntiHero" ;
			}
			else //if(addedChamp instanceof Villian)
			{
				champ += " Type: Villian" +"\n";
			}
			if(p1.getLeader() == addedChamp || p2.getLeader() == addedChamp && a!=0)
				champ += "THIS IS THE LEADER.";
			if(a!=0)
				champ += "\n" + "AppliedEffects: " ;
			ArrayList<model.effects.Effect> effects = addedChamp.getAppliedEffects();
			for(int i=0;i<effects.size();i++)
			{
				champ += effects.get(i).getName() + " ,Duration: "+effects.get(i).getDuration()+" \n ";
			}
			champ += "\n~~~~\n";
		}
		if(a==1)
			this.view.getChampionsData1().setText(champ);
		else if(a==2)
			this.view.getChampionsData2().setText(champ);
		else
		    this.view.getAllChamp().setText(champ);
	
	}

	public void addingChampion(String s) {
		if(player1Champions.size()==3 && player2Champions.size()!=3 ) {
			//second player turn 
			player2Champions.add(s);
		}
		else {
			//you are still adding to player1
			player1Champions.add(s);
		}
		String team = "<html> First Player Team : <html> <br/> " +"<html>---------------------------<html><br/>" ;
		for(String Champion : player1Champions ) {
			if(Champion.equals(player1Champions.get(0)))
				team+= Champion +"(Leader) "+ "<br/>"  ;
			else
				team += Champion + "<br/>" ;
		}
		 team += "<br/> "+"<br/>"+"<br/>" + "<html>Second Player Team : <html><br/> " + "----------------------------- <br/>"  ;
		for(String Champion : player2Champions ) {
			if(Champion.equals(player2Champions.get(0)))
				team+=   Champion +"(Leader) "+ "<br/>" ;
			else
				team += Champion + "<br/>" ;
		}
		this.view.getDisplayChosenChamp().setForeground(Color.white);
		this.view.getDisplayChosenChamp().setText(team);
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		
		if(o instanceof JButton) {
			
			JButton d = (JButton) o ;
			String s = d.getText() ;
			String n = d.getName() ;
			if(n==null)
				n ="" ;
			
		if(d.equals(this.view.getNamefill())) {
				
//					try {
//						this.game = new Game(p1,p2);
//						System.out.print("new game is being created");
//					} catch (IOException e1) {
//						e1.printStackTrace();						
//					}
					
					startAction(d);
				}
			
		
		if(n.equals("Captain America")||n.equals("Deadpool")||n.equals("Dr Strange")||n.equals("Electro")||n.equals("Ghost Rider")||
				n.equals("Hela")||n.equals("Hulk")||n.equals("Iceman")||n.equals("Ironman")||n.equals("Loki")||
				n.equals("Quicksilver")||n.equals("Spiderman")||n.equals("Thor")||n.equals("Venom")||n.equals("Yellow Jacket")) {
			if(this.player1Champions.size()+this.player2Champions.size()<6)
			{
				this.addingChampion(n);
				d.setEnabled(false);
			}
			else {
				//what if he kept clicking on buttons 
			}
		}
	
		if(d.equals(this.view.getStart())) {
			p1 = new Player(view.getName1());
			p2 = new Player(view.getName2());
			//set the 2 teams , set the next panel , 
		
			if(player1Champions.size()==3) {
			for(String chString : this.player1Champions) {
				for(int i =0 ; i<this.game.getAvailableChampions().size() ;i++) {
					if(this.game.getAvailableChampions().get(i).getName().equals(chString))
						{
							if(this.game.getAvailableChampions().get(i).getName().equals(player1Champions.get(0)))
								p1.setLeader(this.game.getAvailableChampions().get(i));
							p1.getTeam().add(this.game.getAvailableChampions().get(i)) ;
							break ;
						}
				}
			}
			}
			else {
				this.view.getStart().setText("PLease player1 choose your 3 champions! ");
				return ;
			}
			
			if(player2Champions.size()==3) {
				for(String chString : this.player2Champions) {
					for(int i =0 ;i<this.game.getAvailableChampions().size() ;i++) {
						if(this.game.getAvailableChampions().get(i).getName().equals(chString))
							{
							if(this.game.getAvailableChampions().get(i).getName().equals(player2Champions.get(0)))
								p2.setLeader(this.game.getAvailableChampions().get(i));
							p2.getTeam().add(this.game.getAvailableChampions().get(i)) ;
							break ;
							}}
				}
				//hena call or set the new panel 
				this.view.boardView();
				//this.game.prepareChampionTurns() ;
				try {
					game =new Game(p1,p2);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				displayChampions(p1.getTeam(),1);
				displayChampions(p2.getTeam(),2);
				displayTurnOrder(game.getTurnOrder());
				displayPlayer1Data(game.getFirstPlayer(), game.isFirstLeaderAbilityUsed());
				displayPlayer2Data(game.getSecondPlayer(), game.isSecondLeaderAbilityUsed());
				displayCurrentChampData(game.getCurrentChampion());
				components=this.game.getBoard();
				displayonBoard(this.game.getBoard()) ;
				game.setMyListener(this);
				this.view.getMoveD().addActionListener(this);
				this.view.getMoveU().addActionListener(this);
				this.view.getMoveL().addActionListener(this);
				this.view.getMoveR().addActionListener(this);
				//this.view.getAbilitieslist().addActionListener(this);
				this.view.getAttack().addActionListener(this);
				this.view.getCastleaderability().addActionListener(this);
				this.view.getCastability().addActionListener(this);
				this.view.getEndturn().addActionListener(this);
				
				}
				else {
					this.view.getStart().setText("PLease player2 choose your 3 champions! ");
				}
			
		}
		
		
		//another button
		
		if(d.equals(this.view.getMoveU())){
			
			if(AttackIsPressed){ 
				try {
					AttackIsPressed=false  ;
					game.attack(Direction.UP);
					this.displayonBoard(this.game.getBoard());
					this.displayCurrentChampData(this.game.getCurrentChampion());
					this.view.getMessage().setText("");
					this.displayChampions(this.game.getFirstPlayer().getTeam(), 1);
					this.displayChampions(this.game.getSecondPlayer().getTeam(), 2);
					
				} catch (NotEnoughResourcesException | ChampionDisarmedException | InvalidTargetException e1) {
					this.view.getMessage().setText(e1.getMessage());
				}
			}
			else if(directionalCastAbility)
			{	this.view.getMessage().setText("");
				directionalCastAbility=false;
				try {
					this.game.castAbility(pressedAbilty, Direction.UP);
				} catch (NotEnoughResourcesException | AbilityUseException
						| CloneNotSupportedException e1) {
					// TODO Auto-generated catch block
					this.view.getMessage().setText(e1.getMessage());
				}
			}
			else
				try {
					game.move(Direction.UP);
					this.view.getMessage().setText("");
				} catch (NotEnoughResourcesException | UnallowedMovementException e1) {
					this.view.getMessage().setText(e1.getMessage());
				}
		}
		if(d.equals(this.view.getMoveD())){
			
			if(AttackIsPressed){ 
				try {
					AttackIsPressed=false ;
					game.attack(Direction.DOWN);
					this.displayonBoard(this.game.getBoard());
					this.displayCurrentChampData(this.game.getCurrentChampion());
					this.view.getMessage().setText("");
					this.displayChampions(this.game.getFirstPlayer().getTeam(), 1);
					this.displayChampions(this.game.getSecondPlayer().getTeam(), 2);
					
				} catch (NotEnoughResourcesException | ChampionDisarmedException | InvalidTargetException e1) {
					this.view.getMessage().setText(e1.getMessage());
				}
			}
			else if(directionalCastAbility)
			{	this.view.getMessage().setText("");
				directionalCastAbility=false;
				try {
					this.game.castAbility(pressedAbilty, Direction.DOWN);
				} catch (NotEnoughResourcesException | AbilityUseException | CloneNotSupportedException e1) {
					this.view.getMessage().setText(e1.getMessage());
				}
			}
			else
				try {
					game.move(Direction.DOWN);
					this.view.getMessage().setText("");
				} catch (NotEnoughResourcesException | UnallowedMovementException e1) {
					this.view.getMessage().setText(e1.getMessage());
				}
		}
		if(d.equals(this.view.getMoveL())){ 
			if(AttackIsPressed){ 
				try {
					AttackIsPressed=false ;
					game.attack(Direction.LEFT);
					this.displayonBoard(this.game.getBoard());
					this.displayCurrentChampData(this.game.getCurrentChampion());
					this.view.getMessage().setText("");
					this.displayChampions(this.game.getFirstPlayer().getTeam(), 1);
					this.displayChampions(this.game.getSecondPlayer().getTeam(), 2);
					
				} catch (NotEnoughResourcesException | ChampionDisarmedException | InvalidTargetException e1) {
					this.view.getMessage().setText(e1.getMessage());
				}
				
			}
			else if(directionalCastAbility)
			{	this.view.getMessage().setText("");
				directionalCastAbility=false;
				try {
					this.game.castAbility(pressedAbilty, Direction.LEFT);
				} catch (NotEnoughResourcesException | AbilityUseException
						| CloneNotSupportedException e1) {
					// TODO Auto-generated catch block
					this.view.getMessage().setText(e1.getMessage());
				}
			}
			else 
				try {
					game.move(Direction.LEFT);
					this.view.getMessage().setText("");
				} catch (NotEnoughResourcesException | UnallowedMovementException e1) {
					this.view.getMessage().setText(e1.getMessage());
				}
		}
		if(d.equals(this.view.getMoveR())){
			
			if(AttackIsPressed){ 
				try {
					AttackIsPressed=false ;
					game.attack(Direction.RIGHT);
					this.displayonBoard(this.game.getBoard());
					this.displayCurrentChampData(this.game.getCurrentChampion());
					this.view.getMessage().setText("");
					this.displayChampions(this.game.getFirstPlayer().getTeam(), 1);
					this.displayChampions(this.game.getSecondPlayer().getTeam(), 2);
					
				} catch (NotEnoughResourcesException | ChampionDisarmedException | InvalidTargetException e1) {
					this.view.getMessage().setText(e1.getMessage());
				}
			}
			else if(directionalCastAbility)
			{
				this.view.getMessage().setText("");
				directionalCastAbility=false;
				try {
					this.game.castAbility(pressedAbilty, Direction.RIGHT);
				} catch (NotEnoughResourcesException | AbilityUseException
						| CloneNotSupportedException e1) {
					// TODO Auto-generated catch block
					this.view.getMessage().setText(e1.getMessage());
				}
			}
			else 
			
				try {
					this.view.getMessage().setText("");
					game.move(Direction.RIGHT);
				} catch (NotEnoughResourcesException | UnallowedMovementException e1) {
					this.view.getMessage().setText(e1.getMessage());
				}
			
		}
		if(d.equals(view.getAttack())) {
			
			AttackIsPressed  =true ;
			this.view.getMessage().setText("please choose a direction to attack...");
			
			
		}
		if(d.equals(view.getCastability())) {
			this.view.getMessage().setText("");
			currentAbilitiesForCasting();
		}
		if(abilitiesBtn.contains(d)){
			Ability abtn= this.game.getAbilityByName(this.game.getCurrentChampion().getAbilities(),d.getName());
			/////
			pressedAbilty=abtn;
			displayAbilityInfo(abtn);
	       // this.view.getMessage().setText("please, choose a Champion to cast this ability");
			this.view.getCurrentAbilitiespnl().setVisible(false);
//			this.view.getActions().setVisible(true);
			this.displayonBoard(this.game.getBoard());
			this.displayCurrentChampData(this.game.getCurrentChampion());
			//this.view.getMessage().setText("");
			this.displayChampions(this.game.getFirstPlayer().getTeam(), 1);
			this.displayChampions(this.game.getSecondPlayer().getTeam(), 2);
		}
		if(d.equals(this.view.getUseAbility()))
		{
			if(pressedAbilty.getCastArea()==AreaOfEffect.SELFTARGET
					|| pressedAbilty.getCastArea()==AreaOfEffect.TEAMTARGET
					|| pressedAbilty.getCastArea()==AreaOfEffect.SURROUND )
			{
				try {
					this.game.castAbility(pressedAbilty);
					this.view.getMessage().setText("");
				} catch (NotEnoughResourcesException | AbilityUseException
						| CloneNotSupportedException e1) {
					// TODO Auto-generated catch block
					this.view.getMessage().setText(e1.getMessage());
				}
			}
			else if(pressedAbilty.getCastArea()==AreaOfEffect.DIRECTIONAL)
			{
				directionalCastAbility=true;
				this.view.getMessage().setText("please, choose a Direction to cast this ability");
			}
			else     //SINGLETARGET
			{
				singletargetCastAbility=true;
				this.view.getMessage().setText("please, choose a Champion to cast this ability");
			}
			this.view.getAbilityInfo().setVisible(false);
			this.view.getActions().setVisible(true);
			this.displayonBoard(this.game.getBoard());
			this.displayCurrentChampData(this.game.getCurrentChampion());
			//this.view.getMessage().setText("");
			this.displayChampions(this.game.getFirstPlayer().getTeam(), 1);
			this.displayChampions(this.game.getSecondPlayer().getTeam(), 2);
		}
		if(d.equals(this.view.getExitAbilityInfo()))
		{
			this.view.getAbilityInfo().setVisible(false);
			this.view.getCurrentAbilitiespnl().setVisible(true);
		}
		if((boardContains(this.game.getBoard(),d)) && singletargetCastAbility)
		{
			singletargetCastAbility=false;
			Champion c= chmpOfBtn(this.game.getBoard(),d);
			try {
				this.game.castAbility(pressedAbilty, c.getLocation().x, c.getLocation().y);
			} catch (NotEnoughResourcesException | AbilityUseException
					| InvalidTargetException | CloneNotSupportedException e1) {
				// TODO Auto-generated catch block
				this.view.getMessage().setText(e1.getMessage());
			}
		}
		if(d.equals(view.getCastleaderability())) {
			try {
				this.view.getMessage().setText("");
				this.game.useLeaderAbility();
			} catch (LeaderNotCurrentException | LeaderAbilityAlreadyUsedException e1) {
				this.view.getMessage().setText(e1.getMessage());
			}
			
		}
		if(d.equals(this.view.getEndturn())) {
			//this.view.getMessage().setText("da5al");
			
			
			if(game.getTurnOrder().size()==0)
				this.game.prepareChampionTurns();
			else
				this.game.endTurn();
			
				this.view.getMessage().setText("");
				this.displayCurrentChampData(this.game.getCurrentChampion());
				this.displayTurnOrder(this.game.getTurnOrder());
				this.displayonBoard(this.game.getBoard());	
	
			if(game.getFirstPlayer().getTeam().size()==0) {
				this.view.winningView();
				this.view.getWinner().setText("CONGRATULATIONS "+p2.getName());
				//this.view.getWinningpnl().setBackground(Color.BLUE);
			}
			if(game.getSecondPlayer().getTeam().size()==0) {
				this.view.winningView();
				this.view.getWinner().setText("CONGRATULATIONS "+p1.getName());
				//this.view.getWinningpnl().setBackground(Color.RED);
			}
			
		}	
	}
		else if (o instanceof JTextField) {
			JTextField t = (JTextField) o;
			if(t.equals(this.view.getNametext())) {
				setP1(playername(t)) ;
				}
			if(t.equals(this.view.getNametext2())) {
				setP2(playername(t)) ;
			}	
		}	
	}


	public void displayChampions(ArrayList<Champion> champions ,int a) {
		String champ = "";
		String p;
		if(a==1)
			p=p1.getName();
		else
			p=p2.getName();
		champ += "Available Champions In " + p + "'s Team Are:\n";
		champ += "'''''''''''\n";
		for (Champion addedChamp : champions) {
			champ += "The Champion's Name is: " + addedChamp.getName() + "\n"
					+ "Current HP = " + addedChamp.getCurrentHP() + "\n"
					+ "Mana = " + addedChamp.getMana() + "\n"
					+ "Speed = " + addedChamp.getSpeed() + "\n"
					+ "Max Actions Per Turn = " + addedChamp.getMaxActionPointsPerTurn() + "\n"	
					+ "Attack Damage = " + addedChamp.getAttackDamage() + "\n"
					+ "Attack Range = " + addedChamp.getAttackRange() + "\n";
			if(addedChamp instanceof Hero)
			{
				champ += "Type: Hero" +"\n";
			}
			else if(addedChamp instanceof AntiHero)
			{
				champ += "Type: AntiHero" +"\n";
			}
			else //if(addedChamp instanceof Villian)
			{
				champ += "Type: Villian" +"\n";
			}
			if(p1.getLeader() == addedChamp || p2.getLeader() == addedChamp)
				champ += "THIS IS THE LEADER.";
			else
				champ += "this is not the leader.";
			champ += "\n" + "AppliedEffects: " ;
			ArrayList<model.effects.Effect> effects = addedChamp.getAppliedEffects();
			for(int i=0;i<effects.size();i++)
			{
				champ += effects.get(i).getName() + " ,Duration: "+effects.get(i).getDuration()+" / ";
			}
			champ += "\n~~~~~~~~~~\n";
		}
		if(a==1)
			this.view.getChampionsData1().setText(champ);
		else 
			this.view.getChampionsData2().setText(champ);
	}
	public void displayTurnOrder(PriorityQueue t)
	{
		String order = "The Current Turn Order is: " + "\n";
		ArrayList<Champion> list = new ArrayList();
		while(!t.isEmpty())
		{
			order += ((Champion)t.peekMin()).getName() + ", ";
			list.add((Champion) t.remove());
		}
		for(int i=0;i<list.size();i++)
			t.insert(list.get(i));
		this.view.getShowTurnOrder().setText(order);
	}
	public void displayPlayer1Data(Player p,boolean l)
	{
		String data="Player-1 Name is: " + p1.getName() + "\n";
		if(l)
			data +="The Leader Ability Is USED" +"\n";
		else
			data +="The Leader Ability Is Still NOT USED";
				
		this.view.getPlayer1Data().setText(data);
		
	}
	public void displayPlayer2Data(Player p,boolean l)
	{
		String data="Player-2 Name is: " + p2.getName() + "\n";
		if(l)
			data +="The Leader Ability Is USED" +"\n";
		else
			data +="The Leader Ability Is Still NOT USED";
				
		this.view.getPlayer2Data().setText(data);
		
	}
	public void displayCurrentChampData(Champion c)
	{
		String data="Current Champion's Abilities are: " ;
		for(int i=0;i<c.getAbilities().size();i++)
			data += c.getAbilities().get(i).getName() + ", ";
		data += "\n" + "It's Current Action Points = " + c.getCurrentActionPoints();
		this.view.getCurrentChampData().setText(data);
		
	}
	
	public void displayonBoard(Object[][] c) {
				
		
		if(game.getFirstPlayer().getTeam().size()==0) {
			this.view.winningView();
			this.view.getWinner().setText("CONGRATULATIONS "+p2.getName());
		}
		if(game.getSecondPlayer().getTeam().size()==0) {
			this.view.winningView();
			this.view.getWinner().setText("CONGRATULATIONS "+p1.getName());
		}
		
		
		this.view.getBoard().removeAll(); 	
		JButton cell ;
		for(int i=4;i>=0;i--) {
			for(int j=0;j<5;j++) {
				Object myc = c[i][j] ;
				if(myc instanceof Cover) {
					cell =new JButton("Cover "+(" "+((Cover)myc).getCurrentHP())) ;
					cell.setName("Cover");
					cell.addActionListener(this);
					cell.setBackground(Color.GRAY);
					
				}
				else if(myc instanceof Champion) {
					Icon icon = championIcon(((Champion)c[i][j]).getName()) ;
					
					 cell =new JButton(icon) ;
					 cell.setName(((Champion)c[i][j]).getName());
					 cell.addActionListener(this);
//					 if(((Champion)c[i][j]).equals(this.game.getCurrentChampion()))
//						 cell.setForeground(Color.YELLOW);
//					 if(this.game.getFirstPlayer().getTeam().contains(((Champion)c[i][j]))) 
//						if(((Champion)c[i][j]).equals(this.game.getFirstPlayer().getLeader()))
//							cell.setBackground(Color.PINK);
//						else 
//							cell.setBackground(Color.RED);
//					 
//					 else if(this.game.getSecondPlayer().getTeam().contains(((Champion)c[i][j]))) 
//							if(((Champion)c[i][j]).equals(this.game.getSecondPlayer().getLeader()))
//								cell.setBackground(Color.CYAN);
//							else 
//								cell.setBackground(Color.BLUE);
					
					 
					 Champion turn = (Champion) this.game.getTurnOrder().peekMin() ; 
					 this.c = turn ;
					 if(((Champion)c[i][j]).equals(turn)) {
						 this.displayCurrentChampData(turn);
						 }
						
						 
					 }							
				
				else {
					//null
					 cell =new JButton("Empty") ;
					 cell.setName("Empty");
					 cell.setForeground(Color.black);
					 cell.addActionListener(this);
					 cell.setBackground(Color.white);
				}
			
				this.view.getBoard().add(cell);
				
			}
		}
		}
		
		
		
		
		
	
	
	
	
	public Player getP1() {
		return p1;
	}

	public void setP1(Player p1) {
		this.p1 = p1;
	}

	public Player getP2() {
		return p2;
	}

	public void setP2(Player p2) {
		this.p2 = p2;
	}

	private void startAction(JButton d) {
		
		this.view.secondpanel();
		view.getStart().addActionListener(this);
		ImageIcon icon1 = new ImageIcon("Al pic/background1.jpg") ;
		
		JLabel tmp = new JLabel(icon1);
		tmp.setVisible(true);
		tmp.setBounds(5,5, 1300,700);
		this.view.getChooseChampions().add(tmp);
		int x= 230;
		int y =70;	
		JTextField displayedMessage = new JTextField("Please select your Champions and THE FIRST SELECTED CHAMPION FOR EACH TEAM IS IT'S LEADER : ") ;
		displayedMessage.setBackground(Color.black);
		displayedMessage.setForeground(Color.white);
		displayedMessage.setVisible(true);
		displayedMessage.setBounds(10, 10, 600, 50);
		this.view.getChooseChampions().add(displayedMessage);
		displayedMessage.setEditable(false);
		for(int i=0 ;i<this.game.getAvailableChampions().size();i++) {
			Icon icon = championIcon(this.game.getAvailableChampions().get(i).getName()) ;
			JButton champion = new JButton(icon);
			champion.setName(this.game.getAvailableChampions().get(i).getName());
			champion.addActionListener(this);
			champion.setBounds(x, y, 150, 120);
			champion.setVisible(true);
			this.view.getChooseChampions().add(champion);
			if(i==4||i==9||i==14)
			{
				x=230 ;
				y+=120 ;
			}
			else x+=170 ;
		}
		
		
	}
	private Player playername(JTextField r) {
		if(r.getText().isEmpty())
		{
			Player player= new Player("...");
			return player;
		}
		else {
			Player player= new Player(r.getText());
			return player;
		}
	}
	
	
	
	
	
	//methods for model changes

	@Override
	public void onMove() {
		
		this.displayonBoard(this.game.getBoard());	
		this.displayCurrentChampData(this.game.getCurrentChampion());
		if(this.game.getCurrentChampion().getCurrentActionPoints()==0)
		{
			this.game.endTurn();
			this.displayonBoard(game.getBoard());
			this.displayCurrentChampData(this.game.getCurrentChampion());
			this.displayTurnOrder(this.game.getTurnOrder());
		}
	}

	public void setView(Gameview view) {
		this.view = view;
	}

	@Override
	public void onAttack() {
		System.out.print(c.getName()+"   "+c.getCurrentActionPoints());
		if(c.getCurrentActionPoints()==0||game.getCurrentChampion().getCurrentHP()==0)
		{
			this.game.endTurn();
			this.displayonBoard(game.getBoard());
			this.displayCurrentChampData(c);
			this.displayTurnOrder(this.game.getTurnOrder());
		}
		if(game.getFirstPlayer().getTeam().size()==0) {
			this.view.winningView();
			this.view.getWinner().setText("CONGRATULATIONS "+p2.getName());
		}
		if(game.getSecondPlayer().getTeam().size()==0) {
			this.view.winningView();
			this.view.getWinner().setText("CONGRATULATIONS "+p1.getName());
		}
		
	}
	
//	public void declaregamewinner() {
//		//new panel old panel set visibility to false add a winning image 
//		
//		
//		
//		
//		
//		
//		
//	}
	
	public void currentAbilitiesForCasting(){
		abilitiesBtn=new ArrayList<JButton>();
		ArrayList<Ability> a= this.game.getCurrentChampion().getAbilities();
		for(int i=0;i<a.size();i++)
		{
			JButton ability= new JButton(a.get(i).getName());
			ability.setName(a.get(i).getName());
			ability.addActionListener(this);
			abilitiesBtn.add(ability);
		}	 
		this.view.getActions().setVisible(false);
		abilitiespnl=new JPanel();
		abilitiespnl.setLayout(new GridLayout(0,4));
		abilitiespnl.setBounds(270,505,550,80);
		this.view.setCurrentAbilitiespnl(abilitiespnl,abilitiesBtn);
		abilitiespnl.setVisible(true);
		this.view.getTmp().add(abilitiespnl);
		
		
	}
	public Boolean boardContains(Object[][] gameBoard, JButton b){
		if(b.getText().equals("Cover"))
			return true;
		else if(b.getText().equals("Empty"))
			return true;
		else
		{
			String btnName= b.getName();
			for(int i=0; i<5; i++)
			{
				for (int j=0; j<5; j++)
				{
					Object curr=gameBoard [i][j];
					if(curr instanceof Champion)
					{
						if(btnName.equals(((Champion) curr).getName()))
							return true;
						
					}
				}
			} return false;
		}
	}
	public Champion chmpOfBtn(Object[][] gameBoard, JButton b){
		String btnName= b.getName();
		Champion shouldNotReturn= this.game.getCurrentChampion();
		for(int i=0; i<5; i++)
		{
			for (int j=0; j<5; j++)
			{
				Object curr=gameBoard [i][j];
				if(curr instanceof Champion)
				{
					if(btnName.equals(((Champion) curr).getName()))
					{
						return (Champion) curr;
					}
					
				}
			}
		} return shouldNotReturn;
	}

	@Override
	public void onCastAbilityDirec() {
		this.displayonBoard(this.game.getBoard());
		this.displayCurrentChampData(this.game.getCurrentChampion());
		this.displayChampions(this.game.getFirstPlayer().getTeam(),1);
		this.displayChampions(this.game.getSecondPlayer().getTeam(),2);
		if(this.c.getCurrentActionPoints()==0)
		{
			this.game.endTurn();
			this.displayCurrentChampData(c);
			this.displayonBoard(game.getBoard());
			this.displayTurnOrder(this.game.getTurnOrder());
			this.displayonBoard(game.getBoard());
		}
		if(game.getFirstPlayer().getTeam().size()==0) {
			this.view.winningView();
			this.view.getWinner().setText("CONGRATULATIONS "+p2.getName());
		}
		if(game.getSecondPlayer().getTeam().size()==0) {
			this.view.winningView();
			this.view.getWinner().setText("CONGRATULATIONS "+p1.getName());
		}
		
	}

	@Override
	public void onCastAbilityPoint() {
		this.displayonBoard(this.game.getBoard());
		this.displayCurrentChampData(this.game.getCurrentChampion());
		this.displayChampions(this.game.getFirstPlayer().getTeam(),1);
		this.displayChampions(this.game.getSecondPlayer().getTeam(),2);
		if(c.getCurrentActionPoints()==0)
		{
			this.game.endTurn();
			this.displayonBoard(game.getBoard());
			this.displayCurrentChampData(c);
			this.displayTurnOrder(this.game.getTurnOrder());
			this.displayonBoard(game.getBoard());
		}
		if(game.getFirstPlayer().getTeam().size()==0) {
			this.view.winningView();
			this.view.getWinner().setText("CONGRATULATIONS "+p2.getName());
		}
		if(game.getSecondPlayer().getTeam().size()==0) {
			this.view.winningView();
			this.view.getWinner().setText("CONGRATULATIONS "+p1.getName());
		}
		
	}
	
	
	
	
public Icon championIcon(String s) {
		
		Icon icon = null ;
		
		if(s.equals("Captain America")) {
			 icon = new ImageIcon ("Al pic/captain america.jpg") ;
		}
		if(s.equals("Deadpool")) {
			 icon = new ImageIcon ("Al pic/dead pool.jpg") ;
		}
		if(s.equals("Dr Strange")) {
			 icon = new ImageIcon ("Al pic/dr strange1.jpg") ;
		}
		if(s.equals("Electro")) {
			 icon = new ImageIcon ("Al pic/elctro.jpg") ;
		}
		if(s.equals("Ghost Rider")) {
			 icon = new ImageIcon ("Al pic/gost rider2.jpg") ;
		}
		if(s.equals("Hela")) {
			 icon = new ImageIcon ("Al pic/hela2.jpg") ;
		}
		if(s.equals("Hulk")) {
			 icon = new ImageIcon ("Al pic/hulk.jpg") ;
		}
		if(s.equals("Iceman")) {
			 icon = new ImageIcon ("Al pic/iceman.jpg") ;
		}
		if(s.equals("Ironman")) {
			 icon = new ImageIcon ("Al pic/ironman.jpg") ;
		}
		if(s.equals("Loki")) {
			 icon = new ImageIcon ("Al pic/loki.jpg") ;
		}
		if(s.equals("Quicksilver")) {
			 icon = new ImageIcon ("Al pic/quicksilver.jpg") ;
		}
		if(s.equals("Spiderman")) {
			 icon = new ImageIcon ("Al pic/spider man.jpg") ;
		}
		if(s.equals("Thor")) {
			 icon = new ImageIcon ("Al pic/thor.jpg") ;
		}
		if(s.equals("Venom")) {
			 icon = new ImageIcon ("Al pic/venom3.jpg") ;
		}
		if(s.equals("Yellow Jacket")) {
			 icon = new ImageIcon ("Al pic/yellow jacket.jpg") ;
		}
		return icon ;
	}	
	
	
	
	

	@Override
	public void onCastAbilityElse() {
		this.displayonBoard(this.game.getBoard());
		this.displayCurrentChampData(this.game.getCurrentChampion());
		this.displayChampions(this.game.getFirstPlayer().getTeam(),1);
		this.displayChampions(this.game.getSecondPlayer().getTeam(),2);
		if(c.getCurrentActionPoints()==0)
		{
			this.game.endTurn();
			this.displayonBoard(game.getBoard());
			this.displayCurrentChampData(c);
			this.displayTurnOrder(this.game.getTurnOrder());
			this.displayonBoard(game.getBoard());
		}
		if(game.getFirstPlayer().getTeam().size()==0) {
			this.view.winningView();
			this.view.getWinner().setText("CONGRATULATIONS "+p2.getName());
		}
		if(game.getSecondPlayer().getTeam().size()==0) {
			this.view.winningView();
			this.view.getWinner().setText("CONGRATULATIONS "+p1.getName());
		}
	}

	@Override
	public void onCastLeaderAbility() {
		
		this.displayonBoard(this.game.getBoard());
		this.displayCurrentChampData(this.game.getCurrentChampion());
		this.displayChampions(this.game.getFirstPlayer().getTeam(),1);
		this.displayChampions(this.game.getSecondPlayer().getTeam(),2);
		if(this.game.isFirstLeaderAbilityUsed())
			displayPlayer1Data(game.getFirstPlayer(),game.isFirstLeaderAbilityUsed());
		if(this.game.isSecondLeaderAbilityUsed())
			this.displayPlayer2Data(this.game.getSecondPlayer(),game.isSecondLeaderAbilityUsed());
		
		if(c.getCurrentActionPoints()==0)
		{
			this.game.endTurn();
			this.displayCurrentChampData(c);
			this.displayonBoard(game.getBoard());
			this.displayTurnOrder(this.game.getTurnOrder());
			this.displayonBoard(game.getBoard());
		}
		
		
		if(game.getFirstPlayer().getTeam().size()==0) {
			this.view.winningView();
			this.view.getWinner().setText("CONGRATULATIONS "+p2.getName());
		}
		if(game.getSecondPlayer().getTeam().size()==0) {
			this.view.winningView();
			this.view.getWinner().setText("CONGRATULATIONS "+p1.getName());
		}
		
	}
	
	
	
	
	public void displayAbilityInfo(Ability a)
	{
		String s = "";
		String t,t2;
		if(a instanceof DamagingAbility)
		{
			t = "Damage Ability";
			t2 = "damage amount = " + ((DamagingAbility) a).getDamageAmount();
		}
		else if(a instanceof HealingAbility)
		{
			t = "Heal Ability";
			t2 = "Heal amount = " + ((HealingAbility) a).getHealAmount();
		}
		else
		{
			t = "CrowdControl Ability";
			t2 = "effect is " + ((CrowdControlAbility) a).getEffect().getName() + " of duration " + ((CrowdControlAbility) a).getEffect().getDuration();
		}
		s += a.getName() + " Ability" + " is a " + t
				+"\n"+ "area of effect = " + a.getCastArea()+ ", cast Range = " + a.getCastRange()
				+"\n"+ "mana cost = " + a.getManaCost() + ", action cost = " + a.getRequiredActionPoints()
				+ "\n"+"current cooldown = " + a.getCurrentCooldown() + " and base cooldown = " + a.getBaseCooldown()
				+ "\n"+t2;
	
       this.view.getAbilityInfotxt().setText(s);
       this.view.getCurrentAbilitiespnl().setVisible(false);
       this.view.getAbilityInfotxt().setVisible(true);
       this.view.getUseAbility().setVisible(true);
       this.view.getExitAbilityInfo().setVisible(true);
       view.getTmp().add(view.getAbilityInfo());
       this.view.getAbilityInfo().setVisible(true);
       this.view.getExitAbilityInfo().addActionListener(this);
       this.view.getUseAbility().addActionListener(this);;
	}
	@Override
	public void onendturn() {
		
		view.getAbilityInfo().setVisible(false);
		if(view.getCurrentAbilitiespnl()!=null)
			view.getCurrentAbilitiespnl().setVisible(false);
		view.getActions().setVisible(true);
		
		
	}
}