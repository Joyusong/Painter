package main;
import static main.MainFrame.TranceColor;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseEvent;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.util.LinkedList;
import static main.MainFrame.SizeText;
public class DrawLine extends DrawObject {
	private LinkedList<Point> startPointList = new LinkedList<>();
	private LinkedList<Point> endPointList = new LinkedList<>();
	private LinkedList<Color> mouseClolrList = new LinkedList<>();
	private Color color;
	
	private Point startPoint = new Point();
	private Point endPoint = new Point();
	private Point oldEndPoint = new Point();
	
	public DrawLine(Screen screen) {
		super(screen);
	}
	
	public void paint(Graphics g) {
	/*	int count = startPointList.size();
		for(int i=0; i < count; i++) {
			color = mouseClolrList.pop();
			g.setColor(color);
			Point start = startPointList.get(i);
			Point end = endPointList.get(i);
			g.drawLine(start.x, start.y, end.x, end.y);
			this.mouseClolrList.add(color);
		}   */
	}
	
	public void mouseDragged(MouseEvent e) {
		Graphics g = getScreen().getGraphics();
		this.endPoint = e.getPoint();
		g.setXORMode(Color.WHITE);
		g.drawLine(startPoint.x, startPoint.y, oldEndPoint.x, oldEndPoint.y);
		//g.drawLine(startPoint.x, startPoint.y, endPoint.x, endPoint.y);
		this.oldEndPoint = endPoint;
		g.setColor(Color.BLACK);
	}
	
	public void mousePressed(MouseEvent e) {			
		this.startPoint = e.getPoint();
			this.endPoint = e.getPoint();
			this.oldEndPoint = e.getPoint();
			
	}

	public void mouseReleased(MouseEvent e) {	
			this.endPoint = e.getPoint();
			startPointList.add(startPoint);
			endPointList.add(endPoint);
			this.mouseClolrList.add(TranceColor);
			Screen.bufferGraphics.setColor(TranceColor);
			Screen.bufferGraphics.drawLine(startPoint.x, startPoint.y, endPoint.x, endPoint.y);
	}

/*	@Override
	public void save(DataOutputStream dos) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void open(DataInputStream dis) {
		// TODO Auto-generated method stub
		
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
