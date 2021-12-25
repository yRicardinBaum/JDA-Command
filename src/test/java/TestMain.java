import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;

import javax.security.auth.login.LoginException;

public class TestMain {
    public static JDA bot;
    public static RiCommand settings;

<<<<<<< HEAD
    public static void main(String[] args) throws LoginException {
        JDABuilder builder = JDABuilder.createDefault("ODk0MzI1MjU2MDU4MTk2MDQw.YVoXTA.FVZx8zfUaIGZui8zvFIf59pV8nU");
=======
    public static void main(String[] args) throws LoginException, InterruptedException {
        JDABuilder builder = JDABuilder.createDefault("TOKEN")
                .setBulkDeleteSplittingEnabled(false)
                .disableCache(CacheFlag.ACTIVITY)
                .setMemberCachePolicy(MemberCachePolicy.VOICE.or(MemberCachePolicy.OWNER))
                .setChunkingFilter(ChunkingFilter.NONE)
                .disableIntents(GatewayIntent.GUILD_PRESENCES, GatewayIntent.GUILD_MESSAGE_TYPING)
                .setCompression(Compression.NONE)
                .setAutoReconnect(true)
                .setLargeThreshold(50);
>>>>>>> 6688290a9ab1ec002b57a813011a16b35fd72dac
        bot = builder.build();
        RiCommand cmds = new RiCommand(bot, "ri!");
        cmds.addCommand(new CommandTest());
        cmds.startCommands();
        settings = cmds;
    }
}
