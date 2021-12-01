package controller;

import util.HttpMethod;
import util.HttpRequest;
import util.HttpResponse;

public abstract class AbstractController implements Controller {
    protected void doGet(HttpRequest request, HttpResponse response) {};
    protected void doPost(HttpRequest request, HttpResponse response) {};

    @Override
    public void service(HttpRequest request, HttpResponse response) {
        if (request.getMethod().isGet()) {
            doGet(request, response);
        } else if (request.getMethod().isPost()) {
            doPost(request, response);
        }
    }
}
