package robertcinciuc.problems.leetcode.array;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class MergeIntervals {

    public static class Interval {
        public final int start;
        public final int end;

        public Interval(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    public static class IntervalComparator implements Comparator<Interval> {

        @Override
        public int compare(Interval o1, Interval o2) {
            if(o1.start < o2.start){
                return -1;
            }else if(o1.start > o2.start){
                return 1;
            }
            return 0;
        }
    }

    public int[][] merge(int[][] intervals) {
        List<Interval> intervalsList = new ArrayList<>();
        for (int[] interval : intervals) {
            intervalsList.add(new Interval(interval[0], interval[1]));
        }
        intervalsList.sort(new IntervalComparator());

        List<Interval> mergedList = new ArrayList<>();
        int currentStart = intervalsList.get(0).start;
        int currentEnd = intervalsList.get(0).end;
        for(int i = 1; i < intervalsList.size(); ++i){
            if(intervalsList.get(i).start > currentEnd){
                mergedList.add(new Interval(currentStart, currentEnd));
                currentStart = intervalsList.get(i).start;
            }
            if(intervalsList.get(i).end > currentEnd){
                currentEnd = intervalsList.get(i).end;
            }
        }
        if(intervalsList.size() > 0){
            mergedList.add(new Interval(currentStart, currentEnd));
        }

        int[][] result = new int[mergedList.size()][2];
        for(int i = 0; i < mergedList.size(); ++i){
            result[i][0] = mergedList.get(i).start;
            result[i][1] = mergedList.get(i).end;
        }

        return result;
    }

    public static void main(String[] args){
        MergeIntervals mergeIntervals = new MergeIntervals();
        int[][] input = new int[][]{{1,3},{2,6},{8,10},{15,18}};
        int[][] merge = mergeIntervals.merge(input);
        System.out.println();
    }

}
