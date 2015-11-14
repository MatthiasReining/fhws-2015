/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.fhws.app.showcase.generics;

import de.fhws.app.business.usermanagement.entity.AppUser;
import de.fhws.app.business.usermanagement.entity.Statistics;

/**
 *
 * @author Matthias Reining
 */
public class GenericTest<T> {
    
    public T getObj() {
        T x = (T) new AppUser();
        return x;
    }
    
    
    public static void main(String... args) {
        //throws Exception
        Statistics s = new GenericTest<Statistics>().getObj();
        
    }
}
