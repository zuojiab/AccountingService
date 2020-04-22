package com.togo.accounting;

import com.togo.accounting.manager.AddManager;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * create by crashLab on 2020/04/22.
 **/
public class AddManagerTest {
    private AddManager addManager = new AddManager();

    @Test
    void testAddMethod() {
        int number = 100;

        int result = addManager.add(number);

        assertEquals(101,result);
    }
}
