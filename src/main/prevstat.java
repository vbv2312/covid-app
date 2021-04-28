package main;
import java.awt.EventQueue;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.sql.*;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import java.text.*;
import java.util.Properties;

import com.google.gson.Gson;
import com.toedter.calendar.JDateChooser;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class prevstat extends JDBCExample{

	public Myframe frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					prevstat window = new prevstat();
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
	public prevstat() throws IOException,InterruptedException {
		frame = new Myframe("prev stats");
		var url="https://api.rootnet.in/covid19-in/stats/history";

		var request=HttpRequest.newBuilder().GET().uri(URI.create(url)).build();
		 var client=HttpClient.newBuilder().build();
		 var response = client.send(request,HttpResponse.BodyHandlers.ofString());
		 String jsonString = response.body().toString(); 
		 Gson gson=new Gson();
	
		history h=gson.fromJson(jsonString, history.class);
		for(int i=h.data.size()-1;i>=0;i--)
		{
		 
try {
    conn = DriverManager.getConnection(DB_URL, USER, PASS); //used from jdbc example
    ResultSet rst=null;
    
    // for giving entry of data for summary table
    
    String ser="Select * from history.summary where day= '"+java.sql.Date.valueOf(h.data.get(i).day)+"'";
    System.out.println(ser+"\n");
  stmt=conn.prepareStatement(ser);
  rst=stmt.executeQuery();
  stmt=null;
  System.out.println(rst);
  if(rst.isBeforeFirst()==false)
  {

	      String sql = "insert into history.summary values(?,?,?,?,?,?)";
	      System.out.println("f");
	      stmt = conn.prepareStatement(sql);
	      stmt.setDate(1,java.sql.Date.valueOf(h.data.get(i).day));
	      stmt.setInt(2,h.data.get(i).summary.total);
	      stmt.setInt(3,h.data.get(i).summary.confirmedCasesIndian);
	      stmt.setInt(4,h.data.get(i).summary.confirmedCasesForeign);
	      stmt.setInt(5,h.data.get(i).summary.discharged);
	      stmt.setInt(6,h.data.get(i).summary.deaths);
stmt.executeUpdate();
	      System.out.println("Created table in given database...");
  

  for(int j=0;j<h.data.get(i).regional.size();j++)
  {
  String spd = "insert into history.regional values(?,?,?,?,?,?)";
  stmt = conn.prepareStatement(spd);
  stmt.setDate(1,java.sql.Date.valueOf(h.data.get(i).day));
  stmt.setString(2,h.data.get(i).regional.get(j).loc);
  stmt.setInt(3,h.data.get(i).regional.get(j).confirmedCasesIndian);
  stmt.setInt(4,h.data.get(i).regional.get(j).confirmedCasesForeign);
  stmt.setInt(5,h.data.get(i).regional.get(j).discharged);
  stmt.setInt(6,h.data.get(i).regional.get(j).deaths);
stmt.executeUpdate();
  System.out.println("Created table in given database..2.");
  System.out.println(h.data.get(i).regional.get(j).loc+"\n");
  }
  }
  else
  {
	  break;
  }
 
	   }catch(SQLException se){
	      //Handle errors for JDBC
	      se.printStackTrace();
	   }catch(Exception e){
	      //Handle errors for Class.forName
	      e.printStackTrace();
	   }finally{
	      //finally block used to close resources
	      try{
	         if(stmt!=null)
	            conn.close();
	      }catch(SQLException se){
	      }// do nothing
	      try{
	         if(conn!=null)
	            conn.close();
	      }catch(SQLException se){
	         se.printStackTrace();
	      }//end finally try
	   }//end try
	   System.out.println("Goodbye!");
		}
		 ///////////////////////design part/////////////////////////
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(43, 266, 1288, 359);
		frame.getContentPane().add(scrollPane);
		UtilDateModel model = new UtilDateModel();
		Properties p = new Properties();
		p.put("text.today", "Today");
		p.put("text.month", "Month");
		p.put("text.year", "Year");
		JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
		datePanel.setBounds(507, 57, 200, 180);
		JDatePickerImpl datePicker=new JDatePickerImpl(datePanel,new DateLabelFormatter());
		frame.getContentPane().add(datePanel);
		
		JButton btnNewButton = new JButton("OK");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				java.util.Date sel=(java.util.Date)datePicker.getModel().getValue();
			Integer n=new Integer(10);
				n=sel.getYear();
				n+=1900;
				String s=new String();
		//	System.out.println(sel.getYear()+1900);
			s=n.toString();
			s+="-";
			n=sel.getMonth();
			n++;
			if(n<10)
			{
				s+="0";
			}
			s+=n.toString();
			s+="-";
			n=sel.getDate();
			if(n<10)
			{
				s+="0";
			}
			s+=n.toString();
			System.out.println(s);
			Connection conn = null;
			   PreparedStatement stmt = null;
			   try {
			   conn = DriverManager.getConnection(DB_URL, USER, PASS);
			    ResultSet rst=null;
			    
			    // for giving entry of data for summary table
			    
			    String ser="Select * from history.regional where day= '"+java.sql.Date.valueOf(s)+"'";
			    System.out.println(ser+"\n");
			  stmt=conn.prepareStatement(ser);
			  rst=stmt.executeQuery();
			
				  if(rst.isBeforeFirst()==false)
				  {
JOptionPane jj=new JOptionPane();
 jj.showMessageDialog(null, "No such record exists");
				  }
				  else
				  {
					  DefaultTableModel dtm=new DefaultTableModel();
					   JTable table;

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
				scrollPane.setViewportView(table);
				int i=0;
             while(rst.next())
             {
            	 String sa=rst.getString("location");
            	 int cci=rst.getInt("confirmedCasesIndian");
            	 int ccf=rst.getInt("confirmedCasesForeign");
            	 int dis=rst.getInt("discharged");
            	 int ded=rst.getInt("deaths");
            	 System.out.println(sa+" "+cci+" "+ccf+" "+dis+" "+ded+"\n");
            	
         			dtm.insertRow(i,new Object[] {sa,cci,ccf,dis,ded});
         		i++;
             }
				  }
			  
			   }
			   catch(SQLException se){
				      //Handle errors for JDBC
				      se.printStackTrace();
				   }catch(Exception ee){
				      //Handle errors for Class.forName
				      ee.printStackTrace();
				   }finally{
				      //finally block used to close resources
				      try{
				         if(stmt!=null)
				            conn.close();
				      }catch(SQLException se){
				      }// do nothing
				      try{
				         if(conn!=null)
				            conn.close();
				      }catch(SQLException se){
				         se.printStackTrace();
				      }//end finally try
				   }//end try
			}
		});
		btnNewButton.setBounds(775, 130, 85, 21);
		frame.getContentPane().add(btnNewButton);

	
	}
}

