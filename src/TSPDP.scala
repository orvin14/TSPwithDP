import scala.io.Source

object TSPDP {
  def readMatrixFromFile(filename: String): Array[Array[Long]] = {
  val lines = Source.fromFile(filename).getLines().toList
  lines.map(_.trim.split("\\s+").map(_.toLong)).toArray
}

    def tsp(graph: Array[Array[Long]]): (Long, List[Int]) = {
    val n = graph.length
    if (n == 1) return (0L, List(0, 0))
    val memo = Array.fill(1 << n, n)(Long.MaxValue)
    val parent = Array.fill(1 << n, n)(-1)
    memo(1)(0) = 0L

    for (mask <- 1 until (1 << n)) {
        for (u <- 0 until n if (mask & (1 << u)) != 0) {
        if (memo(mask)(u) != Long.MaxValue) {
            for (v <- 0 until n if (mask & (1 << v)) == 0) {
            val nextMask = mask | (1 << v)
            val newDist = memo(mask)(u) + graph(u)(v)
            if (newDist < memo(nextMask)(v)) {
                memo(nextMask)(v) = newDist
                parent(nextMask)(v) = u
            }
            }
        }
        }
    }

    var minCost = Long.MaxValue
    var lastNode = -1
    val fullMask = (1 << n) - 1
    for (i <- 1 until n) {
        if (memo(fullMask)(i) != Long.MaxValue) {
        val cost = memo(fullMask)(i) + graph(i)(0)
        if (cost < minCost) {
            minCost = cost
            lastNode = i
        }
        }
    }

    var mask = fullMask
    var path = List[Int]()
    var curr = lastNode
    if (lastNode == -1) return (minCost, List())
    while (curr != 0) {
        path = curr :: path
        val temp = parent(mask)(curr)
        mask ^= (1 << curr)
        curr = temp
    }
    path = 0 :: (path:+0)

    (minCost, path)
    }


  def main(args: Array[String]): Unit = {
    val filename = if (args.nonEmpty) args(0) else "matrix.txt"
    val graph = readMatrixFromFile(filename)
    val (cost, path) = tsp(graph)
    println(s"Minimum cost: $cost")
    if (path.nonEmpty) println(s"Path: ${path.mkString(" -> ")}") else println("No valid path found.")
  }
}
