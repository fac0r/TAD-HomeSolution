package entidades;

public class Tarea {
	
	
	

		private String titulo;
	    private String descripcion;
	    private double duracion;
	    private Empleado responsable;
	    private String estado;
	    
	

		public Tarea(String titulo, String descripcion, double duracion) {
	        this.titulo = titulo;
	        this.descripcion = descripcion;
	        this.duracion = duracion;
	        this.setResponsable(null);
	        this.setEstado(Estado.pendiente); // Por defecto, la tarea no está completada
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
		

		public void setDuracion(double duracion) {
		this.duracion = duracion;
	}


		public Empleado getResponsable() {
			return responsable;
		}


		public void setResponsable(Empleado responsable) {
			this.responsable = responsable;
		}


		public String isEstado() {
			return estado;
		}


		public void setEstado(String pendiente) {
			this.estado = pendiente;
		}

	    
	    @Override
		public String toString() {
			return "Tarea [titulo=" + titulo + ", descripcion=" + descripcion + ", duracion=" + duracion
					+ ", responsable=" + responsable + ", estado=" + estado + "]";
		}

}
