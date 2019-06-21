/*
Question 1:
1. A vampire number v is a number with an even number of digits n,
that can be factored into two numbers x and y each with n/2 digits and not both with trailing zeroes,
where v contains precisely all the digits from x and from y, in any order.
Write a java program to print first 100 vampire numbers.
Example: 2160 = 12*60 where 12 and 60 contain all the digits from 2160
*/
class VampireNumber
{
  public static int getDigits(int number)
  {
    return (int)Math.log10((double)number)+1;
  }

  public static boolean isValidVampireNumber(int product,int x,int y)
  {
    if(getDigits(product)%2!=0) return false;

    int cnt[] = new int[10];
    for(;x>0;x/=10)
      cnt[x%10]++;
    for(;y>0;y/=10)
      cnt[y%10]++;
    for(;product>0;product/=10)
      cnt[product%10]--;
    for(int i=0;i<=9;i++)
      if(cnt[i]!=0) return false;
    return true;
  }

  public static void vampire(int n)
  {
    for(int digits = 1;n>0;digits++)
    {
      for(int i=(int)Math.pow(10,digits-1);i<(int)Math.pow(10,digits);i++)
      {
        for(int j=(int)Math.pow(10,digits-1);j<(int)Math.pow(10,digits);j++)
        {
          if(i%10==0 && j%10==0) continue;

          if(isValidVampireNumber(i*j,i,j))
          {
              System.out.println(i*j+" is a vampire number "+" made up of the factors "+i+" "+j);
              n--;
          }
        }
      }
    }
  }

  public static void main(String args[])
  {
    if(args.length==0)
    {
      System.out.println("The first 100 vampire numbers are: ");
      vampire(100);
    }
    else
    {
      System.out.println("The first "+args[0]+" vampire numbers are: ");
      vampire(Integer.parseInt(args[0]));
    }
  }
}
