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
	
	public static final int SHADE_WIDTH = 4;
	//chieu rong cua vien khoi gach
	
	//toa do x cua o vuong 
	private static final int SQUARE_CENTER_X = 130;	
	
	//toa do y cua o vuong
	private static final int SQUARE_CENTER_Y = 65;	
	
	
	//giam so pixels cho 1 vien gach di 1 nua
	private static final int TILE_SIZE = BoardPanel.TILE_SIZE >> 1; 
	
	
	//so hang cot de hien thi khoi tiep theo cua o trong
	private static final int TILE_COUNT = 5;	
	
	
	//kich thuoc cua o trong
	private static final int SQUARE_SIZE = (TILE_SIZE * TILE_COUNT >> 1); 
	
	
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
		
		int col=tetris.getCotTiepTheo();
		int row=tetris.getHangTiepTheo();
		int rotation=tetris.getNextRotation();
		DangKhoiGach type=tetris.getKhoiTiepTheo();
		for (int i=0; i<type.getSizeGhost();i++)
			for (int j=0;j<type.getSizeGhost();j++)
			{
				if (type.checkTetris(j, i, rotation))
				drawTile(type.getMauCoBan(),type.getMauSang(),type.getMauToi(), j*TILE_SIZE+col*TILE_SIZE ,i*TILE_SIZE+row*TILE_SIZE , g);
			}
		
		
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
		g.drawRect(SQUARE_CENTER_X - SQUARE_SIZE, SQUARE_CENTER_Y - SQUARE_SIZE, SQUARE_SIZE * 2, SQUARE_SIZE * 2);
			}
	
	public void drawTile(DangKhoiGach type,int x,int y,Graphics g){
		drawTile(type.getMauCoBan(),type.getMauSang(),type.getMauToi(),x,y,g);
	}

	public void drawTile(Color mauCoBan, Color mauSang, Color mauToi, int x,
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
