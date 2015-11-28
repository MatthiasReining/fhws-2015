/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.fhws.app.business.usermanagement.controller;

import static org.junit.Assert.*;
import org.junit.Test;

/**
 *
 * @author Matthias Reining
 */
public class PWManagerTest {

    @Test
    public void shouldGeneratePWHash() {

        PWManager pwm = new PWManager();
        String hash = pwm.createPWHash("geheim");
        String hash2 = pwm.createPWHash("geheim");

        System.out.println(hash);
        System.out.println(hash2);

        assertNotSame(hash, hash2);
        assertNotNull(hash);
    }

    @Test
    public void shouldMatchPW() {
        String testPw = "geheim";
        PWManager pwm = new PWManager();

        String hash = pwm.createPWHash(testPw);

        assertTrue(pwm.checkPW(testPw, hash));

    }

}
