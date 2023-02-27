import java.util.function.IntBinaryOperator;

class Operator {

    public static IntBinaryOperator binaryOperator =
            (int x,int y) -> {return x > y ? x : y;};
}