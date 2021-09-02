package dev.mrshawn.minigamecore.utils.config;

import dev.mrshawn.minigamecore.utils.Chat;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

public class FileSettings {

	private final File file;
	private final Map<Enum<?>, Object> values = new HashMap<>();

	public FileSettings(File file) {
		this.file = file;
	}

	public FileSettings(String filePath) {
		this.file = new File(filePath + ".yml");
	}

	public <E extends Enum<E>> FileSettings loadSettings(Class<E> enumClass) {
		try {
			YamlConfiguration config = YamlConfiguration.loadConfiguration(file);

			EnumSet<E> eSet = EnumSet.allOf(enumClass);

			Method method = enumClass.getMethod("getPath");

			for (E value : eSet) {
				values.put(value, config.get((String) method.invoke(value)));
			}
			return this;
		} catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
			Chat.error("Error when loading settings for: " + file);
			e.printStackTrace();
			return null;
		}
	}

	public void print() {
		values.forEach((key, value) -> {
			Chat.log(key + ":");
			if (value instanceof String) {
				Chat.log("String");
			}
			if (value instanceof Integer) {
				Chat.log("Integer");
			}
			if (value instanceof Boolean) {
				Chat.log("Boolean");
			}
		});
	}

	public boolean getBoolean(Enum<?> value) {
		return get(value, Boolean.class);
	}

	public boolean getBoolean(Enum<?> value, boolean defaultValue) {
		return get(value, Boolean.class, defaultValue);
	}

	public String getString(Enum<?> value) {
		return get(value, String.class);
	}

	public String getString(Enum<?> value, String defaultValue) {
		return get(value, String.class, defaultValue);
	}

	public int getInt(Enum<?> value) {
		return get(value, Integer.class);
	}

	public int getInt(Enum<?> value, int defaultValue) {
		return get(value, Integer.class, defaultValue);
	}

	public long getLong(Enum<?> value) {
		return get(value, Long.class);
	}

	public long getLong(Enum<?> value, long defaultValue) {
		return get(value, Long.class, defaultValue);
	}

	public List<String> getStringList(Enum<?> value) {
		List<String> tempList = new ArrayList<>();
		for (Object val : get(value, ArrayList.class)) {
			tempList.add((String) val);
		}
		return tempList;
	}

	public List<String> getStringList(Enum<?> value, List<String> defaultValue) {
		return values.containsKey(value) ? getStringList(value) : defaultValue;
	}

	public <T> T get(Enum<?> value, Class<T> clazz) {
		return clazz.cast(values.get(value));
	}

	public <T> T get(Enum<?> value, Class<T> clazz, T defaultValue) {
		return values.containsKey(value) ? get(value, clazz) : clazz.cast(defaultValue);
	}

}