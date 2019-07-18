import java.util.concurrent.atomic.AtomicInteger;

public class CaesarBreaker
{
    String halfOfString(String message,int start)
    {
        StringBuilder ans = new StringBuilder();
        for(int i=start;i<message.length();i+=2)
            ans.append(message.charAt(i));
        return ans.toString();
    }

    String getKey(String s)
    {

    }
}
