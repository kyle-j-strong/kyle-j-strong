package ca.sheridancollege.ca.strongky.bean;

import java.io.Serializable;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Breed implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7292856647542254501L;
	
	private String breed;
	private String id;
}
