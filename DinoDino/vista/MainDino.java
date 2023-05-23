import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class MainDino extends JFrame {

	private JPanel contentPane;
    private String nombreJugador;

	private AreaJuego areaJuego;
	private AreaJuegoMenu areaJuegoMenu;
	private AreaJuegoReglas areaJuegoReglas;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainDino frame = new MainDino();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainDino() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1110, 768);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		
		contentPane.setLayout(null);
	    
	    areaJuegoMenu = new AreaJuegoMenu();
	    areaJuegoMenu.setBounds(0, 0, 1110, 768);
	    contentPane.add(areaJuegoMenu);
	   
	    
	 // Añadir AreaJuego al panel principal
	    areaJuego = new AreaJuego();
	    areaJuego.setBounds(0, 0, 1110, 768); // Ajusta la posición y tamaño según tus necesidades
	    areaJuego.setFocusable(true);
	    areaJuego.requestFocus();

	    contentPane.add(areaJuego);
	    
	    /*areaJuegoReglas = new AreaJuegoReglas();
	    areaJuegoReglas.setBounds(0, 0, 1110, 768);
	    contentPane.add(areaJuegoReglas);*/
	    
	    areaJuegoMenu.addMouseListener(new MouseAdapter() {
	        @Override
	        public void mouseReleased(MouseEvent e) {
	            int x = e.getX();
	            int y = e.getY();
	            if (x >= 480 && x <= 690 && y >= 424 && y <= 490) {
	                contentPane.remove(areaJuegoMenu);
	                contentPane.add(areaJuego);
	                areaJuego.setFocusable(true);
	                areaJuego.requestFocus();
	                contentPane.repaint();
	            } /*else if (x >= 500 && x <= 690 && y >= 505 && y <= 575) {
	                contentPane.remove(areaJuegoMenu);
	                contentPane.add(areaJuegoReglas);
	            }*/
	            //contentPane.revalidate();
	            //contentPane.repaint();
	        }
	    });

	}

}
