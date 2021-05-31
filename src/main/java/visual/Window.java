package visual;

import datos.SingletonDatos;
import net.miginfocom.swing.MigLayout;
import readersAndWriters.ResourceLoader;
import util.UtilColorearPaneles;

import javax.swing.*;
import java.awt.*;

public class Window extends JFrame
{
    private final SingletonDatos data = SingletonDatos.getInstance();

    public Window()
    {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Generador de C\u00f3digo");
        this.setIconImage(ResourceLoader.getImageIcon("image/iconoPrincipal.png").getImage());
        this.setSize(new Dimension(300, 400));
        this.setLocationRelativeTo(null);

        this.setLayout(new MigLayout("fill,insets 0 10 10 10", "", ""));
        this.add(new PanelPlantillas(this), "grow");

        System.out.println(data.listaPlantillas.get(0));

        UtilColorearPaneles.coloreaComponentes(this.getContentPane(), Color.ORANGE, new Class<?>[]{JPanel.class, JToolBar.class});
        UtilColorearPaneles.coloreaComponentes(this.getContentPane(), Color.lightGray, new Class[]{JViewport.class});
    }
}
