package tfar.ranchcraft.item;

import net.minecraft.block.Block;
import net.minecraft.item.IItemTier;
import net.minecraft.item.ToolItem;

import java.util.Set;

public class MalletItem extends ToolItem {
    public MalletItem(float attackDamageIn, float attackSpeedIn, IItemTier tier, Set<Block> effectiveBlocksIn, Properties builderIn) {
        super(attackDamageIn, attackSpeedIn, tier, effectiveBlocksIn, builderIn);
    }
}
