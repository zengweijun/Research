package 链表;
/**
 * 反转一个单链表。
 * 
 * 示例:
 *		输入: 1->2->3->4->5->NULL
 *		输出: 5->4->3->2->1->NULL
 *
 * 使用递归或迭代两种方式解决
 * 
 * 迭代：正想思维，即一种从头到尾的思考模式：for、while循环等
 * 递归：逆向思维，即一种从尾向前的思考模式：函数自己调自己
 * 
 * note：整体思路：
 * 		1.新建一个空链表 hader->null
 * 		2.遍历原链表，将遍历到的元素插入到新链表头部
 * 
 * 参考思路：https://blog.csdn.net/FX677588/article/details/72357389
 * 
 * https://leetcode-cn.com/problems/reverse-linked-list/
 * @author nius
 *
 */
public class _206_反转链表 {

	//Definition for singly-linked list.
	public static class ListNode {
		int val;
		ListNode next;
		ListNode(int x) { val = x; }
	}
	
	/// --------------------- 迭代模式 ---------------------
	// head->1->2->3->4->5->NULL
	// head->5->4->3->2->1->NULL
	public static ListNode reverseList(ListNode head) {
		// 空链表 or 只有一个节点
        if (head == null || head.next == null) return head;
		
        // 迭代
        // 1.新建一个空链表
        ListNode newHead = null; 
        
        // 2.遍历原链表，将遍历到的元素插入新链表头部
        /**
         * 0.初始状态
         *  head->1->2->3->4->5->null
         *  newHead->null
         * 1.第一次循环
         * head == 1 不为空进入循环
         * temp == 2
         * head.next -> 2 ==> head.next -> null === (1 - > null) 
         * newHead = head ==> newHead -> head == newHead -> 1
         * head = temp ==> head -> 2
         * ----> newHead->1->null
         * 
         * 2.第二次循环
         * head == 2 不为空进入循环
         * temp == 3
         * head.next -> 3 ==> head.next -> 1 === (2 -> 1) 
         * newHead = head ==> newHead -> head == newHead -> 2
         * head = temp ==> head -> 3
         * ----> newHead->2->1->null
         * 
         * ...
         */
        while (head != null) {
			ListNode temp = head.next; // 先记录head的下一个节点
			head.next = newHead;
			newHead = head;
			head = temp;
		}
		return newHead;
    }
	
	/// --------------------- 递归模式 ---------------------
	// head->1->2->3->4->5->NULL
	// head->5->4->3->2->1->NULL
	public static ListNode re_reverseList(ListNode head) {
		if (head == null || head.next == null) return head;
		ListNode newHead = re_reverseList(head.next); //一直循环到链尾 
		
		// note：此处需要等到re_reverseList子层的栈结束才会返回
		head.next.next = head; //翻转链表的指向
		head.next = null; //记得赋值NULL，防止链表错乱		
		return newHead; //新链表头永远指向的是原链表的链尾
	}
	
	/**
	{
	head->1->2->3->null
		{
		head->2->3->null
		
			{
			head->3->null
			// head.next == null成立
			// 直接返回head
			// 此时结构如此：head->3->null
			// 1.------->代码来到这里return head，此栈中后边的三句代码不会执行
			
			head.next.next = head; 
			head.next = null; 		
			return newHead; 
			} // 此栈结束，返回到上一层栈
			
		// 2.------->代码来到这里
		// 返回的head赋值给newHead
		// 此时newHead->3->null，head->2->3->NULL，执行后边代码
		
		// head=2,head.next=3,head.next.next=3.next
		head.next.next = head;  ==> 3->2
		head.next = null; 	==> 3->null	
		return newHead; 
		
		// 3.------->代码来到这里
		// newHead->3->null
		}
		
	head.next.next = head; 
	head.next = null; 		
	return newHead; 
	}
	 */
	
	
	
	public static void printLinkList(ListNode head) {
		ListNode tempHead = head;
		StringBuilder string = new StringBuilder();
		string.append("head->");
		
		while (tempHead != null) {
			string.append(tempHead.val).append("->");
			tempHead = tempHead.next;
		}
		string.append("null");
		System.out.println(string);
	}
	
	public static void main(String[] args) {
		
		// 创建链表 1->2->3->4->5->null
		ListNode node1  = new ListNode(1);
		ListNode node2 = new ListNode(2);
		node1.next = node2;
		ListNode node3 = new ListNode(3);
		node2.next = node3;
		ListNode node4 = new ListNode(4);
		node3.next = node4;
		ListNode node5 = new ListNode(5);
		node4.next = node5;
		ListNode head = node1;

		// 翻转前
		printLinkList(head);

		// 翻转后	
		ListNode newHead = reverseList(head);
		printLinkList(newHead);
		
		// 再递归翻转
		ListNode newHead2 = re_reverseList(newHead);
		printLinkList(newHead2);
	}

}
