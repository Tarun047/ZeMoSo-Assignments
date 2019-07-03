
import JLocker.Container;
import JLocker.Vault;

import java.io.IOException;
import java.util.Arrays;


public class Solution {

    public static void main(String[] args)throws IOException
    {

        Vault myContainer = new Container();
        System.out.println("The files in vault are: "+Arrays.toString(myContainer.listFiles()));
    }

}
