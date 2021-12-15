package io.github.FireTamer.common;

import io.github.FireTamer.init.BlockInit;
import net.minecraft.block.BlockState;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.feature.template.RuleTest;
import net.minecraft.world.gen.placement.Placement;
import net.minecraft.world.gen.placement.TopSolidRangeConfig;
import net.minecraftforge.common.world.BiomeGenerationSettingsBuilder;
import net.minecraftforge.event.world.BiomeLoadingEvent;

public class OreGeneration 
{
	public static void generateOres(final BiomeLoadingEvent event) 
	{
		//if not 	event = Biome_NETHER or event = Biome_END		Do what is below
		if(!(event.getCategory().equals(Biome.Category.NETHER) || event.getCategory().equals(Biome.Category.THEEND))) 
		{
			generateOres(event.getGeneration(), OreFeatureConfig.FillerBlockType.NATURAL_STONE, BlockInit.WARENAI_ORE.defaultBlockState(), 5, 20, 50, 10);

		}
	}
	
	//GenerationBuilder, Fills in overworld stone, Block being generated, veinSize max of 5, minHeight generated, maxHeight generated, amount found in a chunk (rarity).
	private static void generateOres(BiomeGenerationSettingsBuilder settings, RuleTest fillerType, BlockState state, int veinSize, int minHeight, int maxHeight, int amountPerChunk) 
	{
		settings.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Feature.ORE.configured(new OreFeatureConfig(fillerType, state, veinSize))
			.decorated(Placement.RANGE.configured(new TopSolidRangeConfig(minHeight, 0, maxHeight)))
			.squared().count(amountPerChunk));
	}
	
}

/**	
		This could be used for many things like underground structures if I learn how to use it appropriately.
		GenerationStage.Decoration.
			
		For a custom feature that I can make if I figure out how that is done.
		event.getGeneration().withFeature(stage, features)	
		
		
		If I want an ore to only be in a specific biome or dimension then I would put something into this method like what's below.
		
		Gets the category which I believe is like DESERT having multiple different types of desert, but calling this puts it in all desert types.
		if(event.getCategory())
		
		Gets the Biome Climate in case you want generation in a warm climate.
		if(event.getClimate())
		
		Gets the name of the biome incase of wanting a specific one.
		if(event.getName())
		
		Gets the size of the biome.
		if(event.getScale())
		
		Get the generation settings (I actually don't know what this one is or how it works, just put it here incase I figure it out.)
		if(event.getGeneration())
		
**/