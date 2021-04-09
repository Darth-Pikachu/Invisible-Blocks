package darth.invisible;

import darth.invisible.blocks.InvisibleBlock;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.fml.RegistryObject;

public class Registry {
    private static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, InvisibleBlocks.MODID);
    private static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, InvisibleBlocks.MODID);

    public static final RegistryObject<Block> Invisible_Block = BLOCKS.register("invisible_block", () -> new InvisibleBlock(Block.Properties.of(Material.GLASS, MaterialColor.NONE).harvestLevel(0).noOcclusion()));
    public static final RegistryObject<Item> Invisible_Block_Item = ITEMS.register("invisible_block", () -> new BlockItem(Invisible_Block.get(), new Item.Properties().tab(ItemGroup.TAB_BUILDING_BLOCKS)));

    public static void register()
    {
        BLOCKS.register(FMLJavaModLoadingContext.get().getModEventBus());
        ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
    }


}
