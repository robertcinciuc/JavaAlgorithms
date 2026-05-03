package robertcinciuc.problems.leetcode.divideconquer;

public class FindPeakElement {
    public int findPeakElement(int[] nums) {
        if(nums.length == 1){
            return 0;
        }

        int i = nums.length / 2;
        while(i > 0 && i < nums.length - 1){
            if(isPeak(nums, i)){
                return i;
            }else{
                if(nums[i + 1] > nums[i] && nums[i + 1] >= nums[i - 1]){
                    i++;
                }else{
                    i--;
                }
            }
        }

        if(nums[0] > nums[1]){
            return 0;
        }

        if(nums[nums.length - 2] < nums[nums.length - 1]){
            return nums.length - 1;
        }

        return 0;
    }

    private boolean isPeak(int[] nums, int i){
        if(i > 0 && i < nums.length - 1){
            return nums[i - 1] < nums[i] && nums[i] > nums[i + 1];
        }else{
            if(i == 0 && nums[i] > nums[i + 1]){
                return true;
            }
            return i == nums.length - 1 && nums[i] > nums[i - 1];
        }
    }

    public static void main(String[] args){
        FindPeakElement findPeakElement = new FindPeakElement();
        System.out.println(findPeakElement.findPeakElement(new int[]{1,2,3,1}));
        System.out.println(findPeakElement.findPeakElement(new int[]{1,2,1,3,5,6,4}));
        System.out.println(findPeakElement.findPeakElement(new int[]{1,2}));
        System.out.println(findPeakElement.findPeakElement(new int[]{1,2,1}));
    }
}
