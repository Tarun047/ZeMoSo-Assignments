import java.util.*;
import java.text.*;
class Solution
{
  static Calendar mCal = Calendar.getInstance();
  static enum months
  {
    January, February, March, April, May, June,
   July, August, September, October, November, December
  }

  static void resetTime()
  {
    mCal.set(Calendar.HOUR_OF_DAY, 0);
    mCal.set(Calendar.MINUTE, 0);
    mCal.set(Calendar.SECOND, 0);
    mCal.set(Calendar.MILLISECOND, 0);
  }

  static Date findDate(String month,int day,int offset)
  {
    mCal.set(Calendar.DATE,1);
    if(offset==-1)
    {
      mCal.set(Calendar.MONTH,months.valueOf(month).ordinal()+1);
      mCal.add(Calendar.DATE,-1);
    }
    else
      mCal.set(Calendar.MONTH,months.valueOf(month).ordinal());
    while(mCal.get(Calendar.DAY_OF_WEEK)!=day)
      mCal.add(Calendar.DATE,offset);
    resetTime();
    return mCal.getTime();
  }

  static int getWeeksBetween(Date start,Date end)
  {
    int weeks = 0;
    mCal.setTime(start);
    while(mCal.getTime().before(end))
    {
      mCal.add(Calendar.WEEK_OF_YEAR,1);
      weeks++;
    }
    return weeks;
  }

  public int solution(int Y,String A,String B,String W)
  {
    mCal.set(Calendar.YEAR,Y);
    Date start = findDate(A,Calendar.MONDAY,1);
    Date end = findDate(B,Calendar.SUNDAY,-1);
    System.out.println("Book Tickets between: "+start+" and "+end);
    return getWeeksBetween(start,end);
  }
}

class MyCode
{
  public static void main(String args[])
  {
    Scanner sc = new Scanner(System.in);
    int t = sc.nextInt();
    sc.nextLine();
    Solution mySolution = new Solution();
    while(t-->0)
    {
      String test[] = sc.nextLine().split(" ");
      System.out.println("Number of Weeks are: "+mySolution.solution(Integer.parseInt(test[0]),test[1],test[2],test[3]));
    }
  }
}
