package composite;

public class JProgram implements SourceBox {

    private String source;

    public JProgram(String sourceName)
    {
        source=sourceName;
    }

    private boolean compile()throws Exception
    {

        if(!source.endsWith(".java"))
            source+=".java";
        ProcessBuilder pb = new ProcessBuilder("javac",source);

        pb.redirectOutput(ProcessBuilder.Redirect.PIPE);
        pb.redirectError(ProcessBuilder.Redirect.PIPE);
        Process p = pb.start();
        p.waitFor();
        return p.exitValue()==0;
    }

    private void run()throws Exception
    {

        ProcessBuilder pb = new ProcessBuilder("java",source.substring(0,source.indexOf(".")));
        pb.redirectInput(ProcessBuilder.Redirect.PIPE);
        pb.redirectOutput(ProcessBuilder.Redirect.INHERIT);
        Process p = pb.start();
    }

    @Override
    public void executeSource()
    {
        try {
            if(compile())
                run();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
