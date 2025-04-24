package robertcinciuc.problems.leetcode.heap;

import java.util.ArrayList;
import java.util.List;

public class KClosestPointsToOrigin {

    private static class PointNode {
        public final double dist;
        public final int x;
        public final int y;

        private PointNode(double dist, int x, int y) {
            this.dist = dist;
            this.x = x;
            this.y = y;
        }
    }

    public int[][] kClosest(int[][] points, int k) {
        List<PointNode> minHeap = new ArrayList<>();
        for (int[] point : points) {
            double dist = getDistOrigin(point[0], point[1]);
            insert(minHeap, dist, point[0], point[1]);
        }

        int[][] response = new int[k][2];
        for(int i = 0; i < k; ++i){
            PointNode remove = remove(minHeap);
            response[i][0] = remove.x;
            response[i][1] = remove.y;
        }

        return response;
    }

    private double getDistOrigin(int x, int y){
        return Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
    }

    private void insert(List<PointNode> minHeap, double dist, int x, int y){
        minHeap.add(new PointNode(dist, x, y));
        heapifyInsertion(minHeap, minHeap.size() - 1);
    }

    private void heapifyInsertion(List<PointNode> minHeap, int i){
        if(i == 0){
            return;
        }

        int parentIndex = (i - 1)/2;
        if(parentIndex >= 0 && minHeap.get(parentIndex).dist > minHeap.get(i).dist){
            swap(minHeap, parentIndex, i);
        }
        heapifyInsertion(minHeap, parentIndex);
    }

    private PointNode remove(List<PointNode> minHeap){
        PointNode response = minHeap.get(0);

        if(minHeap.size() == 1){
            minHeap.remove(0);
            return response;
        }

        PointNode last = minHeap.get(minHeap.size() - 1);
        minHeap.remove(minHeap.size() - 1);
        minHeap.set(0, last);
        heapifyRemoval(minHeap, 0);
        return response;
    }

    private void heapifyRemoval(List<PointNode> minHeap, int i){
        PointNode left = null;
        int leftIndex = i * 2 + 1;
        if(leftIndex < minHeap.size()){
            left = minHeap.get(leftIndex);
        }

        PointNode right = null;
        int rightIndex = i * 2 + 2;
        if(rightIndex < minHeap.size()){
            right = minHeap.get(rightIndex);
        }

        if(left != null && left.dist <= minHeap.get(i).dist && (right == null || left.dist <= right.dist)){
            swap(minHeap, leftIndex, i);
            heapifyRemoval(minHeap, leftIndex);
        }else if(right != null && left != null && right.dist <= left.dist && right.dist <= minHeap.get(i).dist){
            swap(minHeap, rightIndex, i);
            heapifyRemoval(minHeap, rightIndex);
        }
    }

    private void swap(List<PointNode> minHeap, int i, int j){
        PointNode tmp = minHeap.get(i);
        minHeap.set(i, minHeap.get(j));
        minHeap.set(j, tmp);
    }

    public static void main(String[] args){
        KClosestPointsToOrigin solution = new KClosestPointsToOrigin();
        int[][] resp1 = solution.kClosest(new int[][]{{1, 3}, {-2, 2}}, 1);
        int[][] resp2 = solution.kClosest(new int[][]{{3, 3}, {5, -1}, {-2, 4}}, 2);
        System.out.println();
    }
}
