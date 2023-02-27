import java.util.Objects;
import java.util.function.*;

class Operator {

    public static <T, U> Function<T, U> ternaryOperator(
            Predicate<? super T> condition,
            Function<? super T, ? extends U> ifTrue,
            Function<? super T, ? extends U> ifFalse) {
        return (x) -> {
            if(condition.test(x)) {
                return ifTrue.apply(x);
            } else {
                return ifFalse.apply(x);
            }
        };
    }

}

class Main {
    public static void main(String[] args) {

            Predicate<Object> condition = Objects::isNull;
            Function<Object, Integer> ifTrue = obj -> 0;
            Function<CharSequence, Integer> ifFalse = CharSequence::length;
            Function<String, Integer> safeStringLength = Operator.ternaryOperator(condition, ifTrue, ifFalse);

            String a = "Dima";
            String b = null;

            System.out.println(safeStringLength.apply(a));
            System.out.println(safeStringLength.apply(b));
        }
    }
