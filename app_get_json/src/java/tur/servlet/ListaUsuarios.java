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
import tur.bean.User;
import tur.datasource.BDConnecion;
import tur.manager.ManagerUser;

/**
 *
 * @author ruben
 */
public class ListaUsuarios extends HttpServlet {

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



        try {

            list_iusers = managerUser.list_idUsers();
            LinkedList linkedList= new LinkedList();
            // System.out.println("length--" + list_iusers.size());
            for (int i = 0; i < list_iusers.size(); i++) {

                String id_user = list_iusers.get(i) + "";
                User user = new User();
                
                user.setUser_id(Integer.parseInt(id_user));              

                String osm_user = managerUser.find_osm_user(Integer.parseInt(id_user));
                user.setOsm_user(osm_user);
                
                linkedList.add(user);


            }

            //user.setEditions(managerUser.list_edition_month(Integer.parseInt(id_user)));
            //user.setFeatures(managerUser.list_points_edition(Integer.parseInt(id_user)));
            String json = new Gson().toJson(linkedList);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            //write a json file
            File outputFile = new File(getServletContext().getRealPath("/") + "listtop50user.json");
            FileWriter fout = new FileWriter(outputFile);

            fout.write("callback(" + json + ")");
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
