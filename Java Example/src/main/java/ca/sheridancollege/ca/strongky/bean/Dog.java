package ca.sheridancollege.ca.strongky.bean;
import java.io.Serializable;


import lombok.*;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class Dog implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -9209318953298522523L;
	
	private int entryNumber;
	private String name;
	private String ownerName;
	private String breed;
	private String gender;
	private String special;

	private String[] specials = {"Class", "Specialty"};
	private String[] genders = {"Male", "Female"};

}
