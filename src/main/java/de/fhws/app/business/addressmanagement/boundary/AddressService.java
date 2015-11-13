/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.fhws.app.business.addressmanagement.boundary;

import de.fhws.app.business.addressmanagement.controller.ZipService;

/**
 *
 * @author Matthias Reining
 */
public class AddressService {
    
    
    public String getCityByZip(String zip) {
        
        System.out.println("current zip: " + zip);
        
        ZipService zipService = new ZipService();
        return zipService.getCityByZip(zip);
        
    }
}
