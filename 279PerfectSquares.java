public class Solution {
    public int numSquares(int n) {
        List<Integer> dp = new ArrayList<Integer>(n + 1);
        dp.add(0);
        for (int i = 1; i <= n ; ++i) {
            int result = i;
            for (int j = 1; j * j <= i; ++j) {
                if ((dp.get(i - j * j) + 1) < result) {
                    result = dp.get(i - j * j) + 1;
                }
            }
            dp.add(result);
        }
        
        return dp.get(n);
    }
}
