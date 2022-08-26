package ancheta.comun;

public class ClasePrueba implements Comparable<ClasePrueba> {
	private int id;
	private String text;
	public ClasePrueba(int idP, String textP){
		setId(idP);
		setText(textP);
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getId() {
		return id;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getText() {
		return text;
	}
	@Override
	public int compareTo(ClasePrueba o) {
		if(id==(o.getId()))
		{
			return 0;
		}
		else if(id<(o.getId()))
		{
			return -1;
		}
		else 
		{
			return 1;
		}
			
	}
	public String toString()
	{
		return text;
	}

}