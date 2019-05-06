package wj.nius;

/**
 * 动态数组
 * @author nius
 *
 * @param <E>
 */
@SuppressWarnings("unchecked")
public class ArrayList<E> extends AbList<E> {
	private E[] elements; // 元素容器
	private static final int DEFAULT_CAPACITY = 10;
	
	/// 构造函数
	public ArrayList(int capacity) {
		capacity = (capacity < DEFAULT_CAPACITY) ? DEFAULT_CAPACITY : capacity;
		elements = (E[]) new Object[capacity];	
		size = 0;
	}
	public ArrayList() {
		this(DEFAULT_CAPACITY);
	}
	

	public void clear() {
		for (int i = 0; i < size; i++) {
			elements[i] = null;
		}
		size = 0;
	}

	public E get(int index) {
		validateIndex(index);
		return elements[index];
	}

	public E set(int index, E element) {
		validateIndex(index);
		E old = elements[index];
		elements[index] = element;
		return old;
	}

	// InsertObjectAtIndex
	public void add(int index, E element) {
		validateIndexForAdd(index);
		
		ensureCapacity(size + 1); 
		
		// 后边元素往后挪
		for (int i = size; i > index; i--) {
			elements[i] = elements[i-1];
		}
		
		elements[index] = element;
		size++;
	}
	
	public E remove(int index) {
		validateIndex(index);
		E old = elements[index];
		
		for (int i = index+1; i < size; i++) {
			elements[i-1] = elements[i];
		}
		
		elements[--size] = null;
		return old;
	}

	public int indexOf(E element) {
		if (element == null) {
			for (int i = 0; i < size; i++) {
				if (elements[i] == null) return i;
			}
		} else {
			for (int i = 0; i < size; i++) {
				if (element.equals(elements[i])) return i;
			}
		}
		
		return ELEMENT_NOT_FOUND;
	}
	
	private void ensureCapacity(int needCapacity) {
		int elementsCapacity = elements.length;
		if (elementsCapacity >= needCapacity) return;
		
		// 新容量为旧容量1.5倍
		int newCapacity = elementsCapacity + (elementsCapacity>>1);
		E[] newElements = (E[]) new Object[newCapacity]; 
		for (int i = 0; i < size; i++) {
			newElements[i] = elements[i];
		}
		elements = newElements;
		
		System.out.println(elementsCapacity + "扩容为" + newCapacity);
	}
	
	@Override
	public String toString() {
		StringBuilder string = new StringBuilder();
		string.append("size=").append(size).append(", [\n");
		for (int i = 0; i < size; i++) {
			if (i != 0) string.append(",\n");
			string.append(elements[i]);			
		}
		string.append("\n]");
		return string.toString();
	}
	@Override
	public E remove(E element) {
		return remove(indexOf(element));
	}
}
