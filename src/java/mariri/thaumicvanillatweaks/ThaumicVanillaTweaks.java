package mariri.thaumicvanillatweaks;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import thaumcraft.api.ThaumcraftApi;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.research.ResearchCategories;
import thaumcraft.api.research.ResearchItem;
import thaumcraft.api.research.ResearchPage;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = ThaumicVanillaTweaks.MODID, version = ThaumicVanillaTweaks.VERSION, dependencies = ThaumicVanillaTweaks.DEPENDENCIES )
public class ThaumicVanillaTweaks {
    public static final String MODID = "ThaumicVanillaTweaks";
    public static final String VERSION = "1.7.10-0.1";
    public static final String DEPENDENCIES = "required-after:Thaumcraft;after:McAssistant";
//    public static final String DEPENDENCIES = "after:Thaumcraft";
    
    public static final String RESEARCH_CATEGORY = "THAUMIC_VANILLA_TWEAKS";
    
    public static final String RESEARCH_PLANK = "PLANK";
    public static final String RESEARCH_PLANK_PLUS = "PLANK_PLUS";
    public static final String RESEARCH_CHARCOAL = "CHARCOAL";
    public static final String RESEARCH_STICK = "STICK";
    
    public static final String RESEARCH_FLINT_AND_STEEL = "FLINT_AND_STEEL";
//    public static final String RESEARCH_DIAMOND_PICKAXE = "DIAMOND_PICKAXE";
    public static final String RESEARCH_BUCKET = "BUCKET";
//    public static final String RESEARCH_COMPASS = "COMPASS";
//    public static final String RESEARCH_REDSTONE_TORCH = "REDSTONE_TORCH";
    public static final String RESEARCH_BREAD = "BREAD";
    public static final String RESEARCH_ENDER_EYE = "ENDER_EYE";
//    public static final String RESEARCH_BOW = "BOW";
//    public static final String RESEARCH_LADDER = "RADDER";
//    public static final String RESEARCH_ENCHANT_TABLE = "ENCHANT_TABLE";
//    public static final String RESEARCH_IRON_DOOR = "IRON_DOOR";
//    public static final String RESEARCH_FENCE_GATE = "FENCE_GATE";
//    public static final String RESEARCH_TRAP_DOOR = "TRAP_DOOR";


    
    public static final String RESEARCH_SET_RESPAWN = "SET_RESPAWN";
    public static final String RESEARCH_SLEEP = "SLEEP";
    
    public static final String RESEARCH_WAYPOINT = "WAYPOINT";
    public static final String RESEARCH_SPAWN_CHECKER = "SPAWN_CHECKER";
//    public static final String RESEARCH_PICKUP_WIDELY = "PICKUP_WIDELY";
//    public static final String RESEARCH_DAMAGE_INDICATOR = "DAMAGE_INDICATOR";
    
    public static boolean WOOD_TWEAKS;
    public static boolean BED_TWEAKS;
    public static CreativeTabs creativeTab;
    
    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        Configuration config = new Configuration(event.getSuggestedConfigurationFile());
        config.load();
        
        WOOD_TWEAKS = config.get(Configuration.CATEGORY_GENERAL, "woodTweaks", false).getBoolean(false);
        BED_TWEAKS = config.get(Configuration.CATEGORY_GENERAL, "bedTweaks", true).getBoolean(true);
        
//        PlayerClickHandler.BEDTWEAKS_SET_RESPAWN_MESSAGE = config.get(Configuration.CATEGORY_GENERAL, "bedtweaksSetRespawnMessage", "Set Respawn!!").getString();
//        PlayerClickHandler.BEDTWEAKS_NO_SLEEP_MESSAGE = config.get(Configuration.CATEGORY_GENERAL, "bedtweaksNoSleepMessage", "You can't sleep!!").getString();
      
        config.save();
    }
    
    @EventHandler
    public void init(FMLInitializationEvent event){
    	
//    	creativeTab = new CreativeTabBrewing("Infusion Brewing");
    }
    
    @EventHandler
    public void postInit(FMLPostInitializationEvent event){
		ResourceLocation background = new ResourceLocation("thaumcraft", "textures/gui/gui_researchback.png");
		ResourceLocation icon = new ResourceLocation("minecraft", "textures/items/charcoal.png");
		ResearchCategories.registerCategory(RESEARCH_CATEGORY, icon, background);
		
    	removeVanillaRecipe();
    	
    		
		ItemStack[] logs = new ItemStack[] {
				new ItemStack(Blocks.log, 1, 0), new ItemStack(Blocks.log, 1, 1),
				new ItemStack(Blocks.log, 1, 2), new ItemStack(Blocks.log, 1, 3),
				new ItemStack(Blocks.log2, 1, 0), new ItemStack(Blocks.log2, 1, 1)
		};
		ItemStack[] planks = new ItemStack[] {
				new ItemStack(Blocks.planks, 1, 0), new ItemStack(Blocks.planks, 1, 1),
				new ItemStack(Blocks.planks, 1, 2), new ItemStack(Blocks.planks, 1, 3),
				new ItemStack(Blocks.planks, 1, 4), new ItemStack(Blocks.planks, 1, 5)
		};
		
		ResearchPage[] pagePlanks = new ResearchPage[7];
		ResearchItem researchPlanks = ResearchHelper.makeResearch(RESEARCH_PLANK,
				new AspectList().add(Aspect.EARTH, 1).add(Aspect.TREE, 1),
				-4, 0, 0, false, planks[2], pagePlanks);
		
		ResearchPage[] pagePlanksPlus = new ResearchPage[7];
		ResearchItem researchPlanksPlus = ResearchHelper.makeResearch(RESEARCH_PLANK_PLUS,
				new AspectList().add(Aspect.EARTH, 1).add(Aspect.TREE, 1).add(Aspect.GREED, 1),
				-2, 1, 2, new String[]{ RESEARCH_PLANK }, false, planks[5], pagePlanksPlus);
    	
		ResearchPage[] pageCharcoal = new ResearchPage[7];
		ResearchItem researchCharcoal = ResearchHelper.makeResearch(RESEARCH_CHARCOAL,
    			new AspectList().add(Aspect.FIRE, 1).add(Aspect.ENERGY, 1),
    			0, 0, 0, false, new ItemStack(Items.coal, 1, 1), pageCharcoal);

		
    	for(int i = 0; i < 6; i++){
			ItemStack output;
			output = planks[i].copy();
			output.stackSize = WOOD_TWEAKS ? 8 : 10;
    		pagePlanks[1 + i] =
    				new ResearchPage(ThaumcraftApi.addShapelessArcaneCraftingRecipe(
    						RESEARCH_PLANK, output, new AspectList().add(Aspect.AIR, 1), new Object[] { logs[i] , logs[i] }) );
    		output = planks[i].copy();
    		output.stackSize = 6;
    		pagePlanksPlus[1 + i] =
    				new ResearchPage(ThaumcraftApi.addCrucibleRecipe(
    						RESEARCH_PLANK_PLUS, output, (Object)logs[i],
    						new AspectList().add(Aspect.AIR, 2).add(Aspect.PLANT, 1) ) );
    		
    		pageCharcoal[i + 1] =
    				new ResearchPage(ThaumcraftApi.addCrucibleRecipe(
    						RESEARCH_CHARCOAL, new ItemStack(Items.coal, 1, 1), (Object)logs[i],
    						new AspectList().add(Aspect.FIRE, 1) ) );
    		
//	    	addResearchShapeless(
//	    			RESEARCH_PLANK, new AspectList().add(Aspect.EARTH, 1).add(Aspect.TREE, 1),
//	    			-2, 0, 0, 1, false,
//	    			Blocks.log, 2, Blocks.planks, 8, new AspectList(), 6);
//	    	addResearchShapeless(
//	    			RESEARCH_PLANK_PLUS, new AspectList().add(Aspect.EARTH, 1).add(Aspect.TREE, 1).add(Aspect.GREED, 1),
//	    			-2, 2, 2, 2, new String[]{RESEARCH_PLANK}, false,
//	    			Blocks.log, 3, Blocks.planks, 18 , new AspectList().add(Aspect.EARTH, 2), 6);
    	}
    	
       	ResearchHelper.addResearchShaped(RESEARCH_STICK,
    			new AspectList().add(Aspect.TOOL, 1).add(Aspect.TREE, 1),
     			-2, -1, 0, new String[]{RESEARCH_PLANK}, false,
     			new Object[] { "X", "X", "X", 'X', Blocks.planks }, new ItemStack(Items.stick, WOOD_TWEAKS ? 6 : 9),
     			new AspectList().add(Aspect.AIR, 1));
    	
    	ResearchHelper.addResearchShaped(RESEARCH_FLINT_AND_STEEL,
    			new AspectList().add(Aspect.FIRE, 1).add(Aspect.METAL, 1).add(Aspect.TRAP, 1),
    			2, 1, 2, new String[]{RESEARCH_CHARCOAL}, false,
    			new Object[] { "I ", " F", 'I', Items.iron_ingot, 'F', Items.flint }, new ItemStack(Items.flint_and_steel),
    			new AspectList().add(Aspect.ENTROPY, 4));
    	ResearchHelper.addResearchCrucible(RESEARCH_BREAD,
    			new AspectList().add(Aspect.EARTH, 1).add(Aspect.CROP, 1).add(Aspect.MAN, 1),
    			2, -1, 0, new String[]{RESEARCH_CHARCOAL}, false,
    			new ItemStack(Items.wheat), new ItemStack(Items.bread), new AspectList().add(Aspect.FIRE, 2));
    	ResearchHelper.addResearchShaped(RESEARCH_BUCKET,
    			new AspectList().add(Aspect.WATER, 1).add(Aspect.METAL, 1).add(Aspect.MOTION, 1),
    			2, 3, 1, false,
    			new Object[] { "I I", " I ", 'I', "ingotThaumium" }, new ItemStack(Items.bucket),
    			new AspectList().add(Aspect.ORDER, 4));
    	ResearchHelper.addResearchInfusion(RESEARCH_ENDER_EYE,
    			new AspectList().add(Aspect.SENSES, 1).add(Aspect.TRAVEL, 1).add(Aspect.FIRE, 1).add(Aspect.VOID, 1),
    			4, 2, 2, new String[]{ RESEARCH_BUCKET, RESEARCH_FLINT_AND_STEEL }, false,
    			new ItemStack(Items.ender_pearl),
    			new ItemStack[] { new ItemStack(Items.blaze_powder), new ItemStack(Items.blaze_powder) },
    			new ItemStack(Items.ender_eye),
    			new AspectList().add(Aspect.SENSES, 4).add(Aspect.TRAVEL, 2).add(Aspect.ENERGY, 6), 2);
    	
    	ResearchHelper.makeResearch(RESEARCH_SPAWN_CHECKER,
    			new AspectList().add(Aspect.VOID, 1).add(Aspect.LIGHT, 1).add(Aspect.ORDER, 1),
    			4, 0, 2, new String[]{RESEARCH_CHARCOAL}, false, new ItemStack(Blocks.torch), new ResearchPage[1]);
    	ResearchHelper.makeResearch(RESEARCH_WAYPOINT,
    			new AspectList().add(Aspect.ORDER, 1).add(Aspect.TRAVEL, 1).add(Aspect.MOTION, 1),
    			4, -2, 1, false, new ItemStack(Items.compass), new ResearchPage[1]);  
//    	addResearchNoRecipe(RESEARCH_WAYPOINT, new AspectList().add(Aspect.ORDER, 1).add(Aspect.TRAVEL, 1).add(Aspect.MOTION, 1),
//    			0, 4, 1, false, new ItemStack(Items.map));
//    	addResearchNoRecipe(RESEARCH_SPAWN_CHECKER, new AspectList().add(Aspect.VOID, 1).add(Aspect.LIGHT, 1).add(Aspect.ORDER, 1),
//    			2, 2, 2, new String[]{RESEARCH_CHARCOAL}, false, new ItemStack(Blocks.torch));
    	
		ResearchHelper.makeResearch(RESEARCH_SET_RESPAWN,
				new AspectList().add(Aspect.VOID, 1).add(Aspect.ORDER, 1).add(Aspect.HEAL, 1),
    			0, -2, 2, false, new ItemStack(Items.bed), new ResearchPage[1]);
		ResearchHelper.makeResearch(RESEARCH_SLEEP,
				new AspectList().add(Aspect.LIGHT, 1).add(Aspect.DARKNESS, 1).add(Aspect.MAN, 1),
    			2, -3, 2, new String[]{ RESEARCH_SET_RESPAWN }, false, new ItemStack(Items.clock), new ResearchPage[1]);
    		
//    	addResearchNoRecipe(RESEARCH_SET_RESPAWN, new AspectList().add(Aspect.VOID, 1).add(Aspect.ORDER, 1).add(Aspect.HEAL, 1),
//	    		2, 4, 2, false, new ItemStack(Items.bed));
//	    addResearchNoRecipe(RESEAR		CH_SLEEP, new AspectList().add(Aspect.LIGHT, 1).add(Aspect.DARKNESS, 1).add(Aspect.MAN, 1),
//	    		4, 4, 2, new String[]{ RESEARCH_SET_RESPAWN }, false, new ItemStack(Items.clock));
	    	
        if(BED_TWEAKS){
        	MinecraftForge.EVENT_BUS.register(new PlayerClickHandler());
        	
        	if(Loader.isModLoaded("McAssistant")){
        		mariri.mcassistant.handler.PlayerClickHandler.BEDASSIST_ENABLE = false;
        	}
    	}

//    	addResearchShaped(
//			RESEARCH_DIAMOND_PICKAXE, new AspectList().add(Aspect.EARTH, 1).add(Aspect.TREE, 1), 4, 6, 0, true,
//			new Object[] { "DDD", " S ", " S ", 'D', Items.diamond, 'S', Items.stick }, new ItemStack(Items.diamond_pickaxe), new AspectList());
//    	addResearchShaped(
//    			RESEARCH_COMPASS, new AspectList().add(Aspect.EARTH, 1).add(Aspect.TREE, 1), 2, 4, 0, true,
//    			new Object[] { " I ", "IRI", " I ", 'I', Items.iron_ingot, 'R', Items.redstone }, new ItemStack(Items.compass), new AspectList());
//    	addResearchShaped(
//    			RESEARCH_REDSTONE_TORCH, new AspectList().add(Aspect.EARTH, 1).add(Aspect.TREE, 1), 2, 4, 0, true,
//    			new Object[] { "R", "S", 'R', Items.redstone, 'S', Items.stick }, new ItemStack(Blocks.redstone_torch), new AspectList());
//    	addResearchShaped(
//    			RESEARCH_LADDER, new AspectList().add(Aspect.EARTH, 1).add(Aspect.TREE, 1), 2, 10, 0, true,
//    			new Object[] { "S S", "SSS", "S S", 'S', Items.stick }, new ItemStack(Blocks.ladder), new AspectList());
//    	addResearchShaped(
//    			RESEARCH_ENCHANT_TABLE, new AspectList().add(Aspect.EARTH, 1).add(Aspect.TREE, 1), 2, 12, 0, true,
//    			new Object[] { " B ", "DOD", "OOO", 'B', Items.book, 'D', Items.diamond, 'O', Blocks.obsidian }, new ItemStack(Blocks.enchanting_table), new AspectList());
//    	addResearchShaped(
//    			RESEARCH_IRON_DOOR, new AspectList().add(Aspect.EARTH, 1).add(Aspect.TREE, 1), 2, 14, 0, true,
//    			new Object[] { "II", "II", "II", 'I', Items.iron_ingot}, new ItemStack(Blocks.iron_door), new AspectList());
//    	addResearchShaped(
//    			RESEARCH_FENCE_GATE, new AspectList().add(Aspect.EARTH, 1).add(Aspect.TREE, 1), 2, 16, 0, true,
//    			new Object[] { "SPS", "SPS", 'S', Items.stick, 'P', Blocks.planks }, new ItemStack(Blocks.fence_gate), new AspectList());
//    	addResearchShaped(
//    			RESEARCH_TRAP_DOOR, new AspectList().add(Aspect.EARTH, 1).add(Aspect.TREE, 1), 2, 18, 0, true,
//    			new Object[] { "PPP", "PPP", 'P', Blocks.planks }, new ItemStack(Blocks.trapdoor), new AspectList());
    	
//    	addResearchNoRecipe(RESEARCH_PICKUP_WIDELY, new AspectList().add(Aspect.EARTH, 1), 16, 10, 0, false, new ItemStack(Items.gold_nugget));
//    	addResearchNoRecipe(RESEARCH_DAMAGE_INDICATOR, new AspectList().add(Aspect.EARTH, 1), 18, 10, 0, false, new ItemStack(Items.skull));
    }	
    
    private void removeVanillaRecipe(){
        List recipeList = CraftingManager.getInstance().getRecipeList();
        for(int i = 0; i < recipeList.size(); i++){
        	IRecipe recipe = (IRecipe)recipeList.get(i);
        	try{
        		Item iout = recipe.getRecipeOutput().getItem();
        		Block bout = Blocks.air;
        		if(iout instanceof ItemBlock){
        			bout = ((ItemBlock)iout).field_150939_a;
        		}
        		int meta = recipe.getRecipeOutput().getItemDamage();
        		if(WOOD_TWEAKS && (iout == Items.stick || bout == Blocks.planks) ){
        			recipe.getRecipeOutput().stackSize = 3;
        		}else if(
        				iout == Items.flint_and_steel ||
//        				iout == Items.diamond_pickaxe ||
        				iout == Items.bucket ||
//        				iout == Items.minecart ||
//        				iout == Items.shears ||
//        				iout == Items.compass ||
//        				bout == Blocks.redstone_torch ||
        				iout == Items.bread ||
        				iout == Items.ender_eye
//        				iout == Items.bow ||
//        				bout == Blocks.ladder ||
//        				bout == Blocks.enchanting_table ||
//        				bout == Blocks.iron_door ||
//        				bout == Blocks.trapdoor ||
//        				bout == Blocks.fence_gate
        				){
        			recipeList.remove(i);
        		}
        	}catch(NullPointerException e){}
        }
        Map furnaceList = FurnaceRecipes.smelting().getSmeltingList();
//        FurnaceRecipes furnaceRecipes = FurnaceRecipes.smelting();
//        ItemStack out = furnaceRecipes.getSmeltingResult(new ItemStack(Blocks.log));
//        furnaceRecipes.getSmeltingList().
        for(Iterator iter = furnaceList.keySet().iterator(); iter.hasNext(); ){
        	ItemStack output = (ItemStack)furnaceList.get(iter.next());
        	if(output.getItem() == Items.coal && output.getItemDamage() == 1){
                iter.remove();
        	}
//        	ItemStack input = (ItemStack)in;
//        	ItemStack output = (ItemStack)furnaceList.get(in);
//    		Item iout = output.getItem();
//    		Block bout = Blocks.air;
//    		if(iout instanceof ItemBlock){
//    			bout = ((ItemBlock)iout).field_150939_a;
//    		}
//    		int meta = input.getItemDamage();
//    		if(iout == Items.coal && meta == 1){
//    			furnaceList.remove(input);
//    		}
        }
    }
}
