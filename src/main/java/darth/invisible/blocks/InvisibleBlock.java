package darth.invisible.blocks;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.state.Property;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;


public class InvisibleBlock extends Block
{
    public InvisibleBlock(Properties properties)
    {
        super(properties);
    }

    @Override
    public BlockRenderType getRenderShape(BlockState p_1496451)
    {
        return BlockRenderType.INVISIBLE;
    }
    public boolean propagatesSkylightDown(BlockState p_2001231, IBlockReader p_2001232, BlockPos p_2001233)
    {
        return true;
    }
    public VoxelShape getVisualShape(BlockState p_2303221, IBlockReader p_2303222, BlockPos p_2303223, ISelectionContext p_2303224)
    {
        return VoxelShapes.empty();
    }

    @Override
    public VoxelShape getCollisionShape(BlockState p_220071_1_, IBlockReader p_220071_2_, BlockPos p_220071_3_, ISelectionContext selectionContext) {
        return selectionContext.getEntity() instanceof PlayerEntity ? VoxelShapes.empty() : VoxelShapes.block();
    }




    @OnlyIn(Dist.CLIENT)
    public float getShadeBrightness(BlockState p_2200801, IBlockReader p_2200802, BlockPos p_2200803) {
        return 1.0F;
    }
}