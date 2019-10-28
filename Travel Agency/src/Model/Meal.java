package Model;

/**
 * This class is for meals that users can order after buying ticket
 * Every meal has name as String and price as double
 * 
 * @author Group 25
 *
 */
public class Meal {
	
	String MealName;
	double Mealprice;
	boolean select;
	
	/**
	 * @return the select
	 */
	public boolean isSelect() {
		return select;
	}
	
	/**
	 * @param select the select to set
	 */
	public void setSelect(boolean select) {
		this.select = select;
	}
	
	/**
	 * @param mealName
	 * @param mealprice
	 */
	public Meal(String mealName, double mealprice) {
		MealName = mealName;
		Mealprice = mealprice;
	}
	
	/**
	 * @return the mealName
	 */
	public String getMealName() {
		return MealName;
	}
	
	/**
	 * @param mealName the mealName to set
	 */
	public void setMealName(String mealName) {
		MealName = mealName;
	}
	
	/**
	 * @return the mealprice
	 */
	public double getMealprice() {
		return Mealprice;
	}
	
	/**
	 * @param mealprice the mealprice to set
	 */
	public void setMealprice(double mealprice) {
		Mealprice = mealprice;
	}
	
}
