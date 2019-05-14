//
//  BBSTree.h
//  Algorithm-OC
//
//  Created by 曾维俊 on 2019/5/13.
//  Copyright © 2019年 Nius. All rights reserved.
//

#import "BSTree.h"

/**
 平衡二叉搜索树‘BalanceBinarySearchTree’
 
 该类是AVLTree是和RBTree(红黑树)的抽象，主要提供部分公用接口
  */
@interface BBSTree : BSTree

/// 左旋转
- (void)rotateLeft:(BTNode *)node;

/// 右旋转
- (void)rotateRight:(BTNode *)node;

// 左旋转和右旋转完成都会调用
- (void)afterRotate:(BTNode *)node child:(BTNode *)child tmpRoot:(BTNode *)tmpRoot;

/** 对于不平衡节点的处理，可以统一处理所有情况
 针对四中个情况（x表示添加元素的位置）
              (LL)                (LR)             (RR)                    (RL)
          ┌─────f────┐        ┌────f────┐      ┌─────b────┐            ┌─────b────┐
          │          │        │         │      |          │            |          │
    ┌─────d────┐     g  ┌─────b────┐    g      a    ┌─────d────┐       a    ┌─────f────┐
    │          │        │          │                │          │            │          │
 ┌──b──┐       e        a       ┌──d──┐             c       ┌──f──┐      ┌──d──┐       g
 |     |                        |     |                     |     |      |     |
 a     c                        c     e                     e     g      c     e
 x                              x                           x            x
 
 经过处理（单旋转、双旋转）发现，四种情况最终结果相同，如下：
              ┌─────d────┐
              │          │
           ┌──b──┐    ┌──f──┐
           |     |    │     │
           a     c    e     g
          (x)   (x)  (x)   (x)
 x可能出于a/c/e/g下某个位置，但是整体达到平衡后，四中个情况的结果相同，因此若在选择之前将a/b/c/d/e/f/g寻找出来，统一处理逻辑更为方便
 最终都是d为根节点，左右分别为b、f，b的左右分别a、c，f的左右分别为e、g
 总结为三棵树：a<-b->c,b<-d->f,e<-f->g,d为根节点
 */
- (void)rotateWithUnbalance:(BTNode *)unbalance
                          a:(BTNode *)a b:(BTNode *)b c:(BTNode *)c
                          d:(BTNode *)d
                          e:(BTNode *)e f:(BTNode *)f g:(BTNode *)g;
@end

