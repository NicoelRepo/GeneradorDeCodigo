package visual;

import datos.SingletonDatos;
import entity.Plantilla;
import readersAndWriters.ResourceLoader;
import util.ControlHeaderTabla;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class PanelPlantillas extends JPanel
{
    final List<Plantilla> plantillaList = SingletonDatos.getInstance().listaPlantillas;
    JTable tablaPlantillas;
    JToolBar toolBar;
    final DialogoParametros dialogoParametros;

    public PanelPlantillas(JFrame window)
    {
        dialogoParametros = new DialogoParametros(window);

        inicializarToolBar();
        inicializarTabla();

        this.setLayout(new BorderLayout());
        this.add(toolBar, BorderLayout.NORTH);
        this.add(new JScrollPane(tablaPlantillas), BorderLayout.CENTER);

        ControlHeaderTabla.agregarCalibradorColumnas(tablaPlantillas, 150);
    }

    private void inicializarTabla()
    {
        tablaPlantillas = new JTable(new ModeloTablaPlantillas());
        tablaPlantillas.setRowHeight(20);
        tablaPlantillas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tablaPlantillas.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        tablaPlantillas.setSelectionBackground(Color.gray);
        tablaPlantillas.getTableHeader().setReorderingAllowed(false);

        int col = 0;
        tablaPlantillas.getColumnModel().getColumn(col++).setMinWidth(80);
    }

    private void inicializarToolBar()
    {
        toolBar = new JToolBar();
        toolBar.setFloatable(false);

        JButton btnVerPlantilla = new JButton(ResourceLoader.getImageIcon("image/ver.png"));
        btnVerPlantilla.setBackground(SystemColor.inactiveCaption);
        btnVerPlantilla.addActionListener((actionEvent) -> {
            int filaSeleccionada = tablaPlantillas.getSelectedRow();
            if (filaSeleccionada >= 0)
            {
                Plantilla plantillaSeleccionada = plantillaList.get(tablaPlantillas.convertRowIndexToModel(filaSeleccionada));
                dialogoParametros.abrirDialogo(plantillaSeleccionada);
                SingletonDatos.cargarDatosDataBase();
            }
            else
            {
                JOptionPane.showMessageDialog(this, "Debe seleccionar alguna plantilla", "Alerta", JOptionPane.WARNING_MESSAGE);
            }
        });
        toolBar.add(btnVerPlantilla);

    }
}
