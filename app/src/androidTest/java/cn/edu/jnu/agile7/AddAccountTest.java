package cn.edu.jnu.agile7;


import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withParent;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;

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
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import cn.edu.jnu.agile7.ui.notifications.Account;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class AddAccountTest {
    private Account account;


    @Rule
    public ActivityScenarioRule<MainActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(MainActivity.class);

    @Before
    public void setUp(){
        account = new Account("123", 456);
    }

    public void teardown(){
        account = null;
    }


    @Test
    public void addAccountTest() {
        ViewInteraction bottomNavigationItemView = onView(
                allOf(withId(R.id.navigation_notifications), withContentDescription("Notifications"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.nav_view),
                                        0),
                                3),
                        isDisplayed()));
        bottomNavigationItemView.perform(click());

        ViewInteraction materialButton = onView(
                allOf(withId(R.id.button_add_account), withText("添加账户"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.FrameLayout")),
                                        0),
                                3),
                        isDisplayed()));
        materialButton.perform(click());

        ViewInteraction appCompatEditText = onView(
                allOf(withId(R.id.account_name),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("androidx.appcompat.widget.ContentFrameLayout")),
                                        0),
                                3),
                        isDisplayed()));
        appCompatEditText.perform(replaceText("123"), closeSoftKeyboard());

        ViewInteraction appCompatEditText2 = onView(
                allOf(withId(R.id.account_money),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("androidx.appcompat.widget.ContentFrameLayout")),
                                        0),
                                0),
                        isDisplayed()));
        appCompatEditText2.perform(replaceText("456"), closeSoftKeyboard());

        ViewInteraction materialButton2 = onView(
                allOf(withId(R.id.button_confirm_add_account), withText("保存"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("androidx.appcompat.widget.ContentFrameLayout")),
                                        0),
                                4),
                        isDisplayed()));
        materialButton2.perform(click());

        ViewInteraction textView = onView(
                allOf(withId(R.id.textView_account_item_name), withText(account.getName()),
                        withParent(allOf(withId(R.id.constraintLayout_account_item),
                                withParent(withId(R.id.recyclerView_fg_account)))),
                        isDisplayed()));
        textView.check(matches(withText(account.getName())));

        ViewInteraction textView2 = onView(
                allOf(withId(R.id.textView_account_item_money), withText(String.valueOf(account.getAmount())),
                        withParent(allOf(withId(R.id.constraintLayout_account_item),
                                withParent(withId(R.id.recyclerView_fg_account)))),
                        isDisplayed()));
        textView2.check(matches(withText(String.valueOf(account.getAmount()))));
    }

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
