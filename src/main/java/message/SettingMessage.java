package message;

import lombok.Getter;
import net.dv8tion.jda.api.EmbedBuilder;

public class SettingMessage {
    @Getter
    private final MessageType type;
    @Getter
    private String messageString;
    @Getter
    private EmbedBuilder embedMessage;
    @Getter
    private Object message;

    public SettingMessage(MessageType type) {
        this.type = type;
        this.messageString = null;
        this.embedMessage = null;
    }


    public SettingMessage setMessage(Object message) {
        this.message = message;
        if(this.type.equals(MessageType.EMBED)) {
            this.embedMessage = (EmbedBuilder) message;
        } else if(this.type.equals(MessageType.TEXT)) {
            this.messageString = String.valueOf(message);
        } else {
            System.out.println("Você não pode setar a mensagem com esse objeto! Utilize uma String ou uma MessageEmbed");
        }
        return this;
    }
}
