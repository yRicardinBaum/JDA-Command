# JDA-Command
 Esta API foi criando visando uma melhor experiência com o desenvolvedor.

# Dependencia

 Você precisa adicionar este projeto à lista de dependências de seu projeto! (Gradle).
 Segue as instruções abaixo.


With gradle:
```groovy
dependencies {
    implementation 'ridev.com:Command:2.1'
}
```


# Iniciando

 Para iniciarmos, temos que iniciar os comandos juntamente com o bot
 Siga como instuido abaixo.

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
 Nela estaremos extendendo a livraria CommandExecutor (A livraria executadora do comando).
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

 E pronto! Está tudo funcionando perfeitamente!

 Qualquer dúvida podem entrar em contato com o meu discord: https://discordapp.com/users/409801761470152704
