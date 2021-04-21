package darth.invisible.blocks;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.entity.AgeableEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class BabyBlock extends Block
{
    public BabyBlock(AbstractBlock.Properties properties)
    {
        super(properties);
    }

    @Override
    public BlockRenderType getRenderShape(BlockState state)
    {
        return BlockRenderType.INVISIBLE;
    }
    public boolean propagatesSkylightDown(BlockState state, IBlockReader block, BlockPos pos)
    {
        return true;
    }
    public VoxelShape getVisualShape(BlockState state, IBlockReader block, BlockPos pos, ISelectionContext selectionContext)
    {
        return VoxelShapes.empty();
    }

    @Override
    public VoxelShape getCollisionShape(BlockState state, IBlockReader block, BlockPos pos, ISelectionContext selectionContext)
    {
        if (selectionContext.getEntity() instanceof AgeableEntity && ((AgeableEntity)selectionContext.getEntity()).isBaby())
        {
            return VoxelShapes.empty();
        }
        else
        {
            return VoxelShapes.block();
        }
   }
    @OnlyIn(Dist.CLIENT)
    public float getShadeBrightness(BlockState state, IBlockReader block, BlockPos pos)
    {
        return 1.0F;
    }
}
