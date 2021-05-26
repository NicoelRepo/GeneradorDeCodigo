package entity;

import estrategy.EstrategyGenerateText;

import java.util.*;

public class CodeFile
{
    private StringBuffer text;
    private String path;
    private String name;
    private String extention;
    private final Map<String, Parameter> mapParameters = new HashMap<>();
    private final List<EstrategyGenerateText> estrategysSecuence = new LinkedList<>();

    public String getPath()
    {
        return path;
    }

    public void setPath(String path)
    {
        this.path = path;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getExtention()
    {
        return extention;
    }

    public void setExtention(String extention)
    {
        this.extention = extention;
    }

    public StringBuffer getText()
    {
        return text;
    }

    public void setText(StringBuffer text)
    {
        this.text = text;
    }

    public Map<String, Parameter> getMapParameters()
    {
        return mapParameters;
    }

    public List<EstrategyGenerateText> getEstrategysSecuence()
    {
        return estrategysSecuence;
    }
}
