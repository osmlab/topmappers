/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tur.servlet;

import com.google.gson.Gson;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import tur.datasource.BDConnecion;
import tur.manager.ManagerUser;

/**
 *
 * @author ruben
 */
public class Slist_allUsers extends HttpServlet {

    ManagerUser managerUser = null;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {


        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        ServletContext ctx = this.getServletConfig().getServletContext();
        BDConnecion conexion = new BDConnecion(ctx);
        managerUser = new ManagerUser(conexion);
        // BRecurso bRecurso = new BRecurso();
        List list_iusers = new LinkedList();
        List list = new LinkedList();
        List list_all = new LinkedList();



        try {
            String points = "";
            list_iusers = managerUser.list_idUsers();
            for (int i = 0; i < list_iusers.size(); i++) {

                String id_user = list_iusers.get(i) + "";

                
                list = managerUser.list_User_by_Edicion(Integer.parseInt(id_user));
                System.out.println(list.size());
                for (int j = 0; j < list.size(); j++) {
                    System.out.println(list.get(j));
                    list_all.add(list.get(j));
                }

                

               /* if (i == 99) {
                    points += a_user;
                } else {
                    points += a_user + ",";
                }*/
            }

            String a_user = new Gson().toJson(list_all);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");

            //write a json file
            File outputFile = new File(getServletContext().getRealPath("/") + "users.json");
            FileWriter fout = new FileWriter(outputFile);
            fout.write("{\n"
                    + "	\"type\": \"FeatureCollection\",\n"
                    + "	\"features\":" + a_user + "}");
            /*fout.write("callback({\n"
             + "	\"type\": \"FeatureCollection\",\n"
             + "	\"features\":" + json + "})");*/
            fout.close();

            System.out.println(outputFile.getAbsolutePath());




        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
