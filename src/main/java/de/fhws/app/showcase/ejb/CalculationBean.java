/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.fhws.app.showcase.ejb;

import javax.ejb.Remote;
import javax.ejb.Stateless;

@Stateless
@Remote(CalculationBeanRemote.class)
public class CalculationBean implements CalculationBeanRemote {

    @Override
    public int calculation(int in) {
        return 42 * in;
    }

}
