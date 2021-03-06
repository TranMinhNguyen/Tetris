import java.awt.Color;

public enum DangKhoiGach {
	
	/**
	 * Dang KhoiI.
	 */
	KhoiI(new Color(BoardPanel.COLOR_MIN, BoardPanel.COLOR_MAX, BoardPanel.COLOR_MAX), 4, 4, 1, new boolean[][] {
		{
			false,	false,	false,	false,
			true,	true,	true,	true,
			false,	false,	false,	false,
			false,	false,	false,	false,
		},
		{
			false,	false,	true,	false,
			false,	false,	true,	false,
			false,	false,	true,	false,
			false,	false,	true,	false,
		},
		{
			false,	false,	false,	false,
			false,	false,	false,	false,
			true,	true,	true,	true,
			false,	false,	false,	false,
		},
		{
			false,	true,	false,	false,
			false,	true,	false,	false,
			false,	true,	false,	false,
			false,	true,	false,	false,
		}
	}),
	
	/**
	 * Dang KhoiJ.
	 */
	KhoiJ(new Color(BoardPanel.COLOR_MIN, BoardPanel.COLOR_MIN, BoardPanel.COLOR_MAX), 3, 3, 2, new boolean[][] {
		{
			true,	false,	false,
			true,	true,	true,
			false,	false,	false,
		},
		{
			false,	true,	true,
			false,	true,	false,
			false,	true,	false,
		},
		{
			false,	false,	false,
			true,	true,	true,
			false,	false,	true,
		},
		{
			false,	true,	false,
			false,	true,	false,
			true,	true,	false,
		}
	}),
	
	/**
	 * Dang KhoiL.
	 */
	KhoiL(new Color(BoardPanel.COLOR_MAX, 127, BoardPanel.COLOR_MIN), 3, 3, 2, new boolean[][] {
		{
			false,	false,	true,
			true,	true,	true,
			false,	false,	false,
		},
		{
			false,	true,	false,
			false,	true,	false,
			false,	true,	true,
		},
		{
			false,	false,	false,
			true,	true,	true,
			true,	false,	false,
		},
		{
			true,	true,	false,
			false,	true,	false,
			false,	true,	false,
		}
	}),
	
	/**
	 * Dang KhoiO.
	 */
	KhoiO(new Color(BoardPanel.COLOR_MAX, BoardPanel.COLOR_MAX, BoardPanel.COLOR_MIN), 2, 2, 2, new boolean[][] {
		{
			true,	true,
			true,	true,
		},
		{
			true,	true,
			true,	true,
		},
		{	
			true,	true,
			true,	true,
		},
		{
			true,	true,
			true,	true,
		}
	}),
	
	/**
	 * Dang KhoiS.
	 */
	KhoiS(new Color(BoardPanel.COLOR_MIN, BoardPanel.COLOR_MAX, BoardPanel.COLOR_MIN), 3, 3, 2, new boolean[][] {
		{
			false,	true,	true,
			true,	true,	false,
			false,	false,	false,
		},
		{
			false,	true,	false,
			false,	true,	true,
			false,	false,	true,
		},
		{
			false,	false,	false,
			false,	true,	true,
			true,	true,	false,
		},
		{
			true,	false,	false,
			true,	true,	false,
			false,	true,	false,
		}
	}),
	
	/**
	 * Dang KhoiT.
	 */
	KhoiT(new Color(128, BoardPanel.COLOR_MIN, 128), 3, 3, 2, new boolean[][] {
		{
			false,	true,	false,
			true,	true,	true,
			false,	false,	false,
		},
		{
			false,	true,	false,
			false,	true,	true,
			false,	true,	false,
		},
		{
			false,	false,	false,
			true,	true,	true,
			false,	true,	false,
		},
		{
			false,	true,	false,
			true,	true,	false,
			false,	true,	false,
		}
	}),
	
	/**
	 * Dang KhoiZ.
	 */
	KhoiZ(new Color(BoardPanel.COLOR_MAX, BoardPanel.COLOR_MIN, BoardPanel.COLOR_MIN), 3, 3, 2, new boolean[][] {
		{
			true,	true,	false,
			false,	true,	true,
			false,	false,	false,
		},
		{
			false,	false,	true,
			false,	true,	true,
			false,	true,	false,
		},
		{
			false,	false,	false,
			true,	true,	false,
			false,	true,	true,
		},
		{
			false,	true,	false,
			true,	true,	false,
			true,	false,	false,
		}
	});
	
	private boolean[][] mangGach;
	private int hang;
	private int sizeGhost;
	private int cot;
	private Color mauCoBan;
	private Color mauSang;
	private Color mauToi;
	
	//hang de ve~ khoi gach
	private int hangVe;
	
	private int cotVe;
	
	private DangKhoiGach(Color color, int sizeGhost, int cot, int hang, boolean[][] mangGach){
		this.mauCoBan = color;
		this.mauSang=color.brighter();
		this.mauToi=color.darker();
		this.sizeGhost = sizeGhost;
		this.cot = cot;
		this.hang = hang;
		this.mangGach = mangGach;
			
		this.hangVe=-2;
		this.cotVe=3;
	}
	
	public int getHangVe() {
		return hangVe;
	}

	public int getCotVe() {
		return cotVe;
	}

	public boolean checkTetris(int cot,int hang,int huongXoay){
		return mangGach[huongXoay][hang*sizeGhost+cot];
	}

	public int getHang() {
		return hang;
	}

	public int getSizeGhost() {
		return sizeGhost;
	}

	public int getCot() {
		return cot;
	}

	public Color getMauCoBan() {
		return mauCoBan;
	}

	public Color getMauSang() {
		return mauSang;
	}

	public Color getMauToi() {
		return mauToi;
	}
	
	public int getIndexRight(int rotation){
		for (int i=sizeGhost-1;i>=0;i--)
			for (int j=0;j<sizeGhost;j++)
				if(checkTetris(i, j, rotation)) return i;
		return -1;
	}

	public int getIndexLeft(int rotation){
		for (int i=0;i<sizeGhost;i++)
			for (int j=0;j<sizeGhost;j++)
				if(checkTetris(i, j, rotation)) return i;
		return -1;
	}
	
	public int getIndexEnd(int rotation){
		for (int i=sizeGhost-1;i>=0;i--)
			for (int j=0;j<sizeGhost;j++)
				if(checkTetris(j, i, rotation)) return i;
		return -1;
	}
	
	public int getIndexTop(int rotation){
		for(int i=0;i<sizeGhost;i++)
			for(int j=0;j<sizeGhost;j++)
				if (checkTetris(j,i,rotation)) return i;
		return -1;
		
	}
	
	
	

}
