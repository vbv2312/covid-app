package main;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URI;

import javax.swing.*;

class Myframe extends JFrame
{
  JPanel panel;
  JLabel label;
  JMenuBar menuBar;
  JMenu mnNewMenu;
  JSeparator separator;
  JMenuItem mntmNewMenuItem;
  JSeparator separator_4 ;
  JMenuItem mntmNewMenuItem_1;
  JSeparator separator_1;
  JMenuItem mntmNewMenuItem_2 ;
  JSeparator separator_2;
  JMenuItem mntmNewMenuItem_3;
  JSeparator separator_3;
  JMenuItem mntmNewMenuItem_4;
  // constructor
  Myframe( String title ) throws IOException,InterruptedException
  {
    super( title );                      // invoke the JFrame constructor
    setBounds(100, 100, 1369, 685);
  //  setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
    setJMenuBar(menuBar);
   setLayout(null);      // set the layout manager
   setVisible(true);
   // label = new JLabel("Hello Swing!");  // construct a JLabel
   // add( label );                        // add the label to the JFrame
    menuBar=new JMenuBar();
    menuBar.setBounds(0,0,1369,40);
    add(menuBar);
    mnNewMenu = new JMenu("Other Actions");
	mnNewMenu.setFont(new Font("Segoe UI", Font.BOLD, 33));
	menuBar.add(mnNewMenu);
	
	separator = new JSeparator();
	separator.setBackground(Color.WHITE);
	separator.setForeground(Color.LIGHT_GRAY);
	mnNewMenu.add(separator);
	
	mntmNewMenuItem = new JMenuItem("Covid Stats");
	mntmNewMenuItem.setFont(new Font("Segoe UI", Font.PLAIN, 33));
	mnNewMenu.add(mntmNewMenuItem);
	mntmNewMenuItem.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			try {
			new stat();
			}
			catch(IOException aaa){
				System.out.println("exception");
			}
			catch(InterruptedException aaa)
			{
				System.out.println("ee");
			}
		}
	});
	separator_4 = new JSeparator();
	separator_4.setForeground(Color.BLACK);
	mnNewMenu.add(separator_4);
	
	 mntmNewMenuItem_1 = new JMenuItem("Risk analysis");
	mntmNewMenuItem_1.setFont(new Font("Segoe UI", Font.PLAIN, 33));
	mnNewMenu.add(mntmNewMenuItem_1);
	
	 separator_1 = new JSeparator();
	mnNewMenu.add(separator_1);
	

	
	 mntmNewMenuItem_3 = new JMenuItem("Covid Helpline ");
	mntmNewMenuItem_3.setFont(new Font("Segoe UI", Font.PLAIN, 33));
	mnNewMenu.add(mntmNewMenuItem_3);
	mntmNewMenuItem_3.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			try {
			new covidhelpline();
			}
			catch(IOException aaa){
				System.out.println("exception");
			}
			catch(InterruptedException aaa)
			{
				System.out.println("ee");
			}
		}
	});
	
	separator_3 = new JSeparator();
	mnNewMenu.add(separator_3);
	
	 mntmNewMenuItem_4 = new JMenuItem("Favorite news articles");
	mntmNewMenuItem_4.setFont(new Font("Segoe UI", Font.PLAIN, 33));
	mnNewMenu.add(mntmNewMenuItem_4);
	mntmNewMenuItem_4.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			try {
			new favorite();
			}
			catch(IOException aaa){
				System.out.println("exception");
			}
			catch(InterruptedException aaa)
			{
				System.out.println("ee");
			}
		}
	});
	
     
  }
  public static void main ( String[] args ) throws  IOException,InterruptedException
  {
    Myframe frame = new Myframe("Hello"); // construct a MyFrame object
    frame.setVisible( true );             // ask it to become visible
  }
  }
