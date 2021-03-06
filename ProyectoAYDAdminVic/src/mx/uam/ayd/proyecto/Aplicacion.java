package mx.uam.ayd.proyecto;

import mx.uam.ayd.proyecto.datos.DAOArticulo;

import mx.uam.ayd.proyecto.datos.DAOArticuloBD;
import mx.uam.ayd.proyecto.datos.DAOArticuloEnAlmacen;
import mx.uam.ayd.proyecto.datos.DAOArticuloEnAlmacenBD;
import mx.uam.ayd.proyecto.negocio.ServicioAlmacen;
import mx.uam.ayd.proyecto.negocio.ServicioAlmacenImpl;
import mx.uam.ayd.proyecto.negocio.ServicioArticulo;
import mx.uam.ayd.proyecto.negocio.ServicioArticuloImpl;
import mx.uam.ayd.proyecto.negocio.ServicioUsuario;
import mx.uam.ayd.proyecto.presentacion.ControlEnviarProductoAResurtir;
import mx.uam.ayd.proyecto.presentacion.ControlPrincipal;
import mx.uam.ayd.proyecto.presentacion.ControlRezago;

/**
 * 
 * Clase principal que arranca la aplicacion 
 * 
 * @author humbertocervantes
 *
 */
public class Aplicacion {
	
	// Modulos de la aplicacion
	private static ControlPrincipal controlPrincipal;
	private static ControlRezago controlrezago;
	private static DAOArticulo daoarticulo;
	private static ServicioArticulo servicioarticulo;
	private static DAOArticuloEnAlmacen daoAlmacen;
	private static ServicioAlmacen servicioAlmacen;
	private static ServicioUsuario servicioUsuario;
	private static ControlEnviarProductoAResurtir controlResurtir;
	
	
	
	
	
	/**
	 * Arranca la aplicación
	 * 
	 * @param args argumentos de la linea de comandos
	 */
	public static void main(String[] args) {
		
		creaYConectaModulos();

		controlPrincipal.inicia();
	}
	
	/**
	 * Conecta los modulos de la aplicacion.
	 *
	 */
	private static void creaYConectaModulos() {
		// Conecta los modulos
		
		daoarticulo=new DAOArticuloBD();
		daoAlmacen=new DAOArticuloEnAlmacenBD();
		
		servicioarticulo=new ServicioArticuloImpl(daoarticulo);
		servicioAlmacen=new ServicioAlmacenImpl(daoAlmacen, daoarticulo);
		
		controlrezago=new ControlRezago( servicioAlmacen, servicioarticulo);
		controlPrincipal = new ControlPrincipal( controlrezago,controlResurtir);
		
		controlResurtir=new ControlEnviarProductoAResurtir(servicioUsuario,servicioAlmacen);

	}
	

}