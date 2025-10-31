package entidades;

public class Tarea {
	
	
	
		private String titulo;
	    private String descripcion;
	    private double duracion;
	    private Empleado responsable;
	    private boolean estado;
	    
	

		public Tarea(String titulo, String descripcion, double duracion) {
	        this.titulo = titulo;
	        this.descripcion = descripcion;
	        this.duracion = duracion;
	        this.setResponsable(null);
	        this.setEstado(false); // Por defecto, la tarea no está completada
	    }


		public String getTitulo() {
			return titulo;
		}


		public String getDescripcion() {
			return descripcion;
		}


		public double getDuracion() {
			return duracion;
		}


		public Empleado getResponsable() {
			return responsable;
		}


		public void setResponsable(Empleado responsable) {
			this.responsable = responsable;
		}


		public boolean isEstado() {
			return estado;
		}


		public void setEstado(boolean estado) {
			this.estado = estado;
		}

	    
	    @Override
		public String toString() {
			return "Tarea [titulo=" + titulo + ", descripcion=" + descripcion + ", duracion=" + duracion
					+ ", responsable=" + responsable + ", estado=" + estado + "]";
		}

}
