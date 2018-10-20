package com.rumaruka.emt.item.armor;


import com.rumaruka.emt.client.model.ModelSpecialArmor;
import com.rumaruka.emt.emt;
import com.rumaruka.emt.init.EMTItems;
import com.rumaruka.emt.util.EMTConfigHandler;
import ic2.api.item.ElectricItem;
import ic2.core.IC2;
import ic2.core.audio.AudioSource;
import ic2.core.audio.PositionSpec;
import ic2.core.item.armor.ItemArmorElectric;
import ic2.core.item.armor.jetpack.IJetpack;
import ic2.core.util.StackUtil;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemShield;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.event.entity.living.LivingFallEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Collection;

public class ItemInfusedQuantumChestplate extends ItemArmorElectric implements IJetpack {

    protected static ArrayList<Integer> potionRemovecost = new ArrayList<Integer>();
    public static AudioSource audioSource;
    private static boolean onJetpackActive = false;


    private final String armorName;
    public ItemInfusedQuantumChestplate(String armorName, double maxCharge, double transferLimit, int tier){

        super(null, null, EntityEquipmentSlot.CHEST, maxCharge, transferLimit, tier);
        this.armorName=armorName;
        this.setMaxDamage(27);
    }

    @Override
    protected boolean hasOverlayTexture() {
        return true;
    }

    @Override
    public String getArmorTexture(ItemStack stack, Entity entity, EntityEquipmentSlot slot, String type) {
        if(stack.getItem()==EMTItems.quantumarmor_nano){
            return emt.TEXTURE_PATH+":textures/models/armor/quantumarmor_nano.png";
        }
        if(stack.getItem()==EMTItems.quantumarmor_jetpack){
            return emt.TEXTURE_PATH+":textures/models/armor/quantumarmor_jetpack.png";
        }
        if(stack.getItem()==EMTItems.quantumarmor_quantum){
            return emt.TEXTURE_PATH+":textures/models/armor/quantumarmor_quantum.png";
        }
        if(stack.getItem()==EMTItems.quantumarmor_thaumium){

            return emt.TEXTURE_PATH+":textures/models/armor/quantumarmor_thaumium.png";
        }

        return emt.TEXTURE_PATH+":textures/models/armor/quantumarmor.png";
    }
    @SubscribeEvent
    public void onEntityLivingFallEvent(LivingFallEvent event) {
        if (IC2.platform.isSimulating() && (event.getEntity() instanceof EntityLivingBase)) {
            EntityLivingBase entity = (EntityLivingBase) event.getEntity();
            ItemStack armor = entity.getActiveItemStack();

            if ((armor != null) && (armor.getItem() == this)) {
                int fallDamage = Math.max((int) event.getDistance() - 10, 0);
                double energyCost = getEnergyPerDamage() * fallDamage;

                if (energyCost <= ElectricItem.manager.getCharge(armor)) {
                    ElectricItem.manager.discharge(armor, energyCost, Integer.MAX_VALUE, true, false, false);
                    event.setCanceled(true);
                }
            }
        }
    }
    @Nullable
    @Override
    public ModelBiped getArmorModel(EntityLivingBase entity, ItemStack stack, EntityEquipmentSlot armorSlot, ModelBiped _default) {
        try {
            if (entity instanceof EntityPlayer) {
                if (stack.getItem()==EMTItems.quantumarmor_jetpack) {
                    ModelSpecialArmor mbm = new ModelSpecialArmor(1, 1);
                    return mbm;
                }
                else if (stack.getItem()==EMTItems.quantumarmor_nano||stack.getItem()==EMTItems.quantumarmor_thaumium||stack.getItem()==EMTItems.quantumarmor_quantum) {
                    ModelSpecialArmor mbm = new ModelSpecialArmor(1, 2);
                    mbm.isJumping = stack.getTagCompound().getBoolean("isJumping");
                    return mbm;
                }
            }
        } catch (NullPointerException e) {
            new ModelSpecialArmor(1, 0);
        }
        return new ModelSpecialArmor(1, 0);    }

    @Override
    public String getItemStackDisplayName(ItemStack stack) {
        return I18n.translateToLocal(this.getUnlocalizedNameInefficiently(stack)+".name").trim();
    }

    @Override
    public double getDamageAbsorptionRatio() {
        return 1.1D;
    }

    @Override
    public int getEnergyPerDamage() {
        return 20000;
    }

    @Override
    public EnumRarity getRarity(ItemStack stack) {
        return EnumRarity.RARE;
    }

    @Override
    public void onArmorTick(World world, EntityPlayer player, ItemStack itemStack) {
        NBTTagCompound nbt = StackUtil.getOrCreateNbtData(itemStack);

        byte toggleTimer = nbt.getByte("toggleTimer");
        boolean ret = false;
        boolean hoverMode = nbt.getBoolean("hoverMode");
        boolean jetpackUsed = false;
        if (!world.isRemote) {

            for (PotionEffect effect : (Collection<PotionEffect>) player.getActivePotionEffects()) {
                if (potionRemovecost.contains(effect.getPotion())) {
                    IC2.platform.removePotion(player, effect.getPotion());
                }
            }
            ItemStack currentStack = player.inventory.getCurrentItem();
            Item currentItem = currentStack == ItemStack.EMPTY ? null : currentStack.getItem();

            if (currentStack.getItem() == EMTItems.quantumarmor) {
                return;
            }
            if (IC2.keyboard.isJumpKeyDown(player) && IC2.keyboard.isModeSwitchKeyDown(player) && toggleTimer == 0) {
                toggleTimer = 30;
                hoverMode = !hoverMode;
                if (IC2.platform.isSimulating()) {
                    nbt.setBoolean("hoverMode", hoverMode);
                    if (hoverMode) {
                        IC2.platform.messagePlayer(player, "Hover Mode enabled.", new Object[0]);
                    } else {
                        IC2.platform.messagePlayer(player, "Hover Mode disabled.", new Object[0]);
                    }
                }
            }
            if (currentStack != ItemStack.EMPTY && player.isSneaking() && toggleTimer == 0 && currentStack.getItem() == EMTItems.quantumarmor) {
                if (currentItem ==  Item.getByNameOrId("ic2:jetpack_electric")) {
                    IC2.platform.messagePlayer(player, "Jetpack enabled.", new Object[0]);

                    nbt.setInteger("jetpackCharge", (int) (ElectricItem.manager.getCharge(currentStack)));
                    player.inventory.setInventorySlotContents(player.inventory.currentItem, new ItemStack(EMTItems.quantumarmor_jetpack));
                } else if (currentItem == EMTItems.thaumiumwing) {
                    IC2.platform.messagePlayer(player, "Thaumium wings enabled.", new Object[0]);

                    player.inventory.setInventorySlotContents(player.inventory.currentItem, new ItemStack(EMTItems.quantumarmor_thaumium));
                } else if (currentItem == EMTItems.nanowing) {
                    IC2.platform.messagePlayer(player, "Nano wings enabled.", new Object[0]);
                    nbt.setInteger("NWCharge", (int) (ElectricItem.manager.getCharge(player.inventory.getCurrentItem())));

                    player.inventory.setInventorySlotContents(player.inventory.currentItem, new ItemStack(EMTItems.quantumarmor_nano));
                } else if (currentItem == EMTItems.quantumwing) {
                    IC2.platform.messagePlayer(player, "Quantum wings enabled.", new Object[0]);
                    nbt.setInteger("QWCharge", (int) (ElectricItem.manager.getCharge(player.inventory.getCurrentItem())));

                    player.inventory.setInventorySlotContents(player.inventory.currentItem, new ItemStack(EMTItems.quantumarmor_quantum));
                }
                if(currentStack==ItemStack.EMPTY&& nbt.getBoolean("unequip") && player.isSneaking()){
                    toggleTimer=30;
                    if(currentItem==EMTItems.quantumarmor_jetpack){
                        IC2.platform.messagePlayer(player, "Jetpack disabled.", new Object[0]);
                        ItemStack charged = new ItemStack(Item.getByNameOrId("ic2:jetpack_electric"));
                        if (nbt.getInteger("jetpackCharge") > 0)
                            ElectricItem.manager.charge(charged, nbt.getInteger("jetpackCharge"), 1, true, false);
                        else
                            ElectricItem.manager.charge(charged, 0, 1, true, false);
                        player.inventory.setInventorySlotContents(player.inventory.currentItem, charged);
                    }
                        if(currentItem==EMTItems.quantumarmor_thaumium){
                            IC2.platform.messagePlayer(player, "Thaumium wings disabled.", new Object[0]);
                            player.inventory.setInventorySlotContents(player.inventory.currentItem, new ItemStack(EMTItems.thaumiumwing));
                        }
                    if(currentItem==EMTItems.quantumarmor_nano){
                        IC2.platform.messagePlayer(player, "Nano wings disabled.", new Object[0]);
                        ItemStack charged = new ItemStack(EMTItems.nanowing);
                        if (nbt.getInteger("NWCharge") > 0)
                            ElectricItem.manager.charge(charged, nbt.getInteger("NWCharge"), 3, true, false);
                        else
                            ElectricItem.manager.charge(charged, 0, 3, true, false);
                        player.inventory.setInventorySlotContents(player.inventory.currentItem, charged);
                    }
                    if(currentItem==EMTItems.quantumarmor_thaumium){
                        IC2.platform.messagePlayer(player, "Quantum wings disabled.", new Object[0]);
                        ItemStack charged = new ItemStack(EMTItems.quantumwing);
                        if (nbt.getInteger("QWCharge") > 0)
                            ElectricItem.manager.charge(charged, nbt.getInteger("QWCharge"), 3, true, false);
                        else
                            ElectricItem.manager.charge(charged, 0, 3, true, false);
                        player.inventory.setInventorySlotContents(player.inventory.currentItem, charged);
                    }
                }
                nbt.setBoolean("unequip", false);
            }
            if ((IC2.keyboard.isJumpKeyDown(player) && currentItem == EMTItems.quantumarmor_jetpack) || (hoverMode && player.motionY < -0.029999999329447746D)) {
                jetpackUsed = useJetpack(player, hoverMode, itemStack);
            }

            if (currentItem == EMTItems.quantumarmor_thaumium)
                useWings(player, itemStack, world, 0.15f, 0.7f, 0.5f, 0, false);
            if (currentItem == EMTItems.quantumarmor_nano)
                useWings(player, itemStack, world, 0.25f, 0.6f, 0.3f, 5, true);
            if (currentItem == EMTItems.quantumarmor_quantum)
                useWings(player, itemStack, world, 0.33f, 0.5f, 0.2f, 7, true);



            if (IC2.platform.isSimulating() && toggleTimer > 0) {
                toggleTimer = (byte) (toggleTimer - 1);
                nbt.setByte("toggleTimer", toggleTimer);
            }

            if (IC2.platform.isRendering() && player == IC2.platform.getPlayerInstance()) {
                if (onJetpackActive != jetpackUsed) {
                    if (jetpackUsed) {
                        if (audioSource == null) {
                            audioSource = IC2.audioManager.createSource(player, PositionSpec.Backpack, "Tools/Jetpack/JetpackLoop.ogg", true, false, IC2.audioManager.getDefaultVolume());
                        }
                        if (audioSource != null) {
                            audioSource.play();
                        }
                    }
                    else if (audioSource != null) {
                        audioSource.remove();
                        audioSource = null;
                    }
                    onJetpackActive = jetpackUsed;
                }
                if (audioSource != null) {
                    audioSource.updatePosition();
                }
            }
            ret = jetpackUsed;
            player.extinguish();

            if(ret){
                player.inventoryContainer.detectAndSendChanges();

            }
        }
    }


    @Override
    public int getItemEnchantability() {
        if (EMTConfigHandler.enchanting == false)
            return 0;
        else
            return 4;
    }

    @Override
    public int getTier(ItemStack stack) {
        return 4;
    }


    public boolean useJetpack(EntityPlayer player, boolean hoverMode,ItemStack stack){
        int jetpackMaxCharge = 30000;
        float power = 1.0f;
        float dropPercantage = 0.5f;

        if(ElectricItem.manager.getCharge(stack)<=0){
            return false;
        }
        if (((float) ElectricItem.manager.getCharge(stack) / (float) jetpackMaxCharge) <= dropPercantage) {
            power = (float) (power * (ElectricItem.manager.getCharge(stack) / (float) jetpackMaxCharge) * dropPercantage);
        }
        if(IC2.keyboard.isForwardKeyDown(player)){
            float retruster = 3.5f;

            if(hoverMode){
                retruster = 0.5f;
            }
            float forwardpower = power * retruster * 2.0f;
            if(forwardpower >0.0f){
                if(player.motionY > 0)
                {
                    player.motionY = Math.max(player.motionY - 0.15D, 0);
                }
                else if(player.motionY < 0)
                {
                    if(!player.onGround)
                    {
                        player.motionY = Math.min(player.motionY + 0.15D, 0);
                    }
                }
            }
        }
        int worldHeight  = IC2.getWorldHeight(player.world);
        double y = player.posY;

        if(y> worldHeight - 25){
            if(y>worldHeight){
                y = worldHeight;
            }
            power = (float) (power * ((worldHeight - y) / 25.0D));
        }
        double prevmotion = player.motionY;
        player.motionY = Math.min(player.motionY+power*0.2f, 0.6000000238418579D);

        if(hoverMode){
            float maxHoverY = -0.025f;
            if(IC2.keyboard.isSneakKeyDown(player)){
                maxHoverY = -0.1f;
            }
            if(player.motionY > maxHoverY){
                player.motionY = maxHoverY;
                if(prevmotion > player.motionY){
                    player.motionY = prevmotion;
                }
            }
        }
        double consume = 8.0D;
        if(hoverMode){
            consume = 10.0D;
        }
        ElectricItem.manager.use(stack,consume,player);
        player.fallDistance = 0.0f;
        player.distanceWalkedModified = 0.0f;
        IC2.platform.resetPlayerInAirTime(player);
        return true;
    }

    public void useWings(EntityPlayer player, ItemStack stack, World world, float motionY, float motionXZ, float f1, int amount, boolean isElectric){
        NBTTagCompound nbt = StackUtil.getOrCreateNbtData(stack);
        boolean isJumping = IC2.keyboard.isJumpKeyDown(player);
        nbt.setBoolean("isJumping",isJumping);

        if(isJumping){
            byte f = nbt.getByte("f");
            nbt.setBoolean("isHolding",true);
            nbt.setByte("f", (byte) (f + 1));

            if(f > 7){
                nbt.setByte("f", (byte) 7);
            }
        }
        else if(nbt.getBoolean("isHolding")){
            byte f = nbt.getByte("f");
            nbt.setBoolean("isHolding", false);
            player.motionY = motionY * f;

            if(isElectric){
                ElectricItem.manager.use(stack,((motionY * f)*10)*amount,player);
            }
            if (player.motionX < 0.5 && player.motionZ < 0.5 && player.motionX > -0.5 && player.motionZ > -0.5) {
                player.motionX /= motionXZ;
                player.motionZ /= motionXZ;
            }


            world.playSound(player.posX,player.posY,player.posZ, SoundEvents.ENTITY_GHAST_SHOOT, SoundCategory.MASTER,1,1, true);


            for(int i = 0 ; i < 4; i++){
            world.spawnParticle(EnumParticleTypes.CLOUD, player.posX - 1 + (world.rand.nextInt(100)/50d),player.posY - 1, player.posZ - 1 + (world.rand.nextInt(100) / 50d), 0, -0.5, 0);
            }
            nbt.setByte("f", (byte) 0);
        }
        if (isJumping && !player.onGround && player.motionY < 0) {
            player.motionY *= f1;
        }

        if (player.isInWater() && !player.capabilities.isCreativeMode) {
            player.motionY += -0.05;
        }

        if (EMTConfigHandler.impactOfRain) {
            int playerX = MathHelper.floor(player.posX);
            int playerY = MathHelper.floor(player.posY);
            int playerZ = MathHelper.floor(player.posZ);



            if (world.canBlockSeeSky(new BlockPos(playerX, playerY, playerZ)) && world.isRaining() && !player.capabilities.isCreativeMode ){
                player.motionY += -0.05;
            }
        }

        if (player.isSneaking() && !player.onGround && player.motionY < 0) {
            player.motionY *= 0.6;
        }

        if (player.fallDistance > 0.0F) {
            player.fallDistance = 0;
        }
    }

    @Override
    public boolean drainEnergy(ItemStack itemStack, int i) {
        return ElectricItem.manager.discharge(itemStack, (double)(i + 6), 2147483647, true, false, false) > 0.0D;
    }

    @Override
    public float getPower(ItemStack itemStack) {
        return 1000.1f;
    }

    @Override
    public float getDropPercentage(ItemStack itemStack) {
        return 0;
    }

    @Override
    public double getChargeLevel(ItemStack itemStack) {
        return ElectricItem.manager.getCharge(itemStack) / this.getMaxCharge(itemStack);
    }

    @Override
    public boolean isJetpackActive(ItemStack itemStack) {
        if(itemStack.getItem()==EMTItems.quantumarmor_jetpack)
            return true;
        else
            return false;
    }

    @Override
    public float getHoverMultiplier(ItemStack itemStack, boolean b) {
        return 0;
    }

    @Override
    public float getWorldHeightDivisor(ItemStack itemStack) {
        return 0;
    }
}
