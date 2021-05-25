package estrategy;

import entity.CodeFile;

public interface EstrategyGenerateText
{
    CodeFile codeFile = null;

    void generate(CodeFile codeFile);
}
