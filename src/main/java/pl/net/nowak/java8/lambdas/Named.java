package pl.net.nowak.java8.lambdas;

/**
 * Author: Maciek
 */
public interface Named {

    default String getName() {
        return "Named";
    }
}
