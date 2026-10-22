package io.github.rco21.avatarmod.entity.custom;

import io.github.rco21.avatarmod.entity.ModEntities;
import net.minecraft.Util;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animation.AnimatableManager;
import software.bernie.geckolib.animation.AnimationController;
import software.bernie.geckolib.animation.RawAnimation;
import software.bernie.geckolib.util.GeckoLibUtil;

public class OstrichHorseEntity extends Animal implements GeoEntity {
    
    // 1. Data accessor to sync variant values between server and client
    private static final EntityDataAccessor<Integer> DATA_VARIANT_ID = 
            SynchedEntityData.defineId(OstrichHorseEntity.class, EntityDataSerializers.INT);

    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);

    public OstrichHorseEntity(EntityType<? extends Animal> entityType, Level level) {
        super(entityType, level);
    }

    // 2. Initialize tracking data defaults
    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);
        builder.define(DATA_VARIANT_ID, 0); // Defaults to variant ID 0
    }

    // 3. Getter and Setter methods for the variant
    public OstrichVariant getVariant() {
        return OstrichVariant.byId(this.entityData.get(DATA_VARIANT_ID));
    }

    public void setVariant(OstrichVariant variant) {
        this.entityData.set(DATA_VARIANT_ID, variant.getId());
    }

    // 4. Save variant choice to the world data file
    @Override
    public void addAdditionalSaveData(CompoundTag tag) {
        super.addAdditionalSaveData(tag);
        tag.putInt("Variant", this.getVariant().getId());
    }

    @Override
    public void readAdditionalSaveData(CompoundTag tag) {
        super.readAdditionalSaveData(tag);
        this.setVariant(OstrichVariant.byId(tag.getInt("Variant")));
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new PanicGoal(this, 1.25D));
        this.goalSelector.addGoal(2, new BreedGoal(this, 1.0D));
        this.goalSelector.addGoal(3, new TemptGoal(this, 1.1D, Ingredient.of(Items.WHEAT), false));
        this.goalSelector.addGoal(4, new FollowParentGoal(this, 1.1D));
        this.goalSelector.addGoal(5, new WaterAvoidingRandomStrollGoal(this, 1.0D));
        this.goalSelector.addGoal(6, new LookAtPlayerGoal(this, Player.class, 6.0F));
        this.goalSelector.addGoal(7, new RandomLookAroundGoal(this));
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController<>(this, "controller", 5, event -> {
            if (event.isMoving()) {
                return event.setAndContinue(RawAnimation.begin().thenLoop("Walk"));
            }
            return event.setAndContinue(RawAnimation.begin().thenLoop("idle"));
        }));
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return this.cache;
    }

    @Override
    public net.minecraft.world.entity.SpawnGroupData finalizeSpawn(
            net.minecraft.world.level.ServerLevelAccessor level, 
            net.minecraft.world.DifficultyInstance difficulty, 
            net.minecraft.world.entity.MobSpawnType spawnType, 
            @Nullable net.minecraft.world.entity.SpawnGroupData spawnGroupData) {
        
        spawnGroupData = super.finalizeSpawn(level, difficulty, spawnType, spawnGroupData);
        
        // Pick a random variant from your enum during world generation spawning
        OstrichVariant randomVariant = Util.getRandom(OstrichVariant.values(), this.random);
        this.setVariant(randomVariant);

        // Keeps your existing speed bonus logic intact
        double randomSpeedBonus = this.random.nextDouble() * 0.165D;
        this.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue(0.225D + randomSpeedBonus);
        
        return spawnGroupData;
    }

     public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 30.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.225D);
    }

    @Nullable
    @Override
    public AgeableMob getBreedOffspring(ServerLevel level, AgeableMob parent) {
        return ModEntities.OSTRICH_HORSE.get().create(level);
    }

    @Override
    public boolean isFood(ItemStack stack) {
        return stack.is(Items.WHEAT);
    } 
}