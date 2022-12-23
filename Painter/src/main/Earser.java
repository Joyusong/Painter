package main;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseEvent;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.LinkedList;

import static main.MainFrame.SizeText;
import static main.MainFrame.TranceColor; //색 정보 받아오기

public class Earser extends DrawObject {
	private LinkedList<Point> mousePointList = new LinkedList<>();
	private LinkedList<Color> mouseClolrList = new LinkedList<>();
	private Color color;
	private BufferedImage bufferimage;
	private Graphics bufferG;
	public Earser(Screen screen) {
		super(screen);
	
	}
	

	public void paint(Graphics g) {
		/*
		for(Point point : mousePointList ) {
			color = new Color(255,255,255);
			g.setColor(color);
			g.fillOval(point.x, point.y, 15, 15);
			this.mouseClolrList.add(color);
		}*/
	}
	
	public void mouseDragged(MouseEvent e) {

		this.mousePointList.add(e.getPoint());
		this.mouseClolrList.add(TranceColor);
		Screen.bufferGraphics.setColor(new Color(255,255,255));
		Screen.bufferGraphics.fillOval(e.getPoint().x, e.getPoint().y, Integer.parseInt(SizeText.getText()) ,Integer.parseInt(SizeText.getText()));
		getScreen().repaint();
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
		
		
		
	}
/*
	@Override
	public void save(DataOutputStream dos) {
		try {
			int length = mousePointList.size();
			dos.writeInt(length);
			for(Point point : mousePointList) {
				dos.writeInt(point.x);
				dos.writeInt(point.y);
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}

	@Override
	public void open(DataInputStream dis) {
		try {
			int length = dis.readInt();
			mousePointList.clear();
			for(int i=0; i < length; i++) {
				int x = dis.readInt();
				int y = dis.readInt();
				Point newPoint = new Point(x, y);
				mousePointList.add(newPoint);
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		getScreen().repaint();
	}
*/

	@Override
	public void componentResized(ComponentEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void componentMoved(ComponentEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void componentShown(ComponentEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void componentHidden(ComponentEvent e) {
		// TODO Auto-generated method stub
		
	}
}
