package utility;
//***** https://github.com/fusesource/jansi *******
import org.fusesource.jansi.AnsiConsole;
import static org.fusesource.jansi.Ansi.*;
import static org.fusesource.jansi.Ansi.Color.*;

public class MyLog {

    public static void print(String output){
        //AnsiConsole.systemInstall();
        System.out.println( ansi().eraseScreen().bg(GREEN).fg(BLACK).a(output).reset() );
    }
}
