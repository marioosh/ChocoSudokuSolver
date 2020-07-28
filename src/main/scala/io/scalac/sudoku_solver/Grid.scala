package io.scalac.sudoku_solver

import scala.annotation.tailrec

case class Grid[A](values: Vector[Vector[A]]) {

  def updated(x: Int, y: Int)(value: A) =
    Grid(
      values.updated(x, values(x).updated(y, value))
    )

  def getValue(x: Int, y: Int): A = values(x)(y)

  def map[B](f: A => B): Grid[B] = Grid(values.map(_.map(f)))
}

object Grid {

  def withColumns[A](grid: Grid[A]): Grid[A] = {
    val indexes = for {
      i <- 0 until SIZE
      j <- 0 until SIZE
    } yield i -> j

    indexes
      .toList
      .foldLeft(grid.copy()) { case (newGrid, (i, j)) =>
        newGrid.updated(j, i)(grid.getValue(i, j))
      }
  }

  def withSectors[A](grid: Grid[A]): Grid[A] = {
    val indexes = for {
      i <- 0 until 3
      j <- 0 until 3
      k <- 0 until 3
    } yield (i, j, k)

    indexes
      .toList
      .foldLeft(grid.copy()) {
        case (newGrid, (i, j, k)) =>

          newGrid
            .updated(j + k * 3, i)(grid.getValue(k * 3, i + j * 3))
            .updated(j + k * 3, i + 3)(grid.getValue(1 + k * 3, i + j * 3))
            .updated(j + k * 3, i + 6)(grid.getValue(2 + k * 3, i + j * 3))
      }

  }

  val SIZE: Int = 9

  def fill[A](values: Vector[Vector[A]]) = Grid(values)

  def fill[A](value: A) = Grid(Vector.fill(SIZE, SIZE)(value))

  def display[A](grid: Grid[A]): String = {
    grid.values.map(_.mkString("\t", "  ", "\t")).mkString("\n")
  }
}
