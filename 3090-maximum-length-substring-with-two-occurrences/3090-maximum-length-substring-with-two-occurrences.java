class Solution {
    public int maximumLengthSubstring(String s) {
        int left=0;
        int max=0;
        HashMap<Character,Integer> hm=new HashMap<>();
        for(int i=0;i<s.length();i++){
            if(hm.containsKey(s.charAt(i))&& hm.get(s.charAt(i))<2){
                hm.put(s.charAt(i),hm.get(s.charAt(i))+1);
            }
            else if(!hm.containsKey(s.charAt(i))){
                hm.put(s.charAt(i),1);
            }
            else{
                hm.put(s.charAt(i),hm.get(s.charAt(i))+1);
                while(hm.get(s.charAt(i))>2){
                hm.put(s.charAt(left),hm.get(s.charAt(left))-1);
                left++;
                }
            }
        max=Math.max(max,i-left+1);

        }
     return max;  
    }
    
}