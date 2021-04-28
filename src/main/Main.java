package main;
import java.awt.EventQueue;
import java.sql.*;
import javax.swing.*;

import java.awt.Font;
import java.awt.*;
import java.net.*;
import com.google.gson.Gson;

import java.awt.Color;
import java.awt.Label;
import java.awt.List;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.*;
import com.google.gson.Gson;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.border.LineBorder;
import javax.swing.GroupLayout.Alignment;
import javax.swing.border.CompoundBorder;
import javax.imageio.*;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import net.miginfocom.swing.MigLayout;
import javax.swing.border.BevelBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Main {
	 // JDBC driver name and database URL
	   static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";  
	   static final String DB_URL = "jdbc:mysql://localhost/history";

	   //  Database credentials
	   static final String USER = "root";
	   static final String PASS = "Vaibhav207*";
	   Connection conn;
	   PreparedStatement stmt;
	public Myframe frame;
	
	JPanel panel = new JPanel();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main window = new Main();
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
	public Main() throws IOException,InterruptedException{
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	public void initialize() throws IOException,InterruptedException{
		frame = new Myframe("op");
		frame.setBounds(100, 100, 1369, 685);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		Label label = new Label("Track-a-cov");
		label.setFont(new Font("Dialog", Font.PLAIN, 40));
		label.setAlignment(Label.CENTER);
		label.setBounds(0, 40, 1355, 86);
		frame.getContentPane().add(label);
		
		Label label_1 = new Label("Quick news flash");
		label_1.setAlignment(Label.CENTER);
		

		label_1.setFont(new Font("Dialog", Font.PLAIN, 24));
		label_1.setBounds(10, 121, 1345, 48);
		frame.getContentPane().add(label_1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 172, 1355, 425);
		frame.getContentPane().add(scrollPane);
		
		JList list = new JList();
		list.addListSelectionListener(null); //editing disable
		scrollPane.setViewportView(list);
		
		
		list.setLayoutOrientation(JList.VERTICAL);
var url="http://newsapi.org/v2/top-headlines?country=in&category=health&apiKey=edff4d078a2542d4b93e189524f86d41";
		var request=HttpRequest.newBuilder().GET().uri(URI.create(url)).build();
var client=HttpClient.newBuilder().build();
var response = client.send(request,HttpResponse.BodyHandlers.ofString());

String jsonString = response.body().toString();  
Gson gson=new Gson();
data d=gson.fromJson(jsonString, data.class);


	JLabel title[] = new JLabel[d.articles.size()];
String add[]=new String[d.articles.size()];
String tit[]=new String[d.articles.size()];
String desc[]=new String[d.articles.size()];

	DefaultListModel dlm=new DefaultListModel();
	System.out.println("asd"+d.articles.size());
for(int i=0;i<d.articles.size();i++)
{
	news item= d.articles.get(i);
	tit[i]=item.title;
	desc[i]=item.description;
	title[i]=new JLabel(item.title+"---"+item.description);
	add[i]=item.url;
dlm.addElement(item.title + "------"+item.description);
System.out.println(i);
}
list.setModel(dlm);
list.addMouseListener(new MouseAdapter() {
	@Override
	public void mouseClicked(MouseEvent e) {
	
			 String[] buttons = { "Open in Browser", "SAVE" };
			 int rc = JOptionPane.showOptionDialog(null, "What u wanna do", "Confirmation",
				        JOptionPane.WARNING_MESSAGE, 0, null, buttons, buttons[1]);
	if(rc==0)
		{	try {
			  Desktop desktop = java.awt.Desktop.getDesktop();
			  if(list.getSelectedIndex()>=0)
			  {
			  URI oURL = new URI(add[list.getSelectedIndex()]);
			  desktop.browse(oURL);
			  }
			} catch (Exception f) {
			  f.printStackTrace();
			}
		}
	else
	{
		try {
	      conn = DriverManager.getConnection(DB_URL, USER, PASS);
		 String sql = "insert into history.favs values(?,?,?)";
	      System.out.println("f");
	      stmt = conn.prepareStatement(sql);
	      stmt.setString(1,add[list.getSelectedIndex()]);
	      stmt.setString(2,tit[list.getSelectedIndex()]);
	      stmt.setString(3,desc[list.getSelectedIndex()]);
	      stmt.executeUpdate();

	}
		catch(SQLException se){
		      //Handle errors for JDBC
		      se.printStackTrace();
		   }
	}
	 
	
		
		
		
		
	
	
	}
});
	}
	}




