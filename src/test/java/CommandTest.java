import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;

public class CommandTest extends CommandExecutor {


    public CommandTest() {
        super("ping", "pingar");
    }

    @Override
    public void executeCommand(MessageChannel chat, Member author, String[] args, Message message) {
        long start = System.currentTimeMillis();
        chat.sendTyping().queue(v -> {
            long ping = System.currentTimeMillis() - start;
            chat.sendMessage("Ping: " + ping).queue();
        });
    }
}
