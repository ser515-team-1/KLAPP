package com.asu.ser.klapp;

import android.content.Context;

import com.asu.ser.klapp.utilities.AppUtility;

import org.junit.Test;
import org.junit.runner.RunWith;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
public class CountingActivityTest {

    @Test
    public void testRamdomNum(){

        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        AppUtility.init(appContext);
        assertNotNull(AppUtility.getRandomImagePair());
        assertNotNull(AppUtility.getRandomImagePair());
        assertNotNull(AppUtility.getRandomImagePair());
        assertNotNull(AppUtility.getRandomImagePair());
        assertNotNull(AppUtility.getRandomImagePair());
        assertNotNull(AppUtility.getRandomImagePair());
    }
}
