import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.ChunkingFilter;
import net.dv8tion.jda.api.utils.Compression;
import net.dv8tion.jda.api.utils.MemberCachePolicy;
import net.dv8tion.jda.api.utils.cache.CacheFlag;

import javax.security.auth.login.LoginException;

public class TestMain {
    public static JDA bot;
    public static RiCommand settings;

    public static void main(String[] args) throws LoginException, InterruptedException {
        JDABuilder builder = JDABuilder.createDefault("ODk0MzI1MjU2MDU4MTk2MDQw.YVoXTA.KaZr6fZV9an7sddnrS5TGHPnZk4")
                .setBulkDeleteSplittingEnabled(false)
                .disableCache(CacheFlag.ACTIVITY)
                .setMemberCachePolicy(MemberCachePolicy.VOICE.or(MemberCachePolicy.OWNER))
                .setChunkingFilter(ChunkingFilter.NONE)
                .disableIntents(GatewayIntent.GUILD_PRESENCES, GatewayIntent.GUILD_MESSAGE_TYPING)
                .setCompression(Compression.NONE)
                .setAutoReconnect(true)
                .setLargeThreshold(50);
        bot = builder.build();
        bot.awaitReady();
        RiCommand cmds = new RiCommand(bot, "ri!").addCommand(new CommandTest());
        cmds.startCommands();
        settings = cmds;
    }
}
