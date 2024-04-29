/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controllers;

import com.google.gson.Gson;
import entidades.SeguimientoPaquete;
import entidades.operador;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import services.OperadorService;
import services.SeguimientoPaqueteService;
import util.ApiException;

/**
 *
 * @author carlos
 */
@WebServlet(name = "SeguimientoPaqueteController", urlPatterns = {"/SeguimientoPaquete/*"})
public class SeguimientoPaqueteController extends HttpServlet {
    
    private SeguimientoPaqueteService seguimientoPaqueteService = new SeguimientoPaqueteService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            //Se accede a un recurso a traves de un path param
            if(req.getPathInfo() != null) {
                String pathParam = req.getPathInfo().replace("/", "");
                this.sendResponse(resp, seguimientoPaqueteService.getPaqueteById(Integer.parseInt(pathParam)));
            //Se accede a todos los recursos
            } else {
                this.sendResponse(resp, seguimientoPaqueteService.getPaquetes());
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
            SeguimientoPaquete seguimientoPaquete = gson.fromJson(req.getReader(), SeguimientoPaquete.class);
            this.sendResponse(resp, seguimientoPaqueteService.insertRegistro(seguimientoPaquete));
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
                seguimientoPaqueteService.delete(Integer.parseInt(pathParam));
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
