import java.util.ArrayList;
import java.util.Random;

public class RandomPicker{
	public static String[] pick(ArrayList<ArrayList<String>> a){
		int size = (a.size());
		Random rand = new Random();
		
		int chosenNum = rand.nextInt(size);
		
		ArrayList<String> chosenList = a.get(chosenNum);
		String[] finals = new String[4];
		
		finals[0] = chosenList.get(0);
		finals[1] = chosenList.get(1);
		finals[2] = chosenList.get(2);
		finals[3] = chosenList.get(3);
		
		return finals;
		
	}
}