package entity;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

public class Plantilla
{
    final File raizDirectorio;
    final List<CodeFile> listCodeFiles = new LinkedList<CodeFile>();

    public Plantilla(String path)
    {
        raizDirectorio = new File(path);
    }

    public File getRaizDirectorio()
    {
        return raizDirectorio;
    }

    public List<CodeFile> getListCodeFiles()
    {
        return listCodeFiles;
    }
}
