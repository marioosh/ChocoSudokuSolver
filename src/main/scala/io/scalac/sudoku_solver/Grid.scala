package io.scalac.sudoku_solver

import org.chocosolver.solver.variables.IntVar

case class Grid(values: Vector[Vector[Int]]) {

  def updated(x: Int, y: Int)(value: Int) = Grid.updated(values, x, y)(value)

  def getValue(x: Int, y: Int): Int = values(x)(y)
}

object Grid {
  type InnerGrid = Vector[Vector[Int]]

  val SIZE: Int = 9

  def fill(values: InnerGrid) = Grid(values)

  def fill(value: Int) = Grid(Vector.fill(SIZE, SIZE)(value))

  def empty = fill(0)

  def updated(grid: InnerGrid, x: Int, y: Int)(value: Int) =
    Grid(
      grid.updated(x, grid(x).updated(y, value))
    )
}
