package slimeknights.tconstruct.world.village.loot;

import net.minecraft.item.Item;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.*;
import net.minecraft.world.storage.loot.conditions.LootCondition;
import net.minecraft.world.storage.loot.functions.LootFunction;
import net.minecraft.world.storage.loot.functions.SetCount;
import net.minecraft.world.storage.loot.functions.SetNBT;
import net.minecraftforge.event.LootTableLoadEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import slimeknights.tconstruct.TConstruct;
import slimeknights.tconstruct.library.TinkerRegistry;
import slimeknights.tconstruct.library.materials.Material;
import slimeknights.tconstruct.library.tools.Pattern;
import slimeknights.tconstruct.library.utils.Tags;
import slimeknights.tconstruct.tools.TinkerMaterials;
import slimeknights.tconstruct.tools.TinkerTools;

import java.util.ArrayList;
import java.util.List;

public class VillageLoot {

    public static final ResourceLocation WORKSHOP_PATTERNS = LootTableList.register(
            new ResourceLocation(TConstruct.modID, "chests/workshop_patterns")
    );

    public static final ResourceLocation WORKSHOP_PARTS = LootTableList.register(
            new ResourceLocation(TConstruct.modID, "chests/workshop_parts")
    );

    private final LootPool patternsPool;
    private final LootPool partsPool;

    public VillageLoot() {
        this.patternsPool = makePatternsPool();
        this.partsPool = makePartsPool();
    }

    private LootPool makePatternsPool() {

        List<LootEntry> entries = new ArrayList<>();

        for(Item pattern : TinkerRegistry.getPatternItems()) {

            String patternIdentifier = pattern.getRegistryName().toString();

            NBTTagCompound tag = new NBTTagCompound();
            tag.setString(Pattern.TAG_PARTTYPE, patternIdentifier);

            entries.add(new LootEntryItem(
                    TinkerTools.pattern,
                    20,
                    0,
                    new LootFunction[] {
                            new SetCount(
                                    new LootCondition[0],
                                    new RandomValueRange(1, 3)
                            ),
                            new SetNBT(
                                    new LootCondition[0],
                                    tag
                            )
                    },
                    new LootCondition[0],
                    patternIdentifier.replace(':', '_')
            ));
        }

        return new LootPool(
                entries.toArray(new LootEntry[0]),
                new LootCondition[0],
                new RandomValueRange(5, 30),
                new RandomValueRange(0, 0),
                "workshop_patterns"
        );

    }

    private LootPool makePartsPool() {

        List<LootEntry> entries = new ArrayList<>();

        Item[] partTypes = {
                TinkerTools.pickHead,
                TinkerTools.shovelHead,
                TinkerTools.axeHead,
                TinkerTools.kamaHead,
                TinkerTools.binding,
                TinkerTools.swordBlade,
                TinkerTools.wideGuard,
                TinkerTools.handGuard,
                TinkerTools.crossGuard,
                TinkerTools.knifeBlade,
                TinkerTools.panHead,
                TinkerTools.signHead
        };

        Material[] materialTypes = {
                TinkerMaterials.wood,
                TinkerMaterials.stone,
                TinkerMaterials.iron,
                TinkerMaterials.flint,
                TinkerMaterials.cactus,
                TinkerMaterials.bone,
                TinkerMaterials.obsidian,
                TinkerMaterials.netherrack,
                TinkerMaterials.slime,
                TinkerMaterials.paper,
                TinkerMaterials.prismarine,
                TinkerMaterials.blueslime
        };

        for(Item part : partTypes) {

            for(Material material : materialTypes) {

                NBTTagCompound tag = new NBTTagCompound();
                tag.setString(Tags.PART_MATERIAL, material.getIdentifier());

                entries.add(new LootEntryItem(
                        part,
                        15,
                        0,
                        new LootFunction[] {
                                new SetNBT(
                                        new LootCondition[0],
                                        tag
                                )
                        },
                        new LootCondition[0],
                        part.getRegistryName().getResourcePath() + "_" + material.getIdentifier()
                ));

            }

        }

        return new LootPool(
                entries.toArray(new LootEntry[0]),
                new LootCondition[0],
                new RandomValueRange(3, 27),
                new RandomValueRange(0, 0),
                "workshop_parts"
        );

    }

    @SubscribeEvent
    public void onLootTableLoad(LootTableLoadEvent event) {

        ResourceLocation name = event.getName();
        LootTable table = event.getTable();

        if(name.equals(WORKSHOP_PATTERNS)) {
            table.addPool(patternsPool);
        } else if(name.equals(WORKSHOP_PARTS)) {
            table.addPool(partsPool);
        }

    }

}
