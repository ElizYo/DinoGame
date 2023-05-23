import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

public class Cuello {
    private int posX;
    private int posY;
    private int velocidad;
    private int ancho;
    private int alto;
    private BufferedImage imagen;
    private AreaJuego areaJuego;
    private int velocidadMaxima = 25;
    private int incrementoVelocidad = 3;
    private int colisionesAnteriores;
    private int velocidadAnterior; 

    public Cuello(int limiteIzquierdo, int limiteDerecho, AreaJuego areaJuego) {
        this.areaJuego = areaJuego;
        ancho = 38;
        alto = 68;
        velocidad = 3;

        cargarImagen();
        generarPosicion(limiteIzquierdo, limiteDerecho);
    }

    private void cargarImagen() {
        try {
            String usuario = System.getProperty("user.name");
            imagen = ImageIO.read(new File("C:\\Users\\" + usuario + "\\eclipse-workspace\\DinoDino\\src\\assets\\cuello.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void aumentarVelocidad(int numColisiones) {
        velocidadAnterior = velocidad;

        if (numColisiones >= 5 && numColisiones < 10) {
            velocidad = velocidadAnterior + incrementoVelocidad;
        } else if (numColisiones >= 10 && numColisiones < 15) {
            velocidad = velocidadAnterior + incrementoVelocidad * 2;
        } else if (numColisiones >= 15 && numColisiones < 20) {
            velocidad = velocidadAnterior + incrementoVelocidad * 3;
        } else if (numColisiones >= 20) {
            velocidad = velocidadAnterior + incrementoVelocidad * 4;
        }

        if (velocidad > velocidadMaxima) {
            velocidad = velocidadMaxima;
        }
    }


    private void generarPosicion(int limiteIzquierdo, int limiteDerecho) {
        Random random = new Random();
        posX = random.nextInt(limiteDerecho - limiteIzquierdo - ancho) + limiteIzquierdo;
        posY = 0;
    }

    public void eliminar() {
        if (areaJuego.getArrayCuello().size() > 0) {
            areaJuego.getArrayCuello().remove(this);
        }
    }


    public int getVelocidad() {
        return velocidad;
    }

    public void setVelocidad(int velocidad) {
        this.velocidad = velocidad;
    }

    public void actualizar() {
        if (velocidad > 0) {
            posY += velocidad;
        } else {
            posY -= velocidad;
        }

        if (posY >= 650) {
            eliminar();
        }

        if (areaJuego.getNumColisiones() % 5 == 0 && areaJuego.getNumColisiones() != colisionesAnteriores) {
            colisionesAnteriores = areaJuego.getNumColisiones();
            //System.out.println("velocidad: " + velocidad);
        }
    }

    public int getPosY() {
		return posY;
	}

	public void setPosY(int posY) {
		this.posY = posY;
	}

	public void dibujar(Graphics g) {
        g.drawImage(imagen, posX, posY, ancho, alto, null);

        //g.setColor(Color.RED);
        //g.drawRect(0, 700, 1110, 200);
    }

    public Rectangle obtenerRectangulo() {
        return new Rectangle(posX, posY, ancho, alto);
    }
}
