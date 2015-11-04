/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.fhws.app.showcase.frontcontroller.commands;

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
        //hohe Code Complexität
        long x = System.currentTimeMillis();
        int result = 0;
        if ((in < 5) || (x+4>System.currentTimeMillis())) {
            result = 42 / in;
            if (in >0) {
                System.out.println("blub");
            }
            if (in > 3) {
                System.out.println(">3");
            } else {
                System.out.println("<3");
            }
        }
        return result;
    }

}
