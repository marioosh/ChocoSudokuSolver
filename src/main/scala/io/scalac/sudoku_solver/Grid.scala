package io.scalac.sudoku_solver

case class Grid[A](values: Vector[Vector[A]]) {

  def updated(x: Int, y: Int)(value: A) =
    Grid(
      values.updated(x, values(x).updated(y, value))
    )

  def getValue(x: Int, y: Int): A = values(x)(y)

  def map[B](f: A => B): Grid[B] = Grid(values.map(_.map(f)))
}

object Grid {

  val SIZE: Int = 9

  def fill[A](values: Vector[Vector[A]]) = Grid(values)

  def fill[A](value: A) = Grid(Vector.fill(SIZE, SIZE)(value))
}
