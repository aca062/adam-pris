package mochila;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Map.Entry;

public class MochilaNRP {
	private int esfuerzoMax;
	private ArrayList<String> listadoResult;
	private ArrayList<Requisito> requisitos; //Lista inicial de requisitos
	private int[] resultadoMochila; //Lista indicaci�n de requisitos a escoger (RESULTADO METODO MOCHILA)

	public MochilaNRP(int esfuerzoMax) {
		listadoResult = new ArrayList<String>();
		requisitos = new ArrayList<Requisito>();
		this.esfuerzoMax = esfuerzoMax;
	}

/**
 * Devuelve el listado resultado en forma de cadena una vez introducidos los requisitos
 *  obtenidos en el resultado de la mochila.
 * @return String correspondiente al listado resultados
 */
	public String obtenerRequisitosAIntroducir() {
		this.introducirRequisitos();
		return listadoResult.toString();
	}
	
/**
 * Metodo introducirRequisitos, introduce los requisitos en el listado resultado teniendo en cuenta el array de enteros
 * resultadoMochila.
 */
	private void introducirRequisitos() {

		int[] requisitosEscogidos = this.mochilaBT();

		for (int i = 0; i < requisitosEscogidos.length; i++) { // recorremos la solucion optima indicada por el metodo
																// mochila
			if (requisitosEscogidos[i] == 1) { // anadimos los indicados al listado resultado
				this.listadoResult.add(this.requisitos.get(i).getNombre());
			}
		}
	}

	/**
	 * Metodo mochilaBT, usa backtracking para obtener la solucion optima a nuestro
	 * problema mochila 0/1
	 * 
	 * @return array int[], teniendo un 1 si el objeto de la posicion
	 *         correspondiente es escogido y un 0 si no lo es
	 */
	public int[] mochilaBT() {
		int nivel = 0; // empezamos nivel en 0 para poder realizar accesos correctos a los arrays
		int s[] = new int[requisitos.size() + 1]; // espacio de soluciones
		int soa[] = Arrays.copyOfRange(s, 0, s.length); // espacio de soluciones optimas actuales
		double voa = -10000000000000000.0; // valor optimo actual
		double bact = 0; // beneficio actual
		double pact = 0; // peso actual

		// inicializamos el espacio de soluciones a -1
		for (int i = 0; i < s.length; i++) {
			s[i] = -1;
		}

		while (nivel > -1) { // mientras que no hayamos recorrido el arbol entero

			//**********************************************************************************************************
			// LLAMADA A GENERAR
			s[nivel] = s[nivel] + 1;
			if (nivel < this.requisitos.size() && s[nivel] == 1) { // actualizamos el estado del nodo (si se escoge o
																	// no,si esta explorado o no(-1)...)
				pact = pact + this.requisitos.get(nivel).getEsfuerzo(); // actualizamos el peso y el beneficio actuales
																		// segun
																		// si el nodo
				bact = bact + this.requisitos.get(nivel).getSatisfaccion(); // se escoge o no (con el valor de s[nivel])
			}
			//**********************************************************************************************************
			
			if (solucion(nivel, s, pact) && (bact > voa)) { // si encontramos una solucion mejor actualizamos las sol
															// optimas actuales
				voa = bact; // FUNCION A OPTIMIZAR
				soa = Arrays.copyOfRange(s, 0, s.length);
			} else if (criterio(nivel, pact, bact, voa, s)) // sigue siendo una solucion (cumple restricciones)
				nivel++;

			else {
				while ((!masHermanos(nivel, s)) && (nivel > -1)) {// mientras no queden nodos por explorar en la rama
					// LLAMADA A RETROCEDER
					if (nivel != this.requisitos.size()) {
						pact = pact - this.requisitos.get(nivel).getEsfuerzo() * s[nivel]; // reestablecemos el peso y
																							// el
																							// valor del nivel anterior
						bact = bact - this.requisitos.get(nivel).getSatisfaccion() * s[nivel];
					}
					s[nivel] = -1; // cambiamos el estado del nodo
					nivel -= 1; // retrocedemos un nivel
				}
			}
		}
		this.resultadoMochila = Arrays.copyOfRange(soa, 0, soa.length);
		return soa; // devolvemos la solucion optima
	}

	@SuppressWarnings("unused")
	private void retroceder(int nivel, int[] s, double pact, double bact) {
	}

	/**
	 * Metodo masHermanos, indica si el nodo actual es un puente a mas nodos a
	 * explorar
	 * 
	 * @param nivel Nivel actual del arbol debusqueda
	 * @param s     conjunto solucion actual
	 * @return boolean true si tiene mas hermanos
	 */
	private boolean masHermanos(int nivel, int[] s) {
		if (nivel == -1)
			return false;
		return s[nivel] < 1; // comprobamos que se tengan nodos por explorar en la misma rama
	}

	/**
	 * Metodo criterio, indicara si la solucion que esta siendo llevada a cabo
	 * cumple las restricciones impuestas o por el contrario no merece la pena
	 * seguir explorandola
	 * 
	 * @param nivel nivel actual del arbol de busqueda
	 * @param pact  peso actual de la mochila
	 * @param s 
	 * @return bollean true si cumple las restricciones para ser una solucion
	 */
	private boolean criterio(int nivel, double pact, double bact, double voa, int[] s) {
		if ((nivel < this.requisitos.size()) 	/*Si aun quedan requisitos por explorar*/ 
				&& (pact <= this.esfuerzoMax)		/*Si no sobrepasamos el peso*/ 
				&& bact >= voa) {					/*Si la solucion es mejor que la anterior*/ 
							return cumplimientoRelaciones(nivel,s);						/*Evaluar que se cumplen requisitos de dependencias y exclusi�n*/
		}
		return false;
	}
	/**
	 * 
	 * @param nivel
	 * @param s
	 * @return
	 */
	private boolean cumplimientoRelaciones(int nivel, int[] s) {
		Requisito req = null;
		for(int i = 0; i<=nivel;i++) {
			if(s[i]==1) {	//Si el requisito se encuentra en la solucion exploramos sus dependencias
				req = this.requisitos.get(i);
				if(req.requisitoRelacion == null) continue;//Si no tiene relaciones continuamos iterando
				for (Entry<Requisito, String> reqRelacion : req.requisitoRelacion.entrySet()) {//Si tiene relaciones comprobamos
																								//que se cumplan
					if(reqRelacion.getValue()=="Dependencia") {	//RELACIONES DE DEPENDENCIA
						if(reqRelacion.getKey().isCombinado==true? s[this.requisitos.indexOf(reqRelacion.getKey().padre)]!=1 :
							s[this.requisitos.indexOf(reqRelacion.getKey())] != 1) {//El requisito del que depende o su 
							return false;											//padre en caso de ser combinado deben de estar en la sol
						}
					}
					else if(reqRelacion.getValue()=="Exclusion") {//RELACIONES DE EXCLUSION
						if(reqRelacion.getKey().isCombinado==true? s[this.requisitos.indexOf(reqRelacion.getKey().padre)]==1 :
							s[this.requisitos.indexOf(reqRelacion.getKey())] == 1) {//El requisito que excluye o su 
							return false;											//padre en caso de ser combinado NO deben de estar en la sol
						}
					}
					else //RELACIONES DE COMBINACION YA ESTAN COMPROBADAS
						continue;
				}
			}
		}
		return true;
	}

	/**
	 * Metodo solucion, indica si un conjunto solucion actual puede ser una solucion
	 * valida
	 * 
	 * @param nivel nivel actual
	 * @param s     conjunto de soluciones actuales
	 * @param pact  peso actual
	 * @return boolean true si es una solucion valida
	 */
	private boolean solucion(int nivel, int[] s, double pact) {
		return ((nivel == requisitos.size()) && (pact <= this.esfuerzoMax)); // comprobamos si cumple los requisitos
																				// para ser una solucion
	}

	// !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
	public void cargarListaRequisitos(ArrayList<Requisito> listaAniadir) {
		this.requisitos.clear();
		for (int i = 0; i < listaAniadir.size(); i++) {
			if (listaAniadir.get(i).requisitoRelacion == null
					|| !listaAniadir.get(i).requisitoRelacion.containsValue("Combinacion")) {// Si no es combinado se
																								// aniade directamente
				this.requisitos.add(listaAniadir.get(i));
			}

			else { // Si es combinado creamos un RC, aniadimos todos los adyacentes
				RequisitoCombinado rc = new RequisitoCombinado("RC"); // y borramos de la lista de requisitos los ya
																		// aniadidos al RC
				rc.aniadirCombinados(listaAniadir.get(i));
				for (Requisito reqCombinados : rc.combinados) {
					listaAniadir.remove(reqCombinados);
				}
				this.requisitos.add(rc); // Aniadimos el rc
				i--;
			}
		}
	}

	public ArrayList<Requisito> getRequisitos() {
		return this.requisitos;
	}

}