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
			
			
			private int cotHienTai;
			private int hangHienTai;
			private boolean isPaused;
			
			//khoi gach hien tai
			private DangKhoiGach KhoiHT;
			
			private DangKhoiGach KhoiTiepTheo;
			
			private Random random=new Random();
			
			//huong quay hien tai
			private int currentRotation;
			private SidePanel sidepanel;
			private BoardPanel boardpanel;
			
			
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
							isPaused=!isPaused;
						break;	
						
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
				menu.setMnemonic(KeyEvent.VK_G);
				menuitem = new JMenuItem("New Game");
				menu.add(menuitem);
				menuitem= new JMenuItem("Exit");
				menuitem.setMnemonic(KeyEvent.VK_E);
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
					   if(s == "Exit")
					   {
						   ter.dispose();
					   }
				   }
			   }
			
			public void startGame(){
				this.level=1;
				this.score=0;
				this.hangHienTai=2;
				this.cotHienTai=3;
				this.currentRotation=0;
				this.isPaused=false;
				
				this.KhoiHT=DangKhoiGach.values()[random.nextInt(7)];
				this.KhoiTiepTheo=DangKhoiGach.values()[random.nextInt(7)];
				
					while(true){
						if(!isPaused){
							sec=200000000;
							while (sec>0) sec--;
							updateGame();
					}
				}
				}
				
			public int getCurrentRotation() {
				return currentRotation;
			}

			public void updateGame(){
				if(boardpanel.checkMove(KhoiHT, cotHienTai, hangHienTai+1, currentRotation))
				this.hangHienTai++;
				else {
					boardpanel.themKhoiGach(this.KhoiHT, this.cotHienTai, this.hangHienTai, this.currentRotation);
					this.KhoiHT=this.KhoiTiepTheo;
					this.KhoiTiepTheo=DangKhoiGach.values()[random.nextInt(7)];
					this.cotHienTai=this.KhoiHT.getCotVe();
					this.hangHienTai=this.KhoiHT.getHangVe();
					this.currentRotation=0;
				}
				boardpanel.repaint();
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
	
}
