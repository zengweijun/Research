package com.nius.ArrayList;
import java.util.Iterator;
import java.util.Objects;
import java.util.function.Consumer;

import org.w3c.dom.ranges.RangeException;

public class MyArrayList<E> implements Iterable<E> {
	public static void main(String[] args) {
		MyArrayList<Integer> arrayList = new MyArrayList<Integer>();
		arrayList.add(1);
		arrayList.add(3);
		arrayList.add(2);
		arrayList.add(5);
		arrayList.add(6);
		arrayList.add(1);
		
		Iterator<Integer> iterator = arrayList.itrIterator();
		Iterator<Integer> iterator1 = arrayList.itrIterator();
		System.out.println(iterator);
		System.out.println(iterator1);
		while (iterator.hasNext()) {
			System.out.print(iterator.next() + "\t");
		}
		System.out.println();
		arrayList.forEach(new Consumer<Integer>() {
			public void accept(Integer t) {
				System.out.print(t + "\t");
			}
		});
		System.out.println();
		for (Integer item : arrayList) {
			System.out.println(item + " =>\t");
		}
	}
	
	@SuppressWarnings("unchecked")
	private E[] datas = (E[]) new Object[100];
	int size = 0;
	
	public void add(E e) {
		if (size + 1 > 100) {
			throw new RangeException((short) -1, "只能装100个元素");
		}
		datas[size] = e;
		size++;
	}
	
	public E get(int index) {
		if (size + 1 > 100) {
			throw new RangeException((short) -1, "只能装100个元素");
		}
		return datas[index];
	}
	
	public Iterator<E> itrIterator() {
		return new MyIterator<E>();
	}

	public Iterator<E> iterator() {
		return new MyIterator<E>();
	}
	
	public void forEach(Consumer<? super E> action) {
		Objects.requireNonNull(action);
        for (int i = 0; i < size; i++) {
        	action.accept(datas[i]);
		}
	}
	
	@SuppressWarnings("hiding")
	class MyIterator<E> implements Iterator<E> {
		int idx = 0;
		public boolean hasNext() {
			return idx != size;
		}
		@SuppressWarnings("unchecked")
		public E next() {
			return (E)get(idx++);
		}
	}
}
