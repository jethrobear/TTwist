package net.furrybrigade.ttwist;

import android.content.Context;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void testTextReader() {
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        try {
            Dictionary iface = new Dictionary(appContext);
            assertFalse(iface.readTextFile().isEmpty());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void testGenerateLetterList() {
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        try {
            Dictionary iface = new Dictionary(appContext);
            List max = iface.readTextFile();
            List result = iface.generateRandomLettersFromMax(max);
            System.out.print(result);
            assertFalse(result.isEmpty());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void testGetClues() {
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        try {
            Dictionary iface = new Dictionary(appContext);
            List max = iface.readTextFile();
            List clues = iface.generateRandomLettersFromMax(max);
            List result = iface.getWordsFromClues(iface.readTextFile(), clues);
            assertFalse(result.isEmpty());
        } catch (Exception e) {
            fail();
        }
    }
}