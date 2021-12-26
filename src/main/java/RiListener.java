import cooldown.RiCooldown;
import lombok.Getter;
import lombok.NonNull;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class RiListener extends ListenerAdapter {
    @Getter
    private final RiCommand settings;

    public RiListener(@NonNull RiCommand settings) {
        this.settings = settings;
        settings.getBot().getEventManager().register(this);
    }


    @Override
    public void onMessageReceived(@NonNull MessageReceivedEvent e) {
        String prefix = getSettings().getPrefix();
        if(e.getMessage().getContentRaw().startsWith(prefix)) {
            String[] args = e.getMessage().getContentRaw().split("\\s+");
            String name = args[0].substring(prefix.length());
                if (e.getMember() != null) {
                    if(this.settings.commandExists(name)) {
                        if(this.settings.getSetting() != null) {
                            if (this.settings.getSetting().cooldownTime() != 0) {
                                if (!RiCooldown.isInCooldown(e.getMember().getNickname(), "command")) {
                                    RiCreator cmd = new RiCreator(e.getMember(), e.getChannel(), name, prefix, e.getMessage().getContentRaw(), e.getMessage(), this.settings);
                                    cmd.executeCommand();
                                    RiCooldown cd = new RiCooldown(e.getMember().getNickname(), "command", this.settings.getSetting().cooldownTime());
                                    cd.start();
                                } else {
                                    if (this.settings.getSetting().cooldownMessage(e.getMember(), RiCooldown.getTimeLeft(e.getMember().getNickname(), "command")).getMessage() != null) {
                                        if (this.settings.getSetting().cooldownMessage(e.getMember(), RiCooldown.getTimeLeft(e.getMember().getNickname(), "command")).getMessage() instanceof String) {
                                            e.getChannel().sendTyping().queue();
                                            e.getChannel().sendMessage(this.settings.getSetting().cooldownMessage(e.getMember(), RiCooldown.getTimeLeft(e.getMember().getNickname(), "command")).getMessageString()).queue();
                                        } else if (this.settings.getSetting().cooldownMessage(e.getMember(), RiCooldown.getTimeLeft(e.getMember().getNickname(), "command")).getMessage() instanceof EmbedBuilder) {
                                            e.getChannel().sendTyping().queue();
                                            e.getChannel().sendMessageEmbeds(this.settings.getSetting().cooldownMessage(e.getMember(), RiCooldown.getTimeLeft(e.getMember().getNickname(), "command")).getEmbedMessage().build()).queue();
                                        } else {
                                            System.out.println("ERRO: Message is not embed or text! Ex0002");
                                        }
                                    }
                                }
                            } else {
                                RiCreator cmd = new RiCreator(e.getMember(), e.getChannel(), name, prefix, e.getMessage().getContentRaw(), e.getMessage(), this.settings);
                                cmd.executeCommand();
                            }
                        } else {
                            RiCreator cmd = new RiCreator(e.getMember(), e.getChannel(), name, prefix, e.getMessage().getContentRaw(), e.getMessage(), this.settings);
                            cmd.executeCommand();
                        }
                        } else {
                        if(this.settings.getSetting() != null) {
                            if (this.settings.getSetting().unknowCommand(e.getMember(), name).getMessage() != null) {
                                if (this.settings.getSetting().unknowCommand(e.getMember(), name).getMessage() instanceof String) {
                                    e.getChannel().sendMessage(this.settings.getSetting().unknowCommand(e.getMember(), name).getMessageString()).queue();
                                } else if (this.settings.getSetting().unknowCommand(e.getMember(), name).getMessage() instanceof EmbedBuilder) {
                                    e.getChannel().sendTyping().queue();
                                    e.getChannel().sendMessageEmbeds(this.settings.getSetting().unknowCommand(e.getMember(), name).getEmbedMessage().build()).queue();
                                } else {
                                    System.out.println("ERRO: Message is not embed or text! Ex0003");
                                }
                            }
                        }
                    }
            }
        }
    }
}
