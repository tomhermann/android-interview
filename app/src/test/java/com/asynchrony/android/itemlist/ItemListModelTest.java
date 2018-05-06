package com.asynchrony.android.itemlist;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class ItemListModelTest {
    private ItemListModel testObject;

    @Before
    public void setUp() {
        testObject = new ItemListModel();
    }

    @Test
    public void addItemSavesToList() {
        testObject.addItem("item1");
        testObject.addItem("item2");
        testObject.addItem("item3");

        ArrayList<String> expectedList = new ArrayList<>(Arrays.asList("item1", "item2", "item3"));

        assertEquals(expectedList, testObject.getItems());
    }

    @Test
    public void clearEmptiesTheList() {
        testObject.addItem("item1");
        testObject.addItem("item2");
        testObject.addItem("item3");

        assertEquals(3, testObject.getItems().size());

        testObject.clear();

        assertEquals(0, testObject.getItems().size());
    }

    @Test
    public void sortAscending() {
        testObject.addItem("2");
        testObject.addItem("3");
        testObject.addItem("c");
        testObject.addItem("1");
        testObject.addItem("b");
        testObject.addItem("a");

        testObject.sortAscending();

        ArrayList<String> expectedList = new ArrayList<>(
                Arrays.asList("1", "2", "3", "a", "b", "c")
        );

        assertEquals(expectedList, testObject.getItems());
    }

    @Test
    public void sortDescending() {
        testObject.addItem("2");
        testObject.addItem("3");
        testObject.addItem("c");
        testObject.addItem("1");
        testObject.addItem("b");
        testObject.addItem("a");

        testObject.sortDescending();

        ArrayList<String> expectedList = new ArrayList<>(
                Arrays.asList("c", "b", "a", "3", "2", "1")
        );

        assertEquals(expectedList, testObject.getItems());
    }
}