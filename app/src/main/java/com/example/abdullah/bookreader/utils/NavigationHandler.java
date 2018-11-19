package com.example.abdullah.bookreader.utils;

import android.view.View;

import com.example.abdullah.bookreader.fragments.FileExplorerFragment;
import com.example.abdullah.bookreader.fragments.LandingPageFragment;
import com.example.abdullah.bookreader.helpers.FragmentType;
import com.example.abdullah.bookreader.listeners.NavigationChild;
import com.example.abdullah.bookreader.listeners.NavigationListener;

import java.util.Stack;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class NavigationHandler implements NavigationListener {

    //Top of the stack is the fragment that is shown on the screen
    private Stack<Fragment> fragments = new Stack<>();
    private View containerView;
    private FragmentManager manager;
    public NavigationHandler(View containerView, FragmentManager fragmentManagera){
        this.containerView = containerView;
        this.manager = fragmentManagera;
    }

    /**
     * If the target can handle the action return will be true, so parent class should handle it.
     */
    public boolean goBack() {
        //This should not happen at all bu be safe
        if(fragments.isEmpty()) {
            return false;
        }

        //In this step we are checking whether the active shown fragment has anything to do with nav.
        if(((NavigationChild) fragments.peek()).goBack()){
            return true;
        }
        //Otherwise check the stack and move up the stack
        //Remove the actively shown fragment and change it with the previous one
        fragments.pop();

        changeFragment(fragments.peek());
        return true;
    }

    private void changeFragment(Fragment fragment){
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(containerView.getId(), fragment);
        transaction.disallowAddToBackStack();
        transaction.commit();
    }


    private void changeTo(Fragment fragment){
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(containerView.getId(), fragment);
        transaction.disallowAddToBackStack();
        transaction.commit();
    }

    private void goToFragment(Fragment f){
        fragments.push(f);
        changeFragment(f);
    }


    @Override
    public void goTo(FragmentType type) {
        Fragment f = null;
        switch (type){
            case LANDING_FRAGMENT:
                f = new LandingPageFragment();
                break;
            case EXPLORER_FRAGMENT:
                f = new FileExplorerFragment();
                break;
        }

        goToFragment(f);
    }

    public void popFragment() {
        fragments.pop();
        changeFragment(fragments.peek());
    }
}
