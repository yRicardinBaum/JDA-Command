# JDA-Command
 Esta API foi criando visando uma melhor experiência com o desenvolvedor.

# Dependencia

 Você precisa adicionar este projeto à lista de dependências de seu projeto! (Gradle).
 Segue as instruções abaixo.

 - JDA (v5.0.0).
 - Lombok (v1.18.16).

With gradle:
```groovy
repositories {
        mavenCentral()
        maven { url = 'https://jitpack.io/' }
}
dependencies {
    implementation 'com.github.yRicardinBaum:JDA-Command:v1.6'
}
```


# Iniciando

 Para iniciarmos, temos que iniciar os comandos juntamente com o bot.
 Siga como instruido abaixo.

```java
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;

import javax.security.auth.login.LoginException;

public class TestMain {
    public static JDA bot;
    public static RiCommand settings;

    public static void main(String[] args) throws LoginException {
        JDABuilder builder = JDABuilder.createDefault("YOUR TOKEN");
        bot = builder.build(); 
                                                                              
        RiCommand cmds = new RiCommand(bot, "ri!"); ## SEU PREFIXO AQUI 
        cmds.startCommands();
        settings = cmds;
    }
}
```

 Assim ja interligamos a api com o JDA. (Lembrando que é impossivel a api funcionar sem o startCommand() por isso tem que colocar. Outra coisa, o startCommand() tem que ir depois da inicialização do bot!)


# Adicionando um comando

 Para isso, eu irei criar uma classe chamada CommandTest.java.
 Nela estaremos extendendo a livraria RiExecutor (A livraria executadora do comando).
 Seguiresmo também setando as informações do comando com a função super dentro da variavel publica da classe. 
 E assim, passaremos o parametro executeCommand para ser a variável que executará o comando!
 Segue codigo abaixo.

```java
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;

public class CommandTest extends CommandExecutor {


    public CommandTest() {
        super("ping", "pingar");
    }

    @Override
    public void executeCommand(MessageChannel chat, Member author, String[] args, Message message) {
        long start = System.currentTimeMillis();
        chat.sendTyping().queue(v -> {
            long ping = System.currentTimeMillis() - start;
            chat.sendMessage("Ping: " + ping).queue();
        });
    }
}
```

 Com o comando feito agora iremos cadastra-la na Main do bot.
 Como segue o código abaixo.

```java
        RiCommand cmds = new RiCommand(bot, "ri!");
        cmds.addCommand(new CommandTest());
        cmds.startCommands();
        settings = cmds;
```

# Configurações adicionais
A API também conta com uma configuração avançada!
Você pode alterar configurações do cooldown como tempo e mensagem.
E também alterar a mensagem de comando desconhecido!
Segue instruções abaixo!
```java
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
```
Criado a classe de configurações, nós iremos adiciona-la na main!
Segue codigo abaixo:

```java
    public static void main(String[] args) throws LoginException {
        JDABuilder builder = JDABuilder.createDefault("TOKEN");
        bot = builder.build();
        RiCommand cmds = new RiCommand(bot, "ri!", new CommandSettings());
        cmds.addCommand(new CommandTest());
        cmds.startCommands();
        System.out.println("BOT INICIADO COM SUCESSO!");
        settings = cmds;
    }
```


Relembrando que essa configuração não é algo OBRIGATÓRIO!


 E pronto! Está tudo funcionando perfeitamente!

 Qualquer dúvida podem entrar em contato com o meu discord: https://discordapp.com/users/409801761470152704
