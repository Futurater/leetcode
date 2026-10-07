class Solution {
    public List<String> removeInvalidParentheses(String s) {
        List<String> res=new ArrayList<>();
        Queue<String> q=new LinkedList<>();
        Set<String> set=new HashSet<>();
        boolean found=false;

        set.add(s);
        q.add(s);

    while(!q.isEmpty()){
        String curr=q.remove();
        if(isValid(curr)){
            res.add(curr);
            found=true;
        }

        if(found){
                continue;
            }

        for(int i=0;i<curr.length();i++){

            if(curr.charAt(i)!='(' && curr.charAt(i)!=')'){
                continue;
            }
            
            if(i>0 && curr.charAt(i)==curr.charAt(i-1)){
                continue;
            }

            String r=curr.substring(0,i)+curr.substring(i+1);
            if(!set.contains(r)){
                set.add(r);
                q.add(r);
            }

        }
        

    }
    return res;




    }

    public boolean isValid(String s){
        int count=0;
        for(int i=0;i<s.length();i++){
            if(s.charAt(i)=='('){
                count++;
            }
            if(s.charAt(i)==')'){
                count--;
            }
            if(count<0){
                return false;
            }
        }
        return count==0;
    }
}