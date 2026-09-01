class Solution {
    public int search(int[] nums, int target) {
        int i=0;
        int j=nums.length-1;
        
       while(i<=j){
       int mid= i + (j - i) / 2;
         if(target<nums[mid]){
            j=mid-1;
        }
        if(target>nums[mid]){
            i=mid+1;
        }
        if(target==nums[mid]){
            return mid;
        }

       }
       return -1;
    }
}