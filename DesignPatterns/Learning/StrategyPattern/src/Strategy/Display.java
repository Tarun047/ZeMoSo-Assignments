package Strategy;

public class Display {
    private Viewer mViewer;


    public void setDisplayType(Viewer displayType)
    {
        mViewer=displayType;
    }

    public void show()
    {
        mViewer.show();
    }
}
