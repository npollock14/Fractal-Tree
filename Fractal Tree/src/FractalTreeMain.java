import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class FractalTreeMain extends JPanel
		implements ActionListener, KeyListener, MouseListener, MouseMotionListener, MouseWheelListener {
	private static final long serialVersionUID = 1L;

	int screenWidth = 1000;
	int screenHeight = 1000;
	boolean[] keys = new boolean[300];
	boolean[] keysToggled = new boolean[300];
	boolean[] mouse = new boolean[200];
	int centerX = screenWidth / 2;
	int centerY = screenHeight;
	double initialBranchHeight = 300.0;
	double lineLengthLimit = 10;
	double angleIncrease = Math.PI / 15;

	// ============== end of settings ==================

	public void paint(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		// drawLine(centerX,centerY,100,Math.PI/4,g);
		branch(200, g2, centerX, centerY, 0, 14.0f);
	}

	public void update() throws InterruptedException {

	}

	private void init() {

	}

	public void branch(double len, Graphics2D g2, int centerX, int centerY, double a, float thickness) {
		drawLine(centerX, centerY, len, a, g2, thickness);
		if (len >= 10) {
			branch(len * .75, g2, (int) (centerX + len * Math.sin(a)), (int) (centerY - (len * Math.cos(a))),
					a + angleIncrease, (float) (thickness * .75));
			branch(len * .75, g2, (int) (centerX + (len * Math.sin(a))), (int) (centerY - (len * Math.cos(a))),
					a - angleIncrease, (float) (thickness * .75));
		}
	}

	public void drawLine(int x, int y, double len, double a, Graphics2D g2, float thickness) {
		g2.setStroke(new BasicStroke(thickness));
		g2.drawLine(x, y, (int) (x + len * Math.sin(a)), (int) (y - len * Math.cos(a)));
	}

	public double rBtw(double min, double max) {
		return ((Math.random() * (max - min + 1) + min));
	}

	public Color rColor() {
		return new Color((int) rBtw(0, 255), (int) rBtw(0, 255), (int) rBtw(0, 255));
	}
	// ==================code above ===========================

	@Override
	public void actionPerformed(ActionEvent arg0) {

		try {
			update();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		repaint();
	}

	public static void main(String[] arg) {
		@SuppressWarnings("unused")
		FractalTreeMain d = new FractalTreeMain();
	}

	public FractalTreeMain() {
		JFrame f = new JFrame();
		f.setTitle("Fractal Tree");
		f.setSize(screenWidth, screenHeight);
		f.setBackground(Color.BLACK);
		f.setResizable(false);
		f.addKeyListener(this);
		f.addMouseMotionListener(this);
		f.addMouseWheelListener(this);
		f.addMouseListener(this);

		f.add(this);

		t = new Timer(15, this);
		t.start();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);

		init();

	}

	Timer t;

	@Override
	public void keyPressed(KeyEvent e) {
		keys[e.getKeyCode()] = true;

	}

	@Override
	public void keyReleased(KeyEvent e) {

		keys[e.getKeyCode()] = false;

		if (keysToggled[e.getKeyCode()]) {
			keysToggled[e.getKeyCode()] = false;
		} else {
			keysToggled[e.getKeyCode()] = true;
		}

	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {

	}

	@Override
	public void mouseClicked(MouseEvent e) {

	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {

	}

	@Override
	public void mousePressed(MouseEvent e) {
		mouse[e.getButton()] = true;

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		mouse[e.getButton()] = false;
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		mouse[e.getButton()] = true;

	}

	@Override
	public void mouseMoved(MouseEvent e) {

	}

}