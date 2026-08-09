class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int i=0;
        int j=0;
        List<Integer> al=new ArrayList<>();
    while(i<nums1.length && j<nums2.length){
        if(nums1[i]<=nums2[j]){
            al.add(nums1[i]);
            i++;
        }
        else{
            al.add(nums2[j]);
            j++;
        }
    }
    while(i<nums1.length){
        al.add(nums1[i]);
            i++;
    }
     while(j<nums2.length){
        al.add(nums2[j]);
            j++;
    }
    if(al.size()%2==0){
       return (double)(al.get(al.size()/2)+al.get(al.size()/2-1))/2;
    }else{
        return (long)al.get(al.size()/2);
    }
    
    }
}