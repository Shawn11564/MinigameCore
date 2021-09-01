package dev.mrshawn.minigamecore.utils;

import lombok.Getter;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
public class ItemBuilder {

	private final ItemStack item;
	private final Material material;
	private int amount;
	private final ItemMeta meta;
	private List<String> lore = new ArrayList<>();
	private final Map<Enchantment, Integer> enchants = new HashMap<>();

	public ItemBuilder(Material material) {
		this.material = material;
		amount = 1;
		item = new ItemStack(this.material, amount);
		meta = item.getItemMeta();
	}

	public ItemBuilder(Material material, int amount) {
		this.material = material;
		this.amount = amount;
		item = new ItemStack(this.material, amount);
		meta = item.getItemMeta();
	}

	public ItemBuilder setName(String name) {
		meta.setDisplayName(Chat.colorize(name));
		return this;
	}

	public ItemBuilder setNoName() {
		return setName(" ");
	}

	public ItemBuilder addBlankLoreLine() {
		lore.add(" ");
		return this;
	}

	public ItemBuilder addLoreLine(String line) {
		lore.add(Chat.colorize(line));
		return this;
	}

	public ItemBuilder setLore(List<String> lore) {
		this.lore = Chat.colorizeList(lore);
		return this;
	}

	public ItemBuilder addEnchant(Enchantment type, int level) {
		enchants.put(type, level);
		return this;
	}

	public ItemBuilder setAmount(int amount) {
		this.amount = amount;
		item.setAmount(this.amount);
		return this;
	}

	public ItemBuilder setCustomModelData(int data) {
		meta.setCustomModelData(data);
		return this;
	}

	public ItemBuilder hideAttributes() {
		meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		return this;
	}

	public ItemStack build() {
		meta.setLore(lore);
		enchants.forEach(item::addUnsafeEnchantment);
		item.setItemMeta(meta);
		return item;
	}
}
