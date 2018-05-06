package com.asynchrony.android.itemlist;

import android.view.View;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class ItemListPresenterTest {
    private ItemListPresenter testObject;

    @Mock
    private ItemListView view;

    @Mock
    private ItemListModel model;

    @Mock
    private List<String> itemList;

    @Captor
    private ArgumentCaptor<View.OnClickListener> clickListenerCaptor;

    @Before
    public void setUp() {
        testObject = new ItemListPresenter(model);
        testObject.attach(view);
        verify(view).setAddButtonClickListener(clickListenerCaptor.capture());
    }

    @Test
    public void clickingAddWithEmptyListDoesNothing() {
        when(view.getNewItemText()).thenReturn("");
        clickListenerCaptor.getValue().onClick(mock(View.class));

        verifyZeroInteractions(model);
    }

    @Test
    public void clickingAddNonEmptyItemSavesAndDisplaysItemAndClearsInput() {
        String item = "new item";
        when(view.getNewItemText()).thenReturn(item);

        when(model.getItems()).thenReturn(itemList);
        clickListenerCaptor.getValue().onClick(mock(View.class));

        verify(model).addItem(eq(item));
        verify(view).clearItemInput();
        verify(view).updateList(itemList);
    }

    @Test
    public void sortAscendingMenuItemCallsModelAndUpdatesView() {
        when(model.getItems()).thenReturn(itemList);

        assertTrue(testObject.menuItemSelected(R.id.action_sort_ascending));

        verify(model).sortAscending();
        verify(model).getItems();
        verifyNoMoreInteractions(model);
        verify(view).updateList(itemList);
    }

    @Test
    public void sortDescendingMenuItemCallsModelAndUpdatesView() {
        when(model.getItems()).thenReturn(itemList);

        assertTrue(testObject.menuItemSelected(R.id.action_sort_descending));

        verify(model).sortDescending();
        verify(model).getItems();
        verifyNoMoreInteractions(model);
        verify(view).updateList(itemList);
    }

    @Test
    public void clearListMenuItemCallsModelAndUpdatesView() {
        when(model.getItems()).thenReturn(itemList);

        assertTrue(testObject.menuItemSelected(R.id.action_clear));

        verify(model).clear();
        verify(model).getItems();
        verifyNoMoreInteractions(model);
        verify(view).updateList(itemList);
    }

    @Test
    public void unknownMenuSelectionDoesNothing() {
        assertFalse(testObject.menuItemSelected(-5));

        verifyZeroInteractions(model);
        verifyNoMoreInteractions(view);
    }
}