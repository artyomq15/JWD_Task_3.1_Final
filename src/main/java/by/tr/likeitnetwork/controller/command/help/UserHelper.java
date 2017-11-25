package by.tr.likeitnetwork.controller.command.help;

import by.tr.likeitnetwork.controller.constant.AttributeKey;
import by.tr.likeitnetwork.entity.User;
import by.tr.likeitnetwork.service.ServiceFactory;
import by.tr.likeitnetwork.service.exception.UserServiceException;

import javax.servlet.http.HttpSession;

public class UserHelper {
    public static User getProfileIfAuthorized(HttpSession session) throws UserServiceException{
        if (session==null){
            return null;
        }
        Object id = session.getAttribute(AttributeKey.ID);
        if (id == null){
            return null;
        }
        return ServiceFactory.getInstance().getUserService().findUserById(String.valueOf(id));
    }
}
