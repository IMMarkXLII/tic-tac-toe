# Tic Tac Toe

## Prerequisites

* jdk 1.8 or greater
    - `brew cask install homebrew/cask-versions/adoptopenjdk8`
    - `export JAVA_HOME=/Library/Java/JavaVirtualMachines/adoptopenjdk-8.jdk/Contents/Home`
* maven 3.0 or greater
    - `brew install maven@3.5`
    - if not added, add maven to your path - `export PATH="/usr/local/opt/maven@3.5/bin:$PATH"`
    
## Commands to build and run the app

``` make build or make b``` 
* clean target directory and package the project into a jar with dependencies, adn run tests                 

``` make run or make r ``` 
* build, test and run the app

``` make test or make t ``` 
* run tests

``` make compile ``` 
* compile the project

## Assumptions

* If player 1 inserts incorrect symbol choice or skips the choice, X will automatically be set as default for player 1
* If a player skips name input, the systems sets the player name to player 1 or player 2

### Alternatively on your IDE run the TicTacToe java file.


### Sample Game Play
```
Please enter R to play against a robot, any other key for two player mode.

Player 1, please enter your name:
Leslie Knope
Leslie Knope, please choose your symbol 'X' or 'O', default is 'X': press return to skip
Leslie Knope, Your symbol is 'X'
Player 2, please enter your name:
Ron Swanson
Ron Swanson, Your symbol is O
=====================================================
Leslie Knope is playing X and Ron Swanson is playing O
=====================================================
Board is:
|   |   |   |
|   |   |   |
|   |   |   |

=====================================================
Leslie Knope is playing X, please choose as empty cell for your next move using the numbers 1 to 9
1
=====================================================
Leslie Knope is playing X and Ron Swanson is playing O
=====================================================
Board is:
| X |   |   |
|   |   |   |
|   |   |   |

=====================================================
Ron Swanson is playing O, please choose as empty cell for your next move using the numbers 1 to 9
2
=====================================================
Leslie Knope is playing X and Ron Swanson is playing O
=====================================================
Board is:
| X | O |   |
|   |   |   |
|   |   |   |

=====================================================
Leslie Knope is playing X, please choose as empty cell for your next move using the numbers 1 to 9
5
=====================================================
Leslie Knope is playing X and Ron Swanson is playing O
=====================================================
Board is:
| X | O |   |
|   | X |   |
|   |   |   |

=====================================================
Ron Swanson is playing O, please choose as empty cell for your next move using the numbers 1 to 9
3
=====================================================
Leslie Knope is playing X and Ron Swanson is playing O
=====================================================
Board is:
| X | O | O |
|   | X |   |
|   |   |   |

=====================================================
Leslie Knope is playing X, please choose as empty cell for your next move using the numbers 1 to 9
6
=====================================================
Leslie Knope is playing X and Ron Swanson is playing O
=====================================================
Board is:
| X | O | O |
|   | X | X |
|   |   |   |

=====================================================
Ron Swanson is playing O, please choose as empty cell for your next move using the numbers 1 to 9
4
=====================================================
Leslie Knope is playing X and Ron Swanson is playing O
=====================================================
Board is:
| X | O | O |
| O | X | X |
|   |   |   |

=====================================================
Leslie Knope is playing X, please choose as empty cell for your next move using the numbers 1 to 9
8
=====================================================
Leslie Knope is playing X and Ron Swanson is playing O
=====================================================
Board is:
| X | O | O |
| O | X | X |
|   | X |   |

=====================================================
Ron Swanson is playing O, please choose as empty cell for your next move using the numbers 1 to 9
7
=====================================================
Leslie Knope is playing X and Ron Swanson is playing O
=====================================================
Board is:
| X | O | O |
| O | X | X |
| O | X |   |

=====================================================
Leslie Knope is playing X, please choose as empty cell for your next move using the numbers 1 to 9
9
Leslie Knope has won!
The final board state is:
=====================================================
Leslie Knope is playing X and Ron Swanson is playing O
=====================================================
Board is:
| X | O | O |
| O | X | X |
| O | X | X |

=====================================================
```




