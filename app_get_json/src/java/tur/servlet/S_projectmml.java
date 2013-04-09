/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tur.servlet;

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
public class S_projectmml extends HttpServlet {

    ManagerUser managerUser = null;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();


        ServletContext ctx = this.getServletConfig().getServletContext();
        BDConnecion conexion = new BDConnecion(ctx);
        managerUser = new ManagerUser(conexion);
        List list_iusers = new LinkedList();

        try {

            list_iusers = managerUser.list_idUsers();

            String header = "{\n"
                    + "  \"bounds\": [\n"
                    + "    -127.9687,\n"
                    + "    24.6071,\n"
                    + "    -62.2266,\n"
                    + "    49.6676\n"
                    + "  ],\n"
                    + "  \"center\": [\n"
                    + "    -98.6133,\n"
                    + "    39.504,\n"
                    + "    3\n"
                    + "  ],\n"
                    + "  \"format\": \"png\",\n"
                    + "  \"minzoom\": 0,\n"
                    + "  \"maxzoom\": 12,\n"
                    + "  \"srs\": \"+proj=merc +a=6378137 +b=6378137 +lat_ts=0.0 +lon_0=0.0 +x_0=0.0 +y_0=0.0 +k=1.0 +units=m +nadgrids=@null +wktext +no_defs +over\",\n"
                    + "  \"Stylesheet\": [\n"
                    + "    \"style.mss\"\n"
                    + "  ],\n"
                    + "  \"Layer\": [";

            String layer = "";

            String footer = " ],\n"
                    + "  \"scale\": 1,\n"
                    + "  \"metatile\": 2,\n"
                    + "  \"name\": \"mapers2\",\n"
                    + "  \"description\": \"\",\n"
                    + "  \"attribution\": \"\",\n"
                    + "  \"legend\": \"\"\n"
                    + "}";

            String query = "";

            for (int i = 0; i < list_iusers.size(); i++) {

                String id_user = list_iusers.get(i) + "";
                if (i == 99) {
                    layer += " {\n"
                            + "      \"geometry\": \"point\",\n"
                            + "      \"extent\": [\n"
                            + "        -127.9687,\n"
                            + "24.6071,\n"
                            + "-62.2266,\n"
                            + "49.6676"
                            + "      ],\n"
                            + "      \"id\": \"user" + id_user + "\",\n"
                            + "      \"class\": \"\",\n"
                            + "      \"Datasource\": {\n"
                            + "        \"file\": \"/home/ruben/Dropbox/webpages/report_top_us/json/user" + id_user + ".json\",\n"
                            + "        \"id\": \"user" + id_user + "\",\n"
                            + "        \"project\": \"mapers\",\n"
                            + "        \"srs\": \"+proj=longlat +ellps=WGS84 +datum=WGS84 +no_defs\"\n"
                            + "      },\n"
                            + "      \"srs-name\": \"WGS84\",\n"
                            + "      \"srs\": \"+proj=longlat +ellps=WGS84 +datum=WGS84 +no_defs\",\n"
                            + "      \"advanced\": {},\n"
                            + "      \"name\": \"user" + id_user + "\"\n"
                            + "    }\n";
                } else {
                    layer += " {\n"
                            + "      \"geometry\": \"point\",\n"
                            + "      \"extent\": [\n"
                            + "        -127.9687,\n"
                            + "24.6071,\n"
                            + "-62.2266,\n"
                            + "49.6676"
                            + "      ],\n"
                            + "      \"id\": \"user" + id_user + "\",\n"
                            + "      \"class\": \"\",\n"
                            + "      \"Datasource\": {\n"
                            + "        \"file\": \"/home/ruben/Dropbox/webpages/report_top_us/json/user" + id_user + ".json\",\n"
                            + "        \"id\": \"user" + id_user + "\",\n"
                            + "        \"project\": \"mapers\",\n"
                            + "        \"srs\": \"+proj=longlat +ellps=WGS84 +datum=WGS84 +no_defs\"\n"
                            + "      },\n"
                            + "      \"srs-name\": \"WGS84\",\n"
                            + "      \"srs\": \"+proj=longlat +ellps=WGS84 +datum=WGS84 +no_defs\",\n"
                            + "      \"advanced\": {},\n"
                            + "      \"name\": \"user" + id_user + "\"\n"
                            + "    },\n";

                }
            }
            query = header + layer + footer;

            File outputFile = new File(getServletContext().getRealPath("/") + "project.mml");
            //  File outputFile = new File(getServletContext().getRealPath("/") + "li.json");
            FileWriter fout = new FileWriter(outputFile);
            fout.write(query);
            fout.close();
            System.out.println(outputFile.getAbsolutePath());

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
