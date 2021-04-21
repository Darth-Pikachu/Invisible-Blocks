package darth.invisible.items;

import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;

public class Wand extends Item
{
    static public final int stackSize = 1;
    public Wand()
    {
        super(new Item.Properties().stacksTo(stackSize).tab(ItemGroup.TAB_TOOLS));
    }
}
