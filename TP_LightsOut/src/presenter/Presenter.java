package presenter;

import model.Tablero;
import view.View;

public class Presenter implements ViewListener
{
    private View view;
    private Tablero tablero;

    public Presenter(View view, Tablero tablero )
    {
        this.view = view;
        view.addListener(this);
        this.tablero = tablero;    
    }

    @Override
    public void botonPresionado(int f, int c)
    {
        //actualizamos el model
    	tablero.cambiarEstado(f, c);
        //actualizamos el view
        view.actualizarBotones(tablero.getTablero());
        
        //si esta resuelto finalizamos el juego
        if(tablero.estaResuelto())
        	view.gameOver();;
    }
    
}
