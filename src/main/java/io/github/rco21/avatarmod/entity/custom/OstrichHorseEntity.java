package io.github.rco21.avatarmod.entity.custom;

import io.github.rco21.avatarmod.entity.ModEntities;
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
    
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);

    public OstrichHorseEntity(EntityType<? extends Animal> entityType, Level level) {
        super(entityType, level);
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