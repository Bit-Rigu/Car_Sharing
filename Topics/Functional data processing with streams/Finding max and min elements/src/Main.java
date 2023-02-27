

import java.util.*;
import java.util.function.*;
import java.util.stream.*;


class MinMax {

    public static <T> void findMinMax(Stream<? extends T> stream, Comparator<? super T> order, BiConsumer<? super T, ? super T> minMaxConsumer) {

        var result = stream.collect(() -> new Acc<T>(order), (acc, element) -> acc.append(element), (acc1, acc2) -> acc1.combine(acc2));
        minMaxConsumer.accept(result.min, result.max);
    }

    private static class Acc<T> {
        T min = null;
        T max = null;
        Comparator<? super T> comparator;

        Acc(Comparator<? super T> comparator) {
            this.comparator = comparator;
        }

        public void append(T value) {
            if (min == null || (comparator.compare(value, min) <= 0)) min = value;
            if (max == null || (comparator.compare(value, max) >= 0)) max = value;
        }

        private T takeBest(T a1, T a2, BiFunction<T, T, T> f) {
            if (a1 == null) return a2;
            if (a2 == null) return a1;
            return f.apply(a1, a2);
        }

        public Acc<T> combine(Acc<T> other) {
            Acc<T> result = new Acc<>(this.comparator);
            result.min = takeBest(
                    this.min,
                    other.min,
                    (v1, v2) -> this.comparator.compare(v1, v2) < 0 ? v1 : v2
            );
            result.max = takeBest(
                    this.max,
                    other.max,
                    (v1, v2) -> this.comparator.compare(v1, v2) < 0 ? v1 : v2
            );
            return result;
        }
    }
}

