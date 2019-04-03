import org.junit.Assert;
import org.junit.Test;

public class MatrixTest {

    @Test
    public void shouldSetRandomValuesForMatrix() {
        Matrix testMatrix = new Matrix( 3,3 );
        testMatrix.randomValues();
        Assert.assertTrue( testMatrix.elements[0].length != 0 );
        Assert.assertTrue( testMatrix.elements[1].length != 0 );
    }

    @Test
    public void shouldAddTwoMatrix() {
        Matrix matrix1 = new Matrix( 2,2 );
        Matrix matrix2 = new Matrix( 2,2 );
        matrix1.elements[0][0] = 1;
        matrix1.elements[0][1] = 1;
        matrix1.elements[1][0] = 1;
        matrix1.elements[1][1] = 1;
        matrix2.elements[0][0] = 2;
        matrix2.elements[0][1] = 2;
        matrix2.elements[1][0] = 2;
        matrix2.elements[1][1] = 2;
        Matrix answerMatrix = matrix1.add( matrix2 );
        Matrix expectedMatrix = new Matrix( 2,2 );
        expectedMatrix.elements[0][0] = 3;
        expectedMatrix.elements[0][1] = 3;
        expectedMatrix.elements[1][0] = 3;
        expectedMatrix.elements[1][1] = 3;
        Assert.assertArrayEquals( expectedMatrix.elements[0],  answerMatrix.elements[0]);
        Assert.assertArrayEquals( expectedMatrix.elements[1],  answerMatrix.elements[1]);
    }

    @Test
    public void shouldPrintMatrix() {
        Matrix matrix1 = new Matrix( 2,2 );
        matrix1.elements[0][0] = 1;
        matrix1.elements[0][1] = 2;
        matrix1.elements[1][0] = 3;
        matrix1.elements[1][1] = 4;
        matrix1.print();
        String v1 = matrix1.elements[0][0].toString();
        String v2 = matrix1.elements[0][1].toString();
        String v3 = matrix1.elements[1][0].toString();
        String v4 = matrix1.elements[1][1].toString();
        Assert.assertEquals("1", v1);
        Assert.assertEquals("2", v2);
        Assert.assertEquals("3", v3);
        Assert.assertEquals("4", v4);
    }
}