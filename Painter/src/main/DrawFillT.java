
package main;
import static main.MainFrame.TranceColor;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseEvent;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.LinkedList;

public class DrawFillT extends DrawObject{
	private LinkedList<Point> startPointList = new LinkedList<>();
	private LinkedList<Point> endPointList = new LinkedList<>();
	private Point startPoint = new Point();
	private Point endPoint = new Point();
	private Point oldEndPoint = new Point();
	private int[] oldTPointx = new int[3];
	private int[] oldTPointy = new int[3];
	private int[] TPointx = new int[3];
	private int[] TPointy = new int[3];
	
	
	private LinkedList<Point> rectPointList = new LinkedList<>();
	private LinkedList<Dimension> rectDimList = new LinkedList<>();
	private LinkedList<Color> rectColorList = new LinkedList<>();
	private Color color;
	
	public DrawFillT(Screen screen) {
		
		super(screen);
	}

	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
	/*	int count = startPointList.size();
		for(int i=0; i < count; i++) {
			color = rectColorList.pop();
			g.setColor(color);
			Point start = startPointList.get(i);
			Point end = endPointList.get(i);
			TPointx[0] = startPoint.x;
			TPointy[0] = startPoint.y;
			TPointx[1]= endPoint.x-startPoint.x;
			TPointy[1]= endPoint.y-startPoint.y;
			TPointx[2] =((int)Math.sqrt(3)/2)*TPointx[0]-TPointx[1];
			TPointy[2]=((int)Math.sqrt(3)/2)*TPointy[0]-TPointy[1];
			
			g.fillPolygon(TPointx,TPointy,3);
				this.rectColorList.add(color);
		}*/

	}
/*	public void initBuffered() {
		dim = getScreen().getSize();
		setBackground(Color.white);
		offscreen = createImage(dim.width,dim.height);
		bufferGraphics = offscreen.getGraphics();
	}
*/
	@Override
	public void update(Graphics g) {
		paint(g);
	}
 	@Override
	public void mouseDragged(MouseEvent e) {
		Graphics g = getScreen().getGraphics();
		this.endPoint = e.getPoint();
		g.setXORMode(Color.black);
		TPointx[0] = startPoint.x;
		TPointy[0] = startPoint.y;
		TPointx[1]= endPoint.x;
		TPointy[1]= TPointy[0];
		TPointx[2] =(TPointx[0]+TPointx[1])/2;
		TPointy[2]=endPoint.y;
		oldTPointx[0] = startPoint.x;
		oldTPointy[0] = startPoint.y;
		oldTPointx[1]= oldEndPoint.x;
		oldTPointy[1]= oldTPointy[0];
		oldTPointx[2] =(oldTPointx[0]+oldTPointx[1])/2;
		oldTPointy[2]=oldEndPoint.y;
		g.fillPolygon(oldTPointx,oldTPointy,3);
		g.fillPolygon(TPointx,TPointy,3);
		this.oldEndPoint = endPoint;
		g.setColor(Color.WHITE);
		getScreen().repaint();
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
	
		this.startPoint = e.getPoint();
		this.endPoint = e.getPoint();
		this.oldEndPoint = e.getPoint();
	
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		this.endPoint = e.getPoint();
		startPointList.add(startPoint);
		this.rectColorList.add(TranceColor);
		endPointList.add(endPoint);
		Screen.bufferGraphics.setColor(TranceColor);
		Screen.bufferGraphics.fillPolygon(TPointx,TPointy,3);
		getScreen().repaint();
	
	}
/*
	@Override
	public void save(DataOutputStream dos) {
		// TODO Auto-generated method stub
		try {
			dos.writeInt(this.rectPointList.size());
			for(Point point : rectPointList) {
				dos.writeInt(point.x);
				dos.writeInt(point.y);
			}
			
			dos.writeInt(this.rectDimList.size());
			for(Dimension dim : rectDimList) {
				dos.writeInt(dim.width);
				dos.writeInt(dim.height);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void open(DataInputStream dis) {
		// TODO Auto-generated method stub
		try {
			this.rectPointList.clear();
			this.rectDimList.clear();
			
			int length = dis.readInt();
			for(int i=0; i < length; i++) {
				int x = dis.readInt();
				int y = dis.readInt();
				this.rectPointList.add(new Point(x, y));
				
			}
			length = dis.readInt();
			for(int i=0; i < length; i++) {
				int width = dis.readInt();
				int height = dis.readInt();
				this.rectDimList.add(new Dimension(width, height));
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		getScreen().repaint();
	}
*/
	@Override
	public void componentResized(ComponentEvent e) {
		// TODO Auto-generated method stub
		//initBuffered();
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