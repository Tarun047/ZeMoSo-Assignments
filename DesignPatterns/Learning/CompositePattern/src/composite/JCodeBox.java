package composite;

import java.util.List;

public class JCodeBox implements SourceBox {

    private List<SourceBox> programs;
    public JCodeBox(List<SourceBox> programs)
    {
        this.programs = programs;
    }

    @Override
    public void executeSource() {
        for (SourceBox program:programs)
        {
            program.executeSource();
        }
    }
}
