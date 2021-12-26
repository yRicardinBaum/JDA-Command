import lombok.Getter;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;


public abstract class RiExecutor {
    @Getter
    private final String commandName;
    @Getter
    private final String[] aliases;



    public RiExecutor(String commandName, String... aliases) {
        this.commandName = commandName;
        this.aliases = aliases;
    }

    public abstract void executeCommand(final MessageChannel chat, final Member author, final String[] args, final Message message);

}

