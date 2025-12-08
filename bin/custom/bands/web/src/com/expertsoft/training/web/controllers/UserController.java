package com.expertsoft.training.web.controllers;

import de.hybris.platform.core.model.user.UserModel;
import de.hybris.platform.servicelayer.user.UserService;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

public class UserController implements Controller {
    private static final String USER = "user";
    private static final String UID = "uid";
    private UserService userService;
    @Override
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {
        final String uid = request.getParameter(UID);
        UserModel user = null;
        if (uid == null)
        {
            user = userService.getCurrentUser();
        }
        else
        {
            user = userService.getUserForUID(uid);
        }
        final Map<String, Object> model = new HashMap<>();
        model.put(USER, user);
        return new ModelAndView(USER, model);
    }

    @Required
    public void setUserService(final UserService userService) {
        this.userService = userService;
    }
}
