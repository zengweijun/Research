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

int main(int argc, const char * argv[]) {
    
    cout << "start-----" << endl;
    
    BinaryHeap<int> *bheap = new BinaryHeap<int>();
    
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
    cout << "end-----" << endl;
    
    bheap->print();
    
    return 0;
}
