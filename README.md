# HeuristiChess
Reverse-engineered and modified chess application in the Java Programming language, which features an added AI subcomponent.

This project was for a fourth year undergraduate software development project course; and was focused around gaining hands-on experience
with reverse-engineering, AI design, and the Java programming language.

The program itself is a modification of Austin Philp's JChess application (github.com/austinphilp).
Development of HeuristiChess began with a reverse engineering of JChess to restructure and optimize the program in such a way
that the AI component could be seamlessly added

Programming and testing: Java SE 8 using Netbeans IDE (ver. 8.2)

The AI component generates a state-space, applying a heuristic to score each state, and reduces the state-space size through pruning;
based on the scores of each state.  
All of this allows for a time-efficient search of the state-space to generate the best move that the AI can take in any one turn.
This AI subcomponent also features concurrent processing, to allow for multi-threaded state-space generation - further increasing
on the time efficiency of the program.
