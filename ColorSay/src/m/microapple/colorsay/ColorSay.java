package m.microapple.colorsay;
/**
 * ColorSay Version 1.0.7
 * 
 * By Microapple
 * 
 * Tested on CraftBook build 617
 *
 */
import java.io.File;
import java.util.Arrays;
import java.io.InputStream;
import java.io.FileOutputStream;
import java.util.jar.JarFile;
import java.util.jar.JarEntry;
import org.bukkit.entity.Player;
import org.bukkit.util.config.Configuration;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public class ColorSay extends JavaPlugin {
	public String color;
	public String sayPrefix;
	
	public void onEnable() {
		PluginDescriptionFile pdfFile = this.getDescription();
		System.out.println(pdfFile.getName() + " version " + pdfFile.getVersion() + " is enabled!");
		this.loadConfigFile();
        Configuration cfg = this.getConfiguration();
        color = cfg.getString("say_color", "PURPLE");
        sayPrefix = cfg.getString("say_prefix", "Server");
        System.out.println("ColorSay will use " + color + " as the say color.");
        System.out.println("ColorSay will use [" + sayPrefix + "] as the prefix.");

		}
	public void onDisable() {
		System.out.println("Colorsay Disabled");
	}
	
	public void loadConfigFile() {
		// load config file, creating it first if it doesn't exist
		// Needs import java.io.File;
		//import java.io.InputStream;
		//import java.io.FileOutputStream;
		//import java.util.jar.JarFile;
		//import java.util.jar.JarEntry;
		File configFile = new File(this.getDataFolder(), "config.yml");
			if (!configFile.canRead()) try {
				configFile.getParentFile().mkdirs();
				JarFile jar = new JarFile(this.getFile());
				JarEntry entry = jar.getJarEntry("config.yml");
				InputStream is = jar.getInputStream(entry);
				FileOutputStream os = new FileOutputStream(configFile);
				byte[] buf = new byte[(int)entry.getSize()];
				is.read(buf, 0, (int)entry.getSize());
				os.write(buf);
		os.close();
		this.getConfiguration().load();
		} catch (Exception e) {
		System.out.println("ColorSay: could not create configuration file");
		}


		}
	
	public boolean onCommand(CommandSender sender, Command command, String commandLabel, String[] args) {
        String commandName = command.getName().toLowerCase();
        
            if (sender instanceof Player) {
                if (commandName.equals("csay")) {
                      	boolean op;;
						op = sender.isOp();
                    	if (op == true){
                    		if(args.length > 0) {
                    			getServer().broadcastMessage(ChatColor.valueOf(color) + "[" + sayPrefix + "] " + Arrays.asList(args).toString().substring(1).replaceFirst("]", "").replace(", ", " "));

                    		}
                    		else {
                    			return false;
                    		}
                    	}
                    	else {
	        	            Player player = (Player) sender;
	                        player.sendMessage(ChatColor.RED + "This command requires you to be an Op");
                    		
                    	}
                    	
                    	
                    	
                    
                }
            }
            return true;
   }

	
}
