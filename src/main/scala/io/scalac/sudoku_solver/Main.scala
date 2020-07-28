package io.scalac.sudoku_solver

import org.chocosolver.solver.Model

object Main extends App {

  println("Welcome to Sudoku Solver\nLet's solve sample puzzle")

  val grid = Grid(Vector(
    Vector(0, 0, 0, 2, 0, 0, 0, 0, 0),
    Vector(0, 8, 0, 0, 3, 0, 0, 7, 0),
    Vector(3, 0, 0, 5, 0, 4, 0, 0, 0),
    Vector(0, 0, 0, 0, 0, 0, 0, 2, 8),
    Vector(8, 3, 0 ,0, 1, 0, 0, 0, 0),
    Vector(0, 4, 0, 7, 2, 0, 3, 5, 1),
    Vector(0, 7, 0, 0, 5, 6, 0, 0, 4),
    Vector(0, 0, 3, 0, 0, 0, 0, 0, 0),
    Vector(2, 0, 5, 4, 0, 1, 6, 0, 3)
  ))

  val model = new Model("Sudoku solver")

  val rows = grid.map {
    case 0 => model.intVar(1, 9)
    case n => model.intVar(n)
  }

  val cols = Grid.withColumns(rows)
  val sectors = Grid.withSectors(rows)

  val allConstraints = rows.values ++ cols.values ++ sectors.values


  allConstraints.foreach { contraints =>
    model.post(model.allDifferent(contraints: _*))
  }
  val solver = model.getSolver

  if(solver.solve()){
    println(s"Solved!")
    println(Grid.display(rows.map(_.getValue())))

  } else {
    println("Didn't find any solution! :/")
  }

}
