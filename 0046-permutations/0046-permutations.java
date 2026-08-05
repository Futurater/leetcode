class Solution {
    public List<List<Integer>> permute(int[] nums) {
        boolean []used =new boolean[nums.length];
        List<List<Integer>>ans=new ArrayList<>();
        helper(nums,0,ans,used,new ArrayList<>());
        return ans;
    }

    void helper(int []nums,int i,List<List<Integer>> ans,boolean []used,List<Integer>al){

        if(al.size()==nums.length){
            ans.add(new ArrayList<>(al));
            return;
        }

        for( i=0;i<nums.length;i++){
            if(used[i])
                continue;

            used[i]=true;
            al.add(nums[i]);
            

            helper(nums,i,ans,used,al);

            al.remove(al.size()-1);
            used[i]=false;    
        }

    }
}
