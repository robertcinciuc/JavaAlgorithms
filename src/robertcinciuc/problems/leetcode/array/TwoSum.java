package robertcinciuc.problems.leetcode.array;

import java.util.HashMap;
import java.util.Map;

public class TwoSum {

    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> valuePosition = new HashMap<>();
        for(int i = 0; i < nums.length; ++i){
            valuePosition.put(nums[i], i);
        }

        int[] resp = new int[2];
        for(int i = 0; i < nums.length; ++i){
            if(valuePosition.containsKey(target - nums[i]) && valuePosition.get(target - nums[i]) != i){
                resp[0] = i;
                resp[1] = valuePosition.get(target - nums[i]);
                return resp;
            }
        }

        return resp;
    }

    public static void main(String[] args){
        TwoSum twoSum = new TwoSum();
        int[] ints = twoSum.twoSum(new int[]{2, 7, 11, 15}, 9);
        System.out.println(ints[0] + " " + ints[1]);
    }
}
