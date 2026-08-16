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
 import java.math.BigInteger;
import java.util.*;
class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
       
       BigInteger n1= findint(l1);
       BigInteger n2= findint(l2);
       String sum=n1.add(n2).toString();
       ListNode head = null;
ListNode tail = null;

for(int i = sum.length() - 1; i >= 0; i--){
    ListNode node = new ListNode(sum.charAt(i) - '0');

    if(head == null){
        head = node;
        tail = node;
    } else {
        tail.next = node;
        tail = node;
    }
}

return head;
    }
public static BigInteger findint(ListNode head){
     Stack<Integer> st=new Stack<>();
        StringBuilder sb =new StringBuilder();
        while(head!=null){
            st.push(head.val);
            head=head.next;

        }
        while(st.size()!=0){
            sb.append(st.pop());
        }
       return new BigInteger(sb.toString());
}
}