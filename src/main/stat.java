package main;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

import com.google.gson.Gson;
import javax.swing.JButton;

public class stat {

	public Myframe frame;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args)throws IOException,InterruptedException {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					stat window = new stat();
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
	public stat()throws IOException,InterruptedException {
		frame = new Myframe("lalith");
		
		JLabel lblNewLabel = new JLabel("State wise covid data");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(384, 71, 660, 99);
		frame.getContentPane().add(lblNewLabel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 167, 1335, 414);
		frame.getContentPane().add(scrollPane);
		DefaultTableModel dtm=new DefaultTableModel();
		table = new JTable(dtm)	{
			public boolean isCellEditable(int row,int column)
			{
				return false;
			}
				};
		dtm.addColumn("location");
		dtm.addColumn("Confirmed indian cases");
		dtm.addColumn("Confirmed foreign cases");
		dtm.addColumn("discharged");
		dtm.addColumn("deaths");
dtm.addColumn("total confirmed");
var url="https://api.rootnet.in/covid19-in/stats/latest";

var request=HttpRequest.newBuilder().GET().uri(URI.create(url)).build();
var client=HttpClient.newBuilder().build();
var response = client.send(request,HttpResponse.BodyHandlers.ofString());
//System.out.println(response.statusCode());
//System.out.println(response.body());
String jsonString = response.body().toString(); 
//System.out.println(jsonString);
Gson gson=new Gson();
coviddata d=new coviddata();
d=gson.fromJson(jsonString, coviddata.class);
		scrollPane.setViewportView(table);
		url="https://api.rootnet.in/covid19-in/stats/testing/latest";

		 request=HttpRequest.newBuilder().GET().uri(URI.create(url)).build();
	 client=HttpClient.newBuilder().build();
		 response = client.send(request,HttpResponse.BodyHandlers.ofString());
		 jsonString=response.body().toString();
		 System.out.println(jsonString);
		testing t=gson.fromJson(jsonString,testing.class);
		JLabel lblNewLabel_1 = new JLabel("total samples tested->"+t.data.totalSamplesTested);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1.setBounds(0, 42, 328, 54);
		frame.getContentPane().add(lblNewLabel_1);
		
		JButton btnNewButton = new JButton("hospital beds");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					new hospitalbeds();
					}
					catch(IOException aa){
						System.out.println("exception");
					}
					catch(InterruptedException aa)
					{
						System.out.println("ee");
					}
			}
		});
		btnNewButton.setBounds(375, 591, 189, 21);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Previous data");
		btnNewButton_1.setBounds(1107, 88, 172, 21);
		frame.getContentPane().add(btnNewButton_1);
		frame.setVisible(true);
		for(int i=0;i<d.data.regional.size();i++)
		{
			dtm.insertRow(i,new Object[] {d.data.regional.get(i).loc,d.data.regional.get(i).confirmedCasesIndian,d.data.regional.get(i).confirmedCasesForeign,d.data.regional.get(i).discharged,d.data.regional.get(i).deaths,d.data.regional.get(i).totalConfirmed});
		}
	
	}
}
