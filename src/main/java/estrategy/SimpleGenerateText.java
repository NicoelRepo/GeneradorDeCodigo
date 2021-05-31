package estrategy;

import entity.CodeFile;
import entity.Parameter;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class SimpleGenerateText implements EstrategyGenerateText
{
    private static final char caracter = '#';
    static Map<String, Function<String, String>> mapModifiers;
    static
    {
        mapModifiers = new HashMap<>();
        mapModifiers.put("toLowerCase", String::toLowerCase);
        mapModifiers.put("toUpperCase", String::toUpperCase);
        mapModifiers.put("firstToLowerCase", (s) -> s.substring(0, 1).toLowerCase() + s.substring(1));
        mapModifiers.put("firstToUpperCase", (s) -> s.substring(0, 1).toUpperCase() + s.substring(1));
    }

    @Override
    public void generate(CodeFile codeFile)
    {
        StringBuffer sb = codeFile.getText();
        for (Parameter p : mapParameters.values())
        {
            // Se reemplaza el parametro p en el texto
            replaceAllOcurrences(sb, p.nameParameter, p.value.toString());

            // Se reemplaza el parametro p en el nombre del archivo
            StringBuffer sbForName = new StringBuffer(codeFile.getName());
            replaceAllOcurrences(sbForName, p.nameParameter, p.value.toString());
            codeFile.setName(sbForName.toString());
        }
    }

    private void replaceAllOcurrences(StringBuffer sb, String parameter, String value)
    {
        int i = 0;
        int indexBegin;
        int indexEnd;
        while (i < sb.length())
        {
            if (sb.charAt(i) == caracter)
            {
                indexBegin = i;
                indexEnd = indexBegin + sb.substring(indexBegin+1).indexOf(caracter) + 1;
                String parameterWithModifiers = sb.substring(indexBegin+1, indexEnd);
                String formattedValue = produceValue(parameterWithModifiers, value);
                sb.replace(indexBegin, indexEnd + 1, formattedValue);
                i = indexEnd;
            }
            else
            {
                i++;
            }
        }
    }

    private static String produceValue(String parameterWithModifiers, String value)
    {
        String[] modifiers = parameterWithModifiers.split(":");
        for (int i = 1; i < modifiers.length; i++)
        {
            value = mapModifiers.get(modifiers[i]).apply(value);
        }
        return value;
    }
}