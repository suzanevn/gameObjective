package br.com.game.entities;

/**
 * Object for Dish attributes.
 */
public class Dish {

	private String name;
	private String kind;
	
	public Dish() {
		super();
	}

	public Dish(String name, String kind) {
		super();
		this.name = name;
		this.kind = kind;
	}

	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getKind() {
		return kind;
	}
	public void setKind(String kind) {
		this.kind = kind;
	}
	
	
	
}
