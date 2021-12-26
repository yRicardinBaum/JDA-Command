import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;

import javax.security.auth.login.LoginException;

public class TestMain {
    public static JDA bot;
    public static RiCommand settings;


    public static void main(String[] args) throws LoginException {
        JDABuilder builder = JDABuilder.createDefault("ODk0MzI1MjU2MDU4MTk2MDQw.YVoXTA.An-PrfEXWKusngfIGpZg-aqF1pE");
        bot = builder.build();
        RiCommand cmds = new RiCommand(bot, "ri!", new CommandSettings());
        cmds.addCommand(new CommandTest());
        cmds.startCommands();
        System.out.println("BOT INICIADO COM SUCESSO!");
        settings = cmds;
    }
}
