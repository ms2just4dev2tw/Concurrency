package jcip.ex08;

import java.util.*;

/**
 * <h6>CodeList 8-15 SequentialPuzzleSolver</h6>
 * <i>Sequential puzzle solver</i>
 * <p>
 * 
 * @author Brian Goetz and Tim Peierls
 */

public class SequentialPuzzleSolver<P, M> {
	
	private final Puzzle<P, M> puzzle;
	private final Set<P> seen = new HashSet<P>();

	public SequentialPuzzleSolver(Puzzle<P, M> puzzle) {
		this.puzzle = puzzle;
	}

	public List<M> solve() {
		P pos = puzzle.initialPosition();
		return search(new PuzzleNode<P, M>(pos, null, null));
	}

	private List<M> search(PuzzleNode<P, M> node) {
		if (!seen.contains(node.pos)) {
			seen.add(node.pos);
			if (puzzle.isGoal(node.pos))
				return node.asMoveList();
			for (M move : puzzle.legalMoves(node.pos)) {
				P pos = puzzle.move(node.pos, move);
				PuzzleNode<P, M> child = new PuzzleNode<P, M>(pos, move, node);
				List<M> result = search(child);
				if (result != null)
					return result;
			}
		}
		return null;
	}
}
