class Solution {
    public int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> pq=new PriorityQueue<>(Comparator.reverseOrder());

        for(int i=0;i<nums.length;i++){
            pq.add(nums[i]);
        }

        int q=Integer.MAX_VALUE;
        while(k!=0){
            q=Math.min(pq.remove(),q);
            k--;
        }
        return q;
    }
}