package visual;

import datos.SingletonDatos;
import readersAndWriters.ResourceLoader;
import util.UtilColorearPaneles;

import javax.swing.*;
import java.awt.*;
import java.util.Scanner;

public class Window extends JFrame
{
    private final SingletonDatos data = SingletonDatos.getInstance();

    JTable tablaPlantillas;
    JToolBar toolBar;
    JPanel panelPrincipal;

    public Window()
    {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.setTitle("Generador de C\u00f3digo");

        this.setIconImage(ResourceLoader.getImageIcon("image/iconoPrincipal.png").getImage());

        panelPrincipal = new JPanel(new BorderLayout());
        inicializarToolBar();
        inicializarTablaPlantillas();
        panelPrincipal.add(toolBar, BorderLayout.NORTH);
        panelPrincipal.add(tablaPlantillas, BorderLayout.CENTER);
        add(panelPrincipal);

        this.setSize(new Dimension(500, 500));

        UtilColorearPaneles.coloreaComponentes(this.getContentPane(), SystemColor.inactiveCaption, new Class<?>[]{JPanel.class, JViewport.class, JToolBar.class});
    }

    private void inicializarTablaPlantillas()
    {
        tablaPlantillas = new JTable(new ModeloTablaPlantillas(data));
        tablaPlantillas.setRowHeight(20);
    }

    private void inicializarToolBar()
    {
        toolBar = new JToolBar();
        toolBar.setFloatable(false);

        JButton btnVerPlantilla = new JButton(ResourceLoader.getImageIcon("image/ver.png"));
        btnVerPlantilla.setBackground(SystemColor.inactiveCaption);

        toolBar.add(btnVerPlantilla);

    }

    private void cargarTablaPlantillas()
    {

    }
}
