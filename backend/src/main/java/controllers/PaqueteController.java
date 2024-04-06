/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controllers;

import com.google.gson.Gson;
import entidades.paquete;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import services.PaqueteService;
import util.ApiException;

/**
 *
 * @author carlos
 */
@WebServlet(name = "PaqueteController", urlPatterns = {"/Paquete/*"})
public class PaqueteController extends HttpServlet {

    private PaqueteService paqueteService = new PaqueteService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            //Se accede a un recurso a traves de un path param
            if(req.getPathInfo() != null) {
                String pathParam = req.getPathInfo().replace("/", "");
                this.sendResponse(resp, paqueteService.getPaqueteById(Integer.parseInt(pathParam)));
            //Se accede a todos los recursos
            } else {
                this.sendResponse(resp, paqueteService.getAnimals());
            }
        } catch (ApiException e) {
            this.sendError(resp, e);
        } catch (Exception e) {
            this.sendError(resp, ApiException.builder()
                    .code(HttpServletResponse.SC_INTERNAL_SERVER_ERROR)
                    .message(e.getMessage())
                    .build());
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Gson gson = new Gson();
            paquete paquete = gson.fromJson(req.getReader(), paquete.class);
            this.sendResponse(resp, paqueteService.insertPaquete(paquete));
        } catch (ApiException e) {
            this.sendError(resp, e);
        } catch (Exception e) {
            this.sendError(resp, ApiException.builder()
                    .code(HttpServletResponse.SC_INTERNAL_SERVER_ERROR)
                    .message(e.getMessage())
                    .build());
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            if(req.getPathInfo() != null) {
                String pathParam = req.getPathInfo().replace("/", "");
                paqueteService.deletePaquete(Integer.parseInt(pathParam));
                this.sendResponse(resp, "");
            } else {
                throw ApiException.builder()
                        .code(HttpServletResponse.SC_BAD_REQUEST)
                        .message("Id is required")
                        .build();
            }
        } catch (ApiException e) {
            this.sendError(resp, e);
        } catch (Exception e) {
            this.sendError(resp, ApiException.builder()
                    .code(HttpServletResponse.SC_INTERNAL_SERVER_ERROR)
                    .message(e.getMessage())
                    .build());
        }

    }

    private void sendResponse(HttpServletResponse resp, Object object) throws IOException {
        resp.setContentType("application/json");
        resp.setStatus(HttpServletResponse.SC_OK);
        PrintWriter out = resp.getWriter();
        out.println(new Gson().toJson(object));
    }

    private void sendError(HttpServletResponse resp, ApiException e) throws IOException {
        resp.setContentType("application/json");
        resp.sendError(e.getCode(), e.getMessage());
    }
}
