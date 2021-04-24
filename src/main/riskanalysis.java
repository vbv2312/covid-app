package main;

import java.awt.EventQueue;
import java.io.IOException;

import javax.swing.JFrame;

public class riskanalysis {

	public Myframe frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					riskanalysis window = new riskanalysis();
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
	public riskanalysis() throws IOException,InterruptedException {
		frame = new Myframe("risk");
		
		
	}


}
