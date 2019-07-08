package calculations;

public class Add implements Operator{
    double number1;
    double number2;
    public void setOperands(double x,double y)
    {
        this.number1=x;
        this.number2=y;
    }
    public double result()
    {
        return this.number2+this.number1;
    }
}
