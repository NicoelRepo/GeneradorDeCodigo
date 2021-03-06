package visual;

import datos.SingletonDatos;
import entity.Plantilla;

import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;

public class ModeloTablaPlantillas extends AbstractTableModel implements TableModel
{
    final SingletonDatos data = SingletonDatos.getInstance();
    final String[] columns = new String[]{"Plantilla"};

    public ModeloTablaPlantillas()
    {
    }

    @Override
    public int getRowCount()
    {
        return data.listaPlantillas.size();
    }

    @Override
    public int getColumnCount()
    {
        return columns.length;
    }

    @Override
    public String getColumnName(int column)
    {
        return columns[column];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex)
    {
        Object ret = null;
        if (rowIndex < getRowCount() && columnIndex < getColumnCount())
        {
            Plantilla plantilla = data.listaPlantillas.get(rowIndex);
            switch (columnIndex)
            {
                case 0:
                    ret = plantilla.getName();
                    break;

                default:
                    ret = "no def";
                    break;
            }
        }
        return ret;
    }
}