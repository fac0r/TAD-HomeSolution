package entidades;

import java.util.LinkedList;

public class ColaEmpleadosLibres {
    
   
    private LinkedList<Empleado> empleados;
    
 
    public ColaEmpleadosLibres() {
        this.empleados = new LinkedList<>();
    }
    

    public void crear() {
        this.empleados = new LinkedList<>();
    }
    
  
    public void agregarEmpleado(Empleado empleado) {
        if (empleado != null && !empleado.isEstado()) {
            this.empleados.addLast(empleado);
        }
    }
    
    
    public Empleado desagregarEmpleado() {
        if (!estaVacia()) {
            return this.empleados.removeFirst();
        }
        return null;
    }
    
   
    public boolean estaVacia() {
        return this.empleados.isEmpty();
    }
    
    public int informarCantidadDeEmpleadosEnCola() {
        return this.empleados.size();
    }
    
    public Empleado consultarProximo() {
        if (!estaVacia()) {
            return this.empleados.getFirst();
        }
        return null;
    }
 
    
 
}