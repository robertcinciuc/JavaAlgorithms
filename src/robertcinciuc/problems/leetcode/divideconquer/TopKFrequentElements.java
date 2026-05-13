package robertcinciuc.problems.leetcode.divideconquer;

import java.util.*;

public class TopKFrequentElements {

    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> freq = new HashMap<>();

        for(int num : nums){
            freq.merge(num, 1, Integer::sum);
        }

        Map<Integer, Set<Integer>> inverseFreq = new TreeMap<>(Collections.reverseOrder());
        for(Integer key : freq.keySet()){
            inverseFreq.computeIfAbsent(freq.get(key), myKey -> new HashSet<>()).add(key);
        }

        int i = 0;
        int[] resp = new int[k];
        for(Integer freqForVal: inverseFreq.keySet()){
            for(Integer val: inverseFreq.get(freqForVal)) {
                if (i < k) {
                    resp[i] = val;
                }
                ++i;
            }
        }

        return resp;
    }

    public static void main(String[] args) {
        var v = new TopKFrequentElements();
        System.out.println(Arrays.toString(v.topKFrequent(new int[]{1, 1, 1, 2, 2, 3}, 2)));
        System.out.println(Arrays.toString(v.topKFrequent(new int[]{1, 2}, 2)));
    }

}
