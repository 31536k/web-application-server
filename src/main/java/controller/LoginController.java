package controller;

import db.DataBase;
import model.User;
import util.HttpRequest;
import util.HttpResponse;

public class LoginController extends AbstractController {
    @Override
    protected void doPost(HttpRequest request, HttpResponse response) {
        String userId = request.getParameter("userId");
        String password = request.getParameter("password");
        User user = DataBase.findUserById(userId);
        if (user != null) {
            if (user.login(password)) {
                response.addHeader("Set-Cookie", "logined=true");
                response.sendRedirect("/index.html");
            } else {
                response.addHeader("Set-Cookie", "logined=false");
                response.sendRedirect("/user/login_failed.html");
            }
        } else {
            response.addHeader("Set-Cookie", "logined=false");
            response.sendRedirect("/user/login_failed.html");
        }
    }
}
