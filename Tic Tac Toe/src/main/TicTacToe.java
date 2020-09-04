package main;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.JButton;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.awt.Color;
import javax.swing.border.EtchedBorder;
import javax.swing.JMenuBar;
import javax.swing.border.MatteBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Cursor;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.LineBorder;
import javax.swing.JSeparator;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

public class TicTacToe {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TicTacToe window = new TicTacToe();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public TicTacToe() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	
	int count = 0,check1=0,check2=0,check3=0,check4=0,check5=0,check6=0,check7=0,check8=0,check9=0;
	int undo[] = new int[9];
	int index = -1, sound, undo_enable, settings_changed=0,theme=0;
	String player1,player2;
	private JButton button1;
	private JButton button2;
	private JButton button3;
	private JButton button4;
	private JButton button5;
	private JButton button6;
	private JButton button7;
	private JButton button8;
	private JButton button9;
	private JTextField player1_tf;
	private JTextField player2_tf;
	private JLabel player1_lbl;
	private JLabel player2_lbl;
	private Color background_color;
	private Color scoreboard_color;
	private Color button_color;
	private Color titleBorder_color;
	private Color button_fg_color;
	private Color title_color;
	private Color playerlbl_fg_color;
	private Color playerlbl_border_color;
	private Color playertf_bg_color;
	private Color playertf_border_color;
	private Color score_fg;
	private Color score_border;
	private Color score_panel_border;
	private Color gameoptions_btn_color;
	private Color winbutton_color;
	private Color gameoptions_btn_border_color;
	
	private void initialize() {
		
//		---------------------------------DATA---------------------------------
		
		Database database = new Database();
		String data[] = database.readFile();
		
		sound = Integer.parseInt(data[0]);
		undo_enable = Integer.parseInt(data[1]);
		player1 = data[2];
		player2 = data[3];
		theme = Integer.parseInt(data[4]);
		
		setTheme(theme);
		
//		---------------------------------FRAME---------------------------------
		
		frame = new JFrame();
		frame.setTitle("Tic Tac Toe");
		frame.setVisible(true);
		frame.getContentPane().setBackground(background_color);
		frame.setSize(920, 734);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
	
//		---------------------------------TITLE---------------------------------
		
		JPanel title_panel = new JPanel();
		title_panel.setBackground(background_color);
		title_panel.setBorder(new MatteBorder(2, 2, 2, 2, titleBorder_color));
		title_panel.setBounds(10, 11, 884, 110);
		frame.getContentPane().add(title_panel);
		title_panel.setLayout(null);
		
		JLabel title_lbl = new JLabel("TIC TAC TOE");
		title_lbl.setFont(new Font("Times New Roman", Font.BOLD, 40));
		title_lbl.setLabelFor(frame);
		title_lbl.setHorizontalAlignment(SwingConstants.CENTER);
		title_lbl.setBounds(2, 2, 882, 106);
		title_lbl.setForeground(title_color);
		title_panel.add(title_lbl);

//		---------------------------------SCORE BOARD---------------------------------
		
		JPanel score_board_panel = new JPanel();
		score_board_panel.setBackground(scoreboard_color);
		score_board_panel.setBounds(10, 132, 230, 528);
		score_board_panel.setBorder(new EtchedBorder(EtchedBorder.RAISED, score_panel_border, null));
		frame.getContentPane().add(score_board_panel);
		score_board_panel.setLayout(null);
		
		JLabel score_lbl = new JLabel("SCORE");
		score_lbl.setForeground(score_fg);
		score_lbl.setHorizontalAlignment(SwingConstants.CENTER);
		score_lbl.setFont(new Font("Trebuchet MS", Font.BOLD, 28));
		score_lbl.setBorder(new LineBorder(score_border, 3, true));
		score_lbl.setBounds(10, 11, 210, 62);
		score_board_panel.add(score_lbl);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(4, 84, 222, 2);
		score_board_panel.add(separator);
		
		player1_lbl = new JLabel();
		player1_lbl.setText(player1);
		player1_lbl.setForeground(playerlbl_fg_color);
		player1_lbl.setBorder(new EtchedBorder(EtchedBorder.RAISED, playerlbl_border_color, null));
		player1_lbl.setHorizontalAlignment(SwingConstants.CENTER);
		player1_lbl.setFont(new Font("Trebuchet MS", Font.BOLD, 28));
		player1_lbl.setBounds(10, 108, 210, 50);
		score_board_panel.add(player1_lbl);
		
		player2_lbl = new JLabel("Player 2");
		player2_lbl.setText(player2);
		player2_lbl.setForeground(playerlbl_fg_color);
		player2_lbl.setBorder(new EtchedBorder(EtchedBorder.RAISED, playerlbl_border_color, null));
		player2_lbl.setHorizontalAlignment(SwingConstants.CENTER);
		player2_lbl.setFont(new Font("Trebuchet MS", Font.BOLD, 28));
		player2_lbl.setBounds(10, 228, 210, 50);
		score_board_panel.add(player2_lbl);
		
		player1_tf = new JTextField("0");
		player1_tf.setBackground(playertf_bg_color);
		player1_tf.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, playertf_border_color, null, null, null));
		player1_tf.setHorizontalAlignment(SwingConstants.CENTER);
		player1_tf.setFont(new Font("Trebuchet MS", Font.BOLD, 22));
		player1_tf.setBounds(10, 169, 210, 45);
		player1_tf.setEditable(false);
		score_board_panel.add(player1_tf);
		
		player2_tf = new JTextField("0");
		player2_tf.setBackground(playertf_bg_color);
		player2_tf.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, playertf_border_color, null, null, null));
		player2_tf.setHorizontalAlignment(SwingConstants.CENTER);
		player2_tf.setFont(new Font("Trebuchet MS", Font.BOLD, 22));
		player2_tf.setBounds(10, 289, 210, 45);
		player2_tf.setEditable(false);
		score_board_panel.add(player2_tf);
		
		JButton newgame_btn = new JButton("NEW GAME");
		newgame_btn.setBorder(new LineBorder(gameoptions_btn_border_color));
		newgame_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(settings_changed==1)
				{
					readFile();
				}
				
				if(sound==1)
				{
					playSound(0);
				}
				reset();
			}
		});
		newgame_btn.setFont(new Font("Bodoni MT Black", Font.PLAIN, 14));
		newgame_btn.setBackground(gameoptions_btn_color);
		newgame_btn.setBounds(10, 345, 210, 50);
		score_board_panel.add(newgame_btn);
		
		JButton undo_btn = new JButton("UNDO");
		undo_btn.setBorder(new LineBorder(gameoptions_btn_border_color));
		undo_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(settings_changed==1)
				{
					readFile();
				}
				
				if(undo_enable==1)
				{
					if(sound==1)
					{
						playSound(5);
					}
					undoAction();
				}
			}
		});
		undo_btn.setFont(new Font("Bodoni MT Black", Font.PLAIN, 14));
		undo_btn.setBackground(gameoptions_btn_color);
		undo_btn.setBounds(10, 406, 210, 50);
		score_board_panel.add(undo_btn);
		
		JButton close_btn = new JButton("CLOSE");
		close_btn.setBorder(new LineBorder(gameoptions_btn_border_color));
		close_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		close_btn.setFont(new Font("Bodoni MT Black", Font.PLAIN, 14));
		close_btn.setBackground(gameoptions_btn_color);
		close_btn.setBounds(10, 467, 210, 50);
		score_board_panel.add(close_btn);
		
//		---------------------------------GAME PANEL---------------------------------
		
		JPanel game_panel = new JPanel();
		game_panel.setForeground(Color.WHITE);
		game_panel.setBackground(background_color);
		game_panel.setBounds(252, 132, 642, 528);
		frame.getContentPane().add(game_panel);
		
//		---------------------------------BUTTON 1---------------------------------
		
		button1 = new JButton("");
		button1.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		button1.setBounds(1, 1, 210, 172);
		button1.setFont(new Font("Arial Black", Font.BOLD, 85));
		button1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(settings_changed==1)
				{
					readFile();
				}
				
				if(count==-1)
				{
					reset();
				}
				else if(check1==0)
				{
					if(sound==1)
					{
						playSound(1);
					}
					count++;
					
					index++;
					undo[index] = 1;
					
					if(count%2!=0)
					{
						button1.setText("0");
						check1=1;
					}
					else if(count%2==0)
					{
						button1.setText("X");
						check1=2;
					}
					
					if(check1==check2 && check1==check3)
					{
						if(sound==1)
						{
							playSound(2);
						}
						button1.setBackground(winbutton_color);
						button2.setBackground(winbutton_color);
						button3.setBackground(winbutton_color);
						win(check1);
					}
					else if(check1==check4 && check1==check7)
					{
						if(sound==1)
						{
							playSound(2);
						}
						button1.setBackground(winbutton_color);
						button4.setBackground(winbutton_color);
						button7.setBackground(winbutton_color);
						win(check1);
					}
					else if(check1==check5 && check1==check9)
					{
						if(sound==1)
						{
							playSound(2);
						}
						button1.setBackground(winbutton_color);
						button5.setBackground(winbutton_color);
						button9.setBackground(winbutton_color);
						win(check1);
					}
					else if(count==9)
					{
						if(sound==1)
						{
							playSound(3);
						}
						reset();
					}
				}
				else if(check1!=0)
				{
					if(sound==1)
					{
						playSound(4);
					}
				}
			}
		});
		game_panel.setLayout(null);
		button1.setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
		button1.setBackground(button_color);
		button1.setForeground(button_fg_color);
		game_panel.add(button1);
		
//		---------------------------------BUTTON 2---------------------------------
		
		button2 = new JButton("");
		button2.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		button2.setBounds(216, 1, 210, 172);
		button2.setFont(new Font("Arial Black", Font.BOLD, 85));
		button2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(settings_changed==1)
				{
					readFile();
				}
				
				if(count==-1)
				{
					reset();
				}
				else if(check2==0)
				{
					if(sound==1)
					{
						playSound(1);
					}
					count++;
					
					index++;
					undo[index] = 2;
					
					if(count%2!=0)
					{
						button2.setText("0");
						check2=1;
					}
					else if(count%2==0)
					{
						button2.setText("X");
						check2=2;
					}
					
					if(check2==check1 && check2==check3)
					{
						if(sound==1)
						{
							playSound(2);
						}
						button1.setBackground(winbutton_color);
						button2.setBackground(winbutton_color);
						button3.setBackground(winbutton_color);
						win(check2);
					}
					else if(check2==check5 && check2==check8)
					{
						if(sound==1)
						{
							playSound(2);
						}
						button2.setBackground(winbutton_color);
						button5.setBackground(winbutton_color);
						button8.setBackground(winbutton_color);
						win(check2);
					}
					else if(count==9)
					{
						if(sound==1)
						{
							playSound(3);
						}
						reset();
					}
				}
				else if(check2!=0)
				{
					if(sound==1)
					{
						playSound(4);
					}
				}
			}
		});
		button2.setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
		button2.setBackground(button_color);
		button2.setForeground(button_fg_color);
		game_panel.add(button2);
		
//		---------------------------------BUTTON 3---------------------------------
		
		button3 = new JButton("");
		button3.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		button3.setBounds(431, 1, 210, 172);
		button3.setFont(new Font("Arial Black", Font.BOLD, 85));
		button3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(settings_changed==1)
				{
					readFile();
				}
				
				if(count==-1)
				{
					reset();
				}
				else if(check3==0)
				{
					if(sound==1)
					{
						playSound(1);
					}
					count++;
					
					index++;
					undo[index] = 3;
					
					if(count%2!=0)
					{
						button3.setText("0");
						check3=1;
					}
					else if(count%2==0)
					{
						button3.setText("X");
						check3=2;
					}
					
					if(check3==check1 && check3==check2)
					{
						if(sound==1)
						{
							playSound(2);
						}
						button1.setBackground(winbutton_color);
						button2.setBackground(winbutton_color);
						button3.setBackground(winbutton_color);
						win(check3);
					}
					else if(check3==check5 && check3==check7)
					{
						if(sound==1)
						{
							playSound(2);
						}
						button3.setBackground(winbutton_color);
						button5.setBackground(winbutton_color);
						button7.setBackground(winbutton_color);
						win(check3);
					}
					else if(check3==check6 && check3==check9)
					{
						if(sound==1)
						{
							playSound(2);
						}
						button3.setBackground(winbutton_color);
						button6.setBackground(winbutton_color);
						button9.setBackground(winbutton_color);
						win(check3);
					}
					else if(count==9)
					{
						if(sound==1)
						{
							playSound(3);
						}
						reset();
					}
				}
				else if(check3!=0)
				{
					if(sound==1)
					{
						playSound(4);
					}
				}
			}
		});
		button3.setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
		button3.setBackground(button_color);
		button3.setForeground(button_fg_color);
		game_panel.add(button3);
		
//		---------------------------------BUTTON 4---------------------------------
		
		button4 = new JButton("");
		button4.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		button4.setBounds(1, 178, 210, 172);
		button4.setFont(new Font("Arial Black", Font.BOLD, 85));
		button4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(settings_changed==1)
				{
					readFile();
				}
				
				if(count==-1)
				{
					reset();
				}
				else if(check4==0)
				{
					if(sound==1)
					{
						playSound(1);
					}
					count++;
					
					index++;
					undo[index] = 4;
					
					if(count%2!=0)
					{
						button4.setText("0");
						check4=1;
					}
					else if(count%2==0)
					{
						button4.setText("X");
						check4=2;
					}
					
					if(check4==check1 && check4==check7)
					{
						if(sound==1)
						{
							playSound(2);
						}
						button1.setBackground(winbutton_color);
						button4.setBackground(winbutton_color);
						button7.setBackground(winbutton_color);
						win(check4);
					}
					else if(check4==check5 && check4==check6)
					{
						if(sound==1)
						{
							playSound(2);
						}
						button4.setBackground(winbutton_color);
						button5.setBackground(winbutton_color);
						button6.setBackground(winbutton_color);
						win(check4);
					}
					else if(count==9)
					{
						if(sound==1)
						{
							playSound(3);
						}
						reset();
					}
				}
				else if(check4!=0)
				{
					if(sound==1)
					{
						playSound(4);
					}
				}
			}
		});
		button4.setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
		button4.setBackground(button_color);
		button4.setForeground(button_fg_color);
		game_panel.add(button4);
		
//		---------------------------------BUTTON 5---------------------------------
		
		button5 = new JButton("");
		button5.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		button5.setBounds(216, 178, 210, 172);
		button5.setFont(new Font("Arial Black", Font.BOLD, 85));
		button5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(settings_changed==1)
				{
					readFile();
				}
				
				if(count==-1)
				{
					reset();
				}
				else if(check5==0)
				{
					if(sound==1)
					{
						playSound(1);
					}
					count++;
					
					index++;
					undo[index] = 5;
					
					if(count%2!=0)
					{
						button5.setText("0");
						check5=1;
					}
					else if(count%2==0)
					{
						button5.setText("X");
						check5=2;
					}
					
					if(check5==check1 && check5==check9)
					{
						if(sound==1)
						{
							playSound(2);
						}
						button1.setBackground(winbutton_color);
						button5.setBackground(winbutton_color);
						button9.setBackground(winbutton_color);
						win(check5);
					}
					else if(check5==check3 && check5==check7)
					{
						if(sound==1)
						{
							playSound(2);
						}
						button3.setBackground(winbutton_color);
						button5.setBackground(winbutton_color);
						button7.setBackground(winbutton_color);
						win(check5);
					}
					else if(check5==check4 && check5==check6)
					{
						if(sound==1)
						{
							playSound(2);
						}
						button4.setBackground(winbutton_color);
						button5.setBackground(winbutton_color);
						button6.setBackground(winbutton_color);
						win(check5);
					}
					else if(check5==check2 && check5==check8)
					{
						if(sound==1)
						{
							playSound(2);
						}
						button2.setBackground(winbutton_color);
						button5.setBackground(winbutton_color);
						button8.setBackground(winbutton_color);
						win(check5);
					}
					else if(count==9)
					{
						if(sound==1)
						{
							playSound(3);
						}
						reset();
					}
				}
				else if(check5!=0)
				{
					if(sound==1)
					{
						playSound(4);
					}
				}
			}
		});
		button5.setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
		button5.setBackground(button_color);
		button5.setForeground(button_fg_color);
		game_panel.add(button5);
		
//		---------------------------------BUTTON 6---------------------------------
		
		button6 = new JButton("");
		button6.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		button6.setBounds(431, 178, 210, 172);
		button6.setFont(new Font("Arial Black", Font.BOLD, 85));
		button6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(settings_changed==1)
				{
					readFile();
				}
				
				if(count==-1)
				{
					reset();
				}
				else if(check6==0)
				{
					if(sound==1)
					{
						playSound(1);
					}
					count++;
					
					index++;
					undo[index] = 6;
					
					if(count%2!=0)
					{
						button6.setText("0");
						check6=1;
					}
					else if(count%2==0)
					{
						button6.setText("X");
						check6=2;
					}
					if(check6==check4 && check6==check5)
					{
						if(sound==1)
						{
							playSound(2);
						}
						button4.setBackground(winbutton_color);
						button5.setBackground(winbutton_color);
						button6.setBackground(winbutton_color);
						win(check6);
					}
					else if(check6==check3 && check6==check9)
					{
						if(sound==1)
						{
							playSound(2);
						}
						button3.setBackground(winbutton_color);
						button6.setBackground(winbutton_color);
						button9.setBackground(winbutton_color);
						win(check6);
					}
					else if(count==9)
					{
						if(sound==1)
						{
							playSound(3);
						}
						reset();
					}
				}
				else if(check6!=0)
				{
					if(sound==1)
					{
						playSound(4);
					}
				}
			}
		});
		button6.setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
		button6.setBackground(button_color);
		button6.setForeground(button_fg_color);
		game_panel.add(button6);
		
//		---------------------------------BUTTON 7---------------------------------
		
		button7 = new JButton("");
		button7.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		button7.setBounds(1, 355, 210, 172);
		button7.setFont(new Font("Arial Black", Font.BOLD, 85));
		button7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(settings_changed==1)
				{
					readFile();
				}
				
				if(count==-1)
				{
					reset();
				}
				else if(check7==0)
				{
					if(sound==1)
					{
						playSound(1);
					}
					count++;
					
					index++;
					undo[index] = 7;
					
					if(count%2!=0)
					{
						button7.setText("0");
						check7=1;
					}
					else if(count%2==0)
					{
						button7.setText("X");
						check7=2;
					}
					
					if(check7==check1 && check7==check4)
					{
						if(sound==1)
						{
							playSound(2);
						}
						button1.setBackground(winbutton_color);
						button4.setBackground(winbutton_color);
						button7.setBackground(winbutton_color);
						win(check7);
					}
					else if(check7==check3 && check7==check5)
					{
						if(sound==1)
						{
							playSound(2);
						}
						button3.setBackground(winbutton_color);
						button5.setBackground(winbutton_color);
						button7.setBackground(winbutton_color);
						win(check7);
					}
					else if(check7==check8 && check7==check9)
					{
						if(sound==1)
						{
							playSound(2);
						}
						button7.setBackground(winbutton_color);
						button8.setBackground(winbutton_color);
						button9.setBackground(winbutton_color);
						win(check7);
					}
					else if(count==9)
					{
						if(sound==1)
						{
							playSound(3);
						}
						reset();
					}
				}
				else if(check7!=0)
				{
					if(sound==1)
					{
						playSound(4);
					}
				}
			}
		});
		button7.setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
		button7.setBackground(button_color);
		button7.setForeground(button_fg_color);
		game_panel.add(button7);
		
//		---------------------------------BUTTON 8---------------------------------
		
		button8 = new JButton("");
		button8.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		button8.setBounds(216, 355, 210, 172);
		button8.setFont(new Font("Arial Black", Font.BOLD, 85));
		button8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(settings_changed==1)
				{
					readFile();
				}
				
				if(count==-1)
				{
					reset();
				}
				else if(check8==0)
				{
					if(sound==1)
					{
						playSound(1);
					}
					count++;
					
					index++;
					undo[index] = 8;
					
					if(count%2!=0)
					{
						button8.setText("0");
						check8=1;
					}
					else if(count%2==0)
					{
						button8.setText("X");
						check8=2;
					}
					
					if(check8==check2 && check8==check5)
					{
						if(sound==1)
						{
							playSound(2);
						}
						button2.setBackground(winbutton_color);
						button5.setBackground(winbutton_color);
						button8.setBackground(winbutton_color);
						win(check8);
					}
					else if(check8==check7 && check8==check9)
					{
						if(sound==1)
						{
							playSound(2);
						}
						button7.setBackground(winbutton_color);
						button8.setBackground(winbutton_color);
						button9.setBackground(winbutton_color);
						win(check8);
					}
					else if(count==9)
					{
						if(sound==1)
						{
							playSound(3);
						}
						reset();
					}
				}
				else if(check8!=0)
				{
					if(sound==1)
					{
						playSound(4);
					}
				}
			}
		});
		button8.setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
		button8.setBackground(button_color);
		button8.setForeground(button_fg_color);
		game_panel.add(button8);
		
//		---------------------------------BUTTON 9---------------------------------
		
		button9 = new JButton("");
		button9.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		button9.setBounds(431, 355, 210, 172);
		button9.setFont(new Font("Arial Black", Font.BOLD, 85));
		button9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(settings_changed==1)
				{
					readFile();
				}
				
				if(count==-1)
				{
					reset();
				}
				else if(check9==0)
				{
					if(sound==1)
					{
						playSound(1);
					}
					count++;
					
					index++;
					undo[index] = 9;
					
					if(count%2!=0)
					{
						button9.setText("0");
						check9=1;
					}
					else if(count%2==0)
					{
						button9.setText("X");
						check9=2;
					}
					
					if(check9==check1 && check9==check5)
					{
						if(sound==1)
						{
							playSound(2);
						}
						button1.setBackground(winbutton_color);
						button5.setBackground(winbutton_color);
						button9.setBackground(winbutton_color);
						win(check9);
					}
					else if(check9==check3 && check9==check6)
					{
						if(sound==1)
						{
							playSound(2);
						}
						button3.setBackground(winbutton_color);
						button5.setBackground(winbutton_color);
						button7.setBackground(winbutton_color);
						win(check9);
					}
					else if(check9==check7 && check9==check8)
					{
						if(sound==1)
						{
							playSound(2);
						}
						button7.setBackground(winbutton_color);
						button8.setBackground(winbutton_color);
						button9.setBackground(winbutton_color);
						win(check9);
					}
					else if(count==9)
					{
						if(sound==1)
						{
							playSound(3);
						}
						reset();
					}
				}
				else if(check9!=0)
				{
					if(sound==1)
					{
						playSound(4);
					}
				}
			}
		});
		button9.setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
		button9.setBackground(button_color);
		button9.setForeground(button_fg_color);
		game_panel.add(button9);
		
//		---------------------------------MENU BAR---------------------------------
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu mnGame = new JMenu("Game");
		menuBar.add(mnGame);
		
		JMenuItem mntmNewGame = new JMenuItem("New Game");
		mntmNewGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(settings_changed==1)
				{
					readFile();
				}
				
				if(sound==1)
				{
					playSound(0);
				}
				reset();
			}
		});
		mnGame.add(mntmNewGame);
		
		JMenuItem mntmOptions = new JMenuItem("Options");
		mntmOptions.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Settings settings = new Settings();
				settings_changed = settings.changeSettings(frame);
			}
		});
		mnGame.add(mntmOptions);
		
		JMenuItem mntmRedo = new JMenuItem("Redo");
		mntmRedo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(settings_changed==1)
				{
					readFile();
				}
				
				if(undo_enable==1)
				{
					if(sound==1)
					{
						playSound(5);
					}
					redoAction();
				}
			}
		});
		
		JMenuItem mntmUndo = new JMenuItem("Undo");
		mntmUndo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(settings_changed==1)
				{
					readFile();
				}
				
				if(undo_enable==1)
				{
					if(sound==1)
					{
						playSound(5);
					}
					undoAction();
				}
			}
		});
		
		JSeparator separator_1 = new JSeparator();
		mnGame.add(separator_1);
		mnGame.add(mntmUndo);
		mnGame.add(mntmRedo);
		
		JMenuItem mntmExit = new JMenuItem("Exit");
		mntmExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		
		JMenuItem mntmReset = new JMenuItem("Reset");
		mntmReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				reset();
				player1_tf.setText("0");
				player2_tf.setText("0");
			}
		});
		mnGame.add(mntmReset);
		
		JSeparator separator_2 = new JSeparator();
		mnGame.add(separator_2);
		mnGame.add(mntmExit);
		
		JMenu mnHelp = new JMenu("Help");
		menuBar.add(mnHelp);
		
		JMenuItem mntmHowToPlay = new JMenuItem("How To Play?");
		mnHelp.add(mntmHowToPlay);
		
		JSeparator separator_3 = new JSeparator();
		mnHelp.add(separator_3);
		
		JMenuItem mntmAboutTicTac = new JMenuItem("About Tic Tac Toe");
		mnHelp.add(mntmAboutTicTac);
		
		if(sound==1)
		{
			playSound(0);
		}
	}

	private void setTheme(int theme)
	{
		if(theme==0)
		{
			background_color = Color.BLACK;
			scoreboard_color = Color.MAGENTA;
			button_color = Color.CYAN;
			button_fg_color = Color.RED;
			winbutton_color = new Color(0, 0, 255);
			titleBorder_color = new Color(255, 51, 51);
			title_color = new Color(0, 255, 0);
			playerlbl_fg_color = new Color(0, 0, 128);
			playerlbl_border_color = new Color(0, 255, 0);
			playertf_bg_color = new Color(238, 130, 238);
			playertf_border_color = new Color(255, 0, 0);
			score_fg = new Color(0, 0, 255);
			score_border = new Color(0, 191, 255);
			score_panel_border = new Color(0, 0, 255);
			gameoptions_btn_border_color = new Color(0, 100, 0);
			gameoptions_btn_color = new Color(0, 255, 127);
		}
		else if(theme==1)
		{
			background_color = new Color(255, 255, 224);
			scoreboard_color = new Color(238, 232, 170);
			button_color = new Color(144, 238, 144);
			button_fg_color = new Color(218, 112, 214);
			winbutton_color = new Color(34, 139, 34);
			titleBorder_color = new Color(218, 112, 214);
			title_color = new Color(128, 0, 0);
			playerlbl_fg_color = Color.MAGENTA;
			playerlbl_border_color = Color.MAGENTA;
			score_fg = new Color(0, 128, 0);
			score_border = new Color(135, 206, 250);
			score_panel_border = new Color(0, 0, 255);
			gameoptions_btn_border_color = new Color(0, 100, 0);
			gameoptions_btn_color = new Color(0, 255, 0);
		}
	}
	
	private void reset()
	{
		count=check1=check2=check3=check4=check5=check6=check7=check8=check9=0;
		index=-1;
		
		button1.setText("");
		button2.setText("");
		button3.setText("");
		button4.setText("");
		button5.setText("");
		button6.setText("");
		button7.setText("");
		button8.setText("");
		button9.setText("");
		
		button1.setBackground(button_color);
		button2.setBackground(button_color);
		button3.setBackground(button_color);
		button4.setBackground(button_color);
		button5.setBackground(button_color);
		button6.setBackground(button_color);
		button7.setBackground(button_color);
		button8.setBackground(button_color);
		button9.setBackground(button_color);
	}
	
	public void readFile()
	{
		Database database = new Database();
		String data[] = database.readFile();
		
		sound = Integer.parseInt(data[0]);
		undo_enable = Integer.parseInt(data[1]);
		player1 = data[2];
		player2 = data[3];
		
		player1_lbl.setText(player1);
		player2_lbl.setText(player2);
		
		reset();
		
		settings_changed=0;
	}
	
	private void undoAction()
	{
		if(index==-1)
		{
			//do nothing
		}
		else if(undo[index]==1)
		{
			index--;
			count--;
			button1.setText("");
			check1=0;
		}
		else if(undo[index]==2)
		{
			index--;
			count--;
			button2.setText("");
			check2=0;
		}
		else if(undo[index]==3)
		{
			index--;
			count--;
			button3.setText("");
			check3=0;
		}
		else if(undo[index]==4)
		{
			index--;
			count--;
			button4.setText("");
			check4=0;
		}
		else if(undo[index]==5)
		{
			index--;
			count--;
			button5.setText("");
			check5=0;
		}
		else if(undo[index]==6)
		{
			index--;
			count--;
			button6.setText("");
			check6=0;
		}
		else if(undo[index]==7)
		{
			index--;
			count--;
			button7.setText("");
			check7=0;
		}
		else if(undo[index]==8)
		{
			index--;
			count--;
			button8.setText("");
			check8=0;
		}
		else if(undo[index]==9)
		{
			index--;
			count--;
			button9.setText("");
			check9=0;
		}
	}
	
	private void redoAction()
	{
		index++;
		
		if(index==-1)
		{
			//do nothing
		}
		else if(undo[index]==1)
		{
			count++;
			
			if(count%2!=0)
			{
				button1.setText("0");
				check1=1;
			}
			else if(count%2==0)
			{
				button1.setText("X");
				check1=2;
			}
		}
		else if(undo[index]==2)
		{
			count++;
			
			if(count%2!=0)
			{
				button2.setText("0");
				check2=1;
			}
			else if(count%2==0)
			{
				button2.setText("X");
				check2=2;
			}
		}
		else if(undo[index]==3)
		{
			count++;
			
			if(count%2!=0)
			{
				button3.setText("0");
				check3=1;
			}
			else if(count%2==0)
			{
				button3.setText("X");
				check3=2;
			}
		}
		else if(undo[index]==4)
		{
			count++;
			
			if(count%2!=0)
			{
				button4.setText("0");
				check4=1;
			}
			else if(count%2==0)
			{
				button4.setText("X");
				check4=2;
			}
		}
		else if(undo[index]==5)
		{
			count++;
			
			if(count%2!=0)
			{
				button5.setText("0");
				check5=1;
			}
			else if(count%2==0)
			{
				button5.setText("X");
				check5=2;
			}
		}
		else if(undo[index]==6)
		{
			count++;
			
			if(count%2!=0)
			{
				button6.setText("0");
				check6=1;
			}
			else if(count%2==0)
			{
				button6.setText("X");
				check6=2;
			}
		}
		else if(undo[index]==7)
		{
			count++;
			
			if(count%2!=0)
			{
				button7.setText("0");
				check7=1;
			}
			else if(count%2==0)
			{
				button7.setText("X");
				check7=2;
			}
		}
		else if(undo[index]==8)
		{
			count++;
			
			if(count%2!=0)
			{
				button8.setText("0");
				check8=1;
			}
			else if(count%2==0)
			{
				button8.setText("X");
				check8=2;
			}
		}
		else if(undo[index]==9)
		{
			count++;
			
			if(count%2!=0)
			{
				button9.setText("0");
				check9=1;
			}
			else if(count%2==0)
			{
				button9.setText("X");
				check9=2;
			}
		}
	}
	
	private void win(int check)
	{
		index=-1;
		if(check==1)
		{
			player1_tf.setText(Integer.parseInt(player1_tf.getText()) + 1 + "");
		}
		else if(check==2)
		{
			player2_tf.setText(Integer.parseInt(player2_tf.getText()) + 1 + "");
		}
		
		count=-1;
	}
	
	private void playSound(int sound)
	{
		if(sound==0)
		{
			File start = new File("START.WAV");
			try 
			{
				Clip clip = AudioSystem.getClip();
				clip.open(AudioSystem.getAudioInputStream(start));
				clip.start();
			} 
			catch (IOException | UnsupportedAudioFileException | LineUnavailableException e1) 
			{}
		}
		else if(sound==1)
		{
			File click1 = new File("CLICK_1.WAV");
			try 
			{
				Clip clip = AudioSystem.getClip();
				clip.open(AudioSystem.getAudioInputStream(click1));
				clip.start();
			} 
			catch (IOException | UnsupportedAudioFileException | LineUnavailableException e1) 
			{}
		}
		else if(sound==2)
		{
			File clap = new File("CLAP.WAV");
			try 
			{
				Clip clip = AudioSystem.getClip();
				clip.open(AudioSystem.getAudioInputStream(clap));
				clip.start();
			} 
			catch (IOException | UnsupportedAudioFileException | LineUnavailableException e1) 
			{}
		}
		else if(sound==3)
		{
			File irritating = new File("IRRITATING.WAV");
			try 
			{
				Clip clip = AudioSystem.getClip();
				clip.open(AudioSystem.getAudioInputStream(irritating));
				clip.start();
			} 
			catch (IOException | UnsupportedAudioFileException | LineUnavailableException e1) 
			{}
		}
		else if(sound==4)
		{
			File wrong = new File("WRONG.WAV");
			try 
			{
				Clip clip = AudioSystem.getClip();
				clip.open(AudioSystem.getAudioInputStream(wrong));
				clip.start();
			} 
			catch (IOException | UnsupportedAudioFileException | LineUnavailableException e1) 
			{}
		}
		else if(sound==5)
		{
			File click2 = new File("CLICK_1.WAV");
			try 
			{
				Clip clip = AudioSystem.getClip();
				clip.open(AudioSystem.getAudioInputStream(click2));
				clip.start();
			} 
			catch (IOException | UnsupportedAudioFileException | LineUnavailableException e1) 
			{}
		}
	}
}