import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Dinosaurio {
	int posX, posY;
	int alto, ancho;
	int velocidad;
	private int dirH;
	private boolean shiftPresionado;
    private int contadorColisiones = 0;

	private Image[] imgDerecha;
	private Image[] imgIzq;
	private int imgActual;
	private int imageHeight; 
	private AreaJuego areaJuego;

	public Dinosaurio(AreaJuego areaJuego) {
		this.areaJuego = areaJuego;
		dirH = 1;
		alto = 150;
		ancho = 150;
		posX = 480;
		posY = areaJuego.getHeight() - alto - 50;
		velocidad = 40;
		try {
			cargarImagenes();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void cargarImagenes() throws IOException {
		String usuario = System.getProperty("user.name");

		imgDerecha = new Image[2];
		imgIzq = new Image[2];

		for (int i = 0; i < imgDerecha.length; i++) {
			imgDerecha[i] = ImageIO.read(new File("C:\\Users\\" + usuario + "\\eclipse-workspace\\DinoDino\\src\\assets\\dinoD" + (i + 1) + ".png"));
			imgIzq[i] = ImageIO.read(new File("C:\\Users\\" + usuario + "\\eclipse-workspace\\DinoDino\\src\\assets\\dinoI" + (i + 1) + ".png"));


		}

	}

	public void dibujar(Graphics g) {
		if (imgActual == imgDerecha.length) {
			imgActual = 0;
		}

		int canvasWidth = areaJuego.getWidth();
		int canvasHeight = areaJuego.getHeight();
		int imageWidth = 178; 
		int imageHeight = 264; 

		int drawPosX = posX;
		posY = (canvasHeight - imageHeight) / 2 + 180;

		Image imgAhora;
		if (dirH == 1) {
			imgAhora = imgDerecha[imgActual];
		} else {
			imgAhora = imgIzq[imgActual];
		}

		// Dibujar la imagen
		g.drawImage(imgAhora, drawPosX, posY, imageWidth, imageHeight, areaJuego);
		// ...
		//g.setColor(Color.RED);
		//g.drawRect(posX, posY+30, ancho, alto+50);
	}

	public void mover(int tecla) {
	    if (tecla == KeyEvent.VK_A) {
	        if (!shiftPresionado) {
	            dirH = -1; 
	        }
	        posX = posX - velocidad;
	        if (posX < 160) {
	            posX = 160;
	        }
	    } else if (tecla == KeyEvent.VK_D) {
	        if (!shiftPresionado) {
	            dirH = 1;
	        }
	        posX = posX + velocidad;
	        if (posX > 1030 - ancho) {
	            posX = 1030 - ancho;
	        }
	    } else if (tecla == KeyEvent.VK_SHIFT) {
	        shiftPresionado = true;
	        velocidad = 100;
	    }
	}

	
	public void cambiarImagenDinosaurio() throws IOException {
	    String usuario = System.getProperty("user.name");
	    imgDerecha[0] = ImageIO.read(new File("C:\\Users\\" + usuario + "\\eclipse-workspace\\DinoDino\\src\\assets\\dinoD2.png"));
	    imgIzq[0] = ImageIO.read(new File("C:\\Users\\" + usuario + "\\eclipse-workspace\\DinoDino\\src\\assets\\dinoI2.png"));
	}



	public int getImgActual() {
		return imgActual;
	}

	public void setImgActual(int imgActual) {
		this.imgActual = imgActual;
	}

	//Colision
	public Rectangle getRect() {
		Rectangle r;
		r = new Rectangle(posX, posY+30, ancho, alto+50);
		return r;
	}

}
