package darth.invisible.render;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import com.mojang.realmsclient.client.Request;
import darth.invisible.InvisibleBlocks;
import darth.invisible.Registry;
import darth.invisible.blocks.InvisibleBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.util.math.vector.Matrix4f;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.RegistryObject;


public class ClientEventHandler {
    @SubscribeEvent
    @OnlyIn(Dist.CLIENT)
    public void onRenderWorldLastEvent(RenderWorldLastEvent event)
    {
        ClientPlayerEntity player = Minecraft.getInstance().player;
        MatrixStack matrixStack = event.getMatrixStack();
        GlStateManager._disableDepthTest();
        Vector3d camera = Minecraft.getInstance().gameRenderer.getMainCamera().getPosition();

        matrixStack.pushPose();
        matrixStack.translate(-camera.x, -camera.y, -camera.z);
        IRenderTypeBuffer.Impl buffer = Minecraft.getInstance().renderBuffers().bufferSource();
        IVertexBuilder builder = buffer.getBuffer(RenderType.LINES);
        for (int dx = -20; dx <= 20; dx++)
        {
            for (int dy = -20; dy <= 20; dy++)
            {
                for (int dz = -20; dz <= 20; dz++)
                {
                    BlockPos bPos = player.blockPosition().offset(dx, dy, dz);
                    BlockState blockState = Minecraft.getInstance().level.getBlockState(bPos);
                    AxisAlignedBB aabb = AxisAlignedBB.unitCubeFromLowerCorner(new Vector3d(bPos.getX(), bPos.getY(), bPos.getZ()));

                    if (blockState.getBlock() instanceof InvisibleBlock)
                    {
                        InvisibleBlock block = (InvisibleBlock) blockState.getBlock();
                        float r = 1f;
                        float g = .4f;
                        float b = .4f;
                        float a = .0f;
                        if (player.getItemInHand(Hand.MAIN_HAND).getItem().equals(Registry.Invisible_Block_Item.get())) a=0.5f;
                        WorldRenderer.renderLineBox(matrixStack, builder, aabb, r, g, b, a);
                    }
                }
            }
        }
        matrixStack.popPose();
        buffer.endBatch(RenderType.LINES);
    }
}