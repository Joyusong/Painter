package main;
import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ComponentListener;
import java.awt.event.MouseEvent;
import java.io.DataInputStream;
import java.io.DataOutputStream;

public abstract class DrawObject extends Canvas implements ComponentListener{
	private Screen screen;
	
	
	public DrawObject(Screen screen) {
		addComponentListener(this);
		this.screen = screen;
	}
	public Screen getScreen() {
		return this.screen;
	}
	public abstract void paint(Graphics g);
	public abstract void mouseDragged(MouseEvent e);
	public abstract void mousePressed(MouseEvent e);
	public abstract void mouseReleased(MouseEvent e);
//	public abstract void save(DataOutputStream dos);
	//public abstract void open(DataInputStream dis);
}
