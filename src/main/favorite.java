package main;

import java.awt.EventQueue;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;
import main.Myframe;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JList;
import javax.swing.JOptionPane;
public class favorite {
	 // JDBC driver name and database URL
	   static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";  
	   static final String DB_URL = "jdbc:mysql://localhost/history";

	   //  Database credentials
	   static final String USER = "root";
	   static final String PASS = "Vaibhav207*";
	   Connection conn;
	   PreparedStatement stmt;
	public Myframe frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					favorite window = new favorite();
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
	public favorite()  throws IOException,InterruptedException{
		
			frame = new Myframe("favs");
			
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setBounds(10, 175, 1335, 463);
			frame.getContentPane().add(scrollPane);
			
			
			 Connection conn = null;
			 ResultSet rst=null;
			   PreparedStatement stmt = null;
			   try {
			 conn = DriverManager.getConnection(DB_URL, USER, PASS); //used from jdbc example
			  rst=null;
			  String sql="Select * from history.favs";
			  stmt=conn.prepareStatement(sql);
			  rst=stmt.executeQuery();
			  System.out.println(rst);
			
				  DefaultTableModel dtm=new DefaultTableModel();
				   JTable table;

					table = new JTable(dtm)	{
						public boolean isCellEditable(int row,int column)
						{
							return false;
						}
							};
					
					dtm.addColumn("title");
					dtm.addColumn("description");
			scrollPane.setViewportView(table);
			int i=0;
			if(rst.next()!=false)
			{
				
			System.out.println("asd");
			
			String urlv[]=new String[100];
			while(rst.next())
			{
				String sa=rst.getString("title");
           	 String cci=rst.getString("desc");
           urlv[i]=rst.getString("url");
           System.out.println("FF");
           dtm.insertRow(i,new Object[] {sa,cci});
           i++;
			}
			}
			  }
			   catch(SQLException se){
				      //Handle errors for JDBC
				      se.printStackTrace();
				   }
			
			}
}
