package com.rumaruka.emt.item.armor;

import com.rumaruka.emt.emt;
import com.rumaruka.emt.util.EMTTextHelper;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import thaumcraft.api.items.IVisDiscountGear;

import javax.annotation.Nullable;
import java.util.List;

public class ItemThaumiumReinforcedWing extends ItemFeatherWing implements IVisDiscountGear {
        int visDiscount;
    public ItemThaumiumReinforcedWing(ArmorMaterial material, int par3,  EntityEquipmentSlot equipmentSlotIn) {
        super(material, par3, equipmentSlotIn);
        this.setMaxStackSize(1);
        this.setMaxDamage(250);
        this.isDamageable();
        visDiscount=4;

    }

    @Override
    public float getFallDamageMult() {
        return 0.5f;
    }

    @Override
    public int getVisDiscount(ItemStack itemStack, EntityPlayer entityPlayer) {
        return visDiscount;
    }

    @Nullable
    @Override
    public String getArmorTexture(ItemStack stack, Entity entity, EntityEquipmentSlot slot, String type) {
        return emt.TEXTURE_PATH + ":textures/models/armor/thaumiumwing.png";
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        tooltip.add(EMTTextHelper.localize("tooltip.EMT.visDiscount") + ": " + String.valueOf(visDiscount) + "%");
    }

    @Override
    public void onArmorTick(World world, EntityPlayer player, ItemStack stack) {
        this.useWings(player,stack,world,0.15f, 0.7f, 0.5f, 0);
    }
}
