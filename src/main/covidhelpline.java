package main;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import com.google.gson.Gson;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.*;
public class covidhelpline {

	public Myframe frame;
	private JTable table;
	private JScrollPane scrollPane_1;
	private JTable table_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					covidhelpline window = new covidhelpline();
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
	public covidhelpline() throws IOException,InterruptedException{
		frame=new Myframe("ggwp");
		
		JLabel lblNewLabel = new JLabel("Helpline numbers");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 32));
		lblNewLabel.setBounds(10, 55, 452, 100);
		frame.getContentPane().add(lblNewLabel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 162, 466, 476);
		frame.getContentPane().add(scrollPane);
		DefaultTableModel dtm=new DefaultTableModel();
		table = new JTable(dtm)
				{
			public boolean isCellEditable(int row,int column)
			{
				return false;
			}
				};
		dtm.addColumn("State");
		dtm.addColumn("Number");
		scrollPane.setViewportView(table);
		
		scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(501, 163, 844, 475);
		frame.getContentPane().add(scrollPane_1);
		
	
		
		JLabel lblNewLabel_1 = new JLabel("ADVISORIES");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 32));
		lblNewLabel_1.setBounds(501, 78, 844, 75);
		frame.getContentPane().add(lblNewLabel_1);
var url="https://api.rootnet.in/covid19-in/contacts";
		
		var request=HttpRequest.newBuilder().GET().uri(URI.create(url)).build();
var client=HttpClient.newBuilder().build();
var response = client.send(request,HttpResponse.BodyHandlers.ofString());
String jsonString = response.body().toString(); 
System.out.println(jsonString);
Gson gson=new Gson();
covid d=gson.fromJson(jsonString, covid.class);
for(int i=0;i<d.data.contacts.regional.size();i++)
{
	dtm.insertRow(i,new Object[] {d.data.contacts.regional.get(i).loc,d.data.contacts.regional.get(i).number});

}
DefaultTableModel dlm=new DefaultTableModel();
table_1 = new JTable(dlm)	{
	public boolean isCellEditable(int row,int column)
	{
		return false;
	}
		};
table_1.addMouseListener(new MouseAdapter() {
	@Override
	public void mouseClicked(MouseEvent e) {
		int row=table_1.rowAtPoint(new Point(e.getX(),e.getY()));
		int col=table_1.columnAtPoint(new Point(e.getX(),e.getY()));

		if(col==1)
		{
			try {
				  Desktop desktop = java.awt.Desktop.getDesktop();
				 
				 
						Object o=  table_1.getValueAt(row,col);
						URI oURL=new URI((String)o);
				  desktop.browse(oURL);
				  
				} catch (Exception f) {
				  f.printStackTrace();
				}
		}
	

	}
});
scrollPane_1.setViewportView(table_1);
dlm.addColumn("Title");
dlm.addColumn("Link");
 url="https://api.rootnet.in/covid19-in/notifications";

 request=HttpRequest.newBuilder().GET().uri(URI.create(url)).build();
 client=HttpClient.newBuilder().build();
 response = client.send(request,HttpResponse.BodyHandlers.ofString());
 jsonString = response.body().toString(); 
 gson=new Gson();
notif n=gson.fromJson(jsonString, notif.class);
for(int i=0;i<n.data.notifications.size();i++)
{
	dlm.insertRow(i, new Object[] {n.data.notifications.get(i).title,n.data.notifications.get(i).link});
}
	}
}
