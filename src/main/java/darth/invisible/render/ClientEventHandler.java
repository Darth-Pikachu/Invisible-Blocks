package darth.invisible.render;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import com.sun.media.jfxmedia.logging.Logger;
import darth.invisible.InvisibleBlocks;
import darth.invisible.Registry;
import darth.invisible.blocks.PlayerBlock;
import net.minecraft.block.BlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.Hand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraft.util.math.vector.Matrix4f;
import net.minecraft.client.renderer.RenderState;

public class ClientEventHandler
{

    public static final RenderType HIGHLIGHT = RenderType.create("highlight", DefaultVertexFormats.POSITION_COLOR, 7, 256,
            RenderType.State.builder().setWriteMaskState(new RenderState
            .WriteMaskState(true, true))
            .setTransparencyState(new RenderState.TransparencyState("loony_transparency", RenderSystem::enableBlend, RenderSystem::disableBlend
            )).createCompositeState(false));

    public static void CubeRender(Matrix4f mat, IVertexBuilder builder, int bx, int by, int bz, float r, float g, float b, float a)
    {
        float x = (float)bx;
        float y = (float)by;
        float z = (float)bz;

        // Face 1 -- Negative Z Face
        builder.vertex(mat, x, y, z).color(r, g, b, a).endVertex();
        builder.vertex(mat, x, y + 1, z).color(r, g, b, a).endVertex();
        builder.vertex(mat, x + 1, y + 1, z).color(r, g, b, a).endVertex();
        builder.vertex(mat, x + 1, y, z).color(r, g, b, a).endVertex();

        // Face 2 -- Bottom Face
        builder.vertex(mat, x, y, z).color(r, g, b, a).endVertex();
        builder.vertex(mat, x + 1, y, z).color(r, g, b, a).endVertex();
        builder.vertex(mat, x + 1, y, z + 1).color(r, g, b, a).endVertex();
        builder.vertex(mat, x, y, z + 1).color(r, g, b, a).endVertex();

        // Face 3 -- Negative X Face
        builder.vertex(mat, x, y, z).color(r, g, b, a).endVertex();
        builder.vertex(mat, x, y, z + 1).color(r, g, b, a).endVertex();
        builder.vertex(mat, x, y + 1, z + 1).color(r, g, b, a).endVertex();
        builder.vertex(mat, x, y + 1, z).color(r, g, b, a).endVertex();

        //Face 4 -- Positive Z Face
        builder.vertex(mat, x, y, z + 1).color(r, g, b, a).endVertex();
        builder.vertex(mat, x + 1, y, z + 1).color(r, g, b, a).endVertex();
        builder.vertex(mat, x + 1, y + 1, z + 1).color(r, g, b, a).endVertex();
        builder.vertex(mat, x, y + 1, z + 1).color(r, g, b, a).endVertex();

        //Face 5 -- Top Face
        builder.vertex(mat, x, y + 1, z).color(r, g, b, a).endVertex();
        builder.vertex(mat, x, y + 1, z + 1).color(r, g, b, a).endVertex();
        builder.vertex(mat, x + 1, y + 1, z + 1).color(r, g, b, a).endVertex();
        builder.vertex(mat, x + 1, y + 1, z).color(r, g, b, a).endVertex();

        // Face 6 -- Positive X Face
        builder.vertex(mat, x + 1, y, z).color(r, g, b, a).endVertex();
        builder.vertex(mat, x + 1, y + 1, z).color(r, g, b, a).endVertex();
        builder.vertex(mat, x + 1, y + 1, z + 1).color(r, g, b, a).endVertex();
        builder.vertex(mat, x + 1, y, z + 1).color(r, g, b, a).endVertex();
    }

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
        IVertexBuilder builder = buffer.getBuffer(HIGHLIGHT);
        Matrix4f mat = matrixStack.last().pose();
        for (int dx = -20; dx <= 20; dx++)
        {
            for (int dy = -20; dy <= 20; dy++)
            {
                for (int dz = -20; dz <= 20; dz++)
                {
                    BlockPos bPos = player.blockPosition().offset(dx, dy, dz);
                    BlockState blockState = Minecraft.getInstance().level.getBlockState(bPos);
                    //AxisAlignedBB aabb = AxisAlignedBB.unitCubeFromLowerCorner(new Vector3d(bPos.getX(), bPos.getY(), bPos.getZ()));

                    if (blockState.getBlock() instanceof PlayerBlock)
                    {
                        float r = 0.5f;
                        float g = .1f;
                        float b = .9f;
                        float a = .5f;
                        if (player.getItemInHand(Hand.MAIN_HAND).getItem().equals(Registry.Player_IB_Item.get()))
                        {
                            CubeRender(mat, builder, bPos.getX(), bPos.getY(), bPos.getZ(), r, g, b, a);
                        }
                    }
                }
            }
        }
        matrixStack.popPose();
        buffer.endBatch(HIGHLIGHT);
    }


}