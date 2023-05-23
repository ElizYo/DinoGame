import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class EventosAreaJuego implements KeyListener {
	  private AreaJuego areaJuego;
	  private Dinosaurio dinosaurio;

	    public EventosAreaJuego(AreaJuego areaJuego) {
	        this.areaJuego = areaJuego;
	        this.areaJuego.addKeyListener(this);
	        this.areaJuego.setFocusable(true);
	    }

	    @Override
	    public void keyPressed(KeyEvent e) {
	        int keyCode = e.getKeyCode();
	        Dinosaurio dinosaurio = areaJuego.getDinosaurio(); 

	        if (dinosaurio != null) {
	            dinosaurio.mover(keyCode);
	            areaJuego.repaint(); 
	        }
	    }

	    @Override
	    public void keyReleased(KeyEvent e) {
	    }

	    @Override
	    public void keyTyped(KeyEvent e) {
	    }

	    public Dinosaurio getDinosaurio() {
	        return dinosaurio;
	    }

}
