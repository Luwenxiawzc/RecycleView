package com.com.jnu.recycleview.data;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import java.util.ArrayList;

@RunWith(AndroidJUnit4.class)
public class DataSaverTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void save() {
        DataSaver dataSaver=new DataSaver();
    }

    @Test
    public void load() {
        DataSaver dataSaver=new DataSaver();

        ArrayList<Book> books=dataSaver.Load(InstrumentationRegistry.getInstrumentation().getTargetContext());
        Assert.assertEquals(0,books.size());
    }
}