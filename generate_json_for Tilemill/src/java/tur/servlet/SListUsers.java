/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tur.servlet;

import com.google.gson.Gson;
import com.google.gson.stream.JsonWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
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
public class SListUsers extends HttpServlet {

    ManagerUser managerUser = null;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {


        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        ServletContext ctx = this.getServletConfig().getServletContext();
        BDConnecion conexion = new BDConnecion(ctx);
        managerUser = new ManagerUser(conexion);
        // BRecurso bRecurso = new BRecurso();
        //List list_iusers = new LinkedList();
        ArrayList list_iusers = new ArrayList();
        List list = new LinkedList();



        try {
            /*list_iusers = managerUser.list_idUsers(); 
              
             for (int i = 0; i < list_iusers.size(); i++) {
                  
                  
             String id_user = list_iusers.get(i) + "";
                
                
             list = managerUser.list_User_by_Edicion(Integer.parseInt(id_user));
             String json = new Gson().toJson(list);
             response.setContentType("application/json");
             response.setCharacterEncoding("UTF-8");
             //write a json file
             File outputFile = new File(getServletContext().getRealPath("/") + "user" + id_user + ".json");
             FileWriter fout = new FileWriter(outputFile);
             fout.write("{\n"
             + "	\"type\": \"FeatureCollection\",\n"
             + "	\"features\":" + json + "}");   
             fout.close();

             System.out.println(outputFile.getAbsolutePath());
             }*/
            int[] list_users = {590362	,
475877	,
232126	,
362111	,
78871	,
527355	,
207745	,
93788	,
655680	,
153669	,
502142	,
48060	,
1679	,
574654	,
672878	,
416346	,
169004	,
492311	,
12434	,
55916	,
239998	,
280679	,
447903	,
510836	,
37392	,
315015	,
139555	,
104962	,
121241	,
38487	,
8703	,
3392	,
374193	,
113450	,
92286	,
91347	,
414318	,
22925	,
33757	,
32952	,
542403	,
655800	,
123633	,
119881	,
292665	,
168517	,
84054	,
933061	,
97431	,
14293	
};
                

            for (int i = 0; i < list_users.length; i++) {
                list_iusers.add(list_users[i]);
            }



            for (int i = 0; i < list_iusers.size(); i++) {



                String id_user = list_iusers.get(i) + "";


                list = managerUser.list_User_by_Edicion(Integer.parseInt(id_user));

                
                if(list.size() !=0){
                 System.out.println("-----------------------------------" + list.size());

                String json = new Gson().toJson(list);
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                //write a json file
                //wait(1000);
                File outputFile = new File(getServletContext().getRealPath("/") + "user" + id_user + "_ah.json");
                FileWriter fout = new FileWriter(outputFile);
                fout.write("{\n"
                        + "	\"type\": \"FeatureCollection\",\n"
                        + "	\"features\":" + json + "}");
                fout.close();

                System.out.println(outputFile.getAbsolutePath());
                
                }


            }



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

    public static void wait(int n) {
        long t0, t1;
        t0 = System.currentTimeMillis();
        do {
            t1 = System.currentTimeMillis();
        } while (t1 - t0 < 1000);
    }
}
