import cooldown.RiCooldown;
import lombok.Getter;
import lombok.NonNull;
import message.SettingMessage;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
            Member member = e.getMember();
            if (member != null) {
                String[] args = e.getMessage().getContentRaw().split("\\s+");
                String name = args[0].substring(prefix.length());
                SettingMessage message = this.settings.getSetting().cooldownMessage(member, RiCooldown.getTimeLeft(member.getNickname(), "command"));
                if(this.settings.commandExists(name)) {
                    if(this.settings.getSetting() != null) {
                        if (this.settings.getSetting().cooldownTime() != 0) {
                            if (!RiCooldown.isInCooldown(member.getNickname(), "command")) {
                                List<String> list = new ArrayList<>(Arrays.asList(args));
                                list.remove(0);
                                args = list.toArray(new String[0]);
                                this.settings.getCommand(name).executeCommand(e.getChannel(), member, args, e.getMessage());
                                RiCooldown cd = new RiCooldown(member.getNickname(), "command", this.settings.getSetting().cooldownTime());
                                cd.start();
                            } else {
                                if (message.getMessage() != null) {
                                    if (message.getMessage() instanceof String) {
                                        e.getChannel().sendTyping().queue();
                                        e.getChannel().sendMessage(message.getMessageString()).queue();
                                    } else if (message.getMessage() instanceof EmbedBuilder) {
                                        e.getChannel().sendTyping().queue();
                                        e.getChannel().sendMessageEmbeds(message.getEmbedMessage().build()).queue();
                                    } else {
                                        System.out.println("ERRO: Message is not embed or text! Ex0002");
                                    }
                                }
                            }
                        } else {
                            List<String> list = new ArrayList<>(Arrays.asList(args));
                            list.remove(0);
                            args = list.toArray(new String[0]);
                            this.settings.getCommand(name).executeCommand(e.getChannel(), member, args, e.getMessage());
                        }
                    } else {
                        List<String> list = new ArrayList<>(Arrays.asList(args));
                        list.remove(0);
                        args = list.toArray(new String[0]);
                        this.settings.getCommand(name).executeCommand(e.getChannel(), member, args, e.getMessage());
                    }
                } else {
                    if(this.settings.getSetting() != null) {
                        SettingMessage unknow = this.settings.getSetting().unknowCommand(member, name);
                        if (unknow.getMessage() != null) {
                            if (unknow.getMessage() instanceof String) {
                                e.getChannel().sendMessage(unknow.getMessageString()).queue();
                            } else if (unknow.getMessage() instanceof EmbedBuilder) {
                                e.getChannel().sendTyping().queue();
                                e.getChannel().sendMessageEmbeds(unknow.getEmbedMessage().build()).queue();
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
