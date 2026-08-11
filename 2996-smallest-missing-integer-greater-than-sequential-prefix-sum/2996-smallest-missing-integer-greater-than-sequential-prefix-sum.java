class Solution {
    public int missingInteger(int[] nums) {
        List<Integer>al=new ArrayList<>();
        for(int l=0;l<nums.length;l++){
            al.add(nums[l]);
        }
      
        int j=1;
        int sum=nums[0];
        for(int i=0;i<nums.length-1;i++){
        if(nums[j] == nums[j - 1] + 1){
                sum+=nums[j];
                j++;
            }
         
        }
        
        while(al.contains(sum)){
            sum++;
        }
        return sum;
    }
}