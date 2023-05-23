import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.awt.Font;
import javax.swing.ImageIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

public class AreaJuego extends Canvas {
    private Dinosaurio dinosaurio;
    private Image fondo;
    private int posFondo;
    private Image buffer;
    private ArrayList<Cuello> arrayCuello;
    private int puntuacion;
    private int numColisiones = 0;
    private int velocidadCuello=3;
    private int numCuellosPerdidos = 0;
    private MainDino mainDino;

    public ArrayList<Cuello> getArrayCuello() {
		return arrayCuello;
	}

	public void setArrayCuello(ArrayList<Cuello> arrayCuello) {
		this.arrayCuello = arrayCuello;
	}

	private int velocidadGeneracion=0;
    private Timer timer;
    private EventosAreaJuego eventos;
    private Sonido sonido;
    private Rectangle suelo;

    public AreaJuego() {
        dinosaurio = new Dinosaurio(this);
        eventos = new EventosAreaJuego(this);
        String usuario = System.getProperty("user.name");
        String rutaImagen = "C:\\Users\\" + usuario + "\\eclipse-workspace\\DinoDino\\src\\assets\\fondo.png";
        String rutaSonido = "C:\\Users\\" + usuario + "\\eclipse-workspace\\DinoDino\\src\\assets\\likeADino.wav";
        sonido = new Sonido(rutaSonido);
        iniciarMusica();
        ImageIcon icon = new ImageIcon(rutaImagen);
        fondo = icon.getImage();
        posFondo = 0;
        arrayCuello = new ArrayList<Cuello>();
        // Definir el rectángulo del suelo
        suelo = new Rectangle(0, 700, 1110, 200);

        setFocusable(true);

        timer = new Timer(10, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actualizar();
                repaint();
            }
        });
        timer.start();
    }
    
    public void eliminarCuello(Cuello cuello) {
        arrayCuello.remove(cuello);
    }
    private void actualizarGeneracionCuellos() {
    	if (numColisiones >= 5 && numColisiones < 10) {
    	    velocidadCuello = 6;
    	} else if (numColisiones >= 10 && numColisiones < 15) {
    	    velocidadCuello = 9;
    	} else if (numColisiones >= 15 && numColisiones < 20) {
    	    velocidadCuello = 12;
    	} else if (numColisiones >= 20) {
    	    velocidadCuello = 15;
    	}

    }

    
    public void actualizar() {
        dinosaurio.mover(0);

        for (int i = arrayCuello.size() - 1; i >= 0; i--) {
            Cuello cuello = arrayCuello.get(i);
            cuello.actualizar();

            Rectangle rectCuello = cuello.obtenerRectangulo();
            Rectangle rectDinosaurio = dinosaurio.getRect();

            if (rectCuello.intersects(rectDinosaurio)) {
                // Colisión con el dinosaurio
                puntuacion += 50;
                numColisiones++;
                arrayCuello.remove(i);
                numCuellosPerdidos = 0; 
            } else if (rectCuello.intersects(suelo)) {
                // Colision con el suelo
                numCuellosPerdidos++;
                if (numCuellosPerdidos >= 3) {
                    JOptionPane.showMessageDialog(this, "¡Has perdido!", "Fin del juego",
                            JOptionPane.INFORMATION_MESSAGE);
                    System.exit(0); 
                }

                arrayCuello.remove(i);
            }
        }

        if (numColisiones == 5 && dinosaurio.getImgActual() != 2) {
            try {
                dinosaurio.cambiarImagenDinosaurio();
                dinosaurio.setImgActual(2);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        
        if (arrayCuello.isEmpty()) {
            generarCuelloAleatorio();
        }

    }

    private void generarCuelloAleatorio() {
        int limiteIzquierdo = 160;
        int limiteDerecho = 880;
        Cuello cuello = new Cuello(limiteIzquierdo, limiteDerecho, this);
        arrayCuello.add(cuello);

        // Incrementar la velocidad de los cuellos generados aleatoriamente
        cuello.aumentarVelocidad(numColisiones);
    }

	@Override
    public void paint(Graphics g) {
        super.paint(g);

        g.drawImage(fondo, posFondo, 0, getWidth(), getHeight(), null);

        for (Cuello cuello : arrayCuello) {
            cuello.dibujar(g);
        }
        
        dinosaurio.dibujar(g);

        g.setFont(new Font("Berlin Sans FB Demi", Font.BOLD, 40));
        g.drawString(String.valueOf(puntuacion), 100, 55);
        //g.drawString(String.valueOf(nombre), 1000, 55);

    }

    @Override
    public void update(Graphics g) {
        if (buffer == null) {
            buffer = createImage(getWidth(), getHeight());
        }
        Graphics bg = buffer.getGraphics();
        bg.clearRect(0, 0, getWidth(), getHeight());
        paint(bg);
        g.drawImage(buffer, 0, 0, getWidth(), getHeight(), this);
    }
    public void iniciarMusica() {
        sonido.reproducir();
    }

    public void detenerMusica() {
        sonido.detener();
    }
    // GETTERS & SETTERS
    public int getNumColisiones() {
		return numColisiones;
	}

	public void setNumColisiones(int numColisiones) {
		this.numColisiones = numColisiones;
	}
    public Dinosaurio getDinosaurio() {
        return dinosaurio;
    }

    public void setDinosaurio(Dinosaurio dinosaurio) {
        this.dinosaurio = dinosaurio;
    }
}
