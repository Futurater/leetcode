class Solution {
    public boolean isHappy(int n) {
        HashSet<Integer> hs=new HashSet<>();
while(n!=1){
        if(hs.contains(n)){
            return false;
        }

        hs.add(n);
        int sum=0;
        while(n>0){
            int d=n%10;
            sum+=d*d;
            n=n/10;
        }

 
       n=sum;
}
return true;
    }
}