class Solution {
    public boolean checkDivisibility(int n) {
        int sum=0;
        int mul=1;
        int m=n;
        while(n!=0){
            int digit=n%10;
            sum+=digit;
            mul*=digit;
            n=n/10;
        }

        int res=sum+mul;
        if(m%res==0){
            return true;
        }
        return false;
    }
}