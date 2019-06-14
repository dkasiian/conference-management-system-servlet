package com.dkasiian.controller.utils;

import com.dkasiian.model.entities.Role;
import org.mindrot.jbcrypt.BCrypt;

import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SecurityConfigUtil {

    private static final String IGNORE_PATTERN = "(.*\\.css$|.*\\.js$|.*\\.jpg$|.*\\.png$|.*\\.gif$|.*\\.jpeg$|.*\\.ico$)$";

    private static final Map<String, List<String>> roleCommands = new HashMap<>();
    private static final List<String> permitAllCommands = new ArrayList<>();

    static { init(); }

    private static void init() {

        permitAllCommands.add("/");
        permitAllCommands.add("/index.jsp");
        permitAllCommands.add("/conferences");

        List<String> guestCommands = getGuestCommands();

        List<String> userCommands = getUserCommands();

        List<String> speakerCommands = Stream.of(getUserCommands(), getSpeakerCommands())
                .flatMap(Collection::stream).collect(Collectors.toList());

        List<String> adminCommands =  Stream.of(getUserCommands(), getSpeakerCommands(), getAdminCommands())
                .flatMap(Collection::stream).collect(Collectors.toList());

        roleCommands.put("guest", guestCommands);
        roleCommands.put("user", userCommands);
        roleCommands.put("speaker", speakerCommands);
        roleCommands.put("admin", adminCommands);
    }

    private static List<String> getGuestCommands() {
        List<String> guestCommands = new ArrayList<>();
        guestCommands.add("base");
        guestCommands.add("login");
        guestCommands.add("registration");
        guestCommands.add("conferences");
        guestCommands.add("past-conferences");
        guestCommands.add("future-conferences");
        guestCommands.add("set-language");
        guestCommands.add("reports");
        return guestCommands;
    }

    private static List<String> getUserCommands() {
        List<String> userCommands = new ArrayList<>();
        userCommands.add("base");
        userCommands.add("logout");
        userCommands.add("conferences");
        userCommands.add("past-conferences");
        userCommands.add("future-conferences");
        userCommands.add("set-language");
        userCommands.add("reports");
//        userCommands.add("register");
//        userCommands.add("unregister");
        userCommands.add("register-unregister");
        userCommands.add("rating");
        userCommands.add("set-rating");
        return userCommands;
    }

    private static List<String> getSpeakerCommands() {
        List<String> speakerCommands = new ArrayList<>();
        speakerCommands.add("add-report");
        speakerCommands.add("update-report");
        speakerCommands.add("delete-report");
        return speakerCommands;
    }

    private static List<String> getAdminCommands() {
        List<String> adminCommands = new ArrayList<>();
        adminCommands.add("add-conference");
        adminCommands.add("update-conference");
        adminCommands.add("delete-conference");
        adminCommands.add("statistics");
        return adminCommands;
    }

    public boolean isAllowed(String urlPattern, String role) {
        if (!isPermitAll(urlPattern))
            return roleCommands.get(role).contains(urlPattern.replaceFirst(".*/", ""));
        return true;
    }

    private boolean isPermitAll(String command){
        return permitAllCommands.contains(command);
    }

    public String hashPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    public boolean isIgnore(String urlPattern){
        return urlPattern.matches(IGNORE_PATTERN);
    }

    public static boolean isGuest(HttpServletRequest request) {
        return request.getSession().getAttribute("role").equals(Role.GUEST.name().toLowerCase());
    }
}
