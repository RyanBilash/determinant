//package com.company;

abstract class Determinant{
    public static double determinant(double[][] matrix){
        double result = Integer.MIN_VALUE;
        if(isValidMatrix(matrix)){
            result = 0;
            if(matrix.length==2){
                result = matrix[0][0]*matrix[1][1]-matrix[1][0]*matrix[0][1];
            }else{
                int swap = 1;
                for (int i = 0; i < matrix.length; i++) {
                    result+=swap*matrix[0][i]*determinant(getSubMatrix(matrix,i));
                    swap*=-1;
                }
            }
        }
        return result;
    }

    /**
     * The getSubMatrix method creates a smaller matrix, so a 3x3 matrix would return a 2x2 matrix
     *
     * @param matrix The starting matrix
     * @param column The column to ignore
     * @return subMatrix The smaller matrix excluding the top row and specified column
     */
    private static double[][] getSubMatrix(double[][] matrix, int column){
        double[][] subMatrix = new double[matrix.length-1][matrix.length-1];
        int i2=0,j2=0;
        for (int i = 1; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                if(j!=column){
                    subMatrix[i2][j2] =  matrix[i][j];
                    j2++;
                }
            }
            j2=0;
            i2++;
        }
        return subMatrix;
    }

    /**
     * The isValidMatrix method tests whether a determinant can be found from the given matrix
     * ie. the matrix is a square and at least 2x2 in size
     *
     *
     * @param matrix The matrix to test
     * @return boolean Whether or not the matrix is valid for finding the determinant
     */
    private static boolean isValidMatrix(double[][] matrix){
        int[] lengths = new int[matrix.length];
        for (int i = 0; i < matrix.length; i++) {
            lengths[i] = matrix[i].length;
        }
        boolean allSameLength = true;
        for (int i = 0; i < lengths.length-1; i++) {
            allSameLength = allSameLength && lengths[i]==lengths[i+1];
        }

        boolean least2x2 = matrix.length>=2;
        boolean isSquare = matrix.length == matrix[0].length;

        return allSameLength && isSquare && least2x2;
    }
}
