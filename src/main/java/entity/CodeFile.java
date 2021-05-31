package entity;

public class CodeFile
{
    private StringBuffer text;
    private String path;
    private String name;

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

    public StringBuffer getText()
    {
        return text;
    }

    public void setText(StringBuffer text)
    {
        this.text = text;
    }

}
