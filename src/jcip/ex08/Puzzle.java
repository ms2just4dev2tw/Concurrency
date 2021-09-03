package jcip.ex08;

import java.util.*;

/**
 * <h6>CodeList 8-13 Puzzle</h6>
 * <i>Abstraction for puzzles like the 'sliding blocks puzzle'</i>
 * <p>
 * 
 * @author Brian Goetz and Tim Peierls
 */
public interface Puzzle<P, M> {
	
	P initialPosition();

	boolean isGoal(P position);

	Set<M> legalMoves(P position);

	P move(P position, M move);
}
