/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public int[] nodesBetweenCriticalPoints(ListNode head) {
        ListNode prev=head;
        head=head.next;
        int i=1;
        List<Integer> al=new ArrayList<>();
        while(head.next!=null){
            i++;
            if(prev.val<head.val && head.val>head.next.val){
                al.add(i);
            }
             if(prev.val>head.val && head.val<head.next.val){
                al.add(i);
            }
            head=head.next;
            prev=prev.next;
        }
        if(!al.isEmpty()&& al.size()>=2 ){
        Collections.sort(al);
        int min=al.get(1)-al.get(0);
        int max=0;
        for(int j=1;j<al.size()-1;j++){
            min=Math.min(min,al.get(j+1)-al.get(j));

        }
        max=Math.max(al.get(al.size()-1)-al.get(0),max);

        return new int[]{min,max};
        }
        return new int[]{-1,-1};
    }
}