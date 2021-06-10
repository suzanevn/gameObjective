package br.com.game.entities;

/**
 * Object for Node attributes.
 */
public class Node {

	private Node parent;
	private Node right;
	private Node left;
	private Dish dish;
	
	public Node() {
		super();
	}

	public Node(Node parent, Node right, Node left, Dish dish) {
		super();
		this.parent = parent;
		this.right = right;
		this.left = left;
		this.dish = dish;
	}
	
	public Node getParent() {
		return parent;
	}
	public void setParent(Node parent) {
		this.parent = parent;
	}
	public Node getRight() {
		return right;
	}
	public void setRight(Node right) {
		this.right = right;
	}
	public Node getLeft() {
		return left;
	}
	public void setLeft(Node left) {
		this.left = left;
	}
	public Dish getDish() {
		return dish;
	}
	public void setDish(Dish dish) {
		this.dish = dish;
	}
	
	
}
