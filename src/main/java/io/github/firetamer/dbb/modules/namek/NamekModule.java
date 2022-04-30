package io.github.firetamer.dbb.modules.namek;

import static com.matyrobbrt.lib.registry.BetterRegistryObject.fluid;

import java.util.Random;
import java.util.function.Supplier;

import com.matyrobbrt.lib.annotation.RL;
import com.matyrobbrt.lib.module.IModule;
import com.matyrobbrt.lib.module.Module;
import com.matyrobbrt.lib.module.ModuleHelper;
import com.matyrobbrt.lib.registry.BetterRegistryObject;
import com.matyrobbrt.lib.registry.annotation.AutoBlockItem;
import com.matyrobbrt.lib.registry.annotation.RegisterBlock;
import com.matyrobbrt.lib.registry.annotation.RegisterFluid;
import com.matyrobbrt.lib.registry.annotation.RegisterItem;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.FlowingFluidBlock;
import net.minecraft.block.LeavesBlock;
import net.minecraft.block.RotatedPillarBlock;
import net.minecraft.block.SaplingBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.FlowingFluid;
import net.minecraft.fluid.FluidState;
import net.minecraft.item.BucketItem;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

import io.github.firetamer.dbb.DragonBlockBeyond;
import io.github.firetamer.dbb.api.FluidValues;
import io.github.firetamer.dbb.init.BlockInit;
import io.github.firetamer.dbb.modules.namek.blocks.AjisaBush;
import io.github.firetamer.dbb.modules.namek.blocks.NamekGrass;
import io.github.firetamer.dbb.modules.namek.blocks.NamekKelpStem;
import io.github.firetamer.dbb.modules.namek.blocks.NamekKelpTop;
import io.github.firetamer.dbb.modules.namek.blocks.NamekSeaGrass;
import io.github.firetamer.dbb.modules.namek.blocks.NamekSeaGrassTall;
import io.github.firetamer.dbb.modules.namek.blocks.SpreadableNamekGrass;
import io.github.firetamer.dbb.modules.namek.blocks.TilledNamekDirt;
import io.github.firetamer.dbb.modules.namek.features.NamekTree;
import net.minecraftforge.client.event.EntityViewRenderEvent;
import net.minecraftforge.client.event.RenderBlockOverlayEvent;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.event.entity.player.BonemealEvent;
import net.minecraftforge.event.entity.player.UseHoeEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fluids.FluidAttributes;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@SuppressWarnings(value = {
		"deprecation", "resource"
})
@Mod.EventBusSubscriber(modid = DragonBlockBeyond.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
@Module(id = @RL(modid = DragonBlockBeyond.MOD_ID, path = "namek_feature"))
public class NamekModule extends ModuleHelper implements IModule {

	/******************************************************************************************************************/
	// Main Registration
	/******************************************************************************************************************/

	@AutoBlockItem
	@RegisterBlock("namek_kelp_top")
	public static final Block NAMEK_KELP_TOP = new NamekKelpTop(AbstractBlock.Properties
			.of(Material.REPLACEABLE_WATER_PLANT).noCollission().randomTicks().instabreak().sound(SoundType.WET_GRASS));

	@AutoBlockItem
	@RegisterBlock("namek_kelp_stem")
	public static final Block NAMEK_KELP_STEM = new NamekKelpStem(AbstractBlock.Properties
			.of(Material.REPLACEABLE_WATER_PLANT).noCollission().instabreak().randomTicks().sound(SoundType.WET_GRASS));

	@AutoBlockItem
	@RegisterBlock("namek_tree_sapling")
	public static final Block NAMEK_TREE_SAPLING = new SaplingBlock(new NamekTree(), AbstractBlock.Properties
			.of(Material.PLANT).noCollission().randomTicks().instabreak().sound(SoundType.GRASS));

	@AutoBlockItem
	@RegisterBlock("namek_seagrass")
	public static final Block NAMEK_SEAGRASS = new NamekSeaGrass(AbstractBlock.Properties
			.of(Material.REPLACEABLE_WATER_PLANT).noCollission().instabreak().sound(SoundType.WET_GRASS));

	@AutoBlockItem
	@RegisterBlock("namek_tall_seagrass")
	public static final Block NAMEK_TALL_SEAGRASS = new NamekSeaGrassTall(AbstractBlock.Properties
			.of(Material.REPLACEABLE_WATER_PLANT).noCollission().instabreak().sound(SoundType.WET_GRASS));

	@AutoBlockItem
	@RegisterBlock("namek_grass_block")
	public static final Block NAMEK_GRASS_BLOCK = new SpreadableNamekGrass(AbstractBlock.Properties.of(Material.DIRT)
			.strength(0.5f, 0.5F).harvestTool(ToolType.SHOVEL).harvestLevel(0).sound(SoundType.GRASS).randomTicks());

	@AutoBlockItem
	@RegisterBlock("namek_log")
	public static final Block NAMEK_LOG = new RotatedPillarBlock(AbstractBlock.Properties.of(Material.WOOD)
			.strength(2.0f, 2.0f).harvestTool(ToolType.AXE).sound(SoundType.WOOD).harvestLevel(0));

	@AutoBlockItem
	@RegisterBlock("namek_leaves")
	public static final Block NAMEK_LEAVES = new LeavesBlock(AbstractBlock.Properties.of(Material.LEAVES).strength(0.2F)
			.sound(SoundType.GRASS).randomTicks().noOcclusion()); // "notSolid()" is the original method,
																	// "noOcclusion()" might not be correct.

	@AutoBlockItem
	@RegisterBlock("short_namek_grass")
	public static final Block SHORT_NAMEK_GRASS = new NamekGrass(AbstractBlock.Properties.of(Material.REPLACEABLE_PLANT)
			.noCollission().instabreak().sound(SoundType.GRASS).randomTicks());

	@AutoBlockItem
	@RegisterBlock("tall_namek_grass")
	public static final Block TALL_NAMEK_GRASS = new NamekGrass(AbstractBlock.Properties.of(Material.REPLACEABLE_PLANT)
			.noCollission().instabreak().sound(SoundType.GRASS).randomTicks());

	@AutoBlockItem
	@RegisterBlock("ajisa_bush")
	public static final Block AJISA_BUSH = new AjisaBush(AbstractBlock.Properties.of(Material.LEAVES).strength(0.2f)
			.sound(SoundType.GRASS).noOcclusion().randomTicks());

	@AutoBlockItem
	@RegisterBlock("tilled_namek_dirt")
	public static final Block TILLED_NAMEK_DIRT = new TilledNamekDirt(AbstractBlock.Properties.of(Material.DIRT)
			.strength(0.5f, 0.5F).harvestTool(ToolType.SHOVEL).sound(SoundType.GRASS).harvestLevel(0).randomTicks());

	@RegisterItem("ajisa_flowers")
	public static final Item AJISA_FLOWERS = new Item(new Item.Properties());

	@RegisterItem("namek_kelp_buds")
	public static final Item NAMEK_KELP_BUDS = new Item(new Item.Properties());

	/******************************************************************************************************************/
	// Namek Water
	/******************************************************************************************************************/

	public static final ResourceLocation STILL_RL = new ResourceLocation("block/water_still");
	public static final ResourceLocation FLOWING_RL = new ResourceLocation("block/water_flow");
	public static final ResourceLocation OVERLAY_RL = new ResourceLocation("block/water_overlay");

	@RegisterFluid("namek_fluid_source")
	public static final BetterRegistryObject<FlowingFluid> NAMEK_FLUID_SOURCE = fluid(
			() -> new ForgeFlowingFluid.Source(Properties.NAMEK_FLUID_PROPERTIES));

	@RegisterFluid("namek_fluid_flowing")
	public static final BetterRegistryObject<FlowingFluid> NAMEK_FLUID_FLOWING = fluid(
			() -> new ForgeFlowingFluid.Flowing(Properties.NAMEK_FLUID_PROPERTIES));

	@RegisterBlock("namek_fluid_block")
	public static final FlowingFluidBlock NAMEK_FLUID_BLOCK = new FlowingFluidBlock(NAMEK_FLUID_SOURCE::get,
			AbstractBlock.Properties.copy(Blocks.WATER));

	public static class Properties {

		public static final ForgeFlowingFluid.Properties NAMEK_FLUID_PROPERTIES = registerFluid(FluidValues.NAMEK_WATER,
				NAMEK_FLUID_SOURCE::get, NAMEK_FLUID_FLOWING::get, NAMEK_WATER_BUCKET, NAMEK_FLUID_BLOCK);

		private static ForgeFlowingFluid.Properties registerFluid(FluidValues value, Supplier<FlowingFluid> still,
				Supplier<FlowingFluid> flowing, Item bucket, FlowingFluidBlock block) {
			return new ForgeFlowingFluid.Properties(still, flowing,
					FluidAttributes.builder(STILL_RL, FLOWING_RL).color(value.getColor())
							.density(Math.round(value.getDensity())).viscosity(Math.round(value.getDensity()))
							.temperature(Math.round(value.getTemperature())).luminosity(value.getLuminosity())
							.overlay(OVERLAY_RL)).bucket(() -> bucket).block(() -> block);
		}
	}

	@RegisterItem("namek_water_bucket")
	public static final Item NAMEK_WATER_BUCKET = createBucket(NAMEK_FLUID_SOURCE::get);

	private static BucketItem createBucket(Supplier<FlowingFluid> fluid) {
		return new BucketItem(fluid, new Item.Properties().stacksTo(1).craftRemainder(Items.BUCKET));
	}

	/******************************************************************************************************************/
	// Events
	/******************************************************************************************************************/

	@Override
	public void onClientSetup(FMLClientSetupEvent event) {
		RenderTypeLookup.setRenderLayer(NAMEK_TREE_SAPLING, RenderType.cutoutMipped());

		RenderTypeLookup.setRenderLayer(SHORT_NAMEK_GRASS, RenderType.cutoutMipped());
		RenderTypeLookup.setRenderLayer(TALL_NAMEK_GRASS, RenderType.cutoutMipped());

		RenderTypeLookup.setRenderLayer(NAMEK_SEAGRASS, RenderType.cutoutMipped());
		RenderTypeLookup.setRenderLayer(NAMEK_TALL_SEAGRASS, RenderType.cutoutMipped());
		RenderTypeLookup.setRenderLayer(NAMEK_KELP_STEM, RenderType.cutoutMipped());
		RenderTypeLookup.setRenderLayer(NAMEK_KELP_TOP, RenderType.cutoutMipped());

		RenderTypeLookup.setRenderLayer(NAMEK_FLUID_SOURCE.get(), RenderType.translucent());
		RenderTypeLookup.setRenderLayer(NAMEK_FLUID_FLOWING.get(), RenderType.translucent());
		RenderTypeLookup.setRenderLayer(NAMEK_FLUID_BLOCK, RenderType.translucent());
	}

	@SubscribeEvent
	public static void greenFogInNamekWater(EntityViewRenderEvent.FogColors event) {
		PlayerEntity player = Minecraft.getInstance().player;
		double eyeHeight = player.getEyeY() - 1 / 9d;
		FluidState fluidstate = player.level.getFluidState(new BlockPos(player.getX(), eyeHeight, player.getZ()));

		if (fluidstate.getType() == NamekModule.NAMEK_FLUID_FLOWING.get()
				|| fluidstate.getType() == NamekModule.NAMEK_FLUID_SOURCE.get()) {
			event.setBlue(0.09F);
			event.setGreen(0.45F); // As it stands, what I have done is match the ratios shown in the fluid color
									// to the fog color. 255 for the fluid color being 1.0F for the Fog DBBColor.
			event.setRed(0);
		}
	}

	@SubscribeEvent
	public static void cancelVanillaWaterOverlay(RenderBlockOverlayEvent event) {
		@SuppressWarnings("resource")
		PlayerEntity player = Minecraft.getInstance().player;
		double eyeHeight = player.getEyeY() - 1 / 9d;
		FluidState fluidstate = player.level.getFluidState(new BlockPos(player.getX(), eyeHeight, player.getZ()));

		if (fluidstate.getType() == NamekModule.NAMEK_FLUID_FLOWING.get()
				|| fluidstate.getType() == NamekModule.NAMEK_FLUID_SOURCE.get()) {
			if (event.isCancelable()) {
				event.setCanceled(true);
			}
		}
	}

	// The only weird thing about this event I have done is that you cannot just
	// click any side of the Namek Dirt/Grass and it function,
	// it has to be the top unlike the vanilla functionality.
	@SubscribeEvent
	public static void onHoeUse(UseHoeEvent event) {
		RayTraceResult lookingAt = Minecraft.getInstance().hitResult;
		if (lookingAt != null && lookingAt.getType() == RayTraceResult.Type.BLOCK) {
			double x = lookingAt.getLocation().x();
			double y = lookingAt.getLocation().y();
			double z = lookingAt.getLocation().z();

			BlockPos pos = new BlockPos(x, y, z).below();

			PlayerEntity player = event.getPlayer();
			World world = player.getCommandSenderWorld();
			BlockState state = world.getBlockState(pos);

			if (state == BlockInit.CLAY_DIRT.defaultBlockState()) {
				world.playSound(player, pos, SoundEvents.HOE_TILL, SoundCategory.BLOCKS, 1, 1);
				world.setBlockAndUpdate(pos, NamekModule.TILLED_NAMEK_DIRT.defaultBlockState());
			}

			if (state == NamekModule.NAMEK_GRASS_BLOCK.defaultBlockState()) {
				world.playSound(player, pos, SoundEvents.HOE_TILL, SoundCategory.BLOCKS, 1.0F, 1.0F);
				world.setBlockAndUpdate(pos, NamekModule.TILLED_NAMEK_DIRT.defaultBlockState());
			}
		}
	}

	// This is being used to recreate the vanilla underwater grow effect, but for my
	// Namek Fluid and Sea Grasses
	// Remember to check "BonemealEvent" from forge and "BoneMealItem" form
	// minecraft to get this working.
	@SubscribeEvent
	public static void namekFluidBonemealUse(BonemealEvent event) {
		PlayerEntity player = Minecraft.getInstance().player;
		World worldIn = player.getCommandSenderWorld();
		RayTraceResult lookingAt = Minecraft.getInstance().hitResult;
		Random rand = new Random();
		double eyeHeight = player.getEyeY() - 1 / 9d;
		FluidState fluidstate = player.level.getFluidState(new BlockPos(player.getX(), eyeHeight, player.getZ()));

		if (fluidstate.getType() == NamekModule.NAMEK_FLUID_FLOWING.get()
				|| fluidstate.getType() == NamekModule.NAMEK_FLUID_SOURCE.get()) {
			if (lookingAt != null && lookingAt.getType() == RayTraceResult.Type.BLOCK) {
				double x = lookingAt.getLocation().x();
				double y = lookingAt.getLocation().y();
				double z = lookingAt.getLocation().z();

				BlockPos blockpos_above_1 = new BlockPos(x, y, z).above();

				// player.displayClientMessage(new StringTextComponent("Hey, Bonemeal + Namek
				// water + solid block shoudl do something"), false);
				// Here starts the more experimental stuff... Basically, don't delete anything
				// above here.
				Task: for (int i = 0; i < 128; ++i) {
					BlockPos blockpos1 = blockpos_above_1;

					for (int j = 0; j < i / 16; ++j) {
						blockpos1 = blockpos1.offset(rand.nextInt(3) - 1, (rand.nextInt(3) - 1) * rand.nextInt(3) / 2,
								rand.nextInt(3) - 1);

						if (worldIn.getBlockState(blockpos1).isCollisionShapeFullBlock(worldIn, blockpos1)) {
							continue Task;
						}
					}

					BlockPos blockpos1_above = blockpos1.above();
					BlockState blockstate1 = worldIn.getBlockState(blockpos1);
					BlockState blockstate2 = worldIn.getBlockState(blockpos1_above);

					if (blockstate1.equals(NamekModule.NAMEK_FLUID_BLOCK)
							&& blockstate2.equals(NamekModule.NAMEK_FLUID_BLOCK)) {
						BlockState blockstate3;

						if (rand.nextInt(4) == 0) {
							blockstate3 = NamekModule.TALL_NAMEK_GRASS.defaultBlockState();
						} else {
							blockstate3 = NamekModule.SHORT_NAMEK_GRASS.defaultBlockState();
						}

						worldIn.setBlockAndUpdate(blockpos1, blockstate3);
					}
				}
			}
		}
	}
}
