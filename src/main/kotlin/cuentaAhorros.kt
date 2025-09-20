package org.example

class CuentaAhorros(
    saldoInicial: Double,
    tasaAnual: Double
) : Cuenta(saldoInicial, tasaAnual) {

    companion object {
        private const val SALDO_MIN_ACTIVO = 10_000.0
        private const val NUM_RETIROS_SIN_COMISION = 4
        private const val COMISION_POR_RETIRO_ADICIONAL = 1000.0
    }

    // Guardamos el estado activo para seguir el enunciado; lo recalculamos cuando corresponde.
    private var activa: Boolean = saldo >= SALDO_MIN_ACTIVO

    override fun consignar(cantidad: Double) {
        if (!activa) {
            println("Consignación denegada: la cuenta de ahorros está inactiva (saldo < $SALDO_MIN_ACTIVO).")
            return
        }
        super.consignar(cantidad)
    }

    override fun retirar(cantidad: Double) {
        if (!activa) {
            println("Retiro denegado: la cuenta de ahorros está inactiva (saldo < $SALDO_MIN_ACTIVO).")
            return
        }
        super.retirar(cantidad)
    }

    override fun extractoMensual() {
        if (numeroRetiros > NUM_RETIROS_SIN_COMISION) {
            val retirosExtra = numeroRetiros - NUM_RETIROS_SIN_COMISION
            comisionMensual += retirosExtra * COMISION_POR_RETIRO_ADICIONAL
        }
        super.extractoMensual()
        // Recalcular estado de la cuenta después del extracto
        activa = saldo >= SALDO_MIN_ACTIVO
    }

    override fun imprimir() {
        val transacciones = numeroConsignaciones + numeroRetiros
        println("---- Cuenta de Ahorros ----")
        println("Saldo: ${"%.2f".format(saldo)}")
        println("Comisión mensual: ${"%.2f".format(comisionMensual)}")
        println("Transacciones (mes): $transacciones (consignaciones: $numeroConsignaciones, retiros: $numeroRetiros)")
        println("Estado: ${if (activa) "Activa" else "Inactiva"}")
    }
}