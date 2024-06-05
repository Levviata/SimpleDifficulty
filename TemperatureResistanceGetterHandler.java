package com.charles445.simpledifficulty.handler;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

public class TemperatureResistanceGetterHandler {
	public final Minecraft mc = Minecraft.getMinecraft();
	public static float helmetResist = 0;
	public static float chestplateResist = 0;
	public static float leggingsResist = 0;
	public static float bootsResist = 0;


	@SubscribeEvent
	public void onClientTick(TickEvent.ClientTickEvent event) {
		if (event.phase == TickEvent.Phase.END) {
			if (mc != null && mc.player != null) { // Check if Minecraft instance and player object are not null
				EntityPlayer player = mc.player; // Assign player object

				// Check if the player's ItemStack objects are not null and not empty before accessing them
				ItemStack helmet = player.getItemStackFromSlot(EntityEquipmentSlot.HEAD);
				if (helmet != null && !helmet.isEmpty()) {
					Object tempResist = getNBTTag(helmet, "temperature_resistance");
					if (tempResist != null) {
						helmetResist = (float) tempResist; // Assign helmetResist
					}
				}

				ItemStack chestplate = player.getItemStackFromSlot(EntityEquipmentSlot.CHEST);
				if (chestplate != null && !chestplate.isEmpty()) {
					Object tempResist = getNBTTag(chestplate, "temperature_resistance");
					if (tempResist != null) {
						chestplateResist = (float) tempResist; // Assign chestplateResist
					}
				}

				ItemStack leggings = player.getItemStackFromSlot(EntityEquipmentSlot.LEGS);
				if (leggings != null && !leggings.isEmpty()) {
					Object tempResist = getNBTTag(leggings, "temperature_resistance");
					if (tempResist != null) {
						leggingsResist = (float) tempResist; // Assign leggingsResist
					}
				}

				ItemStack boots = player.getItemStackFromSlot(EntityEquipmentSlot.FEET);
				if (boots != null && !boots.isEmpty()) {
					Object tempResist = getNBTTag(boots, "temperature_resistance");
					if (tempResist != null) {
						bootsResist = (float) tempResist; // Assign bootsResist
					}
				}

			}
		}
	}
	public static Float getNBTTag(ItemStack itemStack, String tagName) {
		NBTTagCompound nbtTagCompound = itemStack.getTagCompound();

		if (nbtTagCompound != null && nbtTagCompound.hasKey(tagName)) {
			return nbtTagCompound.getFloat(tagName);
		}

		return null;
	}

}
