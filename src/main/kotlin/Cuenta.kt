package org.example

open class Cuenta(
    saldoInicial: Double,
    protected var tasaAnual: Double // porcentaje
) {
    protected var saldo: Double = saldoInicial
    protected var numeroConsignaciones: Int = 0
    protected var numeroRetiros: Int = 0
    protected var comisionMensual: Double = 0.0

    /**
     * Consignar dinero en la cuenta. Valida que cantidad > 0.
     */
    open fun consignar(cantidad: Double) {
        require(cantidad > 0) { "La cantidad a consignar debe ser positiva." }
        saldo += cantidad
        numeroConsignaciones++
    }

    /**
     * Retirar dinero de la cuenta. En la clase base no permite sobregiro.
     */
    open fun retirar(cantidad: Double) {
        require(cantidad > 0) { "La cantidad a retirar debe ser positiva." }
        if (cantidad <= saldo) {
            saldo -= cantidad
            numeroRetiros++
        } else {
            println("Retiro denegado: el monto supera el saldo disponible.")
        }
    }

    /**
     * Calcula el interés mensual y lo aplica al saldo.
     * Asume que tasaAnual es porcentaje (p.ej. 3.0 -> 3%).
     */
    protected open fun calcularInteresMensual() {
        val tasaMensual = tasaAnual / 100.0 / 12.0
        val interes = saldo * tasaMensual
        saldo += interes
    }

    /**
     * Extrae mensual: aplica comisión mensual, calcula interés y resetea contadores.
     */
    open fun extractoMensual() {
        saldo -= comisionMensual
        calcularInteresMensual()
        // Reset mensual
        numeroConsignaciones = 0
        numeroRetiros = 0
        comisionMensual = 0.0
    }

    /**
     * Imprime información general de la cuenta.
     */
    open fun imprimir() {
        println("---- Cuenta ----")
        println("Saldo: ${"%.2f".format(saldo)}")
        println("Comisión mensual: ${"%.2f".format(comisionMensual)}")
        println("Consignaciones (mes): $numeroConsignaciones")
        println("Retiros (mes): $numeroRetiros")
    }
}



