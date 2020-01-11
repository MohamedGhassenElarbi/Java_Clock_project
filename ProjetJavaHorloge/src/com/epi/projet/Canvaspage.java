package com.epi.projet;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.util.Calendar;
import java.util.TimeZone;
import java.util.Timer;
import java.util.TimerTask;

//cette classe joue le rôle d'un canvas
public class Canvaspage extends Canvas {

	private static final float radPerSecMin = (float)(Math.PI / 30.0);
	private static final float radPerNum = (float)(Math.PI/ -6);
	private int size;
	private int centerX;
	private int centerY;
	Calendar cal;
	int hour;
	int minute;
	int second;
	Color colorSecond,colorMHour,colorNumber;
	Timer timer;
	TimeZone timeZone;
	
	Canvaspage(){
		setSize(375,400);
		timer = new Timer();
		timeZone = TimeZone.getDefault();
		timer.schedule(new TickTimerTask(), 0, 1000);
	}
	

	class TickTimerTask extends TimerTask{

		@Override
		public void run() {
			// TODO Auto-generated method stub
			//pour récupérer la date systeme
			cal = (Calendar) Calendar.getInstance(timeZone);
			//cette methode déclache automatiquement la methode paint() chaque seconde
			repaint();
		}
		
	}
	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		super.paint(g);	
		
		//pour dessiner la bordure de l'horloge

			g.setColor(Color.BLACK);
			g.fillOval(25, 35, 350, 350);	
			g.setColor(Color.WHITE);
			g.fillOval(35, 45, 330, 330);

		size = 400 -35;		
		centerX = 400/2;
		centerY = 400/2+10;
					
		//pour dessiner l'horloge
		dessinerHorloge(g);
		
		//pour dessiner les nombres de l'horloge
		dessinerNombres(g);
										
		//pour récupérer la date système
		cal = Calendar.getInstance();
		hour = cal.get(Calendar.HOUR);
		minute = cal.get(Calendar.MINUTE);
		second = cal.get(Calendar.SECOND);	

		
		//pour dessiner les aiguilles

			dessinerAiguilles(g,hour,minute,second);


		//pour dessiner le point centrale de l'horloge
		g.setColor(Color.BLACK);
		g.fillOval(centerX-5, centerY-5, 10, 10);
		g.setColor(Color.RED);
		g.fillOval(centerX-3, centerY-3, 6, 6);
			
	}
//pour dessiner l'horloge
	private void dessinerHorloge(Graphics g) {
		
		
		for (int sec = 0; sec<60; sec++) {
			int ticStart;
			if (sec%5 == 0) {
				ticStart = size/2-10;
			}else{
				ticStart = size/2-5;
			}
			

				drawRadius(g, centerX, centerY, radPerSecMin*sec, ticStart-20, size/2-20,colorNumber.BLACK);
			
			
		}
	}
	
	private void drawRadius(Graphics g, int x, int y, double angle,
			int minRadius, int maxRadius, Color colorNumber) {
			float sine = (float)Math.sin(angle);
			float cosine = (float)Math.cos(angle);
			int dxmin = (int)(minRadius * sine);
			int dymin = (int)(minRadius * cosine);
			int dxmax = (int)(maxRadius * sine);
			int dymax = (int)(maxRadius * cosine);
			g.setColor(colorNumber);
			g.drawLine(x+dxmin, y+dymin, x+dxmax, y+dymax);
	}
	
	//pour dessiner les nombres de l'horloge
	private void dessinerNombres(Graphics g) {
		for(int num=12;num>0;num--){			
			placerNombres(g,radPerNum*num,num);			
		}
	}
	
	private void placerNombres(Graphics g, float angle,int n) {
		float sine = (float)Math.sin(angle);
		float cosine = (float)Math.cos(angle);
		int dx = (int)((size/2-20-25) * -sine);
		int dy = (int)((size/2-20-25) * -cosine);		
		
		g.drawString(""+n,dx+centerX-5,dy+centerY+5);
	}
	
	//pour dessiner les aiguilles
	private void dessinerAiguilles(Graphics g, double hour, double minute, double second) {

		double rsecond = (second*6)*(Math.PI)/180;
		double rminute = ((minute + (second / 60)) * 6) * (Math.PI) / 180;
		double rhours = ((hour + (minute / 60)) * 30) * (Math.PI) / 180;
				
		g.setColor(colorSecond.RED);
		g.drawLine(centerX, centerY, centerX + (int) (150 * Math.cos(rsecond - (Math.PI / 2))), centerY + (int) (150 * Math.sin(rsecond - (Math.PI / 2))));
		g.setColor(colorMHour.BLACK);
		g.drawLine(centerX, centerY, centerX + (int) (120 * Math.cos(rminute - (Math.PI / 2))), centerY + (int) (120 * Math.sin(rminute - (Math.PI / 2))));
		g.drawLine(centerX, centerY, centerX + (int) (90 * Math.cos(rhours - (Math.PI / 2))), centerY + (int) (90 * Math.sin(rhours - (Math.PI / 2))));
	}
}
