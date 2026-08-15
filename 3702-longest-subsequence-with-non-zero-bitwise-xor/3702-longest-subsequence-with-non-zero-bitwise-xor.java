class Solution {
    public int longestSubsequence(int[] nums) {
     
      
       int sum=0;
       
       for(int right=0;right<nums.length;right++){
        sum^=nums[right];
       }
          if(sum!=0){
            return nums.length;
        
       }
         for(int i=0;i<nums.length;i++){
            if(nums[i]!=0){return nums.length-1;}
         }
       
      
  
    
    
   return 0;  
}}