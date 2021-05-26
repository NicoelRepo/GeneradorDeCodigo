package estrategy;

import entity.CodeFile;
import entity.Parameter;

public class SimpleGenerateText implements EstrategyGenerateText
{
    @Override
    public void generate(CodeFile codeFile)
    {
        StringBuffer sb = codeFile.getText();
        for (Parameter p : mapParameters.values())
        {
            replaceAllOcurrences(sb, p.nameParameter, p.value.toString());
        }
    }


    public void replaceAllOcurrences(StringBuffer sb, String parameter, String value)
    {
        int index;
        while ((index = sb.indexOf(parameter)) != -1)
        {
            sb.replace(index, index + parameter.length(), value);
        }
    }
}