package main;

import java.awt.EventQueue;
import javax.swing.*;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JMenuBar;
import javax.swing.JSeparator;
import java.awt.Font;
import javax.swing.JTextPane;
import java.awt.*;
import java.net.*;
import com.google.gson.Gson;

import java.awt.Color;
import javax.swing.JTextArea;
import java.awt.Label;
import java.awt.List;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import javax.swing.JList;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.*;
import com.google.gson.Gson;
import javax.swing.JScrollPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.border.LineBorder;
import javax.swing.GroupLayout;
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

	DefaultListModel dlm=new DefaultListModel();
	System.out.println("asd"+d.articles.size());
for(int i=0;i<d.articles.size();i++)
{
	news item= d.articles.get(i);
	title[i]=new JLabel(item.title+"---"+item.description);
	add[i]=item.url;
dlm.addElement(item.title + "------"+item.description);
System.out.println(i);
}
list.setModel(dlm);
list.addMouseListener(new MouseAdapter() {
	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getModifiers()==MouseEvent.BUTTON3_MASK)
		{
			System.out.println("gm");
		}
		try {
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
});
	}
	}




