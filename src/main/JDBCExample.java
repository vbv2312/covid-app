package main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.*;
public class JDBCExample {
   // JDBC driver name and database URL
   static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";  
   static final String DB_URL = "jdbc:mysql://localhost/history";

   //  Database credentials
   static final String USER = "root";
   static final String PASS = "Vaibhav207*";
   Connection conn;
   PreparedStatement stmt;
   public static void main(String[] args) {
	   JDBCExample f=new JDBCExample();
   }
   public JDBCExample()
   {
   Connection conn = null;
   PreparedStatement stmt = null;
   try{

      conn = DriverManager.getConnection(DB_URL, USER, PASS);
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
   
}//end main
}//end JDBCExample
