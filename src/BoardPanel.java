import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JPanel;


public class BoardPanel extends JPanel{
	private static final int BORDER_WIDTH = 5; 
	//chieu rong cua vien quanh bang game
	
	private static final int VISIBLE_ROW_COUNT = 20; 
	// so luong hang hien thi tren bang
	
	private static final int HIDDEN_ROW_COUNT = 2; 
	//so luong hang an^?
	
	public static final int ROW_COUNT = VISIBLE_ROW_COUNT + HIDDEN_ROW_COUNT; 
	//tong so hang cua bang
	
	public static final int TILE_SIZE = 24; 
	//so luong pixel 1 vien gach chiem
	
	public static final int COL_COUNT = 10; 
	//so luong cot tren bang
	
	private static final int CENTER_X = COL_COUNT * TILE_SIZE / 2;
	//toa do hien thi trung tam X
	
	 // font chu lon
	private static final Font LARGE_FONT = new Font("Tahoma", Font.BOLD, 16);

	 // font chu nho
	private static final Font SMALL_FONT = new Font("Tahoma", Font.BOLD, 12);
	
	
	public static final int PANEL_WIDTH = COL_COUNT * TILE_SIZE + BORDER_WIDTH * 2; 
	//tong chieu rong cua bang dieu khien
	public static final int PANEL_HEIGHT = VISIBLE_ROW_COUNT * TILE_SIZE + BORDER_WIDTH * 2;
	
	private Tetris tetris;
	
	public BoardPanel(Tetris tetris){
		this.tetris=tetris;
		setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
		setBackground(Color.BLACK);
	}

	@Override
	public void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);
		
		g.setColor(Color.WHITE);
		g.drawRect(0, 0, TILE_SIZE * COL_COUNT, TILE_SIZE * VISIBLE_ROW_COUNT);
		//"Press Enter to Play"
		g.setFont(SMALL_FONT);
		g.drawString("Press Enter to Play", CENTER_X - g.getFontMetrics().stringWidth("Press Enter to Play") / 2,300 );
		//"TETRIS"
		g.setFont(LARGE_FONT);
		g.drawString("TETRIS", CENTER_X - g.getFontMetrics().stringWidth("TETRIS") / 2, 150);
		 //ve duong ke cho bang
		g.setColor(Color.DARK_GRAY);
		for(int x = 0; x < COL_COUNT; x++) {
			for(int y = 0; y < VISIBLE_ROW_COUNT; y++) {
				g.drawLine(0, y * TILE_SIZE, COL_COUNT * TILE_SIZE, y * TILE_SIZE);
				g.drawLine(x * TILE_SIZE, 0, x * TILE_SIZE, VISIBLE_ROW_COUNT * TILE_SIZE);
			}
		}
	}
	
	
}
