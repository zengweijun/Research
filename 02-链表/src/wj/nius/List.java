package wj.nius;

public interface List<E> {
	
	// 接口中的变量和方法都是公开的，因此不必要添加public关键字，也不能添加private
	
	/**
	 * 没有找到元素
	 * 将改变量放在此处的目的是为了给外部使用
	 * 不放在AbList中是因为其实抽象类，对外部不可见
	 */
	static final int ELEMENT_NOT_FOUND = -1;
	
	/**
	 * 清除表中所有元素
	 */
	void clear();

	/**
	 * @return 返回表中元素个数
	 */
	int size();
 
	/**
	 * @return 返回表是否为空
	 */
	boolean isEmpty();

	/** 
	 * @param element 元素
	 * @return 返回表中是否包含某个元素
	 */
	boolean contains(E element);

	/**
	 * 向表的末尾添加元素
	 * @param element 要添加的元素 
	 */
	public void add(E element);

	/**
	 * 根据索引获取表中元素
	 * @param index 要获取元素的索引
	 * @return 返回获取到的元素
	 */
	E get(int index);

	/**
	 * 根据索引设置某个位置的元素
	 * @param index 索引位置
	 * @param element 元素
	 * @return 返回被覆盖的元素
	 */
	E set(int index, E element);

	/**
	 * 向表中某个位置插入元素
	 * @param index 位置索引
	 * @param element 要插入的元素
	 */
	// InsertObjectAtIndex
	void add(int index, E element);
	
	/**
	 * 删除某个索引位置的元素
	 * @param index 索引位置
	 * @return 被删除的元素
	 */
	E remove(int index);
	
	/**
	 * 删除元素
	 * @param element
	 * @return
	 */
	E remove(E element);

	/**
	 * 根据元素获取在表中的索引位置
	 * @param element 元素
	 * @return 索引位置
	 */
	int indexOf(E element);
}
