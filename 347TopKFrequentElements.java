public class Solution {
    private class Pair implements Comparable<Pair> {
        private Integer key;
        private Integer value;
        public Pair(Integer key, Integer value) {
            this.key = key;
            this.value = value;
        }
        
        public Integer getKey() {
            return key;
        }
        
        public Integer getValue() {
            return value;
        }
        
        public int compareTo(Pair other) {
            return this.value - other.getValue();
        }
    }
    
    public List<Integer> topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        List<Pair> tops = new LinkedList<Pair>();
        List<Integer> results = new LinkedList<Integer>();
        for (int i = 0; i < nums.length; ++i) {
            if (map.containsKey(nums[i])) {
                Integer value = map.get(nums[i]) + 1;
                map.put(nums[i], value);
            } else {
                map.put(nums[i], 1);
            }
        }
        
        for (Integer key : map.keySet()) {
            Integer value = map.get(key);
            if (tops.size() < k) {
                tops.add(new Pair(key, value));
                Collections.sort(tops);
            } else if (tops.get(0).getValue() < value) {
                tops.set(0, new Pair(key, value));
                Collections.sort(tops);
            }
        }
        
        for (Pair p : tops) {
            results.add(p.getKey());
        }
        return results;
    }
}
