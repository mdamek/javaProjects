import java.io.Console;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) throws ElementNotExistException {
        //1
        Random random = new Random();
        Matrix m1 = new Matrix( 3,4 );
        m1.randomValues();
        m1.print();
        Matrix m2 = new Matrix( 3,4 );
        m2.randomValues();
        m2.print();
        Matrix m3 = m1.add( m2 );
        m3.print();

        //2
        List<Integer> integers = new ArrayList<Integer>() {};
        for ( int i = 0; i < 300; i++ ){
            integers.add(random.nextInt(100));
        }
        Integer min = Algorithms.solution(integers);
        System.out.println("Minimum: " + min);

        //3
        String a = "abcd", b="cdabcdab";
        int repeats = Algorithms.substring( a,b );
        System.out.println("String repeats: " + repeats);


        //4
        float[] arr = {2, 7, 11, 15};
        float target = 9;
        int [] indexes = Algorithms.solution( arr, target );

        //5
        int elements = 10000;
        int[] optimistic = new int[elements];
        for(int i = 0; i < elements; i ++){
            optimistic[i] = i;
        }
        int[] pessimistic = new int[elements];
        for(int i = elements - 1, j = 0; i >= 0; i --, j ++){
            pessimistic[j] = i;
        }
        int[] realistic = new int[elements];
        for(int i = 0; i < elements; i ++){
            realistic[i] = random.nextInt(elements);
        }
        double[] time = new double[3];
        //optimistic
        long tStart = System.currentTimeMillis();
        //Sort.Bubble( optimistic );
        //Sort.Comb( optimistic );
        //Sort.Cocktail( optimistic );
        //Sort.Heap( optimistic );
        Sort.Insert( optimistic );
        long tEnd = System.currentTimeMillis();
        long tDelta = tEnd - tStart;
        time[0] = tDelta / 1000.0;


        //pessimistic
        tStart = System.currentTimeMillis();
        //Sort.Bubble( pessimistic );
        //Sort.Comb( pessimistic );
        //Sort.Cocktail( pessimistic );
        //Sort.Heap( pessimistic );
        Sort.Insert( pessimistic );
        tEnd = System.currentTimeMillis();
        tDelta = tEnd - tStart;
        time[1] = tDelta / 1000.0;

        //realistic
        tStart = System.currentTimeMillis();
        //Sort.Bubble( realistic );
        //Sort.Comb( realistic );
        //Sort.Cocktail( realistic );
        //Sort.Heap( realistic );
        Sort.Insert( realistic );
        tEnd = System.currentTimeMillis();
        tDelta = tEnd - tStart;
        time[2] = tDelta / 1000.0;
        System.out.println("Optimistic" + time[0]);
        System.out.println("Pessimistic: " + time[1]);
        System.out.println("Realistic: " + time[2]);

    }

    }

