package main;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.io.*;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import com.google.gson.Gson;
import javax.swing.JScrollPane;
import javax.swing.JTable;
public class hospitalbeds {

	public Myframe frame;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					hospitalbeds window = new hospitalbeds();
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
	public hospitalbeds()  throws IOException,InterruptedException {
		frame = new Myframe("hospital beds");
		
		JLabel lblNewLabel = new JLabel("Statewise Hospital beds data");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblNewLabel.setBounds(10, 51, 1345, 82);
		frame.getContentPane().add(lblNewLabel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 162, 1335, 476);
		frame.getContentPane().add(scrollPane);
		DefaultTableModel dtm=new DefaultTableModel();
		table = new JTable(dtm)	{
			public boolean isCellEditable(int row,int column)
			{
				return false;
			}
				};
		dtm.addColumn("State");		
		dtm.addColumn("Rural Hospitals");
		dtm.addColumn("Rural Beds");
		dtm.addColumn("Urban Hospitals");
		dtm.addColumn("Urban Beds");
		dtm.addColumn("Total Hospitals");
		dtm.addColumn("Total Beds");

		scrollPane.setViewportView(table);
	var url="https://api.rootnet.in/covid19-in/hospitals/beds";
		
		var request=HttpRequest.newBuilder().GET().uri(URI.create(url)).build();
var client=HttpClient.newBuilder().build();
var response = client.send(request,HttpResponse.BodyHandlers.ofString());
String jsonString = response.body().toString(); 
System.out.println(jsonString);
Gson gson=new Gson();
hosp d=gson.fromJson(jsonString, hosp.class);
	for(int i=0;i<d.data.regional.size();i++)
	{
		dtm.insertRow(i,new Object[] {d.data.regional.get(i).state,d.data.regional.get(i).ruralHospitals,d.data.regional.get(i).ruralBeds,d.data.regional.get(i).urbanHospitals,d.data.regional.get(i).urbanBeds,d.data.regional.get(i).totalHospitals,d.data.regional.get(i).totalBeds});
	}
		
	}
}
