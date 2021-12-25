import lombok.Getter;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageEmbed;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;

public class RiCommand {
    @Getter
    private final String prefix;
    @Getter
    private final JDA bot;
    @Getter
    private final HashMap<String, CommandExecutor> commands;



    private MessageEmbed unknownCommandEmbed;
    private Message unknownCommandMessage;

    public RiCommand(@NotNull JDA bot, @NotNull String defaultPrefix) {
    this.bot = bot;
    this.prefix = defaultPrefix;
    this.commands = new HashMap<>();
    }


    public RiCommand addCommand(CommandExecutor command){
        this.commands.put(command.getCommandName(), command);
        if(command.getAliases() != null) {
            for(String s : command.getAliases()) {
                this.commands.put(s, command);
            }
        }
        return this;
    }


    public void startCommands() {
        new CommandListener(this);
    }

    public CommandExecutor getCommand(String command) {
        return this.commands.get(command);
    }

    public boolean commandExists(String command) {
        return this.commands.containsKey(command);
    }



}
