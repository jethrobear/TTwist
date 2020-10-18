package net.furrybrigade.ttwist;

import android.view.View;
import android.widget.TextView;

import androidx.test.espresso.UiController;
import androidx.test.espresso.ViewAction;
import androidx.test.espresso.action.ViewActions;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.hamcrest.Matcher;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import androidx.test.ext.junit.rules.ActivityScenarioRule;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
public class UITest {
    StringBuilder stringBuilder = new StringBuilder();

    @Rule
    public ActivityScenarioRule<MainActivity> mActivityRule =
            new ActivityScenarioRule<>(MainActivity.class);

    @Test
    public void testRandomizeButton() {
        // Get the initial state of the buttons and clue box
        stringBuilder.setLength(0);
        for(int buttonId : CluesLayout.buttonIds) {
            onView(withId(buttonId)).perform(new ViewAction() {
                @Override
                public Matcher<View> getConstraints() {
                    return isAssignableFrom(ToggleClueButton.class);
                }

                @Override
                public String getDescription() {
                    return "Get text from Toggle Clue Buttons";
                }

                @Override
                public void perform(UiController uiController, View view) {
                    stringBuilder.append(((ToggleClueButton) view).getText().toString());
                }
            });
        }
        String oldString = stringBuilder.toString();

        // Shuffle the words
        onView(withId(R.id.btnShuffle)).perform(click());

        // Get the final state after "Shuffling"
        stringBuilder.setLength(0);
        for(int buttonId : CluesLayout.buttonIds) {
            onView(withId(buttonId)).perform(new ViewAction() {
                @Override
                public Matcher<View> getConstraints() {
                    return isAssignableFrom(ToggleClueButton.class);
                }

                @Override
                public String getDescription() {
                    return "Get text from Toggle Clue Buttons";
                }

                @Override
                public void perform(UiController uiController, View view) {
                    stringBuilder.append(((ToggleClueButton) view).getText().toString());
                }
            });
        }
        String newString = stringBuilder.toString();

        assertNotEquals(oldString, newString);
    }

    @Test
    public void testRandomizeButtonOnClueBox(){
        onView(withId(R.id.btnClue1)).perform(click());
        onView(withId(R.id.btnClue2)).perform(click());
        onView(withId(R.id.btnClue3)).perform(click());
        onView(withId(R.id.btnClue4)).perform(click());

        stringBuilder.setLength(0);
        onView(withId(R.id.txtClueGuess)).perform(new ViewAction() {
            @Override
            public Matcher<View> getConstraints() {
                return isAssignableFrom(TextView.class);
            }

            @Override
            public String getDescription() {
                return "Get text from Toggle Clue Buttons";
            }

            @Override
            public void perform(UiController uiController, View view) {
                stringBuilder.append(((TextView) view).getText().toString());
            }
        });
        String oldText = stringBuilder.toString();

        onView(withId(R.id.btnShuffle)).perform(click());

        stringBuilder.setLength(0);
        onView(withId(R.id.txtClueGuess)).perform(new ViewAction() {
            @Override
            public Matcher<View> getConstraints() {
                return isAssignableFrom(TextView.class);
            }

            @Override
            public String getDescription() {
                return "Get text from Toggle Clue Buttons";
            }

            @Override
            public void perform(UiController uiController, View view) {
                stringBuilder.append(((TextView) view).getText().toString());
            }
        });
        String newText = stringBuilder.toString();

        assertEquals(oldText, newText);
    }

    @Test
    public void testBackspace(){
        onView(withId(R.id.btnClue1)).perform(click());
        onView(withId(R.id.btnClue2)).perform(click());
        onView(withId(R.id.btnClue3)).perform(click());
        onView(withId(R.id.btnClue4)).perform(click());

        stringBuilder.setLength(0);
        onView(withId(R.id.txtClueGuess)).perform(new ViewAction() {
            @Override
            public Matcher<View> getConstraints() {
                return isAssignableFrom(TextView.class);
            }

            @Override
            public String getDescription() {
                return "Get text from Toggle Clue Buttons";
            }

            @Override
            public void perform(UiController uiController, View view) {
                stringBuilder.append(((TextView) view).getText().toString());
            }
        });
        String oldText = stringBuilder.toString();

        onView(withId(R.id.btnBackspace)).perform(click());

        stringBuilder.setLength(0);
        onView(withId(R.id.txtClueGuess)).perform(new ViewAction() {
            @Override
            public Matcher<View> getConstraints() {
                return isAssignableFrom(TextView.class);
            }

            @Override
            public String getDescription() {
                return "Get text from Toggle Clue Buttons";
            }

            @Override
            public void perform(UiController uiController, View view) {
                stringBuilder.append(((TextView) view).getText().toString());
            }
        });
        String newText = stringBuilder.toString();

        assertEquals(oldText.substring(0, oldText.length() - 1), newText);
    }

    @Ignore("Checking how to implement test on states (NO MATCH v MATCH v HAS_GUESSED)")
    @Test
    public void testSubmit(){

    }
}
