import composite.*;

import java.util.Arrays;

public class Solution
{
    public static void main(String[] args) {
        SourceBox p1 = new JProgram("helpers/HelloWorld.java");
        SourceBox p2 = new JProgram("helpers/Numbers.java");
        SourceBox p3 = new JProgram("helpers/Add.java");
        SourceBox p4 = new JProgram("helpers/Fib.java");

        SourceBox cb = new JCodeBox(Arrays.asList(p1,p2));

        JContainer jc = new JContainer();
        jc.add(p3);
        jc.add(p4);


        JContainer superTank = new JContainer();
        superTank.add(cb);
        superTank.add(jc);

        superTank.executeSource();
    }
}
