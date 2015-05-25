import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;


public class Tetris extends JFrame{
	//time delay
		private int sec;
		
		private int level;
		private int score;
		
		private boolean isNewGame;
		private boolean isGameOver;
		
		private int cotTiepTheo;
		private int hangTiepTheo;
		private int cotHienTai;
		private int hangHienTai;
		private boolean isPaused;
		private boolean isSpeed;
		
		//khoi gach hien tai
		private DangKhoiGach KhoiHT;
		
		private DangKhoiGach KhoiTiepTheo;
		
		private Random random=new Random();
		
		//huong quay hien tai
		private int currentRotation;
		private int nextRotation;
		private SidePanel sidepanel;
		private BoardPanel boardpanel;
		
		private int speedGame=200000000;
		private int secmax=speedGame;
		
		
		public Tetris(){
			//setSize(500, 600);
			setTitle("Tetris");
			setLayout(new BorderLayout());
			setResizable(false);
		
			setDefaultCloseOperation(EXIT_ON_CLOSE);
			setLocationRelativeTo(null);
			
			CreateMenu();
			this.sidepanel=new SidePanel(this);
			this.boardpanel=new BoardPanel(this);
		
			add(sidepanel,BorderLayout.EAST);
			add(boardpanel,BorderLayout.CENTER);
			
			addKeyListener(new KeyAdapter() {

				@Override
				public void keyPressed(KeyEvent e) {
					// TODO Auto-generated method stub
					
					
					switch(e.getKeyCode()){
					
					//nut Q dung de quay hinh sang trai
					case KeyEvent.VK_Q:
						if (!isPaused){
							int rotation=currentRotation;
							if (rotation==0) rotation=3;
							else rotation--;
							if (boardpanel.checkMove(KhoiHT, cotHienTai, hangHienTai, rotation))
								currentRotation=rotation;
							boardpanel.repaint();
							}
							break;
					//phim E dung de quay hinh sang phai	
					case KeyEvent.VK_E:
						if (!isPaused){
							int rotation=currentRotation;
							if (rotation==3) rotation=0;
							else rotation++;
							if (boardpanel.checkMove(KhoiHT, cotHienTai, hangHienTai, rotation))
								currentRotation=rotation;
							boardpanel.repaint();
						}
						break;
					//phim A dung de dich trai
					case KeyEvent.VK_A:
						if (!isPaused){
							if (boardpanel.checkMove(KhoiHT, cotHienTai-1, hangHienTai, currentRotation)) 
								cotHienTai --;
							}
						break;
					//phim D dung de dich phai
					case KeyEvent.VK_D:
						if (!isPaused){
							if (boardpanel.checkMove(KhoiHT, cotHienTai+1, hangHienTai, currentRotation)) 
								cotHienTai ++;
						}
						break;
					//phim P de tam dung choi game
					case KeyEvent.VK_P:
						if (!isNewGame||!isGameOver)
						isPaused=!isPaused;
					break;	
					//Enter de choi game moi
					case KeyEvent.VK_ENTER:
						if (!isNewGame|| !isGameOver)						
						boardpanel.clear();
						resetGame();
						break;	
					//nhan giu phim S de tang toc cho khoi gach
					case KeyEvent.VK_S:
						secmax=30000000;
						break;
					}
				}
				
				//nha phim S de tro ve toc do binh thuong
				@Override
				public void keyReleased(KeyEvent e) {
					
					switch(e.getKeyCode()) {
					
					case KeyEvent.VK_S:
						secmax=speedGame;
					}
					
				}
				
				
			});
			
			pack();
			setVisible(true);
		}
		
		public void CreateMenu(){
			JMenuBar menubar = new JMenuBar();
			JMenu menu;
			JMenuItem menuitem;
			
			this.setJMenuBar(menubar);
			menu=new JMenu("Game");
		
			
			menuitem = new JMenuItem("New Game");
			menuitem.addActionListener(new MenuHandler(this));
			menu.add(menuitem);
			
			menuitem= new JMenuItem("Exit");
			menuitem.addActionListener(new MenuHandler(this));
			menu.add(menuitem);
			menubar.add(menu);
		}
		
		public class MenuHandler implements ActionListener
		   {
			   Tetris ter;
			   public MenuHandler(Tetris ter)
			   {
				   this.ter = ter;
			   }
			   public void actionPerformed(ActionEvent e) {
				   String s = e.getActionCommand();
				   if(s=="New Game"){
					   boardpanel.clear();resetGame();
				   }
				   if(s == "Exit")
				   {
					   ter.dispose();
				   }
			   }
		   }
		
		public void resetGame(){
			this.isNewGame=false;
			this.isGameOver=false;
			this.level=1;
			this.score=0;
			this.currentRotation=0;
			this.isPaused=false;
			
			this.KhoiHT=DangKhoiGach.values()[random.nextInt(7)];
			this.KhoiTiepTheo=DangKhoiGach.values()[random.nextInt(7)];
			
			this.hangHienTai=this.KhoiHT.getHangVe();
			this.cotHienTai=this.KhoiHT.getCotVe();
			
		}
		
		
		
		public void startGame(){
			resetGame();
			this.isNewGame=true;
				while(true){
				if(isNewGame) System.out.println("Press ENTER to play");
					sec=secmax;
					if((!isPaused) && (!isNewGame) && (!isGameOver)){
						while (sec>0) sec--;
						updateGame();
				}
			}
			
			}
			
		public int getCurrentRotation() {
			return currentRotation;
		}
		public int getNextRotation(){
			return nextRotation;
		}

		public void setGameOver(boolean isGameOver) {
			this.isGameOver = isGameOver;
		}

		public void updateGame(){
			if(boardpanel.checkMove(KhoiHT, cotHienTai, hangHienTai+1, currentRotation))
			this.hangHienTai++;
			else {
				if (hangHienTai+KhoiHT.getIndexTop(currentRotation)<0) {
					isGameOver=true;
					boardpanel.clear();
				}
				else {
				boardpanel.themKhoiGach(this.KhoiHT, this.cotHienTai, this.hangHienTai, this.currentRotation);
				this.KhoiHT=this.KhoiTiepTheo;
				this.KhoiTiepTheo=DangKhoiGach.values()[random.nextInt(7)];
				this.cotHienTai=this.KhoiHT.getCotVe();
				this.hangHienTai=this.KhoiHT.getHangVe();
				this.currentRotation=0;
				
				//Tinh Diem
				int cleared = boardpanel.checkLines();
				if(cleared > 0) {
					score += 50 << cleared;
					
					level=1+score/1000;
					speedGame-=50000000*(score/1000);
				}	
				}
			}
			boardpanel.repaint();
			this.cotTiepTheo=9;
			this.hangTiepTheo=4;
			sidepanel.repaint();
		}
		
		public static void main(String[] args) {
			Tetris tetris =new Tetris(); 
			tetris.startGame();
		}

		public DangKhoiGach getKhoiHT() {
			return KhoiHT;
		}

		public DangKhoiGach getKhoiTiepTheo() {
			return KhoiTiepTheo;
		}
		public int getLevel() {
			
			return level;
		}

		public int getScore() {
			return score;
		}

		public int getCotHienTai() {
			return cotHienTai;
		}

		public int getHangHienTai() {
			return hangHienTai;
		}
		public int getCotTiepTheo(){
			return cotTiepTheo;
		}
		public int getHangTiepTheo(){
			return hangTiepTheo;
		}
		public boolean getIsNewGame(){
			return isNewGame;
		}
		public boolean getIsGameOver(){
			return isGameOver;
		}
		public boolean getIsSpeed(){
			return isSpeed;
		}
		public boolean isPaused() {
			return isPaused;
		}
}
