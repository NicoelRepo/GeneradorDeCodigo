package util;

import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.JTable;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

public class ControlHeaderTabla {

    public static void agregarCalibradorColumnas(final JTable tabla, final Integer minimoAnchoDeColumna)
    {
        setearMinimoAnchoColumnas(tabla, minimoAnchoDeColumna);

        tabla.getParent().addComponentListener(new ComponentAdapter() {

            @Override
            public void componentResized(ComponentEvent e)
            {
                super.componentResized(e);
                calibrarColumnas(tabla, minimoAnchoDeColumna);
            }

        });;

        tabla.getModel().addTableModelListener(new TableModelListener() {

            @Override
            public void tableChanged(TableModelEvent arg0) {
                calibrarColumnas(tabla, minimoAnchoDeColumna);
            }
        });

    }

    private static void calibrarColumnas(JTable tabla, Integer minimo)
    {
        if (tabla.getAutoResizeMode() == JTable.AUTO_RESIZE_ALL_COLUMNS)
        {
            if (anchoHeader(tabla) > tabla.getParent().getWidth())
            {
                tabla.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
            }
        }
        else if (tabla.getAutoResizeMode() == JTable.AUTO_RESIZE_OFF)
        {
            if (anchoHeader(tabla) < tabla.getParent().getWidth())
            {
                tabla.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
            }
        }
    }


    private static Integer anchoHeader(JTable tabla)
    {
        int sum = 0;

        for (int i = 0; i < tabla.getColumnCount(); i++)
        {
            sum += tabla.getColumnModel().getColumn(i).getWidth();
        }
        return sum;
    }


    private static void setearMinimoAnchoColumnas(JTable tabla, Integer minimo)
    {
        for (int i = 0; i < tabla.getColumnCount(); i++)
        {
            tabla.getColumnModel().getColumn(i).setMinWidth(minimo);
        }
    }
}