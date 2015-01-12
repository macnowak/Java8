package pl.net.nowak.java8.lambdas;

import java.io.File;
import java.io.FileFilter;
import java.util.Arrays;
import java.util.Comparator;

/**
 * Author: Maciek
 */
public class Chapter1 {

    public static void main(String[] args) {

        exercise2();

    }

    public static void exercise1() {
        Integer[] ints = {1,2,3,5,2,6,8,3};
        Arrays.sort(ints, Comparator.<Integer>naturalOrder());
        Arrays.asList(ints).forEach(System.out::print);
    }

    public static void exercise2() {

        File file = new File("C://");
        File[] files = file.listFiles((f) -> {
            return f.isDirectory();
        });

        Arrays.asList(files).forEach((it) -> System.out.println(it.getName()));

        file.listFiles(File::isDirectory);

    }

    public static void exercise3() {

    }



}
