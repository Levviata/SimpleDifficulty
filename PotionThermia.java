package com.charles445.simpledifficulty.potion;

import com.charles445.simpledifficulty.api.SDCapabilities;
import com.charles445.simpledifficulty.api.SDPotions;
import com.charles445.simpledifficulty.api.temperature.ITemperatureCapability;
import com.charles445.simpledifficulty.config.ModConfig;
import com.charles445.simpledifficulty.util.DamageUtil;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import static com.charles445.simpledifficulty.handler.TemperatureResistanceGetterHandler.*;

public abstract class PotionThermia extends PotionBase
{	
	public PotionThermia(boolean isBadEffect, int liquidColor)
	{
		super(isBadEffect, liquidColor);
	}


	@Override
	public void performEffect(EntityLivingBase entity, int amplifier)
	{
		if(entity instanceof EntityPlayer && !entity.isPotionActive(SDPotions.heat_resist))
		{
			World world = entity.getEntityWorld();
			EntityPlayer player = (EntityPlayer) entity;
			if(DamageUtil.isModDangerous(world) && DamageUtil.healthAboveDifficulty(world, player))
			{
				ITemperatureCapability capability = SDCapabilities.getTemperatureData(player);
				// Calculate the total armor resistance
				float totalArmorResist = helmetResist + chestplateResist + leggingsResist + bootsResist;
				// Calculate the damage reduction based on total armor resistance
				float damageReduction = 1.0f - totalArmorResist;
				// Calculate the final damage
				float damage = (0.5f + (0.5f * (float)capability.getTemperatureDamageCounter() * (float)ModConfig.server.temperature.temperatureDamageScaling)) * damageReduction;
				attackPlayer(player, damage);
				capability.addTemperatureDamageCounter(1);
			}
		}
	}
	
	public abstract void attackPlayer(EntityPlayer player, float damage);
}
