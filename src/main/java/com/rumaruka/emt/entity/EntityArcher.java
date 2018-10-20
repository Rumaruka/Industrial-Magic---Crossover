package com.rumaruka.emt.entity;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntitySnowman;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.entity.projectile.EntityTippedArrow;
import net.minecraft.init.Enchantments;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;


public class EntityArcher extends EntitySnowman implements ICapabilitySerializable<NBTTagCompound> {
    public EntityArcher(World worldIn) {
        super(worldIn);
        this.tasks.addTask(1, new EntityAISwimming(this));
        this.tasks.addTask(2, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
        this.targetTasks.addTask(3, new EntityAINearestAttackableTarget(this, EntityLiving.class, 0, true, false, IMob.MOB_SELECTOR));
        this.tasks.addTask(4, new EntityAIWander(this, 0.30D));
    }
    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();

        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.30D);
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(40.0D);
    }

    @Override
    public void onDeath(DamageSource damageSource) {
        super.onDeath(damageSource);

        this.world.createExplosion(this, this.posX, this.posY, this.posZ, 1, true);
        this.setDead();
    }

    @Override
    protected Item getDropItem() {
        return Items.ARROW;
    }

    @Override
    protected void dropFewItems(boolean hitByPlayer, int levelOfLooting) {
        int j;
        int k;

        j = this.rand.nextInt(3 + levelOfLooting);

        for (k = 0; k < j; ++k) {
            this.dropItem(Items.ARROW, 1);
        }
        j = this.rand.nextInt(3 + levelOfLooting);

        for (k = 0; k < j; ++k) {
            this.dropItem(Items.BONE, 1);
        }
    }

    @Override
    public void attackEntityWithRangedAttack(EntityLivingBase target, float distanceFactor) {

       EntityArrow entityarrow = this.getArrow(distanceFactor);
       double d0 = target.posX - this.posX;
       double d1 = target.getEntityBoundingBox().minY + (double)(target.height / 3.0F) - entityarrow.posY;
       double d2 = target.posZ - this.posZ;
       double d3 = (double) MathHelper.sqrt(d0 * d0 + d2 * d2);
       int powerLevel = EnchantmentHelper.getEnchantmentLevel(Enchantments.POWER, this.getHeldItem(EnumHand.MAIN_HAND));
       int punchLevel = EnchantmentHelper.getEnchantmentLevel(Enchantments.PUNCH, this.getHeldItem(EnumHand.MAIN_HAND));

        entityarrow.setDamage((double) (distanceFactor * 2.0F) + this.rand.nextGaussian() * 0.25D );
        if(powerLevel>0){

            entityarrow.setDamage(entityarrow.getDamage() + (double) powerLevel * 0.5D + 0.5D);
        }
        if (punchLevel > 0) {
            entityarrow.setKnockbackStrength(punchLevel);
        }

        if (EnchantmentHelper.getEnchantmentLevel(Enchantments.FLAME, this.getHeldItem(EnumHand.MAIN_HAND)) > 0) {
            entityarrow.setFire(100);
        }

        this.playSound(SoundEvents.ENTITY_SKELETON_SHOOT, 1.0F, 1.0F / (this.getRNG().nextFloat() * 0.4F + 0.8F));
        entityarrow.shoot(d0, d1 + d3 * 0.20000000298023224D, d2, 1.6F, (float)(14 - this.world.getDifficulty().getDifficultyId() * 4));
        this.world.spawnEntity(entityarrow);

    }


    protected EntityArrow getArrow(float p_190726_1_)
    {
        EntityTippedArrow entitytippedarrow = new EntityTippedArrow(this.world, this);
        entitytippedarrow.setEnchantmentEffectsFromEntity(this, p_190726_1_);
        return entitytippedarrow;
    }



    public void init(Entity entity, World world) {
    }
}
