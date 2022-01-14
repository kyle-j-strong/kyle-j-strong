package ca.sheridancollege.ca.strongky.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import ca.sheridancollege.ca.strongky.bean.Breed;
import ca.sheridancollege.ca.strongky.bean.Dog;
import ca.sheridancollege.ca.strongky.repository.DogRepo;

@Controller
public class DogController {
	
	@Autowired
	private DogRepo dogRepo;
	
	@GetMapping("/")
	public String home() {
		return "Home.html";
	}
	
	@GetMapping("/register")
	public String register(Model model) {
		model.addAttribute("dog", new Dog());
		model.addAttribute("breeds", dogRepo.getBreeds());
		return "Register.html";
	}
	
	@GetMapping("/save")
	public String save(Model model, @ModelAttribute Dog dog) {
		dogRepo.addDogs(dog);
		return "redirect:/register";
	}
	
	@GetMapping("/view")
	public String view(Model model) {
		model.addAttribute("dogs", dogRepo.getDogs());
		return "View.html";
	}
	
	@GetMapping("/show")
	public String show() {
		return "Show.html";
	}
	
	@GetMapping("/breed")
	public String breed(Model model) {
		model.addAttribute("breed", new Breed());
		return "Breeds.html";
	}
	
	@GetMapping("/edit/{entryNumber}")
	public String editDog(@PathVariable int entryNumber,Model model) {
		Dog dog = dogRepo.getDogByEntryNumber(entryNumber);
		model.addAttribute("dog", dog);
		return "EditDog.html";
	}
	
	@PostMapping("/editDog")
	public String modifyDog(Model model, @ModelAttribute Dog dog) {
		dogRepo.editDogs(dog);
		model.addAttribute("dogs", dogRepo.getDogs());
		return "redirect:/view";
	}
	
	@GetMapping("/delete/{entryNumber}")
	public String deleteDog(@PathVariable int entryNumber, Model model) {
		dogRepo.deleteDogs(entryNumber);
		
		model.addAttribute("dogs", dogRepo.getDogs());
		return "redirect:/view";
	}
	
	@GetMapping("/addBreed")
	public String addBreed(Model model, @ModelAttribute Breed breed) {
		dogRepo.addBreeds(breed);
		return "Breeds.html";
	}
	

}
