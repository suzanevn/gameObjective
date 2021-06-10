package br.com.game.service;

import br.com.game.entities.Dish;
import br.com.game.entities.Node;

/**
 * GameService class manage the actions on the tree.
 */
public class GameService {
	
	private Node rootNode;  
    private Node currentNode;
	
    public Node getRootNode() {
		return rootNode;
	}

	public void setRootNode(Node rootNode) {
		this.rootNode = rootNode;
	}

	public Node getCurrentNode() {
		return currentNode;
	}

	public void setCurrentNode(Node currentNode) {
		this.currentNode = currentNode;
	}

	public GameService() {
    	rootNode = new Node(null, null, null, new Dish("Lasanha", "Massa"));
    	rootNode.setLeft(new Node(rootNode, null, null, new Dish("Bolo de Chocolate", null)));
    	currentNode = rootNode;
    }
    
    public void insertLeft(Dish dish){
		Node node = new Node();
		node.setDish(dish);
		node.setParent(currentNode);
		currentNode.setLeft(node);
    } 
    
   
    public void insertNextToLastNode(Dish dish){
		Node node = new Node();
		node.setDish(dish);
    	node.setLeft(currentNode);
		node.setParent(currentNode.getParent());
		
		currentNode.getParent().setLeft(node);
		currentNode.setParent(node);
    } 
    
    
    public void insertRight(Dish dish){
    	Node node = new Node();
		node.setDish(dish);
		node.setParent(currentNode);
		currentNode.setRight(node);
    } 
    
   
    public Dish nextDish(Dish dish, boolean answer) {
		Node node = new Node();
    	node = nextNodeHaveDish(rootNode, dish);
		if (node == null)
			return null;
    	return answer ?
    			node.getRight() == null ? null : node.getRight().getDish():
    			node.getLeft() == null ? null : node.getLeft().getDish();
	}
    
    public Node nextNodeHaveDish(Node node, Dish dish) {
    	if (node.getDish().equals(dish))
    		return node;
    	Node rightNode = node.getRight() == null ? null : node.getRight();
    	Node leftNode = node.getLeft() == null ? null : node.getLeft();
    	if (rightNode != null) {
    		if (rightNode.getDish().equals(dish)) {
				return rightNode;
    		} else {
    			return nextNodeHaveDish(rightNode, dish);
    		}
    	}
		if (leftNode != null) {
			if (leftNode.getDish().equals(dish)) {
				return leftNode;
			} else {
				return nextNodeHaveDish(leftNode, dish);
			}
		}
		return searchLeftNodeParentNode(node, node.getParent(), dish);
	}

	private Node searchLeftNodeParentNode(Node node, Node parentNode, Dish dish) {
    	Node leftParentNode = parentNode.getLeft() == null ? null : parentNode.getLeft();
		if (parentNode.getRight() != null && node.equals(parentNode.getRight())) {
			if (leftParentNode != null && leftParentNode.getDish().equals(dish)) {
				return leftParentNode;
			} else if (leftParentNode != null) {
				return nextNodeHaveDish(leftParentNode, dish);
			}
		}
		return searchLeftNodeParentNode(parentNode, parentNode.getParent(), dish);
	}
	
}
