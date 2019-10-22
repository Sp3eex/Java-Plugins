package me.Sp3eex.Nametags;

import org.bukkit.ChatColor;

public class ColorUtils {

	static String RemoveColors(String input) {
    	String output = input
    		.replace("&0", "")
    		.replace("&1", "")
    		.replace("&2", "")
    		.replace("&3", "")
    		.replace("&4", "")
    		.replace("&5", "")
    		.replace("&6", "")
    		.replace("&7", "")
    		.replace("&8", "")
    		.replace("&9", "")
    		.replace("&a", "")
    		.replace("&b", "")
    		.replace("&c", "")
    		.replace("&d", "")
    		.replace("&e", "")
    		.replace("&f", "")
    		.replace("&k", "")
    		.replace("&l", "")
    		.replace("&m", "")
    		.replace("&n", "")
    		.replace("&o", "")
    		.replace("&r", "")
    		.replace(")", "")
    		.replace("(", "")
    		.replace("[", "")
    		.replace("]", "");
    	String output1 = ChatColor.stripColor(output);
    	return output1;
    }
	
}
