package com.example.abdullah.bookreader.listeners;

public interface MenuSelectionListener {

    void onShelfOpened(long shelfId);
    void onShelfEdited(long shelfId);
    void onBookOpened(long bookId);
}
