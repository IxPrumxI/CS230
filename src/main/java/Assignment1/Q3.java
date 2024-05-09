package Assignment1;

public class Q3 {
    public static void main(String[] args) {
        int x = 3;
        int a = 1;
        int b = 2;
        int c = 3;
        int d = 4;

        double y = functionY(x, a, b, c, d);
        System.out.println("y = " + y);

        double parenthesesY = parenthesesFunctionY(x, a, b, c, d);
        System.out.println("parenthesesY = " + parenthesesY);
    }

    public static double functionY(int x, int a, int b, int c, int d){
        double y = a * x * x * x + b * x * x + c * x + d;
        return y;
    }

    public static double parenthesesFunctionY(int x, int a, int b, int c, int d) {
        double y = ((a * (x * x * x)) + (b * (x * x)) + (c * x) + d);
        return y;
    }
}
