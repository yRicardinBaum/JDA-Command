import lombok.NonNull;
import message.SettingMessage;
import net.dv8tion.jda.api.entities.Member;

public abstract class RiSettings {



    public abstract int cooldownTime();


    public abstract SettingMessage cooldownMessage(@NonNull Member author, int timeRemaing);

    public abstract SettingMessage unknowCommand(@NonNull Member author, @NonNull String commandName);
}
