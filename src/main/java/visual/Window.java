package visual;

import net.miginfocom.swing.MigLayout;
import readersAndWriters.XMLReader;

import javax.swing.*;
import java.io.File;

public class Window extends JFrame
{
    final JTable tablaPlantillas = new JTable();
    final JPanel panelPrincipal = new JPanel();

    final JMenuBar menuBar = new JMenuBar();
    final JMenu menuArchivo = new JMenu();

    public Window()
    {
        setLayout(new MigLayout("fill, debug", "", ""));
        setJMenuBar(menuBar);
        menuBar.add(menuArchivo);

        add(tablaPlantillas, "cell 0 0 grow");

    }

    private void cargarTablaPlantillas()
    {

    }
}
