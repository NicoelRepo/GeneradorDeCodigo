package entity;

import estrategy.EstrategyGenerateText;

public class Parameter
{
    public final String nameParameter;
    public Object value;
    public final EstrategyGenerateText estrategy;

    public Parameter(String nameParameter, Object value, EstrategyGenerateText estrategy)
    {
        this.nameParameter = nameParameter;
        this.value = value;
        this.estrategy = estrategy;
    }
}
