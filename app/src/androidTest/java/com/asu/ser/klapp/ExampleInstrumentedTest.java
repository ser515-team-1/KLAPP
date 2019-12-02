package com.asu.ser.klapp;

import android.content.Context;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.asu.ser.klapp.activities.BodmasActivity;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();

        assertEquals("com.asu.ser.klapp", appContext.getPackageName());
    }



    @Test
    public void testBodmas_single(){
        String exprs_test = "2+3-5";
        String[] hold_expr_test = exprs_test.split("");
        BodmasActivity b_obj = new BodmasActivity();
        String[] hold_operators_test = b_obj.formOperator_arr(hold_expr_test);
        String o_p = "1,+,3,-";
        String[] tester =  o_p.split(",");
        assertEquals(tester, hold_operators_test);
    }
    @Test
    public void testBodmas_nested(){
        String exprs_test = "((2+3)-5)";
        String[] hold_expr_test = exprs_test.split("");
        BodmasActivity b_obj = new BodmasActivity();
        String[] hold_operators_test = b_obj.formOperator_arr(hold_expr_test);
        String o_p = "1,+,3,-";
        String[] tester =  o_p.split(",");
        assertEquals(tester, hold_operators_test);
    }
    @Test
    public void testBodmas_digits(){
        String exprs_test = "22+31-15";
        String[] hold_expr_test = exprs_test.split("");
        BodmasActivity b_obj = new BodmasActivity();
        String[] hold_operators_test = b_obj.formOperator_arr(hold_expr_test);
        String o_p = "1,+,3,-";
        String[] tester =  o_p.split(",");
        assertEquals(tester, hold_operators_test);
    }

}
