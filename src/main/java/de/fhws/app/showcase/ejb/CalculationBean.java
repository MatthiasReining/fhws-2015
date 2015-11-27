/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.fhws.app.showcase.ejb;

import de.fhws.app.business.usermanagement.entity.Statistics;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.jws.WebService;

@Stateless
@Remote(CalculationBeanRemote.class)
@WebService()
public class CalculationBean implements CalculationBeanRemote {

    @Override
    public int calculation(int in) {
        return 42 * in;
    }

    @Override
    public int test(Statistics s) {
        return 42;
    }

}
