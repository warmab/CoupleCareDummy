package listcalendar;

public class ListEntrance{
	
	private int idImagen; 
	private String textoEncima; 
	private String textoDebajo; 

	public ListEntrance(int idImagen, String textoEncima, String textoDebajo) { 
	    this.idImagen = idImagen; 
	    this.textoEncima = textoEncima.toString(); 
	    this.textoDebajo = textoDebajo.toString(); 
	}

	public String get_textoEncima() { 
	    return textoEncima; 
	}

	public String get_textoDebajo() { 
	    return textoDebajo; 
	}

	public int get_idImagen() {
	    return idImagen; 
	} 

}
