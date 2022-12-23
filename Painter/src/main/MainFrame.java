package main;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FileDialog;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
//import java.awt.event.InputEvent;
import java.awt.event.InputEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;

import javax.sound.sampled.Line;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.KeyStroke;
public class MainFrame extends JFrame implements ActionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7314136332860432911L;
	private final String MENU_FILE_NEW = "새로만들기(N)";
	private final String MENU_FILE_CLOSE = "끝내기(E)";
	private final String MENU_VIEW_STATUS = "생태바 보기(S)";
	private final String MENU_FILE_SAVE = "저장(S)";
	private final String MENU_FILE_OPEN = "열기(O)";

	private JLabel statusBar = null;
	private JButton[] toolboxButtons;
	private JButton[] colorButtons;
	private Color[] colors;
	private JButton selectedColorButton;
	private Screen screen;
	public static Color TranceColor; // color정보 전송
	public static JSlider LineSize = new JSlider(JSlider.HORIZONTAL,0, 50,5);
	public static JTextField SizeText = new JTextField(3); 
	public MainFrame() {
		this.setJMenuBar(createMenuBar());
		this.statusBar = createStatusBar();
		this.add(this.statusBar, BorderLayout.SOUTH);
		this.add(createToolBar(), BorderLayout.NORTH);
		this.screen = new Screen();
		this.screen.setBackground(new Color(255,255,255));
		this.add(this.screen);
		
		setSize(800, 600);
		setTitle("Window Painter");
		this.selectedColorButton.setBackground(new Color(0,0,0));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
		
	}
	private JToolBar createToolBar() {
		JToolBar toolBar = new JToolBar();
		toolBar.setLayout(new FlowLayout(FlowLayout.LEFT));
		JPanel groupPanelDrawBox = new JPanel();
		groupPanelDrawBox.setLayout(new GridLayout(3, 4));
		
		ImageIcon iconClose = new ImageIcon(getClass().getClassLoader().getResource("Resource/eraser.png"));
		iconClose = new ImageIcon(
		iconClose.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH));
		
		
		LineSize.setValue(10);
		LineSize.setMajorTickSpacing(10);
		LineSize.setMinorTickSpacing(5);
		LineSize.setPaintLabels(true);
		LineSize.setPaintTicks(true);
		SizeText.setText(""+LineSize.getValue());
		SizeText.setEnabled(false);
		SizeText.setSize(5,5);
		
		this.toolboxButtons = new JButton[12];
		toolboxButtons[0] = new JButton(iconClose);
		iconClose = new ImageIcon(getClass().getClassLoader().getResource("Resource/pen.png"));
		iconClose = new ImageIcon(
		iconClose.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH));
		toolboxButtons[1] = new JButton(iconClose);
		iconClose = new ImageIcon(getClass().getClassLoader().getResource("Resource/line.png"));
		iconClose = new ImageIcon(
		iconClose.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH));
		toolboxButtons[2] = new JButton(iconClose);
		iconClose = new ImageIcon(getClass().getClassLoader().getResource("Resource/rect.jpg"));
		iconClose = new ImageIcon(
		iconClose.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH));
		toolboxButtons[3] = new JButton(iconClose);
		iconClose = new ImageIcon(getClass().getClassLoader().getResource("Resource/oval.png"));
		iconClose = new ImageIcon(
		iconClose.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH));
		toolboxButtons[4] = new JButton(iconClose);
		iconClose = new ImageIcon(getClass().getClassLoader().getResource("Resource/T.png"));
		iconClose = new ImageIcon(
		iconClose.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH));
		toolboxButtons[5] = new JButton(iconClose);
		iconClose = new ImageIcon(getClass().getClassLoader().getResource("Resource/point.png"));
		iconClose = new ImageIcon(
		iconClose.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH));
		toolboxButtons[6] = new JButton(iconClose);
		iconClose = new ImageIcon(getClass().getClassLoader().getResource("Resource/fillRect.jpg"));
		iconClose = new ImageIcon(
		iconClose.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH));
		toolboxButtons[7] = new JButton(iconClose);
		iconClose = new ImageIcon(getClass().getClassLoader().getResource("Resource/fillT.png"));
		iconClose = new ImageIcon(
		iconClose.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH));
		toolboxButtons[8] = new JButton(iconClose);
		iconClose = new ImageIcon(getClass().getClassLoader().getResource("Resource/RectR.png"));
		iconClose = new ImageIcon(
		iconClose.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH));
		toolboxButtons[9] = new JButton(iconClose);
		iconClose = new ImageIcon(getClass().getClassLoader().getResource("Resource/back.png"));
		iconClose = new ImageIcon(
		iconClose.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH));
		toolboxButtons[10] = new JButton(iconClose);
		iconClose = new ImageIcon(getClass().getClassLoader().getResource("Resource/next.jpg"));
		iconClose = new ImageIcon(
		iconClose.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH));
		toolboxButtons[11] = new JButton(iconClose);
	
		for(int i=0; i < 12; i++) {
			
			JButton toolButton = toolboxButtons[i];
			toolButton.setPreferredSize(new Dimension(20, 20));
			toolButton.addActionListener(this);
			groupPanelDrawBox.add(toolButton);
		}

		JPanel groupPanelSelectedColor = new JPanel();
		
		selectedColorButton = new JButton();
		selectedColorButton.setBackground(new Color(255, 255, 255));
		selectedColorButton.setPreferredSize(new Dimension(60, 60));
		groupPanelSelectedColor.add(selectedColorButton);
		
		JPanel groupPanelColor = new JPanel();
		groupPanelColor.setLayout(new GridLayout(3, 5));
		
		JPanel SizeSlider = new JPanel();
		SizeSlider.setLayout(new GridLayout(1,1));
		
		colorButtons = new JButton[15];
		colors = new Color[15];
		for(int i=0; i < colors.length; i++)
			colors[i] = new Color(0, 0, 0);
		//이미지버튼 색 지정
		colors[0] = new Color(255, 0, 0);
		colors[1] = new Color(0, 255, 0);
		colors[2] = new Color(0, 0, 255);
		colors[3] = new Color(200,200,200);
		colors[5] = new Color(255,255,0);
		colors[6] = new Color(0,255,255);
		colors[7] = new Color(255,0,255);
		colors[8] = new Color(255,100,200);
		colors[9] = new Color(200,0,150);
		colors[10] = new Color(0,0,150);
		colors[11] = new Color(0,200,150);
		colors[12] = new Color(100,200,0);
		colors[13] = new Color(50,200,150);
		colors[14] = new Color(255,200,50);
		
		
		for(int i=0; i < 15; i++) {
			colorButtons[i] = new JButton();
			JButton colorButton = colorButtons[i];
			colorButton.setBackground(colors[i]);
			colorButton.setPreferredSize(new Dimension(20, 20));
			colorButton.addActionListener(this);
			groupPanelColor.add(colorButton);
		}
		SizeSlider.add(LineSize);
		SizeText.setLayout(new BorderLayout());
		SizeText.setSize(10,10);
		toolBar.add(groupPanelDrawBox);
		toolBar.add(groupPanelSelectedColor);
		toolBar.add(groupPanelColor);
		toolBar.add(SizeSlider);
		toolBar.add(SizeText);
		return toolBar;
	}
	
	
	private JMenuBar createMenuBar() {
		JMenuBar menuBar = new JMenuBar();
		
		ImageIcon iconNew = new ImageIcon(getClass().getClassLoader().getResource("Resource/new.png"));
		ImageIcon iconClose = new ImageIcon(getClass().getClassLoader().getResource("Resource/close.png"));
		
		JMenu fileMenu = new JMenu("파일(F)");
		fileMenu.setMnemonic(KeyEvent.VK_F);
		
		JMenuItem newMenuItem = new JMenuItem(MENU_FILE_NEW, iconNew);
		newMenuItem.setMnemonic(KeyEvent.VK_N);
		newMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N,
				InputEvent.CTRL_DOWN_MASK));
		newMenuItem.setToolTipText("파일을 새로 만듭니다.");
		newMenuItem.addActionListener(this);
		
		JMenuItem saveMenuItem = new JMenuItem(MENU_FILE_SAVE);
		saveMenuItem.setMnemonic(KeyEvent.VK_S);
		saveMenuItem.addActionListener(this);

		JMenuItem openMenuItem = new JMenuItem(MENU_FILE_OPEN);
		openMenuItem.setMnemonic(KeyEvent.VK_O);
		openMenuItem.addActionListener(this);

		JMenuItem closeMenuItem = new JMenuItem(MENU_FILE_CLOSE, iconClose);
		closeMenuItem.setMnemonic(KeyEvent.VK_E);
		closeMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E,
				InputEvent.CTRL_DOWN_MASK));
		closeMenuItem.setToolTipText("프로그램을 종료 합니다.");
		closeMenuItem.addActionListener(this);
		
		JMenu viewMenu = new JMenu("보기(V)");
		
		JCheckBoxMenuItem showStatusBarMenuItem = new JCheckBoxMenuItem(MENU_VIEW_STATUS);
		showStatusBarMenuItem.setSelected(true);
		
		showStatusBarMenuItem.addItemListener(new ItemListener() {	
			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				if(e.getStateChange() == ItemEvent.SELECTED) {
					statusBar.setVisible(true);
				}
				else {
					statusBar.setVisible(false);
				}
				
			}
		});
		
		viewMenu.add(showStatusBarMenuItem);
		
		fileMenu.add(newMenuItem);
		fileMenu.addSeparator();
		fileMenu.add(saveMenuItem);
		fileMenu.add(openMenuItem);
		fileMenu.addSeparator();
		fileMenu.add(closeMenuItem);
		
		menuBar.add(fileMenu);
		menuBar.add(viewMenu);
		return menuBar;
	}
	
	private JLabel createStatusBar() {
		JLabel statusBar = new JLabel("Ready");
		statusBar.setBorder(BorderFactory.createEtchedBorder());
		return statusBar;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getActionCommand().equals(MENU_FILE_NEW)) {
			screen.initBuffered();
			this.dispose();
			
		new MainFrame();
		
		}
		else if(e.getActionCommand().equals(MENU_FILE_SAVE)) {
			FileDialog fd = new FileDialog(this, "파일 저장", FileDialog.SAVE);
			fd.setVisible(true);
			if(fd.getFile() != null) {
				System.out.println(fd.getDirectory()+fd.getFile());
				this.screen.save(fd.getDirectory()+fd.getFile());
			}
		}
		else if(e.getActionCommand().equals(MENU_FILE_OPEN)) {
			FileDialog fd = new FileDialog(this, "파일 열기", FileDialog.LOAD);
			fd.setVisible(true);
			if(fd.getFile() != null) {
				System.out.println(fd.getDirectory()+fd.getFile());
				this.screen.open(fd.getDirectory()+fd.getFile());
			}
		}
		else if(e.getActionCommand().equals(MENU_FILE_CLOSE)) {
			this.dispose();
			System.exit(0);
		}
		else {
			for(int i=0; i < this.toolboxButtons.length; i++) {
				if(toolboxButtons[i].equals(e.getSource())) {
					System.out.println("toolbox btn: "+i);
					screen.setDrawMode(i);
					switch(i) {
					case 0:
						break;
					case 1:
						break;
					}
					break;
				}
			}
			for(int i=0; i < this.colorButtons.length; i++) {
				if(colorButtons[i].equals(e.getSource())) {
					System.out.println("color btn: "+i);
					this.selectedColorButton.setBackground(
							this.colors[i]);
					TranceColor = this.colors[i];
					break;
				}
			}
		}
	}
}
