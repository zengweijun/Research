//
//  RBTree.m
//  Algorithm-OC
//
//  Created by 曾维俊 on 2019/5/13.
//  Copyright © 2019年 Nius. All rights reserved.
//

#import "RBTree.h"

typedef BOOL RBTNodeColor;
static const RBTNodeColor RedColor = YES;
static const RBTNodeColor BlackColor = NO;

@interface RBTNode : BTNode {
    @public RBTNodeColor _color;
}
@end

@implementation RBTNode
- (instancetype)init {
    self = [super init];
    if (self) {
        _color =  RedColor;
    }
    return self;
}
- (NSString *)debugPrintdescription {
    return [NSString stringWithFormat:@"%@_(%@)", self->_element,
            ((self->_color == RedColor) ? @"R" : @"B")];
}
@end

@implementation RBTree

- (void)afterAdd:(BTNode *)node {
    /** 红黑树的添加一共拥有12种情况
        note：此图如果显示h不正常，请将xcode拉长即可
     
                     ┌──────────────── (R_38) <-- (B_55) ---------> (R_80)─────────────┐
                     |                   |                             |               |
                     |                   |                             |               |
         (R_17)<--(B_25)-->(R_33)      (B_46)-->(R_50)      (R_72)<--(B_76)         (B_88)
addNode: 左  右             左  右       左       左  右       左  右        右         左  右
    新创建的节点默认为红色
    1. 父节点为黑色的4种情况B_88左右、B_76右、B_46左，这四中个情况添加上去不会导致红黑色树性质被破坏，无需处理
    2. 父节点为红色时，分为两大类共8种情况，用叔父节点颜色区分
       叔父节点为黑色(即null节点)
          LL：R_72左，只需将其祖父节点(B_76右旋转)，新node染黑、B_76染红
          LR：R_72右，将父节点(R_72左旋转)，祖父节点(B_76右旋转)，父节点染黑、B_76染红
          RR：R_50右，只需将其祖父节点(B_46左旋转)，新node染黑、B_46染红
          RL：R_50左，将父节点(R_50右旋转)，祖父节点(B_46左旋转)，父节点染黑、B_46染红
        叔父节点为红色（R_17、R_25）情况
          LL上溢：R_17左，此时重B(2-3-4树)角度看，发生了上溢，由于17和33节点都刚好在25两边，此时将25向上合并最简单，过程如下：
                 将新node的父节点(17)和叔父节点(33)染黑，其实实质是将其变为根节点（实现实质节点分离）
                 将新node的祖父节点25染红，作为新节点向上添加(执行添加逻辑)，实质为向上合并，向上合并
          LR上溢：R_17右，同上
          RR上溢：R_33右，同上
          RL上溢：R_33左，同上
     */
    
    BTNode *parent = node->_parent;
    if (!parent) { // 根节点
        [self setBlack:node];
        return; // 染黑直接返回
    }
    
    // 1.父节点是黑色，直接返回
    if ([self isBlack:parent]) return;
    
    // 2.取得叔父uncle节点和祖父grand节点
    BTNode *uncle = parent.sibling;
    BTNode *grand = parent->_parent;
    
    // 2.1 叔父节点为红色
    if ([self isRed:uncle]) {
        // 将父节点和叔父节点染黑，将祖父节点染红向上添加
        [self setBlack:parent];
        [self setBlack:uncle];
        [self afterAdd:[self setRed:grand]];
        return;
    }
    
    // 2.2 叔父节点为黑色
    // 其实祖父节点无论如何都会被染红，一次旋转父节点染黑，如果两次旋转自己染黑
    [self setRed:grand];
    if (parent.isLeftChild) { // L
        if (node.isLeftChild) { // LL
            [self setBlack:parent];
        } else { // LR
            [self setBlack:node];
            [self rotateLeft:parent];
        }
        [self rotateRight:grand];
    } else { // R
        if (node.isRightChild) { // RR
            [self setBlack:parent];
        } else { // RL
            [self setBlack:node];
            [self rotateRight:parent];
        }
        [self rotateLeft:grand];
    }
}

- (void)afterRemove:(BTNode *)node replace:(BTNode *)replace {
    // 根据二叉搜索树的性质，实际被删除的节点要么度为1，要么度为0
    
    // 删除的节点度为1
    if (replace) { // 实际删除的节点度为1，要用replace节点取代之
        // replace取代之后，独立成一个叶子节点（实际是子树的根），需染成黑色
        [self setBlack:replace];
        return;
    }
    
    // 删除的节点度为0
    if ([self isRed:node]) return; // 红色叶子节点
    
    // 黑色叶子节点（发生下溢）
    
    
}

- (BTNode *)createNodeWithElement:(id)o parent:(BTNode *)parent {
    return [RBTNode nodeWithElement:o parent:parent];
}

#pragma mark - Private
/// 染色
- (BTNode *)set:(BTNode *)node color:(RBTNodeColor)color {
    if (!node) return node;
    ((RBTNode *)node)->_color = color;
    return node;
}

/// 将节点染成红色
- (BTNode *)setRed:(BTNode *)node {
    return [self set:node color:RedColor];
}

/// 将节点染成黑色
- (BTNode *)setBlack:(BTNode *)node {
    return [self set:node color:BlackColor];
}

/// 获取节点颜色，如果节点不存在(即最后的null节点默认为黑色)
- (RBTNodeColor)colorOf:(BTNode *)node {
    return !node ? BlackColor : ((RBTNode *)node)->_color;
}

/// 是否是红色节点
- (BOOL)isBlack:(BTNode *)node {
    return [self colorOf:node] == BlackColor;
}

/// 是否为黑色节点
- (BOOL)isRed:(BTNode *)node {
    return [self colorOf:node] == RedColor;
}

@end
