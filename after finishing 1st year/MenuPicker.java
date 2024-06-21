import java.util.Scanner; 
import java.util.ArrayList;
import java.util.List;

public class MenuPicker{
	@SuppressWarnings("unchecked")
	public static void main(String[] args){
		String[] collection;
		boolean flag = true;
		
		System.out.println("Hello! Welcome to the menu picker.\nLoading...");
		MenuReader s = new MenuReader();
		System.out.println("Successfully loaded the menus.");
		
		while(flag == true){ //for starting all over again.
			boolean internalFlag = true;
			System.out.println("\nWould you like to get a menu pick based on the ingredients?\n");
			Scanner myObj = new Scanner(System.in);
			System.out.println("Y for yes and N for no.");
			
			String answer1 = myObj.nextLine();
			if (!((answer1.toLowerCase().equals("n"))||(answer1.toLowerCase().equals("y")))){
				while (!((answer1.toLowerCase().equals("n"))||(answer1.toLowerCase().equals("y")))){
					System.out.println("Invalid input. Please type it again: ");
					answer1 = myObj.nextLine();
				}
			}
			
			if (answer1.toLowerCase().equals("n")){ //for choosing the menu randomly
				System.out.println("You selected to choose the menu completely randomly.");
				System.out.println("You could choose a menu for breakfast, lunch, dinner, or even dessert! What would you like to choose?");
				System.out.println("You can type B for breakfast, L for lunch, Di for dinner, De for dessert, or just write the full name.\n");
				String timeAnswer = myObj.nextLine();
				timeAnswer = timeAnswer.toLowerCase();
				
				System.out.println();
				
				while (!(((timeAnswer.equals("b"))||(timeAnswer.equals("breakfast")))||(timeAnswer.equals("l"))||(timeAnswer.equals("lunch"))||
					((timeAnswer.equals("di"))||(timeAnswer.equals("dinner")))||((timeAnswer.equals("de"))||(timeAnswer.equals("dessert"))))){
						System.out.println("Invalid input. Please type it again: ");
						timeAnswer = myObj.nextLine();
						timeAnswer = timeAnswer.toLowerCase();
					}
				if((timeAnswer.equals("b"))||(timeAnswer.equals("breakfast"))){
					//breakfast
					ArrayList m = s.Breakfast;
					while(internalFlag == true){
						collection = RandomPicker.pick(m);
						System.out.println("You picked for a breakfast menu. Our pick for today's menu is " + collection[0] + ".");
						System.out.println("For general recipe, you will need " + collection[1] + ", " + collection[2] + ", and " + collection[3] + " to make " + collection[0]);
						System.out.println("\nUnsatisified with the choice? would you like to get another pick? Y/N\n");
						String unsatisified = myObj.nextLine();
						unsatisified = unsatisified.toLowerCase().strip();
						while(!(unsatisified.equals("y")||unsatisified.equals("n"))){
							System.out.println("Invalid input. Type again: ");
							unsatisified = myObj.nextLine();
							unsatisified = unsatisified.toLowerCase().strip();
						}
							
						if(unsatisified.equals("y")){
							internalFlag = true;
						} else if(unsatisified.equals("n")){
							internalFlag = false;
						}
							
					}
				} else if((timeAnswer.equals("l"))||(timeAnswer.equals("lunch"))){
					//lunch
					ArrayList m = s.Lunch;
					while(internalFlag == true){
						System.out.println(m.size()); //bug catch
						collection = RandomPicker.pick(m);
						System.out.println("You picked for a lunch menu. Our pick for today's menu is " + collection[0] + ".");
						System.out.println("For general recipe, you will need " + collection[1] + ", " + collection[2] + ", and " + collection[3] + " to make " + collection[0]);
						System.out.println("\nUnsatisified with the choice? would you like to get another pick? Y/N\n");
						String unsatisified = myObj.nextLine();
						unsatisified = unsatisified.toLowerCase().strip();
						while(!(unsatisified.equals("y")||unsatisified.equals("n"))){
							System.out.println("Invalid input. Type again: ");
							unsatisified = myObj.nextLine();
							unsatisified = unsatisified.toLowerCase().strip();
						}
							
						if(unsatisified.equals("y")){
							internalFlag = true;
						} else if(unsatisified.equals("n")){
							internalFlag = false;
						}
					}
				} else if((timeAnswer.equals("di"))||(timeAnswer.equals("dinner"))){
					//dinner
					ArrayList m = s.Dinner;
					while(internalFlag == true){
						collection = RandomPicker.pick(m);
						System.out.println("You picked for a dinner menu. Our pick for today's menu is " + collection[0] + ".");
						System.out.println("For general recipe, you will need " + collection[1] + ", " + collection[2] + ", and " + collection[3] + " to make " + collection[0]);
						System.out.println("\nUnsatisified with the choice? would you like to get another pick? Y/N\n");
						String unsatisified = myObj.nextLine();
						unsatisified = unsatisified.toLowerCase().strip();
						while(!(unsatisified.equals("y")||unsatisified.equals("n"))){
							System.out.println("Invalid input. Type again: ");
							unsatisified = myObj.nextLine();
							unsatisified = unsatisified.toLowerCase().strip();
						}
							
						if(unsatisified.equals("y")){
							internalFlag = true;
						} else if(unsatisified.equals("n")){
							internalFlag = false;
						}
					}
				} else if((timeAnswer.equals("de"))||(timeAnswer.equals("dessert"))){
					//dessert
					ArrayList m = s.Dessert;
					while(internalFlag == true){
						collection = RandomPicker.pick(m);
						System.out.println("You picked for a dessert. Our pick for today's menu is " + collection[0] + ".");
						System.out.println("For general recipe, you will need " + collection[1] + ", " + collection[2] + ", and " + collection[3] + " to make " + collection[0]);
						System.out.println("\nUnsatisified with the choice? would you like to get another pick? Y/N\n");
						String unsatisified = myObj.nextLine();
						unsatisified = unsatisified.toLowerCase().strip();
						while(!(unsatisified.equals("y")||unsatisified.equals("n"))){
							System.out.println("Invalid input. Type again: ");
							unsatisified = myObj.nextLine();
							unsatisified = unsatisified.toLowerCase().strip();
						}
							
						if(unsatisified.equals("y")){
							internalFlag = true;
						} else if(unsatisified.equals("n")){
							internalFlag = false;
						}
					}
				}
				
			} else if (answer1.toLowerCase().equals("y")){
				//specifing ingredient
				boolean validInput = false;
				int choiceInt=0;
				String choice;
				boolean yFlag = true;
				
				System.out.println("\nYou can choose the number of ingredients up to 2. (If you pick 2, we will recommend a menu that contains either "+
				"the first or second ingredient or both)");
				
				while(yFlag == true){
					validInput = false;
					System.out.println("How many ingredients do you want to pick?   ");
					
					while(validInput == false){
						try{
							choice = myObj.nextLine();
							choiceInt = Integer.parseInt(choice);
							if(!((choiceInt <= 0)||(choiceInt >= 3))){
								validInput = true;
							} else{
								System.out.println("The input must be within the bound of 1 to 2.");
							}
							
						} catch(NumberFormatException e){
							System.out.println("Invalid input. Please type it again:  ");
						}
					}
					ArrayList searchedArr = new ArrayList();
					String[] collected;
					
					if(choiceInt == 1){
						System.out.println("What is your first choice?  ");
						String choiceOne = myObj.nextLine();
						System.out.println("\nWould you like to choose specific meal category?\nYes or no for getting recommendation from the whole menu:  ");
						String mealCat = myObj.nextLine();
						mealCat = mealCat.toLowerCase();
						while(!((mealCat.equals("yes")||(mealCat.equals("no"))))){
							System.out.println("Invalid input. Please type either yes or no:  ");
							mealCat = myObj.nextLine();
							mealCat = mealCat.toLowerCase();
						}
						choiceOne = choiceOne.strip();
						if(mealCat.equals("no")){
							ArrayList dessS = s.finder(choiceOne, MenuReader.Dessert);
							ArrayList breaS = s.finder(choiceOne, MenuReader.Breakfast);
							ArrayList luncS = s.finder(choiceOne, MenuReader.Lunch);
							ArrayList dinnS = s.finder(choiceOne, MenuReader.Dinner);
							
							for (int i = 0; i < dessS.size(); i++) {
								searchedArr.add(dessS.get(i));
							}

							for (int i = 0; i < breaS.size(); i++) {
								searchedArr.add(breaS.get(i));
							}
							for (int i = 0; i < luncS.size(); i++) {
								searchedArr.add(luncS.get(i));
							}
							for (int i = 0; i < dinnS.size(); i++) {
								searchedArr.add(dinnS.get(i));
							}
							if(searchedArr.size() == 0){
								System.out.println("\nWe cannot find the menu that have your chosen ingredient. ");
							}else{
								collected = RandomPicker.pick(searchedArr);
								System.out.println("We recommend " + collected[0] + " based on your chosen ingredient.");
								System.out.println("For general recipe, you will need " + collected[1] + ", " + collected[2] + ", and " + collected[3] + " to make " + collected[0]);
							}
							
						} else{ //choosing the meal category
							System.out.println("You could choose a menu for breakfast, lunch, dinner, or even dessert! What would you like to choose?");
							System.out.println("You can type B for breakfast, L for lunch, Di for dinner, De for dessert, or just write the full name.\n");
							String timeAnswer = myObj.nextLine();
							timeAnswer = timeAnswer.toLowerCase();
							
							while (!(((timeAnswer.equals("b"))||(timeAnswer.equals("breakfast")))||(timeAnswer.equals("l"))||(timeAnswer.equals("lunch"))||
								((timeAnswer.equals("di"))||(timeAnswer.equals("dinner")))||((timeAnswer.equals("de"))||(timeAnswer.equals("dessert"))))){
								System.out.println("Invalid input. Please type it again: ");
								timeAnswer = myObj.nextLine();
								timeAnswer = timeAnswer.toLowerCase().strip();
							}
							
							if((timeAnswer.equals("b"))||(timeAnswer.equals("breakfast"))){
								ArrayList breaS = s.finder(choiceOne, MenuReader.Breakfast);
								if(breaS.size() == 0){
									System.out.println("\nWe cannot find the menu that have your chosen ingredient. ");
								}else{
									collected = RandomPicker.pick(breaS);
									System.out.println("We recommend " + collected[0] + " based on your chosen ingredient.");
									System.out.println("For general recipe, you will need " + collected[1] + ", " + collected[2] + ", and " + collected[3] + " to make " + collected[0]);
								}
							} else if((timeAnswer.equals("l"))||(timeAnswer.equals("lunch"))){
								ArrayList luncS = s.finder(choiceOne, MenuReader.Lunch);
								if(luncS.size() == 0){
									System.out.println("\nWe cannot find the menu that have your chosen ingredient. ");
								} else{
									collected = RandomPicker.pick(luncS);
									System.out.println("We recommend " + collected[0] + " based on your chosen ingredient.");
									System.out.println("For general recipe, you will need " + collected[1] + ", " + collected[2] + ", and " + collected[3] + " to make " + collected[0]);
								
								}
							} else if((timeAnswer.equals("di"))||(timeAnswer.equals("dinner"))){
								ArrayList dinnS = s.finder(choiceOne, MenuReader.Dinner);
								if(dinnS.size() == 0){
									System.out.println("\nWe cannot find the menu that have your chosen ingredient. ");
								} else{
									collected = RandomPicker.pick(dinnS);
									System.out.println("We recommend " + collected[0] + " based on your chosen ingredient.");
									System.out.println("For general recipe, you will need " + collected[1] + ", " + collected[2] + ", and " + collected[3] + " to make " + collected[0]);
								
								}
							} else if((timeAnswer.equals("de"))||(timeAnswer.equals("dessert"))){
								ArrayList dessS = s.finder(choiceOne, MenuReader.Dessert);
								if(dessS.size() == 0){
									System.out.println("\nWe cannot find the menu that have your chosen ingredient. ");
								} else{
									collected = RandomPicker.pick(dessS);
									System.out.println("We recommend " + collected[0] + " based on your chosen ingredient.");
									System.out.println("For general recipe, you will need " + collected[1] + ", " + collected[2] + ", and " + collected[3] + " to make " + collected[0]);
								}
							}
						}

					} else if(choiceInt == 2){ //choosing 2 ingredients
						System.out.println("What is your first choice?  ");
						String choiceOne = myObj.nextLine();
						
						System.out.println("What is your second choice?  ");
						String choiceTwo = myObj.nextLine();
						
						System.out.println("\nWould you like to choose specific meal category?\nYes or no for getting recommendation from the whole menu:  ");
						String mealCat = myObj.nextLine();
						mealCat = mealCat.toLowerCase().strip();
						while(!((mealCat.equals("yes")||(mealCat.equals("no"))))){
							System.out.println("Invalid input. Please type either yes or no:  ");
							mealCat = myObj.nextLine();
							mealCat = mealCat.toLowerCase().strip();
						}
						choiceOne = choiceOne.strip();
						choiceTwo = choiceTwo.strip();
						
						if(mealCat.equals("no")){
							ArrayList dessS = s.finder(choiceOne, MenuReader.Dessert);
							ArrayList breaS = s.finder(choiceOne, MenuReader.Breakfast);
							ArrayList luncS = s.finder(choiceOne, MenuReader.Lunch);
							ArrayList dinnS = s.finder(choiceOne, MenuReader.Dinner);
							
							ArrayList dessSS = s.finder(choiceTwo, MenuReader.Dessert);
							ArrayList breaSS = s.finder(choiceTwo, MenuReader.Breakfast);
							ArrayList luncSS = s.finder(choiceTwo, MenuReader.Lunch);
							ArrayList dinnSS = s.finder(choiceTwo, MenuReader.Dinner);
							
							for (int i = 0; i < dessS.size(); i++) {
								searchedArr.add(dessS.get(i));
							}
							for (int i = 0; i < breaS.size(); i++) {
								searchedArr.add(breaS.get(i));
							}
							for (int i = 0; i < luncS.size(); i++) {
								searchedArr.add(luncS.get(i));
							}
							for (int i = 0; i < dinnS.size(); i++) {
								searchedArr.add(dinnS.get(i));
							}
							
							for (int i = 0; i < dessSS.size(); i++) {
								searchedArr.add(dessSS.get(i));
							}
							for (int i = 0; i < breaSS.size(); i++) {
								searchedArr.add(breaSS.get(i));
							}
							for (int i = 0; i < luncSS.size(); i++) {
								searchedArr.add(luncSS.get(i));
							}
							for (int i = 0; i < dinnSS.size(); i++) {
								searchedArr.add(dinnSS.get(i));
							}
							
							if(searchedArr.size() == 0){ 
								System.out.println("We cannot find a menu that has your chosen ingredient. ");
							}else{
								collected = RandomPicker.pick(searchedArr);
								System.out.println("We recommend " + collected[0] + " based on your chosen ingredient.");
								System.out.println("For general recipe, you will need " + collected[1] + ", " + collected[2] + ", and " + collected[3] + " to make " + collected[0]);
							}
							
						} else{
							ArrayList accArray = new ArrayList();
							System.out.println("You could choose a menu for breakfast, lunch, dinner, or even dessert! What would you like to choose?");
							System.out.println("You can type B for breakfast, L for lunch, Di for dinner, De for dessert, or just write the full name.\n");
							String timeAnswer = myObj.nextLine();
							timeAnswer = timeAnswer.toLowerCase().strip();
							
							while (!(((timeAnswer.equals("b"))||(timeAnswer.equals("breakfast")))||(timeAnswer.equals("l"))||(timeAnswer.equals("lunch"))||
								((timeAnswer.equals("di"))||(timeAnswer.equals("dinner")))||((timeAnswer.equals("de"))||(timeAnswer.equals("dessert"))))){
								System.out.println("Invalid input. Please type it again: ");
								timeAnswer = myObj.nextLine();
								timeAnswer = timeAnswer.toLowerCase().strip();
							}
							
							if((timeAnswer.equals("b"))||(timeAnswer.equals("breakfast"))){
								ArrayList breaS = s.finder(choiceOne, MenuReader.Breakfast);
								ArrayList breaSS = s.finder(choiceTwo, MenuReader.Breakfast);
								
								for(int i=0;i<breaS.size();i++){
									accArray.add(breaS.get(i));
								}
								for(int i=0;i<breaSS.size();i++){
									accArray.add(breaSS.get(i));
								}
								
								if((breaS.size() == 0)&&(breaSS.size() == 0)){
									System.out.println("We cannot find the menu that have your chosen ingredient. ");
								}else{
									collected = RandomPicker.pick(accArray);
									System.out.println("We recommend " + collected[0] + " based on your chosen ingredient.");
									System.out.println("For general recipe, you will need " + collected[1] + ", " + collected[2] + ", and " + collected[3] + " to make " + collected[0]);
								}
							} else if((timeAnswer.equals("l"))||(timeAnswer.equals("lunch"))){
								ArrayList luncS = s.finder(choiceOne, MenuReader.Lunch);
								ArrayList luncSS = s.finder(choiceTwo, MenuReader.Lunch);
								System.out.println("testing " + luncS);//test
								for(int i=0;i<luncS.size();i++){
									accArray.add(luncS.get(i));
								}
								for(int i=0;i<luncSS.size();i++){
									accArray.add(luncSS.get(i));
								}
								
								if((luncS.size() == 0)&&(luncSS.size() == 0)){
									System.out.println("\nWe cannot find the menu that have your chosen ingredient. ");
								} else{
									collected = RandomPicker.pick(accArray);
									System.out.println("We recommend " + collected[0] + " based on your chosen ingredient.");
									System.out.println("For general recipe, you will need " + collected[1] + ", " + collected[2] + ", and " + collected[3] + " to make " + collected[0]);
								
								}
							} else if((timeAnswer.equals("di"))||(timeAnswer.equals("dinner"))){
								ArrayList dinnS = s.finder(choiceOne, MenuReader.Dinner);
								ArrayList dinnSS = s.finder(choiceTwo, MenuReader.Dinner);
								
								for(int i=0;i<dinnS.size();i++){
									accArray.add(dinnS.get(i));
								}
								for(int i=0;i<dinnSS.size();i++){
									accArray.add(dinnSS.get(i));
								}
								
								if((dinnS.size() == 0)&&(dinnSS.size() == 0)){
									System.out.println("\nWe cannot find the menu that have your chosen ingredient. ");
								} else{
									collected = RandomPicker.pick(accArray);
									System.out.println("We recommend " + collected[0] + " based on your chosen ingredient.");
									System.out.println("For general recipe, you will need " + collected[1] + ", " + collected[2] + ", and " + collected[3] + " to make " + collected[0]);
								
								}
							} else if((timeAnswer.equals("de"))||(timeAnswer.equals("dessert"))){
								ArrayList dessS = s.finder(choiceOne, MenuReader.Dessert);
								ArrayList dessSS = s.finder(choiceTwo, MenuReader.Dessert);
								
								for(int i=0;i<dessS.size();i++){
									accArray.add(dessS.get(i));
								}
								for(int i=0;i<dessSS.size();i++){
									accArray.add(dessSS.get(i));
								}
								
								if((dessS.size() == 0)&&(dessSS.size() == 0)){
									System.out.println("\nWe cannot find the menu that have your chosen ingredient. ");
								} else{
									collected = RandomPicker.pick(accArray);
									System.out.println("We recommend " + collected[0] + " based on your chosen ingredient.");
									System.out.println("For general recipe, you will need " + collected[1] + ", " + collected[2] + ", and " + collected[3] + " to make " + collected[0]);
								}
							}
						}
						
					}
					
					System.out.println("\n\nUnsatisified with our choice? Y for getting another recommendation and N for no.");
					String againn = myObj.nextLine().toLowerCase();
					
					while(!((againn.equals("y"))||(againn.equals("n")))){
						System.out.println("Invalid input. Type again: ");
						againn = myObj.nextLine();
					}
					
					if(againn.equals("n")){
						yFlag = false;
					}
				}
			}
			
			System.out.println("\nWould you like to start it from the beginning? Y or N\n");
			String startAgain = myObj.nextLine();
			startAgain = startAgain.toLowerCase();
			
			while(!((startAgain.equals("y"))||(startAgain.equals("n")))){
				System.out.println("Invalid input. Type again: ");
				startAgain = myObj.nextLine();
			}
			
			if(startAgain.equals("n")){
				flag = false;
				System.out.println("Thank you for using the menu picker!");
			}
		}
	} 
}