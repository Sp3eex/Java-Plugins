package me.Sp3eex.MiniCrates;

import java.util.Random;

import org.bukkit.Color;
import org.bukkit.FireworkEffect;

public class FireworkUtils {

	static Color RandomFColor() {
		Color[] colors = { Color.AQUA, Color.BLACK, Color.BLUE, Color.FUCHSIA, Color.GRAY, Color.GREEN, Color.LIME,
				Color.MAROON, Color.NAVY, Color.OLIVE, Color.ORANGE, Color.PURPLE, Color.RED, Color.SILVER, Color.TEAL,
				Color.WHITE, Color.YELLOW };
		return colors[new Random().nextInt(colors.length)];
	}

	static FireworkEffect.Type RandomFEffect() {
		FireworkEffect.Type[] fireworkeffects = { FireworkEffect.Type.BALL, FireworkEffect.Type.BALL_LARGE,
				FireworkEffect.Type.BURST, FireworkEffect.Type.CREEPER, FireworkEffect.Type.STAR };
		return fireworkeffects[new Random().nextInt(fireworkeffects.length)];
	}

}
