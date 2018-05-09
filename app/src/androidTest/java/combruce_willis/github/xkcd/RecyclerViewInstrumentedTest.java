package combruce_willis.github.xkcd;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.Espresso;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.swipeLeft;
import static android.support.test.espresso.action.ViewActions.swipeRight;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isCompletelyDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.assertEquals;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class RecyclerViewInstrumentedTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<>(
            MainActivity.class);

    public static Matcher<View> withIndex(final Matcher<View> matcher, final int index) {
        return new TypeSafeMatcher<View>() {
            int currentIndex = 0;

            @Override
            public void describeTo(Description description) {
                description.appendText("with index: ");
                description.appendValue(index);
                matcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                return matcher.matches(view) && currentIndex++ == index;
            }
        };
    }

    /*
    Check if recycler visible
    */
    @Test
    public void testRecyclerVisible() {
        onView(withId(R.id.fragment_container)).check(matches(isCompletelyDisplayed()));
        onView(withId(R.id.recycler_view)).check(matches(isCompletelyDisplayed()));
    }

    /*
     * Check scroll of recyclerView
     */
    @Test
    public void testRecyclerScroll() {
        onView(withId(R.id.recycler_view)).perform(RecyclerViewActions.scrollToPosition(1000));

    }

    /*
     * Check clicking on recycler view item
     */
    @Test
    public void testRecyclerItemClicked() {
        onView(withId(R.id.recycler_view)).perform(RecyclerViewActions.actionOnItemAtPosition(200, click()));
        onView(withId(R.id.view_pager)).check(matches(isCompletelyDisplayed()));
        onView(withIndex(withId(R.id.full_screen_comic), 0)).check(matches(isCompletelyDisplayed()));
    }

    /*
     * Check swiping on viewgroup
     */
    @Test
    public void testViewGroupSwiping() {
        onView(withId(R.id.recycler_view)).perform(RecyclerViewActions.actionOnItemAtPosition(200, click()));
        onView(withId(R.id.view_pager)).check(matches(isCompletelyDisplayed()));
        final int N = 5;
        for (int i = 0; i < N; ++i) {
            onView(withId(R.id.view_pager)).perform(swipeLeft());
            onView(withId(R.id.view_pager)).perform(swipeRight());
            onView(withId(R.id.view_pager)).perform(swipeLeft());
            onView(withId(R.id.view_pager)).check(matches(isCompletelyDisplayed()));
        }
        for (int i = 0; i < 2 * N; ++i) {
            onView(withId(R.id.view_pager)).perform(swipeRight());
            onView(withId(R.id.view_pager)).check(matches(isCompletelyDisplayed()));
        }
    }

    /*
     * Returning back to recycler
     */
    @Test
    public void TestRecyclerItemClickedAndReturn() {
        onView(withId(R.id.recycler_view)).perform(RecyclerViewActions.actionOnItemAtPosition(400, click()));
        Espresso.pressBack();
        onView(withId(R.id.recycler_view)).check(matches(isCompletelyDisplayed()));
    }

    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();
        assertEquals("combruce_willis.github.xkcd", appContext.getPackageName());
    }
}
