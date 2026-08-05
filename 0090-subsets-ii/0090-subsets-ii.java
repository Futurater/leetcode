class Solution {
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> ans=new ArrayList<>();
    Arrays.sort(nums);
        helper(nums,ans,new ArrayList<>(),0);
        return ans;
    }

    public void helper(int[]nums, List<List<Integer>> ans,List<Integer>al,int i){

        if(i==nums.length){
            if(!ans.contains(al))
            ans.add(new ArrayList<>(al));
            return;
        }

        al.add(nums[i]);
        helper(nums,ans,al,i+1);

        al.remove(al.size()-1);
        helper(nums,ans,al,i+1);
    }
}