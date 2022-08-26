package ancheta.comun;

public class ClasePruebaGrafo implements Comparable<ClasePruebaGrafo> {
	private String id;
	private String text;
	public ClasePruebaGrafo(String idP, String textP){
		setId(idP);
		setText(textP);
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getId() {
		return id;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getText() {
		return text;
	}
	@Override
	public int compareTo(ClasePruebaGrafo o) {
		return text.compareTo(o.getText());			
	}
	public String toString()
	{
		return id+"";
	}

}