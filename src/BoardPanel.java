import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JPanel;


public class BoardPanel extends JPanel{
	//mau sac cho cac khoi gach
	public static final int COLOR_MIN = 35;
	
	public static final int COLOR_MAX = 255 - COLOR_MIN;
	
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
	
	
	public static final int SHADE_WIDTH = 4;
	//chieu rong cua vien khoi gach
	
	
	
	 // font chu lon
	private static final Font LARGE_FONT = new Font("Tahoma", Font.BOLD, 16);

	 // font chu nho
	private static final Font SMALL_FONT = new Font("Tahoma", Font.BOLD, 12);
	
	
	public static final int PANEL_WIDTH = COL_COUNT * TILE_SIZE + BORDER_WIDTH * 2; 
	//tong chieu rong cua bang dieu khien
	public static final int PANEL_HEIGHT = VISIBLE_ROW_COUNT * TILE_SIZE + BORDER_WIDTH * 2;
	
	private Tetris tetris;
	
	
	private DangKhoiGach[][] tiles;
	
	
	public BoardPanel(Tetris tetris){
		this.tetris=tetris;
		setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
		setBackground(Color.BLACK);
	}

	@Override
	public void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);
		
		
		int col=tetris.getCotHienTai();
		int row=tetris.getHangHienTai();
		int rotation=tetris.getCurrentRotation();
		
		DangKhoiGach type=DangKhoiGach.values()[2];
		for (int i=0; i<type.getSizeGhost();i++)
			for (int j=0;j<type.getSizeGhost();j++)
			{
				if (type.checkTetris(j, i, rotation))
				drawTile(type.getMauCoBan(),type.getMauSang(),type.getMauToi(), j*TILE_SIZE+col*TILE_SIZE ,i*TILE_SIZE+row*TILE_SIZE , g);
			}
		
		
		g.setColor(Color.WHITE);
		g.drawRect(0, 0, TILE_SIZE * COL_COUNT, TILE_SIZE * VISIBLE_ROW_COUNT);
		
		
		g.setColor(Color.WHITE);
		g.drawRect(0, 0, TILE_SIZE * COL_COUNT, TILE_SIZE * VISIBLE_ROW_COUNT);
		
		
		//"Press Enter to Play"
		//g.setFont(SMALL_FONT);
		//g.drawString("Press Enter to Play", CENTER_X - g.getFontMetrics().stringWidth("Press Enter to Play") / 2,300 );
		
		
		//"TETRIS"
		//g.setFont(LARGE_FONT);
		//g.drawString("TETRIS", CENTER_X - g.getFontMetrics().stringWidth("TETRIS") / 2, 150);
		 
		
		//ve duong ke cho bang
		g.setColor(Color.DARK_GRAY);
		for(int x = 0; x < COL_COUNT; x++) {
			for(int y = 0; y < VISIBLE_ROW_COUNT; y++) {
				g.drawLine(0, y * TILE_SIZE, COL_COUNT * TILE_SIZE, y * TILE_SIZE);
				g.drawLine(x * TILE_SIZE, 0, x * TILE_SIZE, VISIBLE_ROW_COUNT * TILE_SIZE);
			}
		}
		
		
		
	}

	private void drawTile(Color mauCoBan, Color mauSang, Color mauToi, int x,
			int y, Graphics g) {
		// TODO Auto-generated method stub
		
		//ve khoi gach co mau co ban
		g.setColor(mauCoBan);
		g.fillRect(x, y, TILE_SIZE, TILE_SIZE);
		
		//tao vien toi phia duoi va ben phai khoi gach
		g.setColor(mauToi);
		g.fillRect(x, y + TILE_SIZE - SHADE_WIDTH, TILE_SIZE, SHADE_WIDTH);
		g.fillRect(x + TILE_SIZE - SHADE_WIDTH, y, SHADE_WIDTH, TILE_SIZE);
		
		//tao vien sang phia duoi va ben trai khoi gach
		g.setColor(mauSang);
		for(int i = 0; i < SHADE_WIDTH; i++) {
			g.drawLine(x, y + i, x + TILE_SIZE - i - 1, y + i);
			g.drawLine(x + i, y, x + i, y + TILE_SIZE - i - 1);
		}
		
	}

	
	
}
