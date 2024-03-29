package algorithm.shortestpath;

/**
 *  make an empty list C of closed nodes
 *  make a list O of open nodes and their respective f values containing the start node
 *  while O isn't empty:
 *      pick a node n from O with the best value for f
 *      if n is target:
 *          return solution
 *      for every m which is a neighbor of n:
 *          if (m is not in C) and (m is not in O):
 *              add m to O, set n as m's parent
 *              calculate g(m) and f(m) and save them
 *          else:
 *              if f(m) from last iteration is better than g(m) from this iteration:
 *                  set n as m's parent
 *                  update g(m) and f(m)
 *                  if m is in C:
 *                      move m to O
 *      move n from O to C
 *  return that there's no solution
 *
 */
public class AStar {
}
