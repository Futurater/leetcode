class Solution {
    public int missingMultiple(int[] nums, int k) {

        ArrayList<Integer> a=new ArrayList<>();


        for(int i=0;i<nums.length;i++){
            a.add(nums[i]);
        }

        int j=1;

        while(a.contains(k*j)){
            j++;
        }

        return k*j;

    }
}