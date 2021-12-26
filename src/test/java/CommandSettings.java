import message.MessageType;
import message.SettingMessage;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Member;
import org.jetbrains.annotations.NotNull;

import java.awt.*;

public class CommandSettings extends RiSettings {


    @Override
    public int cooldownTime() {
        return 6;
    }

    @Override
    public SettingMessage cooldownMessage(@NotNull Member user, int timeRemaing) {
        EmbedBuilder embed = new EmbedBuilder();
        embed.setColor(Color.red);
        embed.setDescription("Você deve esperar " + timeRemaing + "s para executar outro comando!");
        return new SettingMessage(MessageType.EMBED).setMessage(embed);
    }

    @Override
    public SettingMessage unknowCommand(@NotNull Member author, @NotNull String commandName) {
        return new SettingMessage(MessageType.TEXT).setMessage("Este comando não existe! Tente utilizar o comando " + TestMain.settings.getPrefix() + "help");
    }
}
