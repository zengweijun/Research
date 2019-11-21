package com.nius.LinkedList;

// https://leetcode-cn.com/problems/reverse-linked-list/
public class _206_反转链表 {

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5};
        ListNode head = ListNode.createList(arr);
        ListNode.show(head);

        ListNode head1 = new _206_反转链表().reverseList1(head);
        ListNode.show(head1);

        ListNode head2 = new _206_反转链表().reverseList2(head1);
        ListNode.show(head2);
    }


    // 迭代版(该算法会改动原链表)
    public ListNode reverseList1(ListNode head) {
        ListNode prev = null;
        ListNode cur = head;
        while (cur != null) {
            ListNode tmpNext = cur.next;
            cur.next = prev;
            prev = cur;
            cur = tmpNext;
        }
        return prev;
    }

    // 递归版(该算法会改动原链表)
    public ListNode reverseList2(ListNode head) {
        return reverse(null, head);
    }

    private ListNode reverse(ListNode prev, ListNode cur) {
        if (cur == null) return prev;
        ListNode tmpNext = cur.next;
        cur.next = prev;
        prev = cur;
        cur = tmpNext;
        return reverse(prev, cur);
    }
}
