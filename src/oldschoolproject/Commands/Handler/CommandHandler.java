package oldschoolproject.Commands.Handler;

import java.io.IOException;
import java.lang.reflect.Field;

import org.bukkit.command.Command;
import org.bukkit.command.SimpleCommandMap;
import org.bukkit.plugin.SimplePluginManager;

import com.google.common.reflect.ClassPath;

import oldschoolproject.Main;

public abstract class CommandHandler {

    void registerAllCommands() throws IOException {
        ClassPath cp = ClassPath.from(getClass().getClassLoader());
        cp.getTopLevelClassesRecursive("mypackage.myplugin.blah.commandspackage").forEach(classInfo -> {
            try {
        	Class c = Class.forName(classInfo.getName());
            Object obj = c.newInstance();
            if (obj instanceof Command) {
                Command cmd = (Command) obj;
                registerCommand(cmd);
            }
            } catch (Exception e) {
            	
            }
        });
    }

    void registerCommand(Command cmd) {
    	SimplePluginManager spm = (SimplePluginManager) Main.getInstance().getServer().getPluginManager();
    	Field commandMapField = spm.getClass().getDeclaredField("commandMap");
    	commandMapField.setAccessible(true);
    	
        SimpleCommandMap simpleCommandMap = (SimpleCommandMap) commandMapField.get(spm);
        simpleCommandMap.register(Main.getInstance().getDescription().getName(), cmd);
    }
	
}
