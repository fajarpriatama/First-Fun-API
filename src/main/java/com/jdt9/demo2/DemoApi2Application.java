package com.jdt9.demo2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class DemoApi2Application {

	public static void main(String[] args) {
		SpringApplication.run(DemoApi2Application.class, args);
		System.out.println("Bisa");
	}

	@RestController
	@RequestMapping("/car")
	class CarController {

		private final List<Car> cars = new ArrayList<>();

		@PostMapping("/bulk") //bulk atau input banyak list
		public List<Car> createBulk(@RequestBody List<Car> newCars) {
			cars.addAll(newCars);
			return cars;
		}

		@PostMapping
		public Car create(@RequestBody Car car) {
			cars.add(car);
			return car;
		}

		@GetMapping
		public List<Car> getCars() {
			return cars;
		}

		@GetMapping("/{index}")
		public Car getCars(@PathVariable int index) {
			return cars.get(index);
		}

		@PutMapping
		public Car edit(@RequestParam int index, @RequestParam String merk, @RequestParam String type) {
			Car car = cars.get(index);
			car.setMerk(merk);
			car.setType(type);
			return car;
		}

		@DeleteMapping
		public void delete(@RequestParam int index) {
			cars.remove(index);
		}
	}
	static class Car{
		String merk;
		String type;

		public Car(){}

		public String getMerk() {
			return merk;
		}

		public void setMerk(String merk) {
			this.merk = merk;
		}

		public String getType() {
			return type;
		}

		public void setType(String type) {
			this.type = type;
		}
	}
}
