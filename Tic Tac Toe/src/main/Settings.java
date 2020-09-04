package main;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JSeparator;
import javax.swing.JRadioButton;

public class Settings extends JFrame 
{
	private JPanel contentPane;
	private JTextField player1_tf;
	private JTextField player2_tf;

	int sound,undo,theme,theme_1;
	String player1,player2;
	
	public int changeSettings(JFrame frame)
	{
		Database database = new Database();
		String data[] = database.readFile();
		
		sound = Integer.parseInt(data[0]);
		undo = Integer.parseInt(data[1]);
		player1 = data[2];
		player2 = data[3];
		theme = Integer.parseInt(data[4]);
		
		setVisible(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(588, 300);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
//		---------------------------------OPTIONS---------------------------------
		
		JLabel options_lbl = new JLabel("OPTIONS");
		options_lbl.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 16));
		options_lbl.setHorizontalAlignment(SwingConstants.CENTER);
		options_lbl.setBounds(0, 0, 572, 42);
		contentPane.add(options_lbl);
		
//		---------------------------------COMPUTER OR TWO PLAYERS---------------------------------
		
		JRadioButton rdbtnComputer = new JRadioButton("Computer");
		rdbtnComputer.setFont(new Font("Trebuchet MS", Font.PLAIN, 13));
		rdbtnComputer.setBounds(22, 56, 111, 23);
		rdbtnComputer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				player1_tf.setEditable(false);
				player2_tf.setEditable(false);
			}
		});
		contentPane.add(rdbtnComputer);
		
		JRadioButton rdbtnTwoPlayers = new JRadioButton("Two Players");
		rdbtnTwoPlayers.setFont(new Font("Trebuchet MS", Font.PLAIN, 13));
		rdbtnTwoPlayers.setBounds(150, 56, 123, 23);
		rdbtnTwoPlayers.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				player1_tf.setEditable(true);
				player2_tf.setEditable(true);
			}
		});
		contentPane.add(rdbtnTwoPlayers);
		
		ButtonGroup player_btngrp = new ButtonGroup();
		player_btngrp.add(rdbtnComputer);
		player_btngrp.add(rdbtnTwoPlayers);
		
		rdbtnTwoPlayers.setSelected(true);
		
//		---------------------------------PLAYERS---------------------------------
		
		JLabel player1_lbl = new JLabel("Player 1");
		player1_lbl.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 14));
		player1_lbl.setHorizontalAlignment(SwingConstants.CENTER);
		player1_lbl.setBounds(10, 110, 111, 30);
		contentPane.add(player1_lbl);
		
		JLabel player2_lbl = new JLabel("Player 2");
		player2_lbl.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 14));
		player2_lbl.setHorizontalAlignment(SwingConstants.CENTER);
		player2_lbl.setBounds(10, 157, 111, 30);
		contentPane.add(player2_lbl);
		
		player1_tf = new JTextField(player1);
		player1_tf.setHorizontalAlignment(SwingConstants.CENTER);
		player1_tf.setBounds(131, 109, 130, 36);
		contentPane.add(player1_tf);
		player1_tf.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char c=e.getKeyChar();
				if(!(Character.isAlphabetic(c)  || Character.isDigit(c) || (c==KeyEvent.VK_BACK_SPACE) || (c==KeyEvent.VK_DELETE) || Character.isDigit(c)))
				{
					e.consume();
				}
			}
		});
		
		player2_tf = new JTextField(player2);
		player2_tf.setHorizontalAlignment(SwingConstants.CENTER);
		player2_tf.setBounds(131, 156, 130, 36);
		contentPane.add(player2_tf);
		player2_tf.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char c=e.getKeyChar();
				if(!(Character.isAlphabetic(c)  || Character.isDigit(c) || (c==KeyEvent.VK_BACK_SPACE) || (c==KeyEvent.VK_DELETE) || Character.isDigit(c)))
				{
					e.consume();
				}
			}
		});
		
//		---------------------------------SOUND---------------------------------	
		
		JLabel sound_lbl = new JLabel("Sound");
		sound_lbl.setHorizontalAlignment(SwingConstants.CENTER);
		sound_lbl.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 14));
		sound_lbl.setBounds(10, 206, 111, 55);
		contentPane.add(sound_lbl);
		
		JRadioButton rdbtnYes = new JRadioButton("Yes");
		rdbtnYes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sound=1;
			}
		});
		rdbtnYes.setFont(new Font("Trebuchet MS", Font.PLAIN, 13));
		rdbtnYes.setBounds(131, 223, 70, 23);
		contentPane.add(rdbtnYes);
		
		JRadioButton rdbtnNo = new JRadioButton("No");
		rdbtnNo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sound=0;
			}
		});
		rdbtnNo.setFont(new Font("Trebuchet MS", Font.PLAIN, 13));
		rdbtnNo.setBounds(203, 223, 70, 23);
		contentPane.add(rdbtnNo);
		
		ButtonGroup sound_btngrp = new ButtonGroup();
		sound_btngrp.add(rdbtnYes);
		sound_btngrp.add(rdbtnNo);
		
		if(sound==1)
		{
			rdbtnYes.setSelected(true);
		}
		else if(sound==0)
		{
			rdbtnNo.setSelected(true);
		}
		
//		---------------------------------UNDO---------------------------------
		
		
		JLabel undo_lbl = new JLabel("Undo");
		undo_lbl.setHorizontalAlignment(SwingConstants.CENTER);
		undo_lbl.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 14));
		undo_lbl.setBounds(291, 47, 94, 42);
		contentPane.add(undo_lbl);
		
		JRadioButton rdbtnEnable = new JRadioButton("Enable");
		rdbtnEnable.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				undo=1;
			}
		});
		rdbtnEnable.setFont(new Font("Trebuchet MS", Font.PLAIN, 13));
		rdbtnEnable.setBounds(391, 56, 70, 23);
		contentPane.add(rdbtnEnable);
		
		JRadioButton rdbtnDisable = new JRadioButton("Disable");
		rdbtnDisable.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				undo=0;
			}
		});
		rdbtnDisable.setFont(new Font("Trebuchet MS", Font.PLAIN, 13));
		rdbtnDisable.setBounds(482, 56, 70, 23);
		contentPane.add(rdbtnDisable);
		
		if(undo==1)
		{
			rdbtnEnable.setSelected(true);
		}
		else if(undo==0)
		{
			rdbtnDisable.setSelected(true);
		}
		
		ButtonGroup undo_btngrp = new ButtonGroup();
		undo_btngrp.add(rdbtnEnable);
		undo_btngrp.add(rdbtnDisable);
		
//		---------------------------------THEME---------------------------------
		
		JLabel theme_lbl = new JLabel("Theme");
		theme_lbl.setHorizontalAlignment(SwingConstants.CENTER);
		theme_lbl.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 14));
		theme_lbl.setBounds(291, 94, 281, 36);
		contentPane.add(theme_lbl);
		
		JRadioButton rdbtnDarkTheme = new JRadioButton("DARK THEME");
		rdbtnDarkTheme.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				theme_1=0;
			}
		});
		rdbtnDarkTheme.setFont(new Font("Trebuchet MS", Font.PLAIN, 13));
		rdbtnDarkTheme.setBounds(299, 137, 162, 23);
		contentPane.add(rdbtnDarkTheme);
		
		JRadioButton rdbtnLightTheme = new JRadioButton("LIGHT THEME");
		rdbtnLightTheme.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				theme_1=1;
			}
		});
		rdbtnLightTheme.setFont(new Font("Trebuchet MS", Font.PLAIN, 13));
		rdbtnLightTheme.setBounds(299, 185, 162, 23);
		contentPane.add(rdbtnLightTheme);
		
		if(theme==0)
		{
			rdbtnDarkTheme.setSelected(true);
		}
		else if(theme==1)
		{
			rdbtnLightTheme.setSelected(true);
		}
		
		ButtonGroup theme_btngrp = new ButtonGroup();
		theme_btngrp.add(rdbtnDarkTheme);
		theme_btngrp.add(rdbtnLightTheme);
		
//		---------------------------------BUTTONS---------------------------------
		
		JButton apply_btn = new JButton("Apply");																				//APPLY BUTTON
		apply_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				database.createFile();
				database.addRecords(sound, undo, player1_tf.getText(), player2_tf.getText(), theme_1);
				database.closeFile();
				
				if(theme!=theme_1)
				{
					frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
					frame.dispose();
					new TicTacToe();
				}
			}
		});
		apply_btn.setBounds(352, 224, 89, 23);
		contentPane.add(apply_btn);
		
		JButton cancel_btn = new JButton("Cancel");																				//CANCEL BUTTON
		cancel_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		cancel_btn.setBounds(463, 224, 89, 23);
		contentPane.add(cancel_btn);
		
//		---------------------------------SEPARATORS---------------------------------
		
		JSeparator separator = new JSeparator();
		separator.setBounds(0, 47, 633, 2);
		contentPane.add(separator);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setOrientation(SwingConstants.VERTICAL);
		separator_1.setBounds(291, 47, 2, 214);
		contentPane.add(separator_1);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setBounds(0, 206, 293, 2);
		contentPane.add(separator_2);
		
		JSeparator separator_3 = new JSeparator();
		separator_3.setBounds(0, 94, 293, 2);
		contentPane.add(separator_3);
		
		JSeparator separator_4 = new JSeparator();
		separator_4.setBounds(291, 94, 342, 2);
		contentPane.add(separator_4);
		
		return 1;
	}
}
