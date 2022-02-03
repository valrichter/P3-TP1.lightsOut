package view;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JToggleButton;
import javax.swing.WindowConstants;

import presenter.ViewListener;


public class View
{
    //la lista de observers
    private ArrayList<ViewListener> listeners = new ArrayList<ViewListener>();;
    private JFrame frame = new JFrame();
    private JToggleButton[][] botones;
    
    public View(int tamanio)
    {
    	this.botones = new JToggleButton[tamanio][tamanio];
    	
        frame.setBounds(100, 100, 500, 500);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(tamanio, tamanio, 2, 2));
        
        //creo los botones y los agrego al frame
        setearBotones();
        //verficamos si alguno boton fue tocado
        verificarActionsListeners();
        
        frame.setVisible(true);
    }
    
    //agrega un observer/escucha a la lista
    public void addListener(ViewListener listener)
    {
        listeners.add(listener);
    }
    
	//notificamos a todos los observers/listeners para que actualicen el modelo
    private void notificarListenersDelCambio(int f, int c)
    {
        for (ViewListener listener : listeners)
        {
            listener.botonPresionado(f, c);
        }
    }
    
    
    //verifica los actionsListeners de todos los botones la matriz de botones
    private void verificarActionsListeners()
    {
    	for(int fila = 0; fila < botones.length; fila++)
	    { 
	    	for(int columna = 0; columna < botones[fila].length; columna++)
	    	{ 
	    		actionListenerButton(botones[fila][columna], fila, columna);
	    	}
	    }
    }
    
    //verifica el actionListener de un boton en particular del tablero
    private void actionListenerButton(JToggleButton button, int f, int c)
    {
    	button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	
            	//notificamos al presenter
            	notificarListenersDelCambio(f, c);
            }
        });
    }
    
    //actualiza el estado de los botones
    public void actualizarBotones(boolean[][] tableroActualilzado)
    {
    	for(int fila = 0; fila < botones.length; fila++)
	    { 
	    	for(int columna = 0; columna < botones[fila].length; columna++)
	    	{ 
	    		botones[fila][columna].setSelected(tableroActualilzado[fila][columna]);
	    	}
	    }
    }
    
    //inicializa los toggleButton dentro de la matriz de botones
    private void setearBotones()
    {
    	for(int fila = 0; fila < botones.length; fila++)
	    { 
	    	for(int columna = 0; columna < botones[fila].length; columna++)
	    	{ 
	    		botones[fila][columna] = crearBoton();
	    	}
	    }
    }
    
    //crear un boton y lo agrega al frame
    private JToggleButton crearBoton()
    {
    	//creo el boton
    	JToggleButton button = new JToggleButton();
    	button.setBackground(Color.BLACK);
    	button.setSelected(false);
    	//agrego el nuevo boton al frame
    	frame.add(button);
		return button;
    }
    
    //lanza un mensaje de game over
    public void gameOver()
    {
    	JOptionPane.showMessageDialog(frame, "Game Over");
    }
    
}