package darth.invisible;

import darth.invisible.blocks.BabyBlock;
import darth.invisible.blocks.ItemBlock;
import darth.invisible.blocks.PlayerBlock;
import darth.invisible.blocks.SolidBlock;
import darth.invisible.items.Wand;
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

public class Registry
{
    private static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, InvisibleBlocks.MODID);
    private static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, InvisibleBlocks.MODID);

    public static final RegistryObject<Block> Player_IB = BLOCKS.register("ethereal_invisible_block", () -> new PlayerBlock(Block.Properties.of(Material.GLASS, MaterialColor.NONE).harvestLevel(0).noOcclusion()));
    public static final RegistryObject<Item> Player_IB_Item = ITEMS.register("ethereal_invisible_block", () -> new BlockItem(Player_IB.get(), new Item.Properties().tab(ItemGroup.TAB_BUILDING_BLOCKS)));

    public static final RegistryObject<Block> Solid_IB = BLOCKS.register("solid_invisible_block", () -> new SolidBlock(Block.Properties.of(Material.GLASS, MaterialColor.NONE).harvestLevel(0).noOcclusion()));
    public static final RegistryObject<Item> Solid_IB_Item = ITEMS.register("solid_invisible_block", () -> new BlockItem(Solid_IB.get(), new Item.Properties().tab(ItemGroup.TAB_BUILDING_BLOCKS)));

    public static final RegistryObject<Block> Baby_IB = BLOCKS.register("childish_invisible_block", () -> new BabyBlock(Block.Properties.of(Material.GLASS, MaterialColor.NONE).harvestLevel(0).noOcclusion()));
    public static final RegistryObject<Item> Baby_IB_Item = ITEMS.register("childish_invisible_block", () -> new BlockItem(Baby_IB.get(), new Item.Properties().tab(ItemGroup.TAB_BUILDING_BLOCKS)));

    public static final RegistryObject<Block> Item_IB = BLOCKS.register("grated_invisible_block", () -> new ItemBlock(Block.Properties.of(Material.GLASS, MaterialColor.NONE).harvestLevel(0).noOcclusion()));
    public static final RegistryObject<Item> Item_IB_Item = ITEMS.register("grated_invisible_block", () -> new BlockItem(Item_IB.get(), new Item.Properties().tab(ItemGroup.TAB_BUILDING_BLOCKS)));

    public static final RegistryObject<Item> Wand = ITEMS.register("invisible_wand", () -> new Wand());

    public static void register()
    {
        BLOCKS.register(FMLJavaModLoadingContext.get().getModEventBus());
        ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
    }
}
