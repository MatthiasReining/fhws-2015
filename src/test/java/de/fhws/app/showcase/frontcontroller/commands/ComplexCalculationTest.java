/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.fhws.app.showcase.frontcontroller.commands;

import de.fhws.app.showcase.frontcontroller.commands.ComplexCalculationCommand;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 *
 * @author Matthias Reining
 */
public class ComplexCalculationTest {

    ComplexCalculationCommand ccc;

    @Before
    public void init() {
        ccc = new ComplexCalculationCommand();
    }

    @Test
    public void shouldReturn42() {
        int actual = ccc.complexCalculation(1);
        assertEquals(42, actual);
    }

    @Test
    public void shouldReturn21() {
        int actual = ccc.complexCalculation(2);
        assertEquals(21, actual);
    }

    @Test(expected = ArithmeticException.class)
    public void shouldThrowException() {
        ccc.complexCalculation(0);
    }

    @Test
    public void shouldThrowExceptionDivisionByZero() {
        try {
            ccc.complexCalculation(0);
            fail("Should throw ArithmeticException: / by zero ");
        } catch (ArithmeticException e) {
            assertEquals("/ by zero", e.getMessage());
        }
    }
}
