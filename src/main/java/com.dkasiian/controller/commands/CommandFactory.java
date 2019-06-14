package com.dkasiian.controller.commands;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class CommandFactory {

    private final static Logger LOG = LogManager.getLogger(CommandFactory.class);

    public static Command getCommandFromRequest(HttpServletRequest request) {
        String[] splitUri = request.getRequestURI().split("/");
        String uriCommand = splitUri[splitUri.length - 1];

        LOG.debug("The command from the request uri is: " + uriCommand);

        return getCommand(uriCommand);
    }

    private static Command getCommand(String type){
        if (type.equalsIgnoreCase("BASE")){
            return new BaseCommand();
        } else if (type.equalsIgnoreCase("LOGIN")){
            return new LogInCommand();
        } else if (type.equalsIgnoreCase("REGISTRATION")){
            return new RegistrationCommand();
        } else if (type.equalsIgnoreCase("LOGOUT")){
            return new LogOutCommand();
        } else if (type.equalsIgnoreCase("SET-LANGUAGE")){
            return new SetLanguageCommand();
        } else if (type.equalsIgnoreCase("CONFERENCES")){
            return new ConferencesCommand();
        } else if (type.equalsIgnoreCase("ADD-CONFERENCE")){
            return new AddConferenceCommand();
        } else if (type.equalsIgnoreCase("DELETE-CONFERENCE")){
            return new DeleteConferenceCommand();
        } else if (type.equalsIgnoreCase("UPDATE-CONFERENCE")) {
            return new UpdateConferenceCommand();
        } else if (type.equalsIgnoreCase("REPORTS") ){
            return new ReportsCommand();
        } else if (type.equalsIgnoreCase("ADD-REPORT")){
            return new AddReportCommand();
        } else if (type.equalsIgnoreCase("UPDATE-REPORT")){
            return new UpdateReportCommand();
        } else if (type.equalsIgnoreCase("DELETE-REPORT")){
            return new DeleteReportCommand();
        } else if (type.equalsIgnoreCase("REGISTER")){
            return new RegisterForConferenceCommand();
        } else if (type.equalsIgnoreCase("UNREGISTER")){
            return new UnregisterFromConferenceCommand();
        } else if (type.equalsIgnoreCase("RATING")){
            return new RatingCommand();
        } else if (type.equalsIgnoreCase("SET-RATING")){
            return new SetRatingCommand();
        } else if (type.equalsIgnoreCase("PAST-CONFERENCES")){
            return new PastConferencesCommand();
        } else if (type.equalsIgnoreCase("FUTURE-CONFERENCES")){
            return new FutureConferencesCommand();
        } else if (type.equalsIgnoreCase("STATISTICS")){
            return new StatisticsCommand();
        }
        return new BaseCommand();
    }
}
