package ai;

import it.unical.mat.embasp.languages.Id;
import it.unical.mat.embasp.languages.Param;

@Id("cella")
public class Cella {
	
	public Cella(int x, int y, int colore) {
		super();
		this.x = x;
		this.y = y;
		this.colore = colore;
	}
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + colore;
		result = prime * result + x;
		result = prime * result + y;
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cella other = (Cella) obj;
		if (colore != other.colore)
			return false;
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "Cella [x=" + x + ", y=" + y + ", colore=" + colore + "]";
	}


	public int getX() {
		return x;
	}


	public void setX(int x) {
		this.x = x;
	}


	public int getY() {
		return y;
	}


	public void setY(int y) {
		this.y = y;
	}


	public int getColore() {
		return colore;
	}


	public void setColore(int colore) {
		this.colore = colore;
	}


	//primo parametro da settare da embasp
    @Param(0)
    private int x;
    
    
    //secondo parametro da settare da embasp
    @Param(1) ///////////////////////////////////////////////////////////////////////////////////////////////////
    private  int y;
    

	//terzo parametro da settare da embasp
    @Param(2) ////////////////////////////////////////////////////////////////////////////////////////////////////
    private  int colore;
	
}
