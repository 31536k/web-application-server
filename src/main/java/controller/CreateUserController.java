package controller;

import db.DataBase;
import model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.HttpRequest;
import util.HttpResponse;

import java.util.Map;

public class CreateUserController extends AbstractController {

    private static final Logger log = LoggerFactory.getLogger(CreateUserController.class);

    @Override
    protected void doPost(HttpRequest request, HttpResponse response) {
        Map<String, String> params = request.getParameters();
        User user = new User(params.get("userId"), params.get("password"), params.get("name"),
                params.get("email"));
        log.debug("user : {}", user);
        DataBase.addUser(user);
        response.sendRedirect("/index.html");
    }
}
