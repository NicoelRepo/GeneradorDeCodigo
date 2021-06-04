package entity;

import estrategy.EstrategyGenerateText;

import java.util.LinkedList;
import java.util.List;

public class Plantilla
{
    final private List<CodeFile> listCodeFiles;
    final String name;
    private final List<EstrategyGenerateText> estrategysSecuence = new LinkedList<>();

    public Plantilla(List<CodeFile> listCodeFiles, String name)
    {
        this.listCodeFiles = listCodeFiles;
        this.name = name;
    }

    public List<CodeFile> getListCodeFiles()
    {
        return listCodeFiles;
    }

    public String getName()
    {
        return name;
    }

    public List<EstrategyGenerateText> getEstrategysSecuence()
    {
        return estrategysSecuence;
    }

    @Override
    public String toString()
    {
        return "Plantilla{" + "listCodeFiles=" + listCodeFiles + ", name='" + name + '\'' + ", estrategysSecuence=" + estrategysSecuence + '}';
    }
}
