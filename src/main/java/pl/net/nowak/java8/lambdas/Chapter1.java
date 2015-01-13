package pl.net.nowak.java8.lambdas;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * Author: Maciek
 */
public class Chapter1 {

    public static void main(String[] args) {

        exercise8();

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

    /**
     * Using the list(FilenameFilter) method of the java.io.File class, write a method
     that returns all files in a given directory with a given extension. Use a lambda
     expression, not a FilenameFilter. Which variables from the enclosing scope does
     it capture?
     */
    public static void exercise3() {
        File file = new File("C://");
        File[] files = file.listFiles((f) -> {
            return f.getName().endsWith(".txt");
        });
        Arrays.asList(files).forEach(System.out::println);
    }

    /**
     * Given an array of File objects, sort it so that the directories come before the
     files, and within each group, elements are sorted by path name. Use a lambda
     expression, not a Comparator.
     */
    public static void exercise4() {
        File file = new File("C://");
        File[] files = file.listFiles();
        Arrays.asList(files).sort((f1,f2) ->{
            if(f1.isDirectory() && f2.isDirectory()) {
                return f1.getAbsolutePath().compareTo(f2.getAbsolutePath());
            }else {
                if(f1.isDirectory() && f2.isFile()) {
                    return -1;
                }else{
                    return 1;
                }
            }});
        Arrays.asList(files).forEach(System.out::println);
    }

    /**
     * Take a file from one of your projects that contains a number of ActionListener,
     Runnable, or the like. Replace them with lambda expressions. How many lines
     did it save? Was the code easier to read? Were you able to use method
     references?
     */
    public static void exercise5() {
    }

    /**
     * Didn’t you always hate it that you had to deal with checked exceptions in a
     Runnable? Write a method uncheck that catches all checked exceptions and turns
     them into unchecked exceptions. For example,
     new Thread(uncheck(
     () -> { System.out.println("Zzz"); Thread.sleep(1000); })).start();
     // Look, no catch (InterruptedException)!
     Hint: Define an interface RunnableEx whose run method may throw any exceptions.
     Then implement public static Runnable uncheck(RunnableEx runner). Use a
     lambda expression inside the uncheck function.
     Why can’t you just use Callable<Void> instead of RunnableEx?
     */

    /**
     *  I JAK TO MA BYC NIBY CZYTELNIEJSZE!!??
     */
    public static void exercise6() {
          new Thread(uncheck(() ->{
              System.out.println("Zzzz!!");
              Thread.sleep(1000);
              System.out.println("!!!!");
          })).start();
    }

    public static  Runnable uncheck(RunnableEx runnableEx) {
        return () -> {
            try {
                runnableEx.run();
            } catch (Exception e) {
                e.printStackTrace();
            }
        };
    }

    @FunctionalInterface
    interface RunnableEx {
        void run() throws Exception;
    }

    /**
     * Write a static method andThen that takes as parameters two Runnable instances
     and returns a Runnable that runs the first, then the second. In the main method,
     pass two lambda expressions into a call to andThen, and run the returned
     instance.
     */
    public static void exercise7() {

        new Thread(andThen(
                () -> System.out.println("1"),
                () -> System.out.println("2")
        )).start();

    }

    public static Runnable andThenOldWAY(Runnable r1, Runnable r2) {
        return new Runnable() {
            @Override
            public void run() {
                r1.run();
                r2.run();
            }
        };
    }

    public static Runnable andThen(Runnable r1, Runnable r2) {
        return () -> {
            r1.run();
            r2.run();
        };
    }

    /**
     * What happens when a lambda expression captures values in an enhanced
     for loop such as this one?
     String[] names = { "Peter", "Paul", "Mary" };
     List<Runnable> runners = new ArrayList<>();
     for (String name : names)
     runners.add(() -> System.out.println(name));
     Is it legal? Does each lambda expression capture a different value, or do they
     all get the last value? What happens if you use a traditional loop for (int i = 0;
     i < names.length; i++)?
     */
    public static void exercise8() {
        String[] names = { "Peter", "Paul", "Mary" };
        List<Runnable> runners = new ArrayList<>();
        for (String name : names) {
            //stworzy nowy Runnable().run(sout);
            runners.add(() -> System.out.println(name));
        }

        for (Runnable runner : runners) {
            new Thread(runner).start();
        }


        //OLD WAY
        for (String name : names) {
            runners.add(new Runnable() {
                @Override
                public void run() {
                    System.out.println(name);
                }
            });
        }
    }






}
