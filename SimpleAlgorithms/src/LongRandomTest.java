import org.junit.Test;

public class LongRandomTest {

    @Test(timeout = 5000)
    public void shouldRandomBigMatrix(){
        Matrix matrix = new Matrix( 10000,10000 );
        matrix.randomValues();
    }
}
