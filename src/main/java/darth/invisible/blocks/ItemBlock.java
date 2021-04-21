package darth.invisible.blocks;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class ItemBlock extends Block{
    public ItemBlock(AbstractBlock.Properties properties)
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
    public VoxelShape getVisualShape(BlockState state, IBlockReader block, BlockPos pos, ISelectionContext p_2303224)
    {
        return VoxelShapes.empty();
    }

    @Override
    public VoxelShape getCollisionShape(BlockState state, IBlockReader block, BlockPos pos, ISelectionContext selectionContext)
    {
        if (selectionContext.getEntity() instanceof ItemEntity)
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
