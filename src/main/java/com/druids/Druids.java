package com.druids;

import com.druids.effects.VoltaicBurst;
import com.druids.entity.*;
import com.druids.config.Default;
import com.druids.config.ServerConfig;
import com.druids.config.ServerConfigWrapper;
import com.druids.effects.CleansingFlame;
import com.druids.effects.EternalYouth;
import com.druids.items.Armors.Armors;
import com.druids.items.DruidsItems;
import com.druids.items.ItemsInit;
import com.druids.items.Weapons;
import com.druids.spells.Spells;
import com.extraspellattributes.ReabsorptionInit;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.JanksonConfigSerializer;
import me.shedaniel.autoconfig.serializer.PartitioningSerializer;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.loot.v2.LootTableEvents;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.runes.tinyconfig.ConfigManager;
import net.spell_engine.api.item.ItemConfig;
import net.spell_engine.api.loot.LootConfig;
import net.spell_engine.api.loot.LootHelper;
import net.spell_power.api.MagicSchool;
import net.spell_power.api.attributes.SpellAttributes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static net.minecraft.registry.Registries.ENTITY_TYPE;

public class Druids implements ModInitializer {
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
    public static final Logger LOGGER = LoggerFactory.getLogger("druids");
	public static final String MODID = "druids";
	public static EntityType<DruidCyclone> CYCLONEENTITY;
	public static EntityType<TotemEntity> TOTEM;
	public static ServerConfig config;
	public static EntityType<SoulBombEntity> SoulFlare;
	public static EntityType<ChainLightning> ChainLightning;

	public static EntityType<DruidLightning> DRUIDLIGHTNING;

	public static StatusEffect CLEANSING = new CleansingFlame(StatusEffectCategory.BENEFICIAL, 0xff4bdd)
			.addAttributeModifier(ReabsorptionInit.RECOUP, "33913b46-e7e3-414b-bbe2-664bcbbbb4ef",0.6, EntityAttributeModifier.Operation.MULTIPLY_BASE);
	public static StatusEffect VoltaicBurst = new VoltaicBurst(StatusEffectCategory.BENEFICIAL, 0xff4bdd)
			.addAttributeModifier(SpellAttributes.POWER.get(MagicSchool.LIGHTNING).attribute, "643cb40d-3fd8-473a-adab-7beaac5e639c",0.02, EntityAttributeModifier.Operation.MULTIPLY_BASE)
			.addAttributeModifier(SpellAttributes.POWER.get(MagicSchool.ARCANE).attribute, "139e91a3-3026-44ed-b1e5-58b30bd31cb9",0.02, EntityAttributeModifier.Operation.MULTIPLY_BASE)
			.addAttributeModifier(SpellAttributes.POWER.get(MagicSchool.SOUL).attribute, "a527f6c6-e910-4c67-a361-a309729826f9",0.02, EntityAttributeModifier.Operation.MULTIPLY_BASE)
			.addAttributeModifier(SpellAttributes.HASTE.attribute, "74a261d0-2e88-488a-ba5f-5145274d5a44",0.02, EntityAttributeModifier.Operation.MULTIPLY_BASE);


	public static StatusEffect ETERNAL = new EternalYouth(StatusEffectCategory.BENEFICIAL, 0xff4bdd)
			.addAttributeModifier(ReabsorptionInit.WARDING, "e481ebc4-6f40-4c58-9dcc-59cc4c98b2eb",20, EntityAttributeModifier.Operation.ADDITION)
			.addAttributeModifier(EntityAttributes.GENERIC_MAX_HEALTH,"e481ebc4-6f40-4c58-9dcc-59cc4c98b2eb",-0.9,EntityAttributeModifier.Operation.MULTIPLY_TOTAL);
	public static ConfigManager<ItemConfig> itemConfig = new ConfigManager<ItemConfig>
			("items_v1", Default.itemConfig)
			.builder()
			.setDirectory(MODID)
			.sanitize(true)
			.build();
	public static ConfigManager<LootConfig> lootConfig = new ConfigManager<LootConfig>
			("loot_v1", Default.lootConfig)
			.builder()
			.setDirectory(MODID)
			.sanitize(true)
			.constrain(LootConfig::constrainValues)
			.build();


	@Override
	public void onInitialize() {
		lootConfig.refresh();
		itemConfig.refresh();
		AutoConfig.register(ServerConfigWrapper.class, PartitioningSerializer.wrap(JanksonConfigSerializer::new));
		config = AutoConfig.getConfigHolder(ServerConfigWrapper.class).getConfig().server;

		Registry.register(Registries.STATUS_EFFECT,new Identifier(MODID,"cleansingfire"),CLEANSING);
		Registry.register(Registries.STATUS_EFFECT,new Identifier(MODID,"eternalyouth"),ETERNAL);
		Registry.register(Registries.STATUS_EFFECT,new Identifier(MODID,"voltaic_burst"),VoltaicBurst);

		ItemsInit.register();
		Weapons.register(itemConfig.value.weapons);
		Armors.register(itemConfig.value.armor_sets);

		CYCLONEENTITY = Registry.register(
				ENTITY_TYPE,
				new Identifier(MODID, "cycloneentity"),
				FabricEntityTypeBuilder.<DruidCyclone>create(SpawnGroup.MISC, DruidCyclone::new)
						.dimensions(EntityDimensions.fixed(4F, 2F)) // dimensions in Minecraft units of the render
						.trackRangeBlocks(128)
						.trackedUpdateRate(1)
						.build()
		);

		TOTEM = Registry.register(
				ENTITY_TYPE,
				new Identifier(MODID, "totem"),
				FabricEntityTypeBuilder.<TotemEntity>create(SpawnGroup.MISC, TotemEntity::new)
						.dimensions(EntityDimensions.fixed(2F, 2F)) // dimensions in Minecraft units of the render
						.trackRangeBlocks(128)
						.trackedUpdateRate(1)
						.build()
		);
		SoulFlare = Registry.register(
				ENTITY_TYPE,
				new Identifier(MODID, "soulflare"),
				FabricEntityTypeBuilder.<SoulBombEntity>create(SpawnGroup.MISC, SoulBombEntity::new)
						.dimensions(EntityDimensions.fixed(1F, 1F)) // dimensions in Minecraft units of the render
						.trackRangeBlocks(128)
						.trackedUpdateRate(1)
						.build()
		);
		ChainLightning = Registry.register(
				ENTITY_TYPE,
				new Identifier(MODID, "chainlightning"),
				FabricEntityTypeBuilder.<ChainLightning>create(SpawnGroup.MISC, ChainLightning::new)
						.dimensions(EntityDimensions.fixed(1F, 1F)) // dimensions in Minecraft units of the render
						.trackRangeBlocks(128)
						.trackedUpdateRate(1)
						.build()
		);
		DRUIDLIGHTNING = Registry.register(
				ENTITY_TYPE,
				new Identifier(MODID, "druidlightning"),
				FabricEntityTypeBuilder.<DruidLightning>create(SpawnGroup.MISC, DruidLightning::new)
						.dimensions(EntityDimensions.fixed(1F, 1F)) // dimensions in Minecraft units of the render
						.trackRangeBlocks(128)
						.trackedUpdateRate(1)
						.build()
		);
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.
		Spells.initializeSpells();
		LootTableEvents.MODIFY.register((resourceManager, lootManager, id, tableBuilder, source) -> {
			LootHelper.configure(id, tableBuilder, lootConfig.value, DruidsItems.entries);
		});
		itemConfig.save();
		LOGGER.info("Hello Fabric world!");
	}
}