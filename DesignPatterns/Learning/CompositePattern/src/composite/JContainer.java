package composite;

import java.util.LinkedList;
import java.util.List;

public class JContainer implements SourceBox {

    private List<SourceBox> boxes = new LinkedList<>();

    public void add(SourceBox myBox)
    {
        boxes.add(myBox);
    }


    public void remove()
    {
        if(boxes.size()!=0)
            boxes.remove(boxes.size()-1);
    }

    @Override
    public void executeSource() {
        for(SourceBox box:boxes)
        {
            box.executeSource();
        }

    }
}
