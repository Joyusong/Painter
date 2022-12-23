package main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Stack;
import javax.imageio.ImageIO;
import static main.MainFrame.LineSize;
import static main.MainFrame.SizeText;
public class Screen extends Canvas implements MouseListener, MouseMotionListener, ComponentListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3266071157170361453L;
	private DrawObject drawMode;
	private DrawObject[] drawObjectList = new DrawObject[12];
	public static final int SELECTION = 0;
	public static final int POINT = 1;
	public static final int LINE = 2;
	public static final int RECT = 3;
	private int backCount = 0;
	private int nextCount = 0;
	public static Dimension dim;
	public static Image offscreen;
	public static Graphics bufferGraphics;
	public static Image[] backoffscreen = new Image[5];
	public static Graphics[] backbufferGraphics = new Graphics[5];
	public static Image[] nextoffscreen = new Image[5];
	public static Graphics[] nextbufferGraphics = new Graphics[5];
	public static Image temoffscreen;
	public static Graphics tembufferGraphics;
	public Screen() {
		drawObjectList[0] = new Earser(this);
		drawObjectList[1] = new DrawPoint(this);
		drawObjectList[2] = new DrawLine(this);
		drawObjectList[3] = new DrawRect(this);
		drawObjectList[4] = new DrawOval(this);
		drawObjectList[5] = new DrawT(this);
		drawObjectList[6] = new DrawFillOval(this);
		drawObjectList[7] = new DrawFillRect(this);
		drawObjectList[8] = new DrawFillT(this);
		drawObjectList[9] = new DrawRoundRect(this);
		

		addComponentListener(this);
		addMouseListener(this);
		addMouseMotionListener(this);
	}

	public void setDrawMode(int mode) {
		if (mode < 10) {
			this.drawMode = drawObjectList[mode];

		} else if (mode == 10) {
//			this.drawMode =null;
			back();
		} else if (mode == 11) {
			// this.drawMode =null;
			next();
		}

	}

	public void initBuffered() {
		dim = getSize();
		setBackground(Color.white);
		offscreen = createImage(dim.width, dim.height);
		bufferGraphics = offscreen.getGraphics();
		temoffscreen = createImage(dim.width, dim.height);
		tembufferGraphics = temoffscreen.getGraphics();
		for (int i = 0; i < 5; i++) {
			backoffscreen[i] = createImage(dim.width, dim.height);
			backbufferGraphics[i] = backoffscreen[i].getGraphics();
			nextoffscreen[i] = createImage(dim.width, dim.height);
			nextbufferGraphics[i] = nextoffscreen[i].getGraphics();
		}
	}

	public void back() {
		if (backCount >= 0) {
			System.out.println();
			System.out.println(backCount);
			if (backCount < 5) {
				nextBuffer();
				bufferGraphics.clearRect(0, 0, dim.width, dim.height);
				bufferGraphics.drawImage(Screen.backoffscreen[backCount], 0, 0, this);
				--backCount;

			}
		}
	}

	public void nextBuffer() {
		if (nextCount > 4) {

			tembufferGraphics = nextbufferGraphics[0];
				for (int j = 1; j < 5; j++) {
				nextbufferGraphics[j - 1] = nextbufferGraphics[j];
			}nextbufferGraphics[4]=tembufferGraphics;
			
		} else {
			nextCount++;
		}
		nextbufferGraphics[nextCount].drawImage(Screen.offscreen, 0, 0, this);

	}

	public void backBuffer() {

		if (backCount >= 4) {
			tembufferGraphics = backbufferGraphics[0];
			for (int i = 1; i < 5; i++) {
				backbufferGraphics[i - 1] = backbufferGraphics[i];
			}
			backbufferGraphics[4]=tembufferGraphics;
		} else {
			backCount++;
		}
		backbufferGraphics[backCount].drawImage(Screen.offscreen, 0, 0, this);

	}

	public void next() {
		System.out.println("실행");
		if (nextCount >= 0) {
			System.out.println();
			System.out.println(nextCount);
			if (nextCount < 5) {
				bufferGraphics.clearRect(0, 0, dim.width, dim.height);
				bufferGraphics.drawImage(Screen.nextoffscreen[nextCount], 0, 0, this);
				--nextCount;
			}
		}
	}

	@Override
	public void paint(Graphics g) {
		SizeText.setText(""+LineSize.getValue());
		if (bufferGraphics == null) {
			this.initBuffered();
		}
		SizeText.setText(""+LineSize.getValue());
		g.drawImage(Screen.offscreen, 0, 0, this);
		repaint();
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		if (drawMode != null)
			this.drawMode.mouseDragged(e);
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		if (e.getButton() == MouseEvent.BUTTON1) {
			if (drawMode != null)
				this.drawMode.mousePressed(e);
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if (e.getButton() == MouseEvent.BUTTON1) {
			if (drawMode != null)
				backBuffer();
			this.drawMode.mouseReleased(e);
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	@Override
	public void update(Graphics g) {
		paint(g);
	}

	public void save(String filename) {
		/*
		 * File file = new File(filename); FileOutputStream fos; try { fos = new
		 * FileOutputStream(file); DataOutputStream dos = new DataOutputStream(fos);
		 * for(DrawObject draw : drawObjectList) { draw.save(dos); }
		 * 
		 * } catch (FileNotFoundException e) { e.printStackTrace(); }
		 */
		BufferedImage image = (BufferedImage) offscreen;
		try {
			File file = new File(filename);
			ImageIO.write(image, "jpg", file);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void open(String filename) {
		File file = new File(filename);
		FileInputStream fis;
		offscreen = null;

		try {
			/*
			 * fis = new FileInputStream(file); DataInputStream dis = new
			 * DataInputStream(fis); for(DrawObject draw : drawObjectList) { draw.open(dis);
			 * 
			 */

			File sourceimage = new File(filename);
			offscreen = ImageIO.read(sourceimage);

			bufferGraphics = offscreen.getGraphics();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

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
