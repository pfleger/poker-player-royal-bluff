package org.leanpoker.player;

import com.google.gson.JsonParser;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/")
public class PlayerServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.getWriter().println("Java player is running");

        String strategy = req.getParameter("strategy");
        if (strategy != null) {
            Player.strategy = strategy;
        }
        resp.getWriter().println("Strategy :" + Player.strategy + " gesetzt");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println(">>>> Strategy: " + Player.strategy + " --- Request: " + req.getRequestURI() + " ---- " + req.getRequestURL().toString());

        if (req.getParameter("action").equals("bet_request")) {
            String gameState = req.getParameter("game_state");

            resp.getWriter().print(Player.betRequest(new JsonParser().parse(gameState)));
        }
        if (req.getParameter("action").equals("showdown")) {
            String gameState = req.getParameter("game_state");

            Player.showdown(new JsonParser().parse(gameState));
        }
        if (req.getParameter("action").equals("version")) {
            resp.getWriter().print(Player.VERSION);
        }
        if (req.getParameter("action").equals("check")) {
            resp.getWriter().print("");
        }

    }
}
