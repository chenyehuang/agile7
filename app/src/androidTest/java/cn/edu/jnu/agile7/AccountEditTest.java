package cn.edu.jnu.agile7;


import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.longClick;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withParent;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import androidx.test.espresso.ViewInteraction;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;
import androidx.test.platform.app.InstrumentationRegistry;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;

import cn.edu.jnu.agile7.ui.Account.Account;
import cn.edu.jnu.agile7.ui.Account.AccountServer;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class AccountEditTest {

    @Rule
    public ActivityScenarioRule<MainActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(MainActivity.class);
    AccountServer dataSaverBackup;
    ArrayList<Account> accountItemsBackup;

    @Before
    public void setUp() throws Exception {
        Context targetContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        new AccountServer().ClearData(targetContext);
        dataSaverBackup=new AccountServer();
        accountItemsBackup=dataSaverBackup.Load(targetContext);
        if (accountItemsBackup.size() == 0){
            accountItemsBackup.add(new Account("111", 111));
            accountItemsBackup.add(new Account("222", 222));
            accountItemsBackup.add(new Account("3334", 4444.0));
        }
    }

    @After
    public void tearDown() throws Exception {
        Context targetContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        new AccountServer().ClearData(targetContext);
//        dataSaverBackup.Save(targetContext,accountItemsBackup);
    }

    @Test
    public void acounteditTest() {
        ViewInteraction bottomNavigationItemView = onView(
                allOf(withId(R.id.navigation_notifications), withContentDescription("Account"),
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
        appCompatEditText.perform(replaceText("111"), closeSoftKeyboard());

        ViewInteraction appCompatEditText2 = onView(
                allOf(withId(R.id.account_money),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("androidx.appcompat.widget.ContentFrameLayout")),
                                        0),
                                0),
                        isDisplayed()));
        appCompatEditText2.perform(replaceText("111"), closeSoftKeyboard());

        ViewInteraction materialButton2 = onView(
                allOf(withId(R.id.button_confirm_add_account), withText("保存"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("androidx.appcompat.widget.ContentFrameLayout")),
                                        0),
                                4),
                        isDisplayed()));
        materialButton2.perform(click());

        ViewInteraction materialButton3 = onView(
                allOf(withId(R.id.button_add_account), withText("添加账户"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.FrameLayout")),
                                        0),
                                3),
                        isDisplayed()));
        materialButton3.perform(click());

        ViewInteraction appCompatEditText3 = onView(
                allOf(withId(R.id.account_name),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("androidx.appcompat.widget.ContentFrameLayout")),
                                        0),
                                3),
                        isDisplayed()));
        appCompatEditText3.perform(replaceText("222"), closeSoftKeyboard());

        ViewInteraction appCompatEditText4 = onView(
                allOf(withId(R.id.account_money),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("androidx.appcompat.widget.ContentFrameLayout")),
                                        0),
                                0),
                        isDisplayed()));
        appCompatEditText4.perform(replaceText("222"), closeSoftKeyboard());

        ViewInteraction materialButton4 = onView(
                allOf(withId(R.id.button_confirm_add_account), withText("保存"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("androidx.appcompat.widget.ContentFrameLayout")),
                                        0),
                                4),
                        isDisplayed()));
        materialButton4.perform(click());

        ViewInteraction materialButton5 = onView(
                allOf(withId(R.id.button_add_account), withText("添加账户"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.FrameLayout")),
                                        0),
                                3),
                        isDisplayed()));
        materialButton5.perform(click());

        ViewInteraction appCompatEditText5 = onView(
                allOf(withId(R.id.account_name),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("androidx.appcompat.widget.ContentFrameLayout")),
                                        0),
                                3),
                        isDisplayed()));
        appCompatEditText5.perform(replaceText("333"), closeSoftKeyboard());

        ViewInteraction appCompatEditText6 = onView(
                allOf(withId(R.id.account_money),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("androidx.appcompat.widget.ContentFrameLayout")),
                                        0),
                                0),
                        isDisplayed()));
        appCompatEditText6.perform(replaceText("444"), closeSoftKeyboard());

        ViewInteraction materialButton6 = onView(
                allOf(withId(R.id.button_confirm_add_account), withText("保存"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("androidx.appcompat.widget.ContentFrameLayout")),
                                        0),
                                4),
                        isDisplayed()));
        materialButton6.perform(click());

        ViewInteraction recyclerView = onView(
                allOf(withId(R.id.recyclerView_fg_account),
                        childAtPosition(
                                withClassName(is("androidx.constraintlayout.widget.ConstraintLayout")),
                                2)));
        recyclerView.perform(actionOnItemAtPosition(2, longClick()));

        ViewInteraction materialTextView = onView(
                allOf(withClassName(is("com.google.android.material.textview.MaterialTextView")), withText("修改"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        0),
                                0),
                        isDisplayed()));
        materialTextView.perform(click());

        ViewInteraction appCompatEditText7 = onView(
                allOf(withId(R.id.account_name_edit), withText("333"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("androidx.appcompat.widget.ContentFrameLayout")),
                                        0),
                                3),
                        isDisplayed()));
//        appCompatEditText7.perform(replaceText("3334"));
        appCompatEditText7.perform(replaceText("3334"));

        ViewInteraction appCompatEditText8 = onView(
                allOf(withId(R.id.account_name_edit), withText("3334"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("androidx.appcompat.widget.ContentFrameLayout")),
                                        0),
                                3),
                        isDisplayed()));
        appCompatEditText8.perform(closeSoftKeyboard());

        ViewInteraction appCompatEditText9 = onView(
                allOf(withId(R.id.account_money_edit), withText("444.0"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("androidx.appcompat.widget.ContentFrameLayout")),
                                        0),
                                0),
                        isDisplayed()));
        appCompatEditText9.perform(replaceText("4444.0"));

        ViewInteraction appCompatEditText10 = onView(
                allOf(withId(R.id.account_money_edit), withText("4444.0"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("androidx.appcompat.widget.ContentFrameLayout")),
                                        0),
                                0),
                        isDisplayed()));
        appCompatEditText10.perform(closeSoftKeyboard());

        ViewInteraction materialButton7 = onView(
                allOf(withId(R.id.button_confirm_add_account_edit), withText("修改"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("androidx.appcompat.widget.ContentFrameLayout")),
                                        0),
                                4),
                        isDisplayed()));
        materialButton7.perform(click());

        ViewInteraction textView = onView(
                allOf(withId(R.id.textView_account_item_name), withText("3334"),
                        withParent(allOf(withId(R.id.constraintLayout_account_item),
                                withParent(withId(R.id.recyclerView_fg_account)))),
                        isDisplayed()));
        textView.check(matches(withText(accountItemsBackup.get(2).getName())));


        ViewInteraction textView2 = onView(
                allOf(withId(R.id.textView_account_item_money), withText("4444.0"),
                        withParent(allOf(withId(R.id.constraintLayout_account_item),
                                withParent(withId(R.id.recyclerView_fg_account)))),
                        isDisplayed()));
        textView2.check(matches(withText(String.valueOf(accountItemsBackup.get(2).getAmount()))));

//        ViewInteraction textView3 = onView(
//                allOf(withId(R.id.textView_account_item_money), withText("4444.0"),
//                        withParent(allOf(withId(R.id.constraintLayout_account_item),
//                                withParent(withId(R.id.recyclerView_fg_account)))),
//                        isDisplayed()));
//        textView3.check(matches(withText("4444.0")));
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
