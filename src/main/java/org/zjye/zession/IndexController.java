package org.zjye.zession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
public class IndexController {

    @RequestMapping("/")
    public String index(Model model) {
        return "index";
    }

    @RequestMapping("link.jsp")
    public String link(Model model) {
        return "link";
    }

    @RequestMapping(value = "login", method = RequestMethod.POST)
    public void login(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        if (username != null && !"".equals(username) && username.equals(password)) {
            req.getSession().setAttribute("username", username);
            String url = resp.encodeRedirectURL(req.getContextPath() + "/");
            resp.sendRedirect(url);
        }
        else {
            String url = resp.encodeRedirectURL(req.getContextPath() + "/?error");
            resp.sendRedirect(url);
        }
    }

    @RequestMapping(value = "logout")
    public void logout(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        String url = resp.encodeRedirectURL(req.getContextPath() + "/");
        resp.sendRedirect(url);
    }
}
