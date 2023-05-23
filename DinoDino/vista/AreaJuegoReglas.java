import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

public class AreaJuegoReglas extends Canvas {
    private Image fondoMenu;
    private Graphics pantVirtual;
    private Image buffer;

    public AreaJuegoReglas() {
        String usuario = System.getProperty("user.name");
        String rutaImagen = "C:\\Users\\" + usuario + "\\eclipse-workspace\\DinoDino\\src\\assets\\reglas.png";
        ImageIcon icon = new ImageIcon(rutaImagen);
        fondoMenu = icon.getImage();
    }

    public void paint(Graphics g) {
        g.drawImage(fondoMenu, 0, 0, getWidth(), getHeight(), this);
    }
}
