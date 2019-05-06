//
//  _237_删除链表中的节点.m
//  链表
//
//  Created by 曾维俊 on 2019/5/6.
//  Copyright © 2019年 Nius. All rights reserved.
//

#import "_237_删除链表中的节点.h"
#import "ListNode.h"

/**
 示例 1:
    输入: head = [4,5,1,9], node = 5
    输出: [4,1,9]
    解释: 给定你链表中值为 5 的第二个节点，那么在调用了你的函数之后，该链表应变为 4 -> 1 -> 9.
 
 示例 2:
    输入: head = [4,5,1,9], node = 1
    输出: [4,5,9]
    解释: 给定你链表中值为 1 的第三个节点，那么在调用了你的函数之后，该链表应变为 4 -> 5 -> 9.
 
 解题思路：
    1->2->3->4
    要删除2:
        1.先让2的下一个节点3的值覆盖2自己的值 1->3->3->4
        2.再直接让2绕过3指向4即可：1->3->4
 */

@implementation _237_________

void deleteNode(ListNode *node) {
    node.val = node.next.val;
    node.next = node.next.next;
}

+ (void)main {
    NSMutableArray *numbers = @[].mutableCopy;
    for (NSInteger i = 0; i < 10; i++) {
        [numbers addObject:@(i)];
    }
    ListNode *head = createList(numbers);
    printList(head, @"删除前 head:");
    
    // 删除链表5节点
    ListNode *destDeleteNode = head;
    while (destDeleteNode) {
        if (destDeleteNode.val == 5) break;
        destDeleteNode = destDeleteNode.next;
    }
    deleteNode(destDeleteNode);
    printList(head, @"删除5后head:");
    
    // 删除链表8节点
    ListNode *destDeleteNode1 = head;
    while (destDeleteNode1) {
        if (destDeleteNode1.val == 8) break;
        destDeleteNode1 = destDeleteNode1.next;
    }
    deleteNode(destDeleteNode1);
    printList(head, @"删除8后head:");
}

@end
