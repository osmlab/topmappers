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
public class SConfigJson extends HttpServlet {

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
            // System.out.println("length--" + list_iusers.size());
            String query = "";
            for (int i = 90; i < list_iusers.size(); i++) {

                String id_user = list_iusers.get(i) + "";

                String a_proyect = "{\n"
                        + "    \"source\": \"mapers\",\n"
                        + "    \"destination\": \"user" + id_user + "\",\n"
                        + "    \"format\": \"mbtiles\", \n"
                        + "    \"mml\": {\n"
                        + "        \"bounds\": [-127.9687,\n"
                        + "        24.6071, -62.2266,\n"
                        + "        49.6676],\n"
                        + "        \"center\": [-98.6133,\n"
                        + "        39.504,\n"
                        + "        3],\n"
                        + "        \"format\": \"png\",\n"
                        + "        \"interactivity\": {\n"
                        + "            \"layer\": \"user\",\n"
                        + "            \"template_teaser\": \"   <h3> <strong>User :</strong>\\n        {{{osm_user}}}\\n    </h3> \\n    <h3> <strong>Edition at :</strong>\\n        {{{closed_at}}}\\n    </h3> \\n    <h3>\\n        <strong>Num Changes  :</strong>\\n        {{{num_changes}}}\\n    </h3>\\n \",\n"
                        + "            \"fields\": [\n"
                        + "                \"user_id\",\n"
                        + "                \"osm_user\",\n"
                        + "                \"closed_at\",\n"
                        + "                \"num_changes\"]\n"
                        + "        },\n"
                        + "        \"minzoom\": 0,\n"
                        + "        \"maxzoom\": 12,\n"
                        + "        \"srs\": \"+proj=merc +a=6378137 +b=6378137 +lat_ts=0.0 +lon_0=0.0 +x_0=0.0 +y_0=0.0 +k=1.0 +units=m +nadgrids=@null +wktext +no_defs +over\",\n"
                        + "        \"Stylesheet\": [\n"
                        + "            \"style.mss\"],\n"
                        + "        \"Layer\": [{\n"
                        + "            \"geometry\": \"point\",\n"
                        + "            \"extent\": [-108.3869607,\n"
                        + "            25.325613900000004, -80.9186574,\n"
                        + "            36.1901764],\n"
                        + "            \"id\": \"user\",\n"
                        + "            \"class\": \"\",\n"
                        + "            \"Datasource\": {\n"
                        + "                \"file\": \"/home/ruben/Dropbox/webpages/report_top_us/json/user" + id_user + ".json\"\n"
                        + "            },\n"
                        + "            \"srs-name\": \"WGS84\",\n"
                        + "            \"srs\": \"+proj=longlat +ellps=WGS84 +datum=WGS84 +no_defs\",\n"
                        + "            \"advanced\": {},\n"
                        + "            \"name\": \"user\"\n"
                        + "        }],\n"
                        + "        \"scale\": 1,\n"
                        + "        \"metatile\": 2,\n"
                        + "        \"name\": \"user" + id_user + "\",\n"
                        + "        \"description\": \"\",\n"
                        + "        \"attribution\": \"\",\n"
                        + "        \"legend\": \"\"\n"
                        + "    },\n"
                        + "    \"cartoVars\": {},\n"
                        + "    \"MBmeta\": {}\n"
                        + "}";

                
                if(i==99){
                    query += a_proyect;
                }else{
                 query += a_proyect+",";
                }
               
                    
                

            }




            File outputFile = new File(getServletContext().getRealPath("/") + "config.json");
            FileWriter fout = new FileWriter(outputFile);
            fout.write("[" + query + "]");
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
