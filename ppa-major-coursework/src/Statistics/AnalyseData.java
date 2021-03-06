package Statistics;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Observable;

import Map.LocationsReader;
import Processing.API;
import Processing.CustomIncident;

public class AnalyseData {
	private ArrayList<CustomIncident> listOfIncidents;
	private HashMap<String, Integer[]> locations;
	private HashMap<String, Integer> stateFrequency;
	
	private int noOfHoaxes;
	private int noOfNonUS;
	private String likeliestState;
	
	/**
	 * initialises the listOfIncidents, locations and stateFrequency
	 */
	public AnalyseData () {
		
		listOfIncidents = new ArrayList<CustomIncident>();
		
		listOfIncidents = API.getCurrentIncidents();

		LocationsReader fileReader = new LocationsReader();

		locations = fileReader.getLocations();

		stateFrequency = API.getStateFrequency();
		
	}
	
	/**
	 * counts the number of hoaxes in the listOfIncedents 
	 */
	public void CalculateNumberOfHoaxes(){
		String Hoax = "HOAX";
		int noOfHoaxes = 0;
		for (CustomIncident incidents : listOfIncidents) {
			if(incidents.getSummary().contains(Hoax)){
				noOfHoaxes++;
			}
			
		}
		
		this.noOfHoaxes =  noOfHoaxes;
		
	}
	
	/**
	 * counts the number of incidents that are non US 
	 */
	public void CalculateNonUSSightings(){
		int nonUSCity = 0;
		for(CustomIncident incidents :listOfIncidents){
			if(!locations.keySet().contains(incidents.getState()) && !incidents.getState().equals("AK") && !incidents.getState().equals("HI")){
				
				nonUSCity++;
			}	
		}
		this.noOfNonUS =  nonUSCity;	
	}
	
	/**
	 * it will choose the state with the highest frequency
	 */
	public void CalculateLikeliestState(){
		
		String highestState = "";
		int currentHighest = 0;
		
		for(String key: stateFrequency.keySet()){
			if(stateFrequency.get(key) > currentHighest){
				currentHighest = stateFrequency.get(key);
				highestState = key;
			}
		}
		this.likeliestState = highestState;
	}

	/**
	 * @return The likeliest state to be affected
	 */
	public String getLikeliestState() {
		return this.likeliestState;
	}
	/**
	 * 
	 * @return The number of possible hoaxes
	 */
	public int getNoOfHoaxes() {
		return noOfHoaxes;
	}

	/**
	 * The number of non us sightings
	 * @return
	 */
	public int getNoOfNonUS() {
		return noOfNonUS;
	}
	
	
}
