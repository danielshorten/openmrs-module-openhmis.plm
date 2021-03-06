package org.openmrs.module.openhmis.plm.impl;

import org.junit.Test;
import org.openmrs.module.openhmis.plm.PersistentList;
import org.openmrs.module.openhmis.plm.PersistentListItem;
import org.openmrs.module.openhmis.plm.PersistentListProvider;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class PersistentStackTest extends PersistentListTestBase {

	@Override
	protected PersistentList createList(PersistentListProvider provider) {
		PersistentStack stack = new PersistentStack(1, "test", provider);
		stack.initialize();

		return stack;
	}

	/**
	 * @verifies Return the items in last in first out order
	 * @see PersistentStack#getItems()
	 */
	@Test
	public void getItems_shouldReturnTheItemsInLastInFirstOutOrder() throws Exception {
		PersistentListItem item1 = new PersistentListItem("1", null);
		PersistentListItem item2 = new PersistentListItem("2", null);
		PersistentListItem item3 = new PersistentListItem("3", null);

		list.add(item1, item2, item3);

		PersistentListItem[] items = list.getItems();
		assertNotNull(items);
		assertEquals(3, items.length);

		assertEquals(item3, items[0]);
		assertEquals(item2, items[1]);
		assertEquals(item1, items[2]);
	}

	/**
	 * @verifies Return items in last in first out order
	 * @see PersistentStack#getNextAndRemove()
	 */
	@Test
	public void getNextAndRemove_shouldReturnItemsInLastInFirstOutOrder() throws Exception {
		PersistentListItem item1 = new PersistentListItem("1", null);
		PersistentListItem item2 = new PersistentListItem("2", null);
		PersistentListItem item3 = new PersistentListItem("3", null);

		list.add(item1, item2, item3);

		PersistentListItem[] items = list.getItems();
		assertNotNull(items);
		assertEquals(3, items.length);

		PersistentListItem item = list.getNextAndRemove();
		assertNotNull(item);
		assertEquals(item3, item);

		item = list.getNextAndRemove();
		assertNotNull(item);
		assertEquals(item2, item);

		item = list.getNextAndRemove();
		assertNotNull(item);
		assertEquals(item1, item);
	}

	/**
	 * @verifies Return items in last in first out order
	 * @see PersistentStack#getNext()
	 */
	@Test
	public void getNext_shouldReturnItemsInLastInFirstOutOrder() throws Exception {
		PersistentListItem item1 = new PersistentListItem("1", null);
		PersistentListItem item2 = new PersistentListItem("2", null);

		list.add(item1, item2);

		PersistentListItem[] items = list.getItems();
		assertNotNull(items);
		assertEquals(2, items.length);

		PersistentListItem item = list.getNext();
		assertNotNull(item);
		assertEquals(item2, item);

		list.remove(item2);
		item = list.getNext();
		assertNotNull(item);
		assertEquals(item1, item);
	}
}
