package com.mycompany.control;

import org.junit.Before;
import org.mockito.MockitoAnnotations;

public class BaseTestClass {
    @Before
    public void initMocks() {
        MockitoAnnotations.initMocks(this);
    }
}
