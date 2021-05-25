package logica;

import entity.CodeFile;
import entity.Plantilla;
import estrategy.EstrategyGenerateText;
import readersAndWriters.FileWriter;

import java.io.IOException;

public class GeneradorPrincipal
{
    public void generarUsandoPlantilla(Plantilla plantilla)
    {
        for (CodeFile codeFile : plantilla.getListCodeFiles())
        {
            // Se insertan los parametros en el texto del codeFile
            aplicarEstrategias(codeFile);

            // Se genera el archivo en la ruta especificada. En este punto codeFile.getText() ya esta listo
            try
            {
                FileWriter.createFile(plantilla.getRaizDirectorio() + codeFile.getPath(), codeFile.getText());
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }

        }
    }

    public void aplicarEstrategias(CodeFile codeFile)
    {
        for (EstrategyGenerateText estrategy : codeFile.getEstrategysSecuence())
        {
            estrategy.generate(codeFile);
        }
    }
}
