package com.example.wusandroidlabs;


import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.pressImeActionButton;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withParent;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import androidx.test.espresso.ViewInteraction;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
/**
 * @author:Wu Xinyu
 * * @version 1.0
 * Instrumented test class for MainActivity.
 *
 * This class contains test cases to verify the behavior of the MainActivity using Espresso.
 */
@LargeTest
@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Rule
    public ActivityScenarioRule<MainActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(MainActivity.class);
    /**
     * Test case for the main activity.
     * It performs the following steps:
     * 1. Enters the text "12345" into the edit text.
     * 2. Closes the soft keyboard.
     * 3. Clicks the button.
     * 4. Checks if the text view displays the message "You shall not pass!".
     */
    @Test
    public void mainActivityTest() {
        ViewInteraction appCompatEditText = onView(withId(R.id.editTextText));
        appCompatEditText.perform(replaceText("12345"),closeSoftKeyboard());


        ViewInteraction materialButton = onView( withId((R.id.button)));
        materialButton.perform(click());

        ViewInteraction textView = onView(withId(R.id.textView));
        textView.check(matches(withText("You shall not pass!")));
    }
    /**
     * Test case to verify if the text view displays "You shall not pass!" when a password is missing an uppercase letter.
     */
    @Test
    public void testFindMissingUpperCase(){
        ViewInteraction appCompatEditText = onView(withId(R.id.editTextText));
        appCompatEditText.perform(replaceText("password123#$*"));


        ViewInteraction materialButton = onView( withId((R.id.button)));
        materialButton.perform(click());

        ViewInteraction textView = onView(withId(R.id.textView));
        textView.check(matches(withText("You shall not pass!")));

    }
    /**
     * Test case to verify if the text view displays "You shall not pass!" when a password is missing a lowercase letter.
     */
    @Test
    public void testFindMissingLowerCase(){
        ViewInteraction appCompatEditText = onView(withId(R.id.editTextText));
        appCompatEditText.perform(replaceText("PASSWORD123#$*"));


        ViewInteraction materialButton = onView( withId((R.id.button)));
        materialButton.perform(click());

        ViewInteraction textView = onView(withId(R.id.textView));
        textView.check(matches(withText("You shall not pass!")));

    }
    /**
     * Test case to verify if the text view displays "You shall not pass!" when a password is missing a number.
     */
    @Test
    public void testFindMissingNumber(){
        ViewInteraction appCompatEditText = onView(withId(R.id.editTextText));
        appCompatEditText.perform(replaceText("password#$*"));


        ViewInteraction materialButton = onView( withId((R.id.button)));
        materialButton.perform(click());

        ViewInteraction textView = onView(withId(R.id.textView));
        textView.check(matches(withText("You shall not pass!")));

    }
    /**
     * Test case to verify if the text view displays "You shall not pass!" when a password is missing a special character.
     */
    @Test
    public void testFindMissingSpecialCharacter(){
        ViewInteraction appCompatEditText = onView(withId(R.id.editTextText));
        appCompatEditText.perform(replaceText("password123"));


        ViewInteraction materialButton = onView( withId((R.id.button)));
        materialButton.perform(click());

        ViewInteraction textView = onView(withId(R.id.textView));
        textView.check(matches(withText("You shall not pass!")));

    }
    /**
     * Test case to verify if the text view displays "Your password is complex enough" when a password is complex enough.
     */
    @Test
    public void testComplexity(){
        ViewInteraction appCompatEditText = onView(withId(R.id.editTextText));
        appCompatEditText.perform(replaceText("Password123#$*"));


        ViewInteraction materialButton = onView( withId((R.id.button)));
        materialButton.perform(click());

        ViewInteraction textView = onView(withId(R.id.textView));
        textView.check(matches(withText("Your password is complex enough")));

    }
    /**
     * Returns a matcher that matches a child view at the specified position within a parent view.
     *
     * @param parentMatcher The matcher for the parent view.
     * @param position The position of the child view within the parent view.
     * @return A matcher for the child view at the specified position.
     */
    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }
}
