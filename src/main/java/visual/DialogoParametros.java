package visual;

import entity.Parameter;
import entity.Plantilla;
import estrategy.EstrategyGenerateText;
import logica.GeneradorPrincipal;
import readersAndWriters.ResourceLoader;
import util.ControlHeaderTabla;
import util.UtilColorearPaneles;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class DialogoParametros extends JDialog
{
    final List<Parameter> datosParametros = new ArrayList<Parameter>();
    JTable tablaParametros;
    Box buttonBox;
    Plantilla plantillaAUtilizar = null;

    public DialogoParametros(JFrame window)
    {
        super(window);
        setModalityType(ModalityType.DOCUMENT_MODAL);
        this.setLocationRelativeTo(window);

        JPanel panelCentral = (JPanel) this.getContentPane();
        panelCentral.setLayout(new BorderLayout());

        inicializarTabla();
        inicializarButtonBox();

        panelCentral.add(new JScrollPane(tablaParametros), BorderLayout.CENTER);
        panelCentral.add(buttonBox, BorderLayout.SOUTH);

        ControlHeaderTabla.agregarCalibradorColumnas(tablaParametros, 150);
        UtilColorearPaneles.coloreaComponentes(this, Color.ORANGE, new Class<?>[]{JPanel.class, Box.class});
        UtilColorearPaneles.coloreaComponentes(this, Color.LIGHT_GRAY, new Class[]{JViewport.class});
        this.setSize(new Dimension(500, 400));
    }

    private void inicializarButtonBox()
    {
        buttonBox = Box.createHorizontalBox();

        JButton btnCorrerPlantilla = new JButton("Ejecutar Plantilla");
//      btnCorrerPlantilla.setIcon(ResourceLoader.getImageIcon("image/flecha.png"));
        buttonBox.add(btnCorrerPlantilla);
        btnCorrerPlantilla.addActionListener((actionEvent) -> {
            String validaOk = validarParametros();
            if (validaOk != null)
            {
                JOptionPane.showMessageDialog(this, validaOk, "Alerta", JOptionPane.WARNING_MESSAGE);
            }
            else
            {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                fileChooser.showOpenDialog(this);
                File directorio = fileChooser.getSelectedFile();
                GeneradorPrincipal.generarUsandoPlantilla(plantillaAUtilizar, directorio.getPath());
            }
        });

        JButton btnCancelar = new JButton("Cancelar");
        buttonBox.add(btnCancelar);
        btnCancelar.addActionListener((actionEvent) -> {
            cerrarDialogo();
        });
    }

    private void inicializarTabla()
    {
        tablaParametros = new JTable(new ModeloTablaParametros(datosParametros));
        tablaParametros.setRowHeight(20);
        tablaParametros.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tablaParametros.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        tablaParametros.setSelectionBackground(Color.gray);
        tablaParametros.getTableHeader().setReorderingAllowed(false);
    }

    private void cargarParametrosAPedir()
    {
        this.datosParametros.clear();
        for (EstrategyGenerateText estrategy : plantillaAUtilizar.getEstrategysSecuence())
        {
            this.datosParametros.addAll(estrategy.getMapParameters().values());
        }
    }

    public void abrirDialogo(Plantilla plantilla)
    {
        this.plantillaAUtilizar = plantilla;
        cargarParametrosAPedir();
        this.setTitle("Generador de C\u00f3digo - " + plantilla.getName());
        this.setVisible(true);
    }

    public void cerrarDialogo()
    {
        this.plantillaAUtilizar = null;
        this.setVisible(false);
        datosParametros.forEach((parameter) -> { parameter.value = null; });
    }

    private String validarParametros()
    {
        String ret = null;
        
        // Validar que todos los parametros sean introducidos
        if (datosParametros.stream().anyMatch((parameter) -> parameter.value == null))
        {
            ret = "Debe colocar todos los parámetros";   
        }
        
        return ret;
    }

}
