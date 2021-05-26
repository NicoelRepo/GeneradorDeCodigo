package entity;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

public class Plantilla
{
    final private File raizDirectorio;
    final private List<CodeFile> listCodeFiles;

    public Plantilla(String path, List<CodeFile> listCodeFiles)
    {
        raizDirectorio = new File(path);
        this.listCodeFiles = listCodeFiles;
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
