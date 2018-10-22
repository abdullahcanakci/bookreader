package com.example.abdullah.bookreader.listeners;

public interface MenuSelectionListener {

    void onShelfOpened(long shelfId);
    void onShelfEdited(long shelfId);

    void onBookEdited(long id);
    void onBookOpened(long bookId);
}
