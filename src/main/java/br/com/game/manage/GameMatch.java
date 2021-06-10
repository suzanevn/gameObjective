package br.com.game.manage;

import javax.swing.JOptionPane;

import br.com.game.entities.Dish;
import br.com.game.entities.Node;
import br.com.game.service.GameService;

/**
 * GameMatch class manage the game phases.
 */
public class GameMatch {

	boolean answeredKind = false;
	Dish currentDish = new Dish();
	Dish returnDish = new Dish();
	
	public void start(){
		GameService gs = new GameService();
		Dish dish = gs.getRootNode().getDish();
		Dish lastDish = gs.getRootNode().getLeft().getDish(); 
		startMatch(gs, dish, lastDish);
	}
	
	public void startMatch(GameService gs, Dish dish, Dish lastDish){
		returnDish = gs.getRootNode().getDish();
		if(JOptionPane.showConfirmDialog(null, "Pense em um prato que gosta", "Jogo Gourmet", JOptionPane.OK_CANCEL_OPTION) == 0) {
			Dish palpiteDish= guessKind(gs, dish,lastDish);
			guessDish(gs, palpiteDish, lastDish);
			startMatch(gs, gs.getRootNode().getDish(), lastDish);
		}else {
			System.exit(1);
		}
	}	

	private Dish guessKind(GameService gs, Dish dish, Dish lastDish) {
		currentDish = dish; 
		if (dish.getKind() == null)
			return lastDish;
		answeredKind = JOptionPane.showConfirmDialog(null, "O prato que você pensou é "+ dish.getKind() +"?",
				"Confirm", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION;
		Dish proximoDish = gs.nextDish(dish, answeredKind);
		if (answeredKind) {
			returnDish = dish;
		}
		return proximoDish == null ? returnDish : guessKind(gs, proximoDish, lastDish);
	}

	private void guessDish(GameService gs, Dish dish, Dish lastDish) {
		boolean answer = JOptionPane.showConfirmDialog(null, "O prato que você pensou é "+ dish.getName() +"?",
				"Confirm", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION;
		if (answer) {
			JOptionPane.showMessageDialog(null, "Acertei de novo!", "Jogo Gourmet", JOptionPane.INFORMATION_MESSAGE);
		} else 
			addDish(gs, currentDish, lastDish);
	}

	private void addDish(GameService gs, Dish dish, Dish lastDish) {
		Dish newDish = new Dish();	
		String name = null;
		String kind = null;
		int first = 0;
		do {
			if(first>0) JOptionPane.showMessageDialog(null, "Informe um valor!", "Jogo Gourmet", JOptionPane.INFORMATION_MESSAGE);
			name = JOptionPane.showInputDialog(null, "Qual prato você pensou?", "Desisto", JOptionPane.QUESTION_MESSAGE);
			first++;
		}while(name==null || name.equals(""));
		newDish.setName(name);
		first = 0;
		do {
			if(first>0) JOptionPane.showMessageDialog(null, "Informe um valor!", "Jogo Gourmet", JOptionPane.INFORMATION_MESSAGE);
			kind = JOptionPane.showInputDialog(null,newDish.getName() + " é ______ mas " + dish.getName() + " não.", 
					"Complete", JOptionPane.QUESTION_MESSAGE);
			first++;
		}while(kind==null || kind.equals(""));
		
		newDish.setKind(kind);
		Node nodeFinded = gs.nextNodeHaveDish(gs.getRootNode(), currentDish);
		gs.setCurrentNode(nodeFinded != null ? nodeFinded : gs.getCurrentNode());
		if (!answeredKind)
			if (nodeFinded.getDish().getName().equals(lastDish.getName()))
				gs.insertNextToLastNode(newDish);
			else
				gs.insertLeft(newDish);
		else {
			gs.insertRight(newDish);
		}
	}
	
}
