class Solution {
    public int firstStableIndex(int[] nums, int k) {
        int op=-1;
        int kk=k;
        for(int i=0;i<nums.length;i++){
        int max=findmax(nums,i);
        int min=findmin(nums,i);
        if(max-min<=k){
           return i;
        }
        }
       return -1;

    }

    public int findmax(int[]nums,int i){
        int max=nums[0];
        for(int j=0;j<=i;j++){
            max=Math.max(max,nums[j]);
        }
        return max;
    }
     public int findmin(int[]nums,int i){
        int max=nums[i];
        for(int j=i;j<nums.length;j++){
            max=Math.min(max,nums[j]);
        }
        return max;
    }



}