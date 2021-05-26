package estrategy;

import entity.CodeFile;
import entity.Parameter;

import java.util.HashMap;
import java.util.Map;

public interface EstrategyGenerateText
{
    CodeFile codeFile = null;
    public final Map<String, Parameter> mapParameters = new HashMap<>();

    void generate(CodeFile codeFile);

    public default Map<String, Parameter> getMapParameters()
    {
        return mapParameters;
    }
}
