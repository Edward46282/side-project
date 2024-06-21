import java.io.File;  
import java.util.List;
import java.io.FileNotFoundException;  
import java.util.Scanner; 
import java.util.ArrayList;

@SuppressWarnings("unchecked")
public class MenuReader{
	public static ArrayList Dessert = new ArrayList();
	public static ArrayList Breakfast = new ArrayList();
	public static ArrayList Lunch = new ArrayList();
	public static ArrayList Dinner = new ArrayList();
	
	
	public MenuReader(){
		int counter = 0;
		try{
			File menuread = new File("menu_list.txt");
			Scanner myReader = new Scanner(menuread);
			String data = myReader.nextLine();
			
			while (myReader.hasNextLine()){
				if(!(data.contains("/"))&&(!data.contains("##"))&&(!data.isBlank())){
					String[] tmp = data.split(":");
					String menuName = tmp[0];
					String ingre = tmp[1];
					
					ArrayList singleMenu = new ArrayList();
					singleMenu.add(menuName);
					String[] ingredients = ingre.strip().split(",");
					for(int i=0; i<ingredients.length; i++){
						singleMenu.add(ingredients[i].toLowerCase().strip());
					} //finished with putting a singlemenu 
					
					if(counter == 0){//add it to dessert
						Dessert.add(singleMenu);
					} else if (counter == 1){//add it to Breakfast
						Breakfast.add(singleMenu);
					} else if (counter == 2){//add it to Lunch
						Lunch.add(singleMenu);
					} else{//to Dinner
						Dinner.add(singleMenu);
					}

					
				}
				if(data.contains("###")){//switching menu category
					counter++;
				}
				data = myReader.nextLine();
			}
		}
		catch (FileNotFoundException e){
			System.out.println("There is no such file. Please make sure the name of the menulist text file is 'menu_list.txt'.");
		}
	}
	
	public ArrayList finder(String s, ArrayList<ArrayList<String>> k){ //function for finding ingredients
		ArrayList result = new ArrayList();
		s = s.toLowerCase().strip();
		for(int i=0;i<k.size();i++){
			List<String> single = k.get(i);
			for(int j=1;j<single.size();j++){//do not include the menu name. Only search ingredient.
				String menuN = single.get(j);
				menuN = menuN.toLowerCase().strip();
				if((menuN.contains(s))||(s.contains(menuN))){
					boolean flager = lengthOfStrings(s,menuN);
					if(flager == true){
						result.add(single);
					}
				}
			}
		}
		return result;
	}
	
	public static boolean lengthOfStrings(String one, String two){
		if((one.length())-(two.length()) < 0){
			if((two.length())-(one.length()) < 2){
				return true;
			}
			return false;
		} else{
			if((one.length())-(two.length()) < 2){
				return true;
			}
			return false;
		}
	}
	
}