package wj.nius;

public abstract class AbList<E> implements List<E> {
	protected int size; // 元素数量

	public int size() {
		return size;
	}
 
	public boolean isEmpty() {
		 return size == 0;
	}

	public boolean contains(E element) {
		return indexOf(element) != ELEMENT_NOT_FOUND;
	}

	public void add(E element) {
		add(size, element);
	}

	protected void validateIndexForAdd(int index) {
		if (index < 0 || index > size) {
			outOfBounds(index);
		}
	}
	
	protected void validateIndex(int index) {
		if (index < 0 || index >= size) {
			outOfBounds(index);
		}
	}
	
	protected void outOfBounds(int index) {
		throw new IndexOutOfBoundsException("size = " + size + ", but index = " + index);
	}
}
