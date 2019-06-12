//
//  BinaryTree.h
//  Algorithm-OC
//
//  Created by 曾维俊 on 2019/5/9.
//  Copyright © 2019年 Nius. All rights reserved.
//

#import <Foundation/Foundation.h>
#import "MJBinaryTrees.h"

typedef enum : NSUInteger {
    BTNodeDegreeZero,
    BTNodeDegreeOne,
    BTNodeDegreeTwo
} BTNodeDegree;

@interface BTNode : NSObject {
@public
    id _element;
    BTNode *_left;
    BTNode *_right;
    __weak BTNode *_parent;
}
- (BTNodeDegree)degree;
- (BOOL)isLeaf;
- (BOOL)isDegreeOne;
- (BOOL)isDegreeTwo;
- (BOOL)isLeftChild;
- (BOOL)isRightChild;
- (BTNode *)sibling;

+ (instancetype)nodeWithElement:(id)e parent:(BTNode *)p;

// Debug print
- (NSString *)debugPrintdescription;
@end

//__covariant
//__contravariant

/// 普通二叉树其实没有任何意义，一般使用子类
@interface BinaryTree<__covariant ObjectType> : NSObject {
    NSInteger _size;
    BTNode *_root;
}

- (NSInteger)size;
- (BOOL)isEmpty;
- (void)clear;
- (BOOL)isComplete; // 是否完全二叉树（从上到下，从左到右排列）
- (NSInteger)height; // 二叉树高度
- (NSInteger)heightWithNode:(BTNode *)node; // 某个节点的高度
- (BTNode *)precursor:(BTNode *)n; // 前驱节点（中序遍历的前一个节点）
- (BTNode *)successor:(BTNode *)n; // 后继节点（中序遍历的后一个节点）

// 遍历
- (void)preorderTraversal:(void(^)(ObjectType o, BOOL *stop))block;
- (void)inorderTraversal:(void(^)(ObjectType o, BOOL *stop))block;
- (void)postorderTraversal:(void(^)(ObjectType o, BOOL *stop))block;
- (void)levelOrderTraversal:(void(^)(ObjectType o, BOOL *stop))block;

@end

@interface BinaryTree(Printer)<MJBinaryTreeInfo>
@end

