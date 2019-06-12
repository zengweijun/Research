//
//  BSTree.m
//  Algorithm-OC
//
//  Created by 曾维俊 on 2019/5/9.
//  Copyright © 2019年 Nius. All rights reserved.
//

#import "BSTree.h"

@interface BSTree()
@end

@implementation BSTree {
    NSComparisonResult(^_comparatorBlock)(id e1, id e2);
}
+ (instancetype)tree {
    return [self treeWithComparatorBlock:nil];
}

+ (instancetype)treeWithComparatorBlock:(NSComparisonResult (^)(id, id))comparatorBlock {
    BSTree *tree = [BSTree new];
    tree->_comparatorBlock = comparatorBlock;
    return tree;
}

- (void)add:(id)o {
    if (!o) return;
    
    if (!_root) {
        _root = [self createNodeWithElement:o parent:nil];
        _size++;
        [self afterAdd:_root];
        return;
    }
    
/*
      ┌──────8──────┐
      │             │
 ┌────3────┐        10───┐
 |         |             │
 1     ┌───6───┐     ┌───14
       │       │     │
       4       7     13
 1.找到父节点parent
 2.创建新节点node
 3.让父节点parent的left或者right=node
 
 */
    
    BTNode *temp = _root;
    BTNode *parent = _root;
    NSComparisonResult result = NSOrderedSame;
    while (temp) {
        parent = temp;
        result = [self _compare:o e2:temp->_element];
        switch (result) {
            case NSOrderedDescending:
                temp = temp->_right;
                break;
            case NSOrderedAscending:
                temp = temp->_left;
                break;
            default: // 相等(替换原来的对象)
                temp->_element = o;
                return;
        }
    }
    
    BTNode *node = [self createNodeWithElement:o parent:parent];
    if (result == NSOrderedDescending) {
        parent->_right = node;
    } else {
        parent->_left = node;
    }
    _size++;
    [self afterAdd:node];
}

- (void)removeNode:(BTNode *)node {
    if (!node) return;
    
    _size--;
    /**
     一、若该节点度为0（直接删除）
      1.1 若为根节点：root = nil
      1.2 若为非根节点：
          node是左子node.parent.left = nil
          node是右子node.parent.right = nil
     
     二、若该节点度为1（直接删除）
      1.1 若为根节点：直接让root绕过自己指向自己子节点：
          node有左子root = node.left
          node有右子root = node.left
          并且，其对应子节点child.parent = nil
     
      1.2 让父节点绕过自己指向子节点即可
          node是左子node.parent.left = node.child
          node是右子node.parent.right = node.child
     
     三、若节点node度为2，应该用前驱或者后继节点替代之(不会破坏二叉搜索树的特点)
     1.先找到前驱或者后继节点tmp，用tmp覆盖node的值，删掉tmp
       此处获取到的node的前驱在左子树中寻找最右节点（最大节点）
       此处获取到的node的后继在右子树中寻找最左节点（最小节点）
       所以，node获取到的前驱或者后继tmp一定是度为1或度为0的节点
           度为0是叶子节点，度为1有如下两种情况（后驱同理）
              前驱度为0             前驱度为1
              ┌────root             ┌────root
            ...                    ...
       ┌──... ──┐             ┌──... ──┐
      ...      tmp           ...    ┌──tmp
                                   ...
     2.由于获取到的tmp只能是度为0或度为1的节点，则删除过程回到（一、二）逻辑
     */
    
    // 度为2，先将其值覆盖，然后deleteNode指向其前驱后后继，删除deleteNode
    BTNode *deleteNode = node;
    if (deleteNode.isDegreeTwo) {
        BTNode *replacement = [self successor:deleteNode];
//        BTNode *replacement = [self precursor:node];
        deleteNode->_element = replacement->_element;
        deleteNode = replacement;
    }
    
    // 来到这里的deleteNode一定是度为1或者0的节点
    BTNode *replacement = deleteNode->_left? deleteNode->_left : deleteNode->_right;
    if (replacement) { // 度为1
        if (!deleteNode->_parent) {//根节点
            _root = replacement;
            replacement->_parent = nil;
        } else {
            if (deleteNode == deleteNode->_parent->_left) {
                deleteNode->_parent->_left = replacement;
            } else {
                deleteNode->_parent->_right = replacement;
            }
            replacement->_parent = deleteNode->_parent;
            
            // 此处replacement其实继承了node角色特点
            [self afterRemove:replacement];
        }
    } else { // 度为0
        if (!deleteNode->_parent) { //根节点
            _root = nil; // 根节点无需处理平衡
        } else {
            if (deleteNode == deleteNode->_parent->_left) {
                deleteNode->_parent->_left = nil;
            } else {
                deleteNode->_parent->_right = nil;
            }
            [self afterRemove:deleteNode];
        }
    }
}

- (void)remove:(id)o {
    return [self removeNode:[self _node:o]];
}

- (BOOL)contains:(id)o {
    return [self _node:o] != nil;
}

#pragma mark - Private
- (NSComparisonResult)_compare:(id)e1 e2:(id)e2 {
    return _comparatorBlock?_comparatorBlock(e1, e2):[e1 compare:e2];
}

- (BTNode *)_node:(id)o {
    BTNode *temp = _root;
    while (temp) {
        NSComparisonResult result = [self _compare:o e2:temp->_element];
        switch (result) {
            case NSOrderedAscending: temp = temp->_left; break;
            case NSOrderedDescending: temp = temp->_right; break;
            default:
                return temp;
                break;
        }
    }
    return nil;
}

#pragma mark - Subclass Override
- (BTNode *)createNodeWithElement:(id)o parent:(__kindof BTNode *)parent {
    return [BTNode nodeWithElement:o parent:parent];
}

- (void)afterAdd:(__kindof BTNode *)node { }
- (void)afterRemove:(__kindof BTNode *)node { }
@end

