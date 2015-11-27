/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.fhws.app.showcase.ejb;

import de.fhws.app.business.usermanagement.entity.Statistics;

public interface CalculationBeanRemote {

    public int calculation(int in);

    public int test(Statistics s);

}
