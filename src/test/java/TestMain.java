import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;

import javax.security.auth.login.LoginException;

public class TestMain {
    public static JDA bot;
    public static RiCommand settings;

    public static void main(String[] args) throws LoginException {
        JDABuilder builder = JDABuilder.createDefault("ODk0MzI1MjU2MDU4MTk2MDQw.YVoXTA.FVZx8zfUaIGZui8zvFIf59pV8nU");
        bot = builder.build();
        RiCommand cmds = new RiCommand(bot, "ri!");
        cmds.addCommand(new CommandTest());
        cmds.startCommands();
        settings = cmds;
    }
}
