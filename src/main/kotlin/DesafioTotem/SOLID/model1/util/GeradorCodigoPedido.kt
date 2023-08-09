package DesafioTotem.SOLID.model1.util

class GeradorCodigoPedido {
    private var pedidoCounter = 1

    fun gerarCodigoPedido(): String {
        val codigoPedido = pedidoCounter.toString().padStart(6, '0')
        pedidoCounter++
        return codigoPedido
    }
}
