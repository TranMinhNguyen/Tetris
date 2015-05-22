import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;


public class Tetris extends JFrame{
	//time delay
	private int sec;
	
	private int level;
	private int score;
	private int cotHienTai;
	private int hangHienTai;
	
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
					if (currentRotation==0) currentRotation=3;
					else currentRotation--;
					
					boardpanel.repaint();
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
		
		while(true){
			
			
			sec=350000000;
			while (sec>0) sec--;
			updateGame();
			
		}
	}
	
	

	public int getCurrentRotation() {
		return currentRotation;
	}

	public void updateGame(){
		this.hangHienTai++;
		boardpanel.repaint();
	}
	
	public static void main(String[] args) {
		Tetris tetris =new Tetris(); 
		tetris.startGame();
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
