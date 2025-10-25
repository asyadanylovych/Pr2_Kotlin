fun main() {
    val coalEmission = calculateEmissionCoal(150.00, 20.47, 412_407.75)
    println("Валовий викид вугілля: %.2f т".format(coalEmission))

    val oilEmission = calculateEmissionOil(0.57, 40.40, 175_657.21)
    println("Валовий викид мазуту: %.2f т".format(oilEmission))

    val gasEmission = calculateEmissionGas()
    println("Валовий викид природного газу: %.2f т".format(gasEmission))

    val totalEmission = coalEmission + oilEmission + gasEmission
    println("\nЗагальний валовий викид: %.2f т".format(totalEmission))
}


fun calculateEmissionCoal(k: Double, q: Double, b: Double): Double{ //викид вугілля
    val E : Double = 1e-6 * k * q * b
    return E
}
fun calculateEmissionOil(k: Double, q: Double, b: Double): Double { //викид мазуту
    return 1e-6 * k * q * b
}

// При спалюванні природного газу тверді частинки не утворюються
fun calculateEmissionGas(): Double {
    return 0.0
}

