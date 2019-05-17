//
//  BSTree.h
//  Algorithm-OC
//
//  Created by 曾维俊 on 2019/5/9.
//  Copyright © 2019年 Nius. All rights reserved.
//

#import "BinaryTree.h"

/**
 二叉搜索树(二叉查找树、二叉排序树)
        ┌──────8──────┐
        │             │
   ┌────3────┐        10───┐
   |         |             │
   1     ┌───6───┐     ┌───14
         │       │     │
         4       7     13
 
 1.任意一节点的值都大于其左子树所有节点的值
 2.任意一节点的值都小于其右子树所有节点的值
 3.它的左右子树也是一棵二叉搜索树
 
 二叉搜索树可以大大提高搜索效率，但是二叉搜索树存储的元素必须具备可比较性
 */
@protocol BSTree <NSObject>
- (void)afterAdd:(BTNode *)node;
- (void)afterRemove:(BTNode *)node;
- (__kindof BTNode *)createNodeWithElement:(id)o parent:(BTNode *)parent;
@end

@interface BSTree<ObjectType> : BinaryTree<ObjectType><BSTree>

+ (instancetype)tree;
+ (instancetype)treeWithComparatorBlock:(NSComparisonResult(^)(ObjectType e1, ObjectType e2))comparatorBlock;

- (void)add:(ObjectType)o;
- (void)remove:(ObjectType)o;
- (BOOL)contains:(ObjectType)o;

@end




