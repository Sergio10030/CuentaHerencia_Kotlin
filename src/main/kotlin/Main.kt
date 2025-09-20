package org.example


fun main() {
    println("=== BANCO KOTLIN ===")
    println("Seleccione el tipo de cuenta:")
    println("1. Cuenta de Ahorros")
    println("2. Cuenta Corriente")
    print("Opción: ")
    val opcion = readLine()!!.toInt()

    print("Ingrese saldo inicial: ")
    val saldoInicial = readLine()!!.toDouble()

    print("Ingrese tasa anual (%): ")
    val tasa = readLine()!!.toDouble()

    when (opcion) {
        1 -> menuCuentaAhorros(CuentaAhorros(saldoInicial, tasa))
        2 -> menuCuentaCorriente(CuentaCorriente(saldoInicial, tasa))
        else -> println("Opción inválida.")
    }
}

fun menuCuentaAhorros(cuenta: CuentaAhorros) {
    var salir = false
    while (!salir) {
        println("\n=== MENÚ CUENTA DE AHORROS ===")
        println("1. Consignar")
        println("2. Retirar")
        println("3. Aplicar extracto mensual")
        println("4. Mostrar información")
        println("5. Salir")
        print("Opción: ")

        when (readLine()!!.toInt()) {
            1 -> {
                print("Monto a consignar: ")
                cuenta.consignar(readLine()!!.toDouble())
            }
            2 -> {
                print("Monto a retirar: ")
                cuenta.retirar(readLine()!!.toDouble())
            }
            3 -> cuenta.extractoMensual()
            4 -> cuenta.imprimir()
            5 -> salir = true
            else -> println("Opción inválida.")
        }
    }
}

fun menuCuentaCorriente(cuenta: CuentaCorriente) {
    var salir = false
    while (!salir) {
        println("\n=== MENÚ CUENTA CORRIENTE ===")
        println("1. Consignar")
        println("2. Retirar")
        println("3. Aplicar extracto mensual")
        println("4. Mostrar información")
        println("5. Salir")
        print("Opción: ")

        when (readLine()!!.toInt()) {
            1 -> {
                print("Monto a consignar: ")
                cuenta.consignar(readLine()!!.toDouble())
            }
            2 -> {
                print("Monto a retirar: ")
                cuenta.retirar(readLine()!!.toDouble())
            }
            3 -> cuenta.extractoMensual()
            4 -> cuenta.imprimir()
            5 -> salir = true
            else -> println("Opción inválida.")
        }
    }
}
