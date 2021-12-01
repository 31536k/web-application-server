package controller;

import db.DataBase;
import model.User;
import util.HttpRequest;
import util.HttpResponse;

import java.util.Collection;
import java.util.Map;

public class ListUserController extends AbstractController {
    @Override
    protected void doGet(HttpRequest request, HttpResponse response) {
        if (!isLogin(request)) {
            response.sendRedirect("/user/login.html");
            return;
        }

        Collection<User> users = DataBase.findAll();
        StringBuilder sb = new StringBuilder();
        sb.append("<table border='1'>");
        for (User user : users) {
            sb.append("<tr>");
            sb.append("<td>" + user.getUserId() + "</td>");
            sb.append("<td>" + user.getName() + "</td>");
            sb.append("<td>" + user.getEmail() + "</td>");
            sb.append("</tr>");
        }
        sb.append("</table>");
        response.forwardBody(sb.toString());
    }

    private boolean isLogin(HttpRequest request) {
        Map<String, String> cookies = request.getCookies();
        String value = cookies.get("logined");
        if (value == null) {
            return false;
        }
        return Boolean.parseBoolean(value);
    }
}
