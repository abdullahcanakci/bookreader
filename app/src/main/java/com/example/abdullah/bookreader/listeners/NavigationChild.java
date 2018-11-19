package com.example.abdullah.bookreader.listeners;

/**
 * Implemented by fragments that are handled by the {@link com.example.abdullah.bookreader.MainActivity}.
 * This interface is called whenever a navigation action is created.
 * if child fragments handle the action they should always return true,
 * otherwise {@link com.example.abdullah.bookreader.MainActivity} will handle the action
 *
 */
public interface NavigationChild {
    boolean goBack();
}
