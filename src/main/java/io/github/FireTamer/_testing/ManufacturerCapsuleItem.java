package io.github.FireTamer._testing;

public class ManufacturerCapsuleItem //extends Item
{
    /**
     public ManufacturerCapsuleItem(Properties properties)
     {
     super(properties);
     }

     //If holding shift, shows tooltip with the first string text, else show "Hold Shift" string text component.
     @Override
     @OnlyIn(Dist.CLIENT)
     public void appendHoverText(ItemStack stack, World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
     super.appendHoverText(stack, worldIn, tooltip, flagIn);
     if (InputMappings.isKeyDown(Minecraft.getInstance().getWindow().getWindow(), GLFW.GLFW_KEY_LEFT_SHIFT)) {
     tooltip.add(new StringTextComponent("Requires a 3 block wide, 3 block deep, and 2 block high space to deploy the Manufacturer."));
     } else {
     tooltip.add(new TranslationTextComponent("Hold Shift for More Info"));
     }
     }


     @Override
     public ActionResultType useOn(ItemUseContext context)
     {
     PlayerEntity playerentity = context.getPlayer();
     World world = context.getLevel();
     BlockPos blockpos = context.getClickedPos();

     //Placing "blockpos.offset(context.getFace())" would result in placing a block offset by the face the item was clicked on. (Click the side place on the side)
     BlockPos core_pos = blockpos.offset(0,1,0);
     BlockPos bottom_east_pos = blockpos.offset(1,1,0);
     BlockPos bottom_west_pos = blockpos.offset(-1,1,0);
     BlockPos bottom_south_pos = blockpos.offset(0,1,1);
     BlockPos bottom_north_pos = blockpos.offset(0,1,-1);
     BlockPos core2_pos = blockpos.offset(0,2,0);
     BlockPos top_east_pos = blockpos.offset(1,2,0);
     BlockPos top_west_pos = blockpos.offset(-1,2,0);
     BlockPos top_south_pos = blockpos.offset(0,2,1);
     BlockPos top_north_pos = blockpos.offset(0,2,-1);

     //Check for aproppriate space (The 3x3x2 space the manufacturer will occupy)
     if ((world.isEmptyBlock(core_pos)) && (world.isEmptyBlock(bottom_east_pos)) && //isEmptyBlock(core_pos)
     (world.isEmptyBlock(bottom_west_pos)) && (world.isEmptyBlock(bottom_south_pos)) &&
     (world.isEmptyBlock(bottom_north_pos)) && (world.isEmptyBlock(core2_pos)) &&
     (world.isEmptyBlock(top_east_pos)) && (world.isEmptyBlock(top_west_pos)) &&
     (world.isEmptyBlock(top_south_pos)) && (world.isEmptyBlock(top_north_pos)))
     {
     if ((playerentity.getDirection()) == Direction.NORTH)
     {
     world.setBlockAndUpdate(core_pos, BlockInit.MANUFACTURER_CORE.defaultBlockState().rotate(world, core_pos, Rotation.CLOCKWISE_180));
     world.setBlockAndUpdate(bottom_south_pos, BlockInit.MANUFACTURER_FACEPLATE.defaultBlockState().rotate(world, bottom_south_pos, Rotation.CLOCKWISE_180));
     world.setBlockAndUpdate(bottom_east_pos, BlockInit.MANUFACTURER_SIDEPLATE.defaultBlockState().rotate(world, bottom_east_pos, Rotation.CLOCKWISE_90));
     world.setBlockAndUpdate(bottom_west_pos, BlockInit.MANUFACTURER_SIDEPLATE.defaultBlockState().rotate(world, bottom_west_pos, Rotation.COUNTERCLOCKWISE_90));
     world.setBlockAndUpdate(core2_pos, BlockInit.MANUFACTURER_CORE2.defaultBlockState().rotate(world, core2_pos, Rotation.CLOCKWISE_180));
     world.setBlockAndUpdate(top_east_pos, BlockInit.MANUFACTURER_GLASSPANEL.defaultBlockState().rotate(world, top_east_pos, Rotation.CLOCKWISE_90));
     world.setBlockAndUpdate(top_west_pos, BlockInit.MANUFACTURER_GLASSPANEL.defaultBlockState().rotate(world, top_west_pos, Rotation.COUNTERCLOCKWISE_90));
     world.setBlockAndUpdate(top_south_pos, BlockInit.MANUFACTURER_GLASSPANEL.defaultBlockState().rotate(world, top_south_pos, Rotation.CLOCKWISE_180));
     world.setBlockAndUpdate(top_north_pos, BlockInit.MANUFACTURER_GLASSPANEL.defaultBlockState().rotate(world, top_north_pos, Rotation.NONE));
     world.setBlockAndUpdate(bottom_north_pos, Blocks.AIR.defaultBlockState());
     }

     if ((playerentity.getDirection()) == Direction.EAST)
     {
     world.setBlockAndUpdate(core_pos, BlockInit.MANUFACTURER_CORE.defaultBlockState().rotate(world, core_pos, Rotation.COUNTERCLOCKWISE_90));
     world.setBlockAndUpdate(bottom_west_pos, BlockInit.MANUFACTURER_FACEPLATE.defaultBlockState().rotate(world, bottom_west_pos, Rotation.COUNTERCLOCKWISE_90));
     world.setBlockAndUpdate(bottom_south_pos, BlockInit.MANUFACTURER_SIDEPLATE.defaultBlockState().rotate(world, bottom_south_pos, Rotation.CLOCKWISE_180));
     world.setBlockAndUpdate(bottom_north_pos, BlockInit.MANUFACTURER_SIDEPLATE.defaultBlockState().rotate(world, bottom_north_pos, Rotation.NONE));
     world.setBlockAndUpdate(core2_pos, BlockInit.MANUFACTURER_CORE2.defaultBlockState().rotate(world, core2_pos, Rotation.COUNTERCLOCKWISE_90));
     world.setBlockAndUpdate(top_east_pos, BlockInit.MANUFACTURER_GLASSPANEL.defaultBlockState().rotate(world, top_east_pos, Rotation.CLOCKWISE_90));
     world.setBlockAndUpdate(top_west_pos, BlockInit.MANUFACTURER_GLASSPANEL.defaultBlockState().rotate(world, top_west_pos, Rotation.COUNTERCLOCKWISE_90));
     world.setBlockAndUpdate(top_south_pos, BlockInit.MANUFACTURER_GLASSPANEL.defaultBlockState().rotate(world, top_south_pos, Rotation.CLOCKWISE_180));
     world.setBlockAndUpdate(top_north_pos, BlockInit.MANUFACTURER_GLASSPANEL.defaultBlockState().rotate(world, top_north_pos, Rotation.NONE));
     world.setBlockAndUpdate(bottom_east_pos, Blocks.AIR.defaultBlockState());
     }

     if ((playerentity.getDirection()) == Direction.SOUTH)
     {
     world.setBlockAndUpdate(core_pos, BlockInit.MANUFACTURER_CORE.defaultBlockState().rotate(world, core_pos, Rotation.NONE));
     world.setBlockAndUpdate(bottom_north_pos, BlockInit.MANUFACTURER_FACEPLATE.defaultBlockState().rotate(world, bottom_north_pos, Rotation.NONE));
     world.setBlockAndUpdate(bottom_east_pos, BlockInit.MANUFACTURER_SIDEPLATE.defaultBlockState().rotate(world, bottom_east_pos, Rotation.CLOCKWISE_90));
     world.setBlockAndUpdate(bottom_west_pos, BlockInit.MANUFACTURER_SIDEPLATE.defaultBlockState().rotate(world, bottom_west_pos, Rotation.COUNTERCLOCKWISE_90));
     world.setBlockAndUpdate(core2_pos, BlockInit.MANUFACTURER_CORE2.defaultBlockState().rotate(world, core2_pos, Rotation.NONE));
     world.setBlockAndUpdate(top_east_pos, BlockInit.MANUFACTURER_GLASSPANEL.defaultBlockState().rotate(world, top_east_pos, Rotation.CLOCKWISE_90));
     world.setBlockAndUpdate(top_west_pos, BlockInit.MANUFACTURER_GLASSPANEL.defaultBlockState().rotate(world, top_west_pos, Rotation.COUNTERCLOCKWISE_90));
     world.setBlockAndUpdate(top_south_pos, BlockInit.MANUFACTURER_GLASSPANEL.defaultBlockState().rotate(world, top_south_pos, Rotation.CLOCKWISE_180));
     world.setBlockAndUpdate(top_north_pos, BlockInit.MANUFACTURER_GLASSPANEL.defaultBlockState().rotate(world, top_north_pos, Rotation.NONE));
     world.setBlockAndUpdate(bottom_south_pos, Blocks.AIR.defaultBlockState());
     }

     if ((playerentity.getDirection()) == Direction.WEST)
     {
     world.setBlockAndUpdate(core_pos, BlockInit.MANUFACTURER_CORE.defaultBlockState().rotate(world, core_pos, Rotation.CLOCKWISE_90));
     world.setBlockAndUpdate(bottom_east_pos, BlockInit.MANUFACTURER_FACEPLATE.defaultBlockState().rotate(world, bottom_east_pos, Rotation.CLOCKWISE_90));
     world.setBlockAndUpdate(bottom_south_pos, BlockInit.MANUFACTURER_SIDEPLATE.defaultBlockState().rotate(world, bottom_south_pos, Rotation.CLOCKWISE_180));
     world.setBlockAndUpdate(bottom_north_pos, BlockInit.MANUFACTURER_SIDEPLATE.defaultBlockState().rotate(world, bottom_north_pos, Rotation.NONE));
     world.setBlockAndUpdate(core2_pos, BlockInit.MANUFACTURER_CORE2.defaultBlockState().rotate(world, core2_pos, Rotation.CLOCKWISE_90));
     world.setBlockAndUpdate(top_east_pos, BlockInit.MANUFACTURER_GLASSPANEL.defaultBlockState().rotate(world, top_east_pos, Rotation.CLOCKWISE_90));
     world.setBlockAndUpdate(top_west_pos, BlockInit.MANUFACTURER_GLASSPANEL.defaultBlockState().rotate(world, top_west_pos, Rotation.COUNTERCLOCKWISE_90));
     world.setBlockAndUpdate(top_south_pos, BlockInit.MANUFACTURER_GLASSPANEL.defaultBlockState().rotate(world, top_south_pos, Rotation.CLOCKWISE_180));
     world.setBlockAndUpdate(top_north_pos, BlockInit.MANUFACTURER_GLASSPANEL.defaultBlockState().rotate(world, top_north_pos, Rotation.NONE));
     world.setBlockAndUpdate(bottom_west_pos, Blocks.AIR.defaultBlockState());
     }

     return ActionResultType.SUCCESS;

     } else {

     if (!world.isClientSide())
     {
     playerentity.displayClientMessage(new StringTextComponent("There is not an appropriate amount of space available"), false);
     }

     return ActionResultType.FAIL;

     }
     }
     **/
}