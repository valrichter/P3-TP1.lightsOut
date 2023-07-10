package model;

import java.util.Random;

public class Tablero
{
	private boolean[][] tablero;
	private Random random = new Random();
	/*Constructor*/
	public Tablero(int tamanio)
	{	
		this.verificarTablero(tamanio);
		this.tablero = new boolean[tamanio][tamanio];
		this.llenarTablero();
	}

	//llena del tablero de booleans randoms
	private void llenarTablero()
	{
		for(int fila = 0; fila<tablero.length; fila++)
	    { 
	    	for(int columna = 0; columna<tablero[fila].length; columna++)
	    	{ 
				tablero[fila][columna] = this.random.nextBoolean();		
			}
		}
		if(Solver.hasSolution(tablero) == false){
			System.out.println("NO TIENEN SOLUCION");
				llenarTablero();
		} else{
			System.out.println("SI TIENEN SOLUCION");
		}
	}

	public void restartTablero(){
		this.llenarTablero();
	}

	//verifica si el juego esta resuelto
	public boolean estaResuelto()
	{
		for(int fila = 0; fila < tablero.length; fila++)
	    { 
	    	for(int columna = 0; columna < tablero[fila].length; columna++)
	    	{
	    		if(tablero[fila][columna] == true)
	    			return false;
	    	}
	    }
	    return true;
	}
	
	//cambiar el estado de la celda pasada por parametro(f, c) y sus vecinas
	public void cambiarEstado(int fila, int columna)
	{
		//verificamos que los datos sean correctos
		verificarFilaYcolumna(fila);
		verificarFilaYcolumna(columna);
		
		//modificamos el tablero
		cambiarEstadoCelda(fila, columna);
		cambiarCeldaArriba(fila, columna);
		cambiarCeldaAbajo(fila, columna);
		cambiarCeldaDerecha(fila, columna);
		cambiarCeldaIzquierda(fila, columna);
	}
	
	//cambiar el estado de la celda pasada por parametro
	private void cambiarEstadoCelda(int fila, int columna)
	{
		tablero[fila][columna] = !tablero[fila][columna];
	}
	
	private void cambiarCeldaArriba(int fila, int columna)
	{	
		if(fila > 0)
			cambiarEstadoCelda(fila-1, columna);
	}
	
	private void cambiarCeldaAbajo(int fila, int columna)
	{
		if(fila < tablero.length - 1)
			cambiarEstadoCelda(fila+1, columna);
	}
	
	private void cambiarCeldaDerecha(int fila, int columna)
	{	
		if(columna < tablero.length - 1)
			cambiarEstadoCelda(fila, columna+1);
	}
	
	private void cambiarCeldaIzquierda(int fila, int columna)
	{	
		if(columna > 0)
			cambiarEstadoCelda(fila, columna-1);
	}

	private void verificarFilaYcolumna(int i)
	{
		if( i < 0 )
			throw new IllegalArgumentException("El numero no puede ser negativo: " + i);
		
		if( i >= tablero.length )
			throw new IllegalArgumentException("El numero " + i + " deben estar entre 0 y " + (tablero.length -1));
	}
	
	private void verificarTablero(int tamanio)
	{
		if( !(tamanio >= 4) )
			throw new IllegalArgumentException("El tamanio del tablero debe ser >= 4 no, " + tamanio);
	}
	
	public boolean[][] getTablero()
	{
		return this.tablero;
	}
	
	@Override
	//imprime el tablero, '*' = luz prendida y '.' = luz apagada
	public String toString() {
		StringBuilder builder = new StringBuilder();
		
		for(int fila = 0; fila < tablero.length; fila++)
	    { 
	    	for(int columna = 0; columna < tablero[fila].length; columna++)
	    	{ 
	    		if(tablero[fila][columna] == true)
	    		{
	    			builder.append("*");
	    			builder.append(" ");
	    		}
	    		else
	    		{
	    			builder.append(".");
	    			builder.append(" ");
	    		}
	    	}
	    	builder.append("\n");
	    }
		builder.append("\n");
		return builder.toString();
	}
	
}
