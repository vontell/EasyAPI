package mapquest;

/**
 * Creates an object representing a node in the MapQuest Open Guidance API
 * NOTE: The time and distance is the information AFTER making the human direction
 * 		 THE HUMAN DIRECTION IS THE DIRECTION INTO THIS NODE
 * @author Aaron Vontell
 * @version 0.1
 */
public class DirectionNode {
	
	//attributes of a node
	private int maneuverType;
	private int index;
	private double distance;
	private int time;
	private String humanDirection;
	
	/**
	 * Creates a direction node
	 * @param manuever The maneuver type
	 * @param id The index of the direction node in reference to drawing points
	 * @param direction The human readable direction
	 * @param dist The distance to the next node
	 * @param t The time to the next node
	 */
	public DirectionNode(int manuever, int id, String direction, double dist, int t){
		
		maneuverType = manuever;
		index = id;
		humanDirection = direction;
		distance = dist;
		time = t;
		
	}

	/**
	 * @return the maneuverType
	 */
	public int getManueverType() {
		return maneuverType;
	}

	/**
	 * @return the index
	 */
	public int getIndex() {
		return index;
	}

	/**
	 * @return the humanDirection
	 */
	public String getHumanDirection() {
		return humanDirection;
	}

	/**
	 * @param maneuverType the maneuverType to set
	 */
	public void setManueverType(int maneuverType) {
		this.maneuverType = maneuverType;
	}

	/**
	 * @param index the index to set
	 */
	public void setIndex(int index) {
		this.index = index;
	}

	/**
	 * @param humanDirection the humanDirection to set
	 */
	public void setHumanDirection(String humanDirection) {
		this.humanDirection = humanDirection;
	}

	/**
	 * @return the distance
	 */
	public double getDistance() {
		return distance;
	}

	/**
	 * @return the time
	 */
	public int getTime() {
		return time;
	}

	/**
	 * @param distance the distance to set
	 */
	public void setDistance(double distance) {
		this.distance = distance;
	}

	/**
	 * @param time the time to set
	 */
	public void setTime(int time) {
		this.time = time;
	}
	
	
	
	

}
