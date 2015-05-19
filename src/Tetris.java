import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;


public class Tetris extends JFrame{

	public Tetris(){
		setSize(500, 600);
		setTitle("Tetris");
		setLayout(new BorderLayout());
		setResizable(false);
	
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		
		CreateMenu();
		
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
	public static void main(String[] args) {
		new Tetris(); 
	
	}

}
