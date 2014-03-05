package com.cmuse13.countdownapp.countdownmodule.pojos;

import org.junit.Test;

import com.cmuse13.countdownapp.countdownmodule.pojos.SamplePojo;

import static org.fest.assertions.api.Assertions.assertThat;

public class SamplePojoTest {

    @Test
    public void testMultiply() {

        // MyClass is tested
        SamplePojo tester = new SamplePojo();
        assertThat(tester.someMethod()).isEqualTo(-1);
    }

}
