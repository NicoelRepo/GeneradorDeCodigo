package visual;

import entity.Parameter;
import estrategy.EstrategyGenerateText;

import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ModeloTablaParametros extends AbstractTableModel implements TableModel
{
    final List<Parameter> datos;
    final String[] columns = new String[]{"Estrategia", "Parametro", "Valor"};

    public ModeloTablaParametros(List<Parameter> datos)
    {
        this.datos = datos;
    }

    @Override
    public int getRowCount()
    {
        return datos.size();
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
            Parameter p = datos.get(rowIndex);
            switch (columnIndex)
            {
                case 0:
                    ret = p.estrategy.getClass().getSimpleName();
                    break;

                case 1:
                    ret = p.nameParameter;
                    break;

                case 2:
                    ret = p.value;
                    break;

                default:
                    ret = "no def";
                    break;
            }
        }
        return ret;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex)
    {
        return columnIndex == 2;
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex)
    {
        switch (columnIndex)
        {
            case 2:
                datos.get(rowIndex).value = aValue;
                break;

            default:
                break;
        }
        super.setValueAt(aValue, rowIndex, columnIndex);
    }
}