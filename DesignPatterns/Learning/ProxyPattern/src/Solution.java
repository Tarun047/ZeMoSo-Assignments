
import JLocker.Container;
import JLocker.Vault;

import java.io.IOException;
import java.util.Arrays;


public class Solution {
    static void list(Vault myContainer)
    {
        System.out.println("The files in vault are: "+Arrays.toString(myContainer.listFiles()));
    }
    public static void main(String[] args)throws IOException
    {

        Vault myContainer = new Container();
        list(myContainer);
        Container.demoCreate(); // Limited access on create
        list(myContainer);
        Container.demoDelete(); //Limited access on delete
        list(myContainer);

    }

}
