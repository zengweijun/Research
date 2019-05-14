//
//  BBSTree.m
//  Algorithm-OC
//
//  Created by 曾维俊 on 2019/5/13.
//  Copyright © 2019年 Nius. All rights reserved.
//

#import "BBSTree.h"

@implementation BBSTree

- (void)rotateLeft:(BTNode *)node {
    // 让其右子节点代替自己作为根(right ==> node)
    BTNode *right = node->_right;
    
    node->_right = right->_left;
    right->_left = node;
    
    // 让right成为新子树的根
    // 并更新node、right、right->_left的parent
    [self afterRotate:node child:node->_right tmpRoot:right];
}

- (void)rotateRight:(BTNode *)node {
    BTNode *left = node->_left;
    
    node->_left = left->_right;
    left->_right = node;
    
    // 让left成为新子树的根
    // 并更新node、left、left->_right的parent
    [self afterRotate:node child:node->_left tmpRoot:left];
}

- (void)afterRotate:(BTNode *)node child:(BTNode *)child tmpRoot:(BTNode *)tmpRoot {
    if (node.isLeftChild) {
        node->_parent->_left = tmpRoot;
    } else if (node.isRightChild) {
        node->_parent->_right = tmpRoot;
    } else { // node是根root节点
        _root = tmpRoot;
    }
    
    tmpRoot->_parent = node->_parent;
    node->_parent = tmpRoot;
    if (child) {
        child->_parent = node;
    }
}

- (void)rotateWithUnbalance:(BTNode *)unbalance
                          a:(BTNode *)a b:(BTNode *)b c:(BTNode *)c
                          d:(BTNode *)d
                          e:(BTNode *)e f:(BTNode *)f g:(BTNode *)g {
    /** a<-b->c、 b<-d->f、  e<-f->g
        ┌─────d────┐
        │          │
     ┌──b──┐    ┌──f──┐
     |     |    │     │
     a     c    e     g
     */
    
    // 处理根节点d
    // tmpRoot为失衡的根节点
    // 让d成为c这可树的根节点
    d->_parent = unbalance->_parent;
    if (unbalance.isLeftChild) {
        unbalance->_parent->_left = d;
    } else if (unbalance.isRightChild) {
        unbalance->_parent->_right = d;
    } else {
        _root = d;
    }
    
    // 处理 a<-b->c
    b->_left = a;
    b->_right = c;
    if (a) a->_parent = b;
    if (c) c->_parent = b;
    
    // e<-f->g
    f->_left = e;
    f->_right = g;
    if (e) e->_parent = f;
    if (g) g->_parent = f;
    
    // b<-d->f
    d->_left = b;
    d->_right = f;
    // b、d、f是造成失衡的基本条件，不可能为空，因此不用if判断
    b->_parent = d;
    f->_parent = d;
}

@end
