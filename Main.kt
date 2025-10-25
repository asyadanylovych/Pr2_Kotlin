fun main() {
    // Вхідні дані для варіанту 8
    val coalMass = 412_407.75      // т
    val oilMass = 175_657.21       // т
    val gasVolume = 195_337.23     // м³

    // Теплота згоряння (вугілля)
    val qCoal = 20.47              // МДж/кг
    val q4Coal = 1.5               // % втрат тепла
    val aCoal = 25.20              // зольність (%)
    val awinCoal = 0.80            // частка леткої золи
    val gwinCoal = 1.5             // горючі речовини в золі (%)
    val etaCoal = 0.985            // ефективність фільтра

    // Теплота згоряння (мазут)
    val qOilRaw = 40.40            // МДж/кг (горюча маса)
    val wOil = 2.0                 // вологість (%)
    val qOil = qOilRaw * (1 - wOil / 100) - 2.51 * wOil / 100  // робоча теплота
    val aOil = 0.15                // зольність (%)
    val awinOil = 1.00             // частка леткої золи
    val etaOil = 0.985             // ефективність фільтра

    // Розрахунок показників емісії
    val kCoal = calculateKCoal(aCoal, awinCoal, gwinCoal, etaCoal, qCoal, q4Coal)
    val kOil = calculateKOil(aOil, awinOil, etaOil, qOil)

    // Розрахунок валових викидів
    val coalEmission = calculateEmission(kCoal, qCoal, coalMass)
    val oilEmission = calculateEmission(kOil, qOil, oilMass)
    val gasEmission = 0.0

    val totalEmission = coalEmission + oilEmission + gasEmission

    println("РОЗРАХУНКИ ДЛЯ ВАРІАНТУ 8")
    println("Показник емісії вугілля: %.2f г/ГДж".format(kCoal))
    println("Валовий викид вугілля: %.2f т".format(coalEmission))

    println("Показник емісії мазуту: %.2f г/ГДж".format(kOil))
    println("Валовий викид мазуту: %.4f т".format(oilEmission))

    println("Показник емісії газу: 0.00 г/ГДж")
    println("Валовий викид газу: %.2f т".format(gasEmission))

    println("\nЗагальний валовий викид: %.2f т".format(totalEmission))

    // Контрольний приклад
    val kControl = calculateKCoal(25.20, 0.80, 1.5, 0.985, 20.47, 1.5)
    println("\n Контрольний приклад ")
    println("Розрахований показник емісії (k): %.2f г/ГДж".format(kControl))

}

// Формула (2.2) для вугілля
fun calculateKCoal(a: Double, awin: Double, gwin: Double, eta: Double, q: Double, q4: Double): Double {
    return (1_000_000 / q) * (a / (100 - q4)) * awin * (1 - eta) * (1 - gwin / 100)
}

// Формула (2.2) для мазуту
fun calculateKOil(a: Double, awin: Double, eta: Double, q: Double): Double {
    return (1_000_000 / q) * a * awin * (1 - eta)
}

// Формула (2.1) — загальна для всіх видів палива
fun calculateEmission(k: Double, q: Double, b: Double): Double {
    return 1e-6 * k * q * b
}

