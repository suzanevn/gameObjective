package br.com.game.tests;

import static org.junit.Assert.*;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import br.com.game.entities.Dish;
import br.com.game.entities.Node;
import br.com.game.service.GameService;

/**
 * Tests for {@link GameService} class.
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestGameService {
	
	Node nodeRet = new Node();
	GameService gs = new GameService();
	Dish currentDish = new Dish();
	Dish returnedDish = new Dish();

	@Test
	public void test1NextDishInitialTree() {
		returnedDish = gs.nextDish(gs.getRootNode().getDish(), true);
		assertEquals(returnedDish, null);
	}
	
	@Test
	public void test2NextNodeHaveDishInitialTree() {
		nodeRet = gs.nextNodeHaveDish(gs.getRootNode(), gs.getCurrentNode().getDish());
		assertEquals(nodeRet, gs.getRootNode());
	}
	
	@Test
	public void test3GetLeftNodeInitialTree() {
		returnedDish = gs.nextDish(gs.getRootNode().getDish(), false);
		nodeRet = gs.nextNodeHaveDish(gs.getRootNode(), returnedDish);
		assertEquals(gs.getRootNode().getLeft().getDish(), nodeRet.getDish());
	}

	@Test
	public void test4InsertRightNodeFirstLevelTree() {
		insertRight();
		returnedDish = gs.nextDish(currentDish, false);
		nodeRet = gs.nextNodeHaveDish(gs.getRootNode(), currentDish);
		assertEquals(nodeRet.getDish(), currentDish);
	}
	
	@Test
	public void test5InsertLeftNodeSecondLevelTree() {
		returnedDish = gs.nextDish(gs.getRootNode().getDish(), true);
		nodeRet = gs.nextNodeHaveDish(gs.getRootNode(), gs.getRootNode().getDish());
		insertRight();
		returnedDish = gs.nextDish(currentDish, false);
		nodeRet = gs.nextNodeHaveDish(gs.getRootNode(), currentDish);
		gs.setCurrentNode(nodeRet);
		insertLeft();
		nodeRet = gs.nextNodeHaveDish(gs.getRootNode(), currentDish);
		assertEquals(nodeRet.getDish(), currentDish);
	}
	
	@Test
	public void test6InsertNextToLastNodeLeftFirstLevelTree() {
		returnedDish = gs.nextDish(gs.getRootNode().getDish(), false);
		nodeRet = gs.nextNodeHaveDish(gs.getRootNode(), returnedDish);
		gs.setCurrentNode(nodeRet);
		insertNextToLastNode();
		nodeRet = gs.nextNodeHaveDish(gs.getRootNode(), currentDish);
		assertEquals(nodeRet.getDish(), currentDish);
	}
	
	// methods
	public void insertRight() {
		currentDish = new Dish();
		currentDish.setKind("redonda");
		currentDish.setName("pizza");
		gs.insertRight(currentDish);
	}
	
	public void insertLeft() {
		currentDish = new Dish();
		currentDish.setKind("pequeno");
		currentDish.setName("nhoque");
		gs.insertLeft(currentDish);
	}
	
	public void insertNextToLastNode() {
		currentDish = new Dish();
		currentDish.setKind("gelado");
		currentDish.setName("sorvete");
		gs.insertNextToLastNode(currentDish);
	}
	

}
