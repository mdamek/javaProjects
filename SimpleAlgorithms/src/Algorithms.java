import java.util.ArrayList;
import java.util.List;

public class Algorithms {
    //2
    public static int solution(List<Integer> numbers) throws TooLongListException, TooBigNumberException {

            if(numbers.size() > 100000)
            {
                throw new TooLongListException( "Your list have " + numbers.size() + " elements, but is 100000 permitted" );
            }
            for ( Integer actual: numbers ) {
                if(actual> 1000000 || actual < -1000000)
                    throw new TooBigNumberException("You pass " + actual +" argument but 1000000 is maximal and -1000000 minimal");
            }
            Integer maxValue = numbers.stream().max( Integer::compare ).get();
            for ( int i = 1; i <= maxValue; i++ ) {
                if (!numbers.contains( i )) {
                    return i;
                }
            }
//        catch (TooBigNumberException | TooLongListException e){
//            System.out.println( e.getMessage() );
//        }
         maxValue = numbers.stream().max( Integer::compare ).get();
        return ++maxValue;
    }
    //3
    public static int substring(String a, String b){
       int startLength = a.length();
       String phrase = a;
        for ( int i = 1;; i++){
            if(a.length() > startLength * 2 && a.length() > 2 * b.length())
                return -1;
            if(a.contains( b ))
                return i;
            a = a.concat( phrase );

        }
    }
    //4
    public static int[] solution (float[] arr, float target){
        for ( int i = 0; i < arr.length; i++ ){
           for ( int  j = i + 1; j < arr.length; j ++ ){
               if(arr[i] + arr[j] == target)
                   return new int [] {i,j};
           }
        }
        throw new IllegalArgumentException( "There is no that sum" );
    }
}