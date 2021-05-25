import entity.CodeFile;
import entity.Plantilla;
import logica.GeneradorPrincipal;

public class EjecutadorParaPruebas
{
    public static void main(String[] args)
    {
        final String raizDirectorio = "C:\\Users\\Nicol\\OneDrive\\Escritorio\\Carpeta de prueba";

        CodeFile codeFile = new CodeFile();


        Plantilla plantilla = new Plantilla(raizDirectorio);

        GeneradorPrincipal generadorPrincipal = new GeneradorPrincipal();
        generadorPrincipal.generarUsandoPlantilla();
    }
}
