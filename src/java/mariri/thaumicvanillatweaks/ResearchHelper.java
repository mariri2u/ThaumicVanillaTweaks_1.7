package mariri.thaumicvanillatweaks;

import net.minecraft.item.ItemStack;
import thaumcraft.api.ThaumcraftApi;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.research.ResearchItem;
import thaumcraft.api.research.ResearchPage;

public class ResearchHelper {


    
    public static ResearchItem makeResearch(
    		String researchName, AspectList researchAspect, int col, int row, int complex,
    		boolean unlock, ItemStack icon, ResearchPage[] pages){
    	return 	makeResearch(researchName, researchAspect, col, row, complex, new String[]{}, unlock, icon, pages);
    }
    public static ResearchItem makeResearch(
    		String researchName, AspectList researchAspect, int col, int row, int complex,
    		String[] parent, boolean unlock, ItemStack icon, ResearchPage[] pages){
    	ResearchItem research = new ResearchItem(
        		researchName, ThaumicVanillaTweaks.RESEARCH_CATEGORY, researchAspect, col, row, complex, icon)
        		.setStub().	setRound().registerResearchItem();
    	if(unlock){ research.setAutoUnlock(); }
    	if(parent.length > 0){ research.setParents(parent); }
    	if(pages != null && pages.length > 0){
    		pages[0] = new ResearchPage("tc.research_page." + researchName + ".0");
    		research.setPages(pages);
    	}
    	return research;
    }
    
    public static void addResearchCrucible(
    		String researchName, AspectList researchAspect, int col, int row, int complex, boolean unlock,
    		ItemStack input, ItemStack output, AspectList recipeAspect){
    	addResearchCrucible(researchName, researchAspect, col, row, complex, new String[]{}, unlock,
    		input, output, recipeAspect);
    }
    public static void addResearchCrucible(
    		String researchName, AspectList researchAspect, int col, int row, int complex, String[] parent, boolean unlock,
    		ItemStack input, ItemStack output, AspectList recipeAspect){
    	ResearchPage[] pages = new ResearchPage[2];
    	ResearchItem research = makeResearch(researchName, researchAspect, col, row, complex, parent, unlock, output, pages);
    	pages[1] = new ResearchPage( ThaumcraftApi.addCrucibleRecipe(researchName, output, input, recipeAspect) );
    }
    
    
//    private void addResearchShapeless(
//    		String researchName, AspectList researchAspect, int col, int row, int complex, int iconmeta, boolean unlock,
//    		Block input, int inputcount, Block output, int outputcount, AspectList recipeAspect, int metacount){
//    	addResearchShapeless(researchName, researchAspect, col, row, complex, iconmeta, new String[]{}, unlock, input, inputcount, output, outputcount, recipeAspect, metacount);
//    }
//    private void addResearchShapeless(
//    		String researchName, AspectList researchAspect, int col, int row, int complex, int iconmeta, String[] parent, boolean unlock,
//    		Block input, int inputcount, Block output, int outputcount, AspectList recipeAspect, int metacount){
//    	ResearchItem research = new ResearchItem(
//            researchName, RESEARCH_CATEGORY, researchAspect, col, row, complex, new ItemStack(output, 1, iconmeta))
//    		.setStub().setRound().registerResearchItem();
//    	if(unlock){ research.setAutoUnlock(); }
//    	if(parent.length > 0){ research.setParents(parent); }
//    	ResearchPage[] pages = new ResearchPage[1 + metacount];
//    	pages[0] = new ResearchPage("tc.research_page." + researchName + ".0");
//    	research.setPages(pages);
//    	for(int i = 0; i < metacount; i++){
//    		Object[] inputobject = new Object[inputcount];
//    		for(int j = 0; j < inputcount; j++){
//    			inputobject[j] = new ItemStack(
//    					(input == Blocks.log && i < 4) ? input : Blocks.log2,
//    					1,
//    					(input == Blocks.log && i < 4) ? i : i - 4);	
//    		}
//    		pages[1 + i] = new ResearchPage(
//    			ThaumcraftApi.addShapelessArcaneCraftingRecipe(researchName, new ItemStack(output, outputcount, i), recipeAspect, inputobject)	);
//    	}
//    }
    public static void addResearchShapeless(
    		String researchName, AspectList researchAspect, int col, int row, int complex, boolean unlock,
    		Object[] input, ItemStack output, AspectList recipeAspect){
    	addResearchShapeless(researchName, researchAspect, col, row, complex, new String[]{}, unlock, input, output, recipeAspect);
    }
    public static void addResearchShapeless(
    		String researchName, AspectList researchAspect, int col, int row, int complex, String[] parent, boolean unlock,
    		Object[] input, ItemStack output, AspectList recipeAspect){
    	ResearchPage[] pages = new ResearchPage[2];
    	ResearchItem research = makeResearch(researchName, researchAspect, col, row, complex, parent, unlock, output, pages);
    	pages[1] = new ResearchPage(
    			ThaumcraftApi.addShapelessArcaneCraftingRecipe(researchName, output, recipeAspect, input ) );
    }
    
    public static void addResearchShaped(
		String researchName, AspectList researchAspect, int col, int row, int complex, boolean unlock,
    		Object[] input, ItemStack output, AspectList recipeAspect){
    	addResearchShaped(researchName, researchAspect, col, row, complex, new String[]{}, unlock,
    		input, output, recipeAspect);
    }
    public static void addResearchShaped(
    		String researchName, AspectList researchAspect, int col, int row, int complex, String[] parent, boolean unlock,
    		Object[] input, ItemStack output, AspectList recipeAspect){
    	
    	ResearchPage[] pages = new ResearchPage[2];
    	ResearchItem research = makeResearch(researchName, researchAspect, col, row, complex, parent, unlock, output, pages);
    	pages[1] = new ResearchPage(
    			ThaumcraftApi.addArcaneCraftingRecipe(researchName, output, recipeAspect, input ) );
    }
    
    public static void addResearchInfusion(
    		String researchName, AspectList researchAspect, int col, int row, int complex, boolean unlock,
    		ItemStack central, ItemStack[] input, ItemStack output, AspectList recipeAspect, int instability){
    	addResearchInfusion(researchName, researchAspect, col, row, complex, new String[]{}, unlock, central, input, output, recipeAspect, instability);
    }
    public static void addResearchInfusion(
    		String researchName, AspectList researchAspect, int col, int row, int complex, String[] parent, boolean unlock,
    		ItemStack central, ItemStack[] input, ItemStack output, AspectList recipeAspect, int instability){
    	ResearchPage[] pages = new ResearchPage[2];
    	ResearchItem research = makeResearch(researchName, researchAspect, col, row, complex, parent, unlock, output, pages);
    	pages[1] = new ResearchPage(
    			ThaumcraftApi.addInfusionCraftingRecipe(researchName, output, instability, recipeAspect, central, input ) );
    }
    
//    private void addResearchNoRecipe(
//    		String researchName, AspectList researchAspect, int col, int row, int complex, boolean unlock, ItemStack icon){
//    	addResearchNoRecipe(researchName, researchAspect, col, row, complex, new String[]{}, unlock, icon);
//    }
//    private void addResearchNoRecipe(
//    		String researchName, AspectList researchAspect, int col, int row, int complex, String[] parent, boolean unlock, ItemStack icon){
//    	
//    	ResearchItem research = new ResearchItem(
//        	researchName, RESEARCH_CATEGORY, researchAspect, col, row, complex, icon)
//    		.setStub().setRound().registerResearchItem();
//    	if(unlock){ research.setAutoUnlock(); }
//    	if(parent.length > 0){ research.setParents(parent); }
//    	ResearchPage[] pages = new ResearchPage[1];
//    	pages[0] = new ResearchPage("tc.research_page." + researchName + ".0");
//    	research.setPages(pages);
//    }	
}
