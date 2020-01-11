package com.epi.projet;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;



public class ClockProject extends JFrame {

	JPanel numerique; 
	JPanel analogique; 
	JLabel time;  
	JTabbedPane tabbedPane;
	Canvaspage cp;
	
	public ClockProject(){
	
		super("Projet Horloge");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(600,500);
		this.setLocationRelativeTo(null);
		
		time=new JLabel("");
		time.setFont(new Font("",Font.BOLD, 50));
		time.setForeground(Color.GRAY);
		time.setHorizontalAlignment(JLabel.CENTER);
		
		//créer une instance du classe "Canvaspage"
		cp=new Canvaspage();
		//création et remplissage du panel "numérique"
		numerique=new JPanel();
		numerique.setLayout(new BorderLayout());
		numerique.add("Center",time);
		
		//création et remplissage du panel "analogique"
		analogique=new JPanel();
		analogique.setLayout(new FlowLayout(FlowLayout.CENTER));
		analogique.add(cp);
		//création et remplissage du tabbed pane
		tabbedPane=new JTabbedPane();
		tabbedPane.add("Horloge Numériquet", numerique);
		tabbedPane.add("Horloge Analogique", analogique);
		this.add(tabbedPane);
		//appel du methode clock
		clock();
		
		
		
	}
	
		//methode clock permet de remplir le label "time" et faire la mise a jour a travers un thread
	public void clock(){
		Thread th=new Thread(){
			public void run(){
				try{
					for(;;){
						Calendar c1=new GregorianCalendar();
						
						int seconds =c1.get(Calendar.SECOND);
						int min =c1.get(Calendar.MINUTE);
						int hour =c1.get(Calendar.HOUR);
						int am_pm =c1.get(Calendar.AM_PM);
						String d_n="";
						if(am_pm==1)
							d_n="PM";
						else
							d_n="AM";
						time.setText(""+hour+" : "+min+" : "+seconds+" : "+d_n+"");
						sleep(1000);
					}
					
				}catch(Exception e){e.printStackTrace();}
			}
		};
		th.start();
	}
	
	public static void main(String[]args){
		ClockProject cp=new ClockProject();
		cp.show();
	}
}
