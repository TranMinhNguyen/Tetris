import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JPanel;


public class SidePanel extends JPanel{

private static final int SMALL_INSET = 20;
	
	private static final int LARGE_INSET = 40;
	
	private static final int STATS_INSET = 175;
	
	private static final int CONTROLS_INSET = 300;
	
	private static final int TEXT_STRIDE = 25;
	
	//Phong Chu Nho
	private static final Font SMALL_FONT = new Font("Tahoma", Font.BOLD, 11);
	
	//Phong Chu Lon
	private static final Font LARGE_FONT = new Font("Tahoma", Font.BOLD, 13);
	
	 //Mau Sac De Ve Van Ban .
	private static final Color DRAW_COLOR = new Color(50, 200, 200);//128,192,128
	private Tetris tetris;

	public SidePanel(Tetris tetris){
		this.tetris= tetris;
		
		setPreferredSize(new Dimension(200,490));
		setBackground(Color.BLACK);
	}

	@Override
	public void paintComponent(Graphics g) {	
		super.paintComponent(g);
		
		g.setColor(DRAW_COLOR);
		g.setFont(LARGE_FONT);
		
		int offset;
		
		//Hien Thi De bat Dau, Do Kho,Diem Dat Duoc
		g.setFont(LARGE_FONT);
		g.drawString("Stats", SMALL_INSET, offset = STATS_INSET);
		g.setFont(SMALL_FONT);
		g.drawString("Level: " + tetris.getLevel(), LARGE_INSET, offset += TEXT_STRIDE);
		g.drawString("Score: " + tetris.getScore(), LARGE_INSET, offset += TEXT_STRIDE);
		
		// Hien Thi Hương Dan Cach Dieu Khien,Cach Choi
		g.setFont(LARGE_FONT);
		g.drawString("Controls", SMALL_INSET, offset = CONTROLS_INSET);
		g.setFont(SMALL_FONT);
		g.drawString("A - Move Left", LARGE_INSET, offset += TEXT_STRIDE);
		g.drawString("D - Move Right", LARGE_INSET, offset += TEXT_STRIDE);
		g.drawString("Q - Rotate Anticlockwise", LARGE_INSET, offset += TEXT_STRIDE);
		g.drawString("E - Rotate Clockwise", LARGE_INSET, offset += TEXT_STRIDE);
		g.drawString("S - Drop", LARGE_INSET, offset += TEXT_STRIDE);
		g.drawString("P - Pause Game", LARGE_INSET, offset += TEXT_STRIDE);
		
		//Hien Thi Phan Tiep Theo Cua O Xem Truoc 
		g.setFont(LARGE_FONT);
		g.drawString("Next Piece:", SMALL_INSET, 70);
		
			}
	
}
