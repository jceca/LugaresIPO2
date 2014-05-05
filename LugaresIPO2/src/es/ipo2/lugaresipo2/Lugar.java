package es.ipo2.lugaresipo2;

import es.ipo2.lugaresipo2.TipoLugar;

public class Lugar {
    private String nombre;
    private String direccion;
    private String foto;
    
    //nuevo argumento con tipo enum
    private TipoLugar tipo;

    private int telefono;
    private String url;
    private String comentario;
    private long fecha;
    private float valoracion;

	public Lugar(String nombre, String direccion, TipoLugar tipo, int telefono,
			String url, String comentario, int valoracion) {
	          	fecha = System.currentTimeMillis();
	          	this.tipo = tipo;
	          	this.nombre = nombre;
	          	this.direccion = direccion;
	          	this.telefono = telefono;
	          	this.url = url;
	          	this.comentario = comentario;
	          	this.valoracion = valoracion;
	}

	public Lugar() {
        fecha = System.currentTimeMillis();
        tipo = TipoLugar.OTROS;
    }
    
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	public int getTelefono() {
		return telefono;
	}

	public void setTelefono(int telefono) {
		this.telefono = telefono;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	public long getFecha() {
		return fecha;
	}

	public void setFecha(long fecha) {
		this.fecha = fecha;
	}

	public float getValoracion() {
		return valoracion;
	}

	public void setValoracion(float valoracion) {
		this.valoracion = valoracion;
	}

	public TipoLugar getTipo() {
		return tipo;
	}

	public void setTipo(TipoLugar tipo) {
		this.tipo = tipo;
	}

	@Override
	public String toString() {
		return "Lugar [nombre=" + nombre + ", direccion=" + direccion
				+ ", foto=" + foto + ", telefono=" + telefono + ", url=" + url
				+ ", comentario=" + comentario + ", fecha=" + fecha
				+ ", valoracion=" + valoracion + "]";
	}
}