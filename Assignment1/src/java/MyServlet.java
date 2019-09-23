/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.text.StringEscapeUtils;


/**
 *
 * @author Jean-Loup
 */
public class MyServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        Enumeration e = request.getHeaderNames();
        Enumeration f = request.getParameterNames();
        List parameterNames = new ArrayList();
        
        String formatType ="html";
        
        //check to see if a format variable is given
        while (f.hasMoreElements())
            {
                parameterNames.add((String)f.nextElement());
                if(parameterNames.contains("format")) formatType = request.getParameter("format");
            }
        //print output
        if(formatType.equals("text"))
        {
            response.setContentType("text; charset=UTF-8"); 
             try (PrintWriter out = response.getWriter()) {
            
            out.println("Request method: "+request.getMethod());
            out.println("Headers:");
            while (e.hasMoreElements())
            {
                String name = (String)e.nextElement();
                out.println(name+": "+request.getHeader(name));
            }
            out.println("Query String:");
            for(int j = 0; j < parameterNames.size();j++)
            {
                out.println(parameterNames.get(j)+": "+request.getParameter((String)parameterNames.get(j)));
            }
            out.close();
        }
        }
        else if (formatType.equals("xml"))
        {
            
            response.setContentType("text/xml; charset=UTF-8"); 

        try (PrintWriter out = response.getWriter()) {
            out.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
            out.println("<response>");
            out.println("<request-method>"+request.getMethod()+"</request-method>");
            out.println("<request-headers>");
            while (e.hasMoreElements())
            {
                String name = (String)e.nextElement();
                out.println("<header name=\""+name+"\">"+request.getHeader(name)+"</header>");
            }
            out.println("</request-headers>");
            out.println("<query-string>");
            for(int j = 0; j < parameterNames.size();j++)
            {
                out.println("<"+parameterNames.get(j)+">"+request.getParameter((String)parameterNames.get(j))+"</"+parameterNames.get(j)+">");
            }
            out.println("</query-string>");
            out.println("</response>");
            out.close();
            
        }
        }
        else if(formatType.equals("html"))
        {
            response.setContentType("text/html; charset=UTF-8"); 
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Page Title</title>"); 
            out.println("<style>table, th, td {" +
                        "  border: 1px solid black;" +
                        "  border-collapse: collapse;" +
                        "}th, td {" +
                        "  padding: 5px;" +
                        "  text-align: left;" +
                        "}</style>"); 
            out.println("</head>");
            out.println("<body>");
            out.println("<table><tr><th>Request method</th><td> "+request.getMethod()+"</td></tr></table>");
            out.println("<h1>Headers:</h1>");
            out.println("<table>");
            while (e.hasMoreElements())
            {
                String name = (String)e.nextElement();
                out.println("<tr>");
                out.println("<th>"+name+"</th>");
                out.println("<td>"+request.getHeader(name)+"</td>");
                out.println("</tr>");
            }
            out.println("</table>");
            out.println("<h1>Query String:</h1>");
            out.println("<table>");
            for(int j = 0; j < parameterNames.size();j++)
            {
                out.println("<tr>");
                out.println("<th>"+parameterNames.get(j)+"</th><td>"+request.getParameter((String)parameterNames.get(j))+"</td>");
                out.println("</tr>");
            }
            out.println("</table>");
            out.println("</body>");
            out.println("</html>");
            out.close();
        }
        }
        //invalid format
        else 
        {
            try (PrintWriter out = response.getWriter()) {
            
            out.println("Invalid format");
            out.close();
            
        }
        }
       
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
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
     * Handles the HTTP <code>POST</code> method.
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
