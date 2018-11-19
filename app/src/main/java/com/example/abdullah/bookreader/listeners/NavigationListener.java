package com.example.abdullah.bookreader.listeners;


import com.example.abdullah.bookreader.helpers.FragmentType;

/**
 * This interface is implemented by navigationManager and manages destinations,
 * transitions etc
 */
public interface NavigationListener {
    void goTo(FragmentType type);
}
