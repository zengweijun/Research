//
//  main.cpp
//  BinaryHeap
//
//  Created by ybf on 2019/6/12.
//  Copyright © 2019 nius. All rights reserved.
//

#include <iostream>
#include "BinaryHeap.cpp"

using namespace std;

int compare(int a, int b) {
    return b-a;
}

int main(int argc, const char * argv[]) {
    
    cout << "普通方式添加元素到堆(默认大顶堆)----------------------" << endl;
    BinaryHeap<int> *bheap = new BinaryHeap<int>(3);
    bheap->add(10);
    bheap->add(40);
    bheap->add(30);
    bheap->add(60);
    bheap->add(90);
    bheap->add(70);
    bheap->add(20);
    bheap->add(50);
    bheap->add(80);
    bheap->add(10);
    bheap->print();
    
    cout << "批量方式添加元素到堆（传入函数指针小顶堆）----------------------" << endl;
    int arr[] = {10,40,30,60,90,70,20,50,80,10};
    BinaryHeap<int> *bheap1 = new BinaryHeap<int>(arr, 10, &compare);
    bheap1->print();
    
    cout << "top k 问题，求前4个最大数（小顶堆）----------------------" << endl;
    // 求出前4个最大数(求最大：使用小顶堆，求最小使用大顶堆)
    // 预期：98 96 92 89
    int arr2[] = {28, 9, 98, 5, 11, 22, 6, 89, 14, 21, 62, 65, 16, 96, 63, 92, 49, 46, 9, 39};
    BinaryHeap<int> *minHeap = new BinaryHeap<int>(4, &compare);
    for (int i = 0; i < 20; i++) {
        if (i < 4) minHeap->add(arr2[i]);
        else {
            int top = minHeap->peek();
            int e = arr2[i];
            if (e > top) minHeap->replace(e);
        }
    }
    minHeap->print();
    
    return 0;
}
