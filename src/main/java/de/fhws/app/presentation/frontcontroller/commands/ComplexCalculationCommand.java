/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.fhws.app.presentation.frontcontroller.commands;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Matthias Reining
 */
public class ComplexCalculationCommand implements Command {

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {

        int calcResult = complexCalculation(1);
        req.setAttribute("calcResult", calcResult);
        
        return "calc-result.jsp";
    }

    protected int complexCalculation(int in) {
        int result = 42 / in;
        return result;
    }

}
