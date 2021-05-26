package entity;

import java.io.File;
import java.util.List;

public class Plantilla
{
    final private File raizDirectorio;
    final private List<CodeFile> listCodeFiles;
    final String name;

    public Plantilla(String path, List<CodeFile> listCodeFiles, String name)
    {
        this.raizDirectorio = new File(path);
        this.listCodeFiles = listCodeFiles;
        this.name = name;
    }

    public File getRaizDirectorio()
    {
        return raizDirectorio;
    }

    public List<CodeFile> getListCodeFiles()
    {
        return listCodeFiles;
    }

    public String getName()
    {
        return name;
    }
}
