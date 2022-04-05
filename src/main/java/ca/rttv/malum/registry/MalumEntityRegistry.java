package ca.rttv.malum.registry;

import ca.rttv.malum.entity.boomerang.ScytheBoomerangEntity;
import ca.rttv.malum.entity.spirit.MirrorItemEntity;
import ca.rttv.malum.util.helper.DataHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import java.util.LinkedHashMap;
import java.util.Map;

import static ca.rttv.malum.Malum.MODID;

public class MalumEntityRegistry {
    private static final Map<EntityType<?>, Identifier> ENTITY_TYPES = new LinkedHashMap<>();
    // entities
    public static final EntityType<MirrorItemEntity> MIRROR_ITEM = registerEntity("mirror_item", EntityType.Builder.<MirrorItemEntity>create((e, w)->new MirrorItemEntity(w), SpawnGroup.MISC).setDimensions(0.5F, 0.5F).maxTrackingRange(10).build(DataHelper.prefix("mirror_item").toString()));
    public static final EntityType<ScytheBoomerangEntity> SCYTHE_BOOMERANG = registerEntity("scythe_boomerang", EntityType.Builder.<ScytheBoomerangEntity>create((e, w)->new ScytheBoomerangEntity(w), SpawnGroup.MISC).setDimensions(2.5F, 0.75F).maxTrackingRange(10).build(DataHelper.prefix("scythe_boomerang").toString()));

    private static <T extends Entity> EntityType<T> registerEntity(String id, EntityType<T> type) {
        ENTITY_TYPES.put(type, new Identifier(MODID, id));
        return type;
    }

//    private static <T extends LivingEntity> EntityType<T> registerEntity(String id, DefaultAttributeContainer.Builder attributes, EntityType<T> type) {
//        FabricDefaultAttributeRegistry.register(type, attributes);
//        ENTITY_TYPES.put(type, new Identifier(MODID, id));
//        return type;
//    }

    static void init() {
        ENTITY_TYPES.forEach((entityType, id) -> Registry.register(Registry.ENTITY_TYPE, id, entityType));
    }
}
