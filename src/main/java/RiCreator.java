import lombok.Getter;
import lombok.NonNull;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import org.jetbrains.annotations.NotNull;

public class RiCreator {

    @Getter
    private final String[] args;
    @Getter
    private final RiCommand settings;
    @Getter
    private final String name;
    @Getter
    private final RiExecutor command;
    @Getter
    private final MessageChannel channel;
    @Getter
    private final Member author;
    @Getter
    private final Message message;


    public RiCreator(@NonNull Member author, @NonNull MessageChannel channel, @NonNull final String commandName, @NonNull final String prefix, @NotNull final String raw, @NonNull final Message msg, @NonNull final RiCommand settings) {
        this.channel = channel;
        this.args = raw.replaceFirst(prefix, "").split("\\s+");
        this.name = commandName;
        this.settings = settings;
        this.command = settings.getCommand(commandName);
        this.author = author;
        this.message = msg;
    }


    public void executeCommand() {
        this.command.executeCommand(this.channel, this.author, this.args, this.message);
    }

}
