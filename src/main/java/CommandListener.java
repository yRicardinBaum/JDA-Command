import lombok.Getter;
import lombok.NonNull;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class CommandListener extends ListenerAdapter {
    @Getter
    private final RiCommand settings;

    public CommandListener(RiCommand settings) {
        this.settings = settings;
        settings.getBot().getEventManager().register(this);
    }


    @Override
    public void onMessageReceived(@NonNull MessageReceivedEvent e) {
        String prefix = getSettings().getPrefix();
        if(e.getMessage().getContentRaw().startsWith(prefix)) {
            String[] args = e.getMessage().getContentRaw().split("\\s+");
            String name = args[0].substring(prefix.length());
            if(this.settings.commandExists(name)) {
                if (e.getMember() != null) {
                    CommandCreator cmd = new CommandCreator(e.getMember(), e.getChannel(), name, prefix, e.getMessage().getContentRaw(), e.getMessage(), this.settings);
                    cmd.executeCommand();
                }
            }
        }

    }
}
