public class Solution {
    public void setZeroes(int[][] matrix) {
        int height = matrix.length;
        int width = matrix[0].length;
        
        Set<Integer> rows = new HashSet<Integer>();
        Set<Integer> columns = new HashSet<Integer>();
        
        for (int i = 0; i < height; ++i) {
            for (int j = 0; j < width; ++j) {
                if (matrix[i][j] == 0) {
                    rows.add(i);
                    columns.add(j);
                }
            }
        }
        
        for (Integer ii : rows) {
            for (int j = 0; j < width; ++j) {
                matrix[ii][j] = 0;
            }
        }
        
        for (Integer jj : columns) {
            for (int i = 0; i < height; ++i) {
                matrix[i][jj] = 0;
            }
        }
    }
}
