class Solution {
    public boolean uniformArray(int[] nums1) {
        Arrays.sort(nums1);

       if(nums1[0]%2==0){
        for(int num:nums1){
            if(num%2==1){
                return false;
            }
        }
       }
       return true;
    }
}