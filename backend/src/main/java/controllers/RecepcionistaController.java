/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controllers;

import com.google.gson.Gson;
import entidades.recepcionista;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import services.RecepcionistaService;
import util.ApiException;

/**
 *
 * @author carlos
 */
@WebServlet(name = "RecepcionistaController", urlPatterns = {"/Recepcionista/*"})
public class RecepcionistaController extends HttpServlet {
    
    private RecepcionistaService recepcionistaService = new RecepcionistaService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        configurarCORS(resp);
        try {
            //Se accede a un recurso a traves de un path param
            if(req.getPathInfo() != null) {
                String pathParam = req.getPathInfo().replace("/", "");
                this.sendResponse(resp, recepcionistaService.getRecepcionistaById(Integer.parseInt(pathParam)));
            //Se accede a todos los recursos
            } else {
                this.sendResponse(resp, recepcionistaService.getRecepcionistas());
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
        configurarCORS(resp);
        try {
            Gson gson = new Gson();
            recepcionista recepcionista = gson.fromJson(req.getReader(), recepcionista.class);
            this.sendResponse(resp, recepcionistaService.insertRecepcionista(recepcionista));
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
        configurarCORS(resp);
        try {
            if(req.getPathInfo() != null) {
                String pathParam = req.getPathInfo().replace("/", "");
                recepcionistaService.deleteRecepcionista(Integer.parseInt(pathParam));
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
    private void configurarCORS(HttpServletResponse resp) {
        // Permite solicitudes desde cualquier origen (cambia '*' por el origen específico de tu aplicación React)
        resp.setHeader("Access-Control-Allow-Origin", "*");
        // Permitir solicitudes con los métodos GET, POST, etc. (ajusta según tus necesidades)
        resp.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
        // Permitir el envío de credenciales (cambiar a "true" si tu aplicación React envía cookies u otras credenciales)
        resp.setHeader("Access-Control-Allow-Credentials", "true");
        // Permitir los encabezados especificados (ajusta según tus necesidades)
        resp.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");
    }
    
   
}
