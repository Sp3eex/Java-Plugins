package _UtilsClasses;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import org.bukkit.ChatColor;

public class Colors {
	public Colors() { }
  
	public static List<String> parseColors(List<String> list) {
		List<String> parsedStrings = new ArrayList<String>();
		for (String string : list) {
			parsedStrings.add(parseColors(string));
		}
		return parsedStrings;
	}
  
	public static String parseColors(String string) {
		return ChatColor.translateAlternateColorCodes('&', string);
	}
  
	public static int[] hexToRGB(String hex) {
		hex = hex.replace("#", "");
    
		if (hex.length() != 6) {
			throw new IllegalArgumentException("Hex length must be 6");
		}
    
		return new int[] { 
			Integer.valueOf(hex.substring(1, 3), 16).intValue(), 
    		Integer.valueOf(hex.substring(3, 5), 16).intValue(), 
    		Integer.valueOf(hex.substring(5, 7), 16).intValue() };
	}
  
	public static Color getColorFromHex(String hex) {
		int[] rgb = hexToRGB(hex);
		return new Color(rgb[0], rgb[1], rgb[2]);
	}
}
