package entidades;

public class Cliente {
	
	int id;
	String nombre;
	String telefono;
	String email;
	
	public Cliente(int id, String nombre, String email,  String telefono) {
		
		this.id=id;;
		this.nombre=nombre;
		this.telefono = telefono;
		this.email = email;
		
	}

	@Override
	public String toString() {
		return "Cliente [id=" + id + ", nombre=" + nombre + ", telefono=" + telefono + ", email=" + email + "]";
	}
	
	
	

}
