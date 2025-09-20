package org.example


class CuentaCorriente(
    saldoInicial: Double,
    tasaAnual: Double
) : Cuenta(saldoInicial, tasaAnual) {

    private var sobregiro: Double = 0.0

    override fun retirar(cantidad: Double) {
        require(cantidad > 0) { "La cantidad a retirar debe ser positiva." }
        if (cantidad <= saldo) {
            saldo -= cantidad
            numeroRetiros++
        } else {
            val exceso = cantidad - saldo
            sobregiro += exceso
            saldo = 0.0
            numeroRetiros++
        }
    }

    override fun consignar(cantidad: Double) {
        require(cantidad > 0) { "La cantidad a consignar debe ser positiva." }
        var restante = cantidad
        var contado = false

        if (sobregiro > 0.0) {
            val reduccion = minOf(restante, sobregiro)
            sobregiro -= reduccion
            restante -= reduccion
            contado = true // ya hubo una consignación (transacción)
        }

        if (restante > 0.0) {
            // super.consignar registra la consignación y suma al saldo
            super.consignar(restante)
        } else if (contado) {
            // si sólo redujo sobregiro (no quedó resto para saldo), contar la consignación
            numeroConsignaciones++
        }
    }

    override fun extractoMensual() {
        super.extractoMensual()
        // Nota: sobregiro se mantiene; no se especifica cobro por sobregiro en el enunciado
    }

    override fun imprimir() {
        val transacciones = numeroConsignaciones + numeroRetiros
        println("---- Cuenta Corriente ----")
        println("Saldo: ${"%.2f".format(saldo)}")
        println("Comisión mensual: ${"%.2f".format(comisionMensual)}")
        println("Transacciones (mes): $transacciones (consignaciones: $numeroConsignaciones, retiros: $numeroRetiros)")
        println("Sobregiro: ${"%.2f".format(sobregiro)}")
    }
}
