package com.asynchrony.android.itemlist

import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

import java.util.Arrays

import org.junit.Assert.assertEquals

@RunWith(MockitoJUnitRunner::class)
class ItemListTest {
    private lateinit var testObject: ItemList

    @Before
    fun setUp() {
        testObject = ItemList()
    }

    @Test
    fun addItemSavesToList() {
        testObject.addItem("item1")
        testObject.addItem("item2")
        testObject.addItem("item3")

        assertEquals(listOf("item1", "item2", "item3"), testObject.items)
    }

    @Test
    fun clearEmptiesTheList() {
        testObject.addItem("item1")
        testObject.addItem("item2")
        testObject.addItem("item3")

        assertEquals(3, testObject.items.size)

        testObject.clear()

        assertEquals(0, testObject.items.size)
    }

    @Test
    fun sortAscending() {
        testObject.addItem("2")
        testObject.addItem("3")
        testObject.addItem("c")
        testObject.addItem("1")
        testObject.addItem("b")
        testObject.addItem("a")

        testObject.sortAscending()

        assertEquals(listOf("1", "2", "3", "a", "b", "c"), testObject.items)
    }

    @Test
    fun sortDescending() {
        testObject.addItem("2")
        testObject.addItem("3")
        testObject.addItem("c")
        testObject.addItem("1")
        testObject.addItem("b")
        testObject.addItem("a")

        testObject.sortDescending()

        assertEquals(listOf("c", "b", "a", "3", "2", "1"), testObject.items)
    }
}