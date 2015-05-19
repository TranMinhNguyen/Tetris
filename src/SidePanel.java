import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;


public class SidePanel extends JPanel{
	private Tetris tetris;

	public SidePanel(Tetris tetris){
		this.tetris= tetris;
		
		setPreferredSize(new Dimension(200,490));
		setBackground(Color.BLACK);
	}

	@Override
	public void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);
		
		
			}
	
}
