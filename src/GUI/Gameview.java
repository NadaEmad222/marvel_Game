package GUI;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.Label;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.BorderFactory;
import javax.swing.ComboBoxModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import engine.Game;
import model.abilities.Ability;
import model.world.AntiHero;
import model.world.Champion;
import model.world.Hero;

public class Gameview extends JFrame{  //i am a frame
	
	private JPanel gamepanel;
	private JButton namefill  ; 
	private JTextField nametext2 ;
	private JTextField nametext;
	private JPanel chooseChampions;
	private JLabel displayChosenChamp ;
	private JButton start ;
	private JPanel boardpnl;
	private JTextArea championsData1;
	private JTextArea championsData2 ;
	private JTextArea showTurnOrder;
	private JTextArea player1Data;
	private JTextArea player2Data;
	private JTextArea currentChampData;
	private JButton moveU  ;
	private JButton moveD  ;
	private JButton moveR  ;
	private JButton moveL  ;
    private JPanel board ;
    private JPanel movebuttons ;
    private JLabel message ;
	private JTextArea abilities ;	
	private JPanel actions ;
	private JPanel currentAbilitiespnl;
    
	private JPanel abilityInfo;
	private JTextArea abilityInfotxt;
	private JButton attack ;
	private JButton castleaderability ;
	private JButton castability ;
	private JButton endturn ;
	//private JLabel winner ;
	private JButton exitAbilityInfo;
	private JButton useAbility;
	
	private JPanel winningpnl;
	private JLabel winner;
	private JLabel finalImgPnl ;
	
	private JTextArea allChamp;
	private JLabel tmp ;
	
	public Gameview() throws HeadlessException {
		super();
		this.setBounds(0,0,1500, 800);	
		this.setTitle("new Marvel Game "); 
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		
		
//		 ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("C:\\Users\\HP\\eclipse-workspace\\Marvel-M2\\Marvel Studios Intro � New (2021)_Trim.mp4"));
//	     Image i2 = i1.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
//	     ImageIcon i3 = new ImageIcon(i2);
//		 winner = new JLabel(i3);
//		 winner.setBounds(0,0,1500, 800);
		 
		gamepanel = new JPanel();	
		gamepanel.setLayout(null);
		gamepanel.setBounds(0, 0, 1500, 800);
		this.add(gamepanel);
		
		
//		ImageIcon icon1 = new ImageIcon("Al pic/marvel.jpg") ;
//		
//		JLabel tmp = new JLabel(icon1);
//		tmp.setVisible(true);
//		tmp.setBounds(0,0, 1600,900);
//		gamepanel.add(tmp);
		
//		ImageIcon icon = new ImageIcon("") ;
//		JLabel toti = new JLabel(icon) ;
		
		Label label = new Label("Player1 name : ") ;
		label.setFont(new Font("Verdana", Font.PLAIN, 18));
		label.setVisible(true);
		label.doLayout();
		label.setEnabled(true);
		label.setBounds(410,250,130,40);
		gamepanel.add(label);
		
		nametext = new JTextField();
		nametext.setBounds(550,250,210,40);
		nametext.addActionListener(null);
		gamepanel.add(nametext);
		nametext.setName("Player1");
		
		Label label2 = new Label("Player2 name : ") ;
		label2.setBounds(410,320,130,40);
		label2.setFont(new Font("Verdana", Font.PLAIN, 18));
		label2.doLayout();
		label2.setEnabled(true);
		label2.setVisible(true);
		gamepanel.add(label2);
		
		nametext2 = new JTextField();
		nametext2.setBounds(550,320,210,40);
		gamepanel.add(nametext2);
		nametext2.setName("Player2");
		
		 namefill = new JButton("go to the next step :)");
		namefill.setName("start");
		namefill.setFont(new Font("Verdana", Font.PLAIN, 18));
		namefill.setVisible(true);
		namefill.setBounds(510, 430, 260, 50) ;
		gamepanel.add(namefill);
		
		allChamp = new JTextArea();
		allChamp.setBounds(0,135,1500,600);
		allChamp.setVisible(true);
		allChamp.setEditable(false);
		allChamp.setFont(new Font(Font.MONOSPACED, Font.BOLD, 10));
		//gamepanel.add(allChamp);
		
		this.setVisible(true);
		//ImageIcon icon = new ImageIcon("file:///C:/Users/nadae/eclipse-workspace/Marvel-M2/Start_img.jpg");
		//Icon icon = new ImageIcon("E:\\editicon.PNG");
//		ImageIcon icon = new ImageIcon("file:///C:/Users/nadae/eclipse-workspace/Marvel-M2/Start_img.jpg");
		 
//		 try {
//			    Image img = ImageIO.read(getClass().getResource("/Marvel-M2/src/pngaaa.com-50682.png"));
//			    img.getScaledInstance(200,50, DO_NOTHING_ON_CLOSE) ;
//			   // namefill.setIcon(new ImageIcon(img));
//			    namefill.setIcon((Icon) img);
//			  } catch (Exception ex) {
//			    System.out.println(ex);
//			  }
		
		
	}

	
	public JButton getMoveU() {
		return moveU;
	}


	public JButton getMoveD() {
		return moveD;
	}


	public JButton getMoveR() {
		return moveR;
	}


	public JButton getMoveL() {
		return moveL;
	}


	public void secondpanel() {
		this.gamepanel.setVisible(false);
		
		chooseChampions = new JPanel();
		chooseChampions.setName("panel2");
		chooseChampions.setLayout(null);
		chooseChampions.setVisible(true);
		this.add(chooseChampions);
		
		JLabel tmp = new JLabel("<html>hoof with the cursor <html><br/>" +" for more Champions") ;
		tmp.setBounds(20, 70, 200, 100);
		tmp.setVisible(true);
		tmp.setBackground(Color.black);
		tmp.setForeground(Color.white);
		chooseChampions.add(tmp) ;
		
		this.displayChosenChamp = new JLabel();
		displayChosenChamp.setBounds(20,300,200,300);
		displayChosenChamp.setVisible(true);
		displayChosenChamp.setLayout(null);
		chooseChampions.add(displayChosenChamp);
		
		this.start = new JButton("Let's Start :)") ;
		start.setForeground(Color.white);
		start.setBackground(Color.black);
		this.start.setName("s");
		start.setBounds(500, 500, 350,50) ;
		chooseChampions.add(start);
	}

	
	public void setChooseChampions(JPanel chooseChampions) {
		this.chooseChampions = chooseChampions;
	}


	public JButton getStart() {
		return start;
	}


	public void boardView()
	{
		// setVisible of the previous to false and this one to true
		this.chooseChampions.setVisible(false);
		
		boardpnl = new JPanel();
		
		boardpnl = new JPanel();
		boardpnl.setName("panel3");
		boardpnl.setLayout(null);	
		boardpnl.setBounds(0,0,1920, 1080);
		boardpnl.setVisible(true);
		
		ImageIcon icon = new ImageIcon("Al pic/background1.jpg") ;
		tmp = new JLabel(icon);
		tmp.setBounds(5,5, 1300, 700);
		boardpnl.add(tmp);
		
		this.add(boardpnl);
		
		championsData1 = new JTextArea();
		championsData1.setBackground(Color.PINK);
		//championsData1.setPreferredSize(new Dimension(200, getHeight()));
		championsData1.setBounds(5,95,250,545);
		championsData1.setVisible(true);
		championsData1.setEditable(false);
		championsData1.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 10));
		tmp.add(championsData1);
		
		championsData2 = new JTextArea();
		championsData2.setBackground(Color.CYAN);
		//championsData2.setPreferredSize(new Dimension(200, getHeight()));
		championsData2.setBounds(1010,95,250,545);
		championsData2.setVisible(true);
		championsData2.setEditable(false);
		championsData2.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 10));
		tmp.add(championsData2);
		
		showTurnOrder = new JTextArea();
		showTurnOrder.setBounds(270, 10, 705, 50);
		showTurnOrder.setVisible(true);
		showTurnOrder.setEditable(false);
		showTurnOrder.setForeground(Color.white);
		showTurnOrder.setBackground(Color.black);
		showTurnOrder.setFont(new Font(Font.MONOSPACED, Font.BOLD, 14));
		tmp.add(showTurnOrder);
		
		player1Data = new JTextArea();
		player1Data.setBounds(5,10,250,70);
		player1Data.setVisible(true);
		player1Data.setEditable(false);
		player1Data.setForeground(Color.RED);
		player1Data.setFont(new Font(Font.MONOSPACED, Font.BOLD, 11));
		tmp.add(player1Data);
		
		player2Data = new JTextArea();
		player2Data.setBounds(1010,10,250,70);
		player2Data.setEditable(false);
		player2Data.setVisible(true);
		player2Data.setForeground(Color.BLUE);
		player2Data.setFont(new Font(Font.MONOSPACED, Font.BOLD, 11));
		tmp.add(player2Data);
		
		currentChampData = new JTextArea();
		currentChampData.setBounds(270,600,730,40);
		currentChampData.setEditable(false);
		currentChampData.setForeground(Color.white);
		currentChampData.setBackground(Color.black);
		currentChampData.setVisible(true);
		//currentChampData.setVisible(true);
		currentChampData.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));
		tmp.add(currentChampData);
		
		board = new JPanel();
		board.setLayout(new GridLayout(5,5));
		board.setBounds(270,70,715,400);
		board.setOpaque(true);
		board.setVisible(true);
		tmp.add(board);
		

		movebuttons = new JPanel() ;
		movebuttons.setBounds(840,480,150,70);
		movebuttons.setBackground(Color.BLACK);
		//movebuttons.setLayout(new BorderLayout(3,2));
		tmp.add(movebuttons) ;
		
		moveL = new JButton("<");
		moveL.setName("LEFT");
		//moveL.setBounds(1390, 720, 100, 50) ;
		movebuttons.add(moveL);
		
		moveU = new JButton("^");
		moveU.setName("UP");
		//moveU.setBounds(1390, 670, 200, 50) ;
		movebuttons.add(moveU);
		
		moveR = new JButton(">");
		moveR.setName("RIGHT");

		movebuttons.add(moveR);
		
		moveD = new JButton("v");
		moveD.setName("DOWN");
		//moveD.setBounds(1390, 770, 200, 50) ;
		movebuttons.add(moveD);
		
		message = new JLabel();
		message.setBounds(280, 415,400,150);
		message.setLayout(null);
		message.setForeground(Color.GREEN);
		message.setFont(new Font(Font.MONOSPACED, Font.ITALIC, 12));
		tmp.add(message);
		
		
		actions = new JPanel();	
		actions.setLayout(new GridLayout(1,3));
		actions.setBounds(270,505,550,80);
		//board.setOpaque(true);
		actions.setVisible(true);
		tmp.add(actions);
		
		endturn = new JButton("END TURN ") ;
		endturn.setName("endturn");
		actions.add(endturn);
		
		castleaderability = new JButton("LEADER ABILITY") ;
		castleaderability.setName("castleaderability");
		actions.add(castleaderability) ;
		
		castability = new JButton("CAST ABILITY") ;
		castability.setName("castability");
		actions.add(castability) ;
		
		attack = new JButton("ATTACK");
		attack.setName("attack");
		actions.add(attack);
		
		abilityInfo = new JPanel();	
		abilityInfo.setBounds(230,505,550,80);
		//board.setOpaque(true);
		//abilityInfo.setVisible(false);
		//boardpnl.add(abilityInfo);
		
		abilityInfotxt= new JTextArea();
		abilityInfotxt.setBounds(230,505,550,60);
		abilityInfotxt.setEditable(false);
		abilityInfotxt.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 10));
		abilityInfotxt.setVisible(false);
		abilityInfo.add(abilityInfotxt);
		
		
		useAbility = new JButton("Use");
		useAbility.setName("useAbility");
		useAbility.setBounds(270,565,270,20) ;
		useAbility.setVisible(false);
		abilityInfo.add(useAbility);
		
		exitAbilityInfo= new JButton("Close");
		exitAbilityInfo.setName("returnToGame");
		exitAbilityInfo.setBounds(270,840,270,20) ;
		exitAbilityInfo.setVisible(false);
		abilityInfo.add(exitAbilityInfo);
		
	}
	public JLabel getTmp() {
		return tmp;
	}


	public void setTmp(JLabel tmp) {
		this.tmp = tmp;
	}


	public void winningView()
	{
        this.boardpnl.setVisible(false);
        
        ImageIcon icon = new ImageIcon("Al pic/marvel-intro1.gif") ;
        icon.setImage(icon.getImage().getScaledInstance(1300, 700, 0));
        JLabel l = new JLabel(icon);
        l.setBounds(5, 5, 1300, 700);
        
        winningpnl = new JPanel();
        winningpnl.setName("gameOver");
        winningpnl.setLayout(null);	
        winningpnl.setBounds(0,0,1920, 1080);
        winningpnl.setVisible(true);
        
        winningpnl.add(l);
        
        winner = new JLabel();
        winner.setFont(new Font(Font.MONOSPACED, Font.BOLD, 36));
        winner.setForeground(Color.cyan);
        winner.setBounds(400,250,500, 500);
        l.add(winner) ;
		this.add(winningpnl,BorderLayout.CENTER);
		
		
//		Icon icon= new ImageIcon("location");
//		finalImgPnl = new JLabel(icon);
//		finalImgPnl.setBounds(110, 20,1100,450);
//		winningpnl.add(finalImgPnl);

		
		
		
	}
    
	
	public JPanel getWinningpnl() {
		return winningpnl;
	}


	public JLabel getWinner() {
		return winner;
	}


	public JPanel getActions() {
		return actions;
	}


	public void setActions(JPanel actions) {
		this.actions = actions;
	}


	public JButton getAttack() {
		return attack;
	}


	public void setAttack(JButton attack) {
		this.attack = attack;
	}


	public JButton getCastleaderability() {
		return castleaderability;
	}


	public void setCastleaderability(JButton castleaderability) {
		this.castleaderability = castleaderability;
	}


	public JButton getCastability() {
		return castability;
	}


	public void setCastability(JButton castability) {
		this.castability = castability;
	}


	public JButton getEndturn() {
		return endturn;
	}


	public void setEndturn(JButton endturn) {
		this.endturn = endturn;
	}


//	public JComboBox<JButton> getAbilitieslist() {
//		return abilitieslist;
//	}
//
//
//	public void setAbilitieslist(JComboBox<JButton> abilitieslist) {
//		this.abilitieslist = abilitieslist;
//	}


	public JTextArea getAbilities() {
		return abilities;
	}


	public void setAbilities(JTextArea abilities) {
		this.abilities = abilities;
	}


	public JPanel getBoard() {
		return board;
	}


	public void setBoard(JPanel board) {
		this.board = board;
	}


	public JPanel getBoardpnl() {
		return boardpnl;
	}


	public void setBoardpnl(JPanel boardpnl) {
		this.boardpnl = boardpnl;
	}


	public JTextArea getPlayer1Data() {
		return player1Data;
	}


	public JTextArea getPlayer2Data() {
		return player2Data;
	}


	public JLabel getDisplayChosenChamp() {
		return displayChosenChamp;
	}

	public JTextArea getChampionsData1() {
		return championsData1;
	}
	public JTextArea getChampionsData2() {
		return championsData2;
	}
	

	public JTextArea getCurrentChampData() {
		return currentChampData;
	}


	public void setDisplayChosenChamp(JLabel displayChosenChamp) {
		this.displayChosenChamp = displayChosenChamp;
	}

	public JButton getNamefill() {
		return namefill;
	}

	public JPanel getChooseChampions() {
		return chooseChampions;
	}

	public void setAllChamp(JTextArea allChamp) {
		this.allChamp = allChamp;
	}


	public JPanel getGamepanel() {
		return gamepanel;
	}

	public JButton getButton() {
		return namefill;
	}
	public JTextField getNametext2() {
		return nametext2;
	}

	public JTextField getNametext() {
		return nametext;
	}

	public String getName1() {
		return nametext.getText();
	}
	public String getName2() {
		return nametext2.getText();
	}
	public JTextArea getShowTurnOrder() {
		return showTurnOrder;
	}
	public JLabel getMessage() {
		return message;
	}

	public void setMessage(JLabel message) {
		this.message = message;
	}
	public JPanel getCurrentAbilitiespnl() {
		return currentAbilitiespnl;
	}


	public void setCurrentAbilitiespnl(JPanel currentAbilitiespnl,ArrayList<JButton> a) {
		this.currentAbilitiespnl = currentAbilitiespnl;
		for(int i=0; i<a.size(); i++)
		{
			currentAbilitiespnl.add(a.get(i));
		}
	}


	public JPanel getAbilityInfo() {
		return abilityInfo;
	}


	public JButton getExitAbilityInfo() {
		return exitAbilityInfo;
	}


	public JButton getUseAbility() {
		return useAbility;
	}


	public JTextArea getAbilityInfotxt() {
		return abilityInfotxt;
	}
	public JTextArea getAllChamp() {
		return allChamp;
	}
	
}
