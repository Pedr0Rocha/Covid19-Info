package com.pedrorocha.covid19info.ui.main.actions;

import android.view.View;

import androidx.test.espresso.UiController;
import androidx.test.espresso.ViewAction;

import org.hamcrest.Matcher;

/*
 * Click on an item inside the recycler test
 * https://stackoverflow.com/questions/28476507/using-espresso-to-click-view-inside-recyclerview-item
 * */
public class CountryAction {

    public static ViewAction clickChildViewWithId(final int id) {
        return new ViewAction() {
            @Override
            public Matcher<View> getConstraints() {
                return null;
            }

            @Override
            public String getDescription() {
                return "Click on a child view with specified id.";
            }

            @Override
            public void perform(UiController uiController, View view) {
                View v = view.findViewById(id);
                v.performClick();
            }
        };
    }
}