import java.util.*;

public class Matrix{
    private Integer[][] elements;
    private int widthX;
    private int heightY;

    Matrix(int widthX, int heightY) {
        this.widthX = widthX;
        this.heightY = heightY;
        this.elements = new Integer[widthX][];
        for ( int i = 0; i < widthX; i++ ) {
            this.elements[i] = new Integer[heightY];
        }
    }
    public void randomValues(){
        Random random = new Random(  );
        for ( int i = 0; i < widthX; i ++ )
            for ( int j = 0; j < heightY; j ++ ){
                elements[i][j] = random.nextInt(10);
            }
    }
    public Matrix add(Matrix matrix){
        if(matrix.widthX != this.widthX || matrix.heightY != this.heightY)
            throw new IllegalArgumentException( "Dimensions of matrix must be the same!" );
        Matrix newMatrix = new Matrix( this.widthX, this.heightY );
        for ( int i = 0; i < widthX; i ++ )
            for ( int j = 0; j < heightY; j ++ ){
                newMatrix.elements[i][j] = this.elements[i][j] + matrix.elements[i][j];
            }
        return newMatrix;
    }
    public void print(){
        Iterator<Integer> iterator = new Iterator<Integer>() {
            Integer actualElement = -1;
            @Override
            public boolean hasNext() {
                return actualElement + 1 < widthX*heightY;
            }

            @Override
            public Integer next() {
                if(hasNext()){
                    ++actualElement;
                    return elements[(actualElement-actualElement%heightY) / heightY][actualElement % heightY];
                }
                return null;
            }
        };
        for ( int i = 0; i < widthX; i ++ ) {
            for ( int j = 0; j < heightY; j++ ) {
                System.out.print( iterator.next() + " " );
            }
            System.out.println();
        }
    }}
