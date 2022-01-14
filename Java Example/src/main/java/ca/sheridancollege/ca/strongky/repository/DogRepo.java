package ca.sheridancollege.ca.strongky.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import ca.sheridancollege.ca.strongky.bean.Breed;
import ca.sheridancollege.ca.strongky.bean.Dog;

@Repository
public class DogRepo {

	@Autowired
	private NamedParameterJdbcTemplate jdbc;
	
	public void addDogs(Dog dog) {
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		String query = "INSERT INTO dog_table (name,ownerName,breed,gender,special)" +
		"VALUES (:name, :ownerName, :breed, :gender, :special)";
		parameters.addValue("name", dog.getName());
		parameters.addValue("ownerName", dog.getOwnerName());
		parameters.addValue("breed", dog.getBreed());
		parameters.addValue("gender", dog.getGender());
		parameters.addValue("special", dog.getSpecial());
		jdbc.update(query,  parameters);
	}
	
	public ArrayList<Dog> getDogs() {
		ArrayList<Dog> dogs = new ArrayList<Dog>();
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		String query = "SELECT * FROM dog_table";
		List<Map<String, Object>> rows = jdbc.queryForList(query, parameters);
		for (Map<String, Object> row : rows) {
			Dog d = new Dog();
				d.setEntryNumber((int)row.get("entryNumber"));
				d.setName((String)row.get("name"));
				d.setOwnerName((String)row.get("ownerName"));
				d.setBreed((String)row.get("breed"));
				d.setGender((String)row.get("gender"));
				d.setSpecial((String)row.get("special"));
				dogs.add(d);
		}
		return dogs;
	}
	
	public Dog getDogByEntryNumber(int entryNumber) {
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		String query = "SELECT * FROM dog_table WHERE entryNumber=:entryNumber";
		parameters.addValue("entryNumber", entryNumber);
		ArrayList<Dog> dogs = 
				(ArrayList<Dog>)jdbc.query(query,  parameters, new BeanPropertyRowMapper<Dog>(Dog.class));
		if(dogs.size()>0) {
			return dogs.get(0);
		}else {
			return null;
		}
	}
	
	public void editDogs(Dog dog) {
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		String query = "UPDATE dog_table SET name=:name, ownerName=:ownerName,breed=:breed,gender=:gender, special=:special WHERE entryNumber=:entryNumber";
		parameters.addValue("entryNumber", dog.getEntryNumber());
		parameters.addValue("name", dog.getName());
		parameters.addValue("ownerName", dog.getOwnerName());
		parameters.addValue("breed", dog.getBreed());
		parameters.addValue("gender", dog.getGender());
		parameters.addValue("special", dog.getSpecial());
		jdbc.update(query,  parameters);
	}
	
	public void deleteDogs(int entryNumber) {
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		String query = "DELETE FROM dog_table WHERE entryNumber=:entryNumber";
		parameters.addValue("entryNumber",  entryNumber);
		jdbc.update(query, parameters);
	}
	
	public void addBreeds(Breed breed) {
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		String query = "INSERT INTO breed_table (breed)" +
		"VALUES (:breed)";
		parameters.addValue("breed", breed.getBreed());
		jdbc.update(query,  parameters);
	}
	
	public ArrayList<Breed> getBreeds() {
		ArrayList<Breed> breeds = new ArrayList<Breed>();
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		String query = "SELECT * FROM breed_table";
		List<Map<String, Object>> rows = jdbc.queryForList(query, parameters);
		for (Map<String, Object> row : rows) {
			Breed b = new Breed();
				b.setBreed((String)row.get("breed"));
				breeds.add(b);
		}
		return breeds;
	}
	
	
	
	
}
