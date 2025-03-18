package robertcinciuc.problems.leetcode.array;

public class TwoSum {
    public int[] twoSum(int[] nums, int target) {
        int[] resp = new int[2];
        for(int i = 0; i < nums.length; ++i){
            resp[0] = i;
            for(int j = i + 1; j < nums.length; ++j){
                if(nums[i] + nums[j] == target){
                    resp[1] = j;
                    return resp;
                }
            }
        }

        return resp;
    }

    public static void main(String[] args){
        TwoSum twoSum = new TwoSum();
        int[] ints = twoSum.twoSum(new int[]{2, 7, 11, 15}, 9);
        System.out.println(ints);
    }
}
