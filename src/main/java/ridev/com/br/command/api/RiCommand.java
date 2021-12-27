package ridev.com.br.command.api;

import lombok.Getter;
import lombok.NonNull;
import net.dv8tion.jda.api.JDA;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;

public class RiCommand {
    @Getter
    private final String prefix;
    @Getter
    private final JDA bot;
    @Getter
    private final HashMap<String, RiExecutor> commands;
    @Getter
    private final RiSettings setting;

    public RiCommand(@NotNull JDA bot, @NotNull String defaultPrefix, @NonNull RiSettings config) {
    this.bot = bot;
    this.prefix = defaultPrefix;
    this.commands = new HashMap<>();
    this.setting = config;
    }
    public RiCommand(@NotNull JDA bot, @NotNull String defaultPrefix) {
        this.bot = bot;
        this.prefix = defaultPrefix;
        this.commands = new HashMap<>();
        this.setting = null;
    }


    public void addCommand(@NonNull RiExecutor command) {
        this.commands.put(command.getCommandName(), command);
        if (command.getAliases() != null) {
            for (String s : command.getAliases()) {
                this.commands.put(s, command);
            }
        }
    }



    public void startCommands() {
        new RiListener(this);
    }

    public RiExecutor getCommand(@NonNull String command) {
        return this.commands.get(command);
    }

    public boolean commandExists(@NonNull String command) {
        return this.commands.containsKey(command);
    }



}
